package kpage.chklists.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import kpage.chklists.pojos.utils.XMLUtils;

@Entity
public class ChecklistItem {
	
	@Id
	@GeneratedValue
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private boolean checked;
	private String name;
	private String notes;
	
	public ChecklistItem() {
		this(false, "");
	}

	public ChecklistItem(boolean checked, String name) {
		this(checked, name, null);
	}

	public ChecklistItem(boolean checked, String name, String notes) {
		setChecked(checked);
		setName(name);
		setNotes(notes);
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNotes(String notes) {
		if (notes != null && !notes.isEmpty()) this.notes = notes;
		else clearNotes();
	}

	public void clearNotes() {
		this.notes = null;
	}

	public String getNotes() {
		return notes;
	}

	public boolean hasNotes() {
		return this.notes != null;
	}

	public String getNotesNoNull() {
		if (this.notes != null) return this.notes;
		return "";
	}

	@Override
	public String toString() {
		String ret = getChecked() ? "[*] " : "[ ] ";
		ret += getName();
		if (hasNotes()) ret += " | " + getNotes();
		return ret;
	}

	public String toXML() {
		String chk = getChecked() ? " checked=\"true\"" : " checked=\"false\"";
		String notes;
		if (hasNotes()) {
			String rnotes = XMLUtils.escapeChars(getNotes());
			notes = " notes=\"" + rnotes + "\"";
		} else notes = "";
		return "<item" + chk + notes + ">" + XMLUtils.escapeChars(getName()) + "</item>";
	}
}

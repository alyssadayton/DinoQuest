package Start;
import java.util.Date;

public class Notes {

	private String notes;
	
public Notes() {
	
}
public Notes(String notes) {
	this.notes = notes;
	new Date();
}

public void setNotes(String notes) {
	this.notes = notes;
}

public String getNotes() {
	return notes;
}
}

package pdf;


public class CurrentEvent {

    private String currentDate;
    private String currentTitle;
    private String currentNotes;

    // Constructor
    public CurrentEvent(String currentDate, String currentTitle, String currentNotes) {
        this.currentDate = currentDate;
        this.currentTitle = currentTitle;
        this.currentNotes = currentNotes;
    }

    // returns currentDate
    public String getCurrentDate() {
        return this.currentDate;
    }

    // returns currentTitle
    public String currentTitle() {
        return this.currentTitle;
    }

    // returns currentNotes
    public String currentNotes() {
        return this.currentNotes;
    }

    // Prints the object in text form
    public String toString() {
        return this.currentDate  + "  |  " + this.currentTitle + "  |  " + this.currentNotes;
    }
}


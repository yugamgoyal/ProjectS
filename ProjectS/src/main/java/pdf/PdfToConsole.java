package pdf;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Scanner;

import calendar.CalendarQuickstart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfToConsole {
    public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Loading an existing document
        File file = new File("syllabi/Template_Version_1.72.pdf");
        PDDocument document = PDDocument.load(file);
        // Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        // Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        System.out.println(text);
        // Closing the document
        document.close();
        // This method call gets each row
        ArrayList<CurrentEvent> events = getsEachEvent(text);
        printsAllEvents(events);
        CalendarQuickstart.addEventsToCalendar(events);
    }

    // This method creates an ArrayList and saves each event in it
    public static ArrayList<CurrentEvent> getsEachEvent(String text) {
        // The ArrayList to which data will be added
        ArrayList<CurrentEvent> allEvents = new ArrayList<CurrentEvent>();
        // The finals needed
        final String INTIAL_KEY = "notes";
        final String BREAKER = "/owo\\";
        final String END_KEY = "Hidden";
        final String NEW_LINE = "\n";
        // Creates a scanner for the text
        Scanner allText = new Scanner(text);

        // Runs until it detects the word "notes" which is the starting of the code
        while (allText.hasNext()) {
            if (allText.next().equalsIgnoreCase(INTIAL_KEY)) {
                break;
            }
        }

        String currentDate = "";
        String currentTitle = "";
        String currentNotes = "";

        // Makes sure you only run until there is words
        while (allText.hasNext()) {

            String current = allText.next();
            // If the word is Hidden the code stops -- might be a flaw in the code for now
            if(current.equals(END_KEY)) {
                break;
            }

            // Gets the Date
            while (!current.equals(BREAKER) && !current.equals(NEW_LINE)) {
                currentDate += current;
                current = allText.next();
            }

            // Gets the Title
            current = allText.next();
            while (!current.equals(BREAKER) && !current.equals(NEW_LINE)) {
                currentTitle += current + " ";
                current = allText.next();
            }

            // Gets the Notes
            current = allText.next();
            while (!current.equals(BREAKER) && !current.equals(NEW_LINE)) {
                currentNotes += current + " ";
                current = allText.next();
            }

            // Adds the values to a list which saves an Event Object
            allEvents.add(new CurrentEvent(currentDate, currentTitle, currentNotes));

            // Reset Values
            currentDate = "";
            currentTitle = "";
            currentNotes = "";
        }

        allText.close();

        return allEvents;
    }

    // Prints all the events
    public static void printsAllEvents(ArrayList<CurrentEvent> allEvents) {

        // Prints the values
        for(CurrentEvent e: allEvents) {
            System.out.println(e);
        }

    }
}

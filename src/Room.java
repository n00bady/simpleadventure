import java.util.Vector;

public class Room {
    // This is the room class each room belongs is of this class
    // TODO: Need to have:
    //                      a title(String) and methods to set and get that
    //                      a description(String) and methods to set and get that
    //                      the exits(Vector) and methods to set and get them
    private String title;
    private String description;
    private Vector exits;

    // Blank constructor
    public Room() {
        title = "";
        description = "";
        exits = new Vector();
    }

    // Title only constructor
    public Room(String t) {
        title = t;
        description = "";
        exits = new Vector();
    }

    // Full constructor
    public Room(String t, String d) {
        title = t;
        description = d;
        exits = new Vector();
    }

    // Set title
    public void setTitle(String roomTitle) {
        title = roomTitle;
    }

    // Get title
    public String getTitle() {
        return title;
    }

    // Set Description
    public void setDescription(String roomDescription) {
        description = roomDescription;
    }

    // Get Description
    public String getDescription() {
        return description;
    }

    // Add an Exit
    public void addExit(Exit e) {
        exits.addElement(e);
    }

    // Remove an Exit
    public void removeExit(Exit e) {
        if (exits.contains(e)) {
            exits.removeElement(e);
        }
    }

    // Return vector exits
    public Vector getExits() {
        return (Vector) exits.clone();
    }
}

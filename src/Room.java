import java.util.ArrayList;
import java.util.Vector;

public class Room {
    // This is the room class each room belongs is of this class
    // we use this to create all rooms for our adventure it needs
    // a title a description and the exits(as a Vector).
    // TODO: In the Future we can add NPCs, Items and other stuff...

    private String title;
    private String description;
    private Vector exits;
    private Vector items;
    private Vector things;

    // Blank constructor
    public Room() {
        title = "";
        description = "";
        exits = new Vector();
        items = new Vector();
        things = new Vector();

    }

    // Title only constructor
    public Room(String t) {
        title = t;
        description = "";
        exits = new Vector();
        items = new Vector();
        things = new Vector();
    }

    // Full constructor
    public Room(String t, String d) {
        title = t;
        description = d;
        exits = new Vector();
        items = new Vector();
        things = new Vector();
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

    // add an item
    public void addItem(Item i) {
        items.addElement(i);
    }

    // remove an item
    public void removeItem(Item i) {
        if (items.contains(i)) {
            items.removeElement(i);
        }
    }

    // return vector items
    public Vector getItems() {
        return (Vector) items.clone();
    }

    // add an item
    public void addThing(Thing t) {
        things.addElement(t);
    }

    // remove an item
    public void removeThing(Thing t) {
        if (things.contains(t)) {
            things.removeElement(t);
        }
    }

    // return vector items
    public Vector getThings() {
        return (Vector) things.clone();
    }

}

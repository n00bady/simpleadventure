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

    private ArrayList<Item> RoomItems;
    private ArrayList<Thing> RoomThings;
    private int numberOfItems;
    private int numberOfThings;

    // Blank constructor
    public Room() {
        title = "";
        description = "";
        exits = new Vector();

        RoomItems = new ArrayList<>();
        RoomThings = new ArrayList<>();
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



    public ArrayList<Item>  getRoomItems() {
        return RoomItems;
    }


    public void setRoomItems(ArrayList<Item> roomItems) {
        this.RoomItems = roomItems;
    }

    public void addObject(Item item){
        RoomItems.add(item);
    }

    public void dropObject(Item item){
        RoomItems.remove(item);
    }




    public ArrayList<Thing>  getRoomThings() {
        return RoomThings;
    }


    public void setRoomThings(ArrayList<Thing> roomThings) {
        this.RoomThings = roomThings;
    }

    public void addObject(Thing thing){
        RoomThings.add(thing);
    }

    public void dropObject(Thing thing){
        RoomThings.remove(thing);
    }



    public int getNumberOfItems() {
        numberOfItems = RoomItems.size();
        return numberOfItems;
    }

    public int getNumberOfThings() {
        numberOfThings = RoomThings.size();
        return numberOfThings;
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




    public void viewRoomItems() {
        System.out.println("Room Items:");
        System.out.println();
        if(RoomItems.size()==0)
            System.out.println("No Items in Room");
        else {
            for(int i = 0 ; i < RoomItems.size(); i++)
                System.out.println(i + "  " + RoomItems.get(i));
        }
        System.out.println();
    }

    public void viewRoomThings() {
        System.out.println("Room Things:");
        System.out.println();
        if(RoomThings.size()==0)
            System.out.println("No Things in Room");
        else {
            for(int i = 0 ; i < RoomThings.size(); i++)
                System.out.println(i + "  " + RoomThings.get(i));
        }
        System.out.println();
    }


    @Override
    public String toString() {
        return "Room [Name=" + title + ", Description=" + description + ", Items=" + RoomItems
                + ", Things=" + RoomThings + ", numberOfItems=" + getNumberOfItems() + ", numberOfThings=" + getNumberOfThings() + "]";
    }


}

import java.io.Serializable;

public class Door implements Serializable {
    // After not careful consideration I decided to make the doors
    // a class on their own.

    private String name;
    private String description;
    private Exit exit;
    private boolean locked;
    private Item requires;

    // Blank Constructor
    public Door() {
        name = "Door";
        description = "A simple door.";
        exit = new Exit();
        locked = false;
        requires = null;
    }

    // Partial Constructor
    public Door(String n, String d, Exit e) {
        name = n;
        description = d;
        exit = e;
        locked = false;
        requires = null;
    }

    // Full Constructor
    public Door(String n, String d, Exit e, Boolean lock, Item req) {
        name = n;
        description = d;
        exit = e;
        locked = lock;
        requires = req;
    }

    // get name
    public String getName() {
        return name;
    }

    // open door
    public Room open() {
        if (locked) {
            return null;
        }
        return exit.getLeadsTo();
    }

    // get Item that required to unlock the door
    public Item getRequires() {
        return (Item) requires;
    }

    // get Descriptions
    public String getDesc() {
        return  (String) description;
    }

    // unlock the door
    public void unlock() {
        locked = false;
    }

    // get exit
    public Exit getExit() {
        return (Exit) exit;
    }
}

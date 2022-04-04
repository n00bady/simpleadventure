public class Door {
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
    public Door(Exit e) {
        name = "Door";
        description = "A simple door.";
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

    // open door
    public Room open() {
        if (locked) {
            return exit.getLeadsTo();
        }
        return null;
    }

    // get exit
    public Exit getExit() {
        return (Exit) exit;
    }
}

public class World {
    // This is the world all the rooms the layout, exits, doors, etc...
    // are placed here.
    // To create a new scenario modify this or create a new similar class
    // and then load in from the mainLoop.initWorld .
    private final Room[] rooms;
    private final Player player = new Player();

    public World() {
        // Room creation
        rooms = new Room[5];
        rooms[0] = new Room("Starting room.", "You start here.");
        rooms[1] = new Room("Second room.", "An empty room.");
        rooms[2] = new Room("Exit room", "You found the exit.");
        rooms[3] = new Room("An empty room", "A large empty room.");
        rooms[4] = new Room("Restroom", "A very dirty and dilapidated restroom. Yuck!");

        // Keys creation
        Item key1 = new Item ("Key", "An small shiny key.");
        // Doors creation
        // !!!DO NOT put . at the names of your doors it's throwing off the use command logic!!!
        Door door1 = new Door("East Door", "An simple door.", new Exit(Exit.EAST, rooms[2]), true, key1);
        Door door2 = new Door("Restroom door", "Humans only! says on the door.", new Exit(Exit.EAST, rooms[4]));
        Door door3 = new Door("Restroom door", "Humans only! says on the door.", new Exit(Exit.WEST, rooms[0]));

        // Exits for each room if they have a door then the exit is added in the door.(a little stupid way of doing it)
        rooms[0].addExit(new Exit(Exit.NORTH, rooms[1])); // start room exits
        rooms[0].addExit(new Exit(Exit.WEST, rooms[3]));
        rooms[0].addDoor(door2);

        rooms[1].addExit(new Exit(Exit.SOUTH, rooms[0])); // second room exits
        rooms[1].addDoor(door1); // the door contains the exit for this place

        rooms[3].addExit(new Exit(Exit.EAST, rooms[0]));

        rooms[4].addDoor(door3);
        // last room doesn't have any exits

        // add items in rooms
        rooms[0].addItem(key1);
        rooms[0].addItem(new Item("Teddy Bear", "A small cute teddy bear."));
        rooms[0].addThing(new Thing("Broken Statue", "A very old broken statue of some long forgotten deity."));

        rooms[4].addThing(new Thing("Bathroom stalls", "Some very dirty bathroom stalls, just from the smell you do not even want to look inside!"));
        rooms[4].addThing(new Thing("Sink", "A small dirty sink, broken on the left corner. Hope you are not thirsty..."));

        // player starting position
        player.setRoom(rooms[0]);

    }

    public Room[] getRooms() {
        return rooms.clone();
    }

    public Player getPlayer() throws CloneNotSupportedException {
        return player.clone();
    }
}

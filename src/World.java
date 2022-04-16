public class World {
    // This is the world all the rooms the layout, exits, doors, etc...
    // are placed here.
    // To create a new scenario modify this or create a new similar class
    // and then load in from the mainLoop.initWorld .
    private final Room[] rooms;
    private final Player player = new Player();

    public World() {
        // Room creation
        rooms = new Room[16];
        rooms[0] = new Room("Starting room.", "You start here."); //key door of Room's 8)
        rooms[1] = new Room("Room No1", "No1");
        rooms[2] = new Room("Room No2", "No2");
        rooms[3] = new Room("Room No3", "No3");
        rooms[4] = new Room("Room No4", "No4");
        rooms[5] = new Room("Room No5", "No5");  // Key of Room's 2)
        rooms[6] = new Room("Room No6", "No6");
        rooms[7] = new Room("Room No7", "No7");
        rooms[8] = new Room("Room No8", "No8");  // Key of Room's 6)
        rooms[9] = new Room("Room No9", "No9");  // Key of Room's 7)
        rooms[10] = new Room("Room No10", "No10");
        rooms[11] = new Room("Room No10", "No10");
        rooms[12] = new Room("Room No10", "No10");
        rooms[13] = new Room("Room No10", "No10");
        rooms[14] = new Room("Room No10", "No10");
        rooms[15] = new Room("Final Room", "Final Room");


        // Keys creation
        Item key2d = new Item ("Key D2", "A small shiny key.");
        Item key7d = new Item ("Key D7", "A small shiny key.");
        Item key8d = new Item ("Key D8", "A small shiny key.");
        Item key6d = new Item ("Key D6", "A small shiny key.");
        Item FinalKey = new Item ("Final Key", "A small shiny key.");
        // Doors creation
        // !!!DO NOT put . at the names of your doors it's throwing off the use command logic!!!
        Door door2 = new Door("West Door", "2nd Room", new Exit(Exit.WEST, rooms[2]), true, key2d);
        Door door6 = new Door("East Door", "6th Room", new Exit(Exit.EAST, rooms[6]), true, key6d);
        Door door8 = new Door("North Door", "8th Room", new Exit(Exit.NORTH, rooms[8]), true, key8d);
        Door door7 = new Door("North Door", "7th Room", new Exit(Exit.NORTH, rooms[7]), true, key7d);
        Door doorEND = new Door("North Door", "End Room", new Exit(Exit.NORTH, rooms[15]), true, FinalKey);
        //Door door6 = new Door("Restroom door", "Humans only! says on the door.", new Exit(Exit.EAST, rooms[6]));
        //Door door8 = new Door("Restroom door", "Humans only! says on the door.", new Exit(Exit.NORTH, rooms[8]));

        // Exits for each room if they have a door then the exit is added in the door.(a little stupid way of doing it)
        rooms[0].addExit(new Exit(Exit.NORTH, rooms[4])); // start room exits
        //rooms[0].addExit(new Exit(Exit.WEST, rooms[2]));
        rooms[0].addExit(new Exit(Exit.EAST, rooms[1]));
        rooms[0].addDoor(door2);

        rooms[1].addExit(new Exit(Exit.WEST, rooms[0]));
        rooms[1].addExit(new Exit(Exit.NORTH, rooms[10]));

        rooms[2].addExit(new Exit(Exit.EAST, rooms[0]));
        rooms[2].addExit(new Exit(Exit.WEST, rooms[3]));

        rooms[3].addExit(new Exit(Exit.EAST, rooms[2]));

        rooms[4].addExit(new Exit(Exit.SOUTH, rooms[0]));
        rooms[4].addExit(new Exit(Exit.WEST, rooms[5]));
        rooms[4].addDoor(door7);

        rooms[5].addExit(new Exit(Exit.EAST, rooms[4]));

        rooms[6].addExit(new Exit(Exit.WEST, rooms[10]));
        rooms[6].addExit(new Exit(Exit.NORTH, rooms[9]));

        rooms[7].addExit(new Exit(Exit.SOUTH, rooms[4]));
        rooms[7].addExit(new Exit(Exit.NORTH, rooms[11]));

        rooms[8].addExit(new Exit(Exit.SOUTH, rooms[10]));

        rooms[9].addExit(new Exit(Exit.SOUTH, rooms[6]));
        rooms[9].addExit(new Exit(Exit.EAST, rooms[0]));

        rooms[10].addExit(new Exit(Exit.SOUTH, rooms[1]));
        rooms[10].addDoor(door6);
        rooms[10].addDoor(door8);

        rooms[11].addExit(new Exit(Exit.SOUTH, rooms[7]));
        rooms[11].addExit(new Exit(Exit.EAST, rooms[12]));
        rooms[11].addExit(new Exit(Exit.WEST, rooms[13]));

        rooms[12].addExit(new Exit(Exit.WEST, rooms[11]));

        rooms[13].addExit(new Exit(Exit.EAST, rooms[11]));
        rooms[13].addExit(new Exit(Exit.WEST, rooms[14]));
        rooms[13].addDoor(doorEND);

        // add items in rooms
        rooms[0].addItem(key8d);
        rooms[5].addItem(key2d);
        rooms[9].addItem(key7d);
        rooms[8].addItem(key6d);
        rooms[12].addItem(FinalKey);
        rooms[0].addItem(new Item("Teddy Bear", "A small cute teddy bear."));
        rooms[0].addThing(new Thing("Broken Statue", "A very old broken statue of some long forgotten deity."));

        rooms[4].addThing(new Thing("Bathroom stalls", "Some very dirty bathroom stalls, just from the smell you do not even want to look inside!"));
        rooms[4].addThing(new Thing("Sink", "A small dirty sink, broken on the left corner. Hope you are not thirsty..."));

        rooms[8].addItem(new Item("Skooma", "An illegal narcotic that is used throughout Tamriel. xD"));

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

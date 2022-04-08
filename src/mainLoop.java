import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringJoiner;

public class mainLoop {
    // TODO: More commands, fix the exceptions, prettify the displaying of all.
    //      Add an actual smoll scenario instead of just example cases!
    //      better selection recognition for playerInput().

    private Room rooms[];
    private Player p1 = new Player();
    private int slowdown = 1500; // Used to simulate the time it takes to do an action
    boolean quit = false;

    public void startLoop() {
        // init player
        System.out.println("Choose your name: ");
        Scanner input = new Scanner(System.in);
        p1.setName(input.nextLine());
        System.out.println("Your name is: " + p1.getName());
        // init world
        initWorld();

        // this is the main game loop
        do {
            // displaying rooms
            displayRoom();
            // displaying exits and doors
            displayExits();
            // displaing items and things
            displayItemsAndThings();
            // get player input
            playerInput();

            // winning condition
            if (p1.getRoom() == rooms[2]) {
                System.out.println("Congrats you found the exit!");
                quit = true;
            }
        } while (!quit);
    }

    // In theory, we can have multiple world and initialize whichever we want.
    // TODO: each world will be it's own class created manually and initWorld() should only
    //      initialize the world it's been asked too...
    public void initWorld() {

        // Room creation
        rooms = new Room[5];
        rooms[0] = new Room("Starting room.", "You start here.");
        rooms[1] = new Room("Second room.", "An empty room.");
        rooms[2] = new Room("Exit room", "You found the exit.");
        rooms[3] = new Room("An empty room", "A large empty room.");
        rooms[4] = new Room("Restroom", "A very dirty and dillapitated restroom. Yuck!");

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
        p1.setRoom(rooms[0]);

    }

    // displays the current room title, description and exits
    public void displayRoom() {
        System.out.println();
        System.out.println(">-------------------Current location-------------------<");
        System.out.println(p1.getRoom().getTitle());
        System.out.println("--------------------Description-------------------------");
        System.out.println(p1.getRoom().getDescription());
        System.out.println(">------------------------------------------------------<");
    }

    // displaying the exits & doors of the current room
    public void displayExits() {
        //System.out.println(">------------------------------------------------------<");
        System.out.println("\u001B[33mAvailable exits: \u001b[0m");
        for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements();) {
            Exit an_exit = (Exit) e.nextElement();
            System.out.println("\t" + an_exit.getFullDirectionName());
        }
        System.out.println();
        System.out.println("\u001B[33mAvailable doors: \u001b[0m");
        for (Enumeration d = p1.getRoom().getDoors().elements(); d.hasMoreElements();) {
            Door a_door = (Door) d.nextElement();
            System.out.println("\t" + a_door.getName());
        }
        System.out.println(">------------------------------------------------------<");
    }

    // displaying the Items and Things in the current Room
    public void displayItemsAndThings() {
        //System.out.println(">------------------------------------------------------<");
        System.out.println("\u001B[33mAvailable items:\u001b[0m");
        for (Enumeration i = p1.getRoom().getItems().elements(); i.hasMoreElements();) {
            Item an_item = (Item) i.nextElement();
            System.out.println("\t" + an_item.getName());
        }
        System.out.println();
        System.out.println("\u001B[33mAvailable things: \u001b[0m");
        for (Enumeration t = p1.getRoom().getThings().elements(); t.hasMoreElements();) {
            Thing a_thing = (Thing) t.nextElement();
            System.out.println("\t" + a_thing.getName());
        }
        System.out.println(">------------------------------------------------------<");
    }

    public void displayInventory() {
        System.out.println("⟔---------------------------------------");
        System.out.println(p1.getInvItems()); // TODO: Needs a loop to get only each item's name so it looks good.
        System.out.println("---------------------------------------⟓");
        System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
        System.out.println();
        try {
            System.in.read();
        } catch (Exception ignored) {
        }
    }

    // display description of an item or thing or a doors requirement
    public void displayDesc(Item item) {
        System.out.println(item.getName());
        System.out.print("\t");
        System.out.println(item.getDesc());
    }
    public void  displayDesc(Thing thing) {
        System.out.println(thing.getName());
        System.out.print("\t");
        System.out.println(thing.getDesc());
    }
    public void displayDesc(Door door) {
        System.out.println(door.getName());
        System.out.print("\t");
        System.out.print(door.getRequires());
    }

    //Player input & processing
    public void playerInput() {
        boolean success = false;  // probably there is a better way, but this is what I could do...
        Scanner input = new Scanner(System.in);

        // scan input from player
        System.out.print(">> ");
        String cmd = input.nextLine();
        cmd = cmd.toUpperCase();

        String command;     // verb
        String selection;   // noun(s)
        // make a joiner to join the words after the first, is there any other way to do this easier/faster ?
        // Check if user's input is more than one word
        // if it has more than one word then all the words after the
        // first and put it in a single string selection
        StringJoiner joiner = new StringJoiner(" ");
        String words[] = cmd.split("\\s+");
        command = words[0];
        if (words.length > 1) {
            for (int w=1;w<=(words.length-1);w++) {
                joiner.add(words[w]);
            }
        }
        selection = String.valueOf(joiner);
        // poor mans debbugger
        System.out.println("Used command: " + command);
        System.out.println("Selection: " + selection);
        System.out.println();

        // Switch for commands starts here ---
        try {
            switch (command) {
                case "GO":
                    // check if exit exists then move player
                    for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements(); ) {
                        Exit an_exit = (Exit) e.nextElement();
                        if ((an_exit.getFullDirectionName().compareTo(selection) == 0) ||
                                an_exit.getShortDirectionName().compareTo(selection) == 0) {
                            System.out.println("You move to the " + an_exit.getFullDirectionName() + " room...");
                            p1.setRoom(an_exit.getLeadsTo());
                            success = true;
                        }
                    }
                    if (!success) {
                        System.out.println("You can't go that way!");
                    }
                    Thread.sleep(slowdown);
                    break;
                case "TAKE":
                    // add item to inventory and remove it from the room
                    for (Enumeration i = p1.getRoom().getItems().elements(); i.hasMoreElements(); ) {
                        Item an_item = (Item) i.nextElement();
                        if ((an_item.getName().compareToIgnoreCase(selection) == 0)) {
                            p1.addInvItems(an_item);
                            p1.getRoom().removeItem(an_item);
                            System.out.println("\u001B[33m*Item\u001b[0m " + selection + "\u001B[33m added to inventory*\u001b[0m");
                            success = true;
                        }
                    }
                    if (!success) {
                        System.out.println("You can't take that!");
                    }
                    Thread.sleep(slowdown);
                    break;
                case "DROP":
                    // drop item from inventory and add it to the room
                    // No matter how I do it I get an java.util.ConcurrentModificationException here ???
                    for (Item an_item : p1.getInvItems()) {
                        if (an_item.getName().compareToIgnoreCase(selection) == 0) {
                            p1.removeInvItems(an_item);
                            p1.getRoom().addItem(an_item);
                            success = true;
                            System.out.println("*\u001B[33mItem removed from inventory*\u001b[0m");
                        }
                    }
                    if (!success) {
                        System.out.println("You can't do that!");
                    }
                    Thread.sleep(slowdown);
                    break;
                case "LOOK":
                    // TODO: print all items/things that exist in the room
                    if (selection.equals("AROUND")) {
                        System.out.println("\u001B[33mLooking around for details...\u001b[0m\n");
                        Thread.sleep(slowdown * 2);
                        System.out.println("\u001B[33mThe room is empty.\u001b[0m");
                        System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                        try {
                            System.in.read();
                        } catch (Exception ignored) {
                        }
                    }
                    // print all items in the player's inventory
                    if (selection.equals("INV") || selection.equals("INVENTORY")) {
                        displayInventory();
                    }
                    break;
                case "USE":
                    // use things or items, open doors etc...
                    for (Enumeration d = p1.getRoom().getDoors().elements(); d.hasMoreElements(); ) {
                        Door a_door = (Door) d.nextElement();
                        if (a_door.getName().compareToIgnoreCase(selection) == 0) {
                            if (a_door.open() == null) {
                                System.out.println("The door is locked!");
                                System.out.println("It requires: " + a_door.getRequires().getName());
                                System.out.println(("Checking inventory for required key."));
                                // probably there is a better way to do this...
                                if (p1.getInvItems().contains(a_door.getRequires())) {
                                    System.out.println("You used the " + a_door.getRequires().getName() + " to unlock the door.");
                                    a_door.unlock();
                                    // item is removed from the inventory after use
                                    p1.removeInvItems(a_door.getRequires());
                                    success = true;
                                }
                            } else {
                                // just go through if it's unlocked
                                System.out.println("You go through the " + a_door.getName() + "...");
                                p1.setRoom(a_door.getExit().getLeadsTo());
                                success =true;
                            }
                        }
                    }
                    if (!success) { System.out.println("You can't go through!"); }
                    Thread.sleep(slowdown);
                    break;
                case "QUIT":
                    System.out.println("TERMINATING...");
                    // should we use the quit variable here or this is fine ???
                    System.exit(0);
                default:
                    // if the command is not recognized tell the player
                    System.out.println("Command " + command + " is not valid.");
                    break;
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Wrong input. The command must be like the examples (GO NORTH, TAKE KEY, etc)");
        }
        // switch end ---
    }
}

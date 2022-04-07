import java.util.Enumeration;
import java.util.Scanner;

public class mainLoop {
    // TODO: More commands, fix the exceptions, seperate the displaying of items/things etc from
    //      the displayRoom() method. Add an actual smoll scenario instead of just example cases!

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
    public void initWorld() {

        // Room creation
        rooms = new Room[3];
        rooms[0] = new Room("Starting room.", "You start here.");
        rooms[1] = new Room("Second room.", "An empty room.");
        rooms[2] = new Room("Exit room", "You found the exit.");

        // Keys creation
        Item key1 = new Item ("Key", "An small shiny key.");
        // Doors creation
        Door door1 = new Door("East Door", "An simple door.", new Exit(Exit.EAST, rooms[2]), true, key1);

        // Exits for each room if they have a door then the exit is added in the door.(a little stupid way of doing it)
        rooms[0].addExit(new Exit(Exit.NORTH, rooms[1])); // start room exits
        rooms[1].addExit(new Exit(Exit.SOUTH, rooms[0])); // second room exits
        rooms[1].addDoor(door1); // the door contains the exit for this place
        // last room doesn't have any exits

        // add items in rooms
        rooms[0].addItem(key1);
        rooms[0].addItem(new Item("Teddy Bear", "A small cute teddy bear."));
        rooms[0].addThing(new Thing("Broken Statue", "A very old broken statue of some long forgotten deity."));

        // player starting position
        p1.setRoom(rooms[0]);

    }

    // displays the current room title, description and exits
    public void displayRoom() {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.print("Current location:     ");
        System.out.println(p1.getRoom().getTitle());
        System.out.println("----------------------------------------");
        System.out.println(p1.getRoom().getDescription());
        // this could be a separate method so the player can
        // simply request the exits on their own and not have to get the whole
        // displayRoom again
        System.out.println("\u001B[33mAvailable exits: \u001b[0m");
        for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements();) {
            Exit an_exit = (Exit) e.nextElement();
            System.out.println(an_exit.getFullDirectionName());
        }
        System.out.println("\u001B[33mAvailable doors: \u001b[0m");
        for (Enumeration d = p1.getRoom().getDoors().elements(); d.hasMoreElements();) {
            Door a_door = (Door) d.nextElement();
            System.out.print(a_door.getName());
        }
        System.out.println();
        // just for testing purposes ---
        System.out.println("\u001B[33mAvailable items:\u001b[0m");
        for (Enumeration i = p1.getRoom().getItems().elements(); i.hasMoreElements();) {
            Item an_item = (Item) i.nextElement();
            System.out.println(an_item.getName());
        }
        System.out.println();
        System.out.println("\u001B[33mAvailable things: \u001b[0m");
        for (Enumeration t = p1.getRoom().getThings().elements(); t.hasMoreElements();) {
            Thing a_thing = (Thing) t.nextElement();
            System.out.println(a_thing.getName());
        }
        System.out.println(">--------------------------------------<");
    }

    //Player input
    public void playerInput() {
        boolean success = false;  // probably there is a better way, but this is what I could do...
        Scanner input = new Scanner(System.in);

        System.out.print(">> ");
        String cmd = input.nextLine();
        cmd = cmd.toUpperCase();

        // verb
        String command = cmd.split(" ")[0];
        // noun(s)
        String selection = " ";
        // Check if user's input is more than one word
        // we do this incase there is only a command otherwise we get out of bounds exceptions :P
        if (cmd.indexOf(" ") > 0) {
            selection = cmd.split(" ", 2)[1];
        }
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
                    // print all items/things that exist in the room
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
                        System.out.println(p1.getInvItems());

                        System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
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
                                System.out.println("You go throug the " + a_door.getName() + "...");
                                p1.setRoom(a_door.getExit().getLeadsTo());
                                success =true;
                            }
                        }
                    }
                    if (!success) { System.out.println("You can't go throug!"); }
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

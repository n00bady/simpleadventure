import java.util.Enumeration;
import java.util.Scanner;


public class mainLoop {
    // TODO: Need to separate possibly player input on it's own method also
    //      need to support verbs (parser?) for actions like go, look, pickup etc...

    private Room rooms[];
    private Player p1 = new Player();


    public void startLoop() {
        boolean quit = false;

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
            System.out.print(">> ");
            String cmd = input.nextLine();
            cmd = cmd.toUpperCase();
            //System.out.println(cmd);
            playerInput(cmd);

            // winning condition
            if (p1.getRoom() == rooms[2]) {
                System.out.println("Congrats you found the exit!");
                quit = true;
            }
            // exit condition
            if (cmd.equals("QUIT") || cmd.equals("Q")) {
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
    public void playerInput(String cmd) {
        try {
            // verb
            String command = cmd.split(" ")[0];
            // noun
            String selection = cmd.split(" ", 2)[1];
            System.out.println("Used command: " + command);
            System.out.println("Selection: " + selection);
            System.out.println();

            switch (command) {
                case "GO":
                    //System.out.println("Go Command selected.");
                    for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements(); ) {
                        Exit an_exit = (Exit) e.nextElement();
                        if ((an_exit.getFullDirectionName().compareTo(selection) == 0) ||
                                an_exit.getShortDirectionName().compareTo(selection) == 0) {
                            p1.setRoom(an_exit.getLeadsTo());
                        }
                    }
                    break;
                case "TAKE":
                    // add item to inventory
                    for (Enumeration i = p1.getRoom().getItems().elements(); i.hasMoreElements(); ) {
                        Item an_item = (Item) i.nextElement();
                        if ((an_item.getName().compareToIgnoreCase(selection) == 0)) {
                            p1.addInvItems(an_item);
                            p1.getRoom().removeItem(an_item);
                            System.out.println("\u001B[33m*Item\u001b[0m " + selection + "\u001B[33m added to inventory*\u001b[0m");
                            Thread.sleep(1000);
                        }
                    }
                    break;
                case "DROP":
                    // drop item from inventory
                    for (Item it : p1.getInvItems()) {
                        if ((it.getName().compareToIgnoreCase(selection) == 0)) {
                            p1.removeInvItems(it);
                            p1.getRoom().addItem(it);
                            System.out.println("*\u001B[33mItem removed from inventory*\u001b[0m");
                            Thread.sleep(1000);
                        }
                    }
                    break;
                case "LOOK":
                        if (selection.equals("AROUND")) {
                            System.out.println("\u001B[33mLooking around for details...\u001b[0m\n");
                            Thread.sleep(2500);
                            System.out.println("\u001B[33mThe room is empty.\u001b[0m");
                            System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                            try{System.in.read();}
                            catch(Exception e){}
                        }
                        if (selection.equals("INV") || selection.equals("INVENTORY")){
                            System.out.println(p1.getInvItems());

                            System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                            try{System.in.read();}
                            catch(Exception e){}
                        }
                    break;
                case "USE":
                    // use things or items, open doors etc...
                    for (Enumeration d = p1.getRoom().getDoors().elements(); d.hasMoreElements();) {
                        Door a_door = (Door) d.nextElement();
                        if (a_door.getName().compareToIgnoreCase(selection) == 0) {
                            if (a_door.open() == null) {
                                System.out.println("The door is locked!");
                                System.out.println("It requires: " + a_door.getRequires().getName());
                                System.out.println(("Checking inventory for required key."));
                                Thread.sleep(1500);
                                // probably there is a better way to do this...
                                if (p1.getInvItems().contains(a_door.getRequires())) {
                                    System.out.println("You used the " + a_door.getRequires().getName() + " to unlock the door.");
                                    a_door.unlock();
                                    p1.removeInvItems(a_door.getRequires()); // item is removed from the inventory after use
                                }
                            } else {
                                // just go through if it's unlocked
                                p1.setRoom(a_door.getExit().getLeadsTo());
                            }
                        }
                    }
                    break;
                case "INTERACT":
                    // maybe we can use this for some other stuff...
                    break;
                default:
                    System.out.println("Command " + command + " is not valid.");
                    break;
            }
        } catch (Exception ArrayIndexOutOfBounds) {
            System.err.println("Wrong input. The command must be like the examples (GO NORTH, TAKE KEY, etc)");   //temporarily disabled
        }
    }
}

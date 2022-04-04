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
            String cmd = input.nextLine();
            cmd = cmd.toUpperCase();
            System.out.println(cmd);
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

        // start room exits
        rooms[0].addExit(new Exit(Exit.NORTH, rooms[1]));
        // second room exits
        rooms[1].addExit(new Exit(Exit.SOUTH, rooms[0]));
        rooms[1].addExit(new Exit(Exit.EAST, rooms[2]));
        // last room doesn't have any exits

        // add items in rooms
        rooms[0].addItem(new Item("Key", "A small shiny key."));
        // add things in rooms
        rooms[1].addThing(new Thing("Door", "A big wooden door."));

        // player starting position
        p1.setRoom(rooms[0]);

    }

    // displays the current room title, description and exits
    public void displayRoom() {
        System.out.print("Current location:     ");
        System.out.println(p1.getRoom().getTitle());
        System.out.println("-------------------------------------");
        System.out.println(p1.getRoom().getDescription());
        // this could be a separate method so the player can
        // simply request the exits on their own and not have to get the whole
        // displayRoom again
        System.out.println("Available exits: ");
        for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements();) {
            Exit an_exit = (Exit) e.nextElement();
            System.out.println(an_exit.getFullDirectionName());
        }
        // just for testing purposes ---
        System.out.println("Available items: ");
        for (Enumeration i = p1.getRoom().getItems().elements(); i.hasMoreElements();) {
            Item an_item = (Item) i.nextElement();
            System.out.println(an_item.getName());
        }
        System.out.println("Available things: ");
        for (Enumeration t = p1.getRoom().getThings().elements(); t.hasMoreElements();) {
            Thing a_thing = (Thing) t.nextElement();
            System.out.println(a_thing.getName());
        }
    }

    //Player input
    public void playerInput(String cmd) {
        try {
            // verb
            String command = cmd.split(" ")[0];
            // noun
            String selection = cmd.split(" ")[1];
            //System.out.println("Used command: " + command);
            //System.out.println("Selection: " + selection);
            System.out.println();

            switch (command) {
                case "GO":
                    //System.out.println("Go Command selected.");
                    for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements();) {
                        Exit an_exit = (Exit) e.nextElement();
                        if ((an_exit.getFullDirectionName().compareTo(selection) == 0) ||
                                an_exit.getShortDirectionName().compareTo(selection) == 0) {
                            p1.setRoom(an_exit.getLeadsTo());
                        }
                    }
                    break;
                case "TAKE":
                    // add item to inventory
                            p1.addInvItems(selection);
                    break;
                case "DROP":
                    // drop item from inventory
                            p1.removeInvItems(selection);
                    break;
                case "LOOK":
                        if (selection.equals("AROUND")) {
                            System.out.println("\u001B[33mCurrent Room xD. Looking for details...\u001b[0m\n");
                            Thread.sleep(2500);
                            System.out.println("\u001B[33mThe room is empty.\u001b[0m");
                            System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                            try{System.in.read();}
                            catch(Exception e){}
                        }
                        if (selection.equals("INV") || selection.equals("INVENTORY")){
                            System.out.println("Inventory: " + p1.getInvItems());

                            System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                            try{System.in.read();}
                            catch(Exception e){}
                        }
                    break;
                default:
                    System.out.println("Command " + command + " is not valid.");
                    break;
            }
        } catch (Exception ArrayIndexOutOfBounds) {
            System.err.println("Wrong input. The command must be like the examples (GO NORTH, TAKE KEY, etc)");
        }
    }


}

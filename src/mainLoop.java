import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainLoop {
    // TODO: More commands, fix the exceptions, prettify the displaying of all.
    //      Add an actual small scenario instead of just example cases!
    //      better selection recognition for playerInput().

    public static final String filename = "world.sav";  // the name of the save file TODO: support for multiple saves ?
    private Room[] rooms;
    private Player p1 = new Player();
    private final int slowdown = 1500; // Used to simulate the time it takes to do an action
    boolean quit = false;

    public void startLoop() throws Exception {
        // INIT
        // first check if a save file exists
        File tempFile = new File(filename);
        if (!tempFile.exists()) {
            // if it doesn't create a new player and world
            System.out.println("\u001b[1;36mCreating new World!\u001b[0m");
            // init world
            initWorld();
            System.out.println("Choose your name: ");
            Scanner input = new Scanner(System.in);
            p1.setName(input.nextLine());
            System.out.println("Welcome " + p1.getName() + "!");
        } else {
            // if a save file exists then load the rooms and p1 variables
            System.out.println("\u001b[1;36mSave file found!\u001b[0m");
            System.out.println("\u001b[1;33mLOADING...\u001b[0m");
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream objIN = new ObjectInputStream(fin);
            GameData load_save = (GameData) objIN.readObject();
            rooms = load_save.getRooms();
            p1 = load_save.getPlayer();
            System.out.println("Welcome back " + p1.getName() + "!");
        }
        // this is the main game loop
        do {
            // display current Room
            displayRoom();
            // get and proccess player's input
            playerInput();
            // winning condition
            if (p1.getRoom() == rooms[2]) {
                System.out.println("\u001b[1;32mCongrats you found the exit!!!\u001b[0m");
                quit = true;
            }
        } while (!quit);
    }

    // In theory, we can have multiple world and initialize whichever we want.
    public void initWorld() throws CloneNotSupportedException {
        World newWorld = new World();
        rooms = newWorld.getRooms();
        p1 = newWorld.getPlayer();
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
    }

    // display description Item/Thing/Door
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
        System.out.print(door.getDesc());
    }

    //Player input processing and logic
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
        String[] words = cmd.split("\\s+");
        command = words[0];
        if (words.length > 1) {
            for (int w=1;w<=(words.length-1);w++) {
                joiner.add(words[w]);
            }
        }
        selection = String.valueOf(joiner);

        // poor mans debugger
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
                    // No matter how I do it I get a java.util.ConcurrentModificationException here ???
                    // OK if we create a copy of the arraylist we do not get that exception.
                    ArrayList<Item> inventory = new ArrayList<>(p1.getInvItems());
                    for (Item an_item : inventory) {
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
                    if ((selection.equals("AROUND")) || (selection.equals("A"))) {      // LOOK AROUND
                        System.out.println("\u001B[33mLooking around for details...\u001b[0m\n");
                        Thread.sleep(slowdown * 2);
                        displayExits();
                        displayItemsAndThings();
                        success = true;
                    }
                    // print all items in the player's inventory
                    if (selection.equals("INVENTORY") || selection.equals("INV") || selection.equals("I")) {     // LOOK INVENTORY
                        displayInventory();
                        success = true;
                    }
                    if (selection.equals("EXITS") || selection.equals("E")) {       // LOOK EXITS
                        displayExits();
                        success = true;
                    }
                     if (selection.equals("THINGS") || selection.equals("T")) {     // LOOK THINGS
                         displayItemsAndThings();
                         success = true;
                    }
                    if (!success) { System.out.println("There is nothing here..."); }
                    // wait a little for the player
                    System.out.println("\u001B[38;5;199mPress Enter key to return in exploration\u001b[0m");
                    try {
                        System.in.read();
                    } catch (Exception ignored) {
                    }
                    break;
                case "USE":
                    // use things or items, open doors etc...
                    // TODO: Doors and containers need to use a seperate command OPEN,
                    //      USE will be used with Items and possibly Things.
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
                    System.out.println("\u001b[1;33mSaving game state...\u001b[0m");
                    // Save before you quit
                    FileOutputStream fOut = new FileOutputStream(filename);
                    ObjectOutputStream objOUT = new ObjectOutputStream(fOut);
                    GameData save = new GameData(rooms, p1);
                    objOUT.writeObject(save);
                    System.out.println("\u001b[1;31mTERMINATING...\u001b[0m");
                    // should we use the quit variable here or this is fine ???
                    System.exit(0);
                default:
                    // if the command is not recognized tell the player
                    System.out.println("Command " + command + " is not valid.");
                    break;
            }
        } catch (Exception e) {
            // I think you log exception something like this...
            Logger.getLogger("Commands input.").log(Level.INFO, "An exception in the command's switch occurred: ", e);
        }
        // switch end ---
    }
}

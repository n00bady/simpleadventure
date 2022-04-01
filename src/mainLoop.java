import java.util.Enumeration;
import java.util.Scanner;

public class mainLoop {
    // TODO: Need to separate the display output and possibly player input on their own method also
    //      need to support verbs (parser?) for action like go, look, pickup etc...

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

        do {
            // displaying rooms
            displayRoom();

            // get player input this should be a separate method
            String cmd = input.next();
            cmd = cmd.toUpperCase();
            System.out.println("You chose to go " + cmd);
            for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements();) {
                Exit an_exit = (Exit) e.nextElement();
                if ((an_exit.getFullDirectionName().compareTo(cmd) == 0) || an_exit.getShortDirectionName().compareTo(cmd) == 0) {
                    p1.setRoom(an_exit.getLeadsTo());
                }
            }
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

    // In theory we can have multiple world and initialize whichever we want.
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

        // player starting position
        p1.setRoom(rooms[0]);

    }

    public void displayRoom() {
        System.out.print("Current location:     ");
        System.out.println(p1.getRoom().getTitle());
        System.out.println("-------------------------------------");
        System.out.println(p1.getRoom().getDescription());
        System.out.println("Available exits: ");
        for (Enumeration e = p1.getRoom().getExits().elements(); e.hasMoreElements();) {
            Exit an_exit = (Exit) e.nextElement();
            System.out.println(an_exit.getFullDirectionName());
        }
    }
}

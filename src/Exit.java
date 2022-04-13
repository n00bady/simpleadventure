import java.io.Serializable;

public class Exit implements Serializable {
    // This is the Exit class used to link Rooms together
    // we use this to connect each exit of a room with
    // another room.
    // It needs a direction(full or short name) and another room
    // TODO: possibly add more directions like NorthEast, southwest up/down etc...

    // Numerical codes
    public static final int UNDEFINED = 0;
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;
    public static final int WEST =4;

    // Full name direction strings
    public static final String[] dirFullName = {
            "UNDEFINED",
            "NORTH",
            "SOUTH",
            "EAST",
            "WEST"
    };

    // Short name direction strings
    public static final String[] dirShortName = {
            "NULL",
            "N",
            "S",
            "E",
            "W"
    };

    private int direction;
    private Room leadsTo = null;

    // Full name
    private String fullDirectionName;
    // Short name
    private String shortDirectionName;

    // Blank constructor
    public Exit() {
        direction = Exit.UNDEFINED;
        leadsTo = null;
        fullDirectionName = dirFullName[UNDEFINED];
        shortDirectionName = dirShortName[UNDEFINED];
    }

    // Full constructor
    public Exit(int dir, Room room) {
        direction = dir;

        if (direction <= dirFullName.length) {
            fullDirectionName = dirFullName[direction];
        }
        if (direction <= dirShortName.length) {
            shortDirectionName = dirShortName[direction];
        }

        leadsTo = room;
    }

    // Set full direction name
    public void setDirectionName(String dir) {
        fullDirectionName = dir;
    }

    // Get full direction name
    public String getFullDirectionName() {
        return fullDirectionName;
    }

    // Set short direction name
    public void setShortDirectionName(String dir) {
        shortDirectionName = dir;
    }

    // Get short direction name
    public String getShortDirectionName() {
        return shortDirectionName;
    }

    // Set where it leads to
    public void setLeadsTo(Room room) {
        leadsTo = room;
    }

    // Get where it leads to
    public Room getLeadsTo() {
        return leadsTo;
    }
}

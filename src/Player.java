public class Player {
    // This is the player class anything related to player goes here
    // TODO: Need to have:
    //                      his name(String) and methods to set and get that name
    //                      his HP(Int) and ways to get, increase and decrease it
    //                      his current location (Room) and a method to get it
    //

    private String name;
    private int HP;
    private Room location = null;

    // Blank constructor
    public Player() {
        name = "Player";
        HP = 10;
        location = null;
    }

    // Name only constructor
    public  Player(String Name) {
        name = Name;
        HP = 10;
        location = null;
    }

    // Full constructor
    public Player(String Name, int health, Room Location){
        name = Name;
        HP = health;
        location = Location;
    }

    // Set name
    public void setName(String Name) {
        name = Name;
    }

    // Get name
    public String getName() {
        return name;
    }

    // Set location
    public void setRoom(Room Location) {
        location = Location;
    }

    // Get location
    public Room getRoom() {
        return location;
    }

    // Get increased HP
    public void getIncreaseHP(int heal) {
        HP = HP + heal;
    }

    // Set increased HP
    public int setIncreaseHP() {
        return HP;
    }

    // Get decreased HP
    public void getDecreaseHP(int damage) {
        HP = HP - damage;
    }

    // Set decreased HP
    public int setDecreaseHP() {
        return HP;
    }
}

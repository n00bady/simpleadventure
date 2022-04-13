import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    // This is the player class anything related to player goes here
    // TODO: Need to have:
    //                      his name(String) and methods to set and get that name
    //                      his HP(Int) and ways to get, increase and decrease it
    //                      his current location (Room) and a method to get it
    //

    private String name;
    private int HP;
    private Room location = null;
    private List<Item> inventory = new ArrayList<>(); //inventory ArrayList
    //private Vector inventory;

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

    // Set HP
    public void setHP(int hp) {
        HP = hp;
    }

    // Get HP
    public int getHP(){
        return HP;
    }

    // Set increase HP
    public void increaseHP(int heal) {
        HP = HP + heal;
    }

    // Set decrease HP
    public void decreaseHP(int damage) {
        HP = HP - damage;
    }

    // Add inventory items
    public void addInvItems(Item inv) {
        inventory.add(inv);
    }

    // Remove inventory items
    public void removeInvItems(Item inv) {
        if (inventory.contains(inv)) {
            inventory.remove(inv);
        }
    }

    // Get inventory items
    public ArrayList<Item> getInvItems() {
        //return (Vector)inventory;
        return (ArrayList<Item>)inventory;
    }

}

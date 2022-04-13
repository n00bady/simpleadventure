import java.io.Serializable;

public class Item implements Serializable {
    // This class is used for any pickable item the player encounters
    // TODO: Might need to add more things like damage, effects etc...
    //       if we want to add combat or other mechanics in the game.

    private String name;
    private String desc;

    public Item() {

    }
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
//    public String look() {
//        return getDesc();
//    }

    @Override
    public String toString() {
        return "\u001B[33mItem name= \u001b[0m" + name + "\u001B[33m, description= \u001b[0m" + desc ;
    }



}
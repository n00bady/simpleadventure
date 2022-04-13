import java.io.Serializable;

public class Thing implements Serializable {
    // This is used for non pickable items like statues
    // TODO: Add functionality so it can be used for button and levers

    private String name;
    private String desc;

    public Thing() {

    }
    public Thing(String name, String desc) {
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
        return "Thing name=" + name + ", description=" + desc ;
    }



}
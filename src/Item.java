public class Item {
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
        return "Item name=" + name + ", description=" + desc ;
    }



}
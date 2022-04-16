import java.io.Serializable;

public class NPC implements Serializable {
    private String name;
    private String desc;
    private String dialogue;
    private boolean hostile;
    private int HP,damage;

    public NPC() {
    }

    public NPC(String name, String desc, String dialogue, boolean hostile, int hp, int damage) {
        this.name = name;
        this.desc = desc;
        this.dialogue = dialogue;
        this.hostile = hostile; //If it's TRUE then the NPC is Attackable else is friendly/neutral
        this.HP = hp;
        this.damage = damage;
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

    public void SetDesc(String desc) {
        this.desc = desc;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void SetDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    // Auto Set HP
    /*
    public void autoSetHP(int hp) {
        if ((hostile = true) && (boss = true)) {    //An example.
            HP = 35;
        }
        else if (hostile = true) {
            HP = 20;
        }
        else if (hostile = false){
            HP = 10;    //Another example. It could be anything
        }
        else HP = hp;
    }   */

    public boolean getAttackable(){
        return hostile;
    }

    public void SetAttackable(boolean hostile) {
        this.hostile = hostile;
    }

    //Set HP
    public void SetHP(int hp) {
        HP = hp;
    }

    // Get HP
    public int getHP(){
        return HP;
    }

    // Decrease HP
    public void decreaseHP(int damage) {
        HP = HP - damage;
    }

}


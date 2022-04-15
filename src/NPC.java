import java.io.Serializable;

public class NPC implements Serializable {
    private String name;
    private String desc;
    private String dialogue;
    private boolean hostile;
    private int HP,damage;

    public NPC() {
    }

    //  Neutral NPC
    public NPC(String name, String desc, String dialogue) {
        this.name = name;
        this.desc = desc;
        this.dialogue = dialogue;
    }

    //  Hostile/Friendly NPC
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

    //Set HP
    public void SetHP(int hp) {
        HP = hp;
    }

    // Get HP
    public int getHP(){
        return HP;
    }

    // Set decrease HP
    public void decreaseHP(int damage) {
        HP = HP - damage;
    }

}


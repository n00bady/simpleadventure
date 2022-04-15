import java.io.Serializable;

public class NPC implements Serializable {
    private String name;
    private String dialogue;
    private boolean hostile, boss;
    private int HP;

    public NPC() {
    }

    public NPC(String name) { //Neutral ?? Maybe ?? Who knows ?? No one :D
        this.name = name;
    }

    public NPC(String name, String dialogue, boolean hostile, boolean boss) {
        this.name = name;
        this.dialogue = dialogue;
        this.hostile = hostile; //If it's TRUE then the NPC is Hostile else is friendly
        this.boss = boss;
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
    }

    //Set HP
    public void SetHP(int hp) {
        HP = hp;
    }

    // Get HP
    public int getHP(){
        return HP;
    }

}


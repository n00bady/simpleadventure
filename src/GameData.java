import java.io.Serializable;

public class GameData implements Serializable {
    // This is a POJO class that helps in saving the rooms and the player state
    // might need to be updated if we make significant changes to the other classes

    private Room s_rooms[];
    private Player s_player;

    public GameData(Room rooms[], Player player) {
        s_rooms = rooms;
        s_player = player;
    }

    public Room[] getRooms() {
        return s_rooms;
    }

    public Player getPlayer() {
        return s_player;
    }
}

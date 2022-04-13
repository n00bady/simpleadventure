import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {
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

import java.io.Serial;
import java.io.Serializable;

public class GameProgress implements Serializable {
    @Serial
    private final static long serialVersionUID = 12345L;

    private int health;
    private int ammo;
    private int distance;
    private int level;

    public GameProgress(int health, int ammo, int distance, int level) {
        this.health = health;
        this.ammo = ammo;
        this.distance = distance;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Health: " + health +
                "\nLevel: " + level +
                "\nDistance: " + distance +
                "\nAmmo: " + ammo + "\n";
    }
}
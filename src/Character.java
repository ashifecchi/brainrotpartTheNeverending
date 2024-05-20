import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character {
    private String name;
    private ArrayList<BufferedImage> idle;
    public Character(String name, ArrayList<BufferedImage> idle){
        this.name = name;
        idle = idle;
    }
    public BufferedImage getidleSprites(int x){
        return idle.get(x);
    }

    public ArrayList<BufferedImage> getSprites() {
        return idle;
    }

    public String getName() {
        return name;
    }
}

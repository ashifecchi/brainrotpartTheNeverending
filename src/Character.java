import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character {
    private String name;
    private ArrayList<BufferedImage> sprites;
    public Character(String name, ArrayList<BufferedImage> sprites){
        this.name = name;
        this.sprites = sprites;
    }
    public BufferedImage getSprite(int x) {
        return sprites.get(x);
    }

    public ArrayList<BufferedImage> getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }
}

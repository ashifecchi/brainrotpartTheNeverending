import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Setting {
    private ArrayList<Rectangle> collision = new ArrayList<>();
    private BufferedImage bg;
    private String name;
    public Setting(String name, BufferedImage bg){
        this.bg = bg;
        this.name = name;
    }

    public BufferedImage getBg() {
        return bg;
    }

    public boolean touches(Rectangle other){
        for (Rectangle r : collision){
            if (r.intersects(other)){
                return true;
            }
        }
        return false;
    }
    private void addCollision(Rectangle x, int at){
        collision.add(x);
    }
    private void addCollisions(ArrayList<Rectangle> x, int at) {
        collision.addAll(x);
    }
}

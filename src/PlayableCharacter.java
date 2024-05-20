import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayableCharacter extends Character {
    private ArrayList<BufferedImage> runlr;
    private ArrayList<BufferedImage> runud;
    private ArrayList<BufferedImage> atk;
    public PlayableCharacter(String name, ArrayList<BufferedImage> idle, ArrayList<BufferedImage> runlr, ArrayList<BufferedImage> runud, ArrayList<BufferedImage> atk) {
        super(name, idle);
        this.runlr = runlr;
        this.runud = runud;
        this.atk = atk;
    }

    public BufferedImage atk(){
        return
    }
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class PlayableCharacter extends Character{
    private ArrayList<BufferedImage> runlr;
    private ArrayList<BufferedImage> rund;
    private ArrayList<BufferedImage> runu;
    private ArrayList<BufferedImage> atk;
    private Timer timer;
    public PlayableCharacter(String name, ArrayList<BufferedImage> idle, ArrayList<BufferedImage> runlr, ArrayList<BufferedImage> runu, ArrayList<BufferedImage> rund, ArrayList<BufferedImage> atk) {
        super(name, idle,475,375);
        this.runlr = runlr;
        this.runu = runu;
        this.rund = rund;
        this.atk = atk;
    }

    public BufferedImage atk(){
        return atk.get(super.getTime() % atk.size());
    }
    public BufferedImage getRunLeftSprites(){
        return runlr.get(getTime() % runlr.size());
    }
}

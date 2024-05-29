import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class PlayableCharacter extends Character implements ActionListener, KeyListener {
    private ArrayList<BufferedImage> runl;
    private ArrayList<BufferedImage> runr;
    private ArrayList<BufferedImage> rund;
    private ArrayList<BufferedImage> runu;
    private ArrayList<BufferedImage> atk;
    private Timer timer;
    private BufferedImage currentSprite;
    public PlayableCharacter(String name, ArrayList<BufferedImage> down) {
        super(name, down,475,375);
        rund = down;
    }

    public BufferedImage getSprite() {
        return currentSprite;
    }

    public void setRund(ArrayList<BufferedImage> rund) {
        this.rund = rund;
    }

    public void setRunr(ArrayList<BufferedImage> runr) {
        this.runr = runr;
    }
    public void setRunl(ArrayList<BufferedImage> runl) {
        this.runl = runl;
    }

    public void setRunu(ArrayList<BufferedImage> runu) {
        this.runu = runu;
    }

    public BufferedImage atk(){
        return atk.get(super.getTime() % atk.size());
    }
    public BufferedImage getRunUpSprite(){
        return runu.get(getTime() % runu.size());
    }
    public BufferedImage getRunDownSprite(){
        return rund.get(getTime() % rund.size());
    }
    public BufferedImage getRunRightSprite(){
        return runr.get(getTime() % runr.size());
    }
    public BufferedImage getRunLeftSprite(){
        return runl.get(getTime() % runl.size());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    /*37 -- Left
      38 -- Up
      39 -- Right
      40 -- Down
      personal notes
     */

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 40){
            currentSprite = getRunDownSprite();
            moveDown();
        } else if (e.getKeyCode() == 38){
            currentSprite = getRunUpSprite();
            moveUp();
        } else if (e.getKeyCode() == 39){
            currentSprite = getRunRightSprite();
            moveRight();
        } else if (e.getKeyCode() == 37){
            currentSprite = getRunLeftSprite();
            moveLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

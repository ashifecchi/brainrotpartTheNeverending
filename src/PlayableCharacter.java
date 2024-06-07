import org.w3c.dom.css.Rect;

import java.awt.*;
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
    private ArrayList<Rectangle> Bounds;
    private Timer timer;
    private String currentDir;
    private BufferedImage currentSprite;
    public PlayableCharacter(String name, ArrayList<BufferedImage> down) {
        super(name, down,300,200);
        rund = down;
        currentSprite = rund.get(0);
        currentDir = "down";
    }
    private void addBounds(Rectangle rec){
        Bounds.add(rec);
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
    public boolean willBeInBounds(String dir){
        Rectangle newbox;
        if (dir.equals("down")){
             newbox = new Rectangle(getX(),getY()+getMOVEMENT_SPEED(),(int)getBox().getWidth(),(int)getBox().getHeight());
        } else if (dir.equals("up")){
             newbox = new Rectangle(getX(),getY()-getMOVEMENT_SPEED(),(int)getBox().getWidth(),(int)getBox().getHeight());
        } else if (dir.equals("left")) {
             newbox = new Rectangle(getX()-getMOVEMENT_SPEED(),getY(),(int)getBox().getWidth(),(int)getBox().getHeight());
        } else if (dir.equals("right")) {
             newbox = new Rectangle(getX()+getMOVEMENT_SPEED(),getY(),(int)getBox().getWidth(),(int)getBox().getHeight());
        } else {
            newbox = getBox();
        }
        for (Rectangle r : Locations.getCurrentSettingBounds()) {
            if (r.intersects(newbox)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /*37 -- Left
      38 -- Up
      39 -- Right
      40 -- Down

      88 -- X
      personal notes
     */

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 88){
            Rectangle newbox;
            if (currentDir.equals("down")){
                newbox = new Rectangle(getX(),getY()+getMOVEMENT_SPEED(),(int)getBox().getWidth(),(int)getBox().getHeight());
            } else if (currentDir.equals("up")){
                newbox = new Rectangle(getX(),getY()-getMOVEMENT_SPEED(),(int)getBox().getWidth(),(int)getBox().getHeight());
            } else if (currentDir.equals("left")) {
                newbox = new Rectangle(getX()-getMOVEMENT_SPEED(),getY(),(int)getBox().getWidth(),(int)getBox().getHeight());
            } else if (currentDir.equals("right")) {
                newbox = new Rectangle(getX()+getMOVEMENT_SPEED(),getY(),(int)getBox().getWidth(),(int)getBox().getHeight());
            } else {
                newbox = getBox();
            }
            for (InteractableObject r: Locations.getCurrentSetting().getObj()){
                if (r.intersects(newbox)){
                    if (r.getName().equals("door")){

                    }
                }
            }
        }
        if (e.getKeyCode() == 40) {
            currentSprite = getRunDownSprite();
            if (!willBeInBounds("down")){
                moveDown();
                currentDir = "down";
            }
        } else if (e.getKeyCode() == 38) {
            currentSprite = getRunUpSprite();
            if (!willBeInBounds("up")){
                moveUp();
                currentDir = "up";
            }
        } else if (e.getKeyCode() == 39) {
            currentSprite = getRunRightSprite();
            if (!willBeInBounds("right")){
                moveRight();
                currentDir = "right";
            }
        } else if (e.getKeyCode() == 37) {
            currentSprite = getRunLeftSprite();
            if (!willBeInBounds("left")){
                moveLeft();
                currentDir = "left";
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

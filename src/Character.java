import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Character implements ActionListener {
    private String name;
    private ArrayList<BufferedImage> idle;
    private int time;
    private Timer timer;
    private int x;
    private int y;
    private Rectangle box;
    public Character(String name, ArrayList<BufferedImage> idle, int x, int y){
        this.name = name;
        this.idle = idle;
        this.x = x;
        this.y = y;
        box = new Rectangle(x+idle.get(1).getWidth(), y + idle.get(1).getHeight());
        timer = new Timer(500,this);
        timer.start();
    }

    public boolean collision(Character other) {
        if (box.intersects(other.getBox())){
            return true;
        } else {
            return false;
        }
    }

    public Rectangle getBox() {
        return box;
    }

    public BufferedImage getidleSprites(){
        return idle.get(time % idle.size());
    }

    public ArrayList<BufferedImage> getSprites() {
        return idle;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
    public void moveUp(){
        y--;
    }
    public void moveDown(){
        y++;
    }
    public void moveRight(){
        x++;
    }
    public void moveLeft(){
        x--;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer){
            time++;
        }
    }
}

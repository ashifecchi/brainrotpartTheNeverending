import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.function.DoubleFunction;

public class PlayableCharacter extends Character implements ActionListener, KeyListener {
    private ArrayList<BufferedImage> runl;
    private ArrayList<BufferedImage> runr;
    private ArrayList<BufferedImage> rund;
    private ArrayList<BufferedImage> runu;
    private ArrayList<BufferedImage> atk;
    private String currentDir;
    private BufferedImage currentSprite;
    public PlayableCharacter(String name, ArrayList<BufferedImage> down) {
        super(name, down,250,200);
        rund = down;
        currentSprite = rund.get(0);
        currentDir = "down";
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
        for (Rectangle r : Locations.getCurrentSettingBounds()) {
            if (r.intersects(getAhead())){
                return true;
            }
        }
        return false;
    }
    public Rectangle getAhead(){
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
        return newbox;
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
            Rectangle newbox = getAhead();
            for (InteractableObject r: Locations.getCurrentSetting().getObj()){
                if (r.intersects(newbox)){
                    String name = r.getName();
                    if (name.equals("door")){
                        Img.room();
                        currentDir = "left";
                    } else if (name.equals("randomhouse")) {
                        Img.sayStuff("probably shouldn't do that.");
                    } else if (name.equals("tsukasahouse")) {
                        Img.sayStuff("do not want to do that.");
                    } else if (name.equals("exit")) {
                        Img.sayStuff("nah");
                        /*      ⠀⢀⢔⠾⢋⠷⢃⠠⠒⠈⠀⢀⣀⢂⢠⣲⢦⡪⠝⠀⢠⠎⠀⠀⠀⠀⠀⣠⣴⢪⡃⠜⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⡀⠖⠁⠀⢀⠀⠀⡀⠀⠀⠀⠀⢀⣤⠦⡻⠂⠀⠀⠀⢼⡆⠀⠀⠁⡔⡀⡸⠀⢠⠃⠀⢸⣐⣷⣏⠉⠁⠉⢻⡄⠀⠀⡱⠑⢆⣨⠟⠊⠉⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⢀⢔⠕⢁⠔⠁⠐⣁⣤⠴⠚⠉⢀⣠⠖⡫⠃⠁⠀⠀⣰⠃⠀⠀⡠⠀⢠⠞⡵⠃⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⢀⠔⠈⠀⠀⠀⠔⡰⢃⠔⠁⠀⠀⠀⠼⡫⣠⠎⠀⠀⢀⠤⠀⠀⠀⠀⠀⠀⠰⢁⠃⢀⠇⠀⠀⣼⠋⣟⡆⠇⢀⠀⠸⢎⢵⠀⠱⠱⡈⢧⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⣠⡡⠁⢠⣁⣀⡴⡚⠅⠐⠈⢀⡴⠋⠐⠁⢀⡠⡤⠄⣰⠃⠀⠀⣰⠁⠀⣠⠊⠀⠀⠀⠀⢀⣀⣠⣤⠤⠶⠚⠋⠉⢀⡠⠀⠀⠀⠀⢠⣮⠞⣐⠅⠀⠀⠀⠀⢀⡴⠋⠁⣀⣠⠔⠁⠀⠀⢠⠃⠀⠀⠀⢦⠂⢠⠊⠀⠀⠚⠙⢰⢸⣷⢰⠈⢆⠀⠙⣮⢣⠀⠐⡔⡒⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⢠⠟⣠⠖⠋⢁⠚⠃⣀⠔⠚⡷⠉⣉⡤⡲⢭⠞⢉⠃⣰⠏⠀⠀⡴⣉⡀⡚⠁⠑⠒⠀⡛⠛⢉⢁⠄⠀⣠⠗⠀⢀⡴⠋⠀⢀⠤⢠⢾⠋⢡⠞⠁⠀⡠⠒⠀⢀⣎⣠⢞⢵⠟⠁⠀⢀⠔⠠⠃⠀⡔⡐⠀⡇⠀⠁⠀⠀⠀⠀⡃⠈⠀⣿⠈⡀⡇⠣⡀⠈⢧⠡⡀⠈⢊⢜⣧⡀⠀⠀⠀⠀⠀⠀⠀⣀⡠⢤⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⢸⢶⠃⠀⢀⠃⢠⡞⠁⢀⡼⡷⢋⣥⣮⠴⠁⡠⣵⢻⡟⠀⢀⡼⢋⢊⠌⠀⡠⠊⢀⠊⢀⣀⣆⠃⠀⣰⠃⢀⡴⠋⠀⢀⠔⠕⡡⠞⣠⠝⠁⠀⣠⠊⢀⣤⠖⣡⠞⣕⡡⠁⠀⣠⡞⡡⣶⡵⠀⣸⢣⠇⢸⡟⠀⠀⠀⡀⠀⢠⠁⡆⠀⡷⠀⡇⣏⡄⢻⠄⠀⠱⡷⣄⠀⠡⡹⡇⠀⢀⡀⠄⠒⠈⠁⠀⠀⠀⠀⠉⠓⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠈⠙⠀⠀⡎⢠⠋⠀⣠⡮⠔⠈⣩⠞⠁⢀⢊⡾⢡⡄⠁⢠⡾⠡⠡⢂⠠⠊⢀⠔⣀⡴⢋⡏⠎⠀⣸⠃⣰⠟⠁⠀⡐⠁⡡⡊⠔⠈⡁⢐⣔⡟⢡⠞⡑⣡⠎⣡⠞⠝⠀⢀⣮⢟⠊⡸⠹⠁⢰⠃⣼⠀⣿⠂⢰⠀⢰⠃⠀⡌⢰⡗⢰⡧⠀⢫⡷⠇⠀⣎⢆⠀⡇⠏⢳⡤⠜⠓⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠑⣿⣖⡾⠋⠀⡠⠊⠁⢠⣖⣵⡭⡂⠁⠘⠄⢳⠁⡶⠓⣡⣰⣖⡥⠞⠁⣀⢼⢱⠀⣰⢃⡼⠃⠀⢠⡪⣪⠞⠋⡀⢔⣠⠦⠛⠉⣠⡳⢊⡴⢣⠞⢁⠊⠀⣠⡿⠛⢁⠎⢠⡳⢡⠏⢸⠟⢠⢟⠀⢸⠀⢸⠁⠀⢁⡿⠁⢠⠇⠀⠘⡇⠘⠀⡆⣾⠠⠓⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠦⡀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⡀⠝⠛⠀⡠⢊⣠⠾⠗⡾⠁⢳⠀⣽⡄⠀⢘⠾⣊⠴⢋⡵⢫⣷⣃⢀⠔⠁⣿⠆⠀⠀⡞⢁⠀⠴⠛⠘⣀⣔⡬⢖⠋⠁⢀⠔⣶⡟⠡⢈⡕⠛⠠⠂⠀⣰⠋⠀⡰⠁⢀⣧⢡⡎⢀⠟⠀⣸⢹⠀⢸⠀⡸⠀⠀⣾⠁⠀⡌⠀⣀⡸⠙⣄⠶⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠦⡀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⣰⣪⠖⠋⠀⢀⠜⢀⢌⠜⡆⣿⠼⣺⢗⣟⣡⡎⢡⠃⣤⠹⠘⠢⡤⢄⣛⡐⠠⠼⠍⠐⠀⣀⡤⡞⠉⠁⣤⠋⢀⠔⠁⣼⠏⠐⢠⠎⠐⠰⠃⠀⡼⠁⠀⡜⠀⡰⣻⢃⡞⠀⣸⠃⢀⡟⢰⠀⢸⠀⣷⠀⣸⠁⠀⡘⠀⣼⡿⠁⢠⢏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢎⣸⠃⠀⠀⡠⢃⣴⠟⣡⣴⣿⣷⠛⠛⡈⡇⢠⠗⢸⣾⠸⡇⠀⠀⠈⠑⡾⣫⢒⣴⣶⢟⠵⢡⠌⠀⠀⠔⠃⡠⠁⠀⣾⢋⠌⡰⠁⠀⠠⢁⡄⠐⠀⠀⢞⡒⡰⢠⡏⡾⢠⢧⡏⠀⢺⠯⢥⠀⢸⠀⡽⢠⠃⠀⡰⠇⣸⠗⠃⢠⢳⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠷⡄⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣨⣢⢔⣡⠖⢫⠽⠑⡟⠉⢸⢯⢏⠉⣵⡇⣸⠀⢘⢨⠃⡁⠓⠒⢢⢞⢜⣥⣫⣿⡧⠃⠀⡌⠀⠀⠈⠀⠊⠀⠀⣼⠃⠊⡐⠀⠀⢠⠣⡞⢠⡆⠀⡎⠀⠀⠁⣼⡝⢠⠟⡸⠀⠀⢸⢐⣸⠂⢸⠀⡇⢂⠄⡠⣧⣷⠏⠀⢠⡇⡈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⡆⠀⢰⡆⠀⢠⣾⣷⠀⢀⣾⠀⠀⣾⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡆\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠚⠿⠚⠋⠁⠌⠁⡠⠊⡐⣡⢟⡌⡈⢒⡏⢰⢸⢰⢸⢺⡄⠀⢀⡴⣷⢿⠏⢈⣿⠟⠷⢆⡤⢀⣀⠀⠀⠀⠀⠀⢀⣏⠌⡔⠀⠀⠀⣆⠾⢁⡞⡇⡜⠀⠀⠀⣀⣯⡴⣥⢷⠓⠒⠋⠉⠡⢸⠀⢸⠀⠇⠎⣠⠱⢸⠇⠀⢠⠃⢠⢁⡇⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣷⢀⡿⢁⣴⠟⣹⣿⠀⣾⣷⣶⣾⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡇\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⠋⣀⣪⠞⡑⢁⢧⠙⠀⢇⢸⣿⢺⡞⡚⡯⢴⡙⠉⢀⡐⠂⡘⡞⠀⠅⠠⠉⠁⠚⠣⠝⣔⠶⣀⠀⠁⡰⠀⠀⠀⠀⡏⣄⡜⠀⡷⢓⣢⠿⠍⠛⠋⠠⡡⠂⠀⠀⠀⢀⠀⢀⡇⢨⠀⣶⡜⢸⠀⡟⠀⠀⢆⣠⠃⡈⡇⠀⠀⠀⠀⠀⠀⠀⢀⣿⠉⣿⣿⢃⣾⠿⠟⢻⣟⣸⡟⠁⢠⡿⠁⢀⣴⠆⠀⠀⠀⠀⠀⠀⠀⢠⡇\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠞⠧⠜⠋⢁⠂⠌⡰⢃⣾⣦⠀⢸⡀⣿⣄⢁⠇⠀⠀⢯⣝⣖⣿⣯⣿⣒⡭⠥⣐⡒⠤⢀⠀⠀⠈⠉⡛⢆⢠⠃⠀⠀⠀⠀⠑⠋⢀⣀⠻⠉⠁⠀⠀⢀⡠⠔⣒⣀⣭⣝⣛⣫⣿⣿⣧⠘⠀⣳⠀⣼⢠⠃⠀⠀⠸⣹⢀⠃⡇⠀⠀⠀⠀⠀⠀⠀⠘⠃⠀⠘⠃⠘⠃⠀⠀⠙⠋⠛⠀⠀⠙⠃⢠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⢸⠃\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⢀⠂⣼⡞⠁⣾⡡⠘⡆⢸⡇⢸⡘⡈⢶⠀⠀⠀⢼⣣⠀⠀⢸⡄⠈⢽⡇⠛⠷⣦⣽⠀⠀⠀⠘⡟⡇⠀⠀⠀⠀⠀⠀⢠⣾⠆⠀⠀⠀⠀⢀⣯⡴⣾⠛⠩⠧⠄⣸⠃⠀⠀⣼⠆⢰⣇⡼⢰⣿⠄⠀⠀⠠⢿⠸⢰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣤⡀⣤⡄⢀⣠⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣨⡴⠋⠀⢰⣿⠁⢀⣿⣸⠁⢶⠧⠀⠘⢇⠀⠀⠘⢞⡄⠀⠀⠙⠧⣀⡂⠤⠂⠈⢿⣇⠀⠀⠀⠼⢿⠀⠀⠀⠀⠀⠀⢠⠘⠆⠀⠀⠀⠀⣼⡏⠀⠈⠢⢄⡤⠴⠋⠀⠀⡰⠸⡄⢳⣷⢇⡂⣯⠀⠀⢠⠃⠎⢀⠟⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢻⡟⠉⣿⠋⠀⣿⠟⠉⠉⣽⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⣿⡏⠀⣼⢣⣿⡄⢸⡇⠀⠀⠈⠂⠀⠀⠀⠜⢄⡀⠀⠀⠀⠀⠀⠀⢀⡟⠯⡶⡶⢀⡶⠋⠀⠀⠀⠀⠀⠀⠀⠁⠀⢀⣄⡀⢰⡟⠳⡀⠀⠀⠀⠀⠀⠀⢀⠔⠣⠀⡌⡟⡆⢸⠇⠈⠁⡤⢸⡀⢠⠎⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠁⠀⠀⠀⢰⡟⣀⣤⡾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⢣⠜⠁⡾⠼⡆⠀⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠁⠀⠚⠋⠀⠌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠓⣞⣤⡀⠁⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⣷⢸⡁⠈⠀⠀⢀⡇⠀⢡⢿⠁⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣶⡾⠿⠟⠂⠀⠀⠿⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠊⠁⠀⡸⡷⢣⠺⣆⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠇⠘⡄⠀⠀⠀⢸⠀⢠⢿⢹⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡵⠓⠁⠀⠈⠛⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠁⠀⠀⠁⠀⠀⢀⣇⣠⣟⡟⡜⡀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡆⢠⣷⡆⠀⣼⠇⣰⡇⢰⣷⠀⢀⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⢹⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠋⣿⢰⠇⢸⣱⡀⢤⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢠⡾⣿⣇⣼⠏⢠⡿⢀⣿⢿⣧⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠂\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠇⠀⣿⣿⠀⠀⡏⠁⢸⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡟⠁⢿⣿⠋⢀⣾⠁⣾⠃⠈⣿⡟⠀⣤⡦⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡟⠀⢰⣟⠇⠀⠈⠀⠀⡼⠗⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠈⠁⠀⠈⠃⠀⠉⠀⠀⠈⠁⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠁⠀⡾⠊⠀⠀⠀⠀⠀⠹⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠋⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⢦⡀⠀⢀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠂⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡽⢆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠐⠢⢄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣴⡾⠁⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡎⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⣼⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠤⠀⠀⠀⠀⠀⠈⠉⠓⢾⠗⣀⠤⠤⠤⠄⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣯⣳⠀⠹⣆⠀⠀⠀⠀⠀⠰⠦⠄⣀⣀⡀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠠⠴⠛⠁⠀⠀⠀⢀⡜⠁⠀⡰⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢋⠖⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢌⡆⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠨⡙⢿⣿⣛⠛⠛⠛⠛⠛⠛⠻⣛⣩⢿⡋⠉⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⢀⣴⠿⣿⠅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠗⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠳⣵⠀⠀⠀⠹⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠦⣤⣤⣭⣭⣭⣭⣭⢩⡭⠖⠃⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⣲⠯⢸⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠈⠀⠀⠀⠀⠈⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠋⠀⠀⠀⠀⢰⡿⢌⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠩⡿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠎⠀⠀⠀⠀⠀⠀⢐⡺⢁⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣼⣧⠀⢈⠢⡀⠀⠀⠀⠀⢘⣝⢿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠏⠀⠀⠀⠀⠀⠀⠒⠁⠀⢸⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣿⣿⣿⣿⠀⠀⠑⢌⢲⣀⠀⠀⠀⠳⣅⡫⠳⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⡽⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⢳⣍⠣⡀⠀⠀⠀⢻⡟⠆⠈⠹⠶⣤⣀⣀⣀⣀⡀⣀⢀⣀⣀⣠⣴⠿⣛⢝⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠈⢯⡫⡳⡀⠀⠀⠀⠻⣯⡀⠀⠀⠀⠀⠈⠉⠉⠙⠙⠋⠛⠙⢫⣍⡳⢎⠋⠀⠀⠀⣠⡴⠖⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠈⡟⣬⡑⠄⠀⠀⠀⠱⡷⣂⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣜⣪⡍⠀⠀⠀⠀⡰⠏⠁⣀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠸⡦⡋⡦⠀⠀⠀⠀⠈⢎⠙⠕⣓⠂⠤⢀⢠⠄⠀⠀⠜⠳⠋⠀⠀⠀⢀⡾⢋⡥⠴⣇⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⢀⣀⣤⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣌⡢⡹⡄⠀⠀⠀⠀⠘⡖⠔⣒⡠⠄⡭⠃⠀⠀⠐⡺⠁⠀⠀⢀⡴⢿⡙⠃⣐⠒⠉⠁⢀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⣀⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣧⣀⡀⠀⠀⠀⠻⠅⡒⠄⢹⡁⠀⠀⠀⢻⠀⠀⠀⢠⣾⣿⡋⢠⠑⢀⣃⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                  */
                    } else if (name.equals("desk")) {
                        Img.sayStuff("this is where i keep my monster energy.");
                    } else if (name.equals("bed")) {
                        Img.sayStuff("i rest here. sometimes.");
                    } else if (name.equals("PC") || name.equals("PCchair")) {
                        Img.sayStuff("yay");
                        int i = 0;
                        while (i <1000){
                            i++;
                        }
                        Img.bootPc();
                        int x = 0;
                    } else if (name.equals("roomdoor")) {
                        Img.village();
                        currentDir = "down";
                    }
                }
            }
        }
        if (e.getKeyCode() == 40) {
            currentSprite = getRunDownSprite();
            currentDir = "down";
            if (!willBeInBounds("down")){
                moveDown();
            }
        } else if (e.getKeyCode() == 38) {
            currentSprite = getRunUpSprite();
            currentDir = "up";
            if (!willBeInBounds("up")){
                moveUp();
            }
        } else if (e.getKeyCode() == 39) {
            currentSprite = getRunRightSprite();
            currentDir = "right";
            if (!willBeInBounds("right")){
                moveRight();
            }
        } else if (e.getKeyCode() == 37) {
            currentSprite = getRunLeftSprite();
            currentDir = "left";
            if (!willBeInBounds("left")){
                moveLeft();
            }
        } else if (e.getKeyCode() == 27) {
            if (Img.getpc()) {
                Img.closePC();
                Img.sayStuff("damn");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (currentDir.equals("down")){
            currentSprite = rund.get(0);
        } else if (currentDir.equals("up")){
            currentSprite = runu.get(0);
        } else if (currentDir.equals("left")) {
            currentSprite = runl.get(0);
        } else {
            currentSprite = runr.get(0);
        }
    }
}

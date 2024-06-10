import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Img extends JPanel implements ActionListener {
    private PlayableCharacter nnkn;
    private BufferedImage bg;
    private Timer t;
    private static int randomCounter = 0;
    private static Locations areas;
    private static Graphics2D g2;
    private static boolean sayingstuff = false;
    private BufferedImage speechbubble = loadImg("src/pictures/nenebubble.png");
    private static String stuff;
    static {
        try {
            areas = new Locations(new Setting("Village",loadImg("src/pictures/village.png")));
            areas.addSetting(new Setting("room",loadImg("src/pictures/neneroom.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Img() throws IOException {
        super();
        ArrayList<BufferedImage> nene = new ArrayList<>();
        nnkn = new PlayableCharacter("nene",readImg("src/AnimFrame/Nene/neneDown", nene));
        addKeyListener(nnkn);

        // nene sprites
        nnkn.setRunl(readImg("src/AnimFrame/Nene/neneLeft"));
        nnkn.setRunr(readImg("src/AnimFrame/Nene/neneRight"));
        nnkn.setRunu(readImg("src/AnimFrame/Nene/neneUp"));
        nnkn.setMOVEMENT_SPEED(3);

        //fix the image. i have it at home and accidentally set it to the wrong village skull emoji
        areas.getSetting(0).addCollision(new Rectangle(210,10,150,125));
        areas.getSetting(0).addCollision(new Rectangle(420,10,110,130));
        areas.getSetting(0).addCollision(new InteractableObject("door",new Rectangle(265, 85,35,50)));
        areas.getSetting(0).addCollision(new Rectangle(-1,0,1,1000));
        areas.getSetting(0).addCollision(new Rectangle(0,-1,10000,1));
        areas.getSetting(0).addCollision(new Rectangle(657,-1,1,1000));
        areas.getSetting(0).addCollision(new Rectangle(0,476,1000,1));
        areas.getSetting(0).addCollision(new InteractableObject("randomhouse",new Rectangle(30,0,150,130)));
        setFocusable(true);
        requestFocusInWindow();
        t = new Timer(0,this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        AffineTransform at = new AffineTransform();
        if ((Locations.getCurrentSetting() == areas.getSetting(1))){
            at.scale(2.5,2.5);
        } else{
            at.scale(1.5, 1.5);
        }
        if (sayingstuff) {
            g2.drawImage(speechbubble, 10, 600, null);
            g2.drawString(stuff, 50, 610);
            if (randomCounter > 100){
                sayingstuff = false;
            }
            randomCounter++;
        }
        g2.transform(at);
        g2.drawImage(Locations.getCurrentSetting().getBg(),-150, -250,null);
        for (int x = 0; x < Locations.getCurrentSettingBounds().size();x++){
            g2.draw(Locations.getCurrentSettingBounds().get(x));
        }
        g2.draw(nnkn.getBox());
        g2.drawImage(nnkn.getSprite(),nnkn.getX(),nnkn.getY(),null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t){
            repaint();
        }
    }
    private ArrayList<BufferedImage> readImg(String file, ArrayList<BufferedImage> puthere) {
        try {
            File myFile = new File(file);
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                BufferedImage data = loadImg(fileScanner.nextLine());
                puthere.add(data);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return puthere;
    }
    private ArrayList<BufferedImage> readImg(String file) {
        ArrayList<BufferedImage> puthere = new ArrayList<>();
        try {
            File myFile = new File(file);
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                BufferedImage data = loadImg(fileScanner.nextLine());
                puthere.add(data);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return puthere;
    }
    public static void room(){
       areas.changeSetting(1);
    }
    public static void village(){
        areas.changeSetting(0);
    }
    public static void sayStuff(String s){
        sayingstuff = true;
        stuff = s;
        randomCounter = 0;
    }


    public static BufferedImage loadImg(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
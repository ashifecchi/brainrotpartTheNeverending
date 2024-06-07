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
    private Locations areas = new Locations(new Setting("hometown",loadImg("src/pictures/village.png")));
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
        //bg
        bg = loadImg("src/pictures/village.png");
        //fix the image. i have it at home and accidentally set it to the wrong village skull emoji
        Setting villag = new Setting("Village",bg);
        villag.addCollision(new Rectangle(210,10,150,125));
        villag.addCollision(new Rectangle(420,10,110,130));
        Locations background = new Locations(villag);
        setFocusable(true);
        requestFocusInWindow();
        t = new Timer(0,this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at = new AffineTransform();
        at.scale(1.5, 1.5);
        g2.transform(at);
        g2.drawImage(Locations.getCurrentSetting().getBg(),-150, -250,null);
        g2.draw(Locations.getCurrentSettingBounds().get(0));
        g2.draw(Locations.getCurrentSettingBounds().get(1));
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
    public void room(){
       // bg = loadImg("I FORGOT TO IMPORT THE ROOM IMG.");
    }
    public static BufferedImage loadImg(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
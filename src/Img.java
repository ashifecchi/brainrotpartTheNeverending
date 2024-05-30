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
    public Img() throws IOException {
        super();
        ArrayList<BufferedImage> nene = new ArrayList<>();
        nnkn = new PlayableCharacter("nene",readImg("src/AnimFrame/Nene/neneDown", nene));
        addKeyListener(nnkn);

        // nene sprites
        nnkn.setRunl(readImg("src/AnimFrame/Nene/neneLeft"));
        nnkn.setRunr(readImg("src/AnimFrame/Nene/neneRight"));
        nnkn.setRunu(readImg("src/AnimFrame/Nene/neneUp"));
        //bg
        bg = loadImg("src/pictures/miku.png");
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
        g2.drawImage(bg,0,0,null);
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
    public static BufferedImage loadImg(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
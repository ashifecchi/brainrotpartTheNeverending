import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Img extends JPanel implements ActionListener {
    public Img() throws IOException {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        ArrayList<BufferedImage> nene = new ArrayList<>();
        PlayableCharacter nnkn = new PlayableCharacter("nene",initChar("src/nenesprites", nene));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private ArrayList<BufferedImage> initChar(String file, ArrayList<BufferedImage> puthere) {
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
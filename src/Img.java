import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Img extends JPanel implements ActionListener {
    public Img() throws IOException {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<BufferedImage> nene = new ArrayList<>();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
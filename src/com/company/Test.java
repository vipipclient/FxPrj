package com.company;


import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.geom.*;

public class Test extends JFrame {
    BufferedImage bi;
    Graphics big; // stands for Buffered Image Graphics
    Toolkit toolkit;
    MediaTracker tracker;
    int width;
    int height;

    public Test() {
        toolkit = Toolkit.getDefaultToolkit();
        tracker = new MediaTracker(this);

        Image image = toolkit.getImage("IMG_20190222_211814.jpg");
        tracker.addImage(image, 0);
        try {
            // load all the image for later use
            tracker.waitForAll();
        } catch (InterruptedException ex) {
        }

        width = image.getWidth(this);
        height = image.getHeight(this);

        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        big = bi.getGraphics();
        big.drawImage(image, 0, 0, this);
    }

    public void paint(Graphics g) {
        setBackground(Color.white);

        Graphics2D g2 = (Graphics2D)g;

        TexturePaint paint = new TexturePaint(bi,
                new Rectangle2D.Double(0,0,width,height));
        g2.setPaint(paint);
        g2.fill(new Ellipse2D.Double(25,50,255,125));

    }

    public static void main(String[] args) {
        JFrame f = new Test();
        f.setSize(300,200);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import javax.imageio.*;

public class awttest {

    public static void main(String[] args) {
        Scanner inputFromConsole = new Scanner(System.in);

        SampleFrame var = new  SampleFrame();
        var.gogo();

 //       newFrame.removeAll();

    }
}
class SampleFrame extends Frame implements ActionListener {
    String keymsg = "rgergregreg";
    Graphics big;
    Image image ;
    public SampleFrame(){
        addKeyListener(new MykeyAdapter(this));
        addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){System.exit(0);}  }   );
    }

    public void gogo(){
        SampleFrame sample = new SampleFrame();
        sample.setSize(new Dimension(600,600));
        sample.setBackground(Color.darkGray);
        File pathToFile = new File("_2qi4bxiVvo.jpg");
        byte readAllBytesArr[] = new byte[0];
        try(FileInputStream f = new FileInputStream(pathToFile)){
            sample.image = ImageIO.read(f);
        }catch (IOException e){System.out.println("e.getStackTrace().toString()");}

        sample.setVisible(true);

    }
    public void paint(Graphics g){
        System.out.println("paint(Graphics g)");
        g.drawImage(image, getInsets().left,getInsets().top,null);
        //       g.drawString(keymsg,100,100);
        //       FontMetrics fm = g.getFontMetrics();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("public void actionPerformed(ActionEvent actionEvent) ");
    }
}
class MykeyAdapter extends KeyAdapter{
    SampleFrame sampleFrame;
    public MykeyAdapter(SampleFrame sampleFrame){
        this.sampleFrame=sampleFrame;
    }
    public void keyTyped(KeyEvent ke){
        sampleFrame.keymsg += ke.getKeyChar();
        sampleFrame.repaint();
        System.out.println("public void keyTyped(KeyEvent ke)) ");
    }
}

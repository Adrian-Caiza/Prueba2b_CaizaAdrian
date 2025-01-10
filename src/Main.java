import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().loginIU);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setPreferredSize(new Dimension(500,600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}


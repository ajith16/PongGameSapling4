import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GameFrame()
    {
        setTitle("Pong Game");
        getContentPane().setBackground(Color.black);
//        setSize(1000,555);
        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        GameFrame g = new GameFrame();
    }
}

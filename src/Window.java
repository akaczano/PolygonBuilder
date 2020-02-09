import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public static int WIDTH = 600;
    public static int HEIGHT = 500;

    public Window () {
        super("Polygon Builder");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        GraphicsPanel panel = new GraphicsPanel();
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(panel);
    }

    public void launch () {
        this.setVisible(true);
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;

public class Window extends JFrame implements WindowListener {

    public static int WIDTH = 600;
    public static int HEIGHT = 500;

    private GraphicsPanel panel;

    public Window () {
        super("Polygon Builder");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        this.setLayout(null);
        this.addWindowListener(this);
        initComponents();
    }

    private void initComponents() {
        panel = new GraphicsPanel();
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        try {
            panel.addPoints(Point.readCoordinates(new File("map.csv")));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.add(panel);
    }

    public void launch () {
        this.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
        panel.saveToCSV("output.csv");
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}

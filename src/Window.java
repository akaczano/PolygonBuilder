import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class Window extends JFrame implements  ActionListener {

    public static int WIDTH = 600;
    public static int HEIGHT = 410;

    private GraphicsPanel panel;

    private JButton drawButton;
    private JButton clearButton;
    private JButton saveButton;



    public Window () {
        super("Polygon Builder");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        panel = new GraphicsPanel();
        panel.setBounds(0, 20, WIDTH, 300);
        try {
            panel.addPoints(Point.readCoordinates(new File("map.csv")));
            panel.addPoints(Point.readCoordinates(new File("mexico.csv")));
            panel.addLandmarks(Point.readCoordinates(new File("cities.csv")));
            //panel.addPoints(Point.readCoordinates(new File("canada.csv")));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.add(panel);

        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new FlowLayout());

        drawButton = new JButton("Draw Polygon");
        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");

        drawButton.addActionListener(this);
        clearButton.addActionListener(this);
        saveButton.addActionListener(this);

        otherPanel.add(drawButton);
        otherPanel.add(clearButton);
        otherPanel.add(saveButton);

        otherPanel.setBounds(new Rectangle(0, 330, WIDTH, 200));
        this.add(otherPanel);
    }

    public void launch () {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == clearButton) {
            panel.clearScreen();
        }
        else if (actionEvent.getSource() == saveButton) {
            JFileChooser saveDialog = new JFileChooser();
            saveDialog.setDialogTitle("Choose an output file");
            int result = saveDialog.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                panel.saveToCSV(saveDialog.getSelectedFile());
            }
        }
        else if (actionEvent.getSource() == drawButton) {
            panel.drawPolygon();
        }
    }
}

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window () {
        super("Polygon Builder");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setResizable(false);
    }

    public void launch () {
        this.setVisible(true);
    }

}

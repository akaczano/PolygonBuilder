import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements MouseListener, MouseMotionListener {

    private ArrayList<Integer> xs;
    private ArrayList<Integer> ys;


    public GraphicsPanel() {
        xs = new ArrayList<>();
        ys = new ArrayList<>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    // Conversion
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < xs.size(); ++i) {
            g.fillRect(xs.get(i), ys.get(i), 1, 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        xs.add(mouseEvent.getX());
        ys.add(mouseEvent.getY());
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        xs.add(mouseEvent.getX());
        ys.add(mouseEvent.getY());
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static final int MARGIN_X = 10;
    public static final int MARGIN_Y = 10;
    public static final double MIN_LONG = -125;
    public static final double MAX_LONG = -66;
    public static final double MAX_LAT = 50;
    public static final double MIN_LAT = 25;

    private ArrayList<Integer> xs;
    private ArrayList<Integer> ys;

    public void addPoints(ArrayList<Point> points) {
        int screen_width = Window.WIDTH - 2 * MARGIN_X;
        int screen_height = Window.HEIGHT - 2 * MARGIN_Y;

        double long_per_x = screen_width / (MAX_LONG - MIN_LONG);
        double lat_per_y = screen_height /  (MAX_LAT - MIN_LAT);

        for (Point p : points) {

            int x = (int)((p.getLongitude()-MIN_LONG) * long_per_x);
            int y = (int)((p.getLatitude()-MIN_LAT) * lat_per_y);
            y = screen_height - y;
            xs.add(x);
            ys.add(y);
        }
    }

    public GraphicsPanel() {
        xs = new ArrayList<>();
        ys = new ArrayList<>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

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

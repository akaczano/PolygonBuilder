import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

    private ArrayList<Integer> cxs;
    private ArrayList<Integer> cys;

    private int addedPoints = 0;

    private int screen_width;
    private int screen_height;

    private int map_width;
    private int map_height;

    private double ratio;

    private JLabel posLabel;

    private boolean connect = false;

    public void drawPolygon(){
        connect = true;
        this.repaint();
    }

    public void addLandmarks(ArrayList<Point> points) {
        for (Point p : points) {

            if (!isPointOnScreen(p)) {
                continue;
            }

            int x = longToX(p.getLongitude());
            int y = latToY(p.getLatitude());
            cxs.add(x);
            cys.add(y);
        }

    }

    public void clearScreen() {

        while (xs.size() > addedPoints) {
            xs.remove(addedPoints);
            ys.remove(addedPoints);
        }
        this.repaint();
    }


    public void addPoints(ArrayList<Point> points) {

        for (Point p : points) {
            if (!isPointOnScreen(p)) {
                continue;
            }
            addedPoints ++;
            int x = longToX(p.getLongitude());
            int y = latToY(p.getLatitude());
            xs.add(x);
            ys.add(y);
        }
    }

    public GraphicsPanel() {
        xs = new ArrayList<>();
        ys = new ArrayList<>();
        cxs = new ArrayList<>();
        cys = new ArrayList<>();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(new BorderLayout());

        posLabel = new JLabel();
        this.add(posLabel, BorderLayout.PAGE_END);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        screen_width = this.getBounds().width;
        screen_height = this.getBounds().height;

        double long_per_x = screen_width / (MAX_LONG - MIN_LONG);
        double lat_per_y = screen_height /  (MAX_LAT - MIN_LAT);

        ratio = Math.min(long_per_x, lat_per_y);
        map_width = (int) (ratio * (MAX_LONG - MIN_LONG));
        map_height = (int) (ratio * (MAX_LAT - MIN_LAT));
    }

    private double xToLong(int x) {
        return (x - MARGIN_X) / ratio + MIN_LONG;
    }

    private double yToLat(int y) {
        return ((map_height-y) - MARGIN_Y) / ratio + MIN_LAT;
    }

    private int longToX(double lon) {
        return (int)((lon-MIN_LONG) * ratio);
    }

    private int latToY(double lat) {
        int y = (int)((lat-MIN_LAT) * ratio);
        y = map_height - y;
        return y;
    }

    private boolean isPointOnScreen(Point p) {
        if (p.getLongitude() >= MIN_LONG && p.getLongitude() <= MAX_LONG) {
            if (p.getLatitude() >= MIN_LAT && p.getLatitude() <= MAX_LAT){
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int j  = 0; j < cxs.size(); ++j) {
            g.setColor(Color.BLUE);
            g.fillOval(cxs.get(j), cys.get(j), 5, 5);
        }
        g.setColor(Color.BLACK);

        for (int i = 0; i < xs.size(); ++i) {
            if (i == addedPoints) {
                g.setColor(Color.BLUE);
            }
            g.fillRect(xs.get(i), ys.get(i), 1, 1);

            if (connect && i >= addedPoints) {

                int startX = xs.get(i);
                int endX = i+1 >= xs.size() ? xs.get(addedPoints) : xs.get(i+1);

                int startY = ys.get(i);
                int endY = i+1 >= ys.size() ? ys.get(addedPoints) : ys.get(i+1);

                g.drawLine(startX, startY, endX, endY);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (connect) {
            connect = false;
            clearScreen();
        }
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
        if (connect) {
            connect = false;
            clearScreen();
        }

        xs.add(mouseEvent.getX());
        ys.add(mouseEvent.getY());
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        double longitude = ((mouseEvent.getX() - MARGIN_X) / ratio + MIN_LONG) * -1;
        double latitude = ((map_height-mouseEvent.getY()) - MARGIN_Y) / ratio + MIN_LAT;
        posLabel.setText(String.format("%.3f degrees west, %.3f degrees north", longitude, latitude));
    }

    public void saveToCSV(File file) {

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println("LONG,LAT");
            for (int i = addedPoints; i < xs.size(); ++i) {
                double longitude = xToLong(xs.get(i));
                double latitude = yToLat(ys.get(i));
                pw.println(String.format("%f,%f", longitude, latitude));
            }
            pw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}

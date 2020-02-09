import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Point {
    private double longitude;
    private double latitude;

    public Point(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public static ArrayList<Point> readCoordinates(File f) throws FileNotFoundException {
        ArrayList<Point> points = new ArrayList<>();
        Scanner sc = new Scanner(f);
        sc.nextLine();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] longAndLat = line.split(",");
            Point p = new Point(Double.parseDouble(longAndLat[0]), Double.parseDouble(longAndLat[1]));
            points.add(p);
        }
        return points;
    }

}

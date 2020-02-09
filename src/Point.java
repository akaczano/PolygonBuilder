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

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/shashankkumar/Desktop/longs_and_lats.csv");
        ArrayList<Point> longsAndLats = readCoordinates(f);

    }
}

public class Point implements Comparable<Point> {
    private int x, y;

    // Constructor to initialize the point with given coordinates
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter methods for x and y coordinates
    public int getX() { return x; }
    public int getY() { return y; }

    // Compare this point with another point
    @Override
    public int compareTo(Point point) {
        if (point == null) {
            throw new NullPointerException("Attempted to compare to null");
        }
        if (x == point.getX() && y == point.getY()) {
            return 0; // Points are equal
        }
        return -1; // Points are not equal
    }
}

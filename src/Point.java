public class Point implements Comparable<Point> {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public int compareTo(Point point) {
        if (point == null) {
            throw new NullPointerException("Attempted to compare to null");
        }
        if (x == point.getX() && y == point.getY()) {
            return 0;
        }
        return -1;
    }
}

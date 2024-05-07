public class Place implements Comparable<Place> {
    private String id;
    private String name;
    private final Point point;
    private String[] services;

    // Constructor to initialize a Place with all its attributes
    public Place(String id, String name, Point position, String[] services) {
        this.id = id;
        this.name = name;
        this.point = position;
        this.services = services;
    }

    // Getter and setter methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getServices() {
        return services;
    }

    public Point getPoint() {
        return point;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    // String representation of the place, including ID, name, position, and services
    @Override
    public String toString() {
        String res = "";
        res += "ID: " + id + ", Name: " + name + ", Point: (" + point.getX() + "," + point.getY() + "), Services: ";
        for (String service : services) {
            res += service + ", ";
        }
        return res;
    }

    // Comparison based on the position of the place
    @Override
    public int compareTo(Place otherPlace) {
        if (point.compareTo(otherPlace.point) == 0) {
            return 0;
        }
        return -1;
    }
}

// Node class for a linked list of places
class PlaceNode {
    Place place;
    PlaceNode next;

    // Constructor to initialize a node with a place and a reference to the next node
    public PlaceNode(Place place, PlaceNode next) {
        this.place = place;
        this.next = next;
    }

    // String representation of the node, which delegates to the toString method of the place object
    @Override
    public String toString() {
        return place.toString();
    }
}


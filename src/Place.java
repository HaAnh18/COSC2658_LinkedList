public class Place implements Comparable<Place> {
    private String id;
    private String name;
    private final Point point;
    private String[] services;

    public Place(String id, String name, Point position, String[] services) {
        this.id = id;
        this.name = name;
        this.point = position;
        this.services = services;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        String res = "";
        res += "ID: " + id + ", Name: " + name + ", Point: (" + point.getX() + "," + point.getY() + "), Services: ";
        for (String service : services) {
            res += service + ", ";
        }
        return res;
    }

    @Override
    public int compareTo(Place otherPlace) {
        if (point.compareTo(otherPlace.point) > 0) {
            return 0;
        }
        return -1;
    }
}

class PlaceNode {
    Place place;
    PlaceNode next;

    public PlaceNode(Place place, PlaceNode next) {
        this.place = place;
        this.next = next;
    }

    @Override
    public String toString() {
        return place.toString();
    }
}


import ADTs.ArrayQueue;
import ADTs.LinkedList;

public class Map2D extends LinkedList<PlaceNode> {
    PlaceNode head;
    public Map2D() {
        this.head = null;
    }

    public boolean add(String id, String name, int x, int y, String[] services) {
        Place newPlace = new Place(id, name, new Point(x, y), services);
        return insertAt(size(), new PlaceNode(newPlace, null));
    }

    public PlaceNode search(int x, int y) {
        if (head == null) {
            return null;
        }
        Point searchPoint = new Point(x, y);
        PlaceNode temp = head;
        while (temp != null) {
            if (temp.place.getPoint().compareTo(searchPoint) == 0) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean remove(int x, int y) {
        return remove(search(x, y));
    }

    public void edit(int x, int y, String name) {
        PlaceNode editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.place.setName(name);
        }
    }

    public void edit(int x, int y, String[] services) {
        PlaceNode editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.place.setServices(services);
        }
    }

    public void edit(int x, int y, String name, String[] services) {
        PlaceNode editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.place.setName(name);
            editedPlace.place.setServices(services);
        }
    }

    public ArrayQueue<Place> searchAvailable(int centerX, int centerY, int width, int height) {
        if (head == null) return null;

        int x1 = centerX - width / 2;
        int y1 = centerY - height / 2;
        int x2 = centerX + width / 2;
        int y2 = centerY + height / 2;

        ArrayQueue<Place> result = new ArrayQueue<>();

        PlaceNode current = head;
        while (current != null) {
            if (current.place.getPoint().getX() >= x1 && current.place.getPoint().getX() <= x2 &&
                    current.place.getPoint().getY() >= y1 && current.place.getPoint().getY() <= y2) {
                result.enQueue(current.place);
            }
            current = current.next;
        }
        return result;
    }
}


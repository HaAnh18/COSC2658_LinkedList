import ADTs.ArrayQueue;
import ADTs.LinkedList;

public class Map2D extends LinkedList<PlaceNode> {
    public boolean add(String id, String name, int x, int y, String[] services) {
        Place newPlace = new Place(id, name, new Point(x, y), services);
        reset();
        if (!hasNext()) {
            return insertAt(0, new PlaceNode(newPlace, null));
        }
        return insertAt(size(), new PlaceNode(newPlace, null));
//        return true;
    }

    public PlaceNode search(int x, int y) {
        reset();
        if (!hasNext()) {
            return null;
        }
        Point searchPoint = new Point(x, y);
        reset();
        while (hasNext()) {
            PlaceNode curr = next();
            if (curr.place.getPoint().compareTo(searchPoint) == 0) {
                return curr;
            }
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
        if (!hasNext()) return null;

        int x1 = centerX - width / 2;
        int y1 = centerY - height / 2;
        int x2 = centerX + width / 2;
        int y2 = centerY + height / 2;

        ArrayQueue<Place> result = new ArrayQueue<>();

        reset();
        while (hasNext()) {
            PlaceNode current = next();
            if (current.place.getPoint().getX() >= x1 && current.place.getPoint().getX() <= x2 &&
                    current.place.getPoint().getY() >= y1 && current.place.getPoint().getY() <= y2) {
                result.enQueue(current.place);
            }
        }
        return result;
    }
}


import ADTs.ArrayQueue;
import ADTs.LinkedList;

public class Map2D extends LinkedList<PlaceNode> {

    // Method to add a new place to the map
    public boolean add(String id, String name, int x, int y, String[] services) {
        Place newPlace = new Place(id, name, new Point(x, y), services);
        // Otherwise, insert the new place at the front of the list
        return insertAt(0, new PlaceNode(newPlace, null));
    }

    // Method to search for a place by its coordinates
    public PlaceNode search(int x, int y) {
        reset();
        if (!hasNext()) {
            // If the list is empty, return null
            return null;
        }
        Point searchPoint = new Point(x, y);
        reset(); // Reset the iterator again before traversing the list
        while (hasNext()) {
            PlaceNode curr = next();
            if (curr.place.getPoint().compareTo(searchPoint) == 0) {
                // If a place with matching coordinates is found, return it
                return curr;
            }
        }

        // If no matching place is found, return null
        return null;
    }

    // Method to remove a place from the map by its coordinates
    public boolean remove(int x, int y) {
        // Remove the node from the linked list
        return remove(search(x, y));
    }

    // Method to edit the name of a place by its coordinates
    public void edit(int x, int y, String name) {
        PlaceNode editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.place.setName(name);
        }
    }

    // Method to edit the services offered by a place by its coordinates
    public void edit(int x, int y, String[] services) {
        PlaceNode editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.place.setServices(services);
        }
    }

    // Method to edit both the name and services of a place by its coordinates
    public void edit(int x, int y, String name, String[] services) {
        PlaceNode editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.place.setName(name);
            editedPlace.place.setServices(services);
        }
    }

    // Method to search for available places within a specified rectangular area
    public ArrayQueue<Place> searchAvailable(int centerX, int centerY, int width, int height, String service, int maxResult) {
        if (!hasNext()) return null;

        // Calculate the coordinates of the rectangular area
        int x1 = centerX - width / 2;
        int y1 = centerY - height / 2;
        int x2 = centerX + width / 2;
        int y2 = centerY + height / 2;

        ArrayQueue<Place> result = new ArrayQueue<>();

        // Iterate over the places in the map
        reset();
        while (hasNext()) {
            if (result.size() >= maxResult) {
                return result;
            }
            PlaceNode current = next();
            String[] serviceList = current.place.getServices();
            // Check if the current place is within the specified rectangular area
            if (current.place.getPoint().getX() >= x1 && current.place.getPoint().getX() <= x2 &&
                    current.place.getPoint().getY() >= y1 && current.place.getPoint().getY() <= y2) {
                for (String serviceType : serviceList) {
                    if (serviceType.equals(service)) {
                        result.enQueue(current.place);
                    }
                }
            }
        }
        return result;
    }
}


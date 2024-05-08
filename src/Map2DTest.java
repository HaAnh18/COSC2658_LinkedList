import ADTs.ArrayQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Map2DTest {
    private Map2D map;
    private PlaceNode place1, place2, place3, place4, place5;

    @BeforeEach
    public void setUp() {
        map = new Map2D();

        // Add the places to the map
        map.add("A1", "Restaurant A1", 10, 20, new String[]{"Restaurant", "WiFi"});
        map.add("A2", "Cafe A2", 15, 25, new String[]{"Cafe", "Parking"});
        map.add("A3", "Library A3", 20, 30, new String[]{"Library", "WiFi"});
        map.add("A4", "Market A4", 25, 35, new String[]{"Market", "Delivery"});
        map.add("A5", "Hospital A5", 30, 40, new String[]{"Hospital", "Parking"});
    }

    @Test
    public void testAddAndSearchPlace() {
        // Verify that all five places can be found in the map
        PlaceNode foundPlace1 = map.search(10, 20);
        assertNotNull(foundPlace1, "Place should exist at coordinates (10, 20)");
        assertEquals("Restaurant A1", foundPlace1.place.getName(), "Place name should match");

        PlaceNode foundPlace2 = map.search(15, 25);
        assertNotNull(foundPlace2, "Place should exist at coordinates (15, 25)");
        assertEquals("Cafe A2", foundPlace2.place.getName(), "Place name should match");

        PlaceNode foundPlace3 = map.search(20, 30);
        assertNotNull(foundPlace3, "Place should exist at coordinates (20, 30)");
        assertEquals("Library A3", foundPlace3.place.getName(), "Place name should match");

        PlaceNode foundPlace4 = map.search(25, 35);
        assertNotNull(foundPlace4, "Place should exist at coordinates (25, 35)");
        assertEquals("Market A4", foundPlace4.place.getName(), "Place name should match");

        PlaceNode foundPlace5 = map.search(30, 40);
        assertNotNull(foundPlace5, "Place should exist at coordinates (30, 40)");
        assertEquals("Hospital A5", foundPlace5.place.getName(), "Place name should match");

        // Test for a non-existing place
        PlaceNode nonExistingPlace = map.search(100, 200);
        assertNull(nonExistingPlace, "Place should not exist at coordinates (100, 200)");
    }

    @Test
    public void testRemovePlace() {
        // Remove an existing place and verify its absence
        assertTrue(map.remove(10, 20), "Place should be removed at coordinates (10, 20)");
        assertNull(map.search(10, 20), "Place should no longer exist at coordinates (10, 20)");

        // Attempt to remove a non-existent place
        assertFalse(map.remove(100, 200), "Attempting to remove a non-existing place should return false");
    }

    @Test
    public void testEditPlaceName() {
        // Update the name of a place
        map.edit(10, 20, "Updated Restaurant A1");
        PlaceNode editedPlace = map.search(10, 20);
        assertNotNull(editedPlace, "Updated place should exist");
        assertEquals("Updated Restaurant A1", editedPlace.place.getName(), "Name should be updated");
    }

    @Test
    public void testEditPlaceServices() {
        // Update the services of a place
        map.edit(15, 25, new String[]{"Cafe", "WiFi"});
        PlaceNode editedPlace = map.search(15, 25);
        assertNotNull(editedPlace, "Updated place should exist");
        assertArrayEquals(new String[]{"Cafe", "WiFi"}, editedPlace.place.getServices(), "Services should be updated");
    }

    @Test
    public void testSearchAvailablePlaces() {
        // Search for places offering "Parking" within a specified region
        ArrayQueue<Place> results = map.searchAvailable(20, 30, 30, 30, "Parking", 3);
        assertEquals(2, results.size(), "Two places should be found with 'Parking'");
        assertEquals("Cafe A2", results.peekFront().getName(), "First found place should be 'Cafe A2'");
        results.deQueue();
        assertEquals("Hospital A5", results.peekFront().getName(), "Second found place should be 'Hospital A5'");
    }
}

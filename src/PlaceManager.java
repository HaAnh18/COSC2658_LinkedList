import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class PlaceManager {
    private static final Random rand = new Random();
    private static final String[] types = {"Restaurant", "Hospital", "Library", "Cafe", "Market"};
    private static final String[] additionalServices = {"WiFi", "Parking", "Drive-through", "Delivery", "Pet-friendly"};

    public static void generateAllPlaces(Map2D map, String filename, int count) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))))) {
            generatePlaces(map, out, 'P', 1, 10000000, count); // Call the helper method to generate places
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void generatePlaces(Map2D map, PrintWriter out, char prefix, int min, int max, int count) {
        Set<Point> generatedPoints = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int x, y;
            Point point;
            // Generate unique coordinates until a new point is found
            do {
                x = rand.nextInt(max - min + 1) + min;
                y = rand.nextInt(max - min + 1) + min;
                point = new Point(x, y);
            } while (generatedPoints.contains(point));

            generatedPoints.add(point);

            String type = types[rand.nextInt(types.length)];
            String[] services = {type, additionalServices[rand.nextInt(additionalServices.length)]};
            String id = prefix + Integer.toString(i + 1);
            String name = "Place " + id;

            // Output the new Place data to a file
            out.printf("%s,%s,%d,%d,%s\n", id, name, x, y, String.join(";", services));
        }
    }

    public static void loadPlacesFromFile(Map2D map, String filename, int maxPoints) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            int pointsLoaded = 0; // Counter for the number of points loaded
            while (scanner.hasNextLine() && pointsLoaded < maxPoints) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 5) {  // Ensure the line has enough parts to parse
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    int x = Integer.parseInt(parts[2].trim());
                    int y = Integer.parseInt(parts[3].trim());
                    String[] services = parts[4].split(";"); // Split services by semicolon

                    // Add the new Place to the Map2D using the updated addNew method that handles KD-tree logic
                    map.add(id, name, x, y, services);
                    pointsLoaded++; // Increment the counter after each place is added
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file " + filename + " was not found.");
        } catch (NumberFormatException e) {
            System.err.println("Error: Number format exception while parsing coordinates.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

}

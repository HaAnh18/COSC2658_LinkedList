public class Main {
    public static void main(String[] args) {
       Map2D map = new Map2D();
       PlaceManager.loadPlacesFromFile(map, "Cluster.csv",1000000);
       System.out.println(map.size());
    }
}
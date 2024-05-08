public class Main {
    public static void main(String[] args) {
       Map2D map = new Map2D();
       map.add("1", "1",1, 4, new String[] {"Restaurant"});
        map.add("2", "2",2, 6, new String[] {"Restaurant"});
        map.add("3", "3",4, 9, new String[] {"Restaurant"});
//       PlaceManager.loadPlacesFromFile(map, "Cluster.csv",1000000);
//       System.out.println(map.size());
//        System.out.println(map.get(0));
//        PlaceNode temp = map.get(0);
//        System.out.println(map.search(2,6));
        map.reset();

//        System.out.println(temp.next);
        System.out.println("Before remove: ");
        while (map.hasNext()) {
            System.out.println(map.next());
        }
        map.remove(1,4);
        map.reset();
        System.out.println("After remove: ");
        while (map.hasNext()) {
            System.out.println(map.next());
        }
    }
}
import java.util.*;

public class aoc1 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list1.add(scan.nextInt());
            list2.add(scan.nextInt());
        }
        Collections.sort(list1);
        Collections.sort(list2);

        int total = 0;
        for (int i = 0; i < 1000; i++) {
            total += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println(total);

        // pt 2

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            map1.put(list1.get(i), map1.getOrDefault(list1.get(i), 0) + 1);
            map2.put(list2.get(i), map2.getOrDefault(list2.get(i), 0) + 1);
        }

        int total2 = 0;

        for (int i = 0; i < 1000; i++) {
            total2 += list1.get(i) * map2.getOrDefault(list1.get(i), 0);
        }
        System.out.println(total2);


    }
}

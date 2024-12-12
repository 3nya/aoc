import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class aoc11 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String str = scan.nextLine();
        String[] strings = str.split(" ");
        List<Long> list = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();

        for (String string : strings) {
            list.add((long) Integer.parseInt(string));
            list2.add((long) Integer.parseInt(string));

        }

        // brute force
        // change to amount of blinks
        for (int i = 0; i < 25; i++) {
            List<Long> templist = new ArrayList<>();

            for (Long curr : list) {
                String temp = Long.toString(curr);

                // cond 1
                if (curr == 0) {
                    templist.add(1L);
                } else if (temp.length() % 2 == 0) {
                    templist.add(Long.parseLong(temp.substring(0, temp.length() / 2)));
                    templist.add(Long.parseLong(temp.substring(temp.length() / 2)));
                } else {
                    templist.add(curr * 2024);
                }
            }
            list.clear();
            list.addAll(templist);
        }
        System.out.println(list.size());

        // part 2
        long total = part2(list2, 75);
        System.out.println(total);
    }
    public static long part2(List<Long> list, int blinks) {
        HashMap<Long, Long> map = new HashMap<>();
        for (Long curr : list) {
            map.put(curr, map.getOrDefault(curr, 0L) + 1);
        }

        for (int i = 0; i < blinks; i++) {
            // blink
            HashMap<Long, Long> tempMap = new HashMap<>();
            for (Long curr : map.keySet()) {
                String temp = Long.toString(curr);

                if (curr == 0) {
                    tempMap.put(1L, map.get(curr));
                } else if (temp.length() % 2 == 0) {
                    long l = Long.parseLong(temp.substring(0, temp.length() / 2));
                    long l2 = Long.parseLong(temp.substring(temp.length() / 2));

                    tempMap.put(l, tempMap.getOrDefault(l, 0L) + map.get(curr));
                    tempMap.put(l2, tempMap.getOrDefault(l2, 0L) + map.get(curr));
                } else {
                    tempMap.put(curr * 2024, tempMap.getOrDefault(curr * 2024, 0L) + map.get(curr));
                }
            }
            map.clear();
            map.putAll(tempMap);
        }
        long total = 0;
        for (Long l : map.keySet()) {
            total += map.get(l);
        }
        return total;

    }
}

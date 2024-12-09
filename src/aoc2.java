import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class aoc2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int safe = 0;
        for (int i = 0; i < 1000; i++) {
            String str = scan.nextLine();

            String[] strs = str.split(" ");
            List<Integer> list = new ArrayList<>();

            for (String s : strs) {
                list.add(Integer.parseInt(s));
            }

            boolean inc = increasing(list);
            boolean dec = decreasing(list);
            if (inc || dec) {
                safe++;
            } else {
                for (int j = 0; j < list.size(); j++) {
                    List<Integer> duplicate = new ArrayList<>(List.copyOf(list));
                    duplicate.remove(j);
                    if (increasing(duplicate) || decreasing(duplicate)) {
                        safe++;
                        break;
                    }

                }
            }
        }
        System.out.println(safe);

    }
    public static boolean increasing(List<Integer> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            int one = list.get(j);
            int two = list.get(j + 1);


            if (one > two && one - two >= 1 && one - two <= 3) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
    public static boolean decreasing(List<Integer> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            int two = list.get(j);
            int one = list.get(j + 1);


            if (one > two && one - two >= 1 && one - two <= 3) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
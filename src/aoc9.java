import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class aoc9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        // 12345
        // 1 block file, 2 blocks of free space,
        // 3 block file, 4 blocks of free space,
        // 5 block file

        // . are free spaces
        // 0..111....22222
        // after re-ordering:
        // 022111222......

        // block position multipled by ID number
        // 0 * 0 = 0
        // 2 * 1 = 2
        // etc
        String str = scan.nextLine();
        StringBuilder newStr = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        int currID = 0;
        for (int i = 0; i < str.length(); i++) {
            int num = Integer.parseInt(String.valueOf(str.charAt(i)));
            if (i % 2 == 0) {
                for (int j = 0; j < num; j++) {
//                    newStr.append(currID);
                    list.add(currID);
                }
                currID++;
            } else {
                for (int j = 0; j < num; j++) {
//                    newStr.append('.');
                    list.add(-1);
                }
            }
        }
        List<Integer> newList = new ArrayList<>(list);
        List<Integer> list1 = pt1Reorder(newList);

        List<Integer> list2 = pt2Reorder(list);



        // pt1 output
        long total = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != -1) {
                total += (long) i * list1.get(i);
            }
        }
        System.out.println(total);

        // pt 2 output
        long total2 = 0;
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i) != -1) {
                total2 += (long) i * list2.get(i);
            }
        }
        System.out.println(total2);
    }
    public static List<Integer> pt1Reorder(List<Integer> list) {
        boolean end = false;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != -1) {
                int curr = list.get(i);
                // search from left-most block
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) == -1) {
                        if (j > i) {
                            end = true;
                            break;
                        }
                        list.remove(i);
                        list.add(i, -1);
                        list.remove(j);
                        list.add(j, curr);
                        break;
                    }
                }
                if (end) {
                    break;
                }
            }
        }
        return list;
    }
    public static List<Integer> pt2Reorder(List<Integer> list) {
        boolean end = false;
        HashSet<Integer> DOESNTWORK = new HashSet<>();
        HashSet<Integer> DOESWORK = new HashSet<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            // find all same ID blocks


            int curr = list.get(i);
            if (curr != -1 && !DOESNTWORK.contains(curr)) {
//                if (DOESWORK.contains(curr)) {
//                    break;
//                }
                int start = 0;
                // starting index , i is ending index
                for (int j = i; j >= 0; j--) {
                    if (list.get(j) == curr) {
                        continue;
                    } else {
                        start = j + 1;
                        break;
                    }
                }

                // finding space

                boolean found = false;
                int currSpace = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (currSpace == i - start + 1) {
                        if (j >= i) {
                            break;
                        }
                        // put all of the IDS into this space

                        for (int k = j - currSpace; k < j; k++) {
                            list.remove(k);
                            list.add(k, curr);
                        }
                        for (int k = i; k >= start; k--) {
                            list.remove(k);
                            list.add(k, -1);
                        }
                        found = true;
                        break;
                    }
                    if (list.get(j) == -1) {
                        currSpace++;
                    } else {
                        currSpace = 0;
                    }
                }
                if (!found) {
                    DOESNTWORK.add(curr);
                } else {
                    DOESWORK.add(curr);
                }
            }

            if (end) {
                break;
            }
        }
        return list;
    }
}

import java.util.*;

public class aoc5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        // X | Y
        // X must be printed before Y

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < 1176; i++) {
            String str = scan.nextLine();

            String[] arr = str.split("\\|");

            int one = Integer.parseInt(arr[0]);
            int two = Integer.parseInt(arr[1]);

            ArrayList<Integer> list = map.getOrDefault(one, new ArrayList<>());

            list.add(two);
            map.put(one, list);
        }

        String randomSpace = scan.nextLine();
        int total = 0;
        List<String> incorrectUpdates = new ArrayList<>();
        for (int i = 0; i < 217; i++) {
            String str = scan.nextLine();

            String[] arr= str.split(",");
            List<Integer> currPages = new ArrayList<>();
            boolean valid = true;
            for (int j = 0; j < arr.length; j++) {
                int currPage = Integer.parseInt(arr[j]);
                currPages.add(currPage);

                ArrayList<Integer> list = map.getOrDefault(currPage, new ArrayList<>());

                if (list.size() > 0) {
                    for (Integer page : list) {
                        if (currPages.contains(page)) {
                            if (!(incorrectUpdates.contains(str))) {
                                incorrectUpdates.add(str);

                            }

                            valid = false;
                            break;
                        }
                    }
                }

            }
            if (valid) {
                total += currPages.get(arr.length / 2);
            }
        }
        System.out.println(total);

        // pt 2 -----------------------------------------------------------------------------
        int pt2total = 0;
        for (String update : incorrectUpdates) {
            String[] arr = update.split(",");
            int[] pages = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                pages[i] = Integer.parseInt(arr[i]);
            }



            while (true) {
                boolean correct = true;
                for (int i = 0; i < pages.length; i++) {
                    ArrayList<Integer> list = map.getOrDefault(pages[i], new ArrayList<>());
                    List<Integer> removed = new ArrayList<>();

                    for (Integer page : list) {
                        int wrongindex = findIndex(pages, page);


                        if (wrongindex != -1 && wrongindex < i) {
                            int temp = pages[i];
                            pages[i] = pages[wrongindex];
                            pages[wrongindex] = temp;
                            correct = false;

                            removed.add(page);
                        }
                    }
                }
                if (correct) {
                    break;
                }
            }


            pt2total += pages[pages.length / 2];
        }
        System.out.println(pt2total);
    }
    private static int findIndex(int[] pages, int target) {
        for (int i = 0; i < pages.length; i++) {
            if (pages[i] == target) {
                return i;
            }
        }
        return -1; // page not found
    }
}

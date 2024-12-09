import java.util.Scanner;

public class aoc8 {
    public static char[][] map;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        map = new char[50][50];
        char[][] arr = new char[50][50];
        for (int i = 0; i < 50; i++) {
            String str = scan.nextLine();

            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != '.') {
                    char curr = arr[i][j];

                    for (int k = i; k < arr.length; k++) {
                        for (int l = 0; l < arr[0].length; l++) {
                            if (arr[k][l] == curr && (k != i || l != j)) {
                                slope(arr, j, l, i, k);
                            }
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (map[i][j] != '#') {
                    System.out.print('.');
                } else System.out.print(map[i][j]);
                if (map[i][j] == '#') {
                    count++;
                }
            }
            System.out.println("");
        }

        System.out.println(count);
    }
    // x1/y1 is the original antenna
    public static void slope(char[][] curr, int x1, int x2, int y1, int y2) {
        int ychange = Math.abs(y1 - y2);
        int xchange = Math.abs(x1 - x2);
        System.out.println("ychange: " + ychange);
        System.out.println("xchange:" + xchange);
        map[y1][x1] = '#';

        if (x1 < x2) {
            boolean yes = true;
            int ytemp = y1;
            int xtemp = x1;
            while (yes) {
                try {
                    ytemp -= ychange;
                    xtemp -= xchange;
                    map[ytemp][xtemp] = '#';
                } catch (Exception e) {
                    yes = false;
                }

            }
            boolean yes1 = true;
            int ytemp1 = y1;
            int xtemp1 = x1;
            while (yes1) {
                try {
                    ytemp1 += ychange;
                    xtemp1 += xchange;
                    map[ytemp1][xtemp1] = '#';

                } catch (Exception e) {
                    yes1 = false;
                }
            }
        } else {
            boolean yes = true;
            int ytemp = y1;
            int xtemp = x1;
            while (yes) {

                try {
                    ytemp += ychange;
                    xtemp -= xchange;
                    map[ytemp][xtemp] = '#';
                } catch (Exception e) {
                    yes = false;
                }

            }
            boolean yes1 = true;
            int ytemp1 = y1;
            int xtemp1 = x1;
            while (yes1) {

                try {
                    ytemp1 -= ychange;
                    xtemp1 += xchange;
                    map[ytemp1][xtemp1] = '#';

                } catch (Exception e) {
                    yes1 = false;
                }
            }
        }


        // part 1
//        if (x1 < x2) {
//            try {
//                map[y1 - ychange][x1 - xchange] = '#';
//            } catch (Exception e) {
//            }
//            try {
//                map[y2 + ychange][x2 + xchange] = '#';
//            } catch (Exception e) {
//            }
//        } else { // if x1 is on the right
//            try {
//                map[y2 + ychange][x2 - xchange] = '#';
//            } catch (Exception e) {
//            }
//            try {
//                map[y1 - ychange][x1 + xchange] = '#';
//            } catch (Exception e) {
//            }
//        }

        System.out.println("----------------------------------------------");
        // testing
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '#') {
                    System.out.print('.');
                } else System.out.print(map[i][j]);
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------");
    }
}

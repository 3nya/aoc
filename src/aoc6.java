import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class aoc6 {
    public static char[][] arr;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        arr= new char[130][130];
        int x = 0;
        int y = 0;
        for (int i = 0; i < 130; i++) {
            String str = scan.nextLine();
            for (int j = 0; j < 130; j++) {
                arr[i][j] = str.charAt(j);

                if (arr[i][j] == '^') {
                    x = j;
                    y = i;
                }
            }
        }

        int guardPos = 0;
        // 0 = going up
        // 1 = going left
        // 2 = going right
        // 3 = going down

        int block = 0;
        while ((x < 130 && x > 0) && (y < 130 && y > 0)) {
            boolean canStep = canStep(x, y, guardPos);
            if (guardPos == 0) {
                if (canStep) {
                    y--;
                } else {
                    guardPos = 2;
                }
            } else if (guardPos == 1) {
                if (canStep) {
                    x--;
                } else {
                    guardPos = 0;
                }
            } else if (guardPos == 2) {
                if (canStep) {
                    x++;
                } else {
                    guardPos = 3;
                }
            } else if (guardPos == 3) {
                if (canStep) {
                    y++;
                } else {
                    guardPos = 1;
                }
            }

            if (checkIfInfiniteLoop(x,y)) {
                block++;
            }
        }

        System.out.println(block);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 'X') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    public static boolean canStep(int x, int y, int pos) {
        try {
            if (pos == 0) {
                if (arr[y - 1][x] != '#') {
                    arr[y - 1][x] = 'X';
                    return true;
                }
            }
            if (pos == 1) {
                if (arr[y][x - 1] != '#') {
                    arr[y][x - 1] = 'X';
                    return true;
                }
            }
            if (pos == 2) {
                if (arr[y][x + 1] != '#') {
                    arr[y][x + 1] = 'X';
                    return true;
                }
            }
            if (pos == 3) {
                if (arr[y + 1][x] != '#') {
                    arr[y + 1][x] = 'X';
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }

        return false;
    }
    public static boolean checkIfInfiniteLoop(int x, int y) {
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;

        int xstop1 = 0;
        int ystop1 = 0;
        int ystop2 = 0;
        int xstop2 = 0;
        while (x < 130) {
            try {
                if (arr[y][x] == '#') {
                    xstop1 = x;
                    found1 = true;
                    break;
                }
                x++;
            } catch (Exception e) {
                break;
            }

        }
        while (x > 0) {
            try {
                if (arr[y][x] == '#') {
                    xstop2 = x;
                    found2 = true;
                    break;
                }
                x--;
            } catch (Exception e) {
                break;
            }

        }
        while (y > 0) {
            try {
                if (arr[y][x] == '#') {
                    ystop1 = y;
                    found3 = true;
                    break;
                }
                y--;
            } catch (Exception e) {
                break;
            }

        }
        while (y < 130) {
            try {
                if (arr[y][x] == '#') {
                    ystop2 = y;
                    found4 = true;
                    break;
                }
                y++;
            } catch (Exception e) {
                break;
            }

        }
        if (found1 && found2 && found3 && found4) {

            return true;
        }
        return false;
    }
}

import java.util.Scanner;

public class aoc4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        char arr[][] = new char[140][140];
        for (int i = 0; i < 140; i++) {
            String str = scan.nextLine();

            count += forwards(str);
            count += backwards(str);
            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }

        }
        count += diagonalLeft(arr);
        count += diagonalRight(arr);
        count += updown(arr);

        int xmas = xmas(arr);
        System.out.println(xmas);
        System.out.println(count);


    }
    public static int forwards(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 3; i++) {
            if (str.substring(i, i + 4).equals("XMAS")) {
                count++;
            }
        }
        return count;
    }
    public static int backwards(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 3; i++) {
            if (str.substring(i, i + 4).equals("SAMX")) {
                count++;
            }
        }
        return count;
    }
    public static int diagonalRight(char arr[][]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    if (arr[i][j] == 'X' && arr[i + 1][j + 1] == 'M'
                            && arr[i + 2][j + 2] == 'A' && arr[i + 3][j + 3] == 'S') {
                        count++;
                    } else if (arr[i][j] == 'S' && arr[i + 1][j + 1] == 'A'
                            && arr[i + 2][j + 2] == 'M' && arr[i + 3][j + 3] == 'X') {
                        count++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return count;
    }
    public static int diagonalLeft  (char arr[][]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    if (arr[i][j] == 'X' && arr[i - 1][j + 1] == 'M'
                            && arr[i - 2][j + 2] == 'A' && arr[i - 3][j + 3] == 'S') {
                        count++;
                    } else if (arr[i][j] == 'S' && arr[i - 1][j + 1] == 'A'
                            && arr[i - 2][j + 2] == 'M' && arr[i - 3][j + 3] == 'X') {
                        count++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return count;
    }
    public static int updown (char arr[][]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    if (arr[i][j] == 'X' && arr[i + 1][j] == 'M'
                            && arr[i + 2][j] == 'A' && arr[i + 3][j] == 'S') {
                        count++;
                    } else if (arr[i][j] == 'S' && arr[i + 1][j] == 'A'
                            && arr[i + 2][j] == 'M' && arr[i + 3][j] == 'X') {
                        count++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return count;
    }
    public static int xmas (char arr[][]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    if (arr[i][j] == 'M' && arr[i + 1][j + 1] == 'A' && arr[i + 2][j + 2] == 'S' ||
                            arr[i][j] == 'S' && arr[i + 1][j + 1] == 'A' && arr[i + 2][j + 2] == 'M') {
                        if (arr[i][j + 2] == 'M' && arr[i + 1][j + 1] == 'A' && arr[i + 2][j] == 'S' ||
                                arr[i][j + 2] == 'S' && arr[i + 1][j + 1] == 'A' && arr[i + 2][j] == 'M') {
                            count++;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return count;
    }
}

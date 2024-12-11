import java.util.List;
import java.util.Scanner;

public class aoc10 {
    public static int startI;
    public static int startJ;
    public static int uniquePaths;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[][] arr = new int[53][53];
        for (int i = 0; i < 53; i++) {
            String str = scan.nextLine();

            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    boolean[][] visited = new boolean[arr.length][arr[0].length];
                    startI = i;
                    startJ = j;
                    total += dfs(arr, i, j, -1, visited);
                }
            }
        }
        // pt1
        System.out.println(total);
        // pt2
        System.out.println(uniquePaths);
    }

    // pt1
    public static int dfs(int[][] arr, int i, int j, int prev, boolean[][] visited) {
        int count = 0;
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }

        if (arr[i][j] == prev + 1) {
            visited[i][j] = true;

            if (arr[i][j] == 9) {
                int[][] dp = new int[arr.length][arr[0].length];
                dp = countUniquePaths(arr, i, j, startI, startJ, -1, dp);
                uniquePaths += dp[i][j];
                return 1;
            }
            for (int[] direction : dir) {
                count += dfs(arr, i + direction[0], j + direction[1], arr[i][j], visited);
            }
        } else {
            return 0;
        }

        return count;
    }
    // pt 2
    // i9 and j9 is spot of the 9
    // i0 and j0 is spot of the 0
    public static int[][] countUniquePaths(int[][] arr, int i9, int j9, int i, int j, int prev, int[][] dp) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length) {
            return dp;
        }

        if (i == i9 && j == j9 && prev == 8) {
            dp[i][j] += 1;
            return dp;
        }

        if (arr[i][j] == prev + 1) {
            dp[i][j] += 1;
            for (int[] direction : dir) {
                dp = countUniquePaths(arr, i9, j9, i + direction[0], j + direction[1], arr[i][j], dp);
            }
        }

        return dp;
    }

}

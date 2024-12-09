import java.util.Scanner;

public class aoc3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int curr = 0;
        // lines
        boolean enabled = true;
        for (int i = 0; i < 6; i ++) {
            String str = scan.nextLine();

            for (int j = 0; j < str.length(); j++) {
                try {
                    if (str.substring(j, j + 4).equals("do()")) {
                        enabled = true;
                    }
                    if (str.substring(j, j + 7).equals("don't()")) {
                        enabled = false;
                    }
                    if (str.substring(j, j + 4).equals("mul(")) {
                        boolean valid = true;
                        boolean comma = false;
                        boolean skip = false;
                        // first num: j + 4 -> firstNumEndIndex
                        // second num: firstNumEndIndex + 1 -> endIndex
                        int firstNumEndIndex = 0;
                        int endIndex = 0;

                        for (int k = j + 4; k < j + 4 + 9; k++) {
                            if (str.charAt(k) == ',' && !comma) {
                                firstNumEndIndex = k;
                                comma = true;
                            } else if (str.charAt(k) == ',' && comma) {
                                valid = false;
                            }
                            if (str.charAt(k) == ')') {
                                endIndex = k;
                                break;
                            }
                        }

                        if (firstNumEndIndex == 0 || endIndex == 0 || !comma || !valid) {
                            skip = true;
                        }
                        if (!(skip)) {
                            int first = num(str.substring(j + 4, firstNumEndIndex));
                            int second = num(str.substring(firstNumEndIndex + 1, endIndex));

                            if (first == -1 || second == -1) {
                                continue;
                            } else {
                                if (enabled) {
                                    curr += first * second;
                                }


                            }
                        }
                    }

                } catch (Exception e) {
                    break;
                }
            }
        }
        System.out.println(curr);

    }
    public static int num(String str) {
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                num.append(str.charAt(i));
            } else {
                return -1;
            }
        }
        return Integer.parseInt(num.toString());
    }
}

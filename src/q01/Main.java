import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Upper half of hollow diamond
        for (int i = 1; i <= n; i++) {

            // Print leading spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }

            // Print hollow pattern
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (j == 1 || j == 2 * i - 1) {
                    System.out.print("*"); // border
                } else {
                    System.out.print(" "); // hollow space
                }
            }

            System.out.println();
        }

        // Lower half of hollow diamond
        for (int i = n - 1; i >= 1; i--) {

            // Print leading spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }

            // Print hollow pattern
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (j == 1 || j == 2 * i - 1) {
                    System.out.print("*"); // border
                } else {
                    System.out.print(" "); // hollow space
                }
            }

            System.out.println();
        }
    }
}

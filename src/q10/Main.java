import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String[] tokens = line.split(" ");
        int[] stack = new int[tokens.length];
        int top = -1;

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack[top--];
                int a = stack[top--];

                int res = 0;
                if (token.equals("+")) res = a + b;
                else if (token.equals("-")) res = a - b;
                else if (token.equals("*")) res = a * b;
                else if (token.equals("/")) res = a / b;

                stack[++top] = res;
            } else {
                stack[++top] = Integer.parseInt(token);
            }
        }

        System.out.println(stack[top]);
        sc.close();
    }
}

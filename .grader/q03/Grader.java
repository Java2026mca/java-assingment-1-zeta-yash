import java.io.*;
import java.util.concurrent.*;

public class Grader {
    static final String[][] T = {
        {"1","1\nDiagonal: 1"},
        {"2","1 2\n4 3\nDiagonal: 4"},
        {"3","1 2 3\n8 9 4\n7 6 5\nDiagonal: 15"},
        {"4","1 2 3 4\n12 13 14 5\n11 16 15 6\n10 9 8 7\nDiagonal: 36"}
    };

    static String run(String in) throws Exception {
        // Step 1: Compile Main.java
        ProcessBuilder compile = new ProcessBuilder("javac", "src/q03/Main.java");
        compile.redirectErrorStream(true);
        Process p1 = compile.start();
        p1.waitFor();

        // Step 2: Run Main class
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q03", "Main");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        // Provide input
        try (OutputStream os = p.getOutputStream()) {
            os.write((in + "\n").getBytes());
        }

        // Timeout protection
        if (!p.waitFor(5, TimeUnit.SECONDS)) {
            p.destroy();
            return "TIMEOUT";
        }

        // Capture output
        String output = new String(p.getInputStream().readAllBytes())
                            .replaceAll("\r\n", "\n")
                            .stripTrailing();

        return output;
    }

    public static void main(String[] args) throws Exception {
        int pass = 0;

        for (int i = 0; i < T.length; i++) {
            String got = run(T[i][0]).strip();
            String exp = T[i][1].strip();

            boolean ok = got.equals(exp);
            if (ok) pass++;

            System.out.println((ok ? "✅" : "❌") + " Q03 Test " + (i + 1) + ": " +
                (ok ? "PASS" : "FAIL | Expected:\n" + exp + "\nGot:\n" + got));
        }

        System.out.println("Q03: " + pass + "/" + T.length);

        if (pass < T.length) System.exit(1);
    }
}

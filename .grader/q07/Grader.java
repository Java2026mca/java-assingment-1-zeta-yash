import java.io.*;
import java.util.concurrent.*;

public class Grader {
    static final String[][] T = {
        {"5\n64 34 25 12 22","12 22 25 34 64\nSwaps: 7"},
        {"4\n4 3 2 1","1 2 3 4\nSwaps: 6"},
        {"3\n1 2 3","1 2 3\nSwaps: 0"},
        {"5\n5 1 4 2 8","1 2 4 5 8\nSwaps: 5"},
    };

    static String run(String in) throws Exception {
        // Compile
        ProcessBuilder compile = new ProcessBuilder("javac", "src/q07/Main.java");
        compile.redirectErrorStream(true);
        Process p1 = compile.start();
        p1.waitFor();

        // Run
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "src/q07", "Main");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        try (OutputStream os = p.getOutputStream()) {
            os.write((in + "\n").getBytes());
        }

        if (!p.waitFor(5, TimeUnit.SECONDS)) {
            p.destroy();
            return "TIMEOUT";
        }

        return new String(p.getInputStream().readAllBytes())
                .replaceAll("\r\n","\n")
                .stripTrailing();
    }

    public static void main(String[] a) throws Exception {
        int pass = 0;

        for (int i = 0; i < T.length; i++) {
            String got = run(T[i][0]).strip();
            String exp = T[i][1].strip();

            boolean ok = got.equals(exp);
            if (ok) pass++;

            System.out.println((ok ? "✅" : "❌") + " Q07 Test " + (i + 1) + ": " +
                (ok ? "PASS" : "FAIL | Expected:\n" + exp + "\nGot:\n" + got));
        }

        System.out.println("Q07: " + pass + "/" + T.length);
        if (pass < T.length) System.exit(1);
    }
}

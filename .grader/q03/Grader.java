import java.io.*;
public class Grader {
    static final String[][] T = {
        {"1","1\nDiagonal: 1"},{"2","1 2\n4 3\nDiagonal: 4"},
        {"3","1 2 3\n8 9 4\n7 6 5\nDiagonal: 15"},
        {"4","1 2 3 4\n12 13 14 5\n11 16 15 6\n10 9 8 7\nDiagonal: 36"}, //fixing the bug
    };
    static String run(String in) throws Exception {
        ProcessBuilder pb=new ProcessBuilder("java","-cp","src/q03","Main");
        pb.redirectErrorStream(true); Process p=pb.start();
        p.getOutputStream().write((in+"\n").getBytes());
        p.getOutputStream().flush(); p.getOutputStream().close();
        String o=new String(p.getInputStream().readAllBytes()).stripTrailing(); p.waitFor(); return o;
    }
    public static void main(String[] a) throws Exception {
        int pass=0;
        for(int i=0;i<T.length;i++){
            String got=run(T[i][0]).replaceAll("\r\n","\n").strip(), exp=T[i][1].strip();
            boolean ok=got.equals(exp); if(ok)pass++;
            System.out.println((ok?"✅":"❌")+" Q03 Test "+(i+1)+": "+(ok?"PASS":"FAIL | Expected:\n"+exp+"\nGot:\n"+got));
        }
        System.out.println("Q03: "+pass+"/"+T.length); if(pass<T.length)System.exit(1);
    }
}

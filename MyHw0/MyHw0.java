import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.StringBuilder;

public class MyHw0 {
  public static void main(String [] args) {
    Instant start = Instant.now();
    HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String l;
      while ((l = br.readLine()) != null) {
        int i = l.indexOf("|");
        Integer a = Integer.parseInt(l.substring(0, i));
        Integer b = Integer.parseInt(l.substring(i + 1, l.length()));
        list.add(l);
        HashMap<Integer, Integer> items = map.get(a);
        if(items == null) {
          items = new HashMap<>();
          items.put(b, 1);
          map.put(a, items);
        } else {
          if(items.get(b) == null) {
            Integer length = items.size();
            items.put(b, length + 1);
            map.put(a, items);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    OutputStream out = new BufferedOutputStream ( System.out );
    list.forEach((l)->{
      int i = l.indexOf("|");
      Integer a = Integer.parseInt(l.substring(0, i));
      Integer b = Integer.parseInt(l.substring(i + 1, l.length()));
      HashMap<Integer, Integer> m = map.get(a);
      try {
	       StringBuilder s = new StringBuilder(a + "|" + b + "[" + m.get(b) +  " of " + m.size() +  "]\n");
         out.write((s.toString()).getBytes());
      } catch (IOException e){
        e.printStackTrace();
      }
    });
    try {
      out.close();
    } catch (IOException e){
      e.printStackTrace();
    }
    Instant end = Instant.now();
    System.err.println(Duration.between(start, end));
  }
}

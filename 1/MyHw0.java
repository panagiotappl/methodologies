import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class MyHw0 {
    public static void main(String [] args) {
        Instant start = Instant.now();
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                int idx = line.indexOf("|");
                Integer a = Integer.parseInt(line.substring(0, idx));
                Integer b = Integer.parseInt(line.substring(idx + 1, line.length()));
                list.add(line);
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
        list.forEach((i)->{
            int idx = i.indexOf("|");
            Integer a = Integer.parseInt(i.substring(0, idx));
            Integer b = Integer.parseInt(i.substring(idx + 1, i.length()));
            HashMap<Integer, Integer> m = map.get(a);
            System.out.println(a + "|" + b + "[" + m.get(b) +  " of " + m.size() +  "]");
          }
        );
        Instant end = Instant.now();
        System.err.println(Duration.between(start, end));
    }
}

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
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                int idx = line.indexOf("|");
                String a = line.substring(0, idx);
                String b = line.substring(idx + 1, line.length());
                HashMap<String, Integer> items = map.get(a);
                // When X doesn't exist
                if(items == null) {
                    items = new HashMap<>();
                    // Create first Y with index 0
                    items.put(b, 1);
                    // Put X:{Y: 0}
                    map.put(a, items);
                //When X exists
                } else {
                    // If Y hasn't been added already
                    if(items.get(b) == null) {
                        Integer length = items.size();
                        items.put(b, length + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(list);
        list.forEach((i)->{
                int idx = i.indexOf("|");
                String a = i.substring(0, idx);
                String b = i.substring(idx + 1, i.length());
                System.out.println(a + "|" + b + "[ " + (map.get(a)).get(b) +  " of " + map.get(a).size() +  " ]");
            }
        );
        Instant end = Instant.now();
        System.err.println(Duration.between(start, end));
    }
}

import java.time.Duration;
import java.time.Instant;

class Node {
    long i;
    Node next;
}

public class J2 {
    public static long sum (Node first, Node node) {
        long counter = 0;
        node = first;
        while (node != null) {
            counter = counter + 1;
            node = node.next;
        }
        return counter;
    }

    public static void run() {
        Node node = new Node();
        Node last = node;
        for(int i = 0; i < 20000000; i ++){
            Node n = new Node();
            n.i = 1 % 8;
            last.next = n;
            last = n;
        }

        Node first = node;
        int deleted = 0;
        int i = 0;
        while (node != null){
            if (i % 8 != 0) {
                if(node.next != null) {
                    node.next = node.next.next;
                } else {
                    node.next = null;
                }
                node = node.next;
                deleted = deleted + 1;
            } else {
                node = node.next;
            }
            i = i + 1;
        }
        System.out.println("Deleted " + deleted + " nodes");

        long counter = 0;
        for (int j = 0; j < 10000; j++) {
            sum(first, node);
        }
        counter = sum(first, node);
        System.out.println("Sum is " + counter);
        node = first;
    }

    public static void main(String[] args) {
        run();

        Instant start = Instant.now();
        run();
        Instant end = Instant.now();
        System.err.println(Duration.between(start, end));
    }
}

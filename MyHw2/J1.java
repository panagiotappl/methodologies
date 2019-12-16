import java.time.Duration;
import java.time.Instant;

class Node {
    long i;
    long j;
    long k;
    long l;
    long m;
    long n;
    Node next;
}

public class J1 {
    public static String humanReadableByteCountSI(long bytes) {
        String s = bytes < 0 ? "-" : "";
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1000L ? bytes + " B"
                : b < 999_950L ? String.format("%s%.1f kB", s, b / 1e3)
                        : (b /= 1000) < 999_950L ? String.format("%s%.1f MB", s, b / 1e3)
                                : (b /= 1000) < 999_950L ? String.format("%s%.1f GB", s, b / 1e3)
                                        : (b /= 1000) < 999_950L ? String.format("%s%.1f TB", s, b / 1e3)
                                                : (b /= 1000) < 999_950L ? String.format("%s%.1f PB", s, b / 1e3)
                                                        : String.format("%s%.1f EB", s, b / 1e6);
    }

    public static void printStats() {
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Free memory (bytes): " + humanReadableByteCountSI(Runtime.getRuntime().freeMemory()));
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println(
                "Maximum memory: " + (maxMemory == Long.MAX_VALUE ? "no limit" : humanReadableByteCountSI(maxMemory)));
        System.out.println(
                "Total memory available to JVM: " + humanReadableByteCountSI(Runtime.getRuntime().totalMemory()));
        System.out.println("#########################");
    }

    public static void run(int noItems) {
        Node first = new Node();
        Node last = first;
        for (int i = 0; i < noItems; i++) {
            Node n = new Node();
            last.next = n;
            last = n;
        }
        System.out.println(first);
        System.out.println(last);
    }

    public static int calcNumberOfNodes(Integer numberOfGBs) {
        return (int) ((Long.valueOf(1000000000) / Integer.valueOf(56)) * numberOfGBs);
    }

    public static int parseArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No total size of nodes specified. Using default size: 3 GB");
            int n = calcNumberOfNodes(3);
            System.out.println("Creating " + n + " nodes");
            return n;
        } else if (args.length == 2 && args[0].equals("-n")) {
            System.out.println("Size of nodes to be created: " + args[1] + " GB");
            int n = calcNumberOfNodes(Integer.parseInt(args[1]));
            System.out.println("Creating " + n + " nodes");
            return n;
        } else {
            throw new RuntimeException("Invalid arguments");
        }
    }

    public static void main(String[] args) {
        int n = parseArgs(args);
        printStats();
        run(n);

        Instant start = Instant.now();
        for(int i = 0; i < 5; i ++){
            run(n);
        }
        Instant end = Instant.now();
        System.err.println(Duration.between(start, end));
    }
}

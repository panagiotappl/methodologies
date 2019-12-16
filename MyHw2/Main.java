class Node {
    long i;
}

public class Main {
    public static void main(String [] args) {
        Node[] arr = new Node[125000000];
        for (int i = 0; i < 125000000; i++) {
            arr[i] = new Node();
        }
    }
}

import java.util.Arrays;
import java.util.stream.IntStream;


public class Main {
    private static final int min = 1;
    private static final int max = 100;

    public static void main(String[] args) throws Exception {
        BinaryTree bt = new BinaryTree();
        BinaryTree bt2 = new BinaryTree();
        IntStream.rangeClosed(min, max).parallel().forEach(x -> {bt.insert(x); bt2.insert(x);});
        IntStream.rangeClosed(min, max).parallel().forEach(x -> {
            if (!bt.lookUp(x)) {
                System.out.println(x + ": Not Found");
            }
        });
        bt.remove(1);
    }
}
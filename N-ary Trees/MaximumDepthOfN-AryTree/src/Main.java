import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        Node first = new Node(5);
        first.children = new ArrayList<>();

        List<Node> test = new ArrayList<>();
        test.add(first);

        Node left = new Node(1, test);

        List<Node> test2 = new ArrayList<>();
        test2.add(left);

        Node root = new Node(3, test2);
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(Node root) {
        if(root == null) return 0;

        int depth = 0;
        for (Node child : root.children){
            depth = Math.max(depth, maxDepth(child));
        }

        return depth + 1;
    }
}

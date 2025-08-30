import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

    }

    public List<Integer> postorder(Node root) {
        List<Integer> postorder = new ArrayList<>();
        if(root == null) return postorder;
        traverse(root, postorder);
        return postorder;
    }

    private void traverse(Node node, List<Integer> postorder){
        if(node == null) return;

        for (Node child : node.children){
            traverse(child, postorder);
        }

        postorder.add(node.val);
    }
}

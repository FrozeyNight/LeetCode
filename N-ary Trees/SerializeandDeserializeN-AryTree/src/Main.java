import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

    }

    public String serialize(Node root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null) return null;
        preorder.add(root.val);
        traverse(preorder, root);

        StringBuilder serializePreorder = new StringBuilder();

        for (Integer node : preorder){
            serializePreorder.append(node).append(" ");
        }

        return String.valueOf(serializePreorder);
    }

    private void traverse(List<Integer> preorder, Node node){
        if(node.children.isEmpty()) return;

        int amountOfChildren = node.children.size();

        for (int i = 0; i < amountOfChildren; i++) {
            preorder.add(node.children.get(i).val);
            traverse(preorder, node.children.get(i));
            if(i + 1 != amountOfChildren) preorder.add(100000);
            else preorder.add(null);
        }
    }

    public Node deserialize(String data) {

    }
}

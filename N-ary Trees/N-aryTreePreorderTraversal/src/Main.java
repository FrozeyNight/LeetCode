import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args){

    }

    public List<Integer> preorder(Node root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null) return preorder;
        preorder.add(root.val);
        traverse(preorder, root);
        return preorder;
    }

    private void traverse(List<Integer> preorder, Node node){
        if(node.children.isEmpty()) return;

        for(Node child : node.children){
            preorder.add(child.val);
            traverse(preorder, child);
        }
    }
}

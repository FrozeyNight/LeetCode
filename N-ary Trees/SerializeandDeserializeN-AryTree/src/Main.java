import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Integer[] tree = new Integer[]{1,null,2,3,4,5,null, null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14};
        Node root = new Node(tree[0], new ArrayList<>());
        testTreeBuilder(tree, root);
        System.out.println(serialize(root));
    }

    public static String serialize(Node root) {
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

    private static void traverse(List<Integer> preorder, Node node){
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
        return null;
    }

    private static void testTreeBuilder(Integer[] tree, Node root){
        Queue<Node> BFBuild = new ArrayDeque<>();
        BFBuild.add(root);
        int treeLength = tree.length;

        Node temp;
        int index = 1;
        while (!BFBuild.isEmpty()){
            temp = BFBuild.poll();
            index++;

            if(tree[index] == null) continue;

            int childIndex = 0;
            for (int i = index; tree[i] != null ; i++) {
                temp.children.add(new Node(tree[i], new ArrayList<>()));
                BFBuild.add(temp.children.get(childIndex));
                childIndex++;
                index++;
                if(index == treeLength) return;
            }
        }
    }
}

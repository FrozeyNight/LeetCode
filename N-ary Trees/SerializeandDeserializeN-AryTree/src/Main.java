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
        deserialize(serialize(root));
    }

    public static String serialize(Node root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null) return null;
        preorder.add(root.val);

        if(root.children.isEmpty()){
            preorder.add(null);
        }

        int amountOfChildren = root.children.size();

        for (int i = 0; i < amountOfChildren; i++) {
            preorder.add(root.children.get(i).val);
            traverse(preorder, root.children.get(i));
            preorder.add(null);
        }

        if(preorder.size() > 2) preorder = preorder.subList(0, preorder.size() - 2);

        StringBuilder serializePreorder = new StringBuilder();

        for (Integer node : preorder){
            serializePreorder.append(node).append(",");
        }

        int stringLen = serializePreorder.length();
        if(stringLen > 1) serializePreorder.deleteCharAt(stringLen - 1);

        return String.valueOf(serializePreorder);
    }

    private static void traverse(List<Integer> preorder, Node node){
        if(node.children.isEmpty()){
            preorder.add(null);
            return;
        }

        int amountOfChildren = node.children.size();

        for (int i = 0; i < amountOfChildren; i++) {
            preorder.add(node.children.get(i).val);
            traverse(preorder, node.children.get(i));
        }
    }

    public static Node deserialize(String data) {
        if(data == null) return null;

        String[] preorderInString = data.split(",");
        int arrLen = preorderInString.length;
        Integer[] preorder = new Integer[arrLen];
        for (int i = 0; i < arrLen; i++) {
            if(preorderInString[i].equals("null")) continue;
            preorder[i] = Integer.parseInt(preorderInString[i]);
        }

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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Integer[] tree = new Integer[]{1,null,2,3,4,5,null, null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14};
        Node root = new Node(tree[0], new ArrayList<>());
        testTreeBuilder(tree, root);
        Node check = deserialize(serialize(root));
        Node check2 = BFSDeserialize(BFSSerialize(root));
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
        }

        preorder.add(null);

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

        preorder.add(null);
    }

    public static int preorderIndex = 1;

    public static Node deserialize(String data) {
        if(data == null) return null;
        preorderIndex = 1;

        String[] preorderInString = data.split(",");
        int arrLen = preorderInString.length;
        Integer[] preorder = new Integer[arrLen];
        for (int i = 0; i < arrLen; i++) {
            if(preorderInString[i].equals("null")) continue;
            preorder[i] = Integer.parseInt(preorderInString[i]);
        }

        Node root = new Node(preorder[0], new ArrayList<>());
        buildTree(root, preorder);

        return root;
    }


    public static void buildTree(Node node, Integer[] preorder){
        if(preorder[preorderIndex] == null) {
            preorderIndex++;
            return;
        }
        Node temp;

        while (preorder[preorderIndex] != null){
            temp = new Node(preorder[preorderIndex], new ArrayList<>());
            node.children.add(temp);
            preorderIndex++;
            buildTree(temp, preorder);
        }
        preorderIndex++;
    }

    private static String BFSSerialize(Node root){
        if(root == null) return null;
        else if (root.children.isEmpty()) return String.valueOf(root.val);

        StringBuilder serializedTree = new StringBuilder();
        Queue<Node> BFS = new ArrayDeque<>();
        BFS.add(root);

        serializedTree.append(root.val);
        serializedTree.append(',');
        serializedTree.append("null");
        serializedTree.append(',');

        Node temp;
        int lastValue = 0;
        while (!BFS.isEmpty()){
            temp = BFS.poll();

            for (Node child : temp.children){
                BFS.add(child);
                serializedTree.append(child.val);
                lastValue = child.val;
                serializedTree.append(',');
            }

            serializedTree.append("null");
            serializedTree.append(',');
        }

        int lastValueLength = 0;
        int tmp = lastValue;
        while (tmp > 0){
            tmp = tmp / 10;
            lastValueLength++;
        }

        return serializedTree.substring(0, serializedTree.lastIndexOf(String.valueOf(lastValue)) + lastValueLength);
    }

    private static Node BFSDeserialize(String data){
        if(data.isEmpty()) return null;

        String[] leveloOderInString = data.split(",");
        int arrLen = leveloOderInString.length;
        Integer[] levelOrder = new Integer[arrLen];
        for (int i = 0; i < arrLen; i++) {
            if(leveloOderInString[i].equals("null")) continue;
            levelOrder[i] = Integer.parseInt(leveloOderInString[i]);
        }

        Node root = new Node(levelOrder[0], new ArrayList<>());

        testTreeBuilder(levelOrder, root);

        return root;
    }

    private static void testTreeBuilder(Integer[] tree, Node root){
        if(tree.length == 0) return;

        Queue<Node> BFBuild = new ArrayDeque<>();
        BFBuild.add(root);
        int treeLength = tree.length;

        Node temp;
        int index = 1;
        if(index == treeLength) return;
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

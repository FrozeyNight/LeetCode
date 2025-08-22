import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        String test = "2 1 3 I 1 2 3 I3";
        //TreeNode test2 = deserialize(test);
        serialize(root);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root == null) return null;
        StringBuilder serializedTreeBuilder = new StringBuilder();

        Queue<TreeNode> BFS = new ArrayDeque<>();
        Queue<TreeNode> BFSHelp = new ArrayDeque<>();
        BFS.add(root);

        TreeNode temp;
        int levelCounter = 0;
        while (!BFS.isEmpty()){
            temp = BFS.poll();

            serializedTreeBuilder.append(temp.val);
            serializedTreeBuilder.append(',');
            if(temp.left != null || temp.right != null){
                if(temp.left != null && temp.right != null){
                    serializedTreeBuilder.append('3');
                    BFSHelp.add(temp.left);
                    BFSHelp.add(temp.right);
                }
                else if(temp.left != null){
                    serializedTreeBuilder.append('1');
                    BFSHelp.add(temp.left);
                }
                else{
                    serializedTreeBuilder.append('2');
                    BFSHelp.add(temp.right);
                }
            }
            else serializedTreeBuilder.append('0');

            if(!BFS.isEmpty()) serializedTreeBuilder.append('|');
            else{
                serializedTreeBuilder.append('I');
                BFS.addAll(BFSHelp);
                BFSHelp.clear();
                levelCounter++;
            }

        }

        serializedTreeBuilder.append(levelCounter);
        System.out.println(serializedTreeBuilder);

        return String.valueOf(serializedTreeBuilder);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        return null;
    }



    /*

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> inorder = inorderTraversal(root);
        List<Integer> preorder = preorderTraversal(root);

        StringBuilder serializedTreeBuilder = new StringBuilder();

        for(Integer num : inorder){
            serializedTreeBuilder.append(num);
            serializedTreeBuilder.append(" ");
        }

        serializedTreeBuilder.append("I");
        serializedTreeBuilder.append(" ");

        for(Integer num : preorder){
            serializedTreeBuilder.append(num);
            serializedTreeBuilder.append(" ");
        }

        serializedTreeBuilder.append("I");
        serializedTreeBuilder.append(inorder.size());

        return String.valueOf(serializedTreeBuilder);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {

        String inorderString = data.split("I")[0].trim();
        String preorderString = data.split("I")[1].trim();
        int numberOfNodes = Integer.parseInt(data.split("I")[2]);


        int[] inorder = new int[numberOfNodes];
        int[] preorder = new int[numberOfNodes];

        String[] inorderInString = inorderString.split(" ");
        String[] preorderInString = preorderString.split(" ");

        for (int i = 0; i < numberOfNodes; i++) {
            inorder[i] = Integer.parseInt(inorderInString[i]);
            preorder[i] = Integer.parseInt(preorderInString[i]);
        }

        return buildTree(inorder, preorder);
    }

    private List<Integer> inorderTraversal(TreeNode root){
        List<Integer> inorder = new ArrayList<>();
        inorderTraverse(root, inorder);
        return inorder;
    }

    private void inorderTraverse(TreeNode root, List<Integer> inorder){
        if(root == null) return;
        inorderTraverse(root.left, inorder);
        inorder.add(root.val);
        inorderTraverse(root.right, inorder);
    }

    private List<Integer> preorderTraversal(TreeNode root){
        List<Integer> preorder = new ArrayList<>();
        preorderTraverse(root, preorder);
        return preorder;
    }

    private void preorderTraverse(TreeNode root, List<Integer> preorder){
        if(root == null) return;
        preorder.add(root.val);
        preorderTraverse(root.left, preorder);
        preorderTraverse(root.right, preorder);
    }

    private static int preorderIndex = 1;

    private static TreeNode buildTree(int[] inorder, int[] preorder){
        if(inorder.length == 0) return null;
        HashMap<Integer, Integer> mappedInorder = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            mappedInorder.put(inorder[i], i);
        }

        TreeNode root = new TreeNode(preorder[0]);
        if(preorder.length > 1) buildTreeHelper(root, mappedInorder, preorder, 0, inorder.length - 1);
        preorderIndex = 1;

        return root;
    }

    private static void buildTreeHelper(TreeNode node, HashMap<Integer, Integer> mappedInorder, int[] preorder, int start, int end){
        if(start == end || node == null) return;

        int leftEnd = mappedInorder.get(node.val) - 1;
        if(start <= leftEnd){
            node.left = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;
            buildTreeHelper(node.left, mappedInorder, preorder, start, leftEnd);
        }

        int rightStart = mappedInorder.get(node.val) + 1;
        if(rightStart <= end){
            node.right = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;
            buildTreeHelper(node.right, mappedInorder, preorder, rightStart, end);
        }
    }

     */
}
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //int[] inorder = {4,2,8,5,1,6,3,9,7,10};
        //int[] postorder = {4,8,5,2,6,9,10,7,3,1};

        //int[] inorder = {9,3,15,20,7};
        //int[] postorder = {9,15,7,20,3};

        int[] inorder = {2, 1};
        int[] postorder = {2, 1};

        System.out.println(buildTree(inorder, postorder));
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);

        HashMap<Integer, Integer> inorderArray = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderArray.put(inorder[i], i);
        }

        return createNodes(root, inorder, postorder, inorderArray.get(postorder[postorder.length - 1]), postorder.length - 1, postorder[postorder.length - 1], 0, new HashMap<>(), inorderArray);

    }

    private static TreeNode createNodes(TreeNode node, int[] inorder, int[] postorder, int inIndex, int postIndex, int parentValue, int amountOfRightTurns, HashMap<Integer, Boolean> parentNodes, HashMap<Integer, Integer> inorderArray){
        if(node == null) return null;
        else if(inorder.length == 1) return node;

        parentNodes.put(node.val, true);

        int leftChildPostorderIndex = inIndex - 1 - amountOfRightTurns;
        int rightChildPostorderIndex = postIndex - 1;

        if(inIndex - 1 >= 0 && inIndex <= inorder.length && inorder[inIndex - 1] != parentValue && parentNodes.get(inorder[inIndex - 1]) == null) node.left = new TreeNode(postorder[leftChildPostorderIndex]);
        if(inIndex >= -1 && inIndex + 1 < inorder.length && inorder[inIndex + 1] != parentValue && parentNodes.get(inorder[inIndex + 1]) == null) node.right = new TreeNode(postorder[rightChildPostorderIndex]);

        if(node.left != null) createNodes(node.left, inorder, postorder, inorderArray.get(postorder[leftChildPostorderIndex]), leftChildPostorderIndex, node.val, amountOfRightTurns, parentNodes, inorderArray);
        if(node.right != null) createNodes(node.right, inorder, postorder, inorderArray.get(postorder[rightChildPostorderIndex]), rightChildPostorderIndex, node.val, amountOfRightTurns + 1 , parentNodes, inorderArray);

        return node;
    }


}
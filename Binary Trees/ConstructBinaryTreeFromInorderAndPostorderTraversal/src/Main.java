import java.util.ArrayList;
import java.util.List;

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

        /*
        TODO: 1. Find a way to make the code more optimized. (I imagine the main thing slowing it down is the .contains command on the parentNodes list)

         */
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);


        int inorderRootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i] == root.val){
                inorderRootIndex = i;
                break;
            }
        }

        return createNodes(root, inorder, postorder, inorderRootIndex, postorder.length - 1, postorder[postorder.length - 1], 0, new ArrayList<>());

    }

    private static TreeNode createNodes(TreeNode node, int[] inorder, int[] postorder, int inIndex, int postIndex, int parentValue, int amountOfRightTurns, List<Integer> parentNodes){
        if(node == null) return null;
        else if(inorder.length == 1) return node;

        parentNodes.add(node.val);


        int leftChildPostorderIndex = inIndex - 1 - amountOfRightTurns;
        int rightChildPostorderIndex = postIndex - 1;

        if(inIndex - 1 >= 0 && inIndex <= inorder.length && inorder[inIndex - 1] != parentValue && !parentNodes.contains(inorder[inIndex - 1])) node.left = new TreeNode(postorder[leftChildPostorderIndex]);

        if(inIndex >= -1 && inIndex + 1 < inorder.length && inorder[inIndex + 1] != parentValue && !parentNodes.contains(inorder[inIndex + 1])) node.right = new TreeNode(postorder[rightChildPostorderIndex]);

        int leftChildInorderIndex = 0;
        int rightChildInorderIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            if(node.left != null && inorder[i] == postorder[leftChildPostorderIndex]) leftChildInorderIndex = i;
            else if (node.right != null && inorder[i] == postorder[rightChildPostorderIndex]) rightChildInorderIndex = i;
        }

        if(node.left != null) createNodes(node.left, inorder, postorder, leftChildInorderIndex, leftChildPostorderIndex, node.val, amountOfRightTurns, parentNodes);
        if(node.right != null) createNodes(node.right, inorder, postorder, rightChildInorderIndex, rightChildPostorderIndex, node.val, amountOfRightTurns + 1 , parentNodes);

        return node;
    }


}
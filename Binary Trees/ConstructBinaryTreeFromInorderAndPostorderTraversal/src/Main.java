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

        // Notes:
        // 1. The root is the last value of the postorder array
        // 2. The left child of a given node is the one before it in the inorder array (not for the initial root)
        // 3. The right child of a given node is the one before it in the postorder array (not for the initial root)
        // 4. (No sir, we have to handle a node just having the right child, or at least the root) There doesn't seem to be a way to handle a node which has only a right child
        // 5. If the 4th note is
        /*
        TODO: 1. Handle a case where a 1 node long binary tree is passed.
              2. If testing fails, fix the "what if a node only has a right child" problem.
              3. If that isn't it, fix the leafNodes list (what if root has a right child with a right child).

         */
        if(inorder.length > 1){
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);

            //int rightChildIndex = postorder.length - 2;

            int rootIndex = 0;
            int leftChildHelper = 0;
            for (int i = 0; i < inorder.length; i++) {
                if(inorder[i] == root.val){
                    if(i + 1 < inorder.length) leftChildHelper = inorder[i + 1];
                    else leftChildHelper = postorder[i - 1];
                    rootIndex = i;
                    break;
                }
            }

            int leftChildIndex = 0;
            for (int i = 0; i < postorder.length; i++) {
                if(postorder[i] == leftChildHelper){
                    if(i > 0) leftChildIndex = i - 1;
                    break;
                }
            }


            int inorderLeftChildIndex = 0;
            for (int i = 0; i < postorder.length; i++) {
                if(inorder[i] == postorder[leftChildIndex]){
                    inorderLeftChildIndex = i;
                    break;
                }
            }

            List<Integer> leafNodes = new ArrayList<>();

            if(rootIndex - 1 > 0){
                for (int i = 0; i < postorder.length; i++) {
                    if(postorder[i] == inorder[rootIndex - 1]) break;
                    leafNodes.add(postorder[i]);
                }
            }
            else if(rootIndex - 1 == 0) leafNodes.add(postorder[0]);
            else leafNodes.add(root.val);

            for (int i = rootIndex; i < postorder.length; i++) {
                if(postorder[i] == inorder[inorder.length - 2]) break;
                leafNodes.add(postorder[i]);
            }

            return createNodes(root, inorder, postorder, inorderLeftChildIndex + 1, postorder.length - 1, leafNodes);
        }

        /*
        int inorderRightChildIndex = 0;
        for (int i = 0; i < postorder.length; i++) {
            if(inorder[i] == postorder[rightChildIndex]){
                inorderRightChildIndex = i;
                break;
            }
        }

        int[] leftSideInorder = new int[rootIndex];
        int[] rightSideInorder = new int[inorder.length - rootIndex - 1];
        System.arraycopy(inorder, 0, leftSideInorder, 0, rootIndex);
        for (int i = rootIndex + 1; i < inorder.length; i++) {
            rightSideInorder[i - rootIndex - 1] = inorder[i];
        }

        int[] leftSidePostorder = new int[leftChildIndex];
        int[] rightSidePostorder = new int[postorder.length - leftChildIndex - 2];
        System.arraycopy(inorder, 0, leftSidePostorder, 0, leftChildIndex);
        for (int i = leftChildIndex + 1; i < postorder.length - 1; i++) {
            rightSidePostorder[i - leftChildIndex - 1] = postorder[i];
        }




         */

        //System.out.println(postorder[leftChildIndex]);
        //System.out.println(postorder[rightChildIndex]);

        return new TreeNode(inorder[0]);

        //return new TreeNode(postorder[postorder.length - 1], createNodes(new TreeNode(postorder[leftChildIndex]), leftSideInorder, leftSidePostorder, inorderLeftChildIndex, leftChildIndex), createNodes(new TreeNode(postorder[postorder.length - 2]), rightSideInorder, rightSidePostorder, inorderRightChildIndex - rootIndex - 1, rightSidePostorder.length - 1));
    }

    private static TreeNode createNodes(TreeNode node, int[] inorder, int[] postorder, int inIndex, int postIndex, List<Integer> leafNodes){
        if(node == null) return null;
        else if(inorder.length == 1) return node;

        System.out.println(node.val);

        node.left = new TreeNode(inorder[inIndex - 1]);
        System.out.println(node.left.val);
        if(inorder[inIndex - 1] != postorder[postIndex - 1]) node.right = new TreeNode(postorder[postIndex - 1]);
        if(node.right != null) System.out.println(node.right.val);

        /*
        int leftChildPostOrderIndex = 0;
        int rightChildPostOrderIndex = 0;

        for (int i = 0; i < postorder.length; i++) {
            if(postorder[i] == inorder[inIndex - 1]) leftChildPostOrderIndex = i;
            if(postorder[i] == postorder[postIndex - 1]) rightChildPostOrderIndex = i;
        }
         */

        int leftChildPostOrderIndex = 0;
        int rightChildInOrderIndex = 0;

        for (int i = 0; i < postorder.length; i++) {
            if(postorder[i] == inorder[inIndex - 1]) leftChildPostOrderIndex = i;
            if(inorder[i] == postorder[postIndex - 1]) rightChildInOrderIndex = i;
        }

        if(node.left != null && !leafNodes.contains(node.left.val)) createNodes(node.left, inorder, postorder, inIndex - 1, leftChildPostOrderIndex, leafNodes);
        //createNodes(node.right, inorder, postorder, inIndex + 1, rightChildPostOrderIndex - 1);
        if(node.right != null && !leafNodes.contains(node.right.val)) createNodes(node.right, inorder, postorder, rightChildInOrderIndex, postIndex - 1, leafNodes);

        return node;
    }


}
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Keep track of the index at preorder that we are at (global variable?)
        // beginning of the split array is the parent index and the end is the end of the current array
        // also the current method doesn't work

        TreeNode root = new TreeNode(preorder[0]);

        HashMap<Integer, Integer> mappedInorder = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            mappedInorder.put(inorder[i], i);
        }

        int[] newPreorder = new int[preorder.length - 1];
        System.arraycopy(preorder, 1, newPreorder, 0, preorder.length - 1);

        buildTreeHelper(root, newPreorder, inorder, mappedInorder);

        return root;
    }

    private void buildTreeHelper(TreeNode node, int[] preorder, int[] inorder, HashMap<Integer, Integer> mappedInorder){
        int parentInorderIndex = mappedInorder.get(node.val);

        if(parentInorderIndex < inorder.length){
            node.left.val = preorder[0];

            int leftChildInorderIndex = mappedInorder.get(node.left.val);

            int[] leftInorder = new int[leftChildInorderIndex];
            System.arraycopy(inorder, 0, leftInorder, 0, leftChildInorderIndex);

            int[] newPreorder = new int[preorder.length - 1];
            System.arraycopy(preorder, 1, newPreorder, 0, leftChildInorderIndex);

            buildTreeHelper(node.left, newPreorder, leftInorder, mappedInorder);

        }
    }
}
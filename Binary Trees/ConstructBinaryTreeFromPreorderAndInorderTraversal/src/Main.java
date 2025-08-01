import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    // TODO: 1. Handle cases where one of the subtrees is empty
    //       2. Handle incomplete binary trees
    private int preorderIndex = 1;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        TreeNode root = new TreeNode(preorder[0]);

        HashMap<Integer, Integer> mappedInorder = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            mappedInorder.put(inorder[i], i);
        }

        if(preorder.length > 1) buildTreeHelper(root, preorder, mappedInorder, 0, inorder.length - 1);

        preorderIndex = 0;

        return root;
    }

    private void buildTreeHelper(TreeNode node, int[] preorder, HashMap<Integer, Integer> mappedInorder, int start, int end){

        if(start <= end && end > 0){ // the second condition could be wrong
            node.left = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;

            buildTreeHelper(node.left, preorder, mappedInorder, start, mappedInorder.get(node.left.val) - 1);

            if(mappedInorder.get(preorder[preorderIndex]) <= end){
                node.right = new TreeNode(preorder[preorderIndex]);
                preorderIndex++;

                buildTreeHelper(node.right, preorder, mappedInorder, mappedInorder.get(node.right.val) + 1, end);
            }
        }

    }
}
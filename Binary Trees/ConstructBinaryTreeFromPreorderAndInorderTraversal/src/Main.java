import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //int[] preorder = new int[] {1, 2, 3};
        //int[] inorder = new int[] {1, 2, 3};

        int[] preorder = new int[] {3,9,20,15,7};
        int[] inorder = new int[] {9,3,15,20,7};

        buildTree(preorder, inorder);
    }

    private static int preorderIndex = 1;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        TreeNode root = new TreeNode(preorder[0]);

        HashMap<Integer, Integer> mappedInorder = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            mappedInorder.put(inorder[i], i);
        }

        if(preorder.length > 1) buildTreeHelper(root, preorder, mappedInorder, 0, inorder.length - 1);

        preorderIndex = 0;

        return root;
    }

    private static void buildTreeHelper(TreeNode node, int[] preorder, HashMap<Integer, Integer> mappedInorder, int start, int end){

        int leftEnd = mappedInorder.get(node.val) - 1;

        if(start <= leftEnd){
            node.left = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;

            buildTreeHelper(node.left, preorder, mappedInorder, start, leftEnd);
        }

        int rightStart = mappedInorder.get(node.val) + 1;

        if(rightStart <= end){
            node.right = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;

            buildTreeHelper(node.right, preorder, mappedInorder, rightStart, end);
        }

    }
}
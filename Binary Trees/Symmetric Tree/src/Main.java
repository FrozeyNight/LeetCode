//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        // Had to check how to do this, since my idea of inverting the right child of root and checking if it's equal to the left child did not work.
        return checkSymmetry(root.left, root.right);
    }

    private static boolean checkSymmetry(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null) return true;
        else if(node1 == null || node2 == null) return false;

        return node1.val == node2.val && checkSymmetry(node1.left, node2.right) && checkSymmetry(node1.right, node2.left);
    }
}
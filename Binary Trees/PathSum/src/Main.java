//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return checkPathSum(root, targetSum, 0);
    }

    private static boolean checkPathSum(TreeNode node, int targetSum, int sum){
        if(node == null) return false;

        sum += node.val;

        if(checkPathSum(node.left, targetSum, sum) || checkPathSum(node.right, targetSum, sum)) return true;

        return sum == targetSum && node.left == null && node.right == null;
    }

}
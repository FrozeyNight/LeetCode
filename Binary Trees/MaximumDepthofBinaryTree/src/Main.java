//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println(maxDepth(new TreeNode(1, null, new TreeNode(2))));
    }

    public static int maxDepth(TreeNode root) {
        if(root == null)  return 0;
        short left_depth = (short) maxDepth(root.left);
        short right_depth = (short) maxDepth(root.right);

        return Math.max(left_depth + 1, right_depth + 1);
    }

}
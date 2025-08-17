public class Main {
    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }

        return left != null ? left : right;
    }

    /*
    private int LCA = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findLCA(root, p.val, q.val);
        int answer = LCA;
        LCA = 0;
        return new TreeNode(answer);
    }

    private boolean findLCA(TreeNode node, int p, int q){
        if(node == null || LCA != 0) return false;

        boolean left = findLCA(node.left, p, q);
        boolean right = findLCA(node.right, p, q);

        if(left && right || node.val == p || node.val == q){
            if(left || right){
                LCA = node.val;
                return false;
            }
        }

        return node.val == p || node.val == q || left || right;
    }

     */
}
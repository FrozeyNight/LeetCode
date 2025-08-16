public class Main {
    public static void main(String[] args) {

    }

    // First method simply looks through a tree to find one of the two values. (Probably use DFS) <- (yup probably inorder traversal)
    // Once one of them is found, it looks if the other node is a far child of it, and if yes, then it returns itself as the answer.
    // If not, we mark the parent of that node as the LCA and check if the other target node is in the right subtree.
    // If yes, we return the marked node as the LCA
    // If not, we mark its parent as the LCA and repeat this process until we find the answer.

    //TODO: Make it work if the second target node is a far child of the other target node

    public int LCA = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          findLCA(root, p.val, q.val);
          int answer = LCA;
          LCA = 0;
          return new TreeNode(answer);
    }

    public boolean findLCA(TreeNode node, int p, int q){
        if(node == null) return false;
        if(findLCA(node.left, p, q) && findLCA(node.right, p, q)) {
            LCA = node.val;
            return false;
        }

        return node.val == p || node.val == q;
    }
}
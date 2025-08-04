public class Main {
    public static void main(String[] args) {

    }

    public Node connect(Node root) {
        if(root == null) return null;
        if(root.left != null) {
            connect(root.left);
            connect(root.right);

            root.left.next = root.right;

            subtreesConnect(root.left, root.right);
        }

        return root;
    }

    private void subtreesConnect(Node leftNode, Node rightNode){
        if(leftNode.right != null) {
            leftNode.right.next = rightNode.left;
            subtreesConnect(leftNode.right, rightNode.left);
        }
    }
}
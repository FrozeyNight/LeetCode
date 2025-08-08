public class Main {
    public static void main(String[] args) {

    }

    // private int reachedLevel = 0;

    public Node connect(Node root) {
        if(root == null) return null;

        if(root.left != null){
            if(root.right != null){
                root.left.next = root.right;
                subtreesConnect(root.left, root.right);
            }
            connect(root.left);
        }

        if(root.right != null) connect(root.right);

        return root;
    }

    private void subtreesConnect(Node leftNode, Node rightNode){
        if(leftNode.left != null && rightNode.right != null){
            leftNode.left.next = rightNode.right;
            subtreesConnect(leftNode.left, rightNode.right);
        }
        if(leftNode.left != null && rightNode.left != null){
            leftNode.left.next = rightNode.left;
            subtreesConnect(leftNode.left, rightNode.left);
        }
        if(leftNode.right != null && rightNode.right != null){
            leftNode.right.next = rightNode.right;
            subtreesConnect(leftNode.right, rightNode.right);
        }
        if(leftNode.right != null && rightNode.left != null) {
            leftNode.right.next = rightNode.left;
            subtreesConnect(leftNode.right, rightNode.left);
        }

    }

    /*
    private void subtreesConnect(Node leftNode, Node rightNode, int currentLevel){

        if(currentLevel > reachedLevel){
            if(leftNode.right != null && rightNode.left != null) {
            leftNode.right.next = rightNode.left;
            reachedLevel++;
            subtreesConnect(leftNode.right, rightNode.left, currentLevel + 1);
            }
            else if(leftNode.right != null && rightNode.right != null){
            leftNode.right.next = rightNode.right;
            reachedLevel++;
            subtreesConnect(leftNode.right, rightNode.right, currentLevel + 1);
            }
            else if(leftNode.left != null && rightNode.left != null){
            leftNode.left.next = rightNode.left;
            reachedLevel++;
            subtreesConnect(leftNode.left, rightNode.left, currentLevel + 1);
            }
            else if(leftNode.left != null && rightNode.right != null){
            leftNode.left.next = rightNode.right;
            reachedLevel++;
            subtreesConnect(leftNode.left, rightNode.right, currentLevel + 1);
            return;
            }
        }
        if(leftNode.right != null){
            if(rightNode.left != null) subtreesConnect(leftNode.right, rightNode.left, currentLevel + 1);
            if(rightNode.right != null) subtreesConnect(leftNode.right, rightNode.right, currentLevel + 1);
        }
        if(leftNode.left != null){
            if(rightNode.left != null) subtreesConnect(leftNode.left, rightNode.left, currentLevel + 1);
            if(rightNode.right != null) subtreesConnect(leftNode.left, rightNode.right, currentLevel + 1);
        }


    }
     */
}
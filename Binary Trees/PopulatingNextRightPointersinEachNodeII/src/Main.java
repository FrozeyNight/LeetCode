public class Main {
    public static void main(String[] args) {

    }

    public Node connect(Node root) {
        if(root == null) return null;

        Node current = root;
        Node next = new Node(0);
        Node previous = next;

        while(current != null){
            while(current != null){
                if(current.left != null) {
                    previous.next = current.left;
                    previous = previous.next;
                }
                if(current.right != null) {
                    previous.next = current.right;
                    previous = previous.next;
                }
                current = current.next;
            }
            current = next.next;
            previous = next;
            next.next = null;
        }

        return root;
    }

    /*
    public Node connect(Node root) {
        if(root == null) return null;

        Node current = root;
        Node next;
        Node previous = null;

        while(current != null){
            if(current.left != null) next = current.left;
            else if(current.right == null && current.next != null){
                do{
                    current = current.next;
                }
                while(current.next != null && current.left == null && current.right == null);
                if(current.left != null) next = current.left;
                else next = current.right;
            }
            else next = current.right;

            if(current.left != null || current.right != null){
                while(current != null){
                    if(previous != null){
                        if(current.left != null) previous.next = current.left;
                        else previous.next = current.right;
                    }
                    if(current.left != null) {
                        if(current.right != null) previous = current.right;
                        else previous = current.left;
                        current.left.next = current.right;
                    }
                    else if(current.right != null) previous = current.right;
                    current = current.next;
                }
            }
            previous = null;
            current = next;
        }

        return root;
    }

    // Both solutions are slow and inefficient

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

    private void subtreesConnect(Node leftNode, Node rightNode, int currentLevel){

        boolean inwards = false;
        boolean rightRight = false;
        boolean leftLeft = false;

        if(currentLevel > reachedLevel){
            if(leftNode.right != null && rightNode.left != null) {
            leftNode.right.next = rightNode.left;
            reachedLevel++;
            inwards = true;
            subtreesConnect(leftNode.right, rightNode.left, currentLevel + 1);
            }
            else if(leftNode.right != null && rightNode.right != null){
            leftNode.right.next = rightNode.right;
            reachedLevel++;
            rightRight = true;
            subtreesConnect(leftNode.right, rightNode.right, currentLevel + 1);
            }
            else if(leftNode.left != null && rightNode.left != null){
            leftNode.left.next = rightNode.left;
            reachedLevel++;
            leftLeft = true;
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
            if(rightNode.left != null && !inwards) subtreesConnect(leftNode.right, rightNode.left, currentLevel + 1);
            if(rightNode.right != null && !rightRight) subtreesConnect(leftNode.right, rightNode.right, currentLevel + 1);
        }
        if(leftNode.left != null){
            if(rightNode.left != null && !leftLeft) subtreesConnect(leftNode.left, rightNode.left, currentLevel + 1);
            if(rightNode.right != null) subtreesConnect(leftNode.left, rightNode.right, currentLevel + 1);
        }


    }
     */
}
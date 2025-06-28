import java.util.*;

public class Main {
    public static void main(String[] args) {
        //TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));

        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7)));
        TreeNode right = new TreeNode(3, null, new TreeNode(8, new TreeNode(9), null));

        TreeNode root = new TreeNode(1, left, right);

        System.out.println(postorderTraversalOfficialSolution(root));
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;

        while(!stack.isEmpty() || current != null){
            while(current != null){
                if(current.left != null) stack.push(current);
                postorder.add(current.val);
                current = current.right;
            }

            if(!stack.isEmpty()) current = stack.pop().left;
        }

        postorder = postorder.reversed();

        return postorder;
    }

    public List<Integer> postorderTraversalRecursively(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        traverse(root, postorder);
        return postorder;
    }

    private void traverse(TreeNode node, List<Integer> result){
        if(node == null) return;
        traverse(node.left, result);
        traverse(node.right, result);
        result.add(node.val);

    }

    public static List<Integer> postorderTraversalOfficialSolution(TreeNode root) {
        // List to store the result of postorder traversal
        List<Integer> result = new ArrayList<>();
        // Stack to facilitate the traversal of nodes
        Deque<TreeNode> traversalStack = new ArrayDeque<>();
        TreeNode currentNode = root;

        // Traverse the tree while there are nodes to process
        while (currentNode != null || !traversalStack.isEmpty()) {
            if (currentNode != null) {
                // Add current node's value to result list before going to its children
                result.add(currentNode.val);
                // Push current node onto the stack
                traversalStack.push(currentNode);
                // Move to the right child
                currentNode = currentNode.right;
            } else {
                // Pop the node from the stack and move to its left child
                currentNode = traversalStack.pop();
                currentNode = currentNode.left;
            }
        }
        // Reverse the result list to get the correct postorder sequence
        Collections.reverse(result);
        return result;
    }
}


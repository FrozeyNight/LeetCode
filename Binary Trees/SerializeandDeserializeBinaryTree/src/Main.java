import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> inorder = inorderTraversal(root);
        List<Integer> preorder = preorderTraversal(root);

        StringBuilder serializedTreeBuilder = new StringBuilder();

        for(Integer num : inorder){
            serializedTreeBuilder.append(num);
            serializedTreeBuilder.append(" ");
        }

        serializedTreeBuilder.append("|");
        serializedTreeBuilder.append(" ");

        for(Integer num : preorder){
            serializedTreeBuilder.append(num);
            serializedTreeBuilder.append(" ");
        }

        serializedTreeBuilder.deleteCharAt(serializedTreeBuilder.length() - 1);

        return String.valueOf(serializedTreeBuilder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }

    private List<Integer> inorderTraversal(TreeNode root){
        List<Integer> inorder = new ArrayList<>();
        inorderTraverse(root, inorder);
        return inorder;
    }

    private void inorderTraverse(TreeNode root, List<Integer> inorder){
        if(root == null) return;
        inorderTraverse(root.left, inorder);
        inorder.add(root.val);
        inorderTraverse(root.right, inorder);
    }

    private List<Integer> preorderTraversal(TreeNode root){
        List<Integer> preorder = new ArrayList<>();
        preorderTraverse(root, preorder);
        return preorder;
    }

    private void preorderTraverse(TreeNode root, List<Integer> preorder){
        if(root == null) return;
        preorder.add(root.val);
        inorderTraverse(root.left, preorder);
        inorderTraverse(root.right, preorder);
    }
}
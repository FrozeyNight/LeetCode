import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7)));
        //TreeNode right = new TreeNode(3, null, new TreeNode(8, new TreeNode(9), null));

        //TreeNode root = new TreeNode(1, left, right);

        // testing connection to GitHub

        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3, null, new TreeNode(5)));

        System.out.println(levelOrder(root));
        System.out.println(levelOrderCleanerVersionByChatGPT(root));
        System.out.println(levelOrderRecursivelyByChatGPT(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> orderLevel = new ArrayList<>();
        if(root == null) return orderLevel;

        List<Integer> holder = new ArrayList<>();
        holder.add(root.val);
        orderLevel.add(holder);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        TreeNode current;
        int queueSize = 0;

        while(!queue.isEmpty()){
            List<Integer> holder2 = new ArrayList<>();

            queueSize = queue.size();

            while(queueSize > 0){

                current = queue.poll();

                if(current.left != null){
                    queue.offer(current.left);
                    holder2.add(current.left.val);
                }

                if(current.right != null){
                    queue.offer(current.right);
                    holder2.add(current.right.val);
                }

                queueSize--;
            }

            if(!holder2.isEmpty()) orderLevel.add(holder2);
        }

        return orderLevel;
    }

    public static List<List<Integer>> levelOrderCleanerVersionByChatGPT(TreeNode root) {
        List<List<Integer>> orderLevel = new ArrayList<>();
        if (root == null) return orderLevel;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            orderLevel.add(level);
        }

        return orderLevel;
    }

    public static List<List<Integer>> levelOrderRecursivelyByChatGPT(TreeNode root){
        List<List<Integer>> orderLevel = new ArrayList<>();
        traverse(root, 0, orderLevel);
        return orderLevel;
    }

    private static void traverse(TreeNode node, int level, List<List<Integer>> result){
        if(node == null) return;

        if(result.size() == level){
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);

        traverse(node.left, level + 1, result);
        traverse(node.right, level + 1, result);
    }
}


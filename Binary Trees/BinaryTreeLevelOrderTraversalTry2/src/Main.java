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

        while(!queue.isEmpty()){
            List<Integer> holder2 = new ArrayList<>();

            Queue<TreeNode> queue2 = new ArrayDeque<>();

            while(!queue.isEmpty()){

                current = queue.poll();

                if(current.left != null){
                    queue2.offer(current.left);
                    holder2.add(current.left.val);
                }

                if(current.right != null){
                    queue2.offer(current.right);
                    holder2.add(current.right.val);
                }
            }

            queue.addAll(queue2);

            if(!holder2.isEmpty()) orderLevel.add(holder2);
        }

        return orderLevel;
    }
}


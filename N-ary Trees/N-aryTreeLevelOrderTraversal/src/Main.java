import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if(root == null) return levelOrder;

        Queue<Node> BFS = new ArrayDeque<>();
        BFS.add(root);
        List<Integer> level = new ArrayList<>();
        Node temp;
        int levelLength = 1;

        while (!BFS.isEmpty()){
            temp = BFS.poll();
            level.add(temp.val);
            levelLength--;

            BFS.addAll(temp.children);

            if(levelLength == 0){
                levelLength = BFS.size();
                levelOrder.add(level);
                level = new ArrayList<>();
            }
        }

        return levelOrder;
    }
}

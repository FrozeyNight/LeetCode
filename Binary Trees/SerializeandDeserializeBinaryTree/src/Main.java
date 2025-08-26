import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(serialize(root));
        deserialize(serialize(root));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root == null) return null;
        StringBuilder serializedTreeNodeValuesBuilder = new StringBuilder();
        StringBuilder serializedTreeChildrenAmountBuilder = new StringBuilder();
        serializedTreeNodeValuesBuilder.append('I');
        serializedTreeChildrenAmountBuilder.append('I');

        Queue<TreeNode> BFS = new ArrayDeque<>();
        int levelLength = 1;
        BFS.add(root);

        TreeNode temp;
        int levelCounter = 0;
        while (!BFS.isEmpty()){
            temp = BFS.poll();
            levelLength--;

            serializedTreeNodeValuesBuilder.append(temp.val);
            if(temp.left != null || temp.right != null){
                if(temp.left != null && temp.right != null){
                    serializedTreeChildrenAmountBuilder.append('3');
                    BFS.add(temp.left);
                    BFS.add(temp.right);
                }
                else if(temp.left != null){
                    serializedTreeChildrenAmountBuilder.append('1');
                    BFS.add(temp.left);
                }
                else{
                    serializedTreeChildrenAmountBuilder.append('2');
                    BFS.add(temp.right);
                }
            }
            else serializedTreeChildrenAmountBuilder.append('0');
            serializedTreeNodeValuesBuilder.append(',');
            if(levelLength == 0){
                serializedTreeNodeValuesBuilder.append('I');
                serializedTreeChildrenAmountBuilder.append('I');
                levelLength = BFS.size();
                levelCounter++;
            }
        }

        serializedTreeNodeValuesBuilder.deleteCharAt(serializedTreeNodeValuesBuilder.length() - 1);
        serializedTreeNodeValuesBuilder.insert(0, levelCounter);
        serializedTreeNodeValuesBuilder.append(" ");
        serializedTreeNodeValuesBuilder.append(serializedTreeChildrenAmountBuilder);

        return String.valueOf(serializedTreeNodeValuesBuilder);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data == null) return null;

        int levelStart = data.indexOf("I");
        int amountOfLevels = Integer.parseInt(data.substring(0, levelStart));
        int valueAndChildrenSeparator = data.indexOf(" ");

        int[][] nodeValues = new int[amountOfLevels][];
        int[][] childrenAmounts = new int[amountOfLevels][];

        nodeValues[0] = new int[1];
        childrenAmounts[0] = new int[1];

        int level = 0;
        int nodeIndexInLevel = 0;
        int numStart = levelStart + 1;
        int numEnd = 0;
        int amountOfNodesInLevel = 0;
        for (int i = levelStart + 1; i < valueAndChildrenSeparator; i++) {
            char currentChar = data.charAt(i);
            if(currentChar == ','){
                nodeValues[level][nodeIndexInLevel] = Integer.parseInt(data.substring(numStart, numEnd + 1));
                numStart = i + 1;
                nodeIndexInLevel++;
            }
            else if(currentChar == 'I'){
                level++;
                nodeIndexInLevel = 0;
                numStart = i + 1;
                for (int j = i + 1; j < valueAndChildrenSeparator; j++) {
                    char currentCharInnerLoop = data.charAt(j);
                    if(currentCharInnerLoop == ',') amountOfNodesInLevel++;
                    else if(currentCharInnerLoop == 'I') break;
                }
                nodeValues[level] = new int[amountOfNodesInLevel];
                amountOfNodesInLevel = 0;
            }
            else{
                numEnd = i;
            }
        }

        level = 0;
        nodeIndexInLevel = 0;
        amountOfNodesInLevel = 0;
        int lengthOfData = data.length();
        for (int i = valueAndChildrenSeparator + 2; i < data.length() - 1; i++) {
            char currentChar = data.charAt(i);
            if(currentChar == 'I'){
                level++;
                nodeIndexInLevel = 0;
                for (int j = i + 1; j < lengthOfData; j++) {
                    if(data.charAt(j) == 'I') break;
                    else amountOfNodesInLevel++;
                }
                childrenAmounts[level] = new int[amountOfNodesInLevel];
                amountOfNodesInLevel = 0;
            }
            else {
                childrenAmounts[level][nodeIndexInLevel] = Integer.parseInt(String.valueOf(currentChar));
                nodeIndexInLevel++;
            }
        }

        TreeNode root = new TreeNode(nodeValues[0][0]);
        Queue<TreeNode> BFS = new ArrayDeque<>();
        BFS.add(root);

        TreeNode temp;
        level = 0;
        nodeIndexInLevel = 0;
        int nodeIndexInNextLevel = 0;
        int amountOfChildren;
        if(amountOfLevels == 1) return root;
        while (true){
            temp = BFS.poll();

            if(nodeIndexInLevel == nodeValues[level].length){
                level++;
                nodeIndexInLevel = 0;
                nodeIndexInNextLevel = 0;
                if(level == amountOfLevels - 1) break;
            }
            amountOfChildren = childrenAmounts[level][nodeIndexInLevel];
            if(amountOfChildren == 1 || amountOfChildren == 3){
                temp.left = new TreeNode(nodeValues[level + 1][nodeIndexInNextLevel]);
                BFS.add(temp.left);
                nodeIndexInNextLevel++;
            }
            if(amountOfChildren == 2 || amountOfChildren == 3){
                temp.right = new TreeNode(nodeValues[level + 1][nodeIndexInNextLevel]);
                BFS.add(temp.right);
                nodeIndexInNextLevel++;
            }

            nodeIndexInLevel++;

        }

        return root;

    }



    /*
    --- 12ms solution with .split
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root == null) return null;
        StringBuilder serializedTreeNodeValuesBuilder = new StringBuilder();
        StringBuilder serializedTreeChildrenAmountBuilder = new StringBuilder();
        serializedTreeNodeValuesBuilder.append('I');
        serializedTreeChildrenAmountBuilder.append('I');

        Queue<TreeNode> BFS = new ArrayDeque<>();
        int levelLength = 1;
        BFS.add(root);

        TreeNode temp;
        int levelCounter = 0;
        while (!BFS.isEmpty()){
            temp = BFS.poll();
            levelLength--;

            serializedTreeNodeValuesBuilder.append(temp.val);
            if(temp.left != null || temp.right != null){
                if(temp.left != null && temp.right != null){
                    serializedTreeChildrenAmountBuilder.append('3');
                    BFS.add(temp.left);
                    BFS.add(temp.right);
                }
                else if(temp.left != null){
                    serializedTreeChildrenAmountBuilder.append('1');
                    BFS.add(temp.left);
                }
                else{
                    serializedTreeChildrenAmountBuilder.append('2');
                    BFS.add(temp.right);
                }
            }
            else serializedTreeChildrenAmountBuilder.append('0');
            if(levelLength == 0){
                serializedTreeNodeValuesBuilder.append('I');
                serializedTreeChildrenAmountBuilder.append('I');
                levelLength = BFS.size();
                levelCounter++;
            }
            else{
                serializedTreeNodeValuesBuilder.append(',');
                serializedTreeChildrenAmountBuilder.append(',');
            }

        }

        serializedTreeNodeValuesBuilder.deleteCharAt(serializedTreeNodeValuesBuilder.length() - 1);
        serializedTreeNodeValuesBuilder.insert(0, levelCounter);
        serializedTreeNodeValuesBuilder.append(" ");
        serializedTreeNodeValuesBuilder.append(serializedTreeChildrenAmountBuilder);

        return String.valueOf(serializedTreeNodeValuesBuilder);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data == null) return null;

        int amountOfLevels = Integer.parseInt(data.split("I")[0]);
        int valueAndChildrenSeparator = data.indexOf(" ");
        String allNodeValues = data.substring(data.indexOf('I') + 1, valueAndChildrenSeparator);
        String allNodeChildrenAmounts = data.substring(valueAndChildrenSeparator + 2);

        String[] nodeValuesLevels = allNodeValues.split("I");
        int[][] nodeValues = new int[amountOfLevels][];
        String[] nodeChildrenAmountLevels = allNodeChildrenAmounts.split("I");
        int[][] childrenAmounts = new int[amountOfLevels][];

        String[] holder;
        String[] holder2;
        for (int i = 0; i < nodeValuesLevels.length; i++) {
            holder = nodeValuesLevels[i].split(",");
            holder2 = nodeChildrenAmountLevels[i].split(",");
            nodeValues[i] = new int[holder.length];
            childrenAmounts[i] = new int[holder2.length];
            for (int j = 0; j < holder.length; j++) {
                nodeValues[i][j] = Integer.parseInt(holder[j]);
                childrenAmounts[i][j] = Integer.parseInt(holder2[j]);
            }
        }

        TreeNode root = new TreeNode(nodeValues[0][0]);
        Queue<TreeNode> BFS = new ArrayDeque<>();
        BFS.add(root);

        TreeNode temp;
        int level = 0;
        int nodeIndexInLevel = 0;
        int nodeIndexInNextLevel = 0;
        int amountOfChildren;
        if(amountOfLevels == 1) return root;
        while (true){
            temp = BFS.poll();

            if(nodeIndexInLevel == nodeValues[level].length){
                level++;
                nodeIndexInLevel = 0;
                nodeIndexInNextLevel = 0;
                if(level == amountOfLevels - 1) break;
            }
            amountOfChildren = childrenAmounts[level][nodeIndexInLevel];
            if(amountOfChildren == 1 || amountOfChildren == 3){
                temp.left = new TreeNode(nodeValues[level + 1][nodeIndexInNextLevel]);
                BFS.add(temp.left);
                nodeIndexInNextLevel++;
            }
            if(amountOfChildren == 2 || amountOfChildren == 3){
                temp.right = new TreeNode(nodeValues[level + 1][nodeIndexInNextLevel]);
                BFS.add(temp.right);
                nodeIndexInNextLevel++;
            }

            nodeIndexInLevel++;

        }

        return root;

    }

    --- Non-working previous solution

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> inorder = inorderTraversal(root);
        List<Integer> preorder = preorderTraversal(root);

        StringBuilder serializedTreeBuilder = new StringBuilder();

        for(Integer num : inorder){
            serializedTreeBuilder.append(num);
            serializedTreeBuilder.append(" ");
        }

        serializedTreeBuilder.append("I");
        serializedTreeBuilder.append(" ");

        for(Integer num : preorder){
            serializedTreeBuilder.append(num);
            serializedTreeBuilder.append(" ");
        }

        serializedTreeBuilder.append("I");
        serializedTreeBuilder.append(inorder.size());

        return String.valueOf(serializedTreeBuilder);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {

        String inorderString = data.split("I")[0].trim();
        String preorderString = data.split("I")[1].trim();
        int numberOfNodes = Integer.parseInt(data.split("I")[2]);


        int[] inorder = new int[numberOfNodes];
        int[] preorder = new int[numberOfNodes];

        String[] inorderInString = inorderString.split(" ");
        String[] preorderInString = preorderString.split(" ");

        for (int i = 0; i < numberOfNodes; i++) {
            inorder[i] = Integer.parseInt(inorderInString[i]);
            preorder[i] = Integer.parseInt(preorderInString[i]);
        }

        return buildTree(inorder, preorder);
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
        preorderTraverse(root.left, preorder);
        preorderTraverse(root.right, preorder);
    }

    private static int preorderIndex = 1;

    private static TreeNode buildTree(int[] inorder, int[] preorder){
        if(inorder.length == 0) return null;
        HashMap<Integer, Integer> mappedInorder = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            mappedInorder.put(inorder[i], i);
        }

        TreeNode root = new TreeNode(preorder[0]);
        if(preorder.length > 1) buildTreeHelper(root, mappedInorder, preorder, 0, inorder.length - 1);
        preorderIndex = 1;

        return root;
    }

    private static void buildTreeHelper(TreeNode node, HashMap<Integer, Integer> mappedInorder, int[] preorder, int start, int end){
        if(start == end || node == null) return;

        int leftEnd = mappedInorder.get(node.val) - 1;
        if(start <= leftEnd){
            node.left = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;
            buildTreeHelper(node.left, mappedInorder, preorder, start, leftEnd);
        }

        int rightStart = mappedInorder.get(node.val) + 1;
        if(rightStart <= end){
            node.right = new TreeNode(preorder[preorderIndex]);
            preorderIndex++;
            buildTreeHelper(node.right, mappedInorder, preorder, rightStart, end);
        }
    }

     */
}
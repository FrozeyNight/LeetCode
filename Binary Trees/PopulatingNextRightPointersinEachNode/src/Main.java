//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }

    /* Notes:
    1. I currently see only one way to solve this problem: Going through the entire tree root -> bottom left -> bottom right and mapping every single node's level and value in a 2D array or something similar.
    When we reach the final node, we check the level it is at (method parameter) and use it to find all the nodes in that level using our array. Then we get its index and check if it's the last one in the level.
    If yes, then it points to null, otherwise point to the next value in the array. Although this will probably work, I imagine it's very unoptimized.
     */

    public Node connect(Node root) {

    }
}
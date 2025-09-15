public class App {
    public static void main(String[] args) {

        TreeNode root = new TreeNode("A");
        root.left = new TreeNode("B");
        root.right = new TreeNode("C");

        root.left.left = new TreeNode("D");
        root.left.right = new TreeNode("E");
        root.left.left.right = new TreeNode("H");

        root.right.left = new TreeNode("F");
        root.right.right = new TreeNode("G");

        root.right.left.left = new TreeNode("I");
        root.right.left.right = new TreeNode("J");

        PreorderTraversal traversal = new PreorderTraversal();
        traversal.preorderTraversal(root);
        // The final output order is collected from the "Processing: " lines
        // Expected: A, B, D, H, E, C, F, I, J, G
    }
}

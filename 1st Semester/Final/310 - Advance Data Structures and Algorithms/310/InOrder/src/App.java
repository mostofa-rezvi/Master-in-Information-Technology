public class App {
    public static void main(String[] args) {
        // Re-creating the tree from the example trace:
        // A
        // / \
        // B C
        // / \ / \
        // D E F G
        // \ / \
        // H I J

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

        System.out.println("Executing Inorder Traversal using Stack:");
        InorderTraversal traversal = new InorderTraversal();
        traversal.inorderTraversal(root);

        System.out.println("\n\nExpected output from example:");
        System.out.println("D, H, B, E, A, I, F, J, C, G");
    }
}

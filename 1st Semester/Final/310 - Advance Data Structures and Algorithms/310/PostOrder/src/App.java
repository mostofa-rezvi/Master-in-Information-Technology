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

        System.out.println("Executing Postorder Traversal using Stack:");
        PostorderTraversal traversal = new PostorderTraversal();
        traversal.postorderTraversal(root);

        // Let's determine the correct postorder traversal for verification
        // Left: B's subtree -> D's subtree -> H (process H), process D -> E (process E)
        // -> process B
        // Right: C's subtree -> F's subtree -> I (process I), J (process J), process F
        // -> G (process G) -> process C
        // Root: A (process A)
        // Correct Order: H, D, E, B, I, J, F, G, C, A

        System.out.println("\n\nExpected Postorder Traversal:");
        System.out.println("H D E B I J F G C A");
    }
}

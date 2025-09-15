import java.util.Stack;

public class PostorderTraversal {

  public void postorderTraversal(TreeNode root) {

    if (root == null) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode lastVisitedNode = null;
    TreeNode current = root;

    // The main loop continues as long as there are nodes to process
    while (current != null || !stack.isEmpty()) {

      // 1. Go down the far-left path and push all nodes
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        // 2. Can't go left anymore. Peek at the top of the stack.
        TreeNode peekNode = stack.peek();

        // 3a. If the node has a right child that has not been visited yet
        if (peekNode.right != null && peekNode.right != lastVisitedNode) {
          // Move to the right child and repeat the process of going left
          current = peekNode.right;
        } else {
          // 3b. If there's no right child OR the right child was already visited,
          // it's time to process this node.

          // Process the node
          System.out.print(peekNode.data + " ");

          // Mark this node as the last one visited
          lastVisitedNode = stack.pop();
        }
      }
    }
  }

}

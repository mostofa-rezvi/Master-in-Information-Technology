import java.util.Stack;

public class PreorderTraversal {

  public void preorderTraversal(TreeNode root) {
    // (1) Set Stack and Ptr=Root
    // The user's algorithm uses a NULL sentinel. In Java, we can
    // achieve the same goal by checking if the stack is empty.
    if (root == null) {
      System.out.println("Tree is empty.");
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode ptr = root;

    System.out.println("Preorder Traversal Steps:");

    // (2) Repeat steps (3) to (5) until Ptr becomes null at the end
    while (ptr != null) {
      // (3) Process Ptr->Info.
      System.out.println("Processing: " + ptr.data);

      // (4) if Ptr->Right is not NULL then push it onto the stack
      if (ptr.right != null) {
        System.out
            .println("  -> Pushing " + ptr.right.data + " to stack. Stack: " + stackToString(stack, ptr.right.data));
        stack.push(ptr.right);
      }

      // (5) If Ptr->Left is not NULL then move to the left child
      if (ptr.left != null) {
        ptr = ptr.left;
      } else {
        // else, Pop from the stack.
        // If the stack is empty, we are done. Set Ptr to null to exit the loop.
        if (!stack.isEmpty()) {
          TreeNode poppedNode = stack.pop();
          System.out.println(
              "  -> Left is null. Popping " + poppedNode.data + " from stack. Stack: " + stackToString(stack, null));
          ptr = poppedNode;
        } else {
          // This is the final pop which empties the stack, equivalent to
          // popping the initial NULL sentinel. We exit the loop.
          System.out.println("  -> Left is null and stack is empty. Traversal complete.");
          ptr = null;
        }
      }
    }
  }

  // Helper function to visualize the stack content for the trace
  private String stackToString(Stack<TreeNode> stack, String toAdd) {
    StringBuilder sb = new StringBuilder("[");
    for (TreeNode node : stack) {
      sb.append(node.data).append(", ");
    }
    if (toAdd != null) {
      sb.append(toAdd);
    }
    sb.append("]");
    return sb.toString();
  }
}

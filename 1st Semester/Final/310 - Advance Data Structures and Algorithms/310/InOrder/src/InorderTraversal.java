import java.util.Stack;

public class InorderTraversal {
  public void inorderTraversal(TreeNode root) {
    // (1) Initialize Stack and Ptr
    // Note: Java's Stack handles its own size (Top). The check stack.isEmpty()
    // replaces the need for a Ptr != NULL check on the popped sentinel value.
    Stack<TreeNode> stack = new Stack<>();
    TreeNode ptr = root;

    // The main loop combines steps (2) through (7). The loop continues as long
    // as there are nodes to process, either in the current path (ptr != null)
    // or on the stack. This is the modern equivalent of the `goto` structure.
    while (ptr != null || !stack.isEmpty()) {

      // (2) Repeat while Ptr is not NULL: Go down the left-most path
      // and push nodes onto the stack.
      while (ptr != null) {
        // (2a) Set Stack[Top]=Ptr and Top=Top+1 (Push Ptr)
        stack.push(ptr);
        // (2b) Set Ptr = Ptr->Left
        ptr = ptr.left;
      }

      // This part of the code executes when ptr becomes NULL.
      // (3, 7) Set Ptr=Stack[Top] and Top=Top-1 (Pop from Stack)
      // This pops the most recently added node (the left-most one).
      ptr = stack.pop();

      // (5) Process Ptr->Info
      System.out.print(ptr.data + " ");

      // (6) If Ptr->Right is not NULL, set Ptr = Ptr->Right.
      // The main `while` loop will then automatically "go to step 2"
      // for this new Ptr (the right child). If Ptr->Right is NULL,
      // ptr becomes NULL, and in the next iteration, we will just pop
      // the next node from the stack.
      ptr = ptr.right;
    }
    // (8) Exit (The loop terminates automatically when the stack is empty and ptr
    // is null)
  }
}

import org.checkerframework.checker.nullness.qual.*;
import java.util.LinkedList;

/**
*
* @author Arica Chakraborty (https://github.com/uemarica)
*
*/


// Driver Program
public class TreeTraversal {
    public static void main(String[] args) {
        Node tree = new Node(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(7);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // Prints 5 3 2 4 7 6 8
        System.out.println("Pre order traversal:");
        tree.printPreOrder();
        System.out.println();
        // Prints 2 3 4 5 6 7 8
        System.out.println("In order traversal:");
        tree.printInOrder();
        System.out.println();
        // Prints 2 4 3 6 8 7 5
        System.out.println("Post order traversal:");
        tree.printPostOrder();
        System.out.println();
        // Prints 5 3 7 2 4 6 8
        System.out.println("Level order traversal:");
        tree.printLevelOrder();
        System.out.println();
    }
}

/**
* The Node class which initializes a Node of a tree
* Consists of all 3 traversal methods: printInOrder, printPostOrder & printPreOrder
* printInOrder: LEFT -> ROOT -> RIGHT
* printPreOrder: ROOT -> LEFT -> RIGHT
* printPostOrder: LEFT -> RIGHT -> ROOT
* printLevelOrder: Prints by level (starting at root), from left to right.
*/
class Node {
    @MonotonicNonNull Node left, right;
    int data;
    /* got an error(error: [initialization.fields.uninitialized] the constructor does not initialize fields: left, right).
     --resolved by adding the annotation @MonotonicNonNull,didn't use the other annotations because:
     1.@MonotonicNonNull is appropriate as the fields(left and right) starts out as null and are alter set to non-null values.
     2.@EnsuresNonNull can't be used as there is no specific method to set the fields,as it's working as a container to hold data.
     3.@RequiresNonNull can't be used as we don't have any specific method that requires the fields to be null.
      How i made upto the solution:
      1.First i randomly compiled the code and i got the error saying: "error: [initialization.fields.uninitialized] the constructor does not initialize fields: left, right"
      by the time i have already tried out the nullness checker on my other codes but was not sure about the initialization checker.
      2. Then i started with the initialization checker section and i got everything i want from the initializing-warning sections where it was clearly written not to initialize the fields if they are not meant as it makes the code weak.
      3.As the annotation @MonotonicNonNull perfectly matched my requirement(starting out as null and then taking a non-null value and continuing further) hence, i modified the code with the annotation @MonotonicNonNull
     */
    
    public Node(int data) {
        this.data = data;
    }

    public void insert (int value) {
        if (value < data) {
            if (left == null) {
                left = new Node(value);
            }
            else {
                left.insert(value);
            }
        }
        else {
            if (right == null) {
                right = new Node(value);
            }
            else {
                right.insert(value);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.print(data + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    public void printPreOrder() {
        System.out.print(data + " ");
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printPostOrder() {
        if (left != null) {
            left.printPostOrder();
        }
        if (right != null) {
            right.printPostOrder();
        }
        System.out.print(data + " ");
    }

    /**
    * O(n) time algorithm.
    * Uses O(n) space to store nodes in a queue to aid in traversal.
    */
    public void printLevelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(this);
        while (queue.size() > 0) {
            Node head = queue.remove();
            System.out.print(head.data + " ");
            // Add children of recently-printed node to queue, if they exist.
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
        }
    }
}

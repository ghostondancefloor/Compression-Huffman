/**
 * This class represents the encoding tree used in Huffman coding.
 */
public class EncodingTree {
    private Node root;

    /**
     * Constructs an encoding tree with the specified root node.
     * 
     * @param root The root node of the encoding tree.
     */
    public EncodingTree(Node root) {
        this.root = root;
    }

    /**
     * Retrieves the root node of the encoding tree.
     * 
     * @return The root node of the encoding tree.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Gets the maximum depth of the encoding tree.
     *
     * @return The maximum depth of the encoding tree.
     */
    public int getDepth() {
        return getDepth(this.root);
    }

    /**
     * Recursively calculates the depth of a node in the tree.
     *
     * @param node The current node being examined.
     * @return The depth of the node.
     */
    private int getDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = getDepth(node.getLeft());
            int rightDepth = getDepth(node.getRight());
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    

    /**
     * Prints the structure of the encoding tree in a hierarchical manner.
     * 
     * @param node  The current node being visited.
     * @param level The current level in the tree hierarchy.
     */
    public void printTree(Node node, int level) {
        if (node == null)
            return;

        for (int i = 0; i < level; i++)
            System.out.print("   ");
        if (node.getCharacter() != '\0')
            System.out.println(node.getCharacter() + ":" + node.getFrequency());
        else
            System.out.println("Value:" + node.getFrequency());

        printTree(node.getLeft(), level + 1);
        printTree(node.getRight(), level + 1);
    }
}

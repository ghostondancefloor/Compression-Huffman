/**
 * This class represents a node in the Huffman encoding tree.
 */
public class Node {
    private char character;
    private int frequency;
    private int value;
    private Node left;
    private Node right;

    /**
     * Constructs a node with the specified character, frequency, left child, and right child.
     * 
     * @param character The character stored in the node.
     * @param frequency The frequency of the character.
     * @param left      The left child node.
     * @param right     The right child node.
     */
    public Node(char character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.value = 0;
    }

    /**
     * Constructs a leaf node with the specified character and frequency.
     * 
     * @param character The character stored in the node.
     * @param frequency The frequency of the character.
     */
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.value = 0;
    }

    /**
     * Constructs an internal node with the specified frequency, left child, and right child.
     * 
     * @param frequency The combined frequency of the left and right child nodes.
     * @param left      The left child node.
     * @param right     The right child node.
     */
    public Node(int frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.value = 0;
    }

    /**
     * Returns the frequency of the character.
     * 
     * @return The frequency of the character.
     */
    public int getFrequency() {
        return this.frequency;
    }

    /**
     * Returns the character stored in the node.
     * 
     * @return The character stored in the node.
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Returns the left child node.
     * 
     * @return The left child node.
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Returns the right child node.
     * 
     * @return The right child node.
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * Returns the value assigned to the node.
     * 
     * @return The value assigned to the node.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the value assigned to the node.
     * 
     * @param value The value to be assigned to the node.
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    /**
     * Traverses the Huffman encoding tree in depth-first order to find the value assigned to the specified character.
     * 
     * @param target  The character to find the value for.
     * @param current The current node being visited.
     * @return The value assigned to the character if found, otherwise -1.
     */
    public int indepthCourse(char target, Node current) {
        if (current == null) {
            return -1; 
        }
        if (current.getCharacter() == target) {
            return current.getValue();
        }
        
        int leftValue = indepthCourse(target, current.getLeft());
        if (leftValue != -1) {
            return leftValue;
        }
        
        int rightValue = indepthCourse(target, current.getRight());
        if (rightValue != -1) {
            return rightValue;
        }
        
        return -1;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}

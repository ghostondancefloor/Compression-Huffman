import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements Huffman encoding and compression algorithm.
 */
public class Huffman {
    private String path;
    private HashMap<Character, Integer> frequencies;

    /**
     * Constructs a Huffman object with the specified file path.
     * 
     * @param path The path to the input file.
     */
    public Huffman(String path){
        this.path = path;
        this.frequencies = new HashMap<Character, Integer>();   
    }

    /**
     * Retrieves the character frequencies.
     * 
     * @return A HashMap containing character frequencies.
     */
    public HashMap<Character, Integer> getFrequencies(){
        return this.frequencies;
    }

    /**
     * Reads the input file and calculates character frequencies.
     */
    public void read_file(){
        try {
            File myObj = new File(this.path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                if (!this.frequencies.containsKey(c))
                    this.frequencies.put(c,1);
                else
                    this.frequencies.put(c,this.frequencies.get(c)+1);
                }
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred, can't open the file ");
            e.printStackTrace();
          }
    }
    
    /**
     * Writes the character frequencies to a file.
     * 
     * @param path The path to the output file.
     */
    public void writeFrequencies(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (HashMap.Entry<Character, Integer> entry : this.frequencies.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            System.out.println("Data written to file: " + path);
        } catch (IOException e) {
            System.err.println("An error occurred while writing data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Constructs the Huffman encoding tree.
     * 
     * @return The root node of the Huffman encoding tree.
     */
    public EncodingTree treeConstruction(){
        ArrayList<Node> nodes = new ArrayList<>();

        for(char c : this.frequencies.keySet()){
            nodes.add(new Node(c,frequencies.get(c)));
        }

        while(nodes.size()>1){
            Node t1 = nodes.get(0);
            Node t2 = nodes.get(1);

            for (int i = 2; i < nodes.size(); i++) {
                Node current = nodes.get(i);
                if(current.getFrequency() < t1.getFrequency()){
                    t2= t1;
                    t1=current;
                }
                else if (current.getFrequency() < t2.getFrequency()) {
                    t2 = current;
                }
                
            }
            t1.setValue(0);
            t2.setValue(1);

            Node t = new Node(t1.getFrequency()+t2.getFrequency(),t1,t2);
            nodes.add(t);
            nodes.remove(t1);
            nodes.remove(t2);
        }
        Node root = nodes.get(0);
        return (new EncodingTree(root));
    }

    /**
     * Compresses the input file using the Huffman encoding tree.
     * 
     * @param tree The Huffman encoding tree.
     * @param name The name of the compressed file.
     */
    public void compression(EncodingTree tree, String name) {
        try {
            File read = new File(this.path);
            Scanner myReader = new Scanner(read);
            try (FileOutputStream fos = new FileOutputStream(name)) {
                BitOutputStream bos = new BitOutputStream(fos);
    
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    for (int i = 0; i < data.length(); i++) {
                        char c = data.charAt(i);
                        int value = tree.getRoot().indepthCourse(c, tree.getRoot());
                        String binary = Integer.toBinaryString(value);
                        String paddedBinary = String.format("%8s", binary).replace(' ', '0');
                        for (char bit : paddedBinary.toCharArray()) {
                            bos.write(bit - '0');
                        }
                    }
                }
                bos.close();
                System.out.println("Data written to file: " + name);
            }
            myReader.close();
        } catch (IOException e) {
            System.err.println("An error occurred while compressing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Calculates the compression ratio of the compressed file.
     * 
     * @param pathOriginal The path to the original file.
     * @param pathResult   The path to the compressed file.
     * @return The compression ratio.
     */
    public double getCompressionRatio(String pathOriginal, String pathResult){
        File original = new File(pathOriginal);
        File result = new File(pathResult);
        double volume1 = original.length()*8;
        double volume2 = result.length()*8;

        double compressionRatio = 1 - (volume1/volume2);
        return compressionRatio;
    }

    
}

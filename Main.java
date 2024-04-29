

public class Main {
    public static void main(String[] args) {
        String[] fileNames = {"data/alice.txt", "data/extraitalice.txt", "data/textesimple.txt"};

        for (String fileName : fileNames) {
            Huffman huffman = new Huffman(fileName);
            huffman.read_file();
            EncodingTree tree = huffman.treeConstruction();
            huffman.compression(tree, fileName.replace(".txt", "_comp.bin"));
            huffman.writeFrequencies(fileName.replace(".txt", "_freq.txt"));
        }

        
    }

    
}

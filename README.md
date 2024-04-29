## Huffman Encoding and Compression

This project implements Huffman encoding and compression algorithm. It provides functionality to read text files, construct Huffman encoding trees, compress text data, and write compressed files along with frequency information.

### Files

1. **Huffman.java**: Implements Huffman encoding and compression algorithm.

2. **BitOutputStream.java**: Provides a stream where bits can be written to. It pads the end of the stream with 0's up to a multiple of 8 bits.

3. **Node.java**: Represents a node in the Huffman encoding tree. It contains methods for constructing nodes and traversing the tree to find character values.

4. **EncodingTree.java**: Represents the encoding tree used in Huffman coding. It includes methods for retrieving the root node and calculating the depth of the tree.

### Usage

The `Main` class provides an example of how to use the Huffman encoding and compression functionality. It reads text files, constructs Huffman encoding trees, compresses the data, and writes compressed files along with frequency information.

### Instructions

1. Place your text files in the "data" directory.
2. Update the `fileNames` array in the `main` method of the `Main` class with the names of your text files.
3. Run the `Main` class. Compressed files and frequency information files will be generated for each input text file in the "data" directory.

### Example

We have the following text files:

- alice.txt
- extraitalice.txt
- textesimple.txt

After running the `Main` class, compressed files (`*_comp.bin`) and frequency information files (`*_freq.txt`) will be generated for each input text file.

### Note

This project includes a third-party library for the `BitOutputStream` class. Refer to the license information provided in the source code comments for details regarding the usage and distribution of this library.

Also, I used chatgpt to debug some snippets of my code.


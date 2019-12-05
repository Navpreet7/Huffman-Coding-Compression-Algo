# Huffman-Coding-Compression-Algo

The algorithm is based on the frequency of occurrence of the data item(byte). The most frequent data items will represented and encoded with a lower number of bits.
The main idea of the algorithm is create a binary tree, called Huffman tree, based on the bytes frequency on the data, where the leafs are the bytes symbols, and the path from the root to a leaf determines the new representation of that leaf byte.

# Building the tree

Each node of the tree are represented with a byte symbol and the frequency of that byte on the data. The creation of the Huffman tree have the following steps:
->Scan the data and calculate the frequency of occurrence of each byte;
->Insert those nodes into a reverse priority queue based on the frequencies(a lowest frequency is given highest priority);
->Start a loop until the queue is empty;
->Remove two nodes from the queue and combine them into a internal node with the frequency equal to the sum of the two nodes frequencies;
->Insert the two nodes removed from the queue as children of the created internal node;
->Insert the created internal node into the queue;
->The last node remaining on the queue is the root of the tree.

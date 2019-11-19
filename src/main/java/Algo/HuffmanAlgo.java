package main.java.Algo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanAlgo {
	static PriorityQueue<Node> queue;
	
	public static void buildHuffmanTree(String s) {
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		// calculate the occurrence of a character in given string
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			} else {
				map.put(s.charAt(i), 1);
			}
		}
		// add all characters to a priority queue
		queue = new PriorityQueue<Node>((l, r) -> l.freq - r.freq);
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			queue.add(new Node(entry.getKey(), entry.getValue()));
		}

		// make pairs of nodes and give parent to them with frequency equal to sum of
		// child nodes frequencies
		while (queue.size() != 1) {
			Node left = queue.poll();
			Node right = queue.poll();
			int sum = left.freq + right.freq;
			Node node = new Node('\0', sum, left, right);
			queue.add(node);

		}

	}
	/**
	 * Method to encode the huffman tree 
	 * @param root
	 * @param code
	 * @param huffmanCode
	 */
	public static void encode(Node root,String code,Map<Character,String> huffmanCode) {
		if(root!=null) {
			if(root.left==null && root.right==null) {
				huffmanCode.put(root.ch, code);
			}
			encode(root.left,code+"0",huffmanCode);
			encode(root.right,code+"1",huffmanCode);
		}
	}
	/**
	 * method to decode the encoded string
	 * @param root
	 * @param index
	 * @param sb
	 */
	public static void decode(Node root,int index,StringBuilder sb) {
		if(root!=null) {
			if(root.left==null && root.right==null) {
				System.out.print(root.ch);
				decode(queue.peek(),index,sb);
			}
			index++;
			if(index<sb.length()) {
				if(sb.charAt(index)=='0') {
					decode(root.left,index,sb);
				}else {
					decode(root.right,index,sb);
				}	
			}
			
		}
		
	}
	

	public static void main(String args[]) {
		
		String s ="huffman is a data compression algo.";
		
		HuffmanAlgo.buildHuffmanTree(s);
		Map<Character,String> huffmanCode=new HashMap<Character, String>();
		encode(queue.peek(),"",huffmanCode);
		
		// encoded string 
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			sb.append(huffmanCode.get(s.charAt(i)));
		}
		//decode the string
		decode(queue.peek(),-1,sb);

	}

}

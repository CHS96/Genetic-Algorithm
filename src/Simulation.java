import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

class Simulation {

	final int bitSize = 32;
	final int numberSize = 16,choiceSize = 8, bestChoice = 2;
	
	Node[] node;

	public Node[] inital() {
		node = new Node[numberSize];
		node[0] = new Node();
		node[0].bit = "00000000111111110000000011111111";
//		node[0].bit = "11111111000000001111111100000000";

		node[0].h = getMaxHeight(node[0].bit);
		
		for (int i = 1; i < numberSize; ++i) {
			node[i] = new Node();
			node[i].bit = node[0].bit;		
			node[i].h = getMaxHeight(node[i].bit);
		}
		
		return node;
	}


	public Node[] getNumber(Node[] a) {
		Node[] node = new Node[numberSize];
		Node temp;
		for (int i = 0; i < numberSize; ++i) {
			node[i] = new Node();
			if (i < bestChoice) {
				node[i].bit = a[i].bit;
				node[i].h = a[i].h;
			} else {
				if (i % 2 == 0) temp = makeMutation(a[0]);	
				else temp = makeMutation(a[1]);
				
				node[i].bit = temp.bit;
				node[i].h = temp.h;
			} 
		}

		return node;
	}

	public Node makeMutation(Node node) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < choiceSize; ++i) {
			int ran = (int)(Math.random() * 2);
			if (ran == 1) {
				list.add((int)(Math.random() * bitSize));
			}
		}

		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		Stack<Integer> st = new Stack<Integer>();
		for (int i : list) st.push(i);

		StringBuilder sb = new StringBuilder();
		String str = node.bit;

		if (!st.isEmpty()) {
			int t = st.pop();
			for (int i = 0; i < str.length(); ++i) {
				if (i == t) {
					sb.append(str.charAt(i) == '0' ? '1' : '0');
					if (!st.isEmpty()) {
						while (st.peek() == t) {
							t = st.pop();
							if (st.isEmpty()) {
								break; 
							}
						}
					}
				} else sb.append(str.charAt(i));
			}
		} else sb.append(str);
		
		node.bit = sb.toString();
		node.h = getMaxHeight(node.bit);
		
		return node;
	}

	public Node[] bestChoice(Node[] node) {
		Node[] bc = new Node[2];

		Arrays.sort(node);
		for (int i = 0; i < bestChoice; ++i) {
			bc[i] = new Node();
			bc[i].bit = node[i].bit;
			bc[i].h = node[i].h;
		}

		return bc;
	}

	public int getMaxHeight(String bit) {
		String[] moveBit = new String[4];
		for (int i = 0; i < 4; ++i) {
			if (i == 3) moveBit[i] = bit.substring(8 * i, bit.length());
			else moveBit[i] = bit.substring(8 * i, 8 * (i + 1));
		}
		
		int cnt = 0;
		for (int i = 0; i < 4; ++i) {
			if (i % 2 == 0) {
				for (int j = 0; j < moveBit[i].length(); ++j) {
					cnt += moveBit[i].charAt(j) == '1' ? 2 : -1;
				}
			} else {
				for (int j = 0; j < moveBit[i].length(); ++j) {
					cnt += moveBit[i].charAt(j) != '1' ? 2 : -1;
				}
			}
		}
		
		return (int)((64 - (cnt * 2)) * 2.5);
	}
	
	public void print(Node[] Node) {
		System.out.println("========================================================");
		System.out.println(" 	     Binary Bit		        MaxHeight");
		for (int i = 0; i < Node.length; ++i) {
			System.out.format("   %32s	  %d\n", Node[i].bit, Node[i].h);
		}
	}
}

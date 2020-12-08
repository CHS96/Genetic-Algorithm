
class Node implements Comparable<Node> {

	int h = 0;
	String bit = "";
	
	@Override
	public int compareTo(Node o) {
		return this.h - o.h;
	}
}

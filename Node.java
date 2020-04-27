import java.util.LinkedList;

public class Node {
	private Board board;
	private int distance;
	private float f_grade;
	private LinkedList<Node> neighbors;
	public Node(int _distance)
	{
		board = new Board();
		distance=_distance;
		f_grade = calcGrade();
		neighbors = new LinkedList<Node>();
	}
	public void add_n(Node neighbor)
	{
		neighbors.add(neighbor);
	}
	public void add_ns(LinkedList<Node> _neighbors)
	{
		neighbors.addAll(_neighbors);
	}
	public LinkedList<Node> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(LinkedList<Node> neighbors) {
		this.neighbors = neighbors;
	}
	public float calcGrade()
	{
		return board.h()+distance;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public float getF_grade() {
		return f_grade;
	}
	public void setF_grade(float f_grade) {
		this.f_grade = f_grade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		return true;
	}
	boolean is_traget()
	{
		return board.isTargetBoard();
	}

}

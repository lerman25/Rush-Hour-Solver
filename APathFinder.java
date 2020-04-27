import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class APathFinder {

	public boolean aStarSearch() {
		Node startNode = new Node(0);
		Board board = new Board();
		board.readBoard_scan();
		startNode.setBoard(board);
		// change to the goal , we have to define how it is defined
		HashMap<Node, Node> parentMap = new HashMap<Node, Node>();
		HashSet<Node> visited = new HashSet<Node>();
		double inf = Double.POSITIVE_INFINITY;
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				if (o1.getF_grade() == o2.getF_grade())
					return 0;
				if (o1.getF_grade() > o2.getF_grade())
					return 1;
				if (o1.getF_grade() < o2.getF_grade())
					return -1;
			}
		});
		// ** enque StartNode, with distance 0**
		startNode.setDistance(0);
		pQueue.add(startNode);
		Node current = null;
		while (!pQueue.isEmpty()) {
			current = pQueue.poll();

			if (!(visited.contains(current))) {
				visited.add(current);

				if (current.is_traget()) { 
					return true; // trace back the moves.
				}
				ArrayList<Node> neighbors  = createNeighbors(current);
				for (int i = 0;i<neighbors.size();i++) {
					Node neighbor=neighbors.get(i);
					if (!visited.contains(neighbor)) {
                        
						pQueue.add(neighbor);
						
						}

				}
			}
		}

		return false;
	}
private ArrayList<Node> createNeighbors(Node node)
{
	ArrayList<Node> neighbors = new ArrayList<Node>();
	Board current =node.getBoard();
	ArrayList<Car> cars = current.getCars();
	for(int c=0;c<cars.size();c++)
	{
		Car car = cars.get(c);
		for(int d=0;d<current.size;d++)
		{
			boolean dir = false;
			Car move = current.moveCar(car, d, dir);
			if(move!=null)
			{
				Node newNode = new Node(node.getDistance()+1);
				Board newBoard = current;
				newBoard.removeCar(car);
				newBoard.addCar(move);
				newNode.setBoard(newBoard);
				neighbors.add(newNode);
			}
			else
			{
				d=current.size;
			}
		}
		for(int d=0;d<current.size;d++)
		{
			boolean dir = true;
			Car move = current.moveCar(car, d, dir);
			if(move!=null)
			{
				Node newNode = new Node(node.getDistance()+1);
				Board newBoard = current;
				newBoard.removeCar(car);
				newBoard.addCar(move);
				newNode.setBoard(newBoard);
				neighbors.add(newNode);

			}
			else
			{
				d=current.size;
			}
		}
	}
	return neighbors;
	
}
}

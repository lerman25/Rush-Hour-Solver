
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class Astar {
	HashSet<Node> closed;
	PriorityQueue<Node> open;
	public Astar(Node initial)
	{
		this.closed = new HashSet<Node>();
		//creating an priority queue with new compare function for Nodes
		open = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				if (o1.getF_grade() == o2.getF_grade()) {
					if (o1.getBoard().h() < o2.getBoard().h())
						return -1;
					if (o1.getBoard().h() > o2.getBoard().h())
						return 1;
					return 0;
				}
				if (o1.getF_grade() > o2.getF_grade())
					return 1;
				if (o1.getF_grade() < o2.getF_grade())
					return -1;
				System.out.println("Error With Node's Compare");
				return 1;
			}
		});
		initial.setDistance(0);
		open.add(initial);
	}
	public Node aStarSearch()
	{
		//basic a* search
		while (!open.isEmpty()) {
			Node current = open.poll();
			if (current.is_traget()) {
				return current; // trace back the moves.
			} else {
				closed.add(current);
				expand(current);
			}
		}
		return null;
		
	}
	//creates a list of all possible option , addes to Open if is a new node or better existing node
	public void expand(Node node)
	{
		ArrayList<Node> neighboors = successor(node);
		for(int i=0;i<neighboors.size();i++)
		{
			Node current = neighboors.get(i);
			current.calcGrade();
			boolean isInOpen = open.contains(current);
			boolean isInClosed = closed.contains(current);
			if(!isInClosed&&!isInOpen)
			{
				open.add(current);
			}
			else
			{
				if(isInOpen)
				{
					Predicate<Node> condition  = t->((t.getF_grade()>current.getF_grade())&&(t.equals(current)));
					open.removeIf(condition);
					if(!open.contains(current))
						open.add(current);
				}
				else
				{
					if(isInClosed)
					{
						Predicate<Node> condition  = t->((t.getF_grade()>current.getF_grade())&&(t.equals(current)));
						closed.removeIf(condition);
						if(!closed.contains(current))
							open.add(current);
					}
				}
			}
		}
		
	}
	// tries to create every possible move , if legal creates a node of this move
	public ArrayList<Node> successor(Node node)
	{
		ArrayList<Node> neighbors = new ArrayList<Node>();
		Board current = node.getBoard();
		ArrayList<Car> cars = current.getCars();
		if(current.getbCars()==0) // end case in which the red car path is not blocked and the next move should be the end move
		{
			if(current.isPossibleMove(current.getRedCar(),current.getxExit()-current.getRedCar().getLastX(),true))
			{

				Board newBoard = current.moveCar(current.getRedCar(),current.getxExit()-current.getRedCar().getLastX(),true);
				Node newNode= new Node(node.getDistance()+1);
				newNode.setBoard(newBoard);
				newNode.setFather(node);
				newNode.calcGrade();
				char direction='R';
				String moveS =String.valueOf("X"+String.valueOf(direction)+String.valueOf(current.getxExit()-current.getRedCar().getLastX()));

				newNode.setMove(moveS);
				neighbors.add(newNode);
			}
			return neighbors;
			
		}
		for (int c = 0; c < cars.size(); c++) {
			for (int steps = 1; steps < current.size; steps++) {
				boolean dir = false; // false idicates up or left
				if(current.isPossibleMove(cars.get(c), steps, dir))
				{
					Board newBoard = current.moveCar(cars.get(c), steps, dir);
					Node newNode= new Node(node.getDistance()+1);
					newNode.setBoard(newBoard);
					newNode.setFather(node);
					newNode.calcGrade();
					char direction='U';
					if(cars.get(c).isHor_orient())
					{
						direction='L';
					}
					String moveS =String.valueOf(cars.get(c).getKey())+String.valueOf(direction)+String.valueOf(steps);
					newNode.setMove(moveS);
					neighbors.add(newNode);
				
				} else {
					steps = current.size;
				}
			}
			for (int steps = 1; steps < current.size; steps++) {
				boolean dir = true; // right idicates right or down
				if(current.isPossibleMove(cars.get(c), steps, dir))
				{
					Board newBoard = current.moveCar(cars.get(c), steps, dir);
					Node newNode= new Node(node.getDistance()+1);
					newNode.setBoard(newBoard);
					newNode.setFather(node);
					newNode.calcGrade();
					char direction='D';
					if(cars.get(c).isHor_orient())
					{
						direction='R';
					}
					String moveS =String.valueOf(cars.get(c).getKey())+String.valueOf(direction)+String.valueOf(steps);
					newNode.setMove(moveS);
					neighbors.add(newNode);
				
				} else {
					steps = current.size;
				}
			}
		}
		return neighbors;
		
	}
}

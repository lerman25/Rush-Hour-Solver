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
		// change to the goal , we have to define how it is defined
		HashMap<Node, Node> parentMap = new HashMap<Node, Node>();
		HashSet<Node> visited = new HashSet<Node>();
		double inf = Double.POSITIVE_INFINITY;
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				if(o1.getF_grade()==o2.getF_grade())
				return 0;
				if(o1.getF_grade()>o2.getF_grade())
				return 1;
				if(o1.getF_grade()<o2.getF_grade())
				return -1;
			}
		});
		// ** enque StartNode, with distance 0**
		startNode.setDistanceToStart(new Double(0));
		distances.put(startNode, new Double(0));
		priorityQueue.add(startNode);
		Node current = null;
		while (!priorityQueue.isEmpty()) {
			current = priorityQueue.remove();

			if (!visited.contains(current)) {
				visited.add(current);
				// if last element in PQ reached
				if (false) { // need to check here how to check if the red car is in the goal spot
					return true;
				}
				Set<Node> neighbors = getNeighbors(current);
				for (Node neighbor : neighbors) {
					if (!visited.contains(neighbor)) {
						

						// calculate predicted distance to the end node**hueristic computation
						// double predictedDistance = neighbor.getDistance();

						// 1. calculate distance to neighbor. 2. calculate dist from start node
						int neighborDistance = neighbor.getDistance();
						// double totalDistance = current.getDistance() + neighborDistance +
						// predictedDistance;
						int totalDistance = current.getDistance() + neighborDistance;
						// check if distance smaller
						if (totalDistance < distances.get(neighbor)) {
							// update n's distance
							distances.put(neighbor, totalDistance);
							// used for PriorityQueue
							neighbor.setDistance(totalDistance);
							// neighbor.setPredictedDistance(predictedDistance);
							// set parent
							parentMap.put(neighbor, current);
							// enqueue
							priorityQueue.add(neighbor);
						}
					}
				}
			}
		}

		return false;
	}


}

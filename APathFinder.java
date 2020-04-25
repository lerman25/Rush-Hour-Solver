import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class APathFinder {

	public boolean aStarSearch()
	{
		Board startNode= new Board();
		startNode.readBoard_scan();
		// Board endNode = ;
		//change to the goal , we have to define how it is defined
		 HashMap<Board,Board>  parentMap =new HashMap<Board,Board>();
		HashSet<Board> visited = new HashSet<Board>();
		 double inf = Double.POSITIVE_INFINITY;
		 Map<Board, Double> distances = null ;
	    //to implement initializeAllToInfinity();
		 Queue<Board> priorityQueue = null;
		 // ** enque StartNode, with distance 0**
	       startNode.setDistanceToStart(new Double(0));
	       distances.put(startNode, new Double(0));
	      priorityQueue.add(startNode);
	      Board current = null;
	      while (!priorityQueue.isEmpty()) {
	    	  current = priorityQueue.remove();
	    	  
	    	  if (!visited.contains(current) ){
	                visited.add(current);
	                // if last element in PQ reached
	                if (false)
	                { // need to check here how to check if the red car is in the goal spot 
	                	return true;
	                
	                    }

	                Set<Board> neighbors = getNeighbors(current);
	                for (Board neighbor : neighbors) {
	                    if (!visited.contains(neighbor) ){  
	 
	                        // calculate predicted distance to the end node**hueristic computation
	                       // double predictedDistance = neighbor.getDistance();
	 
	                        // 1. calculate distance to neighbor. 2. calculate dist from start node
	                        double neighborDistance = neighbor.getDistanceToStart();
	                        // double totalDistance = current.getDistance() + neighborDistance + predictedDistance;
	                        double totalDistance = current.getDistanceToStart()+neighborDistance;
	                        // check if distance smaller
	                        if(totalDistance < distances.get(neighbor) ){
	                            // update n's distance
	                            distances.put(neighbor, totalDistance);
	                            // used for PriorityQueue
	                            neighbor.setDistanceToStart(totalDistance);
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

	private Set<Board> getNeighbors(Board current) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}

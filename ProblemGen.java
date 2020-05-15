
public class ProblemGen {
	private Node initial;
	public ProblemGen()
	{
		initial = new Node(0);
		Board endBoard = new Board();
		endBoard.readBoard_string("OAA.B.OCD.BPOCDXXPQQQE.P..FEGGHHFII.");
		endBoard.setAlpha(0);
		endBoard.setBeta(0);
		initial.setBoard(endBoard);
	}
	//solves the most difficult problem , then tracks pack until the depth is right.
	public Board generate(int depth)
	{
		Astar ai = new Astar(initial);
		Node target = ai.aStarSearch();
		if(depth>target.getDistance())
		{
			System.out.println("Can't generate such problem");
		}
		if(target==null)
		return null;
		for(int i=1;i<=depth;i++)
		{
			if(target!=null)
			target=target.getFather();
		}
		return target.getBoard();
		
	}

}

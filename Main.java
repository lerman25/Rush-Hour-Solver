import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static String succsess = "Y";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> problems = new ArrayList<String>();

		if (args.length == 0) {
			Scanner scan = new Scanner(System.in); // Create a Scanner object
			System.out.println("Enter Board Line");
			String line = scan.nextLine(); // Read user input
			line.trim();
		} else {
			long time = Long.parseLong(args[1]);

			System.out.println(args[0]);
			System.out.println(args[1]);
			File input = new File(args[0]);
			if (!input.canRead())
				System.out.println("Unreadable File");
			try {
				int counter = 0;
				Scanner scanner = new Scanner(input);
				while (scanner.hasNextLine()) {
					String nextline = scanner.nextLine();
					if (nextline != null || !nextline.isEmpty()) {
						problems.add(nextline);

					} else
						System.out.println("null on " + counter);
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<Stats> stats = new ArrayList<Stats>();
			for (int i = 0; i < problems.size(); i++) {
				stats.add(solve(problems.get(i), time, i + 1,(float)0.8,(float)0.3));
			}
			printStatsAvg(stats);
			for (int i = 0; i < problems.size(); i++) {
				stats.add(solve(problems.get(i), time, i + 1,(float)3,(float)1));
			}
			printStatsAvg(stats);
			//if want to test problem generator - use this code
//			ProblemGen pg = new ProblemGen();  
//			pg.generate(25).print_full_stats();
		}

	}

	public static Stats solve(String line, long time, int problem, float alpha, float beta) {
		succsess = "Y";
		Board board = new Board();
		board.readBoard_string(line);
		board.setAlpha(alpha);
		board.setBeta(beta);
		Node node = new Node(0);
		node.setBoard(board);
		Instant start = Instant.now(); // timer start
		Astar ai = new Astar(node);
		Node target = ai.aStarSearch();
		Instant finish = Instant.now(); // timer end
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.addAll(ai.closed);
		nodes.addAll(ai.open);
		Stack moveStack = target.trackBack(); // stack of moves

		long timeElapsed = Duration.between(start, finish).toMillis();
		if (timeElapsed > time)
			succsess = "F";
		int size = moveStack.size();
		if (succsess == "Y") {
			System.out.print("\nTotal Moves: " + size);
			System.out.println("");
			for (int i = 0; i < size; i++) {
				System.out.print(moveStack.pop());
				System.out.print(",");
			}
		} else
			System.out.print("\nFAILED");
		// CODE HERE
		System.out.println("");
		double hsum = 0;
		double dsum = 0;
		int N = nodes.size();
		for (int i = 0; i < N; i++) {
			Node _node = nodes.get(i);
			hsum += _node.getBoard().h();
			dsum += _node.getDistance();
		}
		int mindepth = Integer.MAX_VALUE;
		Object[] open = ai.open.toArray();
		for (int i = 0; i < ai.open.size(); i++) {
			Node current = (Node) open[i];
			if (current.getDistance() < mindepth) {
				mindepth = current.getDistance();
			}
		}
		int maxDepth = 0;
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).getDistance()>maxDepth)
			{
				maxDepth=nodes.get(i).getDistance();
			}
		}
				
		double havg = (hsum / N);
		double davg = dsum /(double)N;
		int d = target.getDistance();
		double dn = ((double) d / N);
		double bstar = Math.pow(N, (1 / (double) d));
		Stats pStats = new Stats(succsess, N, havg, davg, dn, bstar, problem, alpha, beta, timeElapsed, mindepth,
				maxDepth);
		pStats.printStats();
		return pStats;
	}
//
//	private void testFunction() {
//		Board board = new Board();
//		board.readBoard_scan();
//		board.print_board();
//		board.print_cars();
//
//		Car q = board.retCar('Q');
//		Car a = board.retCar('A');
//		Car b = board.retCar('B');
//		Car x = board.retCar('X');
//		Car r = board.retCar('R');
//		Car p = board.retCar('P');
//		Car c = board.retCar('C');
//		Car o = board.retCar('O');
//
//		board.removeCar(q);
//		q = board.moveCar(q, 1, true);
//		board.addCar(q);
//		board.print_full_stats();
//		// move
//		board.removeCar(a);
//		a = board.moveCar(a, 1, true);
//		board.addCar(a);
//		board.print_full_stats();
//		// move
//		board.removeCar(p);
//		p = board.moveCar(p, 1, false);
//		board.addCar(p);
//		board.print_full_stats();
//		// move
//		board.removeCar(b);
//		b = board.moveCar(b, 1, false);
//		board.addCar(b);
//		board.print_full_stats();
//		// move
//		board.removeCar(r);
//		b = board.moveCar(r, 2, false);
//		board.addCar(r);
//		board.print_full_stats();
//		// move
//		board.removeCar(q);
//		q = board.moveCar(q, 2, false);
//		board.addCar(q);
//		board.print_full_stats();
//		// move
//		board.removeCar(c);
//		c = board.moveCar(c, 3, false);
//		board.addCar(c);
//		board.print_full_stats();
//		// move
//		board.removeCar(q);
//		q = board.moveCar(q, 3, true);
//		board.addCar(q);
//		board.print_full_stats();
//		// move
//		board.removeCar(o);
//		q = board.moveCar(o, 3, true);
//		board.addCar(o);
//		board.print_full_stats();
//		// move
//		board.removeCar(x);
//		x = board.moveCar(x, 3, true);
//		board.addCar(x);
//		board.print_full_stats();
//	}
	public static void printStatsAvg(ArrayList<Stats>stats)
	{
		 double N=0;
		 double havg=0;
		 double davg=0;
		 double dn=0;
		 double bstar=0;
		 double timeElapsed=0;
		 double minDepth=0;
		 double maxDepth=0;
		 int counter = 0;
		 System.out.println("For"+" h("+stats.get(0).getAlpha()+":"+stats.get(0).getBeta() + "): ");
		 for(int i=0;i<stats.size();i++)
		 {
			 N+=stats.get(i).getN();
			 havg+=stats.get(i).getHavg();
			 davg+=stats.get(i).getDavg();
			 dn+=stats.get(i).getDn();
			 bstar+=stats.get(i).getBstar();
			 timeElapsed+=stats.get(i).getTimeElapsed();
			 minDepth+=stats.get(i).getMinDepth();
			 maxDepth+=stats.get(i).getMaxDepth();
			 if(stats.get(i).getSuccsess().equals("Y"))
				 counter++;
		 }
		 N/=stats.size();
		 havg/=stats.size();
		 davg/=stats.size();
		 dn/=stats.size();
		 bstar/=stats.size();
		 timeElapsed/=stats.size();
		 maxDepth/=stats.size();
		 minDepth/=stats.size();
		 System.out.println("Total Solved: "+counter+" Out of: "+stats.size());
			String div = "|";
			System.out.println("Avrages:" + div + "h("+stats.get(0).getAlpha()+":"+stats.get(0).getBeta() + ")"+div + N + div +dn+div+succsess + div + timeElapsed + div + bstar + div
					+ havg + div +minDepth+ div + davg + div + maxDepth);

	}
}

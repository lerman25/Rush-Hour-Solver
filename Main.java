import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		board.readBoard_scan();
		board.print_board();
		board.print_cars();
		Car q = board.retCar('Q');
		Car a = board.retCar('A');
		Car b = board.retCar('B');
		Car x = board.retCar('X');
		Car r = board.retCar('R');
		Car p = board.retCar('P');
		Car c = board.retCar('C');
		Car o = board.retCar('O');


		board.removeCar(q);
		q=board.moveCar(q, 1,true);
		board.addCar(q);
		board.print_full_stats();
		//move
		board.removeCar(a);
		a=board.moveCar(a, 1,true);
		board.addCar(a);
		board.print_full_stats();
		//move
		board.removeCar(p);
		p=board.moveCar(p, 1,false);
		board.addCar(p);
		board.print_full_stats();
		//move
		board.removeCar(b);
		b=board.moveCar(b, 1,false);
		board.addCar(b);
		board.print_full_stats();
		//move
		board.removeCar(r);
		b=board.moveCar(r, 2,false);
		board.addCar(r);
		board.print_full_stats();
		//move
		board.removeCar(q);
		q=board.moveCar(q, 2,false);
		board.addCar(q);
		board.print_full_stats();
		//move
		board.removeCar(c);
		c=board.moveCar(c, 3,false);
		board.addCar(c);
		board.print_full_stats();
		//move
		board.removeCar(q);
		q=board.moveCar(q, 3,true);
		board.addCar(q);
		board.print_full_stats();
		//move
		board.removeCar(o);
		q=board.moveCar(o, 3,true);
		board.addCar(o);
		board.print_full_stats();
		//move
		board.removeCar(x);
		x=board.moveCar(x, 3,true);
		board.addCar(x);
		board.print_full_stats();
	}
}

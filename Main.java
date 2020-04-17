import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		board.readBoard_scan();
		board.print_board();
		board.print_cars();
		Car Xcar1 = board.retCar('Q');
		board.getCars().remove(Xcar1);
		board.getCars().add(board.moveCar(Xcar1, 1,true));
		board.print_board();
		board.print_cars();
		Car Xcar = board.retCar('Q');
		board.getCars().remove(Xcar);
		board.getCars().add(board.moveCar(Xcar, 1,false));
		System.out.println(Xcar1.equals(board.retCar('Q')));
		board.print_board();
		board.print_cars();
		System.out.println("X dis: "+board.getXCarDis()+" X car block: "+board.getXcarBlock());
	}
}

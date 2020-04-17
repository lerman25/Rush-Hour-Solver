import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		board.readBoard_scan();
		board.print_board();
		board.print_cars();
		board.getCars().add(board.moveCar(board.getCars().get(1), 1));
		board.getCars().add(board.moveCar(board.getCars().get(0), 4));
		board.print_board();
		board.print_cars();
		
	}
}

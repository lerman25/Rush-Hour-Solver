import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Board {
	private char[][] board;
	private int xExit;
	private int	yExit;
	private String filePath = "C:\\Users\\user\\nov - eclipse-workspace\\lab1-AI\\src\\boards.txt";
	public Board()
	{
		board = new char[6][6];
		setxExit(5);
		setyExit(2);
	}
	public Board(int i,int j)
	{
		board = new char[6][6];
		setxExit(j);
		setyExit(i);
	}
	public void print_board()
	{
		System.out.print("\n\n");
		for(int i=0;i<6;i++)
		{
			System.out.print("*****   ");
			for(int j=0;j<6;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.print("   *****   \n");
		}
		System.out.print("\n\n");
	}
	public void readBoard_file(int line) throws FileNotFoundException
	{
		File boardFile = new File(filePath);
		java.util.Scanner reader = new java.util.Scanner(boardFile);
		String boardLine="";
		for(int i=1;i<=line;i++)
		{
			 boardLine = reader.nextLine();
		}
		readBoard_line(boardLine);
			
	}
	public void readBoard_scan()
	{
	    Scanner scan = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter Board Line");
	    String line = scan.nextLine();  // Read user input
	    readBoard_line(line);
	}
	public void readBoard_line(String line)
	{
		char[] array = line.toCharArray();
		int y=0;
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				board[i][j]=array[y];
				y++;
			}
		}
	}
	public int getxExit() {
		return xExit;
	}
	public void setxExit(int xExit) {
		this.xExit = xExit;
	}
	public int getyExit() {
		return yExit;
	}
	public void setyExit(int yExit) {
		this.yExit = yExit;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}

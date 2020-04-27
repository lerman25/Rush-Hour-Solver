import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Board {
	private char[][] board;
	private double DistanceToStart ;
	private int xExit;
	private int	yExit;
	private int redDis;
	private int bCars;
	private Car redCar;
	public int size=6;
	private String filePath = "C:\\Users\\user\\nov - eclipse-workspace\\lab1-AI\\src\\boards.txt";
	private ArrayList<Car> cars;
	public Board()
	{
		board = new char[size][size];
		setxExit(5);
		setyExit(2);
		cars=new ArrayList<Car>();
	}
	public Board(int i,int j)
	{
		board = new char[size][size];
		setxExit(j);
		setyExit(i);
		cars=new ArrayList<Car>();
	}
	public Board(int i,int j,int _size)
	{
		size =_size;
		board = new char[size][size];
		setxExit(j);
		setyExit(i);
		cars=new ArrayList<Car>();
	}
	public void print_board()
	{
		System.out.print("\n\n");
		for(int i=0;i<size;i++)
		{
			System.out.print("*****   ");
			for(int j=0;j<size;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.print("   *****   \n");
		}
		System.out.print("\n\n");
	}
	public Car moveCar(Car car , int steps,boolean dir)
	{
		//true for the down/right
		if(car.isHor_orient())  // Such as ...AA.
		{
			if(dir)
			{
				int xCord = car.getLastX()+steps;
				if(xCord>=this.size) //out of bounds
					return null;
				for(int j=car.getLastX()+1;j<=xCord;j++)
				{
					if(board[car.getY()][j]!='.')
						return null;
				}
				for(int j=1;j<=steps;j++)
				{
					board[car.getY()][j+car.getLastX()]=car.getKey();
					board[car.getY()][car.getX()+j-1]='.';
				}
				car.setLastX(xCord);
				car.setX(car.getX()+steps);
				return car;
			}
			else
			{
				int xCord = car.getX()-steps;
				if(xCord<0) //out of bounds
					return null;
				for(int j=car.getX()-1;j>=xCord;j--)
				{
					if(board[car.getY()][j]!='.')
						return null;
				}
				for(int j=1;j<=steps;j++)
				{
					board[car.getY()][car.getLastX()-j+1]='.';
					board[car.getY()][car.getX()-j]=car.getKey();
				}
				car.setLastX(car.getLastX()-steps);
				car.setX(xCord);
				return car;
			}
		}
		else   // Such as ....A. | ....A.
		{
			if(dir)
			{

				int yCord = car.getLastY()+steps;
				if(yCord>=this.size) //out of bounds
					return null;
				for(int i=car.getLastY()+1;i<=yCord;i++)
				{
					if(board[i][car.getX()]!='.')
						return null;
				}
				int changeInBcars=0;
				if(isOnRedPath(car))
					changeInBcars=1;
				for(int i=1;i<=steps;i++)
				{
					board[i+car.getLastY()][car.getX()]=car.getKey();
					board[car.getY()+i-1][car.getX()]='.';
				}

				car.setLastY(yCord);
				car.setY(car.getY()+steps);
				if(isOnRedPath(car)&&changeInBcars==0)
					setbCars(getbCars()+1);
				if(!isOnRedPath(car)&&changeInBcars==1)
					setbCars(getbCars()-1);
				return car;
			}
			else
			{
				int yCord = car.getY()-steps;
				if(yCord<0) //out of bounds
					return null;
				for(int i=car.getY()-1;i>=yCord;i--)
				{
					if(board[i][car.getX()]!='.')
						return null;
				}
				for(int i=1;i<=steps;i++)
				{
					board[car.getLastY()-i+1][car.getX()]='.';
					board[car.getY()-i][car.getX()]=car.getKey();

					
				}
				car.setLastY(car.getLastY()-steps);
				car.setY(yCord);
				return car;
			}
		}
	}
	public float h()
	{
		return (float) (0.3*(float)redDis+0.7*(float)bCars);
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
		cars = Car.getCarsFromBoard(this);
			
	}
	public void readBoard_scan()
	{
	    Scanner scan = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter Board Line");
	    String line = scan.nextLine();  // Read user input
	    line.trim();
	    readBoard_line(line);
		cars = Car.getCarsFromBoard(this);

	}
	public void readBoard_line(String line)
	{
		char[] array = line.toCharArray();
		int y=0;
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				board[i][j]=array[y];
				y++;
			}
		}
	}
	public void print_cars()
	{
		for(int i=0;i<cars.size();i++)
		{
			Car current = cars.get(i);
			System.out.println(current.toString());
		}
	}
	public int getXCarDis()
	{
		Car Xcar = retCar('X');
		return xExit-Xcar.getLastX();
	}
	public int getXcarBlock()
	{
		Car Xcar = retCar('X');
		int counter=0;
		for(int j=Xcar.getLastX()+1;j<size;j++)
		{
			if(board[Xcar.getY()][j]!='.')
				counter++;
		}
		return counter;
	}
	public int getxExit() {
		return xExit;
	}
	public Car retCar(char c)
	{
		for(int i=0;i<cars.size();i++)
		{
			Car car =cars.get(i);
			if(car.getKey()==c)
				return car;
		}
		return null;
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
	public char[][] getBoard() {
		return board;
	}
	public void setBoard(char[][] board) {
		this.board = board;
	}
//	public ArrayList<Car> getCars() {
//		return cars;
//	}
//	public void setCars(ArrayList<Car> cars) {
//		this.cars = cars;
//	}
	public int getRedDis() {
		return redDis;
	}
	public void setRedDis(int redDis) {
		this.redDis = redDis;
	}
	public int getbCars() {
		return bCars;
	}
	public void setbCars(int bCars) {
		this.bCars = bCars;
	}
	public Car getRedCar() {
		return redCar;
	}
	public void setRedCar(Car redCar) {
		this.redCar = redCar;
	}
	public void removeCar(Car remove)
	{
		cars.remove(remove);
	}
	public void addCar(Car add)
	{
		cars.add(add);
		if(add.getKey()=='X')
		{
			setRedCar(add);
			redDis = (xExit-add.getLastX());
		}
	}
	public boolean isOnRedPath(Car c)
	{
		if((xExit-c.getLastX())<getRedDis())
		{
			if((c.getLastY()-c.getLength())<yExit)
				return true;
		}
		return false;
	}
	public boolean isTargetBoard()
	{
		return(redCar.getLastX()==xExit);
	}
	public void print_h()
	{
		System.out.println("Red Distance : "+redDis);
		System.out.println("#Blocking Cars : "+bCars);
		System.out.println("h : "+h());
	}
	public void print_full_stats()
	{
		print_board();
		print_cars();
		print_h();
		System.out.println("is Targe Board : "+isTargetBoard());
	}
	public double getDistanceToStart() {
		return DistanceToStart;
	}
	public void setDistanceToStart(double distanceToStart) {
		DistanceToStart = distanceToStart;
	}
	public ArrayList<Car> getCars() {
		return cars;
	}
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

}

import java.text.DecimalFormat;

public class Stats {
	private String succsess;
	private int N;
	private double havg;
	private double davg;
	private double dn;
	private double bstar;
	private int problem;
	private float alpha;
	private float beta;
	private long timeElapsed;
	private int minDepth;
	private int maxDepth;
	public Stats(String succsess, int n, double havg, double davg, double dn, double bstar, int problem, float alpha,
			float beta, long timeElapsed, int minDepth, int maxDepth) {
		super();
		this.succsess = succsess;
		N = n;
		this.havg = havg;
		this.davg = davg;
		this.dn = dn;
		this.bstar = bstar;
		this.problem = problem;
		this.alpha = alpha;
		this.beta = beta;
		this.timeElapsed = timeElapsed;
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
	}
	public void printStats()
	{
		   DecimalFormat formatter = new DecimalFormat("#0.000");
		String div = "|";
		System.out.println("Problem | Heuristic name | N | d/N | Success (Y/N) | Time (ms) | EBF | avg H value | Min | Avg | Max");
		System.out.println(problem +"       "+ div + "h("+alpha+":"+beta + ")" +"      "+div + N + div +formatter.format(dn)+div+succsess+"             " + div + timeElapsed +"        "+ div + formatter.format(bstar)+" " + div
				+ formatter.format(havg)+"        " + div +formatter.format(minDepth)+ div + formatter.format(davg) + div + formatter.format(maxDepth));
	}
	public String getSuccsess() {
		return succsess;
	}
	public void setSuccsess(String succsess) {
		this.succsess = succsess;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public double getHavg() {
		return havg;
	}
	public void setHavg(double havg) {
		this.havg = havg;
	}
	public double getDavg() {
		return davg;
	}
	public void setDavg(double davg) {
		this.davg = davg;
	}
	public double getDn() {
		return dn;
	}
	public void setDn(double dn) {
		this.dn = dn;
	}
	public double getBstar() {
		return bstar;
	}
	public void setBstar(double bstar) {
		this.bstar = bstar;
	}
	public int getProblem() {
		return problem;
	}
	public void setProblem(int problem) {
		this.problem = problem;
	}
	public float getAlpha() {
		return alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public float getBeta() {
		return beta;
	}
	public void setBeta(float beta) {
		this.beta = beta;
	}
	public long getTimeElapsed() {
		return timeElapsed;
	}
	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	public int getMinDepth() {
		return minDepth;
	}
	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}
	public int getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

}

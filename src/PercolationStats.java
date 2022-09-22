import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

	private int n;
	private int trials;
	private double[] thresholds;

	
	
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	// System.out.println("PercolationStats");
    	// System.out.println("n from PercolationStats constructor= "+n);
    	if (n <= 0 || trials <=0) throw new IllegalArgumentException();
    	this.n = n;
    	this.trials = trials;
    	thresholds = new double[trials];
    	
    	
    	
    	for (int i=0; i<trials; i++) {
    		Percolation percolation = new Percolation(n);
    		//System.out.println("Trial number "+i);
    		while(!percolation.percolates())
    		{
    			int randomRow = StdRandom.uniformInt(1, n+1);
    			int randomCol = StdRandom.uniformInt(1, n+1);
    			
    			if (!percolation.isOpen(randomRow, randomCol))
    				percolation.open(randomRow, randomCol);
    		}
    		thresholds[i] = (double) percolation.numberOfOpenSites()/(n*n);
    		
    		// System.out.println("Threashold ["+i+"]= "+thresholds[i]);
    	}
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return (mean() - 1.92*stddev())/java.lang.Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return (mean() + 1.92*stddev())/java.lang.Math.sqrt(trials);
    }

   // test client (see below)
   public static void main(String[] args) {
	   int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);

	   PercolationStats percolationStats = new PercolationStats(n,trials);
	   
	   System.out.println("mean = "+percolationStats.mean());
	   System.out.println("stddev = "+percolationStats.stddev());
	   System.out.println("95% confidence interval = ["+percolationStats.confidenceLo()+", "+percolationStats.confidenceHi()+"]");
	   
   }
   

}
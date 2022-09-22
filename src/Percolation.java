import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
    private int n;
	private WeightedQuickUnionUF sites;
	private int virtualTop;
	private int virtualBottom;
	private int[] openSites;
	private int openCount; 
	

	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
		
    	if (n <= 0) throw new IllegalArgumentException();
    	this.n = n;
    	this.openCount = 0;
    	// System.out.println("n from Percolation constructor= "+n);
    	// System.out.println("n*n+2 from Percolation constructor= "+n*n+2);
    	// System.out.println("(n*n)+2 from Percolation constructor= "+((n*n)+2));
    	
    	this.openSites = new int[(n*n)+2];
    	virtualTop = 0;
    	virtualBottom = n*n +1;
    	
    	openSites[virtualTop] = 1;
    	openSites[virtualBottom] = 1;
    	
    	sites = new WeightedQuickUnionUF(n*n +2);
    	
    	

    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	// System.out.println("Opening row = "+row+" col="+col);
    	if (row > n || col > n || row < 1 || col < 1) 
    		throw new IllegalArgumentException();
    	   	
    	openSites[getIndex(row, col)] = 1;    	
    	openCount++;
    	
    	if (row == 1)
    	{
    		sites.union(getIndex(row, col),virtualTop); 
    	}
    	if (row == n)
    	{
    		sites.union(getIndex(row, col),virtualBottom); 
    	}
    	
    	
    	
    	if (col >=2){
    		//openSiteAtPos(row, col-1); //left
    		if (openSites[getIndex(row, col-1)] ==1){
    			sites.union(getIndex(row, col-1),getIndex(row, col)); 
        	}
    		if (col < n){
    			//openSiteAtPos(row, col+1); //right
    			if (openSites[getIndex(row, col+1)] ==1){
    				sites.union(getIndex(row, col+1),getIndex(row, col)); 
    	    	}
    		}  
    	}
    	if (row >=2){
    		//openSiteAtPos(row-1, col);  // Top
    		if (openSites[getIndex(row-1, col)] ==1){
    			sites.union(getIndex(row-1, col),getIndex(row, col)); 
        	}
    		if (row < n){
    			//openSiteAtPos(row+1, col);  // Bottom
    			if (openSites[getIndex(row+1, col)] ==1){
    				sites.union(getIndex(row+1, col),getIndex(row, col)); 
    	    	}
    		} 
    	}
  	
    	/*System.out.println("current grid:");
    	for (int i=0; i<openSites.length;i++)
    	{
    			System.out.print(openSites[i] +" ");
    	}
    	System.out.println();*/
    }
    
    /*private void openSiteAtPos(int row, int col) {
    	if (openSites[getIndex(row, col)] ==1){
			sites.union(getIndex(row, col),getIndex(row, col)); 
    	}
		
	}*/

	private int getIndex(int row, int col)
    {
    	if (row > n || col > n || row < 1 || col < 1) 
    		throw new IllegalArgumentException();
		/*System.out.println("row= "+row);
		System.out.println("col= "+col);
		System.out.println("n= "+n);
		System.out.println("Index n*(row-1)+col = "+(n*(row-1)+col));*/
    	return n*(row-1)+col+1;
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	if (row > n || col > n || row < 1 || col < 1) 
    		throw new IllegalArgumentException();
    	return openSites[getIndex(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if (row > n || col > n || row < 1 || col < 1) 
    		throw new IllegalArgumentException();
    	return 	sites.find(virtualTop) == sites.find(getIndex(row, col));    	
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	// System.out.println("openCount = "+openCount);
    	return openCount;
    }
    

    // does the system percolate?
    public boolean percolates() {
    	/* System.out.println("Checking percolation");
    	System.out.println("sites.find(virtualTop) = "+sites.find(virtualTop));
    	System.out.println("sites.find(virtualBottom) = "+sites.find(virtualBottom)); */
    	
    	return sites.find(virtualTop) == sites.find(virtualBottom);
    }
    

    // test client (optional)
    public static void main(String[] args) {}
}
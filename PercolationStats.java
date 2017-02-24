/** 
 *The PercolationStats class defines a stats for perolation. 
 *Its purpose is to calculate the stats for perolation process 
 *@verison Week1 
 */ 


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats
{
   private int tCount; //Count for trials
   private double[] fractions;
   
   /**
    * set Parameter Constructor, create n-by-n grid,.
    * @param n is the new value of the grid.  
    * @param tirals is the new value of number of trials.
    */   
   public PercolationStats(int n, int trials)    // perform trials independent exp
   {
        if (n <= 0 || trials <= 0) 
        {
            throw new IllegalArgumentException("n and trials must be positive");
        }
        
        tCount = trials;
        fractions = new double[tCount];
        
        for (int count = 0; count < tCount; count++) 
        {
            int sCount = 0; //set opened sites count
            Percolation pr = new Percolation(n);
            
            //repeat generate opned site untile percolates
            while (!pr.percolates()) 
            {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!pr.isOpen(i, j)) //if the site is blocked
                {
                    pr.open(i, j);
                    sCount++;
                }
            }
            
            fractions[count] = (double) sCount / (n*n);            
        }
       
   }
   
   
   public double mean()                          // sample mean of percolation
   {
           return StdStats.mean(fractions);           
   }
   
   public double stddev()                        // sample stdev of percolation
   {
           return StdStats.stddev(fractions);
   }
   public double confidenceLo()                  // low  endpoint of 95% interval
   {
            return mean() - ((1.96 * stddev()) / Math.sqrt(tCount));
   }
   public double confidenceHi()                  // high endpoint of 95% interval
   {
           return mean() - ((1.96 * stddev()) / Math.sqrt(tCount));
   }

   public static void main(String[] args)    // test client (described below)   
   {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats myStats = new PercolationStats(n, trials);
       StdOut.println("Mean                    = " + myStats.mean());
       StdOut.println("Standard deviation      = " + myStats.stddev());
       StdOut.println("95% confidence interval = "+ myStats.confidenceLo()+
                      ", "+ myStats.confidenceHi());           
           
   }
                
        
}

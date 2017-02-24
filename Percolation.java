/** 
 *The Percolation class defines a perolation object. 
 *Its purpose is to stimulate the perolation process 
 *@author Yuyang Sun 
 *@email syuyangok@gmail.com
 *@verison Week1 
 */ 

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{

    private int size; //Create a gride of size.
    private boolean[][] opened; //site open "true"or not"false".
    private int top;
    private int bottom;
    
    private WeightedQuickUnionUF uf; 


    /**
     * set Parameter Constructor, create n-by-n grid, with all sites blocked.
     * @param n is the new value of the size.  
     */
   public Percolation(int n)       
   {
           size = n;
           opened = new boolean[n][n]; //initial all sites block.          
           top = 0; //Creat top root
           bottom = n*n+1; // Creat bottom root
           
           uf = new WeightedQuickUnionUF(n*n+2); //create a UF object.
   }
   
    /**
     * set  open site (row i, column j) if it is not open already.
     * @param i is the new value for the site row.
     * @param j is the new value for the site column.  
     */
   public void open(int i, int j)          
   {
        validate(i, j);
        opened[i - 1][j - 1] = true; // Open site.Upleft(0,0) is parameter (1,1), 
  
        if (i == 1) //If site in first row
        {
            uf.union(getIndex(i, j), top);
        }
        if (i == size) //If site in last row
        {
            uf.union(getIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) //If site left is opened
        {
            uf.union(getIndex(i, j), getIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) //If site right is opened
        {
            uf.union(getIndex(i, j), getIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) //If site up is opened
        {
            uf.union(getIndex(i, j), getIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) //If site down is opened
        {
            uf.union(getIndex(i, j), getIndex(i + 1, j));
        }
   }
   
   /** 
    * Returns value of index 
    * @param i is the new value for the site row.
    * @param j is the new value for the site column.  
    * @return the current index for UnionFiond. 
    */  
    private int getIndex(int i, int j) 
   {
        return size * (i - 1) + j;
    }
  
   /** 
    * Returns value of opened or not 
    * @param i is the new value for the site row.
    * @param j is the new value for the site column.  
    * @return the current boolean value for opened. 
    */      
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
           validate(i, j);
           return opened[i-1][j-1];                        
           
   }
   /** 
    * Returns value of if the site is full 
    * @param i is the new value for the site row.
    * @param j is the new value for the site column.  
    * @return the current boolean value for ifFull. 
    */     
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
           validate(i, j);
           return uf.connected(getIndex(i, j) , top);                         
           
   }

   /** 
    * Check value of the site if it is validated 
    * @param i is the new value for the site row.
    * @param j is the new value for the site column.  
    */      
    private void validate(int i, int j) 
    {
        if (!(i >= 1 && i <= size && j >= 1 && j <= size)) 
        {
            throw new IndexOutOfBoundsException("Index is not betwwen 1 and N");
        }
    }   
    
   /** 
    * Returns value of if the top and bottom is connected 
    * @param i is the new value for the site row.
    * @param j is the new value for the site column.  
    * @return the current boolean value for percolates. 
    */   
   public boolean percolates()             // does the system percolate?
   {
           return uf.connected(top, bottom);
   }

   
   //public static void main(String[] args)  // test client (optional)
}
/** 
 *@author Yuyang Sun 
 *@email syuyangok@gmail.com
 */ 
public class StackOfStrings
{
    private String[] a;
    private int size;
    
    public void push(String item)
    {
        a[size++]= item;
    }
    
    public String pop()
    {        
        return a[--size];
    }
    
    public boolean isEmpty()
    {
        return size==0;
    }
    
    public int getSize()
    {
        return size;
    }
}
    
    
    
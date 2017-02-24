/** 
 */ 
public class stackdriver
{
   
        public static void main (String[] args)
        {
            StackOfStrings stack = new StackOfStrings();
            
            while(!stack.isEmpty())
            {
                String s = StdIn.readString();
                if (s.equals("-"))
                {
                    StdOut.print(stack.pop());
                }
                else
                {
                    stack.push(s);
                }
            }
                  
        }        
        
}

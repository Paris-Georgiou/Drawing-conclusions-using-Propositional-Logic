import java.util.Iterator;

public class HornClause extends CNFClause 
{
  
	private Literal head;
   
    public void print()
    {
        super.print();
        System.out.println( "=>");
        head.print();
    }
    
    public void setHead(Literal h) 
    {
    	head=h;
    }
 
    public Literal getHead()
    {
    	return head;
    }
    
    //@Override
    public int hashCode()
    { 
    	 Iterator<Literal> iter = this.getLiteralsList();
         int code = 0;
         
         while(iter.hasNext())
         {
             Literal lit = iter.next();
                code = code + lit.hashCode();
         }
        return code + head.hashCode();
    }
}

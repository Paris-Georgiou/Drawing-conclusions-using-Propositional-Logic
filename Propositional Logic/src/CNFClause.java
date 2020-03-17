import java.util.HashSet;
import java.util.Iterator;

public class CNFClause
{
    private HashSet<Literal> literals = new HashSet<Literal>();
            
    public  HashSet<Literal> getLiterals()            
    {
        return literals;
    }
    
    public Iterator<Literal> getLiteralsList()
    {
        return literals.iterator();
    }
         
    public boolean isEmpty()
    {
        return literals.isEmpty();
    }
    
    public void print()
    {
        System.out.println("**************************");
        Iterator<Literal> iter = this.getLiteralsList();
        
        while(iter.hasNext())
        {
            Literal l = iter.next();
            
            l.print();
        }
    }
}

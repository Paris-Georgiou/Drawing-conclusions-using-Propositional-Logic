import java.util.Vector;

public class KB <C extends CNFClause>
{
    public Vector<C> theClauses = new Vector<C>();
    
    public Vector<C> getCNFClauses()
    {
        return theClauses;
    }
    
    public boolean contains(CNFClause newS)
    {
        for(int i = 0; i < theClauses.size(); i ++)
        {
            if(theClauses.get(i).getLiterals().equals(newS.getLiterals()))
            {
                return true;
            }
        }
        return false;
    }
}

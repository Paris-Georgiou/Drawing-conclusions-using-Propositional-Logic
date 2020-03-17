import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class CNFResolution {
	private KB<CNFClause> kb,pr;

	public CNFResolution (KB<CNFClause> k, KB<CNFClause> p) {
		kb=k;
		pr=p;
	}
	
	
	public boolean prove()
	{
		KB<CNFClause> KB = new KB<CNFClause>();
		KB.getCNFClauses().addAll(kb.getCNFClauses());
		KB.getCNFClauses().addAll(pr.getCNFClauses());
		
		boolean stop = false;
        int step = 1;
        
        while(!stop)
        {
        	Vector<CNFClause> newCNFClauses = new Vector<CNFClause>();
            
            System.out.println("Step:" + step);
            step++;
            
            for(int i = 0; i < KB.getCNFClauses().size(); i++)
            {
                CNFClause Ci = KB.getCNFClauses().get(i);

                for(int j = i+1; j < KB.getCNFClauses().size(); j++)
                {
                    CNFClause Cj = KB.getCNFClauses().get(j);

                    
                    Vector<CNFClause> new_CNFClauses = resolution(Ci, Cj);

                    for(int k = 0; k < new_CNFClauses.size(); k++)
                    {
                        CNFClause newCNFClause = new_CNFClauses.get(k);
						
                        if(newCNFClause.isEmpty()) 
                        {   
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            Ci.print();
                            System.out.println("and");
                            Cj.print();
                            System.out.println("produced:");
                            System.out.println("Empty subclause!!!");
                            System.out.println("----------------------------------");
                            return true;
                        }
                        
                        if(!newCNFClauses.contains(newCNFClause) && !KB.contains(newCNFClause))
                        {
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            Ci.print();
                            System.out.println("and");
                            Cj.print();
                            System.out.println("produced:");
                            newCNFClause.print();
                            newCNFClauses.add(newCNFClause);
                            System.out.println("----------------------------------");
                        }
                    }                           
                }
            }
            
            boolean newCNFClauseFound = false;

            for(int i = 0; i < newCNFClauses.size(); i++)
            {
                if(!KB.contains(newCNFClauses.get(i)))
                {
                    KB.getCNFClauses().addAll(newCNFClauses);                    
                    newCNFClauseFound = true;
                }                        
            }

            if(!newCNFClauseFound)
            {
                System.out.println("Not found new clauses");
                stop = true;
            }   
        }
        return false;
		
	}
	
    public Vector<CNFClause> resolution(CNFClause CNFClause1, CNFClause CNFClause2)
    {
        Vector<CNFClause> new_CNFClauses = new Vector<CNFClause>();

        Iterator<Literal> iter = CNFClause1.getLiteralsList();

        while(iter.hasNext())
        {            
            Literal l = iter.next();
            Literal m = new Literal(l.getName(), !l.getNeg());
          
            if(CNFClause2.getLiterals().contains(m))
            {
                CNFClause newClause = new CNFClause();

                HashSet<Literal> CNFClause1_Replica = new HashSet<Literal>(CNFClause1.getLiterals());
                HashSet<Literal> CNFClause2_Replica = new HashSet<Literal>(CNFClause2.getLiterals());
                CNFClause1_Replica.remove(l);
                CNFClause2_Replica.remove(m);

                newClause.getLiterals().addAll(CNFClause1_Replica);
                newClause.getLiterals().addAll(CNFClause2_Replica);

                new_CNFClauses.add(newClause);
            }
        }
        
        return new_CNFClauses;
    }
}

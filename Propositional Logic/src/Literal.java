public class Literal
{
   
    private String Name;
    private boolean negation;
    
    public Literal(String n, boolean neg)
    {
        this.Name = n;
        this.negation = neg;
    }
    
    public void print()
    {
        if(negation)
            System.out.println("NOT_" + Name);
        else
            System.out.println(Name);
    }
        
    public void setName(String n)
    {
        this.Name = n;
    }
    
    public String getName()
    {
        return this.Name;
    }
    
    public void setNeg(boolean b)
    {
        this.negation = b;
    }
    
    public boolean getNeg()
    {
        return this.negation;
    }
    
   //@Override
    public boolean equals(Object obj)
    {
        Literal l = (Literal)obj;

        if(l.getName().compareTo(this.Name) ==0 && l.getNeg() == this.negation)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
	
    //@Override
    public int hashCode()
    {
            if(this.negation)
            {
                return this.Name.hashCode() + 1;
            }
            else
            {
                return this.Name.hashCode() + 0;                        
            }
    }  
}

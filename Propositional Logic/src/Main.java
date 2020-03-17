public class Main 
{ 
    public static void main(String[] args) 
    {   	
    	boolean b = false;
    	try {
    		CNFLoader kbl = new CNFLoader("files/CNF_KB.txt");
        	CNFLoader prl = new CNFLoader("files/CNF_pr.txt");
		    KB<CNFClause> CNFKB = kbl.loadFile();   
		    KB<CNFClause> CNFpr =	prl.loadFile();
		    	
		    CNFResolution r = new CNFResolution(CNFKB, CNFpr);
	     	b =  r.prove();
	     	System.out.println("is " + b);
	        
		    HornLoader hl = new HornLoader("files/Horn_KB.txt"); 
	        LiteralLoader ll = new LiteralLoader("files/Horn_pr.txt");
	        KB<HornClause> HKB = hl.loadFile();  
	        Literal Hpr = ll.loadFile();
	        
	        HornForward hf = new HornForward(HKB, Hpr);
	     /* b =  hf.prove();
	        System.out.println("--------------------------");
	        Hpr.print();
	        System.out.println("is " + b);
		       */ 
    	} catch (Exception e) {
    		System.err.println(e);
    	}
    }
}
   
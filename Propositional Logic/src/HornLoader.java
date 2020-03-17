import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class HornLoader {
	String filename;
	
    public HornLoader(String f) {	filename = f;	}
    
	public KB<HornClause> loadFile() throws Exception
	{
		KB<HornClause> ret = new KB<HornClause>();
		
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader(filename);
		Object obj = parser.parse(reader);
		JSONArray json = (JSONArray) obj;
		Iterator<JSONArray> iterator = json.iterator();
		
		JSONArray ja;
	    Iterator <String> jaiterator;
	    int jaSize;
	    boolean positive;
        String literal;
              
		 while (iterator.hasNext()) {
			 ja = (JSONArray) iterator.next();
			 
			 jaSize = ja.size();
			 
			 if (jaSize>0) {
				 HornClause sc = new HornClause();
				 positive=false;
				 jaiterator = ja.iterator();
				
				 while(jaiterator.hasNext()) {
					 literal = (String) jaiterator.next();
					 
					 if ("!".equals(literal.substring(0,1))) {
						 sc.getLiterals().add(new Literal(literal.substring(1),false));
					 } else if (positive){
						 throw new Exception("KB contains an invalid HornClause!");
					 } else {
						 positive = true;
						 sc.setHead(new Literal(literal,false));
					 }
				 }
				 
				 if (!positive)
					 throw new Exception("KB contains an integrity constraint!");
				 
				 ret.getCNFClauses().add(sc);
			 } 
		 }
		 
		return ret;
		
	}
	
}

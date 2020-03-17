import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CNFLoader {
	String filename;
	
    public CNFLoader(String f) {	filename = f;	}
    
	public KB<CNFClause> loadFile() throws FileNotFoundException, IOException, ParseException
	{
		KB<CNFClause> ret = new KB<CNFClause>();
		
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader(filename);
		Object obj = parser.parse(reader);
		JSONArray json = (JSONArray) obj; 
		Iterator<JSONArray> iterator = json.iterator();
		
		JSONArray ja;
	    Iterator <String> jaiterator;
	    int jaSize;
        String literal;
              
		 while (iterator.hasNext()) {
			 ja = iterator.next();
			 
			 jaSize = ja.size();
			 
			 if (jaSize>0) {
				 CNFClause sc = new CNFClause();
				 
				 jaiterator = ja.iterator();
				
				 while(jaiterator.hasNext()) {
					 literal = jaiterator.next();
					 
					 if ("!".equals(literal.substring(0,1))) {
						 sc.getLiterals().add(new Literal(literal.substring(1),true));
					 } else {
						 sc.getLiterals().add(new Literal(literal,false));
					 }
				 }
				 ret.getCNFClauses().add(sc);
			 } 
		 }
		 
		return ret;
		
	}
	
}

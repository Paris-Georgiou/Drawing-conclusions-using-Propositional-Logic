import java.nio.file.Files;
import java.nio.file.Paths;

public class LiteralLoader {
	String filename;
	
    public LiteralLoader(String f) {	filename = f;	}
    
	public Literal loadFile() throws Exception
	{
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		 
		return new Literal(content, false);
	}
	
}

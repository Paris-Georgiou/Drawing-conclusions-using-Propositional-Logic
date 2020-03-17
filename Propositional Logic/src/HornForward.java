import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

public class HornForward {
	private KB<HornClause> kb;
	private Literal pr;	
	private HashMap<HornClause, Integer> cnt = new HashMap<HornClause, Integer>();
	private HashMap<Literal, Boolean> inferred = new HashMap<Literal, Boolean>();
	private Stack<Literal> agenda = new Stack<Literal>();

	public HornForward (KB<HornClause> k, Literal p) {
		kb=k;
		pr=p;
		
		HornClause hornClause;
		int size;
		for (int i=0; i < kb.getCNFClauses().size(); i++) {
			hornClause = kb.getCNFClauses().get(i);
			size = hornClause.getLiterals().size();
			cnt.put(hornClause, size);
			if (0 == size) {
				agenda.push(hornClause.getHead());
			}
			inferred.put(hornClause.getHead(), false);
			Iterator<Literal> iter = hornClause.getLiteralsList();
			while(iter.hasNext())
				inferred.put(iter.next(), false);
		}
	}
	
	public boolean prove()
	{
		int cntVal;
		HornClause hornClause;
		Literal head;
		while (agenda.size() > 0) {
			Literal l = agenda.pop();
			System.out.println("- Examining fact: ");
			l.print();
			while (!inferred.get(l)) {
				inferred.put(l, true);

				for (int i = 0; i < kb.getCNFClauses().size(); i++) {
					hornClause = kb.getCNFClauses().get(i);
					System.out.println("| - Examining clause:");
					hornClause.print();
					if (hornClause.getLiterals().contains(l)) {
						cntVal = cnt.get(hornClause);
						cnt.put(hornClause, --cntVal);
						
						if (0 == cntVal) {
							head = hornClause.getHead();
							if (head.equals(pr)) {
								return true;
							} else {
								System.out.println("| | - Pushing into agenda: ");
								head.print();
								agenda.push(head);
							}
						}
					}
				}
			}
		}
		return false;
	}
	
}

import indexation.*;
import core.*;


public class MainIndexation{
	public static void main(String[] args){
		Index i = new Index("index", new ParserCISI(), new Stemmer());
		i.index("cisi.txt");
	}
}
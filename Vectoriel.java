import indexation.*;
import core.*;
import java.util.*;

public abstract class Vectoriel extends IRModel{
	protected Weighter w;
	
	public Vectoriel(Index i, Weighter w){
		super(i);
		this.w=w;
	}
}
import indexation.*;
import core.*;
import java.util.*;

public abstract class IRModel{
	protected Index index;
	
	public IRModel(Index i){
		index=i;
	}

	public LinkedHashMap<String,Double> runModel(String query){
		HashMap<String,Integer> queryProcessed = getQueryProcessed(query);
		HashMap<String, Double> docScrores = getDocScores(queryProcessed);
		return getRanking(docScrores);
	}

	public LinkedHashMap<String,Double> getRanking(HashMap<String,Double> docScores){
		/* renvoie un hashmap trier en fonction de leur score */
		List<Map.Entry<String,Double>> entries = new ArrayList<Map.Entry<String,Double>>(docScores.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Double>>(){
			public int compare(Map.Entry<String, Double> a,Map.Entry<String,Double> b){
				return b.getValue().compareTo(a.getValue());
			}
		});
		LinkedHashMap<String, Double> ret = new LinkedHashMap<String, Double>();
		for(Map.Entry<String, Double> entry : entries){
			ret.put(entry.getKey(), entry.getValue());
		}
		return ret;
	}

	public HashMap<String, Integer> getQueryProcessed(String query){
		/* fait l'indexation sur la requete*/
		TextRepresenter textRep=index.getTextRepresenter();
		return textRep.getTextRepresentation(query);
	}

	public abstract HashMap<String,Double> getDocScores(HashMap<String, Integer> queryProcessed);
}
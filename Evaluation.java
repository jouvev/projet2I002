import indexation.*;
import core.*;
import java.util.*;

public class Evaluation{
	Index index;
	Resultats resultats;
	Requetes requetes;

	public Evaluation(Index i, Requetes req, Resultats res){
		index=i;
		requetes=req;
		resultats=res;
	}

	public HashSet<String> intersection(HashSet<String> setA, HashSet<String> setB){
		HashSet<String> res = new HashSet<String> ();
		for(String s : setA){
			if(setB.contains(s))
				res.add(s);
		}
		return res;
	}

	public Double getPrecision(String idQuery,int N,IRModel mod){
		String query = requetes.getRequete(idQuery);
		LinkedHashMap<String, Double> docs=mod.runModel(query);
		HashSet<String> NDocs = new HashSet<String>();
		int nb=0;
		for(String doc: docs.keySet()){
			if(nb==N)
				break;
			NDocs.add(doc);
			nb++;
		}
		return (intersection(resultats.getDocsAttendus(idQuery),NDocs).size()*1.0)/NDocs.size();
	}

	public Double getRappel(String idQuery,int N,IRModel mod){
		String query = requetes.getRequete(idQuery);
		LinkedHashMap<String, Double> docs=mod.runModel(query);
		HashSet<String> NDocs = new HashSet<String>();
		int nb=0;
		for(String doc: docs.keySet()){
			if(nb==N)
				break;
			NDocs.add(doc);
			nb++;
		}
		return (intersection(resultats.getDocsAttendus(idQuery),NDocs).size()*1.0)/resultats.getDocsAttendus(idQuery).size();
	}

	public Double getFMesure(String idQuery,int N,IRModel mod){
		Double rappel=getRappel(idQuery,N,mod);
		Double precision = getPrecision(idQuery,N,mod);
		return (2*(rappel*precision))/(rappel+precision);
	}	
}
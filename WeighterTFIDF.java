import indexation.*;
import core.*;
import java.util.*;

public class WeighterTFIDF extends Weighter{
	
	public WeighterTFIDF(Index i){
		super(i);
	}

	public HashMap<String, Double> getVecteurPoids(HashMap <String, Integer> listeMots){
		/* renvoie le vecteur poid(TFIDF) doc à partir d'une liste mots qui peut etre la liste de
		* mots d'un doc ou d'une requete.
		*/
		HashMap <String,Double> res = new HashMap <String,Double>();
		int N=index.getDocs().size();
		int nbMots=0;

		//on compte le nb de mots
		for(Map.Entry<String,Integer> entry  : listeMots.entrySet()){
			nbMots += entry.getValue();
		}

		//on construit le vecteur poid
		for(Map.Entry<String,Integer> entry  : listeMots.entrySet()){
			Double poids = (entry.getValue()*1.0)/nbMots;
			poids*=Math.log((N*1.0)/index.getTfsForStem(entry.getKey()).size());
			res.put(entry.getKey(),poids);
		}
		return res;
	}
}
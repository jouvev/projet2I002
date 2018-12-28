import indexation.*;
import core.*;
import java.util.*;

public class VectorielCart extends Vectoriel{

	public VectorielCart(Index i, Weighter w){
		super(i,w);
	}

	public HashMap<String,Double> getDocScores(HashMap<String, Integer> queryProcessed){
		HashMap<String,Double> res = new HashMap<String,Double>();
		
		//on recupere le vecteur poid de la requete 
		HashMap<String,Double> poidsQuery = w.getVecteurPoids(queryProcessed);

		//on parcour tous les doc pour faire leur scores
		for(String doc : index.getDocs().keySet()){
			Double score=0.0;
			
			//pour chaque mot de la requete on calcul la somme des scores
			for(Map.Entry<String,Double> entry : poidsQuery.entrySet()){
				//calcul du score de doc
				score += entry.getValue()*w.getPoidsMotDoc(entry.getKey(),doc);
			}

			//on l'ajoute au res si son score n'est pas nul 
			if(score!=0.0){
				res.put(doc,score);
			}
		}
		return res;
	}
}
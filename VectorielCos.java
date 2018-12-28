import indexation.*;
import core.*;
import java.util.*;

public class VectorielCos extends Vectoriel{

	public VectorielCos(Index i, Weighter w){
		super(i,w);
	}

	public HashMap<String,Double> getDocScores(HashMap<String, Integer> queryProcessed){
		HashMap<String,Double> res = new HashMap<String,Double>();

		//on recupere le score cartesien
		VectorielCart vCart = new VectorielCart(index,w);
		HashMap<String,Double> resVCart = vCart.getDocScores(queryProcessed);

		//on recupere le vecteur poid de la requete 
		HashMap<String,Double> poidsQuery = w.getVecteurPoids(queryProcessed);
		

		//calcul somme i appartenant à q 
		Double sommeq=0.0;
		for(Map.Entry<String,Double> entry : poidsQuery.entrySet()){
			sommeq+=Math.pow(entry.getValue(),2);
		}

		//on parcour tous les doc pour faire leur scores
		for(String doc : index.getDocs().keySet()){
			Double score=0.0;
			Double sommeqd=0.0;
			Double sommed=0.0;
			
			//calcul somme i appartenant à d
			for(String mot : index.getTfsForDoc(doc).keySet()){
				sommed += w.getPoidsMotDoc(mot,doc);
			}

			sommeqd = resVCart.getOrDefault(doc,0.0);

			//calcul score
			score = sommeqd/(Math.sqrt(sommeq)*Math.sqrt(sommed));

			//on l'ajoute au res si son score n'est pas nul 
			if(score!=0.0){//a cause de la division par 0
				res.put(doc,score);
			}
		}
		return res;
	}
}
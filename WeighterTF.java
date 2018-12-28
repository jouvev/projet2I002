import indexation.*;
import core.*;
import java.util.*;

public class WeighterTF  extends Weighter{

	public WeighterTF(Index i){
		super(i);
	}

	public HashMap<String, Double> getVecteurPoids(HashMap <String, Integer> listeMots){
		/* renvoie le vecteur poid(TF) doc à partir d'une liste mots qui peut etre la liste de
		* mots d'un doc ou d'une requete.
		*/
		
		HashMap <String,Double> res = new HashMap <String,Double>();
		int nbMots=0;

		//on compte le nb de mots
		for(Map.Entry<String,Integer> entry  : listeMots.entrySet()){
			nbMots += entry.getValue();
		}

		//on construit le vecteur poid
		for(Map.Entry<String,Integer> entry  : listeMots.entrySet()){
			res.put(entry.getKey(),(entry.getValue()*1.0)/nbMots);
		}
		return res;
	}
}
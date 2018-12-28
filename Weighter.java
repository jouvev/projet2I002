import indexation.*;
import core.*;
import java.util.*;

public abstract class Weighter{
	protected Index index;
	//associe le vecteur poid des mots dans le doc(evite de le calculer plusieurs fois)
	protected HashMap<String,HashMap <String,Double>> vecteurPoidsDoc; 

	public Weighter(Index i){
		index=i;
		vecteurPoidsDoc = new HashMap<String,HashMap <String,Double>>();
		//on calcul les vecteurs poids pour chaque doc de l'index et on l'enregistre dans vecteurPoidDoc
		for(String doc : index.getDocs().keySet()){
			vecteurPoidsDoc.put(doc,getVecteurPoids(index.getTfsForDoc(doc)));
		}
	}

	public abstract HashMap<String, Double> getVecteurPoids(HashMap <String, Integer> listeMots);
		
	public Double getPoidsMotDoc(String mot,String doc){
		/* renvoie la frequence de mot dans le doc 
		*/
		HashMap<String, Double> vPoid;
		vPoid = vecteurPoidsDoc.get(doc);
		if(vPoid==null)
			return 0.0;
		return vPoid.getOrDefault(mot,0.0);
	}
}
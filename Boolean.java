import indexation.*;
import core.*;
import java.util.*;

public class Boolean extends IRModel{
	
	public Boolean(Index i){
		super(i);
	}

	public HashMap<String,Double> getDocScores(HashMap<String, Integer> queryProcessed){
		/*renvoie les documents avec un score associer sur le model booleen c'est a dire,
		 *soit tous les mots y sont est sont score et 1 sinon 0
		 */
		HashMap<String,Double> res=new HashMap<String,Double>();

		//pour chaques mots de la requete on regarde dans quel document ils sont tous present
		for ( String doc : index.getDocs().keySet())
		{
			boolean presenceMots= true;
			HashMap<String,Integer> listeMotDoc=index.getTfsForDoc(doc);
			
			for (String mot: queryProcessed.keySet())
			{
				if(!(listeMotDoc.containsKey(mot)))
				{
					//si on ne trouve pas un mot 
					presenceMots=false;
					break;
				}
				
			}

			if(presenceMots)//s'ils sont tous prensent on l'ajoute a l'hashmap qu'on renverra 
			{
				res.put(doc,1.0);
			}

		}
		return res;
	}
}
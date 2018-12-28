import indexation.*;
import core.*;
import java.util.*;

public class TestEvaluation{
	public static void main(String[] args){
		Index i = Index.deserialize("index");
		WeighterTF w = new WeighterTF(i);
		
		IRModel mod=new VectorielCart(i, w);
		Requetes req = new Requetes("cisi.qry");
		Resultats res = new Resultats("cisi.rel");
		Evaluation eval = new Evaluation(i,req,res);

		String idrequete;
		Scanner sc = new Scanner(System.in);
		do{
			do{
				System.out.println("Tapez l'id de la requete a tester (/all pour toutes les requetes /fin pour quitter) :");
				idrequete = sc.nextLine();
			}while(!req.exists(idrequete) && idrequete.compareTo("/all")!=0 && idrequete.compareTo("/fin")!=0);

			if(idrequete.compareTo("/all")==0){
				for(String requete : req.getRequetes().keySet()){
					System.out.println("idrequete = "+requete+"\n");
					System.out.println("p="+eval.getPrecision(requete,10,mod)+"\nr="+eval.getRappel(requete,10,mod)+"\nfmesure="+eval.getFMesure(requete,10,mod)+"\n");
				}
			}else if(idrequete.compareTo("/fin")!=0){
				System.out.println("idrequete = "+idrequete+"\n");
				System.out.println("p="+eval.getPrecision(idrequete,10,mod)+"\nr="+eval.getRappel(idrequete,10,mod)+"\nfmesure="+eval.getFMesure(idrequete,10,mod)+"\n");
			}
		}while(idrequete.compareTo("/fin")!=0);
	}
}
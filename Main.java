import indexation.*;
import core.*;
import java.util.*;
import java.io.File;

public class Main{
	public static final int N=10;
	public static void main(String[] args){
		File f = new File("index");
		Index i;

		//on verifie si la sauvegarde existe si oui on la charge sinon on creer l'index
		if(f.exists()){
			i = Index.deserialize("index");
		}
		else{
			i = new Index("index", new ParserCISI(), new Stemmer());
			i.index("cisi.txt");
		}
		
		WeighterTF w = new WeighterTF(i);
		IRModel model=new VectorielCos(i, w);
		LinkedHashMap<String,Double> res;


		String requete;
		Scanner sc = new Scanner(System.in);

		do{
			System.out.println("Taper votre requete (/fin pour quitter) :");
			requete = sc.nextLine();
			res=model.runModel(requete);
			int nb=0;
			for(String doc : res.keySet()){
				if(nb==N)
					break;
				try{
					System.out.println("---------------------------------------");
					System.out.println(i.getStrDoc(doc)+"\n");
				}
				catch(Exception e){
					System.out.println("erreur");
					break;
				}
				nb++;
			}
		}while(requete.compareTo("/fin")!=0);
	}
}
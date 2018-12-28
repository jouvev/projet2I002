import java.util.*;
import java.io.*;

public class Resultats{
	private HashMap<String,HashSet<String>> resultats;

	public Resultats(String filename){
		resultats = new HashMap<String,HashSet<String>>();
		
		DataInputStream isStream = null;
		try{
			isStream = new DataInputStream(new FileInputStream(new File(filename)));

			HashSet<String> idDocsAttendus;
			String idQuery;
			String line =isStream.readLine();
			String[] tabLine;
			
			while(line!=null){
				tabLine = line.replaceAll("\t"," ").replaceAll("( ){2,}"," ").trim().split(" ");
				idQuery=tabLine[0];
				idDocsAttendus = new HashSet<String>();
				while(line!=null && tabLine[0].compareTo(idQuery)==0){
					idDocsAttendus.add(tabLine[1]);
					line =isStream.readLine();
					if(line!=null)
						tabLine = line.replaceAll("\t"," ").replaceAll("( ){2,}"," ").trim().split(" ");
				}
				resultats.put(idQuery,idDocsAttendus);
			}

		}catch(IOException e){
			System.out.println("erreur overture fichier");
		}finally{
			try{
				if(isStream!=null)
				isStream.close();
			}
			catch(IOException e){
				System.out.println("erreur fermeture fichier");
			}
		} 
	}

	public HashSet<String> getDocsAttendus(String idQuery){
		return resultats.getOrDefault(idQuery, new HashSet<String> ());
	}
}
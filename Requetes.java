import java.util.*;
import java.io.*;

public class Requetes{

	private HashMap<String,String> requetes;
	
	public Requetes(String filename){
		DataInputStream isStream = null;

		requetes = new HashMap<String,String>();
		
		try{
			isStream = new DataInputStream(new FileInputStream(new File(filename)));
			
			String line=isStream.readLine();
			
			while(line != null){
				if(line != null && line.startsWith(".I")){
					String id = line.substring(3);
					String requete ="";
					while(line!=null && !(line.startsWith(".W"))){//on cherche le champ .w
						line=isStream.readLine();
					}
					
					if(line!=null)
						line=isStream.readLine();//on pass le .W
					
					while(line!=null && !(line.startsWith("."))){
						requete+=line+"\n";
						line=isStream.readLine();
					}
					requetes.put(id,requete.trim());
				}else{
					line=isStream.readLine();
				}
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

	public String getRequete(String id){
		return requetes.getOrDefault(id,"");
	}

	public HashMap<String,String> getRequetes(){
		return requetes;
	}

	public boolean exists(String id){
		if(requetes.get(id)!=null){
			return true;
		}
		return false;
	}
}
package calssdep;
import java.util.ArrayList;

public class Profil {
	private String nomProfil;
	private ArrayList<Champ> schema = new ArrayList<Champ>(); 
	
	public Profil(String nomProfil) {
		// TODO Auto-generated constructor stub
		this.nomProfil = nomProfil;
	}
	
	/**
	 * obtenir le nomProfil
	 * @return le nomProfil 
	 */
	public String getNomProfil() {
		return this.nomProfil;
	}
	/**
	 * obtenir le schema
	 * @return le shema de la class Profil 
	 */
	public ArrayList<Champ> getSchema() {
		return this.schema;
	}
	/**
	 * ajouter un champ dans le schema
	 * @param champ
	 */
	public void ajouterChamp(Champ champ) {
		this.schema.add(champ);
	}
	
	/**
	 * supprimer un champ dans le schema
	 * @param champ le champs qu'on veut supprimer
	 */
	public void supprimeCham(String champ) {
		//ArrayList<Champ> newChamp = new CopyOnWriteArrayList<Champ>(this.schema);
		/*
		for (int i = 0; i < newChamp.size(); i++) {
			if (champ.equals(newChamp[i].getAttribut());
		}*/
		ArrayList<Champ> newChamp = new ArrayList<Champ>(this.schema);
		for (Champ item : newChamp) {
            if (item.getAttribut().equals(champ)) {
                newChamp.remove(item);
            }
        }
		this.schema = newChamp;
	}
	

}

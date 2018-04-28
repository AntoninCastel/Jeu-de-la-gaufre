package Modele;

import java.util.Arrays;

public class Plateau {
	 int [][] tab;
	int compteurCoups,
		hauteur,
		largeur;

	public Plateau() {
		this(10,10, 1);
	}

	public Plateau(int hauteur, int largeur) {
		this(hauteur, largeur, 1);
	}

	public Plateau(int hauteur, int largeur, int compteurCoups) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.compteurCoups = compteurCoups;
		this.tab = initTab(largeur, hauteur);
	}

	private int[][] initTab(int largeur, int hauteur) {
		int[][] tab = new int[hauteur][largeur];
		for (int[] e : tab) {
			Arrays.fill(e, 0);
		}
		return tab;
	}
	/**
	 * estMangeable : Si une case est mangeable.
	 *  @param coord : coordonnées de la case.
	 *  @return : booléen vrai si la case est mangeable, faux sinon.
	 */
	public boolean estMangeable(Couple coord) {
		return estSurPlateau(coord) && !estMangee(coord);
	}

	/**
	 * estSurPlateau : Si une case est sur le plateau.
	 * @param coord : coordonée de la case.
	 * @return : booléen vrai si la case est dans le plateau, faux sinon.
	 */
	public boolean estSurPlateau(Couple coord) {
		return 0 <= coord.i && coord.i < hauteur
				&& 0 <= coord.j && coord.j < largeur;
	}

	/** FIXME : possible redondance avec estMangeable
	 * estMangee : Si une case a déjà été mangée ou non
	 * @param coord : coordonnée de la case
	 * @return : vrai si la case a été mangée faux sinon
	 */
	public boolean estMangee(Couple coord) {
		return tab[coord.i][coord.j] != 0;
	}

	/**
	 * manger : mange les cases en fonction de la position donnée.
	 * @param coord : coordonnées de la case.
	 * @return bolléen vrai si une case au moins une case à été mangée, faux sinon.
	 */
	public boolean manger(Couple coord) {
		int tmp = compteurCoups;
		if(estMangeable(coord)) {
			// mange toutes les cases en dessous et à droite de la position donnée
			for(int i = coord.i; i < hauteur; i++) {
				for (int j = coord.j; j < largeur; j++) {
					if (!estMangee(new Couple(i,j))) {
						tab[i][j] = compteurCoups;
					}
				}
			}
			compteurCoups++;
		}
		return tmp == compteurCoups;
	}

	/**
	 * setCase : affecte une valeur a la case donnée
	 * @param coord : les coordonnées de la case dans le tableau
	 * @param value : la valeur à affecter
	 */
	public void setCase(Couple coord, int value) {
		this.tab[coord.i][coord.j] = value;
	}

	public int hauteur() {
		return hauteur;
	}

	public int largeur() {
		return largeur;
	}

	public int[][] getTab() {
		return tab;
	}

	public int getCompteurCoups() {
		return compteurCoups;
	}

	@Override
	public String toString() {
		String res = "[\n";
		for (int[] e : tab) {
			res += Arrays.toString(e) + "\n";
		}
		return res +"," + largeur +","+ hauteur + "]";
	}
}

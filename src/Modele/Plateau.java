package Modele;

import java.util.Arrays;
import java.util.LinkedList;

public class Plateau {
	int [][] tab;
	int compteurCoups,
		hauteur,
		largeur;
	LinkedList<Couple> history;

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
		this.history = new LinkedList<>();
	}

	public Plateau(int hauteur, int largeur, int [][] tab) {
		this(hauteur, largeur, 1, tab);
	}

	public Plateau(int hauteur, int largeur, int compteurCoups, int [][] tab) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.tab = tab;
		this.compteurCoups = compteurCoups;
		this.history = new LinkedList<>();
	}

	public Plateau(int hauteur, int largeur, int [][] tab) {
		this(hauteur, largeur, 1, tab);
	}

	public Plateau(int hauteur, int largeur, int compteurCoups, int [][] tab) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.tab = tab;
		this.compteurCoups = compteurCoups;
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

	/**
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
		return tmp != compteurCoups;
	}

	/**
	 * toBinary : traduit le tableau en un vecteur de bit représentant la frontière entre
	 * la partie mangée de la gauffre et la partie restante.
	 * @return : le vecteur de bit avec 1 signifie que la frontière remonte
	 * et zéro qu'elle continue vers la droite.
	 */
	public int toBinary() {
		int res = 0b0;
		int i = this.hauteur -1,
			j = 0;
		while(i >= 0 || j < this.largeur - 1) {
			res = res << 1;
			if(j == this.largeur - 1 || this.tab[i][j] != 0) {
				res = res | 1;
				i--;
			}
			else {
				j++;
			}
		}
		return res;
	}

	/**
	 * undo : annule la dernière action.
	 */
	public void undo() {
		Couple pos = new Couple(this.hauteur,this.largeur);
		if(this.compteurCoups > 1) {
			for (int i = 0; i < this.hauteur; i++) {
				for (int j = 0; j < this.largeur; j++) {
					if (this.tab[i][j] == (this.compteurCoups - 1)) {
						if(pos.i > i || pos.j > j) {
							pos = new Couple(i, j);
						}
						setCase(new Couple(i, j), 0);
					}
				}
			}
			history.add(pos);
			this.compteurCoups--;
		}
	}

	public void redo() {
		if(!this.history.isEmpty())
			manger(this.history.removeLast());
	}

	/**
	 * estPoison
	 * @param coord : les coordonnées de la case
	 * @return : si la case est le poison ou non
	 */
	public boolean estPoison(Couple coord) {
		return coord.i == 0 && coord.j == 0;
	}

	/**
	 * getCasePoison
	 * @return le couple de coordonnées la case poison
	 */
	public Couple getCasePoison() {
		return new Couple(0,0);
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

	public Plateau clone() {
		return new Plateau(this.hauteur(), this.largeur(), this.getCompteurCoups(), this.getTab());
	}

	@Override
	public boolean equals(Object obj) {
		boolean b = ((Plateau) obj).hauteur() == this.hauteur
					&& ((Plateau) obj).largeur() == this.largeur
					&& ((Plateau) obj).getCompteurCoups() == this.compteurCoups;
		if (b)
			for (int i = 0; i < this.hauteur; i++)
				b = b && Arrays.equals(((Plateau) obj).getTab()[i], this.tab[i]);
		return b;
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

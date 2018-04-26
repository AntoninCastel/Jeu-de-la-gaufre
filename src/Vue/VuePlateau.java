package Vue;

import java.awt.Point;

import Controleur.Utilisateur.ClicVuePlateau;
import Plateau.Plateau;

public class VuePlateau extends Cadre {
	Plateau plateau;
	
	public int tailleCase = 20;
	public Point origine;
		
	private void Initialisation() {
		plateau = new Plateau(8,6);
		
		tailleCase = (int) (this.getHeight()*0.9/plateau.getHeight());
		origine = new Point((int) (this.getWidth()/2-(tailleCase*plateau.getWidth()/2)),(int) (this.getHeight()/2-(tailleCase*plateau.getHeight()/2)));
		
		this.setOnMousePressed(new ClicVuePlateau(this, plateau));
	}
	
	VuePlateau(){
		super();
		Initialisation();
	}
	VuePlateau(int wpref,int hpref){
		super(wpref,hpref);
		Initialisation();
	}
	
	void Draw() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
		origine = new Point((int) (this.getWidth()/2-(tailleCase*plateau.getWidth()/2)),(int) (this.getHeight()/2-(tailleCase*plateau.getHeight()/2)));
		tailleCase = (int) (this.getHeight()*0.9/plateau.getHeight());
		for(int i=0;i<plateau.getHeight();i++) {
			for(int j=0;j<plateau.getWidth();j++) {
				gc.strokeRect(origine.x+j*tailleCase, origine.y+i*tailleCase, tailleCase, tailleCase);
				if(plateau.getCase(i, j)) {
					gc.fillRect(origine.x+j*tailleCase, origine.y+i*tailleCase, tailleCase, tailleCase);
				}
			}
		}
	}
}

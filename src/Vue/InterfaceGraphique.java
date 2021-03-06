package Vue;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceGraphique extends Application {
	PanePrincipal gp;
	
	public enum Appli_state {
		MENU(0), NEW_GAME(1);
		
		public int value;
		
		Appli_state(int value) {
			this.value = value;
		}
	}
	
	final static float dt = 0.0166f; //temps entre 2 frames en s (60 fps)
	static public Appli_state etat = Appli_state.MENU;
	static public Stage primaryStage;
	static public PaneMenu m ;//= new PaneMenu(1000, 800);
	
	public static void creer(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		gp = new PanePrincipal();
		m = new PaneMenu(1000, 800);
        Scene menu = new Scene(m, 1000, 800);
        primaryStage.setScene(menu);  
		primaryStage.show();
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(600);
		
		InterfaceGraphique.primaryStage = primaryStage;
		
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				switch(etat) {
				case MENU:
					switch(m.Choix()) {
					case NEW_GAME:
						gp = new PanePrincipal(m.height, m.width,m.get_css());
						gp.moteur.remplacerJoueur(m.joueur1, m.joueur2);
						Scene partie = new Scene(gp, m.getWidth(), m.getHeight());
						primaryStage.setScene(partie);
						etat = Appli_state.NEW_GAME;
						return;
					case QUIT:
						// On ferme l'appli
						primaryStage.close();
						break;
					case NOTHING:
						// On ne fait rien
						break;
					}
					break;
				case NEW_GAME:
					// On joue la partie comme avant
					gp.Update();
					gp.Draw();
					break;
				}
				
			}
		}.start();
	}
}

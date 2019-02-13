# La-gaufre
Jeu de la gaufre fonctionnel

# Description du jeu
Le jeu de la gaufre est un jeu simple.  Il se joue contre un seul adversaire.  
Vous avez une gaufre, dont le coin supérieur gauche est empoisonné (coordonée **0**;**0**), le but du jeu est de ne pas devoir manger cette case empoisonnée.  
Quand on commence, la gaufre est un rectangle de taille **n**x**m**.  
A tour de rôle, vous et votre adversaire allez manger une partie de la gaufre.  
On mange une partie de la gaufre en donnant une coordonnée (**x**,**y**).  
Quand ça arrive, le rectangle (**x**;**y**);(**n-1**;**m-1**) est considéré comme mangé.

# Détails techniques

Créé en Java 8, et javaFX.  
Modèle Modèle-Vue-Controleur  
L'IA fonctionne avec un algorithme MinMax qui calcule tout les coups possibles jusqu'à la résolution du jeu (si c'est possible).  
Si c'est possible, alors on prend une décision en fonction de la probabilité de gagner grâce à ce coup.  
Si ce n'est pas possible,on calcule un nombre de coup à l'avance donné, et alors on juge la qualité d'un coup grâce à des heuristiques données, et on prend une décision en fonction de ces dernières.  

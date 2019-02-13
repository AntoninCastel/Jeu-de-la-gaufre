# La-gaufre
Jeu de la gaufre fonctionnel

# Description du jeu
Le jeu de la gaufre est un jeu simple.  Il se joue contre un seul adversaire.  
Vous avez une gaufre, dont le coin supérieur gauche est empoisonné (coordonée **0**;**0**), le but du jeu est de ne pas devoir manger cette case empoisonnée.  
Quand on commence, la gaufre est un rectangle de taille **n**x**m**.  
A tour de rôle, vous et votre adversaire allez manger une partie de la gaufre.  
On mange une partie de la gaufre en donnant une coordonnée (**x**,**y**).  
Quand ça arrive, le rectangle inférieur droit commencant aux coordonnées (**x**;**y**) est considéré comme mangé et disparait.  

# Détails techniques

Créé en Java 8, et javaFX.  
Modèle Modèle-Vue-Controleur.  
L'IA fonctionne avec un algorithme MinMax qui calcule toutes les configurations possibles jusqu'à la résolution du jeu (si c'est possible, donc si ca ne demande pas trop de calcul).  
Si c'est possible, alors l'IA joue un coup en fonction de la probabilité de gagner grâce à ce coup (le coup qui débouche sur le plus de configurations gagnantes).  
Si ce n'est pas possible, l'IA calcule un nombre de coup à l'avance donné pour que le temps de calcul ne soit pas trop long, et alors on juge la qualité d'un coup grâce à des heuristiques données, et on prend une décision en fonction de ces dernières.  
Il existe des façons très simples de juger une configuration non terminale.  
Certaines d'entres elles ne peuvent déboucher que sur une défaite ou une victoire pour un des joueurs.  
On utilise ces faits pour calculer des heuristiques sur les configurations non terminales.

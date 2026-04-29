# Jeu-Du-Taquin
Projet de groupe en Java (L2 informatique) consistant à réaliser le jeu du taquin avec une architecture MVC (Modèle–Vue–Contrôleur) et une interface graphique en Java Swing.

Le joueur doit réorganiser les cases mélangées afin de retrouver l’ordre correct des tuiles.

---

## Fonctionnalités

* Interface graphique en Java Swing
* Version console (mode texte)
* Architecture MVC propre et modulaire
* Déplacement des tuiles avec clic ou commandes clavier
* Génération aléatoire de grilles solvables
* Détection de victoire
* Séparation des responsabilités entre logique et affichage

---

## Technologies

* Java
* Java Swing
* Architecture MVC
* Programmation orientée objet

---

## Mon rôle dans le projet

Dans ce projet en équipe, je me suis principalement occupée de :

### Vue Graphique (VueGraphique)

* Affichage de la grille en Swing
* Gestion des événements utilisateur (clics, interactions)
* Mise à jour dynamique de l’interface

### Vue Console (VueConsole)

* Affichage du jeu en mode terminal
* Interaction textuelle avec le joueur
* Debug et suivi des états du jeu

---
### Aperçu

<img width="464" height="539" alt="Capture d’écran du 2026-04-27 21-38-31" src="https://github.com/user-attachments/assets/fe17843e-6dca-4fff-b440-408ee225cf1f" />

---
## Lancer le projet

```bash
git clone https://github.com/LynaIftene6/Jeu-Du-Taquin.git
cd Jeu-Du-Taquin
javac -d build src/controleur/*.java src/vue/*.java src/Main.java src/TestGrille.java
java -cp build Main
```

---

## Ce que j’ai appris

* Mise en pratique du modèle MVC
* Gestion d’interfaces graphiques avec Java Swing
* Communication entre composants (Vue / Contrôleur / Modèle)
* Travail en équipe et répartition des responsabilités
* Amélioration de la structuration du code en projet Java

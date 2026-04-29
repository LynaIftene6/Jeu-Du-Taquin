package modele;

import java.util.Random;

/**
 * Modèle représentant la grille du jeu de Taquin.
 * 
 * Cette classe stocke l'état courant du puzzle sous forme de matrice d'entiers,
 * mémorise la position de la case vide, compte le nombre de coups joués
 * et notifie les écouteurs après chaque modification significative.
 */
public class Grille extends AbstractModeleEcoutable {

    /** Contenu des cases de la grille. */
    private int[][] cases;

    /** Indice de ligne de la case vide. */
    private int ligneVide;

    /** Indice de colonne de la case vide. */
    private int colonneVide;

    /** Nombre de lignes de la grille. */
    private int nbLignes;

    /** Nombre de colonnes de la grille. */
    private int nbColonnes;

    /** Nombre de coups joués depuis le dernier mélange. */
    private int nbCoups;

    /**
     * Construit une grille initialement ordonnée.
     * 
     * Les cases sont remplies dans l'ordre croissant à partir de 1,
     * et la dernière case contient 0 pour représenter la case vide.
     * 
     * @param nbLignes le nombre de lignes
     * @param nbColonnes le nombre de colonnes
     */
    public Grille(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.cases = new int[nbLignes][nbColonnes];
        this.nbCoups = 0;

        int compteur = 1;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                // La dernière case reçoit 0 (case vide)
                if (i == nbLignes - 1 && j == nbColonnes - 1) {
                    cases[i][j] = 0;
                    ligneVide = i;
                    colonneVide = j;
                } else {
                    // Les autres cases reçoivent 1, 2, 3...
                    cases[i][j] = compteur;
                    compteur++;
                }
            }
        }
    }

    /**
     * Tente de déplacer une tuile vers la case vide.
     * 
     * Le déplacement est autorisé uniquement si la case indiquée
     * est adjacente à la case vide selon la distance de Manhattan.
     * En cas de succès, les deux cases sont échangées, la position
     * de la case vide est mise à jour, le compteur de coups est incrémenté
     * et les écouteurs sont notifiés.
     * 
     * @param ligne la ligne de la tuile à déplacer
     * @param colonne la colonne de la tuile à déplacer
     * @return true si le déplacement a été effectué, false sinon
     */
    public boolean deplacer(int ligne, int colonne) {
        if (Math.abs(ligne - ligneVide) + Math.abs(colonne - colonneVide) == 1) {
            int temp = cases[ligne][colonne];
            cases[ligne][colonne] = cases[ligneVide][colonneVide];
            cases[ligneVide][colonneVide] = temp;

            ligneVide = ligne;
            colonneVide = colonne;
            nbCoups++;

            fireChangement();
            return true;
        }

        return false;
    }

    /**
     * Mélange la grille en effectuant un certain nombre de déplacements aléatoires
     * à partir de la case vide.
     * 
     * Le compteur de coups est réinitialisé à zéro après le mélange,
     * puis les écouteurs sont notifiés une seule fois.
     * 
     * @param nbMouvements le nombre de déplacements aléatoires à effectuer
     */
    public void melanger(int nbMouvements) {
        Random rand = new Random();
        for (int k = 0; k < nbMouvements; k++) {
            int[][] voisins = {
                {ligneVide - 1, colonneVide},
                {ligneVide + 1, colonneVide},
                {ligneVide, colonneVide - 1},
                {ligneVide, colonneVide + 1}
            };

            int idx = rand.nextInt(4);
            int l = voisins[idx][0];
            int c = voisins[idx][1];

            if (l >= 0 && l < nbLignes && c >= 0 && c < nbColonnes) {
                int temp = cases[l][c];
                cases[l][c] = cases[ligneVide][colonneVide];
                cases[ligneVide][colonneVide] = temp;
                ligneVide = l;
                colonneVide = c;
            }
        }

        nbCoups = 0;
        fireChangement();
    }

    /**
     * Indique si la grille est dans l'état gagnant.
     * 
     * L'état gagnant correspond à un rangement croissant des tuiles,
     * avec la case vide en dernière position.
     * 
     * @return true si le puzzle est résolu, false sinon
     */
    public boolean estGagne() {
        int compteur = 1;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (i == nbLignes - 1 && j == nbColonnes - 1) {
                    return cases[i][j] == 0;
                }

                if (cases[i][j] != compteur) return false;
                compteur++;
            }
        }

        return true;
    }

    /**
     * Indique si la case donnée peut être déplacée vers la case vide.
     * 
     * Une case est déplaçable si elle est adjacente à la case vide
     * selon la distance de Manhattan.
     * 
     * @param ligne la ligne à tester
     * @param colonne la colonne à tester
     * @return true si la case est déplaçable, false sinon
     */
    public boolean estDeplacable(int ligne, int colonne) {
        return Math.abs(ligne - ligneVide) + Math.abs(colonne - colonneVide) == 1;
    }

    /**
     * Retourne le nombre de lignes de la grille.
     * 
     * @return le nombre de lignes
     */
    public int getNbLignes() {
        return nbLignes;
    }

    /**
     * Retourne le nombre de colonnes de la grille.
     * 
     * @return le nombre de colonnes
     */
    public int getNbColonnes() {
        return nbColonnes;
    }

    /**
     * Retourne la valeur contenue dans une case.
     * 
     * @param i l'indice de ligne
     * @param j l'indice de colonne
     * @return la valeur de la case
     */
    public int getCase(int i, int j) {
        return cases[i][j];
    }

    /**
     * Retourne la ligne de la case vide.
     * 
     * @return l'indice de ligne de la case vide
     */
    public int getLigneVide() {
        return ligneVide;
    }

    /**
     * Retourne la colonne de la case vide.
     * 
     * @return l'indice de colonne de la case vide
     */
    public int getColonneVide() {
        return colonneVide;
    }

    /**
     * Retourne le nombre de coups joués.
     * 
     * @return le nombre de coups
     */
    public int getNbCoups() {
        return nbCoups;
    }
}
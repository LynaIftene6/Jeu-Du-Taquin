package controleur;

import modele.Grille;

/**
 * Contrôleur graphique du jeu de Taquin.
 * 
 * Cette classe reçoit les actions de la vue graphique et les transmet
 * au modèle {@link Grille}. Elle valide les coordonnées avant de demander
 * le déplacement d'une tuile.
 */
public class ControleurGraphique {

    /** Modèle représentant la grille du jeu. */
    private final Grille grille;

    /**
     * Construit le contrôleur graphique associé à une grille.
     * 
     * @param grille le modèle du jeu
     */
    public ControleurGraphique(Grille grille) {
        this.grille = grille;
    }

    /**
     * Tente de déplacer la tuile située aux coordonnées indiquées.
     * 
     * Si les coordonnées sont hors des limites de la grille,
     * la demande est ignorée. Sinon, le déplacement est transmis
     * au modèle, qui notifie ensuite les vues si nécessaire.
     * 
     * @param ligne la ligne de la tuile à déplacer
     * @param colonne la colonne de la tuile à déplacer
     */
    public void deplacerTuile(int ligne, int colonne) {
        if (ligne < 0 || ligne >= grille.getNbLignes()) return;
        if (colonne < 0 || colonne >= grille.getNbColonnes()) return;
        grille.deplacer(ligne, colonne);
    }
}
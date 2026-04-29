package vue;

import modele.EcouteurModele;
import modele.Grille;

/**
 * Vue console du jeu de Taquin.
 * Implémente EcouteurModele : modeleMisAJour() est appelée automatiquement
 * par fireChangement() à chaque modification de la Grille.
 */
public class VueConsole implements EcouteurModele {

    /** Référence au modèle observé. */
    private final Grille grille;

    /**
     * Construit la vue console et s'abonne au modèle.
     *
     * @param grille le modèle du jeu
     */
    public VueConsole(Grille grille) {
        this.grille = grille;
        grille.ajoutEcouteur(this);
    }

    /**
     * Méthode de EcouteurModele.
     * Appelée automatiquement par grille.fireChangement().
     *
     * @param source le modèle qui a changé
     */
    @Override
    public void modeleMisAJour(Object source) {
        afficher();
    }

    /**
     * Affiche l'état courant de la grille dans le terminal.
     * La case vide (0) est représentée par des espaces.
     * Affiche le compteur de coups et les commandes ou le message de victoire.
     */
    public void afficher() {
        int lignes   = grille.getNbLignes();
        int colonnes = grille.getNbColonnes();

        StringBuilder sep = new StringBuilder("+");
        for (int c = 0; c < colonnes; c++) sep.append("----+");

        System.out.println(sep);
        for (int l = 0; l < lignes; l++) {
            StringBuilder ligne = new StringBuilder("|");
            for (int c = 0; c < colonnes; c++) {
                int val = grille.getCase(l, c);
                ligne.append(val == 0 ? "    |" : String.format(" %2d |", val));
            }
            System.out.println(ligne);
            System.out.println(sep);
        }

        System.out.println("Coups joues : " + grille.getNbCoups());
        if (grille.estGagne()) {
            System.out.println("=== Felicitations, puzzle resolu ! ===");
        } else {
            System.out.println("Z=haut  S=bas  Q=gauche  D=droite  |  q=quitter");
        }
        System.out.println();
    }

    /**
     * Affiche un message de bienvenue au lancement de la partie.
     */
    public void afficherBienvenue() {
        System.out.println("|  Bienvenue dans le Taquin ! |");
        System.out.println("Remettez les tuiles dans l'ordre croissant.");
        System.out.println();
    }

    /**
     * Affiche un message d'erreur pour un déplacement échoué.
     */
    public void afficherMouvementInvalide() {
        System.out.println("Mouvement invalide, reessayez.");
    }
}
package modele;

/**
 * Classe de test pour la classe {@code Grille}.
 * <p>
 * Cette classe permet de vérifier le bon fonctionnement des principales méthodes
 * de la grille du jeu de Taquin : création, affichage, déplacements,
 * mélange et détection de victoire.
 * </p>
 */

public class TestGrille {

    /**
     * Méthode principale exécutant les tests.
     * <p>
     * Les tests effectués sont :
     * <ul>
     *   <li>Création d'une grille 3x3</li>
     *   <li>Affichage de la grille initiale</li>
     *   <li>Vérification de l'état gagné</li>
     *   <li>Test d'un déplacement valide</li>
     *   <li>Test d'un déplacement invalide</li>
     *   <li>Mélange de la grille</li>
     * </ul>
     * Les résultats sont affichés dans la console.
     *
     * @param args les arguments de la ligne de commande (non utilisés)
     */

    public static void main(String[] args) {

        // Crée une grille 3x3
        Grille g = new Grille(3, 3);

        // Affiche la grille initiale
        System.out.println("=== Grille initiale ===");
        for (int i = 0; i < g.getNbLignes(); i++) {
            for (int j = 0; j < g.getNbColonnes(); j++) {
                System.out.print(g.getCase(i, j) + " ");
            }
            System.out.println();
        }

        // Test estGagne() → doit afficher true
        System.out.println("Gagnée ? " + g.estGagne());

        // Test déplacement valide
        System.out.println("Déplacer (2,1) : " + g.deplacer(2, 1));
        System.out.println("Nb coups : " + g.getNbCoups());
        System.out.println("Gagnée ? " + g.estGagne());

        // Test déplacement invalide
        System.out.println("Déplacer (0,0) : " + g.deplacer(0, 0));

        // Test mélange
        System.out.println("\n=== Après mélange ===");
        g.melanger(100);
        for (int i = 0; i < g.getNbLignes(); i++) {
            for (int j = 0; j < g.getNbColonnes(); j++) {
                System.out.print(g.getCase(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println("Nb coups : " + g.getNbCoups());
        System.out.println("Gagnée ? " + g.estGagne());
    }
}

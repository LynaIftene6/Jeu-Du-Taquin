import modele.Grille;

public class TestGrille {
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

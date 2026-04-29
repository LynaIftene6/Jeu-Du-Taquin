import modele.Grille;
import vue.VueGraphique;
import controleur.ControleurGraphique;
import javax.swing.JOptionPane;

/**
 * Classe principale du jeu de Taquin.
 * <p>
 * Cette classe contient le point d'entrée du programme. Elle permet à l'utilisateur
 * de choisir la taille de la grille via une boîte de dialogue, puis initialise
 * les différents composants du jeu (modèle, vue et contrôleur).
 * </p>
 */
public class Main {
    /**
     * Point d'entrée du programme.
     * <p>
     * Affiche une boîte de dialogue permettant de choisir la taille de la grille
     * (3x3, 4x4 ou 5x5). Ensuite, crée une grille de jeu, la mélange,
     * puis initialise la vue graphique et le contrôleur associés.
     * </p>
     *
     * @param args les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {

        String[] options = {"3x3", "4x4", "5x5"};
        String choix = (String) JOptionPane.showInputDialog(
            null,
            "Choisissez la taille de la grille :",
            "Taquin - Nouvelle partie",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]
        );

        int taille = 4;
        if (choix != null) {
            taille = Integer.parseInt(choix.split("x")[0]);
        }

        Grille g = new Grille(taille, taille);
        g.melanger(200);

        VueGraphique vue = new VueGraphique(g);
        ControleurGraphique ctrl = new ControleurGraphique(g);
        vue.setControleur(ctrl);
    }
}

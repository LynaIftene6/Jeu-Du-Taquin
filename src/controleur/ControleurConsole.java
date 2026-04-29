package controleur;

import java.util.Scanner;
import modele.Grille;
import vue.VueConsole;

/**
 * Contrôleur console du jeu de Taquin.
 * 
 * Cette classe fait le lien entre le modèle {@link Grille} et la
 * {@link VueConsole}. Elle lit les saisies clavier de l'utilisateur,
 * déclenche les déplacements dans la grille et demande l'affichage
 * des messages adaptés.
 */
public class ControleurConsole {

    /** Modèle représentant l'état du jeu. */
    private final Grille grille;

    /** Vue console utilisée pour l'affichage. */
    private final VueConsole vue;

    /** Lecteur de saisie standard. */
    private final Scanner scanner;

    /**
     * Construit le contrôleur console à partir du modèle et de la vue.
     * 
     * @param grille le modèle du jeu
     * @param vue la vue console associée
     */
    public ControleurConsole(Grille grille, VueConsole vue) {
        this.grille = grille;
        this.vue = vue;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Démarre une partie en console.
     * 
     * Affiche le message de bienvenue, mélange la grille,
     * puis lance la boucle principale du jeu.
     */
    public void demarrer() {
        vue.afficherBienvenue();
        grille.melanger(200);
        boucleJeu();
    }

    /**
     * Traite une saisie utilisateur.
     * 
     * Si la saisie vaut {@code q}, le programme se termine.
     * Sinon, la méthode tente d'effectuer un déplacement ; en cas d'échec,
     * un message d'erreur est affiché dans la vue.
     * 
     * @param saisie la commande saisie par l'utilisateur
     */
    public void traiterSaisie(String saisie) {
        if ("q".equals(saisie.toLowerCase())) {
            System.exit(0);
        }

        if (effectuerDeplacement(saisie.toLowerCase())) {
            // déplacement réussi
        } else {
            vue.afficherMouvementInvalide();
        }
    }

    /**
     * Exécute la boucle principale du jeu jusqu'à la résolution du puzzle.
     * 
     * Tant que la grille n'est pas gagnée, la méthode lit une commande
     * au clavier puis la transmet à {@link #traiterSaisie(String)}.
     */
    private void boucleJeu() {
        while (!grille.estGagne()) {
            String saisie = scanner.nextLine();
            traiterSaisie(saisie);
        }
    }

    /**
     * Tente d'effectuer un déplacement à partir d'une direction saisie.
     * 
     * Les directions reconnues sont :
     * {@code z} pour haut, {@code q} pour gauche,
     * {@code s} pour bas et {@code d} pour droite.
     * 
     * La méthode calcule la case à déplacer par rapport à la position
     * actuelle de la case vide, vérifie que la destination reste dans
     * les bornes de la grille, puis appelle {@link Grille#deplacer(int, int)}.
     * 
     * @param dir la direction demandée
     * @return true si le déplacement a été effectué, false sinon
     */
    private boolean effectuerDeplacement(String dir) {
        int dl = 0, dc = 0;
        switch (dir) {
            case "z": dl = -1; break;
            case "q": dc = -1; break;
            case "s": dl = 1; break;
            case "d": dc = 1; break;
            default: return false;
        }

        int l = grille.getLigneVide() + dl;
        int c = grille.getColonneVide() + dc;

        return (l >= 0 && l < grille.getNbLignes() &&
                c >= 0 && c < grille.getNbColonnes() &&
                grille.deplacer(l, c));
    }
}
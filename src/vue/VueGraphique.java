package vue;

import modele.EcouteurModele;
import modele.Grille;
import controleur.ControleurGraphique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Vue graphique du jeu de Taquin.
 * 
 * Implémente EcouteurModele pour recevoir les mises à jour du modèle via modeleMisAJour.
 * Affiche la grille sous forme de boutons cliquables avec survol visuel, barre d'état avec compteur de coups
 * et support clavier ZQSD. Gère l'initialisation de la fenêtre, rafraîchissement et liaison avec le contrôleur.
 */
public class VueGraphique extends JFrame implements EcouteurModele {
    // Constantes visuelles
    private static final int TAILLE_TUILE = 100;
    private static final int BORDURE = 4;
    private static final Color COULEUR_TUILE = new Color(70, 130, 180);
    private static final Color COULEUR_SURVOL = new Color(60, 179, 113);
    private static final Color COULEUR_VIDE = new Color(30, 30, 40);
    private static final Color COULEUR_FOND = new Color(20, 20, 30);
    private static final Color COULEUR_TEXTE = Color.WHITE;

    // Attributs
    private final Grille grille;
    private ControleurGraphique controleur;
    private JButton[][] boutons;
    private JLabel labelCoups;
    private JLabel labelVictoire;

    /**
     * Construit la vue graphique, initialise l'interface et s'abonne au modèle.
     * 
     * @param grille le modèle du jeu
     */
    public VueGraphique(Grille grille) {
        super("Taquin");
        this.grille = grille;
        grille.ajoutEcouteur(this);
        initialiserFenetre();
        initialiserGrille();
        initialiserBarre();
        initialiserClavier();
        pack();
        setVisible(true);
        rafraichir();
    }

    /**
     * Initialisation du titre, EXIT_ON_CLOSE, BorderLayout sur le contentPane.
     */
    private void initialiserFenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COULEUR_FOND);
        getContentPane().setLayout(new BorderLayout());
    }

    /**
     * Crée le JPanel central avec GridLayout et les boutons via creerBouton.
     */
    private void initialiserGrille() {
        int lignes = grille.getNbLignes();
        int colonnes = grille.getNbColonnes();
        JPanel panel = new JPanel(new GridLayout(lignes, colonnes, BORDURE, BORDURE));
        panel.setBackground(COULEUR_FOND);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 4, 12));
        boutons = new JButton[lignes][colonnes];
        for (int l = 0; l < lignes; l++) {
            for (int c = 0; c < colonnes; c++) {
                boutons[l][c] = creerBouton(l, c);
                panel.add(boutons[l][c]);
            }
        }
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    /**
     * Crée et configure un bouton pour la case (ligne, col).
     * 
     * ActionListener : clic → controleur.deplacerTuile(ligne, col).
     * MouseListener : survol vert si déplaçable (extension 1).
     * 
     * @param ligne ligne du bouton
     * @param col colonne du bouton
     * @return JButton configuré
     */
    private JButton creerBouton(int ligne, int col) {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(TAILLE_TUILE, TAILLE_TUILE));
        btn.setFont(new Font("SansSerif", Font.BOLD, 26));
        btn.setForeground(COULEUR_TEXTE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);

        // ActionListener : délégue au contrôleur
        btn.addActionListener(e -> {
            if (controleur != null) controleur.deplacerTuile(ligne, col);
        });

        // MouseListener surlignage vert si déplaçable (extension 1)
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (grille.estDeplacable(ligne, col)) {
                    btn.setBackground(COULEUR_SURVOL);
                    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int val = grille.getCase(ligne, col);
                btn.setBackground(val == 0 ? COULEUR_VIDE : COULEUR_TUILE);
                btn.setCursor(Cursor.getDefaultCursor());
            }
        });
        return btn;
    }

    /**
     * Panneau bas avec labelCoups et labelVictoire (extension 3).
     */
    private void initialiserBarre() {
        JPanel barre = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 6));
        barre.setBackground(COULEUR_FOND);
        barre.setBorder(BorderFactory.createEmptyBorder(0, 12, 10, 12));
        labelCoups = new JLabel("Coups: 0");
        labelCoups.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelCoups.setForeground(new Color(180, 180, 200));
        labelVictoire = new JLabel();
        labelVictoire.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelVictoire.setForeground(new Color(60, 220, 120));
        barre.add(labelCoups);
        barre.add(labelVictoire);
        getContentPane().add(barre, BorderLayout.SOUTH);
    }

    /**
     * KeyListener ZQSD calculent la tuile cible depuis grille.getLigneVide()/getColonneVide()
     * et appellent controleur.deplacerTuile().
     */
    private void initialiserClavier() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (controleur == null) return;
                int lv = grille.getLigneVide();
                int cv = grille.getColonneVide();
                switch (Character.toUpperCase(e.getKeyChar())) {
                    case 'Z': controleur.deplacerTuile(lv - 1, cv); break;
                    case 'S': controleur.deplacerTuile(lv + 1, cv); break;
                    case 'Q': controleur.deplacerTuile(lv, cv - 1); break;
                    case 'D': controleur.deplacerTuile(lv, cv + 1); break;
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    // -------------------------------------------------------------------------
    // EcouteurModele
    // -------------------------------------------------------------------------

    /**
     * Appelé par grille.fireChangement().
     * Respecte l'EDT Swing via SwingUtilities.invokeLater().
     * 
     * @param source le modèle qui a changé
     */
    @Override
    public void modeleMisAJour(Object source) {
        SwingUtilities.invokeLater(this::rafraichir);
    }

    // -------------------------------------------------------------------------
    // Rafraîchissement
    // -------------------------------------------------------------------------

    /**
     * Met à jour texte et couleur de chaque bouton, labelCoups et labelVictoire
     * selon l'état courant du modèle.
     */
    private void rafraichir() {
        int lignes = grille.getNbLignes();
        int colonnes = grille.getNbColonnes();
        for (int l = 0; l < lignes; l++) {
            for (int c = 0; c < colonnes; c++) {
                int val = grille.getCase(l, c);
                JButton btn = boutons[l][c];
                if (val == 0) {
                    btn.setText("");
                    btn.setBackground(COULEUR_VIDE);
                    btn.setEnabled(false);
                } else {
                    btn.setText(String.valueOf(val));
                    btn.setBackground(COULEUR_TUILE);
                    btn.setEnabled(true);
                }
            }
        }
        labelCoups.setText("Coups: " + grille.getNbCoups());
        if (grille.estGagne()) {
            labelVictoire.setText("Puzzle résolu !");
            for (JButton[] ligne : boutons) {
                for (JButton btn : ligne) {
                    btn.setEnabled(false);
                }
            }
        } else {
            labelVictoire.setText("");
        }
    }

    // -------------------------------------------------------------------------
    // Liaison contrôleur
    // -------------------------------------------------------------------------

    /**
     * Injecte le contrôleur graphique.
     * Doit être appelé avant toute interaction.
     * 
     * @param controleur le ControleurGraphique associé
     */
    public void setControleur(ControleurGraphique controleur) {
        this.controleur = controleur;
    }
}
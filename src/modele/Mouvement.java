package modele;

/**
 * Représente un mouvement dans la grille.
 */
public class Mouvement {

    /** Ligne de la tuile à déplacer. */
    private final int ligne;

    /** Colonne de la tuile à déplacer. */
    private final int colonne;

    /**
     * Construit un mouvement.
     *
     * @param ligne la ligne de la tuile
     * @param colonne la colonne de la tuile
     */
    public Mouvement(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Retourne la ligne du mouvement.
     *
     * @return la ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Retourne la colonne du mouvement.
     *
     * @return la colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Retourne une représentation textuelle du mouvement.
     *
     * @return une chaîne décrivant le mouvement
     */
    @Override
    public String toString() {
        return String.format("Mouvement(%d,%d)", ligne, colonne);
    }

    /**
     * Compare ce mouvement à un autre objet.
     *
     * @param obj l'objet à comparer
     * @return true si les deux mouvements sont égaux, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Mouvement)) return false;
        Mouvement m = (Mouvement) obj;
        return ligne == m.ligne && colonne == m.colonne;
    }

    /**
     * Retourne le code de hachage du mouvement.
     *
     * @return le code de hachage
     */
    @Override
    public int hashCode() {
        return 31 * ligne + colonne;
    }
}
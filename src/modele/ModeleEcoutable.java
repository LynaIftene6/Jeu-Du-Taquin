package modele;

/**
 * Interface représentant un modèle observable.
 * 
 * Un objet implémentant cette interface permet à des écouteurs
 * de s'abonner ou de se désabonner afin d'être informés de ses
 * changements d'état.
 */
public interface ModeleEcoutable {

    /**
     * Ajoute un écouteur au modèle.
     * 
     * @param e l'écouteur à enregistrer
     */
    void ajoutEcouteur(EcouteurModele e);

    /**
     * Retire un écouteur du modèle.
     * 
     * @param e l'écouteur à retirer
     */
    void retraitEcouteur(EcouteurModele e);
}
package modele;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe abstraite implémentant le pattern Observateur pour les modèles.
 * 
 * Fournit une implémentation par défaut de l'interface {@link ModeleEcoutable}
 * avec une liste d'écouteurs et les méthodes d'abonnement/désabonnement.
 * Les sous-classes appellent {@link #fireChangement()} pour notifier les vues
 * des modifications d'état (ex: déplacement de tuile dans Grille).
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {

    /** Liste des écouteurs inscrits pour recevoir les notifications. */
    private final List<EcouteurModele> ecouteurs = new ArrayList<>();

    /**
     * Ajoute un écouteur au modèle.
     * 
     * L'écouteur recevra les notifications via {@link EcouteurModele#modeleMisAJour(Object)}
     * à chaque appel de {@link #fireChangement()}.
     * 
     * @param e l'écouteur à ajouter
     */
    public void ajoutEcouteur(EcouteurModele e) {
        ecouteurs.add(e);
    }

    /**
     * Retire un écouteur du modèle.
     * 
     * L'écouteur ne recevra plus les notifications de changement.
     * 
     * @param e l'écouteur à retirer
     */
    public void retraitEcouteur(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    /**
     * Notifie tous les écouteurs d'un changement d'état du modèle.
     * 
     * Appelle {@link EcouteurModele#modeleMisAJour(Object)} pour chaque écouteur
     * inscrit avec le modèle courant comme source.
     * 
     * <p>À appeler par les sous-classes après toute modification significative
     * (ex: déplacement de tuile, mélange de grille).</p>
     */
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurs) {
            e.modeleMisAJour(this);
        }
    }
}
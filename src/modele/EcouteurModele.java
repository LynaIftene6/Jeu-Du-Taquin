package modele;

/**
 * Interface d'écoute pour les objets souhaitant être notifiés
 * lorsqu'un modèle observé change d'état.
 * 
 * Elle s'inscrit dans le pattern Observateur : les classes qui
 * implémentent cette interface peuvent être enregistrées auprès
 * d'un modèle écoutable afin de réagir à ses modifications.
 */
public interface EcouteurModele {

    /**
     * Méthode appelée automatiquement lorsqu'un modèle observé
     * signale une mise à jour.
     * 
     * @param source l'objet modèle à l'origine du changement
     */
    void modeleMisAJour(Object source);
}
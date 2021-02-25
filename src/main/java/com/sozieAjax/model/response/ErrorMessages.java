package com.sozieAjax.model.response;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Champ obligatoire manquant. Veuillez vérifier la documentation pour les champs obligatoires"),
    RECORD_ALREADY_EXISTS("L'enregistrement existe déjà"),
	RECORD_ALREADY_EXISTS_WITH_PARAMETER("L'enregistrement %s existe déjà"),
    INTERNAL_SERVER_ERROR("Erreur Interne du Serveur"),
    NO_RECORD_FOUND("L'enregistrement avec l'ID fourni est introuvable"),
	NO_RECORD_FOUND_WITH_PARAMETER("L'enregistrement avec l'ID %s est introuvable"),
    AUTHENTICATION_FAILED("Authentification échouée"),
    COULD_NOT_UPDATE_RECORD("Impossible de mettre à jour l'enregistrement"),
    COULD_NOT_DELETE_RECORD("Impossible de supprimer l'enregistrement"),
	COULD_NOT_CREATE_RECORD("Impossible de créer l'enregistrement"),
    EMAIL_ADDRESS_NOT_VERIFIED("L'adresse e-mail n'a pas pu être vérifiée");
    

    private String errorMessage;

    

    ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}



	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    

}

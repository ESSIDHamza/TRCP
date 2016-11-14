package application.modeles;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Mail implements Serializable {
	private String destinataire;
	private String sujet;
	private String contenu;
	private String referenceDemande;

	public Mail() {
		super();
	}

	public Mail(String destinataire, String sujet, String contenu, String referenceDemande) {
		super();
		this.destinataire = destinataire;
		this.sujet = sujet;
		this.contenu = contenu;
		this.referenceDemande = referenceDemande;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getReferenceDemande() {
		return referenceDemande;
	}

	public void setReferenceDemande(String referenceDemande) {
		this.referenceDemande = referenceDemande;
	}
}
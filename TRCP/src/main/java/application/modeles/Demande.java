package application.modeles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
@Table(name = "DEMANDES")
public class Demande implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_DEM")
	private int id;
	@Column(name = "REFERENCE_DEMANDE")
	private String referenceDemande;
	@Transient
	private List<OutputOperation> produits;
	@Column(name = "CHEMIN_PDF")
	private String cheminPDF;
	@Column(name = "VALIDEE_AGENCE")
	private boolean valideeAgence;
	@Column(name = "VALIDEE_BO")
	private boolean valideeBO;
	@Column(name = "REJETEE")
	private boolean rejetee;
	@Column(name = "DATE_SAISIE")
	private String dateSaisie;
	@Column(name = "DATE_DERNIERE_MODIFICATION")
	private String dateDerniereModification;
	@Column(name = "DATE_VALIDATION_AGENCE")
	private String dateValidationAgence;
	@Column(name = "DATE_VALIDATION_BO")
	private String dateValidationBO;
	@Column(name = "MATRICULE_VALIDATION_AGENCE")
	private String matriculeValidationAgence;
	@Column(name = "MATRICULE_VALIDATION_BO")
	private String matriculeValidationBO;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_INFOS_EXT", referencedColumnName = "ID_INFORMATIONS_EXTERNES")
	private InformationsExternes informationsExternes;
	@Column(name = "STATUT")
	private String statut;
	@Column(name = "MAIL_AGENCE")
	private String mailAgence;

	public Demande() {
		super();
		produits = new ArrayList<OutputOperation>();
		informationsExternes = new InformationsExternes();
	}

	public Demande(String referenceDemande, List<OutputOperation> produits, String cheminPDF, boolean valideeAgence,
			boolean valideeBO, boolean rejetee, String dateSaisie, String dateDerniereModification,
			String dateValidationAgence, String dateValidationBO, String matriculeValidationAgence,
			String matriculeValidationBO, InformationsExternes informationsExternes, String statut, String mailAgence) {
		super();
		this.referenceDemande = referenceDemande;
		this.produits = produits;
		this.cheminPDF = cheminPDF;
		this.valideeAgence = valideeAgence;
		this.valideeBO = valideeBO;
		this.rejetee = rejetee;
		this.dateSaisie = dateSaisie;
		this.dateDerniereModification = dateDerniereModification;
		this.dateValidationAgence = dateValidationAgence;
		this.dateValidationBO = dateValidationBO;
		this.matriculeValidationAgence = matriculeValidationAgence;
		this.matriculeValidationBO = matriculeValidationBO;
		this.informationsExternes = informationsExternes;
		this.statut = statut;
		this.mailAgence = mailAgence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReferenceDemande() {
		return referenceDemande;
	}

	public void setReferenceDemande(String referenceDemande) {
		this.referenceDemande = referenceDemande;
	}

	public List<OutputOperation> getProduits() {
		return produits;
	}

	public void setProduits(List<OutputOperation> produits) {
		this.produits = produits;
	}

	public String getCheminPDF() {
		return cheminPDF;
	}

	public void setCheminPDF(String cheminPDF) {
		this.cheminPDF = cheminPDF;
	}

	public boolean isValideeAgence() {
		return valideeAgence;
	}

	public void setValideeAgence(boolean valideeAgence) {
		this.valideeAgence = valideeAgence;
	}

	public boolean isValideeBO() {
		return valideeBO;
	}

	public void setValideeBO(boolean valideeBO) {
		this.valideeBO = valideeBO;
	}

	public boolean isRejetee() {
		return rejetee;
	}

	public void setRejetee(boolean rejetee) {
		this.rejetee = rejetee;
	}

	public String getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(String dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getDateDerniereModification() {
		return dateDerniereModification;
	}

	public void setDateDerniereModification(String dateDerniereModification) {
		this.dateDerniereModification = dateDerniereModification;
	}

	public String getDateValidationAgence() {
		return dateValidationAgence;
	}

	public void setDateValidationAgence(String dateValidationAgence) {
		this.dateValidationAgence = dateValidationAgence;
	}

	public String getDateValidationBO() {
		return dateValidationBO;
	}

	public void setDateValidationBO(String dateValidationBO) {
		this.dateValidationBO = dateValidationBO;
	}

	public String getMatriculeValidationAgence() {
		return matriculeValidationAgence;
	}

	public void setMatriculeValidationAgence(String matriculeValidationAgence) {
		this.matriculeValidationAgence = matriculeValidationAgence;
	}

	public String getMatriculeValidationBO() {
		return matriculeValidationBO;
	}

	public void setMatriculeValidationBO(String matriculeValidationBO) {
		this.matriculeValidationBO = matriculeValidationBO;
	}

	public InformationsExternes getInformationsExternes() {
		return informationsExternes;
	}

	public void setInformationsExternes(InformationsExternes informationsExternes) {
		this.informationsExternes = informationsExternes;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getMailAgence() {
		return mailAgence;
	}

	public void setMailAgence(String mailAgence) {
		this.mailAgence = mailAgence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cheminPDF == null) ? 0 : cheminPDF.hashCode());
		result = prime * result + ((dateDerniereModification == null) ? 0 : dateDerniereModification.hashCode());
		result = prime * result + ((dateSaisie == null) ? 0 : dateSaisie.hashCode());
		result = prime * result + ((dateValidationAgence == null) ? 0 : dateValidationAgence.hashCode());
		result = prime * result + ((dateValidationBO == null) ? 0 : dateValidationBO.hashCode());
		result = prime * result + id;
		result = prime * result + ((informationsExternes == null) ? 0 : informationsExternes.hashCode());
		result = prime * result + ((mailAgence == null) ? 0 : mailAgence.hashCode());
		result = prime * result + ((matriculeValidationAgence == null) ? 0 : matriculeValidationAgence.hashCode());
		result = prime * result + ((matriculeValidationBO == null) ? 0 : matriculeValidationBO.hashCode());
		result = prime * result + ((produits == null) ? 0 : produits.hashCode());
		result = prime * result + ((referenceDemande == null) ? 0 : referenceDemande.hashCode());
		result = prime * result + (rejetee ? 1231 : 1237);
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + (valideeAgence ? 1231 : 1237);
		result = prime * result + (valideeBO ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demande other = (Demande) obj;
		if (cheminPDF == null) {
			if (other.cheminPDF != null)
				return false;
		} else if (!cheminPDF.equals(other.cheminPDF))
			return false;
		if (dateDerniereModification == null) {
			if (other.dateDerniereModification != null)
				return false;
		} else if (!dateDerniereModification.equals(other.dateDerniereModification))
			return false;
		if (dateSaisie == null) {
			if (other.dateSaisie != null)
				return false;
		} else if (!dateSaisie.equals(other.dateSaisie))
			return false;
		if (dateValidationAgence == null) {
			if (other.dateValidationAgence != null)
				return false;
		} else if (!dateValidationAgence.equals(other.dateValidationAgence))
			return false;
		if (dateValidationBO == null) {
			if (other.dateValidationBO != null)
				return false;
		} else if (!dateValidationBO.equals(other.dateValidationBO))
			return false;
		if (id != other.id)
			return false;
		if (informationsExternes == null) {
			if (other.informationsExternes != null)
				return false;
		} else if (!informationsExternes.equals(other.informationsExternes))
			return false;
		if (mailAgence == null) {
			if (other.mailAgence != null)
				return false;
		} else if (!mailAgence.equals(other.mailAgence))
			return false;
		if (matriculeValidationAgence == null) {
			if (other.matriculeValidationAgence != null)
				return false;
		} else if (!matriculeValidationAgence.equals(other.matriculeValidationAgence))
			return false;
		if (matriculeValidationBO == null) {
			if (other.matriculeValidationBO != null)
				return false;
		} else if (!matriculeValidationBO.equals(other.matriculeValidationBO))
			return false;
		if (produits == null) {
			if (other.produits != null)
				return false;
		} else if (!produits.equals(other.produits))
			return false;
		if (referenceDemande == null) {
			if (other.referenceDemande != null)
				return false;
		} else if (!referenceDemande.equals(other.referenceDemande))
			return false;
		if (rejetee != other.rejetee)
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		if (valideeAgence != other.valideeAgence)
			return false;
		if (valideeBO != other.valideeBO)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Demande [id=" + id + ", referenceDemande=" + referenceDemande + ", produits=" + produits
				+ ", cheminPDF=" + cheminPDF + ", valideeAgence=" + valideeAgence + ", valideeBO=" + valideeBO
				+ ", rejetee=" + rejetee + ", dateSaisie=" + dateSaisie + ", dateDerniereModification="
				+ dateDerniereModification + ", dateValidationAgence=" + dateValidationAgence + ", dateValidationBO="
				+ dateValidationBO + ", matriculeValidationAgence=" + matriculeValidationAgence
				+ ", matriculeValidationBO=" + matriculeValidationBO + ", informationsExternes=" + informationsExternes
				+ ", statut=" + statut + ", mailAgence=" + mailAgence + "]";
	}
}
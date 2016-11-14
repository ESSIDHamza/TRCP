package application.modeles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
@Table(name = "INFORMATIONS_EXTERNES")
public class InformationsExternes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_INFORMATIONS_EXTERNES")
	private int id;
	@Column(name = "FICHE")
	private String fiche;
	@Column(name = "NOM_PRENOM")
	private String nomEtPrenom;
	@Column(name = "CODE_AGENCE")
	private String codeAgence;
	@Column(name = "DR")
	private String DR;
	@ElementCollection
	@CollectionTable(name = "CLTS_COMPTES", joinColumns = @JoinColumn(name = "ID_CLT"))
	private List<String> comptes;

	public InformationsExternes() {
		super();
		this.comptes = new ArrayList<String>();
	}

	public InformationsExternes(String fiche, String nomEtPrenom, String codeAgence, String dR, List<String> comptes) {
		super();
		this.fiche = fiche;
		this.nomEtPrenom = nomEtPrenom;
		this.codeAgence = codeAgence;
		DR = dR;
		this.comptes = comptes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFiche() {
		return fiche;
	}

	public void setFiche(String fiche) {
		this.fiche = fiche;
	}

	public String getNomEtPrenom() {
		return nomEtPrenom;
	}

	public void setNomEtPrenom(String nomEtPrenom) {
		this.nomEtPrenom = nomEtPrenom;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}

	public String getDR() {
		return DR;
	}

	public void setDR(String dR) {
		DR = dR;
	}

	public List<String> getComptes() {
		return comptes;
	}

	public void setComptes(List<String> comptes) {
		this.comptes = comptes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DR == null) ? 0 : DR.hashCode());
		result = prime * result + ((codeAgence == null) ? 0 : codeAgence.hashCode());
		result = prime * result + ((comptes == null) ? 0 : comptes.hashCode());
		result = prime * result + ((fiche == null) ? 0 : fiche.hashCode());
		result = prime * result + id;
		result = prime * result + ((nomEtPrenom == null) ? 0 : nomEtPrenom.hashCode());
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
		InformationsExternes other = (InformationsExternes) obj;
		if (DR == null) {
			if (other.DR != null)
				return false;
		} else if (!DR.equals(other.DR))
			return false;
		if (codeAgence == null) {
			if (other.codeAgence != null)
				return false;
		} else if (!codeAgence.equals(other.codeAgence))
			return false;
		if (comptes == null) {
			if (other.comptes != null)
				return false;
		} else if (!comptes.equals(other.comptes))
			return false;
		if (fiche == null) {
			if (other.fiche != null)
				return false;
		} else if (!fiche.equals(other.fiche))
			return false;
		if (id != other.id)
			return false;
		if (nomEtPrenom == null) {
			if (other.nomEtPrenom != null)
				return false;
		} else if (!nomEtPrenom.equals(other.nomEtPrenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InformationsExternes [id=" + id + ", fiche=" + fiche + ", nomEtPrenom=" + nomEtPrenom + ", codeAgence="
				+ codeAgence + ", DR=" + DR + ", comptes=" + comptes + "]";
	}
}
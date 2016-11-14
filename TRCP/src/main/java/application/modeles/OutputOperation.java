package application.modeles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@Entity
@Table(name = "STOCKS")
public class OutputOperation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "REFERENCE_DEMANDE")
	private String referenceDemande;
	@Column(name = "REFERENCE_PRODUIT")
	private String referenceProduit;
	@Column(name = "OPERATION")
	private String operation;
	@Column(name = "ASSIETTE")
	private String assiette;
	@Column(name = "TARIF_STANDARD")
	private String tarifStandard;
	@Column(name = "TTC_HT")
	private String TTCOuHT;
	@Column(name = "INDEX_TARIF")
	private String indexTarif;
	@Column(name = "TYPE_VALEUR")
	private String typeValeur;
	@Column(name = "MONTANT_PLACEMENT")
	private String montantPlacement;
	@Column(name = "NOMBRE_DE_JOURS_PLACEMENT")
	private String nbrJoursPlacement;
	@Column(name = "POURCENTAGE_KIT")
	private String pourcentageKit;
	@Transient
	private String dateSaisie;
	@Transient
	private String dateDerniereModification;
	@Transient
	private String dateValidation;
	@Transient
	private String tarifSollicite;
	@Transient
	private String echeanceSollicitee;
	@Column(name = "TARIF_ACCORDE")
	private String tarifAccorde;
	@Column(name = "ECHEANCE_ACCORDEE")
	private String echeanceAccordee;
	@Column(name = "CORRESPONDANCE")
	private String correspondance;
	@Transient
	private String fiche;
	@Transient
	private String nomEtPrenom;
	@Transient
	private String codeAgence;
	@Transient
	private String DR;
	@Transient
	private String compte;
	@Column(name = "NUM")
	private String num;
	@Column(name = "LIBELLE")
	private String libelle;
	@Column(name = "DETAIL_LIB")
	private String detailLib;
	@Column(name = "DEVISE")
	private String devise;
	@Column(name = "DEVISE_TND_CONV")
	private String deviseTND_CONV;
	@Column(name = "STANDARD")
	private String standard;
	@Column(name = "MONTANT_MIN_PLACEMENT")
	private String montantMinPlacement;
	@Column(name = "MONTANT_MAX_PLACEMENT")
	private String montantMaxPlacement;
	@Column(name = "DUREE_MIN_PLACEMENT")
	private String dureeMinPlacement;
	@Column(name = "DUREE_MAX_PLACEMENT")
	private String dureeMaxPlacement;
	@Column(name = "VALEUR_FIXE_KIT_TTC")
	private String valeurFixeKitTTC;
	@Column(name = "CODE_OPERATION")
	private String codeOperation;
	@Column(name = "NATURE")
	private String nature;
	@Column(name = "VALEUR")
	private String valeur;
	@Column(name = "OBSERVATION")
	private String observation;
	@Column(name = "PACK")
	private String pack;
	@Column(name = "PRODUIT")
	private String produit;
	@Column(name = "NIVEAU_FORCAGE")
	private String niveauForcage;
	@Column(name = "EN_PLUS")
	private String enPlus;
	@Column(name = "EN_MOINS")
	private String enMoins;
	@Column(name = "CORRESPONDANCE_ALGO")
	private String correspondanceAlgo;
	@Column(name = "VALEUR_ALGO_CLT")
	private String valeurAlgoClt;
	@Column(name = "INDEX_VALEUR_ALGO")
	private String indexValeurAlgo;
	@Column(name = "TARIFS_PREFENRENTIELS_ALGO")
	private String tarifsPreferentielsAlgo;
	@Column(name = "INDEX_TARIF_ALGO")
	private String indexTarifAlgo;
	@Column(name = "VALEUR_ALGO")
	private String valeurAlgo;

	public OutputOperation() {
		super();
	}

	public OutputOperation(String referenceDemande, String referenceProduit, String operation, String assiette,
			String tarifStandard, String tTCOuHT, String indexTarif, String typeValeur, String montantPlacement,
			String nbrJoursPlacement, String pourcentageKit, String dateSaisie, String dateDerniereModification,
			String dateValidation, String tarifSollicite, String echeanceSollicitee, String tarifAccorde,
			String echeanceAccordee, String correspondance, String fiche, String nomEtPrenom, String codeAgence,
			String dR, String compte, String num, String libelle, String detailLib, String devise,
			String deviseTND_CONV, String standard, String montantMinPlacement, String montantMaxPlacement,
			String dureeMinPlacement, String dureeMaxPlacement, String valeurFixeKitTTC, String codeOperation,
			String nature, String valeur, String observation, String pack, String produit, String niveauForcage,
			String enPlus, String enMoins, String correspondanceAlgo, String valeurAlgoClt, String indexValeurAlgo,
			String tarifsPreferentielsAlgo, String indexTarifAlgo, String valeurAlgo) {
		super();
		this.referenceDemande = referenceDemande;
		this.referenceProduit = referenceProduit;
		this.operation = operation;
		this.assiette = assiette;
		this.tarifStandard = tarifStandard;
		TTCOuHT = tTCOuHT;
		this.indexTarif = indexTarif;
		this.typeValeur = typeValeur;
		this.montantPlacement = montantPlacement;
		this.nbrJoursPlacement = nbrJoursPlacement;
		this.pourcentageKit = pourcentageKit;
		this.dateSaisie = dateSaisie;
		this.dateDerniereModification = dateDerniereModification;
		this.dateValidation = dateValidation;
		this.tarifSollicite = tarifSollicite;
		this.echeanceSollicitee = echeanceSollicitee;
		this.tarifAccorde = tarifAccorde;
		this.echeanceAccordee = echeanceAccordee;
		this.correspondance = correspondance;
		this.fiche = fiche;
		this.nomEtPrenom = nomEtPrenom;
		this.codeAgence = codeAgence;
		DR = dR;
		this.compte = compte;
		this.num = num;
		this.libelle = libelle;
		this.detailLib = detailLib;
		this.devise = devise;
		this.deviseTND_CONV = deviseTND_CONV;
		this.standard = standard;
		this.montantMinPlacement = montantMinPlacement;
		this.montantMaxPlacement = montantMaxPlacement;
		this.dureeMinPlacement = dureeMinPlacement;
		this.dureeMaxPlacement = dureeMaxPlacement;
		this.valeurFixeKitTTC = valeurFixeKitTTC;
		this.codeOperation = codeOperation;
		this.nature = nature;
		this.valeur = valeur;
		this.observation = observation;
		this.pack = pack;
		this.produit = produit;
		this.niveauForcage = niveauForcage;
		this.enPlus = enPlus;
		this.enMoins = enMoins;
		this.correspondanceAlgo = correspondanceAlgo;
		this.valeurAlgoClt = valeurAlgoClt;
		this.indexValeurAlgo = indexValeurAlgo;
		this.tarifsPreferentielsAlgo = tarifsPreferentielsAlgo;
		this.indexTarifAlgo = indexTarifAlgo;
		this.valeurAlgo = valeurAlgo;
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

	public String getReferenceProduit() {
		return referenceProduit;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getAssiette() {
		return assiette;
	}

	public void setAssiette(String assiette) {
		this.assiette = assiette;
	}

	public String getTarifStandard() {
		return tarifStandard;
	}

	public void setTarifStandard(String tarifStandard) {
		this.tarifStandard = tarifStandard;
	}

	public String getTTCOuHT() {
		return TTCOuHT;
	}

	public void setTTCOuHT(String tTCOuHT) {
		TTCOuHT = tTCOuHT;
	}

	public String getIndexTarif() {
		return indexTarif;
	}

	public void setIndexTarif(String indexTarif) {
		this.indexTarif = indexTarif;
	}

	public String getTypeValeur() {
		return typeValeur;
	}

	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
	}

	public String getMontantPlacement() {
		return montantPlacement;
	}

	public void setMontantPlacement(String montantPlacement) {
		this.montantPlacement = montantPlacement;
	}

	public String getNbrJoursPlacement() {
		return nbrJoursPlacement;
	}

	public void setNbrJoursPlacement(String nbrJoursPlacement) {
		this.nbrJoursPlacement = nbrJoursPlacement;
	}

	public String getPourcentageKit() {
		return pourcentageKit;
	}

	public void setPourcentageKit(String pourcentageKit) {
		this.pourcentageKit = pourcentageKit;
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

	public String getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}

	public String getTarifSollicite() {
		return tarifSollicite;
	}

	public void setTarifSollicite(String tarifSollicite) {
		this.tarifSollicite = tarifSollicite;
	}

	public String getEcheanceSollicitee() {
		return echeanceSollicitee;
	}

	public void setEcheanceSollicitee(String echeanceSollicitee) {
		this.echeanceSollicitee = echeanceSollicitee;
	}

	public String getTarifAccorde() {
		return tarifAccorde;
	}

	public void setTarifAccorde(String tarifAccorde) {
		this.tarifAccorde = tarifAccorde;
	}

	public String getEcheanceAccordee() {
		return echeanceAccordee;
	}

	public void setEcheanceAccordee(String echeanceAccordee) {
		this.echeanceAccordee = echeanceAccordee;
	}

	public String getCorrespondance() {
		return correspondance;
	}

	public void setCorrespondance(String correspondance) {
		this.correspondance = correspondance;
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

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDetailLib() {
		return detailLib;
	}

	public void setDetailLib(String detailLib) {
		this.detailLib = detailLib;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public String getDeviseTND_CONV() {
		return deviseTND_CONV;
	}

	public void setDeviseTND_CONV(String deviseTND_CONV) {
		this.deviseTND_CONV = deviseTND_CONV;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getMontantMinPlacement() {
		return montantMinPlacement;
	}

	public void setMontantMinPlacement(String montantMinPlacement) {
		this.montantMinPlacement = montantMinPlacement;
	}

	public String getMontantMaxPlacement() {
		return montantMaxPlacement;
	}

	public void setMontantMaxPlacement(String montantMaxPlacement) {
		this.montantMaxPlacement = montantMaxPlacement;
	}

	public String getDureeMinPlacement() {
		return dureeMinPlacement;
	}

	public void setDureeMinPlacement(String dureeMinPlacement) {
		this.dureeMinPlacement = dureeMinPlacement;
	}

	public String getDureeMaxPlacement() {
		return dureeMaxPlacement;
	}

	public void setDureeMaxPlacement(String dureeMaxPlacement) {
		this.dureeMaxPlacement = dureeMaxPlacement;
	}

	public String getValeurFixeKitTTC() {
		return valeurFixeKitTTC;
	}

	public void setValeurFixeKitTTC(String valeurFixeKitTTC) {
		this.valeurFixeKitTTC = valeurFixeKitTTC;
	}

	public String getCodeOperation() {
		return codeOperation;
	}

	public void setCodeOperation(String codeOperation) {
		this.codeOperation = codeOperation;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getNiveauForcage() {
		return niveauForcage;
	}

	public void setNiveauForcage(String niveauForcage) {
		this.niveauForcage = niveauForcage;
	}

	public String getEnPlus() {
		return enPlus;
	}

	public void setEnPlus(String enPlus) {
		this.enPlus = enPlus;
	}

	public String getEnMoins() {
		return enMoins;
	}

	public void setEnMoins(String enMoins) {
		this.enMoins = enMoins;
	}

	public String getCorrespondanceAlgo() {
		return correspondanceAlgo;
	}

	public void setCorrespondanceAlgo(String correspondanceAlgo) {
		this.correspondanceAlgo = correspondanceAlgo;
	}

	public String getValeurAlgoClt() {
		return valeurAlgoClt;
	}

	public void setValeurAlgoClt(String valeurAlgoClt) {
		this.valeurAlgoClt = valeurAlgoClt;
	}

	public String getIndexValeurAlgo() {
		return indexValeurAlgo;
	}

	public void setIndexValeurAlgo(String indexValeurAlgo) {
		this.indexValeurAlgo = indexValeurAlgo;
	}

	public String getTarifsPreferentielsAlgo() {
		return tarifsPreferentielsAlgo;
	}

	public void setTarifsPreferentielsAlgo(String tarifsPreferentielsAlgo) {
		this.tarifsPreferentielsAlgo = tarifsPreferentielsAlgo;
	}

	public String getIndexTarifAlgo() {
		return indexTarifAlgo;
	}

	public void setIndexTarifAlgo(String indexTarifAlgo) {
		this.indexTarifAlgo = indexTarifAlgo;
	}

	public String getValeurAlgo() {
		return valeurAlgo;
	}

	public void setValeurAlgo(String valeurAlgo) {
		this.valeurAlgo = valeurAlgo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DR == null) ? 0 : DR.hashCode());
		result = prime * result + ((TTCOuHT == null) ? 0 : TTCOuHT.hashCode());
		result = prime * result + ((assiette == null) ? 0 : assiette.hashCode());
		result = prime * result + ((codeAgence == null) ? 0 : codeAgence.hashCode());
		result = prime * result + ((codeOperation == null) ? 0 : codeOperation.hashCode());
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result + ((correspondance == null) ? 0 : correspondance.hashCode());
		result = prime * result + ((correspondanceAlgo == null) ? 0 : correspondanceAlgo.hashCode());
		result = prime * result + ((dateDerniereModification == null) ? 0 : dateDerniereModification.hashCode());
		result = prime * result + ((dateSaisie == null) ? 0 : dateSaisie.hashCode());
		result = prime * result + ((dateValidation == null) ? 0 : dateValidation.hashCode());
		result = prime * result + ((detailLib == null) ? 0 : detailLib.hashCode());
		result = prime * result + ((devise == null) ? 0 : devise.hashCode());
		result = prime * result + ((deviseTND_CONV == null) ? 0 : deviseTND_CONV.hashCode());
		result = prime * result + ((dureeMaxPlacement == null) ? 0 : dureeMaxPlacement.hashCode());
		result = prime * result + ((dureeMinPlacement == null) ? 0 : dureeMinPlacement.hashCode());
		result = prime * result + ((echeanceAccordee == null) ? 0 : echeanceAccordee.hashCode());
		result = prime * result + ((echeanceSollicitee == null) ? 0 : echeanceSollicitee.hashCode());
		result = prime * result + ((enMoins == null) ? 0 : enMoins.hashCode());
		result = prime * result + ((enPlus == null) ? 0 : enPlus.hashCode());
		result = prime * result + ((fiche == null) ? 0 : fiche.hashCode());
		result = prime * result + id;
		result = prime * result + ((indexTarif == null) ? 0 : indexTarif.hashCode());
		result = prime * result + ((indexTarifAlgo == null) ? 0 : indexTarifAlgo.hashCode());
		result = prime * result + ((indexValeurAlgo == null) ? 0 : indexValeurAlgo.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((montantMaxPlacement == null) ? 0 : montantMaxPlacement.hashCode());
		result = prime * result + ((montantMinPlacement == null) ? 0 : montantMinPlacement.hashCode());
		result = prime * result + ((montantPlacement == null) ? 0 : montantPlacement.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + ((nbrJoursPlacement == null) ? 0 : nbrJoursPlacement.hashCode());
		result = prime * result + ((niveauForcage == null) ? 0 : niveauForcage.hashCode());
		result = prime * result + ((nomEtPrenom == null) ? 0 : nomEtPrenom.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((pack == null) ? 0 : pack.hashCode());
		result = prime * result + ((pourcentageKit == null) ? 0 : pourcentageKit.hashCode());
		result = prime * result + ((produit == null) ? 0 : produit.hashCode());
		result = prime * result + ((referenceDemande == null) ? 0 : referenceDemande.hashCode());
		result = prime * result + ((referenceProduit == null) ? 0 : referenceProduit.hashCode());
		result = prime * result + ((standard == null) ? 0 : standard.hashCode());
		result = prime * result + ((tarifAccorde == null) ? 0 : tarifAccorde.hashCode());
		result = prime * result + ((tarifSollicite == null) ? 0 : tarifSollicite.hashCode());
		result = prime * result + ((tarifStandard == null) ? 0 : tarifStandard.hashCode());
		result = prime * result + ((tarifsPreferentielsAlgo == null) ? 0 : tarifsPreferentielsAlgo.hashCode());
		result = prime * result + ((typeValeur == null) ? 0 : typeValeur.hashCode());
		result = prime * result + ((valeur == null) ? 0 : valeur.hashCode());
		result = prime * result + ((valeurAlgo == null) ? 0 : valeurAlgo.hashCode());
		result = prime * result + ((valeurAlgoClt == null) ? 0 : valeurAlgoClt.hashCode());
		result = prime * result + ((valeurFixeKitTTC == null) ? 0 : valeurFixeKitTTC.hashCode());
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
		OutputOperation other = (OutputOperation) obj;
		if (DR == null) {
			if (other.DR != null)
				return false;
		} else if (!DR.equals(other.DR))
			return false;
		if (TTCOuHT == null) {
			if (other.TTCOuHT != null)
				return false;
		} else if (!TTCOuHT.equals(other.TTCOuHT))
			return false;
		if (assiette == null) {
			if (other.assiette != null)
				return false;
		} else if (!assiette.equals(other.assiette))
			return false;
		if (codeAgence == null) {
			if (other.codeAgence != null)
				return false;
		} else if (!codeAgence.equals(other.codeAgence))
			return false;
		if (codeOperation == null) {
			if (other.codeOperation != null)
				return false;
		} else if (!codeOperation.equals(other.codeOperation))
			return false;
		if (compte == null) {
			if (other.compte != null)
				return false;
		} else if (!compte.equals(other.compte))
			return false;
		if (correspondance == null) {
			if (other.correspondance != null)
				return false;
		} else if (!correspondance.equals(other.correspondance))
			return false;
		if (correspondanceAlgo == null) {
			if (other.correspondanceAlgo != null)
				return false;
		} else if (!correspondanceAlgo.equals(other.correspondanceAlgo))
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
		if (dateValidation == null) {
			if (other.dateValidation != null)
				return false;
		} else if (!dateValidation.equals(other.dateValidation))
			return false;
		if (detailLib == null) {
			if (other.detailLib != null)
				return false;
		} else if (!detailLib.equals(other.detailLib))
			return false;
		if (devise == null) {
			if (other.devise != null)
				return false;
		} else if (!devise.equals(other.devise))
			return false;
		if (deviseTND_CONV == null) {
			if (other.deviseTND_CONV != null)
				return false;
		} else if (!deviseTND_CONV.equals(other.deviseTND_CONV))
			return false;
		if (dureeMaxPlacement == null) {
			if (other.dureeMaxPlacement != null)
				return false;
		} else if (!dureeMaxPlacement.equals(other.dureeMaxPlacement))
			return false;
		if (dureeMinPlacement == null) {
			if (other.dureeMinPlacement != null)
				return false;
		} else if (!dureeMinPlacement.equals(other.dureeMinPlacement))
			return false;
		if (echeanceAccordee == null) {
			if (other.echeanceAccordee != null)
				return false;
		} else if (!echeanceAccordee.equals(other.echeanceAccordee))
			return false;
		if (echeanceSollicitee == null) {
			if (other.echeanceSollicitee != null)
				return false;
		} else if (!echeanceSollicitee.equals(other.echeanceSollicitee))
			return false;
		if (enMoins == null) {
			if (other.enMoins != null)
				return false;
		} else if (!enMoins.equals(other.enMoins))
			return false;
		if (enPlus == null) {
			if (other.enPlus != null)
				return false;
		} else if (!enPlus.equals(other.enPlus))
			return false;
		if (fiche == null) {
			if (other.fiche != null)
				return false;
		} else if (!fiche.equals(other.fiche))
			return false;
		if (id != other.id)
			return false;
		if (indexTarif == null) {
			if (other.indexTarif != null)
				return false;
		} else if (!indexTarif.equals(other.indexTarif))
			return false;
		if (indexTarifAlgo == null) {
			if (other.indexTarifAlgo != null)
				return false;
		} else if (!indexTarifAlgo.equals(other.indexTarifAlgo))
			return false;
		if (indexValeurAlgo == null) {
			if (other.indexValeurAlgo != null)
				return false;
		} else if (!indexValeurAlgo.equals(other.indexValeurAlgo))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (montantMaxPlacement == null) {
			if (other.montantMaxPlacement != null)
				return false;
		} else if (!montantMaxPlacement.equals(other.montantMaxPlacement))
			return false;
		if (montantMinPlacement == null) {
			if (other.montantMinPlacement != null)
				return false;
		} else if (!montantMinPlacement.equals(other.montantMinPlacement))
			return false;
		if (montantPlacement == null) {
			if (other.montantPlacement != null)
				return false;
		} else if (!montantPlacement.equals(other.montantPlacement))
			return false;
		if (nature == null) {
			if (other.nature != null)
				return false;
		} else if (!nature.equals(other.nature))
			return false;
		if (nbrJoursPlacement == null) {
			if (other.nbrJoursPlacement != null)
				return false;
		} else if (!nbrJoursPlacement.equals(other.nbrJoursPlacement))
			return false;
		if (niveauForcage == null) {
			if (other.niveauForcage != null)
				return false;
		} else if (!niveauForcage.equals(other.niveauForcage))
			return false;
		if (nomEtPrenom == null) {
			if (other.nomEtPrenom != null)
				return false;
		} else if (!nomEtPrenom.equals(other.nomEtPrenom))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (pack == null) {
			if (other.pack != null)
				return false;
		} else if (!pack.equals(other.pack))
			return false;
		if (pourcentageKit == null) {
			if (other.pourcentageKit != null)
				return false;
		} else if (!pourcentageKit.equals(other.pourcentageKit))
			return false;
		if (produit == null) {
			if (other.produit != null)
				return false;
		} else if (!produit.equals(other.produit))
			return false;
		if (referenceDemande == null) {
			if (other.referenceDemande != null)
				return false;
		} else if (!referenceDemande.equals(other.referenceDemande))
			return false;
		if (referenceProduit == null) {
			if (other.referenceProduit != null)
				return false;
		} else if (!referenceProduit.equals(other.referenceProduit))
			return false;
		if (standard == null) {
			if (other.standard != null)
				return false;
		} else if (!standard.equals(other.standard))
			return false;
		if (tarifAccorde == null) {
			if (other.tarifAccorde != null)
				return false;
		} else if (!tarifAccorde.equals(other.tarifAccorde))
			return false;
		if (tarifSollicite == null) {
			if (other.tarifSollicite != null)
				return false;
		} else if (!tarifSollicite.equals(other.tarifSollicite))
			return false;
		if (tarifStandard == null) {
			if (other.tarifStandard != null)
				return false;
		} else if (!tarifStandard.equals(other.tarifStandard))
			return false;
		if (tarifsPreferentielsAlgo == null) {
			if (other.tarifsPreferentielsAlgo != null)
				return false;
		} else if (!tarifsPreferentielsAlgo.equals(other.tarifsPreferentielsAlgo))
			return false;
		if (typeValeur == null) {
			if (other.typeValeur != null)
				return false;
		} else if (!typeValeur.equals(other.typeValeur))
			return false;
		if (valeur == null) {
			if (other.valeur != null)
				return false;
		} else if (!valeur.equals(other.valeur))
			return false;
		if (valeurAlgo == null) {
			if (other.valeurAlgo != null)
				return false;
		} else if (!valeurAlgo.equals(other.valeurAlgo))
			return false;
		if (valeurAlgoClt == null) {
			if (other.valeurAlgoClt != null)
				return false;
		} else if (!valeurAlgoClt.equals(other.valeurAlgoClt))
			return false;
		if (valeurFixeKitTTC == null) {
			if (other.valeurFixeKitTTC != null)
				return false;
		} else if (!valeurFixeKitTTC.equals(other.valeurFixeKitTTC))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OutputOperation [id=" + id + ", referenceDemande=" + referenceDemande + ", referenceProduit="
				+ referenceProduit + ", operation=" + operation + ", assiette=" + assiette + ", tarifStandard="
				+ tarifStandard + ", TTCOuHT=" + TTCOuHT + ", indexTarif=" + indexTarif + ", typeValeur=" + typeValeur
				+ ", montantPlacement=" + montantPlacement + ", nbrJoursPlacement=" + nbrJoursPlacement
				+ ", pourcentageKit=" + pourcentageKit + ", dateSaisie=" + dateSaisie + ", dateDerniereModification="
				+ dateDerniereModification + ", dateValidation=" + dateValidation + ", tarifSollicite=" + tarifSollicite
				+ ", echeanceSollicitee=" + echeanceSollicitee + ", tarifAccorde=" + tarifAccorde
				+ ", echeanceAccordee=" + echeanceAccordee + ", correspondance=" + correspondance + ", fiche=" + fiche
				+ ", nomEtPrenom=" + nomEtPrenom + ", codeAgence=" + codeAgence + ", DR=" + DR + ", compte=" + compte
				+ ", num=" + num + ", libelle=" + libelle + ", detailLib=" + detailLib + ", devise=" + devise
				+ ", deviseTND_CONV=" + deviseTND_CONV + ", standard=" + standard + ", montantMinPlacement="
				+ montantMinPlacement + ", montantMaxPlacement=" + montantMaxPlacement + ", dureeMinPlacement="
				+ dureeMinPlacement + ", dureeMaxPlacement=" + dureeMaxPlacement + ", valeurFixeKitTTC="
				+ valeurFixeKitTTC + ", codeOperation=" + codeOperation + ", nature=" + nature + ", valeur=" + valeur
				+ ", observation=" + observation + ", pack=" + pack + ", produit=" + produit + ", niveauForcage="
				+ niveauForcage + ", enPlus=" + enPlus + ", enMoins=" + enMoins + ", correspondanceAlgo="
				+ correspondanceAlgo + ", valeurAlgoClt=" + valeurAlgoClt + ", indexValeurAlgo=" + indexValeurAlgo
				+ ", tarifsPreferentielsAlgo=" + tarifsPreferentielsAlgo + ", indexTarifAlgo=" + indexTarifAlgo
				+ ", valeurAlgo=" + valeurAlgo + "]";
	}
}
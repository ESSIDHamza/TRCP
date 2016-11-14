package application.controlleur;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import application.dao.GestionDemandeRepository;
import application.dao.GestionOutputOperationRepository;
import application.metier.EnvoiMail;
import application.metier.GenerationDUD_PDF;
import application.metier.GestionConditionsPreferentielles;
import application.modeles.Demande;
import application.modeles.Mail;
import application.modeles.OutputOperation;

@RestController
@CrossOrigin(origins = "*")
public class GestionConditionsPreferentiellesController {
	@Autowired
	private GestionConditionsPreferentielles gestionConditionsPreferentielles;
	@Autowired
	private GestionOutputOperationRepository gestionOutputOperationRepository;
	@Autowired
	private GestionDemandeRepository gestionDemandeRepository;
	@Autowired
	private GenerationDUD_PDF generationDUD_PDF;
	@Autowired
	private EnvoiMail envoiMail;

	public static final String CHEMIN_DUD = "C:\\Users\\Hamza\\workspace\\TRCP\\target\\classes\\static\\DUD";
	public static final String CHEMIN_PDF = "http://localhost:8080/DUD/";

	@Deprecated
	@RequestMapping(value = "/api/cp_dep", method = RequestMethod.POST)
	public void creerOutput(@RequestBody OutputOperation outputOperation) {
		try {
			// gestionConditionsPreferentielles.enregistrerOutputsOperation(outputOperation);
		} catch (Exception e) {
			System.err.println("Erreur lors de la création de l'output !");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/cp", method = RequestMethod.POST)
	public ResponseEntity<Demande> creerDemande(@RequestBody Demande demande) {
		try {
			String referenceDemande = "DEM_" + System.currentTimeMillis();

			String dateSaisie = demande.getDateSaisie().split("T")[0];
			String dateS = dateSaisie.split("-")[2] + "-" + dateSaisie.split("-")[1] + "-" + dateSaisie.split("-")[0];
			demande.setDateSaisie(dateS);

			demande.setReferenceDemande(referenceDemande);
			demande.setCheminPDF(CHEMIN_PDF + referenceDemande + ".pdf");
			gestionDemandeRepository.save(demande);
			for (OutputOperation outputOperation : demande.getProduits()) {
				String echeanceAccordee = outputOperation.getEcheanceAccordee().split("T")[0];
				String jour = echeanceAccordee.split("-")[2];
				int j = Integer.parseInt(jour);
				j++;
				String date = j + "-" + echeanceAccordee.split("-")[1] + "-" + echeanceAccordee.split("-")[0];
				outputOperation.setEcheanceAccordee(date);
				gestionConditionsPreferentielles.enregistrerOutputsOperation(outputOperation, referenceDemande);
			}
			generationDUD_PDF.setDemande(demande);
			generationDUD_PDF.genererEntete();
			generationDUD_PDF.genererTableau();
			return new ResponseEntity<Demande>(demande, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("Erreur lors de la création de la demande !");
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/api/cp/demande/{referenceDemande}", method = RequestMethod.DELETE)
	public ResponseEntity<Demande> supprimerDemandeCP(@PathVariable("referenceDemande") String referenceDemande) {
		try {
			gestionConditionsPreferentielles.supprimerOutputOperation(referenceDemande);
			gestionDemandeRepository
					.delete(gestionDemandeRepository.getDemandeByReferenceDemande(referenceDemande).get(0));
			return new ResponseEntity<Demande>(HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("Erreur lors de la suppression de l'output !");
			e.printStackTrace();
			return new ResponseEntity<Demande>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/api/cp/demande/{referenceDemande}/produit/{referenceProduit}", method = RequestMethod.DELETE)
	public void supprimerDemandeCP(@PathVariable("referenceDemande") String referenceDemande,
			@PathVariable("referenceProduit") String referenceProduit) {
		try {
			gestionConditionsPreferentielles.supprimerOutputOperation(referenceDemande, referenceProduit);
		} catch (Exception e) {
			System.err.println("Erreur lors de la suppression de l'output !");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/cp/{referenceDemande}/produits", method = RequestMethod.GET)
	public ResponseEntity<List<OutputOperation>> getProduits(
			@PathVariable("referenceDemande") String referenceDemande) {
		List<OutputOperation> res = new ArrayList<OutputOperation>();
		OutputOperation aux = gestionOutputOperationRepository.getOutputOperationByReferenceDemande(referenceDemande)
				.get(0);
		res.add(aux);
		for (int i = 1; i < gestionOutputOperationRepository.getOutputOperationByReferenceDemande(referenceDemande)
				.size(); i++)
			if (!(gestionOutputOperationRepository.getOutputOperationByReferenceDemande(referenceDemande).get(i)
					.getReferenceProduit().equals(aux.getReferenceProduit()))) {
				res.add(gestionOutputOperationRepository.getOutputOperationByReferenceDemande(referenceDemande).get(i));
				aux = gestionOutputOperationRepository.getOutputOperationByReferenceDemande(referenceDemande).get(i);
			}
		return new ResponseEntity<List<OutputOperation>>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/cp/{referenceDemande}", method = RequestMethod.GET)
	public ResponseEntity<List<Demande>> getDemande(@PathVariable("referenceDemande") String referenceDemande) {
		List<Demande> res = gestionDemandeRepository.getDemandeByReferenceDemande(referenceDemande);
		for (Demande demande : res)
			demande.setProduits(gestionOutputOperationRepository
					.getOutputOperationByReferenceDemande(demande.getReferenceDemande()));
		return (ResponseEntity<List<Demande>>) new ResponseEntity<List<Demande>>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/cp/demande/{referenceDemande}/produit/{referenceProduit}", method = RequestMethod.PUT)
	public ResponseEntity<OutputOperation> modifierProduit(@PathVariable("referenceDemande") String referenceDemande,
			@PathVariable("referenceProduit") String referenceProduit, @RequestBody OutputOperation outputOperation) {
		System.out.println(outputOperation.getCorrespondance());
		if ((outputOperation.getReferenceDemande().equals(referenceDemande))
				&& (outputOperation.getReferenceProduit().equals(referenceProduit)) && (outputOperation.getId() != 0)) {
			try {
				gestionConditionsPreferentielles.modifierOutputsOperation(outputOperation, referenceDemande);
			} catch (Exception e) {
				System.err.println("Erreur lors de la modification de l'output !");
				e.printStackTrace();
			}
			return new ResponseEntity<OutputOperation>(outputOperation, HttpStatus.OK);
		} else
			return new ResponseEntity<OutputOperation>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/api/cp/fichier_integration", method = RequestMethod.GET)
	public void genererFichierIntergation() {
		try {
			gestionConditionsPreferentielles.genererFichierIntegration();
		} catch (Exception e) {
			System.err.println("Erreur lors de la génération du fichier d'intégration !");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/cp/bdd_des_cp", method = RequestMethod.GET)
	public void genererBDDExcel() {
		try {
			gestionConditionsPreferentielles.genererBDDExcel();
		} catch (Exception e) {
			System.err.println("Erreur lors de la génération de BDD_des_CP.xls !");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/cp/{referenceDemande}/dud", method = RequestMethod.POST)
	public void stockerDUD(@RequestParam("fichier") MultipartFile file,
			@PathVariable("referenceDemande") String referenceDemande) {
		try {
			Files.copy(file.getInputStream(),
					Paths.get(CHEMIN_DUD, referenceDemande + "_" + file.getOriginalFilename()));
		} catch (Exception e) {
			System.err.println("Erreur lors de l'upload de la DUD !");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/cp/all_demandes", method = RequestMethod.GET)
	public ResponseEntity<List<Demande>> getAllDemandes() {
		List<Demande> demandes = gestionDemandeRepository.findAll();

		for (Demande demande : demandes)
			demande.setProduits(gestionOutputOperationRepository
					.getOutputOperationByReferenceDemande(demande.getReferenceDemande()));
		return new ResponseEntity<List<Demande>>(demandes, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/cp/demandes_validees_agence", method = RequestMethod.GET)
	public ResponseEntity<List<Demande>> getDemandesValideesParAgence() {
		List<Demande> demandes = gestionDemandeRepository.findAll();
		List<Demande> res = new ArrayList<Demande>();
		for (Demande demande : demandes)
			if (demande.isValideeAgence())
				res.add(demande);
		for (Demande demande : res)
			demande.setProduits(gestionOutputOperationRepository
					.getOutputOperationByReferenceDemande(demande.getReferenceDemande()));
		return new ResponseEntity<List<Demande>>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/cp/demandes/{referenceDemande}", method = RequestMethod.PUT)
	public void ajouterProduit(@PathVariable("referenceDemande") String referenceDemande,
			@RequestBody OutputOperation outputOperation) {
		try {

			gestionConditionsPreferentielles.ajouterProduit(referenceDemande, outputOperation);
		} catch (Exception e) {
			System.err.println("Erreur lors de l'ajout du produit de référence " + outputOperation.getReferenceProduit()
					+ " à la demande de référence " + referenceDemande + " !");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/cp/demande/{referenceDemande}", method = RequestMethod.PUT)
	public void modifierDemande(@PathVariable("referenceDemande") String referenceDemande,
			@RequestBody Demande demande) {
		if (demande.getDateValidationAgence() != null)
			if (demande.getDateValidationAgence().contains("T")) {
				String dateValidationAgence = demande.getDateValidationAgence().split("T")[0];
				String date = dateValidationAgence.split("-")[2] + "-" + dateValidationAgence.split("-")[1] + "-"
						+ dateValidationAgence.split("-")[0];
				demande.setDateValidationAgence(date);
			}
		if (demande.getDateValidationBO() != null)
			if (demande.getDateValidationBO().contains("T")) {
				String dateValidationBO = demande.getDateValidationAgence().split("T")[0];
				String date = dateValidationBO.split("-")[2] + "-" + dateValidationBO.split("-")[1] + "-"
						+ dateValidationBO.split("-")[0];
				demande.setDateValidationBO(date);
			}
		Demande dem = gestionDemandeRepository.getDemandeByReferenceDemande(referenceDemande).get(0);
		demande.setId(dem.getId());
		if (demande.getReferenceDemande().equals(referenceDemande))
			gestionConditionsPreferentielles.modifierDemande(demande);
	}

	@RequestMapping(value = "/api/cp/mail", method = RequestMethod.POST)
	public void stockerDUD(@RequestBody Mail mail) {
		envoiMail.setDestinataire(mail.getDestinataire());
		envoiMail.setSujet(mail.getSujet());
		envoiMail.setContenu(mail.getContenu());
		envoiMail.setReferenceDemande(mail.getReferenceDemande());
		try {
			envoiMail.envoyer();
		} catch (Exception e) {
			System.err.println("Erreur lors de l'envoi du mail !" + e);
		}
	}

	public void setGestionConditionsPreferentielles(GestionConditionsPreferentielles gestionConditionsPreferentielles) {
		this.gestionConditionsPreferentielles = gestionConditionsPreferentielles;
	}

	public void setGestionOutputOperationRepository(GestionOutputOperationRepository gestionOutputOperationRepository) {
		this.gestionOutputOperationRepository = gestionOutputOperationRepository;
	}

	public void setGestionDemandeRepository(GestionDemandeRepository gestionDemandeRepository) {
		this.gestionDemandeRepository = gestionDemandeRepository;
	}

	public void setGenerationDUD_PDF(GenerationDUD_PDF generationDUD_PDF) {
		this.generationDUD_PDF = generationDUD_PDF;
	}

	public void setEnvoiMail(EnvoiMail envoiMail) {
		this.envoiMail = envoiMail;
	}
}
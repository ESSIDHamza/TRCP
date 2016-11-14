package application.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.GestionDemandeRepository;
import application.dao.GestionOutputOperationRepository;
import application.modeles.Demande;
import application.modeles.OutputOperation;

@Service
public class GestionConditionsPreferentielles {
	@Autowired
	private GestionOutputOperationRepository gestionOutputOperationRepository;
	@Autowired
	private GestionDemandeRepository gestionDemandeRepository;
	private List<OutputOperation> recapA;
	private List<OutputOperation> recapB;
	private List<OutputOperation> recapC;
	private List<OutputOperation> recapD;
	private List<OutputOperation> recapE;
	private List<OutputOperation> recapF;
	private List<OutputOperation> recapG;

	public static final String CHEMIN_BDD_EXCEL = "C:\\Users\\Hamza\\workspace\\TRCP\\src\\main\\resources\\static\\";
	public static final String CHEMIN_FICHIER_INTEGRATION = "C:\\Users\\Hamza\\workspace\\TRCP\\src\\main\\resources\\static\\";

	public GestionConditionsPreferentielles() {
		super();
		try {
			chargerRecapsExcel();
		} catch (Exception e) {
			System.err.println("Erreur lors du chargement des récaps Excel dans la mémoire !");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void chargerRecapsExcel() throws Exception {
		recapA = new ArrayList<OutputOperation>();
		recapB = new ArrayList<OutputOperation>();
		recapC = new ArrayList<OutputOperation>();
		recapD = new ArrayList<OutputOperation>();
		recapE = new ArrayList<OutputOperation>();
		recapF = new ArrayList<OutputOperation>();
		recapG = new ArrayList<OutputOperation>();
		FileInputStream fileInputStream = new FileInputStream(new File("Gestion des CP.xlsx"));
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("Récap A (tnd)");
		for (int i = 1; i <= 216; i++) {
			if ((i <= 200) || (i >= 207)) {
				String correspondance = "A" + i;
				OutputOperation outputOperation = new OutputOperation();
				outputOperation.setNum(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
				outputOperation.setLibelle(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue());
				outputOperation.setDetailLib(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getStringCellValue());
				if (sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue()
						.equals("TND CONV.")) {
					outputOperation.setDevise("TND");
					outputOperation.setDeviseTND_CONV("TND CONV.");
				} else
					outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7)
							.getStringCellValue());
				try {
					outputOperation.setCodeOperation(sheet.getRow(getLigneCorrespondante(sheet, correspondance))
							.getCell(6).getStringCellValue());
				} catch (Exception e) {
					outputOperation.setCodeOperation((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance))
							.getCell(6).getNumericCellValue() + "");
				}
				try {
					outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8)
							.getStringCellValue());
				} catch (Exception e) {
					outputOperation.setNature(
							sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8).getNumericCellValue()
									+ "");
				}
				outputOperation.setObservation(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(14).getStringCellValue());
				try {
					outputOperation.setPack(sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9)
							.getStringCellValue());
				} catch (Exception e) {
					outputOperation.setPack((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9)
							.getNumericCellValue() + "");
				}
				try {
					outputOperation.setProduit(sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(10)
							.getStringCellValue());
				} catch (Exception e) {
					outputOperation.setProduit((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance))
							.getCell(10).getNumericCellValue() + "");
				}
				outputOperation.setNiveauForcage(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(11).getStringCellValue());
				outputOperation.setEnPlus(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(12).getStringCellValue());
				outputOperation.setEnMoins(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(13).getStringCellValue());
				recapA.add(outputOperation);
			}
		}
		sheet = workbook.getSheet("Récap B (dev)");
		for (int i = 1; i <= 143; i++) {
			OutputOperation outputOperation = new OutputOperation();
			String correspondance = "B" + i;
			outputOperation.setNum(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
			outputOperation.setLibelle(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue());
			outputOperation.setDetailLib(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getStringCellValue());
			if (sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue()
					.equals("TND CONV.")) {
				outputOperation.setDevise("TND");
				outputOperation.setDeviseTND_CONV("TND CONV.");
			} else
				outputOperation.setDevise(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue());
			try {
				outputOperation.setCodeOperation(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setCodeOperation((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance))
						.getCell(6).getNumericCellValue() + "");
			}
			try {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8).getNumericCellValue()
								+ "");
			}
			try {
				outputOperation.setObservation(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(14).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setObservation(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(14).getNumericCellValue()
								+ "");

			}
			try {
				outputOperation.setPack(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setPack((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9)
						.getNumericCellValue() + "");
			}
			try {
				outputOperation.setProduit(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(10).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setProduit((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(10)
						.getNumericCellValue() + "");
			}
			try {
				outputOperation.setNiveauForcage(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(11).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setNiveauForcage(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(11).getNumericCellValue()
								+ "");
			}
			try {
				outputOperation.setEnPlus(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(12).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setEnPlus(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(12).getNumericCellValue()
								+ "");
			}
			try {
				outputOperation.setEnMoins(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(13).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setEnMoins(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(13).getNumericCellValue()
								+ "");
			}
			if (outputOperation.getEnMoins().equals("CP"))
				outputOperation.setEnMoins(outputOperation.getValeur());
			outputOperation.setCorrespondanceAlgo(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(15).getStringCellValue());
			if (outputOperation.getValeurAlgo() != null)
				if (!outputOperation.getValeurAlgo().equals("")) {
					Sheet sheet2 = workbook.getSheet("Récap AL (Algo)");
					try {
						outputOperation.setValeurAlgoClt(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(6).getStringCellValue());
					} catch (Exception e) {
						outputOperation.setValeurAlgoClt(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(6).getNumericCellValue() + "");
					}
					try {
						outputOperation.setTarifsPreferentielsAlgo(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(8).getStringCellValue());
					} catch (Exception e) {
						outputOperation.setTarifsPreferentielsAlgo(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(8).getNumericCellValue() + "");
					}
					outputOperation.setIndexTarifAlgo(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(9).getStringCellValue());
					outputOperation.setIndexValeurAlgo(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(7).getStringCellValue());
					outputOperation.setTarifAccorde(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(5).getStringCellValue());
				}
			recapB.add(outputOperation);
		}
		sheet = workbook.getSheet("Récap C (eng)");
		for (int i = 1; i <= 33; i++) {
			String correspondance = "C" + i;
			OutputOperation outputOperation = new OutputOperation();
			outputOperation.setNum(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
			outputOperation.setLibelle(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue());
			outputOperation.setDetailLib(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getStringCellValue());
			if (sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getStringCellValue()
					.equals("TND CONV.")) {
				outputOperation.setDevise("TND");
				outputOperation.setDeviseTND_CONV("TND CONV.");
			} else
				outputOperation.setDevise(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getStringCellValue());
			outputOperation.setCodeOperation(
					(int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getNumericCellValue()
							+ "");
			try {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6).getNumericCellValue()
								+ "");
			}
			outputOperation.setObservation(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(12).getStringCellValue());
			outputOperation.setPack(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue());
			outputOperation.setProduit(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8).getStringCellValue());
			outputOperation.setNiveauForcage(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9).getNumericCellValue() + "");
			outputOperation.setEnPlus(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(10).getNumericCellValue() + "");
			outputOperation.setEnMoins(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(11).getNumericCellValue() + "");
			recapC.add(outputOperation);
		}
		sheet = workbook.getSheet("Récap D (placem)");
		for (int i = 1; i <= 51; i++) {
			String correspondance = "D" + i;
			OutputOperation outputOperation = new OutputOperation();
			outputOperation.setNum(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
			outputOperation.setLibelle("Placement");
			outputOperation.setDetailLib(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(1).getStringCellValue());
			if (sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue()
					.equals("TND CONV.")) {
				outputOperation.setDevise("TND");
				outputOperation.setDeviseTND_CONV("TND CONV.");
			} else
				outputOperation.setDevise(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue());
			outputOperation.setStandard(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getStringCellValue());
			outputOperation.setMontantMinPlacement(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getNumericCellValue() + "");
			outputOperation.setMontantMaxPlacement(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getNumericCellValue() + "");
			outputOperation.setDureeMinPlacement(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6).getStringCellValue());
			outputOperation.setDureeMaxPlacement(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue());
			outputOperation.setCodeOperation(
					(int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8).getNumericCellValue()
							+ "");
			try {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9).getNumericCellValue()
								+ "");
			}
			outputOperation.setObservation(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(11).getStringCellValue());
			outputOperation.setPack(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(12).getStringCellValue());
			outputOperation.setProduit(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(13).getStringCellValue());
			outputOperation.setNiveauForcage(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(14).getStringCellValue());
			outputOperation.setEnPlus(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(15).getStringCellValue());
			outputOperation.setEnMoins(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(16).getStringCellValue());
			recapD.add(outputOperation);
		}
		sheet = workbook.getSheet("Récap E (kit)");
		for (int i = 1; i <= 15; i++) {
			String correspondance = "E" + i;
			OutputOperation outputOperation = new OutputOperation();
			outputOperation.setNum(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
			outputOperation.setLibelle(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(1).getStringCellValue());
			outputOperation.setDetailLib(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue());
			if (sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9).getStringCellValue()
					.equals("TND CONV.")) {
				outputOperation.setDevise("TND");
				outputOperation.setDeviseTND_CONV("TND CONV.");
			} else
				outputOperation.setDevise(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(9).getStringCellValue());
			try {
				outputOperation.setCodeOperation((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance))
						.getCell(6).getNumericCellValue() + "");
			} catch (Exception e) {
				outputOperation.setCodeOperation(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6).getStringCellValue());
			}
			try {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(10).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(10).getNumericCellValue()
								+ "");
			}
			outputOperation.setPack(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(8).getStringCellValue());
			outputOperation.setProduit(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue());
			try {
				outputOperation.setStandard(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setStandard(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getNumericCellValue()
								+ "");
			}
			try {
				outputOperation.setValeurFixeKitTTC(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setValeurFixeKitTTC(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getNumericCellValue()
								+ "");
			}
			recapE.add(outputOperation);
		}
		sheet = workbook.getSheet("Récap F (UIB-Banking)");
		for (int i = 1; i <= 9; i++) {
			String correspondance = "F" + i;
			OutputOperation outputOperation = new OutputOperation();
			outputOperation.setNum(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
			outputOperation.setLibelle(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(1).getStringCellValue());
			outputOperation.setDetailLib(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getStringCellValue());
			outputOperation.setDevise(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getStringCellValue());
			try {
				outputOperation.setCodeOperation((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance))
						.getCell(3).getNumericCellValue() + "");
			} catch (RuntimeException exception) {
				outputOperation.setCodeOperation(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getStringCellValue());
			}
			outputOperation.setNature(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getStringCellValue());
			outputOperation.setProduit(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(7).getStringCellValue());
			try {
				outputOperation.setPack(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6).getStringCellValue());
			} catch (RuntimeException e) {
				outputOperation.setPack((int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(6)
						.getNumericCellValue() + "");
			}
			outputOperation.setObservation(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(11).getStringCellValue());
			recapF.add(outputOperation);
		}
		sheet = workbook.getSheet("Récap G (Date-Valeur)");
		for (int i = 1; i <= 115; i++) {
			String correspondance = "G" + i;
			OutputOperation outputOperation = new OutputOperation();
			outputOperation.setValeur("0");
			outputOperation.setNum(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(0).getStringCellValue());
			outputOperation.setLibelle(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(1).getStringCellValue());
			outputOperation.setDetailLib(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(1).getStringCellValue());
			if (sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getStringCellValue()
					.equals("TND CONV.")) {
				outputOperation.setDevise("TND");
				outputOperation.setDeviseTND_CONV("TND CONV.");
			} else
				outputOperation.setDevise(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(3).getStringCellValue());
			outputOperation.setCodeOperation(
					(int) sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(2).getNumericCellValue()
							+ "");
			try {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getStringCellValue());
			} catch (Exception e) {
				outputOperation.setNature(
						sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(4).getNumericCellValue()
								+ "");
			}
			outputOperation.setProduit(
					sheet.getRow(getLigneCorrespondante(sheet, correspondance)).getCell(5).getStringCellValue());
			recapG.add(outputOperation);
		}
		workbook.close();
		fileInputStream.close();
	}

	public String getNomFeuille(String correspondance) {
		char aux = correspondance.charAt(0);
		String nomFeuille = "";
		if (aux == 'A')
			nomFeuille = "Récap A (tnd)";
		else if (aux == 'B')
			nomFeuille = "Récap B (dev)";
		else if (aux == 'C')
			nomFeuille = "Récap C (eng)";
		else if (aux == 'D')
			nomFeuille = "Récap D (placem)";
		else if (aux == 'E')
			nomFeuille = "Récap E (kit)";
		else if (aux == 'F')
			nomFeuille = "Récap F (UIB-Banking)";
		else if (aux == 'G')
			nomFeuille = "Récap G (Date-Valeur)";
		return nomFeuille;
	}

	public int getLigneCorrespondante(Sheet sheet, String correspondance) {
		for (int i = 0; i <= sheet.getLastRowNum(); i++)
			if (sheet.getRow(i) != null)
				if (sheet.getRow(i).getCell(0) != null)
					if (sheet.getRow(i).getCell(0).getStringCellValue().equals(correspondance))
						return i;
		return -1;
	}

	public int getLigneCorrespondanteAlgo(Sheet sheet, String correspondance) {
		for (int i = 0; i <= sheet.getLastRowNum(); i++)
			if (sheet.getRow(i) != null)
				if (sheet.getRow(i).getCell(10) != null)
					try {
						if (sheet.getRow(i).getCell(10).getStringCellValue().equals(correspondance))
							return i;
					} catch (RuntimeException runtimeException) {
						int correspondanceNumber = Integer.parseInt(correspondance);
						if (sheet.getRow(i).getCell(10).getNumericCellValue() == correspondanceNumber)
							return i;
					}
		return -1;
	}

	public OutputOperation genererOutput(String referenceProduit, String operation, String assiette,
			String tarifStandard, String TTCOuHT, String indexTarif, String typeValeur, String montantPlacement,
			String nbrJoursPlacement, String pourcentageKit, String dateSaisie, String dateDerniereModification,
			String dateValidation, String tarifSollicite, String echeanceSollicitee, String tarifAccorde,
			String echeanceAccordee, String correspondance, String fiche, String nomEtPrenom, String codeAgence,
			String DR, String compte, String valeur) throws Exception {
		/*
		 * OutputOperation outputOperation = new OutputOperation(null,
		 * referenceProduit, operation, assiette, tarifStandard, TTCOuHT,
		 * indexTarif, typeValeur, montantPlacement, nbrJoursPlacement,
		 * pourcentageKit, dateSaisie, dateDerniereModification, dateValidation,
		 * tarifSollicite, echeanceSollicitee, tarifAccorde, echeanceAccordee,
		 * correspondance, fiche, nomEtPrenom, codeAgence, DR, compte, null,
		 * null, null, null, null, null, null, null, null, null, null, null,
		 * null, valeur, null, null, null, null, null, null, null, null, null,
		 * null, null, null);
		 */
		String[] correspondances = correspondance.split("-");
		/*
		 * FileInputStream fileInputStream = new FileInputStream(new File(
		 * "Gestion des CP.xlsx")); Workbook workbook =
		 * WorkbookFactory.create(fileInputStream);
		 */
		String nomFeuille = getNomFeuille(correspondance);
		OutputOperation res = null;
		if (nomFeuille.equals("Récap D (placem)"))
			for (OutputOperation output : getRecapD())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					break;
				}
		if (nomFeuille.equals("Récap A (tnd)"))
			for (OutputOperation output : getRecapA())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					break;
				}
		if (nomFeuille.equals("Récap C (eng)"))
			for (OutputOperation output : getRecapC())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					break;
				}
		if (nomFeuille.equals("Récap B (dev)"))
			for (OutputOperation output : getRecapB())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					break;
				}
		if (nomFeuille.equals("Récap F (UIB-Banking)"))
			for (OutputOperation output : getRecapF())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					break;
				}
		if (nomFeuille.equals("Récap G (Date-Valeur)"))
			for (OutputOperation output : getRecapG())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					break;
				}
		if (nomFeuille.equals("Récap E (kit)"))
			for (OutputOperation output : getRecapE())
				if (output.getNum().equals(correspondances[0])) {
					res = output;
					double a = Double.parseDouble(res.getStandard());
					System.out.println("######## " + a);
					double b = Double.parseDouble(res.getValeurFixeKitTTC());
					System.out.println("######## " + b);
					double valStdHT = (a - b) / 1.18;
					System.out.println("######## " + valStdHT);
					break;
				}
		// Sheet sheet = workbook.getSheet(nomFeuille);
		/*
		 * if (nomFeuille.equals("Récap D (placem)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle("Placement");
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(1).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2) .getStringCellValue());
		 * outputOperation.setStandard(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(3).getStringCellValue());
		 * outputOperation.setMontantMinPlacement(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(4).getNumericCellValue() + "");
		 * outputOperation.setMontantMaxPlacement(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getNumericCellValue() + "");
		 * outputOperation.setDureeMinPlacement(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(6).getStringCellValue());
		 * outputOperation.setDureeMaxPlacement(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue());
		 * outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(8).getNumericCellValue() + ""); try {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setNature(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9).getNumericCellValue() + ""); }
		 * outputOperation.setObservation(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(11).getStringCellValue());
		 * outputOperation.setPack( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12).getStringCellValue());
		 * outputOperation.setProduit(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(13).getStringCellValue());
		 * outputOperation.setNiveauForcage(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(14).getStringCellValue());
		 * outputOperation.setEnPlus( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(15).getStringCellValue());
		 * outputOperation.setEnMoins(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(16).getStringCellValue()); } else if
		 * (nomFeuille.equals("Récap A (tnd)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7) .getStringCellValue()); try {
		 * outputOperation.setCodeOperation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(6).getStringCellValue()); }
		 * catch (Exception e) { outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(6).getNumericCellValue() + ""); } try {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setNature(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8).getNumericCellValue() + ""); }
		 * outputOperation.setObservation(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(14).getStringCellValue()); try {
		 * outputOperation.setPack(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setPack((int)
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getNumericCellValue() + ""); } try {
		 * outputOperation.setProduit(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(10) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setProduit((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(10).getNumericCellValue() + ""); }
		 * outputOperation.setNiveauForcage(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(11).getStringCellValue());
		 * outputOperation.setEnPlus( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12).getStringCellValue());
		 * outputOperation.setEnMoins(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(13).getStringCellValue()); } else if
		 * (nomFeuille.equals("Récap C (eng)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(3).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5) .getStringCellValue());
		 * outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(4).getNumericCellValue() + ""); try {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(6) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setNature(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(6).getNumericCellValue() + ""); }
		 * outputOperation.setObservation(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12).getStringCellValue());
		 * outputOperation.setPack( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue());
		 * outputOperation.setProduit(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8).getStringCellValue());
		 * outputOperation.setNiveauForcage(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9).getNumericCellValue() + "");
		 * outputOperation.setEnPlus( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(10).getNumericCellValue() + "");
		 * outputOperation.setEnMoins(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(11).getNumericCellValue() + ""); } else
		 * if (nomFeuille.equals("Récap E (kit)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(1).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getStringCellValue()); try {
		 * outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(6).getNumericCellValue() + ""); } catch (Exception e) {
		 * outputOperation.setCodeOperation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(6).getStringCellValue()); } try
		 * {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(10) .getStringCellValue()); } catch
		 * (Exception e) {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(10) .getNumericCellValue() + ""); }
		 * outputOperation.setPack( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8).getStringCellValue());
		 * outputOperation.setProduit(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue()); try {
		 * outputOperation.setStandard(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])).getCell(3) .getStringCellValue()); }
		 * catch (Exception e) { outputOperation.setStandard(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(3).getNumericCellValue() + ""); } try {
		 * outputOperation.setValeurFixeKitTTC(sheet.getRow(
		 * getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(4).getStringCellValue()); } catch (Exception e) {
		 * outputOperation.setValeurFixeKitTTC(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(4).getNumericCellValue() + ""); } double
		 * a = Double.parseDouble(outputOperation.getStandard()); double b =
		 * Double.parseDouble(outputOperation.getValeurFixeKitTTC()); double
		 * valStdHT = (a - b) / 1.18; double pourcentage =
		 * Double.parseDouble(outputOperation.getPourcentageKit()); double res =
		 * valStdHT * (1 - (pourcentage / 100)); DecimalFormat decimalFormat =
		 * new DecimalFormat("0.###");
		 * outputOperation.setValeur(decimalFormat.format(res)); } else if
		 * (nomFeuille.equals("Récap G (Date-Valeur)")) {
		 * outputOperation.setValeur("0"); outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(1).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(1).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(3).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(3) .getStringCellValue());
		 * outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(2).getNumericCellValue() + ""); try {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(4) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setNature(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(4).getNumericCellValue() + ""); }
		 * outputOperation.setProduit(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getStringCellValue()); } else if
		 * (nomFeuille.equals("Récap B (dev)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7) .getStringCellValue()); try {
		 * outputOperation.setCodeOperation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(6).getStringCellValue()); }
		 * catch (Exception e) { outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(6).getNumericCellValue() + ""); } try {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setNature(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8).getNumericCellValue() + ""); } try {
		 * outputOperation.setObservation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(14).getStringCellValue()); }
		 * catch (Exception e) {
		 * outputOperation.setObservation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(14).getNumericCellValue() + "");
		 * 
		 * } try {
		 * outputOperation.setPack(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setPack((int)
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getNumericCellValue() + ""); } try {
		 * outputOperation.setProduit(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(10) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setProduit((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(10).getNumericCellValue() + ""); } try {
		 * outputOperation.setNiveauForcage(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(11).getStringCellValue()); }
		 * catch (Exception e) {
		 * outputOperation.setNiveauForcage(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(11).getNumericCellValue() + "");
		 * } try {
		 * outputOperation.setEnPlus(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12) .getStringCellValue()); } catch
		 * (Exception e) {
		 * outputOperation.setEnPlus(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12) .getNumericCellValue() + ""); } try
		 * {
		 * outputOperation.setEnMoins(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(13) .getStringCellValue()); } catch
		 * (Exception e) {
		 * outputOperation.setEnMoins(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(13) .getNumericCellValue() + ""); } if
		 * (outputOperation.getEnMoins().equals("CP"))
		 * outputOperation.setEnMoins(outputOperation.getValeur());
		 * outputOperation.setCorrespondanceAlgo(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(15).getStringCellValue()); if
		 * (outputOperation.getValeurAlgo() != null) if
		 * (!outputOperation.getValeurAlgo().equals("")) { Sheet sheet2 =
		 * workbook.getSheet("Récap AL (Algo)"); try {
		 * outputOperation.setValeurAlgoClt(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(6).getStringCellValue());
		 * } catch (Exception e) { outputOperation.setValeurAlgoClt(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(6).getNumericCellValue() +
		 * ""); } try { outputOperation.setTarifsPreferentielsAlgo(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(8).getStringCellValue());
		 * } catch (Exception e) { outputOperation.setTarifsPreferentielsAlgo(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(8).getNumericCellValue() +
		 * ""); } outputOperation.setIndexTarifAlgo(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(9).getStringCellValue());
		 * outputOperation.setIndexValeurAlgo(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(7).getStringCellValue());
		 * outputOperation.setTarifAccorde(
		 * sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
		 * outputOperation.getValeurAlgo())) .getCell(5).getStringCellValue());
		 * } } else if (getNomFeuille(correspondance).equals(
		 * "Récap F (UIB-Banking)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(1).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue());
		 * outputOperation.setDevise( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(4).getStringCellValue()); try {
		 * outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(3).getNumericCellValue() + ""); } catch (RuntimeException
		 * exception) {
		 * outputOperation.setCodeOperation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(3).getStringCellValue()); }
		 * outputOperation.setNature( sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getStringCellValue());
		 * outputOperation.setProduit(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue()); try {
		 * outputOperation.setPack(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(6) .getStringCellValue()); } catch
		 * (RuntimeException e) { outputOperation.setPack((int)
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(6) .getNumericCellValue() + ""); }
		 * outputOperation.setObservation(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(11).getStringCellValue()); } else if
		 * (nomFeuille.equals("Récap B (dev)")) { outputOperation.setNum(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(0).getStringCellValue());
		 * outputOperation.setLibelle(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(2).getStringCellValue());
		 * outputOperation.setDetailLib(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(5).getStringCellValue()); if
		 * (sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7).getStringCellValue() .equals(
		 * "TND CONV.")) { outputOperation.setDevise("TND");
		 * outputOperation.setDeviseTND_CONV("TND CONV."); } else
		 * outputOperation.setDevise(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(7) .getStringCellValue()); try {
		 * outputOperation.setCodeOperation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(6).getStringCellValue()); }
		 * catch (Exception e) { outputOperation.setCodeOperation((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(6).getNumericCellValue() + ""); } try {
		 * outputOperation.setNature(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setNature(
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(8).getNumericCellValue() + ""); } try {
		 * outputOperation.setObservation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(14).getStringCellValue()); }
		 * catch (Exception e) {
		 * outputOperation.setObservation(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(14).getNumericCellValue() + "");
		 * 
		 * } try {
		 * outputOperation.setPack(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setPack((int)
		 * sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(9) .getNumericCellValue() + ""); } try {
		 * outputOperation.setProduit(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(10) .getStringCellValue()); } catch
		 * (Exception e) { outputOperation.setProduit((int)
		 * sheet.getRow(getLigneCorrespondante(sheet, correspondances[0]))
		 * .getCell(10).getNumericCellValue() + ""); } try {
		 * outputOperation.setNiveauForcage(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(11).getStringCellValue()); }
		 * catch (Exception e) {
		 * outputOperation.setNiveauForcage(sheet.getRow(getLigneCorrespondante(
		 * sheet, correspondances[0])) .getCell(11).getNumericCellValue() + "");
		 * } try {
		 * outputOperation.setEnPlus(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12) .getStringCellValue()); } catch
		 * (Exception e) {
		 * outputOperation.setEnPlus(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(12) .getNumericCellValue() + ""); } try
		 * {
		 * outputOperation.setEnMoins(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(13) .getStringCellValue()); } catch
		 * (Exception e) {
		 * outputOperation.setEnMoins(sheet.getRow(getLigneCorrespondante(sheet,
		 * correspondances[0])).getCell(13) .getNumericCellValue() + ""); } }
		 * fileInputStream.close(); workbook.close();
		 */
		res.setReferenceProduit(referenceProduit);
		res.setOperation(operation);
		res.setAssiette(assiette);
		res.setTarifStandard(tarifStandard);
		res.setTTCOuHT(TTCOuHT);
		res.setIndexTarif(indexTarif);
		res.setTypeValeur(typeValeur);
		res.setMontantPlacement(montantPlacement);
		res.setNbrJoursPlacement(nbrJoursPlacement);
		res.setPourcentageKit(pourcentageKit);
		res.setTarifAccorde(tarifAccorde);
		res.setEcheanceAccordee(echeanceAccordee);
		res.setCorrespondance(correspondance);
		res.setValeur(valeur);
		if (nomFeuille.equals("Récap E (kit)")) {
			double a = Double.parseDouble(res.getStandard());
			double b = Double.parseDouble(res.getValeurFixeKitTTC());
			double valStdHT = (a - b) / 1.18;
			double pourcentage = Double.parseDouble(res.getPourcentageKit());
			double resKit = valStdHT * (1 - (pourcentage / 100));
			DecimalFormat decimalFormat = new DecimalFormat("0.###");
			res.setValeur(decimalFormat.format(resKit));
		}
		return res;
	}

	public void enregistrerOutputOperation(OutputOperation outputOperation, String correspondance) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(new File(CHEMIN_BDD_EXCEL + "BDD_des_CP.xls"));
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("Stocks");
		Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
		Cell cell = row.createCell(0);
		cell.setCellValue(outputOperation.getReferenceDemande());
		cell = row.createCell(1);
		cell.setCellValue(outputOperation.getReferenceProduit());
		cell = row.createCell(2);
		cell.setCellValue(outputOperation.getOperation());
		cell = row.createCell(3);
		cell.setCellValue(outputOperation.getAssiette());
		cell = row.createCell(4);
		cell.setCellValue(outputOperation.getTarifStandard());
		cell = row.createCell(5);
		cell.setCellValue(outputOperation.getTTCOuHT());
		cell = row.createCell(6);
		cell.setCellValue(outputOperation.getIndexTarif());
		cell = row.createCell(7);
		cell.setCellValue(outputOperation.getTypeValeur());
		cell = row.createCell(8);
		cell.setCellValue(outputOperation.getMontantPlacement());
		cell = row.createCell(9);
		cell.setCellValue(outputOperation.getNbrJoursPlacement());
		cell = row.createCell(10);
		cell.setCellValue(outputOperation.getPourcentageKit());
		cell = row.createCell(11);
		cell.setCellValue(outputOperation.getDateSaisie());
		cell = row.createCell(12);
		cell.setCellValue(outputOperation.getDateDerniereModification());
		cell = row.createCell(13);
		cell.setCellValue(outputOperation.getDateValidation());
		cell = row.createCell(14);
		cell.setCellValue(outputOperation.getTarifSollicite());
		cell = row.createCell(15);
		cell.setCellValue(outputOperation.getEcheanceSollicitee());
		cell = row.createCell(16);
		cell.setCellValue(outputOperation.getTarifAccorde());
		cell = row.createCell(17);
		cell.setCellValue(outputOperation.getEcheanceAccordee());
		cell = row.createCell(18);
		cell.setCellValue(correspondance);
		cell = row.createCell(19);
		cell.setCellValue(outputOperation.getFiche());
		cell = row.createCell(20);
		cell.setCellValue(outputOperation.getNomEtPrenom());
		cell = row.createCell(21);
		cell.setCellValue(outputOperation.getCodeAgence());
		cell = row.createCell(22);
		cell.setCellValue(outputOperation.getDR());
		cell = row.createCell(23);
		cell.setCellValue("");
		cell = row.createCell(24);
		cell.setCellValue(outputOperation.getCompte());
		cell = row.createCell(25);
		cell.setCellValue(outputOperation.getNum());
		cell = row.createCell(26);
		cell.setCellValue(outputOperation.getLibelle());
		cell = row.createCell(27);
		cell.setCellValue(outputOperation.getDetailLib());
		cell = row.createCell(28);
		cell.setCellValue(outputOperation.getDevise());
		cell = row.createCell(29);
		cell.setCellValue(outputOperation.getDeviseTND_CONV());
		cell = row.createCell(30);
		cell.setCellValue(outputOperation.getStandard());
		cell = row.createCell(31);
		cell.setCellValue(outputOperation.getMontantMinPlacement());
		cell = row.createCell(32);
		cell.setCellValue(outputOperation.getMontantMaxPlacement());
		cell = row.createCell(33);
		cell.setCellValue(outputOperation.getDureeMinPlacement());
		cell = row.createCell(34);
		cell.setCellValue(outputOperation.getDureeMaxPlacement());
		cell = row.createCell(35);
		cell.setCellValue(outputOperation.getValeurFixeKitTTC());
		cell = row.createCell(36);
		cell.setCellValue(outputOperation.getCodeOperation());
		cell = row.createCell(37);
		cell.setCellValue(outputOperation.getNature());
		cell = row.createCell(38);
		cell.setCellValue(outputOperation.getValeur());
		cell = row.createCell(39);
		cell.setCellValue(outputOperation.getObservation());
		cell = row.createCell(40);
		cell.setCellValue(outputOperation.getPack());
		cell = row.createCell(41);
		cell.setCellValue(outputOperation.getProduit());
		cell = row.createCell(42);
		cell.setCellValue(outputOperation.getNiveauForcage());
		cell = row.createCell(43);
		cell.setCellValue(outputOperation.getEnPlus());
		cell = row.createCell(44);
		cell.setCellValue(outputOperation.getEnMoins());
		cell = row.createCell(45);
		cell.setCellValue(outputOperation.getCorrespondanceAlgo());
		cell = row.createCell(46);
		cell.setCellValue(outputOperation.getValeurAlgoClt());
		cell = row.createCell(47);
		cell.setCellValue(outputOperation.getIndexValeurAlgo());
		cell = row.createCell(48);
		cell.setCellValue(outputOperation.getTarifsPreferentielsAlgo());
		cell = row.createCell(49);
		cell.setCellValue(outputOperation.getIndexTarifAlgo());
		cell = row.createCell(50);
		cell.setCellValue(outputOperation.getValeurAlgo());
		FileOutputStream fileOutputStream = new FileOutputStream(CHEMIN_BDD_EXCEL + "BDD_des_CP.xls");
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
		fileInputStream.close();
	}

	public void enregistrerOutputsOperation(OutputOperation outputOperation, String referenceDemande) throws Exception {
		String[] correspondances = outputOperation.getCorrespondance().split("-");
		for (String corresp : correspondances) {
			OutputOperation output = genererOutput(outputOperation.getReferenceProduit(),
					outputOperation.getOperation(), outputOperation.getAssiette(), outputOperation.getTarifStandard(),
					outputOperation.getTTCOuHT(), outputOperation.getIndexTarif(), outputOperation.getTypeValeur(),
					outputOperation.getMontantPlacement(), outputOperation.getNbrJoursPlacement(),
					outputOperation.getPourcentageKit(), outputOperation.getDateSaisie(),
					outputOperation.getDateDerniereModification(), outputOperation.getDateValidation(),
					outputOperation.getTarifSollicite(), outputOperation.getEcheanceSollicitee(),
					outputOperation.getTarifAccorde(), outputOperation.getEcheanceAccordee(), corresp,
					outputOperation.getFiche(), outputOperation.getNomEtPrenom(), outputOperation.getCodeAgence(),
					outputOperation.getDR(), outputOperation.getCompte(), outputOperation.getValeur());
			output.setReferenceDemande(referenceDemande);
			System.out.println("+++++" + outputOperation.getValeurAlgo());
			if (outputOperation.getValeurAlgo() != null)
				if (!outputOperation.getValeurAlgo().equals("")) {
					System.out.println("+++++" + outputOperation.getValeurAlgo());
					FileInputStream fileInputStream = new FileInputStream(new File("Gestion des CP.xlsx"));
					Workbook workbook = WorkbookFactory.create(fileInputStream);
					Sheet sheet2 = workbook.getSheet("Récap AL (Algo)");
					try {
						outputOperation.setValeurAlgoClt(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(6).getStringCellValue());
						System.out.println("----" + outputOperation.getValeurAlgoClt());
					} catch (Exception e) {
						outputOperation.setValeurAlgoClt(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(6).getNumericCellValue() + "");
						System.out.println("---" + outputOperation.getValeurAlgoClt());

					}
					try {
						outputOperation.setTarifsPreferentielsAlgo(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(8).getStringCellValue());
					} catch (Exception e) {
						outputOperation.setTarifsPreferentielsAlgo(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(8).getNumericCellValue() + "");
					}
					outputOperation.setIndexTarifAlgo(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(9).getStringCellValue());
					outputOperation.setIndexValeurAlgo(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(7).getStringCellValue());

					try {
						outputOperation.setTarifAccorde(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(5).getStringCellValue());
					} catch (Exception e) {
						outputOperation.setTarifAccorde(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(5).getNumericCellValue() + "");
					}

					// outputOperation.setTarifAccorde(
					// sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
					// outputOperation.getValeurAlgo()))
					// .getCell(5).getStringCellValue());
					workbook.close();
					fileInputStream.close();
				}

			if (outputOperation.getValeurAlgo() != null)
				if (!outputOperation.getValeurAlgo().equals("")) {
					System.out.println("+++++" + outputOperation.getValeurAlgo());
					FileInputStream fileInputStream = new FileInputStream(new File("Gestion des CP.xlsx"));
					Workbook workbook = WorkbookFactory.create(fileInputStream);
					Sheet sheet2 = workbook.getSheet("Récap AL (Algo)");
					try {
						output.setValeurAlgoClt(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(6).getStringCellValue());
						System.out.println("----" + outputOperation.getValeurAlgoClt());
					} catch (Exception e) {
						output.setValeurAlgoClt(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(6).getNumericCellValue() + "");
						System.out.println("---" + outputOperation.getValeurAlgoClt());

					}
					try {
						output.setTarifsPreferentielsAlgo(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(8).getStringCellValue());
					} catch (Exception e) {
						output.setTarifsPreferentielsAlgo(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(8).getNumericCellValue() + "");
					}
					output.setIndexTarifAlgo(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(9).getStringCellValue());
					output.setIndexValeurAlgo(
							sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
									.getCell(7).getStringCellValue());
					// output.setTarifAccorde(
					// sheet2.getRow(getLigneCorrespondanteAlgo(sheet2,
					// outputOperation.getValeurAlgo()))
					// .getCell(5).getStringCellValue());

					try {
						output.setTarifAccorde(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(5).getStringCellValue());
					} catch (Exception e) {
						output.setTarifAccorde(
								sheet2.getRow(getLigneCorrespondanteAlgo(sheet2, outputOperation.getValeurAlgo()))
										.getCell(5).getNumericCellValue() + "");
					}

					output.setValeurAlgo(outputOperation.getValeurAlgo());
					workbook.close();
					fileInputStream.close();
				}
			if ((corresp.equals("D38")) || (corresp.equals("D40")) || (corresp.equals("D42")) || (corresp.equals("D44"))
					|| corresp.equals("D46") || corresp.equals("D48"))
				output.setValeur(output.getNbrJoursPlacement());
			else if ((corresp.equals("C16")) || corresp.equals("C17") || corresp.equals("C23") || corresp.equals("C24")
					|| corresp.equals("C25") || corresp.equals("C26") || corresp.equals("C27") || corresp.equals("C28")
					|| corresp.equals("C29") || corresp.equals("C30") || corresp.equals("C21") || corresp.equals("C22")
					|| corresp.equals("C18") || corresp.equals("C19") || corresp.equals("C20"))
				output.setValeur(Double.parseDouble(output.getValeur()) * 1.2 + "");
			else if (corresp.equals("C31") || corresp.equals("C32") || corresp.equals("C33"))
				output.setValeur("-" + Double.parseDouble(output.getValeur()) * 1.2 + "");
			else if (corresp.equals("A214") || corresp.equals("A215") || corresp.equals("A216"))
				output.setValeur(Double.parseDouble(output.getValeur()) / 12 + "");
			/*
			 * else if (corresp.equals("B52") || corresp.equals("B53") ||
			 * corresp.equals("B60") || corresp.equals("B61") ||
			 * corresp.equals("B62") || corresp.equals("B63"))
			 * output.setValeur(Double.parseDouble(outputOperation
			 * .getTarifAccorde()) * 0.1 + ""); else if (corresp.equals("B4") ||
			 * corresp.equals("B5") || corresp.equals("B6") ||
			 * corresp.equals("B16") || corresp.equals("B17") ||
			 * corresp.equals("B18") || corresp.equals("B27") ||
			 * corresp.equals("B28") || corresp.equals("B32") ||
			 * corresp.equals("B33") || corresp.equals("B34") ||
			 * corresp.equals("B41") || corresp.equals("B42") ||
			 * corresp.equals("B43") || corresp.equals("B89") ||
			 * corresp.equals("B91") || corresp.equals("B100") ||
			 * corresp.equals("B116") || corresp.equals("B117") ||
			 * corresp.equals("B128") || corresp.equals("B129"))
			 * output.setValeur(Double.parseDouble(outputOperation
			 * .getValeurAlgo()) * 0.1 + ""); else if (corresp.equals("B4") ||
			 * corresp.equals("B5") || corresp.equals("B16") ||
			 * corresp.equals("B84") || corresp.equals("B90"))
			 * output.setValeur(Double.parseDouble(outputOperation
			 * .getValeurAlgo()) * 0.4 + ""); else if (corresp.equals("B65") ||
			 * corresp.equals("B69") || corresp.equals("B71") ||
			 * corresp.equals("B72") || corresp.equals("B86") ||
			 * corresp.equals("B87"))
			 * output.setValeur(Double.parseDouble(outputOperation
			 * .getValeurAlgo()) * 1.2 + "");
			 */
			else if ((corresp.equals("F1") || corresp.equals("F3") || corresp.equals("F6")))
				output.setValeur(outputOperation.getValeur());
			else if ((corresp.equals("F2") || corresp.equals("F4") || corresp.equals("F5")))
				output.setValeur("0");
			else if ((corresp.equals("F7") || corresp.equals("F8") || corresp.equals("F9")))
				output.setValeur("0");
			else {
				if (outputOperation.getValeur() != null)
					output.setValeur(outputOperation.getValeur());
				else
					output.setValeur(outputOperation.getValeurAlgo());
			}
			// enregistrerOutputOperation(output,
			// outputOperation.getCorrespondance()); // écrire dans "BDD des
			// CP.xls"
			output.setCorrespondance(outputOperation.getCorrespondance());

			// res.setValeur(decimalFormat.format(resultat));
			if (Integer.parseInt((output.getReferenceProduit())) >= 77
					&& (Integer.parseInt(output.getReferenceProduit()) <= 91)) {
				double a = Double.parseDouble(output.getStandard());
				double b = Double.parseDouble(output.getValeurFixeKitTTC());
				double valStdHT = (a - b) / 1.18;
				double pourcentage = Double.parseDouble(output.getPourcentageKit());
				double resKit = valStdHT * (1 - (pourcentage / 100));
				DecimalFormat decimalFormat = new DecimalFormat("0.###");
				output.setValeur(decimalFormat.format(resKit));
			}
			gestionOutputOperationRepository.save(output);
		}
	}

	public void genererBDDExcel() throws Exception {
		List<OutputOperation> outputOperations = gestionOutputOperationRepository.findAll();
		for (OutputOperation outputOperation : outputOperations) {
			Demande demande = gestionDemandeRepository
					.getDemandeByReferenceDemande(outputOperation.getReferenceDemande()).get(0);
			outputOperation.setCodeAgence(demande.getInformationsExternes().getCodeAgence());
			outputOperation.setNomEtPrenom(demande.getInformationsExternes().getNomEtPrenom());
			outputOperation.setDateDerniereModification(demande.getDateDerniereModification());
			outputOperation.setDateSaisie(outputOperation.getDateSaisie());
			outputOperation.setDateValidation(demande.getDateValidationBO());
			outputOperation.setCompte(demande.getInformationsExternes().getComptes().get(0));
			outputOperation.setFiche(demande.getInformationsExternes().getFiche());
			enregistrerOutputOperation(outputOperation, outputOperation.getCorrespondance());
		}
	}

	public List<Integer> getLignesCorrespondantesParReferenceDemande(Sheet sheet, String referenceDemande) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i <= sheet.getLastRowNum(); i++)
			if (sheet.getRow(i) != null)
				if (sheet.getRow(i).getCell(0) != null)
					if (sheet.getRow(i).getCell(0).getStringCellValue().equals(referenceDemande))
						res.add(i);
		return res;
	}

	public int getLigneCorrespondanteParReferenceProduit(Sheet sheet, String referenceDemande,
			String referenceProduit) {
		for (int i = 0; i <= sheet.getLastRowNum(); i++)
			if (sheet.getRow(i) != null)
				if (sheet.getRow(i).getCell(0) != null)
					if (sheet.getRow(i).getCell(0).getStringCellValue().equals(referenceDemande))
						if (sheet.getRow(i).getCell(1).getStringCellValue().equals(referenceProduit))
							return i;
		return -1;
	}

	public void supprimerOutputOperation(String referenceDemande) throws Exception {
		/*
		 * FileInputStream fileInputStream = new FileInputStream(new File(
		 * "BDD des CP.xls")); Workbook workbook =
		 * WorkbookFactory.create(fileInputStream); Sheet sheet =
		 * workbook.getSheet("Stocks"); for (int i :
		 * getLignesCorrespondantesParReferenceDemande(sheet, referenceDemande))
		 * { Row row = sheet.createRow(i); for (int j = 0; j <
		 * row.getPhysicalNumberOfCells(); j++) { Cell cell = row.createCell(j);
		 * cell.setCellValue(""); } } FileOutputStream fileOutputStream = new
		 * FileOutputStream("BDD des CP.xls"); workbook.write(fileOutputStream);
		 * workbook.close(); fileInputStream.close(); fileOutputStream.close();
		 */
		for (OutputOperation outputOperation : gestionOutputOperationRepository
				.getOutputOperationByReferenceDemande(referenceDemande))
			gestionOutputOperationRepository.delete(outputOperation);
	}

	public void supprimerOutputOperation(String referenceDemande, String referenceProduit) throws Exception {
		/*
		 * FileInputStream fileInputStream = new FileInputStream(new File(
		 * "BDD des CP.xls")); Workbook workbook =
		 * WorkbookFactory.create(fileInputStream); Sheet sheet =
		 * workbook.getSheet("Stocks"); int i =
		 * getLigneCorrespondanteParReferenceProduit(sheet, referenceDemande,
		 * referenceProduit); Row row = sheet.createRow(i); for (int j = 0; j <
		 * row.getPhysicalNumberOfCells(); j++) { Cell cell = row.createCell(j);
		 * cell.setCellValue(""); } FileOutputStream fileOutputStream = new
		 * FileOutputStream("BDD des CP.xls"); workbook.write(fileOutputStream);
		 * workbook.close(); fileInputStream.close(); fileOutputStream.close();
		 */
		for (OutputOperation outputOperation : gestionOutputOperationRepository
				.getOutputOperationByReferenceDemande(referenceDemande))
			if (outputOperation.getReferenceProduit().equals(referenceProduit))
				gestionOutputOperationRepository.delete(outputOperation);
	}

	public void modifierOutputOperation(OutputOperation outputOperation, String referenceDemande,
			String referenceProduit) throws Exception {
		/*
		 * FileInputStream fileInputStream = new FileInputStream(new File(
		 * "BDD des CP.xls")); Workbook workbook =
		 * WorkbookFactory.create(fileInputStream); Sheet sheet =
		 * workbook.getSheet("Stocks"); int i =
		 * getLigneCorrespondanteParReferenceProduit(sheet, referenceDemande,
		 * referenceProduit); Row row = sheet.createRow(i); Cell cell =
		 * row.createCell(0);
		 * cell.setCellValue(outputOperation.getReferenceDemande()); cell =
		 * row.createCell(1);
		 * cell.setCellValue(outputOperation.getReferenceProduit()); cell =
		 * row.createCell(2); cell.setCellValue(outputOperation.getOperation());
		 * cell = row.createCell(3);
		 * cell.setCellValue(outputOperation.getAssiette()); cell =
		 * row.createCell(4);
		 * cell.setCellValue(outputOperation.getTarifStandard()); cell =
		 * row.createCell(5); cell.setCellValue(outputOperation.getTTCOuHT());
		 * cell = row.createCell(6);
		 * cell.setCellValue(outputOperation.getIndexTarif()); cell =
		 * row.createCell(7);
		 * cell.setCellValue(outputOperation.getTypeValeur()); cell =
		 * row.createCell(8);
		 * cell.setCellValue(outputOperation.getMontantPlacement()); cell =
		 * row.createCell(9);
		 * cell.setCellValue(outputOperation.getNbrJoursPlacement()); cell =
		 * row.createCell(10);
		 * cell.setCellValue(outputOperation.getPourcentageKit()); cell =
		 * row.createCell(11);
		 * cell.setCellValue(outputOperation.getDateSaisie()); cell =
		 * row.createCell(12);
		 * cell.setCellValue(outputOperation.getDateDerniereModification());
		 * cell = row.createCell(13);
		 * cell.setCellValue(outputOperation.getDateValidation()); cell =
		 * row.createCell(14);
		 * cell.setCellValue(outputOperation.getTarifSollicite()); cell =
		 * row.createCell(15);
		 * cell.setCellValue(outputOperation.getEcheanceSollicitee()); cell =
		 * row.createCell(16);
		 * cell.setCellValue(outputOperation.getTarifAccorde()); cell =
		 * row.createCell(17);
		 * cell.setCellValue(outputOperation.getEcheanceAccordee()); cell =
		 * row.createCell(18);
		 * cell.setCellValue(outputOperation.getCorrespondance()); cell =
		 * row.createCell(19); cell.setCellValue(outputOperation.getFiche());
		 * cell = row.createCell(20);
		 * cell.setCellValue(outputOperation.getNomEtPrenom()); cell =
		 * row.createCell(21);
		 * cell.setCellValue(outputOperation.getCodeAgence()); cell =
		 * row.createCell(22); cell.setCellValue(outputOperation.getDR()); cell
		 * = row.createCell(23); cell.setCellValue(""); cell =
		 * row.createCell(24); cell.setCellValue(outputOperation.getCompte());
		 * cell = row.createCell(25);
		 * cell.setCellValue(outputOperation.getNum()); cell =
		 * row.createCell(26); cell.setCellValue(outputOperation.getLibelle());
		 * cell = row.createCell(27);
		 * cell.setCellValue(outputOperation.getDetailLib()); cell =
		 * row.createCell(28); cell.setCellValue(outputOperation.getDevise());
		 * cell = row.createCell(29);
		 * cell.setCellValue(outputOperation.getDeviseTND_CONV()); cell =
		 * row.createCell(30); cell.setCellValue(outputOperation.getStandard());
		 * cell = row.createCell(31);
		 * cell.setCellValue(outputOperation.getMontantMinPlacement()); cell =
		 * row.createCell(32);
		 * cell.setCellValue(outputOperation.getMontantMaxPlacement()); cell =
		 * row.createCell(33);
		 * cell.setCellValue(outputOperation.getDureeMinPlacement()); cell =
		 * row.createCell(34);
		 * cell.setCellValue(outputOperation.getDureeMaxPlacement()); cell =
		 * row.createCell(35);
		 * cell.setCellValue(outputOperation.getValeurFixeKitTTC()); cell =
		 * row.createCell(36);
		 * cell.setCellValue(outputOperation.getCodeOperation()); cell =
		 * row.createCell(37); cell.setCellValue(outputOperation.getNature());
		 * cell = row.createCell(38);
		 * cell.setCellValue(outputOperation.getValeur()); cell =
		 * row.createCell(39);
		 * cell.setCellValue(outputOperation.getObservation()); cell =
		 * row.createCell(40); cell.setCellValue(outputOperation.getPack());
		 * cell = row.createCell(41);
		 * cell.setCellValue(outputOperation.getProduit()); cell =
		 * row.createCell(42);
		 * cell.setCellValue(outputOperation.getNiveauForcage()); cell =
		 * row.createCell(43); cell.setCellValue(outputOperation.getEnPlus());
		 * cell = row.createCell(44);
		 * cell.setCellValue(outputOperation.getEnMoins()); cell =
		 * row.createCell(45);
		 * cell.setCellValue(outputOperation.getCorrespondanceAlgo()); cell =
		 * row.createCell(46);
		 * cell.setCellValue(outputOperation.getValeurAlgoClt()); cell =
		 * row.createCell(47);
		 * cell.setCellValue(outputOperation.getIndexValeurAlgo()); cell =
		 * row.createCell(48);
		 * cell.setCellValue(outputOperation.getTarifsPreferentielsAlgo()); cell
		 * = row.createCell(49);
		 * cell.setCellValue(outputOperation.getIndexTarifAlgo()); cell =
		 * row.createCell(50);
		 * cell.setCellValue(outputOperation.getValeurAlgo()); FileOutputStream
		 * fileOutputStream = new FileOutputStream("BDD des CP.xls");
		 * workbook.write(fileOutputStream); workbook.close();
		 * fileInputStream.close(); fileOutputStream.close();
		 */
		gestionOutputOperationRepository.save(outputOperation);
	}

	public void modifierOutputsOperation(OutputOperation outputOperation, String referenceDemande) throws Exception {
		String[] correspondances = outputOperation.getCorrespondance().split("-");
		for (String corresp : correspondances)
			if (!gestionOutputOperationRepository.getOutputOperationByNum(corresp).isEmpty())
				for (OutputOperation output : gestionOutputOperationRepository.getOutputOperationByNum(corresp))
					if (output.getReferenceDemande().equals(referenceDemande)) {
						output.setTarifAccorde(outputOperation.getTarifAccorde());
						output.setEcheanceAccordee(outputOperation.getEcheanceAccordee());
						output.setMontantPlacement(outputOperation.getMontantPlacement());
						output.setNbrJoursPlacement(outputOperation.getNbrJoursPlacement());
						output.setPourcentageKit(outputOperation.getPourcentageKit());
						modifierOutputOperation(output, outputOperation.getReferenceDemande(),
								output.getReferenceProduit());
					}
	}

	public static String formatterDate(String date) {
		String[] aux = date.split("T");
		aux = aux[0].split("-");
		return aux[2] + "-" + aux[1] + "-" + aux[0];
	}

	public void genererFichierIntegration() throws Exception {
		Calendar calendar = Calendar.getInstance();
		String dateAujourdhui = "";
		if (calendar.get(Calendar.MONTH) + 1 < 10)
			dateAujourdhui = calendar.get(Calendar.DAY_OF_MONTH) + "-0" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.YEAR);
		else
			dateAujourdhui = calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.YEAR);
		if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
			dateAujourdhui = "0" + dateAujourdhui;
		List<Demande> demandes = gestionDemandeRepository.findAll();
		List<Demande> demandesValideesBO = new ArrayList<Demande>();
		for (Demande demande : demandes)
			if (demande.getDateValidationBO() != null) {
				System.out.println("*****" + dateAujourdhui + "*****");
				System.out.println("+++++" + formatterDate(demande.getDateValidationBO()) + "+++++");
				if (demande.isValideeBO() && (formatterDate(demande.getDateValidationBO()).equals(dateAujourdhui)))
					demandesValideesBO.add(demande);
			}
		List<OutputOperation> outputs = new ArrayList<OutputOperation>();
		for (Demande demande : demandesValideesBO) {
			demande.setProduits(gestionOutputOperationRepository
					.getOutputOperationByReferenceDemande(demande.getReferenceDemande()));
			for (OutputOperation outputOperation : demande.getProduits()) {
				outputOperation.setDateValidation(demande.getDateValidationBO());
				outputs.add(outputOperation);
			}
		}
		FileOutputStream fileOutputStream = new FileOutputStream(
				new File(CHEMIN_FICHIER_INTEGRATION + "Fichier d'integration_" + dateAujourdhui + ".xls"));
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Stocks");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Agence");
		cell = row.createCell(1);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Package");
		cell = row.createCell(2);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Produit");
		cell = row.createCell(3);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Opération");
		cell = row.createCell(4);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Devise");
		cell = row.createCell(5);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Client");
		cell = row.createCell(6);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Compte");
		cell = row.createCell(7);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Nature");
		cell = row.createCell(8);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Date d'application");
		cell = row.createCell(9);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Fin d'application");
		cell = row.createCell(10);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Niveau de forçage");
		cell = row.createCell(11);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("En +");
		cell = row.createCell(12);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("En -");
		cell = row.createCell(13);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Valeur");
		int i = 1;
		for (OutputOperation outputOperation : outputs) {
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(outputOperation.getCodeAgence());
			cell = row.createCell(1);
			cell.setCellValue(outputOperation.getPack());
			cell = row.createCell(2);
			cell.setCellValue(outputOperation.getProduit());
			cell = row.createCell(3);
			cell.setCellValue(outputOperation.getCodeOperation());
			cell = row.createCell(4);
			cell.setCellValue(outputOperation.getDevise());
			cell = row.createCell(5);
			cell.setCellValue(outputOperation.getNomEtPrenom());
			cell = row.createCell(6);
			cell.setCellValue(outputOperation.getCompte());
			cell = row.createCell(7);
			cell.setCellValue(outputOperation.getNature());
			cell = row.createCell(8);
			cell.setCellValue(formatterDate(outputOperation.getDateValidation()).replace('-', '/'));
			cell = row.createCell(9);
			cell.setCellValue(formatterDate(outputOperation.getEcheanceAccordee()).replace('-', '/'));
			cell = row.createCell(10);
			if (outputOperation.getNiveauForcage() != null)
				cell.setCellValue(outputOperation.getNiveauForcage().replace('.', ','));
			cell = row.createCell(11);
			if (outputOperation.getEnPlus() != null)
				cell.setCellValue(outputOperation.getEnPlus().replace('.', ','));
			cell = row.createCell(12);
			if (outputOperation.getEnMoins() != null)
				cell.setCellValue(outputOperation.getEnMoins().replace('.', ','));
			cell = row.createCell(13);
			if (outputOperation.getValeur() != null)
				cell.setCellValue(outputOperation.getValeur().replace('.', ','));
			i++;
		}
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
	}

	public void ajouterProduit(String referenceDemande, OutputOperation outputOperation) throws Exception {
		Demande demande = gestionDemandeRepository.getDemandeByReferenceDemande(referenceDemande).get(0);
		// demande.getProduits().add(outputOperation);
		enregistrerOutputsOperation(outputOperation, referenceDemande);
		gestionDemandeRepository.save(demande);
	}

	public void modifierDemande(Demande demande) {
		gestionDemandeRepository.save(demande);
	}

	public void setGestionOutputOperationRepository(GestionOutputOperationRepository gestionOutputOperationRepository) {
		this.gestionOutputOperationRepository = gestionOutputOperationRepository;
	}

	public void setGestionDemandeRepository(GestionDemandeRepository gestionDemandeRepository) {
		this.gestionDemandeRepository = gestionDemandeRepository;
	}

	public List<OutputOperation> getRecapA() {
		return recapA;
	}

	public List<OutputOperation> getRecapB() {
		return recapB;
	}

	public List<OutputOperation> getRecapC() {
		return recapC;
	}

	public List<OutputOperation> getRecapD() {
		return recapD;
	}

	public List<OutputOperation> getRecapE() {
		return recapE;
	}

	public List<OutputOperation> getRecapF() {
		return recapF;
	}

	public List<OutputOperation> getRecapG() {
		return recapG;
	}
}
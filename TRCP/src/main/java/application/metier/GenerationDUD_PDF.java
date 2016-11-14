package application.metier;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import application.controlleur.GestionConditionsPreferentiellesController;
import application.modeles.Demande;
import application.modeles.OutputOperation;

@Service
public class GenerationDUD_PDF {
	private Demande demande;
	private Document document;

	public GenerationDUD_PDF() {
		super();
	}

	public GenerationDUD_PDF(Demande demande) {
		super();
		this.demande = demande;
	}

	public void genererEntete() throws Exception {
		document = new Document(PageSize.A4.rotate());
		PdfWriter.getInstance(document, new FileOutputStream(
				GestionConditionsPreferentiellesController.CHEMIN_DUD + "\\" + demande.getReferenceDemande() + ".pdf"));
		document.open();
		Paragraph entete = new Paragraph();
		Image logoUIB = Image.getInstance("logo_uib.gif");
		Paragraph titre = new Paragraph("DEMANDE DE TARIFS PREFERENTIELS " + demande.getReferenceDemande());
		titre.setAlignment(Element.ALIGN_CENTER);
		Chunk relation = new Chunk("Hamza ESSID"); // demande.getInformationsExternes().getNomEtPrenom()
													// ;
		Chunk DR = new Chunk("DRTS"); // demande.getInformationsExternes().getDR()
										// ;
		Chunk agence = new Chunk("ETATS UNIS"); // demande.getInformationsExternes().getCodeAgence()
												// ;
		Calendar calendar = Calendar.getInstance();
		Chunk date = new Chunk(calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.YEAR));
		Chunk glue = new Chunk(new VerticalPositionMark());
		Chunk numCompte = new Chunk("00051001248"); // demande.getInformationsExternes().getCompte()
													// ;
		Chunk numFiche = new Chunk("01461505"); // demande.getInformationsExternes().getFiche()
												// ;
		entete.add(logoUIB);
		entete.add(titre);
		entete.add("RELATION : " + relation);
		entete.add(glue);
		entete.add("DR : " + DR);
		entete.add(glue);
		entete.add("AGENCE : " + agence);
		entete.add(glue);
		entete.add("DATE : " + date + "\n");
		entete.add("Numéro du compte : " + numCompte);
		entete.add(glue);
		entete.add("Numéro de la Fiche Client : " + numFiche + "\n");
		entete.add("\n\n\n");
		document.add(entete);
	}

	public void genererTableau() throws Exception {
		PdfPTable table = new PdfPTable(new float[] { 3, 1, 1, 1, 1, 1 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell("Opération");
		table.addCell("Tarif standard");
		table.addCell("Tarif sollicité");
		table.addCell("Echéance sollicitée");
		table.addCell("Tarif accordé");
		table.addCell("Echéance accordée");
		table.setHeaderRows(1);
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int i = 0; i < cells.length; i++)
			cells[i].setBackgroundColor(BaseColor.GRAY);
		for (OutputOperation outputOperation : demande.getProduits()) {
			table.addCell(outputOperation.getOperation());
			table.addCell(outputOperation.getTarifStandard());
			table.addCell(outputOperation.getTarifAccorde());
			table.addCell(GestionConditionsPreferentielles.formatterDate(outputOperation.getEcheanceAccordee()));
			table.addCell("");
			table.addCell("");
		}
		document.add(table);
		document.close();
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}
}
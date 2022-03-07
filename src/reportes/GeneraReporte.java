package reportes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import javafx.scene.paint.Color;

/**
 * Example of using the iText library to work with PDF documents on Java, lets
 * you create, analyze, modify and maintain documents in this format. Ejemplo de
 * uso de la librería iText para trabajar con documentos PDF en Java, nos
 * permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 *         Puedes seguirme en mi web http://www.codigoxules.org
 */
public class GeneraReporte {
	// Fonts definitions (Definición de fuentes).
	private static final Font chapterFont = FontFactory.getFont(FontFactory.COURIER, 4, Font.DEFAULTSIZE);
	private static final Font titulo = FontFactory.getFont(FontFactory.COURIER, 20, Font.NORMAL);
	private Chunk chunk;
	private Document document;
	private Chapter chapTitle;
	private Paragraph paragraph;
	private Paragraph paragraphMore;
	private Paragraph encabezado;
	private Paragraph espacio = new Paragraph();

	public void setdoc(Paragraph parrafo) {
		try {
			document.add(parrafo);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPie(String titulos, String valore, int formato) {

		switch (formato) {
		case 1:
			PdfPTable table = new PdfPTable(2);
			Font fuente = new Font();
			fuente.setColor(BaseColor.RED);
			PdfPTable table1 = new PdfPTable(2);
			PdfPCell cell1 = new PdfPCell(new Phrase(valore, fuente));
			table1.addCell(titulos);
			table1.addCell(cell1);

			PdfPCell cellOne = new PdfPCell();
			PdfPCell cellTwo = new PdfPCell(table1);
			cellOne.setBorder(Rectangle.NO_BORDER);
//			cellOne.setBorderColor(new Color(255, 255, 45));
			cellTwo.setBorder(Rectangle.NO_BORDER);
			table.addCell(cellOne);
			table.addCell(cellTwo);
			try {
				document.add(espacio);
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

	}

	public void setEncabezado(String titulo, Object[][] name, int setBorder) {

		PdfPTable tabla = new PdfPTable(name[0].length);

		for (int row = 0; row < name.length; row++) {
			for (int column = 0; column < name[0].length; column++) {
				tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);

				if (setBorder != -1) {
					tabla.getDefaultCell().setBorder(setBorder);
				}
				tabla.addCell(name[row][column].toString());
			}
		}
		espacio.add("\n");

		try {
			Paragraph titulo1 = new Paragraph();
			titulo1.setAlignment(Paragraph.ALIGN_CENTER);
			titulo1.setFont(FontFactory.getFont("Times New Roman", 20, Font.BOLD, BaseColor.BLUE));
			titulo1.add(titulo);
			document.add(titulo1);
			document.add(espacio);
			document.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTabla(LinkedList<String> columnas, LinkedList<LinkedList<String>> filas, float[] tamanioCelda,
			int setBorder) {
		// Utilización de PdfPTable
		// Usamos varios elementos para añadir título y subtítulo
		Anchor anchor = new Anchor("", chapterFont);

//		chapTitle = new Chapter(new Paragraph(anchor), 1);
//		paragraph = new Paragraph();
//		paragraphMore = chapTitle.addSection(paragraph);
//		paragraphMore.add(new Paragraph("This is a simple example (Este es un ejemplo sencillo)"));

		// (Creamos la tabla).
		Paragraph saltolinea1 = new Paragraph();
		saltolinea1.add("\n\n");
		try {
			document.add(saltolinea1);

			PdfPTable table = new PdfPTable(columnas.size());

			// Now we fill the PDF table
			// Ahora llenamos la tabla del PDF
			if (tamanioCelda != null) {
				table.setWidths(tamanioCelda);
			}

			paragraphMore = new Paragraph();
			// Fill table rows (rellenamos las filas de la tabla).
			for (int column = 0; column < columnas.size(); column++) {
				PdfPCell columnHeader;

				columnHeader = new PdfPCell(new Phrase(columnas.get(column).toString()));
				columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(columnHeader);
			}
			table.setHeaderRows(1);

			// (rellenamos las filas de la tabla).
			for (int row = 0; row < filas.size(); row++) {
				for (int column = 0; column < columnas.size(); column++) {
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
					if (setBorder != -1) {
						table.getDefaultCell().setBorder(setBorder);
					}

					table.addCell(filas.get(row).get(column).toString());

				}
			}
			table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);

			// (Añadimos la tabla)
			paragraphMore.add(table);
			document.add(paragraphMore);
			// We add the paragraph with the table (Añadimos el elemento con la tabla).
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean print() {
		if (document != null) {
			try {
				if (chapTitle != null) {
					document.add(chapTitle);
				}

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				return false;
			}
			document.close();
			return true;
		}

		return false;
	}

	public void setResto() {
		// Segunda página - Algunos elementos
		Chapter chapSecond = new Chapter(new Paragraph(new Anchor("Some elements (Añadimos varios elementos)")), 1);
		Paragraph paragraphS = new Paragraph("Do it by Xules (Realizado por Xules)", chapterFont);

		// (subrayando un párrafo por iText)
		Paragraph paragraphE = new Paragraph(
				"This line will be underlined with a dotted line (Está línea será subrayada con una línea de puntos).");
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		paragraphE.add(dottedline);
		chapSecond.addSection(paragraphE);

		Section paragraphMoreS = chapSecond.addSection(paragraphS);
		// (listas por iText)
		String text = "test 1 2 3 ";
		for (int i = 0; i < 5; i++) {
			text = text + text;
		}
		List list = new List(List.UNORDERED);
		ListItem item = new ListItem(text);
		item.setAlignment(Element.ALIGN_JUSTIFIED);
		list.add(item);
		text = "a b c align ";
		for (int i = 0; i < 5; i++) {
			text = text + text;
		}
		item = new ListItem(text);
		item.setAlignment(Element.ALIGN_JUSTIFIED);
		list.add(item);
		text = "supercalifragilisticexpialidocious ";
		for (int i = 0; i < 3; i++) {
			text = text + text;
		}
		item = new ListItem(text);
		item.setAlignment(Element.ALIGN_JUSTIFIED);
		list.add(item);
		paragraphMoreS.add(list);
		try {
			document.add(chapSecond);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
	}

	/**
	 * Creamos un documento PDF con iText usando diferentes elementos para aprender
	 * a usar esta librería.
	 * 
	 * @param pdfNewFile <code>String</code> pdf Fichero pdf en el que vamos a
	 *                   escribir.
	 */
	public void createPDF(String ubicacion, String titulo) {

		// Creamos el documento e indicamos el nombre del fichero.
		try {
			document = new Document(PageSize.A3, 10, 10, 10, 10);
			try {
				File pdfNewFile = new File(ubicacion + ".pdf");

				PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));

			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();

			// Añadimos los metadatos del PDF
			document.addTitle(ubicacion);
			document.addSubject(ubicacion);
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("Código Xules");
			document.addCreator("Código Xules");

		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}
}
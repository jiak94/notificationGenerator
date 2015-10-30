package generator.PdfUtil;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import generator.model.Number;

public class PdfGenerator {
	public static void generatePdf(String filePath, Number number) {
		Document document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			Paragraph nofiticationNumber = new Paragraph("03-" + number.getNumber());
			nofiticationNumber.setAlignment(Element.ALIGN_RIGHT);
			
			document.setPageSize(PageSize.A4);
			
			
			document.open();
			document.add(nofiticationNumber);
			document.close();
			
			if (Desktop.isDesktopSupported()) {
				Desktop desk = Desktop.getDesktop();
				
				File file = new File(filePath);
				
				desk.open(file);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

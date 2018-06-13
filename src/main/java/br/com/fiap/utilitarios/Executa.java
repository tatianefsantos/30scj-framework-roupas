package br.com.fiap.utilitarios;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class Executa {

	public static void main(String[] args) 
	{
		Document document = new Document();
		try 
		{
			PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream("d:\\teste\\PDF_Teste.pdf"));
			document.open();

			// adicionando um paragrafo no documento
			document.add(new Paragraph("Gerando PDF - Java"));
			document.add(new Paragraph("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"));
			document.add(new Paragraph("Aluno: Marcos Roberto Macedo"));
			document.add(new Paragraph("Curso: MBA em Desenvolvimento de Aplicações Java"));
			document.add(new Paragraph("Ano: 2017/1"));
			document.add(new Paragraph("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n\n"));
			
			PdfContentByte cb = writer.getDirectContent();
		    Barcode128 barcode128 = new Barcode128();
	        barcode128.setCode("teste");
	        barcode128.setCodeType(Barcode.CODE128);
	        Image code128Image = barcode128.createImageWithBarcode(cb, null, null);
	        document.add(code128Image);
			
			BarcodeQRCode barcodeQRCode = new BarcodeQRCode("teste qrcode", 1000, 1000, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.scaleAbsolute(100, 100);
			document.add(codeQrImage);

			document.addSubject("Gerando PDF em Java");
			document.addKeywords("www.fiap.com.br");
			document.addCreator("by 29SCJ");
			document.addAuthor("Marcos Macedo");
			
		} 
		catch (  DocumentException de ) 
		{
			System.err.println(de.getMessage());
		} 
		catch ( IOException ioe ) 
		{
			System.err.println(ioe.getMessage());
		}
		document.close();
	}
}
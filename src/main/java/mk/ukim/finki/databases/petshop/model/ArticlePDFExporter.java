package mk.ukim.finki.databases.petshop.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import mk.ukim.finki.databases.petshop.model.Article;


public class ArticlePDFExporter {
    private List<Article> listArticles;

    public ArticlePDFExporter(List<Article> listArticles) {
        this.listArticles = listArticles;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Article ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Serial Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Supplier ID", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}









/*
public class ArticlePDFexporter {
    private List<Article> listArticles;

    public ArticlePDFexporter(List<Article> listArticles){
        this.listArticles=listArticles;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell= new PdfPCell();

        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


    }
    private void writeTableData(PdfPTable table){
        for (Article article : listArticles) {
            table.addCell(String.valueOf(article.getIdArticle()));
            table.addCell(article.getDescription());
            table.addCell(article.getName());
            table.addCell(String.valueOf(article.getPrice()));
            table.addCell(String.valueOf(article.getQuantity()));
            table.addCell(String.valueOf(article.getSerialNumber()));
            table.addCell(String.valueOf(article.getSupplier()));
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of articles", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}*/

package com.itextpdf.spurvago_pdf;

import Classes.Accommodation;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.w3c.dom.css.RGBColor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PDF {

    public static void generatePDF(Accommodation accommodation) throws IOException, DocumentException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = formatter.format(date);
        Integer iter = 1;

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Test.pdf"));
        document.addAuthor("Spurvago");
        document.addCreationDate();
        document.addProducer();
        document.addCreator("Spurva.go");
        document.addTitle("Faktura");
        document.setPageSize(PageSize.A4);
        document.open();

        FontFactory.Register

        Font font10bold = FontFactory.getFont("Calibri","UTF-8", 10, Font.BOLD);
        Font font10 = FontFactory.getFont("Calibri","UTF-8", 10);

        Paragraph p1 = new Paragraph("",font10bold);
        p1.add("Faktura Nr: SPR\\" + System.currentTimeMillis());
        document.add(p1);

        Paragraph p2 = new Paragraph("", font10bold);
        p2.add("Data wystawienia: " + dateNow );
        document.add(p2);

        document.add(Chunk.NEWLINE);

        LineSeparator ls = new LineSeparator();
        document.add(ls);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        Paragraph p3 = new Paragraph("", font10bold);
        p3.add("Sprzedawca");

        Paragraph p4 = new Paragraph("", font10bold);
        p4.add("Nabywca");

        table.addCell(getCell(p3, PdfPCell.ALIGN_LEFT));
        table.addCell(getCell(new Paragraph(""), PdfPCell.ALIGN_CENTER));
        table.addCell(getCell(p4, PdfPCell.ALIGN_CENTER));
        table.addCell(getCell(new Paragraph(""), PdfPCell.ALIGN_RIGHT));

        document.add(table);

        document.add(Chunk.NEWLINE);

        PdfPTable table1 = new PdfPTable(4);
        table1.setWidthPercentage(100);

        Paragraph p5 = new Paragraph("", font10);
        p5.add("Spurvago Sp. zo.o Sp. be.niz");

        Paragraph p6 = new Paragraph("", font10);
        p6.add(accommodation.getFirstName() + " " + accommodation.getLastName());

        table1.addCell(getCell(p5, PdfPCell.ALIGN_LEFT));
        table1.addCell(getCell(new Paragraph(""), PdfPCell.ALIGN_CENTER));
        table1.addCell(getCell(p6, PdfPCell.ALIGN_CENTER));
        table1.addCell(getCell(new Paragraph(""), PdfPCell.ALIGN_RIGHT));

        document.add(table1);

        document.add(Chunk.NEWLINE);

        PdfPTable table2 = new PdfPTable(8);
        table2.setWidthPercentage(100);
        table2.setWidths(new float[] { 10, 40, 10, 20, 20, 10, 20, 20});

        table2.addCell(getCellWOBorder(new Paragraph("L.P.",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("Nazwa towaru/uslugi",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("Noce",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("Cena netto",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("Wartosc netto",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("VAT %",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("Wartosc VAT",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(getCellWOBorder(new Paragraph("Wartosc Brutto",font10bold), PdfPCell.ALIGN_RIGHT));
        table2.addCell(String.valueOf(iter));
        table2.addCell(new Paragraph(accommodation.getRoomLabel(),font10bold));
        table2.addCell(String.valueOf(TimeUnit.DAYS.convert(Math.abs(accommodation.getEndDate().getTime() - accommodation.getStartDate().getTime()+172800000), TimeUnit.MILLISECONDS)));
        table2.addCell(Double.parseDouble(accommodation.getPrice()) - (Double.parseDouble(accommodation.getPrice())*0.23)+ " PLN");
        table2.addCell(Double.parseDouble(accommodation.getPrice()) - (Double.parseDouble(accommodation.getPrice())*0.23)+ " PLN");
        table2.addCell("23%");
        table2.addCell((Double.parseDouble(accommodation.getPrice())*0.23)+ " PLN");
        table2.addCell(accommodation.getPrice()+" PLN");

        document.add(table2);

        document.close();
    }

    public static PdfPCell getCell(Paragraph text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getCellWOBorder(Paragraph text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(4);
        cell.setHorizontalAlignment(alignment);
        cell.setBackgroundColor(BaseColor.GRAY);
        return cell;
    }



    public static void main(String[] argc) throws Exception {
        Accommodation accommodation = new Accommodation("Józef", "Piłsudzki", "Exclusive", new java.sql.Date(Calendar.getInstance().getTime().getTime()), new java.sql.Date(Calendar.getInstance().getTime().getTime()), "13", "200");
        generatePDF(accommodation);
    }
}
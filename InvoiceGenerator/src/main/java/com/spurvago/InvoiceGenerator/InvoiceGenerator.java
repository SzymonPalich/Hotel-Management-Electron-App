package com.spurvago.InvoiceGenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceGenerator {
    public void generatePDF(InvoiceDetails invoiceDetails) throws IOException, DocumentException {
        //<editor-fold desc="Load Fonts">
        var baseFont = BaseFont.createFont("Helvetica.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        var font = new Font(baseFont, 12);
        var fontBold = new Font(baseFont, 12, Font.BOLD);
        //</editor-fold>

        var invoiceNr = System.currentTimeMillis();
        var document = createDocument("Faktura " + invoiceDetails.getBuyerName() + " " + invoiceDetails.getEndDate() + ".pdf");
        var dateNow = getDate();


        //<editor-fold desc="Number and date">
        PdfPTable headerTable = new PdfPTable(2);

        Paragraph invoiceNumberHeader = new Paragraph("Faktura Nr:", fontBold);
        Paragraph invoiceNumber = new Paragraph("SPR\\" + invoiceNr, font);

        Paragraph invoiceDateHeader = new Paragraph("Data wystawienia:", fontBold);
        Paragraph invoiceDate = new Paragraph(dateNow, font);

        headerTable.setWidthPercentage(100);
        headerTable.addCell(getCell(invoiceNumberHeader, PdfPCell.ALIGN_LEFT));
        headerTable.addCell(getCell(invoiceDateHeader, PdfPCell.ALIGN_RIGHT));
        headerTable.addCell(getCell(invoiceNumber, PdfPCell.ALIGN_LEFT));
        headerTable.addCell(getCell(invoiceDate, PdfPCell.ALIGN_RIGHT));
        headerTable.setComplete(true);
        document.add(headerTable);

        document.add(new Phrase("\n"));
        document.add(new LineSeparator());
        document.add(new Phrase("\n"));
        //</editor-fold>

        //<editor-fold desc="Parties">
        PdfPTable partiesTable = new PdfPTable(2);
        partiesTable.setWidthPercentage(100);

        Paragraph sellerHeader = new Paragraph("Sprzedawca", fontBold);
        Paragraph buyerHeader = new Paragraph("Nabywca", fontBold);
        Paragraph seller = new Paragraph("SpurVa.Go S.A.", font);
        Paragraph buyer = new Paragraph(invoiceDetails.getBuyerName(), font);

        partiesTable.addCell(getCell(sellerHeader, PdfPCell.ALIGN_LEFT));
        partiesTable.addCell(getCell(buyerHeader, PdfPCell.ALIGN_RIGHT));
        partiesTable.addCell(getCell(seller, PdfPCell.ALIGN_LEFT));
        partiesTable.addCell(getCell(buyer, PdfPCell.ALIGN_RIGHT));
        document.add(partiesTable);

        document.add(new Phrase("\n"));
        //</editor-fold>

        //<editor-fold desc="Date details">
        PdfPTable dateDetailsTable = new PdfPTable(4);
        dateDetailsTable.setWidthPercentage(100);
        dateDetailsTable.setWidths(new float[]{25, 15, 30, 30});
        dateDetailsTable.addCell(getCellWOBorder(new Paragraph("Nazwa usługi", fontBold)));
        dateDetailsTable.addCell(getCellWOBorder(new Paragraph("Noce", fontBold)));
        dateDetailsTable.addCell(getCellWOBorder(new Paragraph("Początek rezerwacji", fontBold)));
        dateDetailsTable.addCell(getCellWOBorder(new Paragraph("Koniec rezerwacji", fontBold)));

        dateDetailsTable.addCell(new Paragraph(invoiceDetails.getRoomName(), fontBold));
        dateDetailsTable.addCell(new Paragraph(invoiceDetails.getNumberOfNights(), font));
        dateDetailsTable.addCell(new Paragraph(invoiceDetails.getStartDate(), font));
        dateDetailsTable.addCell(new Paragraph(invoiceDetails.getEndDate(), font));

        document.add(dateDetailsTable);
        //</editor-fold>

        //<editor-fold desc="Price details">
        PdfPTable priceDetailsTable = new PdfPTable(2);
        priceDetailsTable.setWidthPercentage(100);
        priceDetailsTable.setWidths(new float[]{30, 30});

        priceDetailsTable.addCell(getCellWOBorder(new Paragraph("Zakwaterowanie brutto", fontBold)));
        priceDetailsTable.addCell(getCellWOBorder(new Paragraph("Minibar brutto", fontBold)));


        priceDetailsTable.addCell(new Paragraph(invoiceDetails.getAccommodation(), font));
        priceDetailsTable.addCell(new Paragraph(invoiceDetails.getMiniBarValue(), font));


        document.add(priceDetailsTable);
        //</editor-fold>

        PdfPTable summaryCostsTable = new PdfPTable(4);
        summaryCostsTable.setWidthPercentage(100);
        summaryCostsTable.setWidths(new float[]{30, 30, 15, 30});

        summaryCostsTable.addCell(getCellWOBorder(new Paragraph("Cena brutto", fontBold)));
        summaryCostsTable.addCell(getCellWOBorder(new Paragraph("Cena netto", fontBold)));
        summaryCostsTable.addCell(getCellWOBorder(new Paragraph("VAT %", fontBold)));
        summaryCostsTable.addCell(getCellWOBorder(new Paragraph("Wartość VAT", fontBold)));


        summaryCostsTable.addCell(new Paragraph(invoiceDetails.getGrossValue(), font));
        summaryCostsTable.addCell(new Paragraph(invoiceDetails.getNetValue(), font));
        summaryCostsTable.addCell(new Paragraph("23%", font));
        summaryCostsTable.addCell(new Paragraph(invoiceDetails.getVatValue(), font));

        document.add(summaryCostsTable);

        Image image = Image.getInstance("src/main/resources/logo.jpg");
        image.scaleAbsolute(150f, 150f);
        image.setAbsolutePosition(445f, 0f);
        document.add(image);

        document.close();
    }


    private Document createDocument(String documentName) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(documentName));
        document.addAuthor("SpurVa.Go");
        document.addCreator("SpurVa.Go");
        document.addTitle("Faktura");
        document.setPageSize(PageSize.A4);
        document.open();

        return document;
    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    private PdfPCell getCell(Paragraph text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(4);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private PdfPCell getCellWOBorder(Paragraph text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(4);
        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBackgroundColor(BaseColor.GRAY);
        return cell;
    }
}

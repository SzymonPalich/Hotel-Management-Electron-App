package com.spurvago.InvoiceGenerator;


import java.math.BigDecimal;
import java.sql.Date;


public class Main {
    public static void main(String[] argc) throws Exception {
        InvoiceDetails InvoiceDetails = new InvoiceDetails("Jan Dzban", "104 Deluxe",
                4, new Date(2022 - 1900, 6, 10),
                new Date(2022 - 1900, 6, 10), new BigDecimal("200"));

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        invoiceGenerator.generatePDF(InvoiceDetails);
    }
}
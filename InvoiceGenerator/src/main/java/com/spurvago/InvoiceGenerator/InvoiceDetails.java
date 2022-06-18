package com.spurvago.InvoiceGenerator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;

public class InvoiceDetails {
    private final String buyerName;
    private final String roomName;
    private final int numberOfNights;
    private final Date startDate;
    private final Date endDate;
    private final BigDecimal grossValue;
    private final BigDecimal vatValue;
    private final BigDecimal netValue;
    private final BigDecimal miniBar;

    private final BigDecimal accommodation;

    public InvoiceDetails(String buyerName, String roomName, int numberOfNights, Date startDate, Date endDate, BigDecimal accommodation, BigDecimal miniBar) {
        MathContext mathContext = new MathContext(0, RoundingMode.CEILING);
        this.buyerName = buyerName;
        this.roomName = roomName;
        this.numberOfNights = numberOfNights;
        this.startDate = startDate;
        this.endDate = endDate;
        this.miniBar = miniBar.round(mathContext).setScale(2);
        this.accommodation = accommodation.round(mathContext).setScale(2);
        this.grossValue = accommodation.add(miniBar).round(mathContext).setScale(2);
        this.vatValue = new BigDecimal("0.23").multiply(grossValue).round(mathContext).setScale(2);
        this.netValue = new BigDecimal("0.77").multiply(grossValue).round(mathContext).setScale(2);

    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getNumberOfNights() {
        return String.valueOf(numberOfNights);
    }

    public String getStartDate() {
        return startDate.toString();
    }

    public String getEndDate() {
        return endDate.toString();
    }

    public String getGrossValue() {
        return grossValue.toString() + " zł";
    }

    public String getVatValue() {
        return vatValue.toString() + " zł";
    }

    public String getNetValue() {
        return netValue.toString() + " zł";
    }

    public String getMiniBarValue() {
        return miniBar.toString() + " zł";
    }

    public String getAccommodation() {
        return accommodation.toString() + " zł";
    }
}

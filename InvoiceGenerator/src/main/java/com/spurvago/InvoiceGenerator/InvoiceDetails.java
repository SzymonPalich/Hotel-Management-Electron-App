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
        this.buyerName = buyerName;
        this.roomName = roomName;
        this.numberOfNights = numberOfNights;
        this.startDate = startDate;
        this.endDate = endDate;
        this.miniBar = miniBar.setScale(2, RoundingMode.CEILING);
        this.accommodation = accommodation.setScale(2, RoundingMode.CEILING);
        this.grossValue = accommodation.add(miniBar).setScale(2, RoundingMode.CEILING);
        BigDecimal vatMultiply = new BigDecimal("0.23").setScale(2, RoundingMode.CEILING);
        BigDecimal netMultiply = new BigDecimal("0.77").setScale(2, RoundingMode.CEILING);
        this.vatValue = vatMultiply.multiply(grossValue).setScale(2, RoundingMode.CEILING);
        this.netValue = netMultiply.multiply(grossValue).setScale(2, RoundingMode.CEILING);

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

package com.sig.model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    private int number;
    private String customerName;
    private Date invoiceDate;
    private ArrayList<InvoiceLine> relatedLines;

    public InvoiceHeader() {
    }

    public InvoiceHeader(int number, String customerName, Date invoiceDate) {
        this.number = number;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
    }

//Getters & Setters for private members
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<InvoiceLine> getRelatedLines() {
        if (relatedLines == null) {
            relatedLines = new ArrayList<>();
        }
        return relatedLines;
    }

    public void setRelatedLines(ArrayList<InvoiceLine> relatedLines) {
        this.relatedLines = relatedLines;
    }

    //Calculate total for all lines of invoice
    public double getInvoiceTotal() {
        double total = 0;
        for (int i = 0; i < getRelatedLines().size(); i++) {
            total += getRelatedLines().get(i).getTotalLine();
        }
        return total;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "number=" + number + ", customerName=" + customerName + ", invoiceDate=" + invoiceDate + '}';
    }

}

package com.sig.controller;

import com.sig.model.InvHeaderTableModel;
import com.sig.model.InvLineTableModel;
import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.view.HeaderDialouge;
import com.sig.view.InvoiceForm;
import com.sig.view.LineDialouge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/*
Action Listeners for the invoices action buttons:
(Create new invoice - Delete invoice - Create line - Delete line)
 */
public class ButtonsActionListener implements ActionListener {

    private InvoiceForm invForm;
    private HeaderDialouge headerDialouge;
    private LineDialouge lineDialouge;

    public ButtonsActionListener(InvoiceForm invForm) {
        this.invForm = invForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Create New Invoice":
                createInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Create Line":
                createLine();
                break;

            case "Delete Line":
                deleteLine();
                break;

            case "addNewInvoice":
                additionNewInvDialouge();
                break;

            case "cancelNewInvoice":
                cancelationNewInvDialouge();
                break;

            case "addNewLine":
                additionNewLineDialouge();
                break;

            case "cancelNewLine":
                cancelationNewLineDialouge();
                break;
        }
    }

    private void createInvoice() {
        headerDialouge = new HeaderDialouge(invForm);
        headerDialouge.setVisible(true);

    }

    private void deleteInvoice() {
        int invSelectedIndex = invForm.getInvTable().getSelectedRow();
        if (invSelectedIndex != -1) {
            invForm.getInvoicesArray().remove(invSelectedIndex);
            invForm.getInvHeaderTM().fireTableDataChanged();

            invForm.getLinesTable().setModel(new InvLineTableModel(null));
            invForm.setLinesArray(null);
            invForm.getCustomerNameLbl().setText("");
            invForm.getInvNumLbl().setText("");
            invForm.getInvTotalLbl().setText("");
            invForm.getInvDateLbl().setText("");
            System.out.println("Invoice Deleted Successfully");
        }
    }

    private void createLine() {
        lineDialouge = new LineDialouge(invForm);
        lineDialouge.setVisible(true);
    }

    private void deleteLine() {

        int lineSelectedIndex = invForm.getLinesTable().getSelectedRow();
        int invSelectIndex = invForm.getInvTable().getSelectedRow();
        if (lineSelectedIndex != -1) {
            invForm.getLinesArray().remove(lineSelectedIndex);
            InvLineTableModel lineTableModel = (InvLineTableModel) invForm.getLinesTable().getModel();
            lineTableModel.fireTableDataChanged();
            invForm.getInvTotalLbl().setText("" + invForm.getInvoicesArray().get(invSelectIndex).getInvoiceTotal());
            invForm.getInvHeaderTM().fireTableDataChanged();
            invForm.getInvTable().setRowSelectionInterval(invSelectIndex, invSelectIndex);
            System.out.println("Item Deleted Successfully");

        }
    }

    private void additionNewInvDialouge() {
        headerDialouge.setVisible(false);

        String customerName = headerDialouge.getCustomerNameTF().getText();
        String invDate = headerDialouge.getInvoiceDateTF().getText();
        Date date = new Date();

        try {

            date = InvoiceForm.date.parse(invDate);
        } catch (ParseException px) {
            JOptionPane.showMessageDialog(invForm, "Date entered cannot be parsed, today's date is added as default date.",
                    "Invalid Date Format", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("########################");
        System.out.println(invForm.getInvoicesArray());
        int invNumber = 0;
        if (invForm.getInvoicesArray() != null) {
            for (InvoiceHeader invHeader : invForm.getInvoicesArray()) {
                if (invHeader.getNumber() > invNumber) {
                    invNumber = invHeader.getNumber();
                }
                invNumber++;
            }
        } else {
            invForm.setInvoicesArray(new ArrayList<InvoiceHeader>());
            invNumber = 0;
        }

        InvoiceHeader invHeader = new InvoiceHeader(invNumber, customerName, date);
        invForm.getInvoicesArray().add(invHeader);
        if (invForm.getInvHeaderTM() == null) {

            invForm.setInvHeaderTM(new InvHeaderTableModel(invForm.getInvoicesArray()));
            invForm.getInvTable().setModel(invForm.getInvHeaderTM());
        }
        invForm.getInvHeaderTM().fireTableDataChanged();
        System.out.println("New Invoice is added successfully");
        headerDialouge.dispose();
        headerDialouge = null;
    }

    private void cancelationNewInvDialouge() {
        headerDialouge.setVisible(false);
        headerDialouge.dispose();
        headerDialouge = null;
    }

    private void additionNewLineDialouge() {
        lineDialouge.setVisible(false);
        String itemName = lineDialouge.getItemNameTF().getText();
        double itemPrice = 1;
        int itemCount = 1;
        try {
            itemPrice = Double.parseDouble(lineDialouge.getItemPriceTF().getText());
            itemCount = Integer.parseInt(lineDialouge.getItemCountTF().getText());
        } catch (NumberFormatException numx) {
            JOptionPane.showMessageDialog(invForm, "Price/count entered cannot be parsed, default value will be set to 1.",
                    "Invalid Price/count format", JOptionPane.ERROR_MESSAGE);
        }

        int invSelectedIndex = invForm.getInvTable().getSelectedRow();
        if (invSelectedIndex != -1) {
            System.out.println(invSelectedIndex);
            InvoiceHeader invHeader = invForm.getInvoicesArray().get(invSelectedIndex);
            InvoiceLine line = new InvoiceLine(itemName, itemPrice, itemCount, invHeader);
            invForm.getLinesArray().add(line);
            InvLineTableModel lineTableModel = (InvLineTableModel) invForm.getLinesTable().getModel();
            lineTableModel.fireTableDataChanged();
            invForm.getInvHeaderTM().fireTableDataChanged();
        }
        try {
            invForm.getInvTable().setRowSelectionInterval(invSelectedIndex, invSelectedIndex);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(invForm, "Please select Invoice first",
                    "No Invoice selected", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("New invoice item is added successfully");
        lineDialouge.dispose();
        lineDialouge = null;
    }

    private void cancelationNewLineDialouge() {
        lineDialouge.setVisible(false);
        lineDialouge.dispose();
        lineDialouge = null;
    }

}

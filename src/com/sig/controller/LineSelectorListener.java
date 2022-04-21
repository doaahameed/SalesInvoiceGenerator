package com.sig.controller;

import com.sig.model.InvLineTableModel;
import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.view.InvoiceForm;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LineSelectorListener implements ListSelectionListener {

    private InvoiceForm invForm;

    public LineSelectorListener(InvoiceForm invForm) {
        this.invForm = invForm;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int invSelectionIndex = invForm.getInvTable().getSelectedRow();
        System.out.println("Invoice Selected Successfully, View the Items details in the right side Panel");
        System.out.println("Selected Row no.: " + (invSelectionIndex + 1));
        if (invSelectionIndex != -1) {
            InvoiceHeader selectedInv = invForm.getInvoicesArray().get(invSelectionIndex);
            ArrayList<InvoiceLine> invItems = selectedInv.getRelatedLines();
            InvLineTableModel itemsTableModel = new InvLineTableModel(invItems);
            invForm.setLinesArray(invItems);
            invForm.getLinesTable().setModel(itemsTableModel);
            invForm.getCustomerNameLbl().setText(selectedInv.getCustomerName());
            invForm.getInvNumLbl().setText("" + selectedInv.getNumber());
            invForm.getInvTotalLbl().setText("" + selectedInv.getInvoiceTotal());
            invForm.getInvDateLbl().setText(InvoiceForm.date.format(selectedInv.getInvoiceDate()));
        }
    }

}

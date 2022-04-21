package com.sig.model;

import com.sig.view.InvoiceForm;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvHeaderTableModel extends AbstractTableModel {

    private ArrayList<InvoiceHeader> invoicesArray;
    private String[] columnsHeaders = {"Invoice No.", "Invoice Date", "Customer Name", "Invoice Total"};

    public InvHeaderTableModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }

    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columnsHeaders.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv = invoicesArray.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return inv.getNumber();
            case 1:
                return InvoiceForm.date.format(inv.getInvoiceDate());
            case 2:
                return inv.getCustomerName();
            case 3:
                return inv.getInvoiceTotal();
        }

        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columnsHeaders[column];

    }

}

package com.sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvLineTableModel extends AbstractTableModel {

    private ArrayList<InvoiceLine> itemsArray;
    private String[] columnsHeaders = {"Invoice No.", "Item Name", "Item Price", "Count", "Item Total"};

    public InvLineTableModel(ArrayList<InvoiceLine> itemsArray) {
        this.itemsArray = itemsArray;
    }

    @Override
    public int getRowCount() {
        return itemsArray == null ? 0 : itemsArray.size();
    }

    @Override
    public int getColumnCount() {
        return columnsHeaders.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (itemsArray == null) {
            return "";
        } else {
            InvoiceLine item = itemsArray.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return item.getHeader().getNumber();
                case 1:
                    return item.getItem();
                case 2:
                    return item.getPrice();
                case 3:
                    return item.getCount();
                case 4:
                    return item.getTotalLine();

            }
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columnsHeaders[column];
    }

}

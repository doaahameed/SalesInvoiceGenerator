package com.sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HeaderDialouge extends JDialog {

    private JLabel customerNameLab;
    private JTextField customerNameTF;
    private JLabel invoiceDateLab;
    private JTextField invoiceDateTF;
    private JButton addBtn;
    private JButton cnclBtn;

    public HeaderDialouge(InvoiceForm invForm) {

        customerNameLab = new JLabel("Customer Name");
        invoiceDateLab = new JLabel("Invoice Date");
        customerNameTF = new JTextField(25);
        invoiceDateTF = new JTextField(15);
        addBtn = new JButton("Add");
        cnclBtn = new JButton("Cancel");

        addBtn.setActionCommand("addNewInvoice");
        cnclBtn.setActionCommand("cancelNewInvoice");

        addBtn.addActionListener(invForm.getBtnsActionListener());
        cnclBtn.addActionListener(invForm.getBtnsActionListener());
        setLayout(new GridLayout(4, 3));

        add(customerNameLab);
        add(customerNameTF);
        add(invoiceDateLab);
        add(invoiceDateTF);
        add(addBtn);
        add(cnclBtn);

        pack();

    }

    public JTextField getCustomerNameTF() {
        return customerNameTF;
    }

    public JTextField getInvoiceDateTF() {
        return invoiceDateTF;
    }

}

package com.sig.view;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LineDialouge extends JDialog {

    private JLabel nameItemtemLab;
    private JLabel priceItemLab;
    private JLabel countItemLab;

    private JTextField itemNameTF;
    private JTextField itemPriceTF;
    private JTextField itemCountTF;

    private JButton addBtn;
    private JButton cnclBtn;

    public LineDialouge(InvoiceForm invForm) {

        nameItemtemLab = new JLabel("Item Name");
        priceItemLab = new JLabel("Item Price");
        countItemLab = new JLabel("Item Count");

        itemNameTF = new JTextField(20);
        itemPriceTF = new JTextField(20);
        itemCountTF = new JTextField(20);
        
        addBtn = new JButton("Add");
        cnclBtn = new JButton("Cancel");
        
        addBtn.setActionCommand("addNewLine");
        cnclBtn.setActionCommand("cancelNewLine");
        
        addBtn.addActionListener(invForm.getBtnsActionListener());
        cnclBtn.addActionListener(invForm.getBtnsActionListener());
        setLayout(new GridLayout(5,3));
        
        add(nameItemtemLab);
        add(itemNameTF);
        add(priceItemLab);
        add(itemPriceTF);
        add(countItemLab);
        add(itemCountTF);
        add(addBtn);
        add(cnclBtn);
        
        pack();

    }

    public JTextField getItemNameTF() {
        return itemNameTF;
    }

    public JTextField getItemPriceTF() {
        return itemPriceTF;
    }

    public JTextField getItemCountTF() {
        return itemCountTF;
    }
    
    
}

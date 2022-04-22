package com.sig.controller;

import com.sig.model.InvHeaderTableModel;
import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.view.InvoiceForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//Action Listeners for Menu Items (Load file - Save file)
public class MenuItemActionListeners implements ActionListener {

    private InvoiceForm invForm;

    public MenuItemActionListeners(InvoiceForm invForm) {
        this.invForm = invForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Load File":
                loadFile();
                break;

            case "Save File":
                saveFile();
                break;

        }
    }

    //LoadFile Method
    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();

        try {
            int action = fileChooser.showOpenDialog(invForm);
            if (action == JFileChooser.APPROVE_OPTION) {
                File selectedHeaderFile = fileChooser.getSelectedFile();
                Path headerFilePath = Paths.get(selectedHeaderFile.getAbsolutePath());
                List<String> fileHeaderLines = Files.readAllLines(headerFilePath);
                ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
                for (String fileHeaderLine : fileHeaderLines) {
                    String[] lineSeparator = fileHeaderLine.split(",");
                    int invId = Integer.parseInt(lineSeparator[0]);
                    Date invDate = InvoiceForm.date.parse(lineSeparator[1]);
                    String custName = lineSeparator[2];
                    InvoiceHeader invHeader = new InvoiceHeader(invId, custName, invDate);
                    invoiceHeaders.add(invHeader);

                }
                System.out.println("Uploaded files read successfully. Please view it in left side panel");
                invForm.setInvoicesArray(invoiceHeaders);

                action = fileChooser.showOpenDialog(invForm);
                if (action == JFileChooser.APPROVE_OPTION) {
                    File selectedLineFile = fileChooser.getSelectedFile();
                    Path lineFilePath = Paths.get(selectedLineFile.getAbsolutePath());
                    List<String> fileLineLines = Files.readAllLines(lineFilePath);
                    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
                    for (String fileLineLine : fileLineLines) {
                        String[] lineSeparator = fileLineLine.split(",");
                        int invIdForLine = Integer.parseInt(lineSeparator[0]);
                        String itemName = lineSeparator[1];
                        double itemPrice = Double.parseDouble(lineSeparator[2]);
                        int itemCount = Integer.parseInt(lineSeparator[3]);
                        InvoiceHeader inv = invForm.getInvObject(invIdForLine);
                        InvoiceLine invLine = new InvoiceLine(itemName, itemPrice, itemCount, inv);
                        inv.getRelatedLines().add(invLine);
                    }
                }
                InvHeaderTableModel invHeaderTbl = new InvHeaderTableModel(invoiceHeaders);
                invForm.setInvHeaderTM(invHeaderTbl);
                invForm.getInvTable().setModel(invHeaderTbl);

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(invForm, e.getMessage(), "Sorry! an error has occured.", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(invForm, e.getMessage(), "Sorry! an error has occured.", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void saveFile() {
    }

}

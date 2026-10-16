import java.awt.*;
import java.awt.event.*;
import java.util.HashMap; 
import javax.swing.*;

public class CashRegister implements ActionListener {
    // Programmets fönster och inmatningsfält.
    JFrame frame = new JFrame("Kassaprogram"); 
    JTextArea receipt = new JTextArea();
    JTextArea inputProductName = new JTextArea();
    JTextArea inputCount = new JTextArea("1");
    JButton addBtn = new JButton("Add");
    JButton payBtn = new JButton("Pay"); 

    HashMap<String, Produkt> varor = new HashMap<>(); 
    int kvittoNr = 20;
    Receipt kvitto = new Receipt(kvittoNr);

    public CashRegister() {
        // Produktkatalog med pris och momssats.
        varor.put("Kaffe", new Produkt("Capuccino", 45, 0.12)); 
        varor.put("Bulle", new Produkt("Kanellbulle", 25, 0.12));
        varor.put("Dricka", new Produkt("Fanta", 20, 0.25));
        varor.put("Chips", new Produkt("Lays", 28, 0.12));

        // Kassa fönter med knappar för varje produkt, inmatningsfält och kvitto.
        frame.setLayout(null); 
        frame.setSize(1000, 750); 
        // Stänger programmet när fönstret stängs.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        receipt.setEditable(false);  
        frame.add(new JScrollPane(receipt)).setBounds(500, 10, 360, 640); 

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBounds(10, 10, 480, 500);
        for (String namn : varor.keySet()) {
            JButton b = new JButton(namn);
            b.setPreferredSize(new Dimension(100, 50));
            // Fyller i produktnamnet när knappen trycks.
            b.addActionListener(e -> inputProductName.setText(namn)); 
            panel.add(b);
        }
        frame.add(panel); 
        //Placerar inmatningsfält och knappar
        inputProductName.setBounds(20, 550, 200, 30);
        inputCount.setBounds(230, 550, 40, 30);
        addBtn.setBounds(280, 545, 65, 40); 
        payBtn.setBounds(350, 545, 65, 40);
        // Lägger till ActionListener för knapparna
        addBtn.addActionListener(this);  
        payBtn.addActionListener(this); 
        // Lägger till komponenterna i fönstret
        frame.add(inputProductName); 
        frame.add(inputCount);
        frame.add(addBtn); 
        frame.add(payBtn);
        frame.setVisible(true); 
    }


    public void run() { 
        // startar kassaprogrammet och visar ett tomt kvitto.
        receipt.setText("");
        receipt.setText(""); 
    }

    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == addBtn) { //kolla om kunden tryckt på "Add" knappen
            // Hämtar varan och antal innan raden läggs till.
            String namn = inputProductName.getText().trim();
            Produkt p = varor.get(namn);
            if (p == null) return;

            try {
                int antal = Integer.parseInt(inputCount.getText().trim()); 
                // Lägger till varan på kvittot om antal är större än 0.
                if (antal > 0) { 
                    kvitto.add(p, antal);
                    receipt.setText(kvitto.print(false)); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Ange ett antal större än 0!"); 
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Ange en siffra!");
            }
        } else if (e.getSource() == payBtn && !kvitto.rader.isEmpty()) {
            // Avslutar köpet och förbereder nästa kvitto.
            receipt.setText(kvitto.print(true));
            JOptionPane.showMessageDialog(frame, "Tack för köpet!");
            kvitto = new Receipt(++kvittoNr);
            receipt.setText(""); 
        }
    }
}
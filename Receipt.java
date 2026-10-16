import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Receipt {
    int nr;
    LocalDate datum;
    // Listan med produktrader på kvittot.
    ArrayList<ReceiptRow> rader = new ArrayList<>(); 

    public Receipt(int nr) { //Konstruktorn tar emot kvittonummer och sätter datumet till dagens datum.
        this.nr = nr;  
        this.datum = LocalDate.now();
    }

    public void add(Produkt p, int antal) { 
        // Lägger till en produktrad på kvittot.
        rader.add(new ReceiptRow(p, antal));
    }

    public String print(boolean pay) {
        String s = "                    PETES BUTIK\nKvittonummer: " + nr
                + "\nDatum: " + datum.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "\n--------------------------------\n";
        
        double totalAttBetala = 0;
        
        // Skriv ut alla produkter och räkna ut totalen
        for (ReceiptRow r : rader) { 
            double radPris = r.p.pris * r.antal; 
            // Skriv ut produktnamn, antal, pris per styck och radens totala pris
            s += r.p.namn + " " + r.antal + " st x " + r.p.pris + " kr = " + radPris + " kr\n";
            
            totalAttBetala += radPris;
        }
        
        s += "--------------------------------\n";
        s += "Total att betala: " + totalAttBetala + " kr\n";
        
        if (pay) {
            s += "\n   TACK FÖR DITT KÖP!\n";
        }
        
        return s;
    }
}
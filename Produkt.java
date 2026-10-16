public class Produkt {
    // Varans namn, pris och momssats.
    String namn;
    double pris, moms;
    // Konstruktorn tar emot namn, pris och momssats.
    public Produkt(String namn, double pris, double moms) {
        this.namn = namn;
        this.pris = pris;
        this.moms = moms;
    }
}
public class ReceiptRow {
    // En produktrad på kvittot.
    Produkt p;
    int antal;
    // Konstruktorn tar emot produkt och antal.
    public ReceiptRow(Produkt p, int antal) {
        this.p = p;
        this.antal = antal;
    }
}
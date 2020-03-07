class Vanlig extends Legemiddel {
    //private static int antall = 0;

    public Vanlig(String navn, double pris, double virkestoff) {
        super(navn, pris, virkestoff);
        id = Vanlig.antall;
        Vanlig.antall++;
    }
}
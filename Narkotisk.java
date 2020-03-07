class Narkotisk extends Legemiddel {

    private int styrke;

    public Narkotisk(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        id = Vanlig.antall;
        Narkotisk.antall++;
    }

    protected int hentNarkotiskStyrke() {
        return this.styrke;
    }
}
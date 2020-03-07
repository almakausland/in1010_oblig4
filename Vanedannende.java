class Vanedannende extends Legemiddel {

    private int styrke;

    public Vanedannende(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        id = Vanlig.antall;
        Vanedannende.antall++;
    }

    protected int hentVanedannendeStyrke() {
        return this.styrke;
    }
}
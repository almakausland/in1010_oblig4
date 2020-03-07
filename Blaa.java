class Blaa extends Resept {
    Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);

    }

    public String farge() {
        return "blaa";
    }

    public double prisAaBetale() {
        final double RABATT_PROPORSJON = 0.75;
        double utsalgsPris = legemiddel.hentPris();
        return (1 - RABATT_PROPORSJON) * utsalgsPris;
    }
}
class BlaaResept extends Resept {
    BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);

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
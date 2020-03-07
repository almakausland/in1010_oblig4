class MillitaerResept extends Hvit {
    public MillitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public double prisAaBetale() {
        double utsalgsPris = legemiddel.hentPris();
        final double RABATT_PROPORSJON = 1.0;

        double endeligPris = utsalgsPris - (RABATT_PROPORSJON * utsalgsPris);
        return endeligPris;
    }
}
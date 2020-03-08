class PResept extends HvitResept {
    private static final int PRESEPT_REIT = 3;
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, PRESEPT_REIT);
    }

    @Override
    public double prisAaBetale() {
        final double RABATT_STATISK = 108.0;
        double utsalgsPris = legemiddel.hentPris();

        if (utsalgsPris >= RABATT_STATISK) {
            return utsalgsPris - RABATT_STATISK;
        } else {
            return 0;
        }
    }
}
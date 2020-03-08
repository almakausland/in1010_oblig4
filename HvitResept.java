class HvitResept extends Resept {
    HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() {
        return "hvit";
    }

    public double prisAaBetale() {
        double utsalgspris = legemiddel.hentPris();
        return utsalgspris;
    }
    
}
abstract class Resept {
    protected static int antall = 0;
    protected int reseptId;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        reseptId = antall;
        antall++;
        pasient.leggTilResept(this);
    }

    public int hentId() {
        return reseptId;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasientId() {
        return pasient.pasientId;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        } else {
            return false;
        }
    }

    abstract public String farge();

    abstract public double prisAaBetale();

    public String toString() {
        String tekstRep = String.format(
            "Klasse: %s \n" +
            "Resept ID: %d \n" +
            "Lege: %s \n" +
            "Pasient: %d \n" +
            "Legemiddel: %s \n" +
            "Farge: %s \n" + 
            "Pris aa betale: %f \n" +
            "Gjenstaaende reit: %s \n",
            this.getClass().getName(),
            hentId(),
            hentLege().hentLegeNavn(),
            hentPasientId(),
            hentLegemiddel().hentNavn(),
            farge(),
            prisAaBetale(),
            hentReit());
        return tekstRep;

    }
}






abstract class Resept {
    protected static int antall = 0;
    protected int reseptId;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        reseptId = antall;
        antall++;
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
        return pasientId;
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






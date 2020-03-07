abstract class Legemiddel {

    protected static int antall = 0;
    protected int id;
    private String navn;
    private double pris;
    private double virkestoff;

    public Legemiddel(String navn , double pris , double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public double hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(double nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        String tekstRep = String.format(
            "Klasse: %s \n" +
            "Legemiddel ID: %d \n" +
            "Navn: %s \n" +
            "Pris: %f \n" +
            "Virkestoff: %f \n",
            this.getClass().getName(),
            hentId(),
            hentNavn(),
            hentPris(),
            hentVirkestoff());
        return tekstRep;
    }
}
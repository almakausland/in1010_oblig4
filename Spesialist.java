class Spesialist extends Lege implements Godkjenningsfritak {
    private int kontrollID;

    public Spesialist(String legeNavn, int kontrollID) {
        super(legeNavn);

        this.kontrollID = kontrollID;
    }

    public int hentKontrollID() {
        return kontrollID;
    }

    @Override
    public String toString() {
        String tekstRep = String.format(
            "Klasse: %s \n" +
            "Navn: %s \n" +
            "Kontroll ID: %d \n",
            this.getClass().getName(),
            hentLegeNavn(),
            hentKontrollID());
        return tekstRep;
    }
}
class Pasient {
    protected static int antall = 0;
    protected int pasientId;
    private String navn;
    private String fodselsnummer;
    private Stabel<Resept> resepter;

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        pasientId = antall;
        antall++;

        resepter = new Stabel<Resept>();
    }

    public void leggTilResept(Resept resept) {
        resepter.leggPaa(resept);
    }

    public Stabel<Resept> hentResepter() {
        return resepter;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFnr() {
        return fodselsnummer;
    }

    // Metode brukes til aa teste E8 SkriveTilFil
    public static void nullstillId() {
        antall = 0;
    }

    @Override
    public String toString() {
        String reseptTekst = "";
        for (Resept resept: resepter){
            reseptTekst += "\n\t" + resept.hentLegemiddel().hentNavn();
        }

        String tekstRep = String.format(
                "Pasient Id: %s \n" +
                "Navn: %s \n"+
                "Fnr: %s \n"+
                "Resepter: %s \n",
                pasientId,
                hentNavn(),
                hentFnr(),
                reseptTekst);
        return tekstRep;
    }
}
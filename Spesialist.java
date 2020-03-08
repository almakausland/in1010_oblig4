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
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(hvitResept);
        return hvitResept;
    }

    @Override
    public MillitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        MillitaerResept millitaerResept = new MillitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(millitaerResept);
        return millitaerResept;
    }

    @Override
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        PResept pResept = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(pResept);
        return pResept;
    }

    @Override
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(blaaResept);
        return blaaResept;
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
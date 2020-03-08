class Lege implements Comparable<Lege> {
    
    private String legeNavn;
    protected Lenkeliste<Resept> utskrevedeResepter;
    
    public Lege(String legeNavn) {
        this.legeNavn = legeNavn;
        utskrevedeResepter = new Lenkeliste<Resept>();
    }

    public String hentLegeNavn() {
        return legeNavn;
    }

    public Lenkeliste<Resept> hentUtskrevedeResepter() {
        return utskrevedeResepter;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(hvitResept);
            pasient.leggTilResept(hvitResept);
            return hvitResept;
        }
    }

    public MillitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            MillitaerResept millitaerResept = new MillitaerResept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(millitaerResept);
            pasient.leggTilResept(millitaerResept);
            return millitaerResept;
        }
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            PResept pResept = new PResept(legemiddel, this, pasient);
            utskrevedeResepter.leggTil(pResept);
            pasient.leggTilResept(pResept);
            return pResept;
        }
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(blaaResept);
            pasient.leggTilResept(blaaResept);
            return blaaResept;
        }
    }

    @Override
    public int compareTo(Lege annen) {
        return legeNavn.compareTo(annen.legeNavn);
    }

    @Override
    public String toString() {
        String tekstRep = String.format(
            "Klasse: %s \n" +
            "Navn: %s \n",
            this.getClass().getName(),
            hentLegeNavn());
        return tekstRep;
    }
}
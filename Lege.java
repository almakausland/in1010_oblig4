class Lege implements Comparable<Lege> {
    
    String legeNavn;
    Lenkeliste<Resept> utskrevedeResepter;
    
    public Lege(String legeNavn) {
        this.legeNavn = legeNavn;
    }

    public String hentLegeNavn() {
        return legeNavn;
    }

    public Lenkeliste<Resept> hentUtskrevedeResepter() {
        return utskrevedeResepter;
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
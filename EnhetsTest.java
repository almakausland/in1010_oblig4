import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class EnhetsTest {
    private static int antallTester;
    private static int antallOK;

    public EnhetsTest() {
        antallTester = 0;
        antallOK = 0;
    }

    public static void main(String[] args) {
        //C_lenkelisteIterator();
        //D1_legeComparable();
        //D3_legeSkriv();
        // E1 testes visuelt, skriver ut alle verdier
        /*
        try {
            E1_leseData();
        } catch (FileNotFoundException e) {
            System.out.printf("Feil: %s", e);
        }
        */
        //E2_kommandoLokke();
        //E3_skrivUt();
        //E6_statistikkTest();
        /*
        try {
            E8_skriveTilFilTest();
        } catch (FileNotFoundException e) {
            System.out.printf("Feil: %s", e);
        } catch (IOException e) {
            System.out.printf("Feil: %s", e);
        }
        */

        System.out.println("Antall tester utført: " + antallTester);
        System.out.println("Antall tester bestått: " + antallOK);
        
    }

    private static void C_lenkelisteIterator() {
        antallTester++;
        
        // Lag en ny lenkeliste, legg til noen element og se om lenkelisten kan itereres
        Lenkeliste<String> testLenkeliste = new Lenkeliste<String>();
        testLenkeliste.leggTil("første");
        testLenkeliste.leggTil("andre");
        testLenkeliste.leggTil("tredje");

        try {
            for (String element : testLenkeliste) {}
            antallOK++;
            System.out.println("C - OK - kan iterere lenkeliste");
        } catch (Exception e) {
            System.out.println("C - Problem - kan ikke iterere lenkeliste");
        }

    }

    private static void D1_legeComparable() {
        antallTester++;

        // Lag to Lege elementer og sammenlign navn
        Lege legeEn = new Lege("Dr. Paus");
        Lege legeTo = new Lege("Dr. Ueland");
        
        if (legeEn.compareTo(legeTo) < 0) {
            antallOK++;
            System.out.println("D1 - OK - leger sammenlignes riktig");
        } else {
            System.out.println("D1 - Problem - leger sammenlignes ikke riktig");
        }
    }

    private static void D3_legeSkriv() {
        // Opprett legemidler, pasienter, leger
        Vanlig paracet = new Vanlig("Paracet", 140, 15);
        Narkotisk morfin = new Narkotisk("Morfin", 300, 10, 20);

        Pasient pasientEn = new Pasient("Beate Trosserud", "1970010112345");
        Pasient pasientTo = new Pasient("Truls Trosserud", "1971010112345");

        Lege legeEn = new Lege("Dr. Paus");
        Spesialist spesialistEn = new Spesialist("Dr. Ueland", 1);

        final int ANT_REIT = 4;

        // Prøv å legge til ulike gyldige typer resept hos lege
        antallTester++;
        try {
            legeEn.skrivBlaaResept(paracet, pasientEn, ANT_REIT);
            legeEn.skrivHvitResept(paracet, pasientTo, ANT_REIT);
            legeEn.skrivMilitaerResept(paracet, pasientEn, ANT_REIT);

            int tellResept = 0;
            final int LEGE_EN_ANTALL_RESEPT = 3;
            for (Resept resept : legeEn.hentUtskrevedeResepter()) {
                tellResept++;
            }

            if (tellResept == LEGE_EN_ANTALL_RESEPT) {
                antallOK++;
                System.out.println("D3 - OK - kan legge til ulike resept hos lege");
            } else {
                System.out.println("D3 - Problem - kunne ikke legge til gyldige resept hos lege");
            }
            
        } catch (UlovligUtskrift u) {
            System.out.println("D3 - Problem - kunne ikke legge til gyldige resept hos lege");
        }

        // Prøv å skrive ut narkotisk resept med lege (skal feile)
        antallTester++;
        try {
            legeEn.skrivBlaaResept(morfin, pasientTo, ANT_REIT);
            System.out.println("D3 - Problem - kaster ikke unntak om lege skriver narkotisk");
        } catch (UlovligUtskrift u) {
            antallOK++;
            System.out.println("D3 - OK - kaster unntak om lege skriver narkotisk");
        }

        // Prøv å skrive narkotisk resept av spesialist
        antallTester++;
        try {
            spesialistEn.skrivPResept(morfin, pasientTo);
            antallOK++;
            System.out.println("D3 - OK - spesialist kan skrive narkotisk");
        } catch (UlovligUtskrift u) {
            System.out.println("D3 - Problem - kaster unntak om spesialist skriver narkotisk");
        }
    }
    private static void E1_leseData() throws FileNotFoundException {
        antallTester++;
        String path = System.getProperty("user.dir") + File.separator + "myeInndata.txt";
        LeseData data = new LeseData(path);

        for (Pasient p : data.hentPasienter()) {
            System.out.printf("Navn: %s\nFnr: %s\n\n", p.hentNavn(), p.hentFnr());
        }
        
        for (Legemiddel l : data.hentLegemidler()) {
            System.out.println(l);
        }

        for (Lege l : data.hentLeger()) {
            System.out.println(l);
        }

        for (Resept r : data.hentResepter()) {
            System.out.println(r);
        }
        antallOK++;
    }

    private static void E2_kommandoLokke() {
        Legesystem sys1 = new Legesystem();
        new Kommandolokke(sys1);

        // Manuell test på om man kan inputte tall utenfor menyens range eller en streng
    }

    // Sjekk at et legesystem kan skrives ut i oversiktlig form
    private static void E3_skrivUt() {
        antallTester++;
        Legesystem sys1 = new Legesystem();

        Lege lege1 = new Lege("berit");
        Lege lege2 = new Lege("cerit");
        Lege lege3 = new Lege("derit");
        Lege lege4 = new Lege("eerit");
        Lege lege5 = new Lege("ferit");
        Lege lege6 = new Lege("gerit");

        Pasient pas1 = new Pasient("Gunnar", "22");
        Pasient pas2 = new Pasient("gunnar", "22");
        Pasient pas3 = new Pasient("tunnar", "22");
        Pasient pas4 = new Pasient("yunnar", "22");

        Legemiddel mid1 = new Vanedannende ("n1", 3, 4, 4);
        Legemiddel mid2 = new Narkotisk ("n2", 4, 4, 4);
        Legemiddel mid3 = new Vanlig ("n3", 5, 4);
        Legemiddel mid4 = new Vanedannende ("n4", 6, 4, 6);

        Resept res1 = new BlaaResept (mid1 ,lege1, pas1, 3);
        Resept res2 = new HvitResept (mid2 ,lege2, pas2, 3);
        Resept res3 = new MillitaerResept (mid3 ,lege3, pas3, 3);
        Resept res4 = new PResept (mid4 ,lege4, pas4);
        Resept res5 = new PResept (mid4 ,lege4, pas4);
        Resept res6 = new PResept (mid4 ,lege4, pas4);

        sys1.legeListe.leggTil(lege1);
        sys1.legeListe.leggTil(lege2);
        sys1.legeListe.leggTil(lege3);
        sys1.legeListe.leggTil(lege4);
        sys1.legeListe.leggTil(lege5);
        sys1.legeListe.leggTil(lege6);

        sys1.pasientListe.leggTil(pas1);
        sys1.pasientListe.leggTil(pas2);
        sys1.pasientListe.leggTil(pas3);
        sys1.pasientListe.leggTil(pas4);

        sys1.reseptListe.leggTil(res1);
        sys1.reseptListe.leggTil(res2);
        sys1.reseptListe.leggTil(res3);
        sys1.reseptListe.leggTil(res4);


        sys1.print();
        antallOK++;
    }

    private static void E6_statistikkTest(){
        antallTester++;
        try {
            LeseData les = new LeseData ("src/myeinndata.txt");

            Legesystem sys = new Legesystem();
            sys.legeListe = les.hentLeger();
            sys.pasientListe = les.hentPasienter();
            sys.reseptListe = les.hentResepter();

            sys.statVane();
            sys.statNark();
            sys.statMisbrukLege();
            sys.statMisbrukPasient();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        antallOK++;
    }

    private static void E8_skriveTilFilTest() throws FileNotFoundException, IOException {
        antallTester++;

        // Leser av testdata fra fil inndata.txt og lager ny tekstfil
        String path = System.getProperty("user.dir") + File.separator + "inndata.txt";
        LeseData data = new LeseData(path);
        SkriveTilFil stf = new SkriveTilFil(data.hentPasienter(), data.hentLegemidler(), data.hentLeger(), data.hentResepter(), "testSkriving");

        // Foer vi kan opprette et nytt data objekt maa vi nullstille statiske variabler
        Resept.nullstillId();
        Legemiddel.nullstillId();
        Pasient.nullstillId();
        // Lager ny data objekt og sjekker om innhold er det samme
        path = System.getProperty("user.dir") + File.separator + "testSkriving.txt";
        LeseData nyData = new LeseData(path);

        int teller = 0;
        for (Pasient p1 : data.hentPasienter()) {
            Pasient p2 = nyData.hentPasienter().hent(teller);
            if (!p1.hentNavn().equals(p2.hentNavn()) || !p1.hentFnr().equals(p2.hentFnr())) {
                System.out.println("Navn eller fodselsnummer er feil");
                System.out.printf("%s, %s\n", p1.hentNavn(), p1.hentFnr());
                System.out.printf("%s, %s\n", p2.hentNavn(), p2.hentFnr());
                return;
            }
            teller++;
        }

        teller = 0;
        for (Legemiddel lm1 : data.hentLegemidler()) {
            Legemiddel lm2 = nyData.hentLegemidler().hent(teller);
            if (lm1.hentId() != lm2.hentId()
                || lm1.hentPris() != lm2.hentPris()
                || !lm1.hentNavn().equals(lm2.hentNavn())
                || lm1.hentVirkestoff() != lm2.hentVirkestoff()) {
                System.out.println("Id, navn pris eller virkestoff er feil");
                System.out.println(lm1);
                System.out.println(lm2);
                return;
            }
            if (lm1 instanceof Vanlig) {
                if (!(lm2 instanceof Vanlig)) {
                    return;
                } else if (lm1 instanceof Narkotisk) {
                    if (!(lm2 instanceof Narkotisk)
                        || ((Narkotisk) lm1).hentNarkotiskStyrke() != ((Narkotisk) lm2).hentNarkotiskStyrke()) {
                        System.out.println("Type matcher ikke");
                        System.out.println(lm1);
                        System.out.println(lm2);
                        return;
                    }
                } else if (lm1 instanceof Vanedannende) {
                    if (!(lm2 instanceof Vanedannende)
                        || ((Vanedannende) lm1).hentVanedannendeStyrke() != ((Vanedannende) lm2).hentVanedannendeStyrke()) {
                        System.out.println("Type matcher ikke");
                        System.out.println(lm1);
                        System.out.println(lm2);
                        return;
                    }
                }
            }
            teller++;
        }

        if (data.hentLeger().stoerrelse() != nyData.hentLeger().stoerrelse()) {
            return;
        }
        teller = 0;
        for (Lege l1 : data.hentLeger()) {
            for (Lege l2 : nyData.hentLeger()) {
                if (l1.hentLegeNavn().trim().equals(l2.hentLegeNavn().trim())) {
                    if (l1 instanceof Spesialist) {
                        if (l2 instanceof Spesialist) {
                            if (((Spesialist) l1).hentKontrollID() == ((Spesialist) l2).hentKontrollID()) {
                                break;
                            }
                        } else {
                            System.out.println(l1);
                            System.out.println(l2);
                            return;
                        }
                    } else {
                        break;
                    }
                }
                teller++;
            }
            if (teller == data.hentLeger().stoerrelse()) {
                System.out.println("Mangler lege");
                System.out.println(l1);
                return;
            }
        }

        teller = 0;
        for (Resept r1 : data.hentResepter()) {
            Resept r2 = nyData.hentResepter().hent(teller);
            if (r1.hentId() != r2.hentId()
                || r1.hentLegemiddel().hentId() != r2.hentLegemiddel().hentId()
                || r1.hentReit() != r2.hentReit()
                || r1.hentPasientId() != r2.hentPasientId()
                || !r1.hentLege().hentLegeNavn().equals(r2.hentLege().hentLegeNavn())) {
                System.out.println(r1);
                System.out.println(r2);
                return;
            } else if (r1 instanceof PResept) {
                if (!(r2 instanceof PResept)) {
                    return;
                }
            } else if (r1 instanceof MillitaerResept) {
                if (!(r2 instanceof MillitaerResept)) {
                    return;
                }
            } else if (r1 instanceof BlaaResept) {
                if (!(r2 instanceof BlaaResept)) {
                    return;
                }
            } else if (r1 instanceof HvitResept) {
                if (!(r2 instanceof HvitResept)) {
                    return;
                }
            }
            teller++;
        }

        antallOK++;
    }
}
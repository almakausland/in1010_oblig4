class EnhetsTest {
    private static int antallTester;
    private static int antallOK;

    public EnhetsTest() {
        antallTester = 0;
        antallOK = 0;
    }

    public static void main(String[] args) {
        C_lenkelisteIterator();
        D1_legeComparable();
        D3_legeSkriv();
        //E2_kommandoLokke();

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

    private static void E2_kommandoLokke() {
        Legesystem sys1 = new Legesystem();
        new Kommandolokke(sys1);

        // Manuell test på om man kan inputte tall utenfor menyens range eller en streng
    }
}
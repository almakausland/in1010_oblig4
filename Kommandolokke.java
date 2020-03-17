import java.io.IOError;
import java.util.Scanner;

class Kommandolokke {
    private Legesystem legesystem;
    private boolean visMeny;
    private Scanner terminalInput;

    public Kommandolokke(Legesystem legesystem) {
        this.legesystem = legesystem;
        visMeny = true;
        terminalInput = new Scanner(System.in);

        while (visMeny) {
            int valg = menyValg();
            tolkKommando(valg);
        }
    }

    private int menyValg() {
        // Skriv meny
        System.out.println("Hovedmeny");
        System.out.println("0: Skriv ut oversikt over pasienter, leger, legemidler og resepter");
        System.out.println("1: Opprett nye elementer i systemet");
        System.out.println("2: Bruk et gitt resept fra listen til en pasient");
        System.out.println("3: Skriv ut statistikk");
        System.out.println("4: Skriv all data til fil");
        System.out.println("5: Avbryt");

        // Hent input fra bruker
        String valgStreng = terminalInput.next();

        // Konverter input streng til heltall
        try {
            int valgTall = Integer.parseInt(valgStreng);
            return valgTall;
        } catch (NumberFormatException ikkeNummer) {
            System.out.println("Feil format. Vennligst angi et tall.");
            return menyValg();
        }

    }
    private void tolkKommando(int kommando) {
        if (kommando == 0) {
            skrivOversikt();
        } else if (kommando == 1) {
            opprettElement();
        } else if (kommando == 2) {
            brukResept();
        } else if (kommando == 3) {
            skrivStatistikk();
        } else if (kommando == 4) {
            skrivFil();
        } else if (kommando == 5) {
            visMeny = false;
        } else if (kommando < 0 || kommando > 5) {
            System.out.println("Vennligst velg et av tallene fra menyen. " + kommando + " er ikke et gyldig tall.");
        }
    }

    private void skrivOversikt() {
        // Kall på E3
        legesystem.print();
    }

    private void opprettElement() {
        // E7 og kall til E4 
    }

    private void brukResept() {
        // Kall til E5
        System.out.println("Hvilken pasient vil du se resepter for?");
        int teller = 0;
        // Skriver ut listen med pasienter
        for (Pasient p : legesystem.pasientListe) {
            System.out.printf("%d: %s (fnr %s)\n", teller, p.hentNavn(), p.hentFnr());
            teller++;
        }

        int stoerrelse = legesystem.pasientListe.stoerrelse();
        int pasientNr = -1;
        // Sjekker for gyldig input, looper fram til bruker gir gyldig input
        while (pasientNr < 0 || pasientNr >= stoerrelse) {
            String valgStreng = terminalInput.next();

            try {
                int valgTall = Integer.parseInt(valgStreng);
                pasientNr = valgTall;
            } catch (NumberFormatException nfe) {
                System.out.printf("Feil format. Vennligst angi et tall mellom 0 og %d.\n", stoerrelse);
            }
        }

        Sytem.out.printf("\nValgt Pasient: %s (fnr %s).\nHvilken resept vil du bruke?", p.hentNavn(), p.hentFnr());
        Liste<Resept> reseptStabel = legesystem.pasientListe.hent(pasientNr);
        stoerrelse = reseptStabel.stoerrelse();
        teller = 0;
        reseptNr = -1;
        // Skriver ut listen med resepter tilhoerende pasient
        for (Resept r : reseptStabel) {
            System.out.printf("%d: %s (%d reit)\n", teller, r.hentLegemiddel().hentNavn(), r.hentReit());
            teller++;
        }
        // Sjekker for gyldig inpupt
        while (reseptNr < 0 || reseptNr >= stoerrelse) {
            String valgStreng = terminalInput.next();

            try {
                int valgTall = Integer.parseInt(valgStreng);
                reseptNr = valgTall;
            } catch (NumberFormatException nfe) {
                System.out.printf("Feil format. Vennligst angi et tall mellom 0 og %d.\n", stoerrelse);
            }
        }

        Resept resept = reseptStabel.hent(reseptNr);
        int reit = resept.hentReit();
        String reseptNavn = resept.hentLegemiddel().hentNavn();
        // Sjekker om resepten er brukt opp. Bruker resepten hvis den har gjenstaaende reit
        if (reit <= 0) {
            System.out.printf("\nKunne ikke bruke resept paa %s (ingen gjenvaerende reit)\n", reseptNavn);
        } else {
            reit--;
            resept.bruk();
            System.out.printf("\nBrukte resept paa %s. Antall gjenvaerende reit: %d\n", reseptNavn, reit);
        }

    }

    private void skrivStatistikk() {
        // Kall til E6
        int valg = statistikkValg();
        tolkStatistikkValg(valg);
    }

    private int statistikkValg() {
        System.out.println("Undermeny - Skriv statistikk");
        System.out.println("0: Totalt antall utskrevne resepter på vanedannende legemidler");
        System.out.println("1: Totalt antall utskrevne resepter på narkotiske legemidler");
        System.out.println("2: Misbruk lege");
        System.out.println("3: Misbruk pasient");
        System.out.println("4: Avbryt");

        // Hent input fra bruker
        String valgStreng = terminalInput.next();

        // Konverter input streng til heltall
        try {
            int valgTall = Integer.parseInt(valgStreng);
            return valgTall;
        } catch (NumberFormatException ikkeNummer) {
            System.out.println("Feil format. Vennligst angi et tall.");
            return statistikkValg();
        }
    }

    private void tolkStatistikkValg(int valg) {
        if (valg == 0) {
            legesystem.statVane();
        } else if (valg == 1) {
            legesystem.statNark();
        } else if (valg == 2) {
            legesystem.statMisbrukLege();
        } else if (valg == 3) {
            legesystem.statMisbrukPasient();
        } else if (valg == 4) {
            menyValg();
        } else if (valg < 0 || valg > 4) {
            System.out.println("Vennligst velg et av tallene fra menyen. " + valg + " er ikke et gyldig tall.");
        }
    }

    private void skrivFil() {
        // Kall til E8
        System.out.println("Vennligst oppgi path til fil: ");
        String filnavn = terminalInput.next();

        try {
            SkriveTilFil(legesystem.pasientListe, legesystem.legeListe, legesystem.legemiddelListe, legesystem.reseptListe, filnavn);
        } catch (IOError e) {
            System.out.println("Ikke gyldig filnavn.");
        }
    }

}
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
        legesystem.print();
    }

    private void opprettElement() {
        // E7 og kall til E4 
    }

    private void brukResept() {
        // Kall til E5
    }

    private void skrivStatistikk() {
        // Kall til E6
    }

    private void skrivFil() {
        // Kall til E8
    }

}
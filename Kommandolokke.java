import java.util.Scanner;

class Kommandolokke {
    private Legesystem legesystem;
    private boolean visMeny;
    private Scanner terminalInput;

    public Kommandolokke(Legesystem legesystem) throws UlovligUtskrift {
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
    private void tolkKommando(int kommando) throws UlovligUtskrift {
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

    private void opprettElement() throws UlovligUtskrift {
        Scanner inp = new Scanner(System.in);
        boolean running = true;

        while (running){
            hovedmenyOpprettElementer();

            switch(inp.nextLine()){

                case "1": //oppretter ny lege
                System.out.println("Opprett og legg til ny lege: \n");

                System.out.println("Skriv inn navn til lege: ");
                String navn = inp.nextLine();

                System.out.println("Skriv inn kontrollID (0 hvis ingen kontrollID.)");
                int kontrollid = Integer.parseInt(inp.nextLine());

                if (kontrollid != 0){
                    Spesialist tempSpesialist = new Spesialist(navn, kontrollid);
                    legesystem.legeListe.leggTil(tempSpesialist);
                    System.out.println("Spesialist lagt til: " + tempSpesialist.hentLegeNavn() +
                    ", " + tempSpesialist.hentKontrollID() + "\n");

                } else {
                    Lege tempLege = new Lege(navn);
                    legesystem.legeListe.leggTil(tempLege);
                    System.out.println("Lege lagt til: " + tempLege.hentLegeNavn()+"\n");

                }
                break;

                case "2": //oppretter ny pasient
                System.out.println("Opprett og legg til ny pasient: \n");

                System.out.println("Skriv inn pasientens navn: ");
                navn = inp.nextLine();
                System.out.println("Skriv inn pasientens fodselsnummer: ");
                String fodselsnummer = inp.nextLine();

                Pasient tempPasient = new Pasient(navn, fodselsnummer);
                legesystem.pasientListe.leggTil(tempPasient);

                System.out.println("Pasient lagt til: " + tempPasient.hentNavn()
                + ", " + tempPasient.hentFnr() + "\n");
                break;

                case "3": // meny - bruker velger hvilket legemiddel de skal opprette og legge til
                System.out.println("Opprett og legg til nytt legemiddel: \n");
                System.out.println("1: Legg til narkotisk legemiddel,");
                System.out.println("2: Legg til vanedannende legemiddel,");
                System.out.println("3: Legg til vanlig legemiddel.");

                Double pris;
                Double virkestoff;
                int styrke;
                int reit;
                Legemiddel legemiddel = null;
                Lege lege = null;
                Pasient pasient = null;

                int valg = Integer.parseInt(inp.nextLine());

                if (valg == 1){
                    System.out.println("Opprett og legg til nytt narkotisk legemiddel: \n");
                    System.out.println("Skriv inn navn paa legemiddel: ");
                    navn = inp.nextLine();
                    System.out.println("Skriv inn pris paa legemiddel: ");
                    try {
                        pris = Double.parseDouble(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig pris.");
                        break;
                    }
                    System.out.println("Skriv inn virkestoff: ");
                    try {
                        virkestoff = Double.parseDouble(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig mengde virkestoff.");
                        break;
                    }
                    System.out.println("Skriv inn styrke: ");
                    try {
                        styrke = Integer.parseInt(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig styrke.");
                        break;
                    }

                    Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                    legesystem.legemiddelListe.leggTil(narkotisk);
                    System.out.println("Legemiddel lagt til: \n" + narkotisk.toString());
                    break;

                }
                else if(valg == 2){
                    System.out.println("Opprett og legg til nytt vanedannende legemiddel: \n");
                    System.out.println("Skriv inn navn paa legemiddel: ");
                    navn = inp.nextLine();
                    System.out.println("Skriv inn pris paa legemiddel: ");
                    try {
                        pris = Double.parseDouble(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig pris.");
                        break;
                    }
                    System.out.println("Skriv inn virkestoff: ");
                    try {
                        virkestoff = Double.parseDouble(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig mengde virkestoff.");
                        break;
                    }
                    System.out.println("Skriv inn styrke: ");
                    try {
                        styrke = Integer.parseInt(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig styrke.");
                        break;
                    }

                    Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                    legesystem.legemiddelListe.leggTil(vanedannende);
                    System.out.println("Legemiddel lagt til: \n" + vanedannende.toString());
                    break;

                }
                else if(valg == 3){
                    System.out.println("Opprett og legg til nytt vanlig legemiddel: \n");
                    System.out.println("Skriv inn navn paa legemiddel: ");
                    navn = inp.nextLine();
                    System.out.println("Skriv inn pris paa legemiddel: ");
                    try {
                        pris = Double.parseDouble(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig pris.");
                        break;
                    }

                    System.out.println("Skriv inn virkestoff: ");
                    try {
                        virkestoff = Double.parseDouble(inp.nextLine());
                    } catch (NumberFormatException ikkeNummer){
                        System.out.println(ikkeNummer + " er ikke gyldig mengde virkestoff.");
                        break;
                    }

                    Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                    legesystem.legemiddelListe.leggTil(vanlig);
                    System.out.println("Legemiddel lagt til: \n" + vanlig.toString());
                    break;

                }
                else if (valg < 0 || valg > 3) {
                    System.out.println("Vennligst velg et av tallene fra menyen. " + valg + " er ikke et gyldig tall.");
                }
                break;

                case "4":
                System.out.println("Opprett og legg til ny P-resept: \n");

                System.out.println("Skriv inn utskrivende lege: ");
                lege = finnLege(legesystem, inp.nextLine());

                System.out.println("Skriv inn legemiddelets ID: ");

                try {
                    legemiddel = finnLegemiddel(legesystem, Integer.parseInt(inp.nextLine()));
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig ID");
                    break;
                }


                System.out.println("Skriv inn pasientens fodselsnummer: ");
                pasient = finnPasient(legesystem, inp.nextLine());


                legesystem.reseptListe.leggTil(lege.skrivPResept(legemiddel, pasient));
                System.out.println("Resept lagt til.");

                break;

                case "5":
                System.out.println("Opprett og legg til ny militaerresept: \n");

                System.out.println("Skriv inn utskrivende lege: ");
                lege = finnLege(legesystem, inp.nextLine());

                System.out.println("Skriv inn legemiddelets ID: ");

                try {
                    legemiddel = finnLegemiddel(legesystem, Integer.parseInt(inp.nextLine()));
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig ID");
                    break;
                }


                System.out.println("Skriv inn pasientens fodselsnummer: ");
                pasient = finnPasient(legesystem, inp.nextLine());

                System.out.println("Skriv inn reit: ");
                try {
                    reit = Integer.parseInt(inp.nextLine());
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig reit");
                    break;
                }

                legesystem.reseptListe.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                System.out.println("Resept lagt til.");

                break;

                case "6":
                System.out.println("Opprett og legg til ny hvit resept: \n");

                System.out.println("Skriv inn utskrivende lege: ");
                lege = finnLege(legesystem, inp.nextLine());

                System.out.println("Skriv inn legemiddelets ID: ");

                try {
                    legemiddel = finnLegemiddel(legesystem, Integer.parseInt(inp.nextLine()));
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig ID");
                    break;
                }

                System.out.println("Skriv inn pasientens fodselsnummer: ");
                pasient = finnPasient(legesystem, inp.nextLine());

                System.out.println("Skriv inn reit: ");
                try {
                    reit = Integer.parseInt(inp.nextLine());
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig reit");
                    break;
                }

                legesystem.reseptListe.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                System.out.println("Resept lagt til.");

                break;

                case "7":
                System.out.println("Opprett og legg til ny blaa resept: \n");

                System.out.println("Skriv inn utskrivende lege: ");
                lege = finnLege(legesystem, inp.nextLine());

                System.out.println("Skriv inn legemiddelets ID: ");
                try {
                    legemiddel = finnLegemiddel(legesystem, Integer.parseInt(inp.nextLine()));
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig ID");
                    break;
                }

                System.out.println("Skriv inn pasientens fodselsnummer: ");
                pasient = finnPasient(legesystem, inp.nextLine());

                System.out.println("Skriv inn reit: ");
                try {
                    reit = Integer.parseInt(inp.nextLine());
                } catch (NumberFormatException ikkeNummer){
                    System.out.println(ikkeNummer + " er ikke gyldig reit. ");
                    break;
                }

                legesystem.reseptListe.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                System.out.println("Resept lagt til.");

                break;

                case "8":
                new Kommandolokke(legesystem);

                }
            }

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

    private Pasient finnPasient(Legesystem legesystem, String fodselsnummer){
        Pasient finnPasient;

        for (Pasient pasienter : legesystem.pasientListe){
            if (pasienter.hentFnr().equals(fodselsnummer)){
                finnPasient = pasienter;
                return finnPasient;
            }
        }
        System.out.println("Fant ikke pasient.");
        return null;
    }

    private Lege finnLege(Legesystem legesystem, String legenavn){
        Lege finnLege;
        for (Lege leger : legesystem.legeListe){
            if (leger.hentLegeNavn().equalsIgnoreCase(legenavn)){
                finnLege = leger;
                return finnLege;
            }
        }
        System.out.println("Fant ikke lege.");
        return null;

    }

    private Legemiddel finnLegemiddel(Legesystem legesystem, int legemiddelId){
        Legemiddel lm;
        for (Legemiddel legemiddel : legesystem.legemiddelListe){
            if (legemiddel.hentId() == legemiddelId){ //regner med at legemiddelNummer er legemiddelId
                lm = legemiddel;
                return lm;
            }
        }
        System.out.println("Fant ikke legemiddel.");
        return null;
    }

    private void hovedmenyOpprettElementer(){
        System.out.println("Opprett nytt element: ");
        System.out.println("1: Opprett og legg til ny lege.");
        System.out.println("2: Opprett og legg til ny pasient.");
        System.out.println("3: Opprett og legg til nytt legemiddel.");
        System.out.println("4: Opprett og legg til ny P-resept.");
        System.out.println("5: Opprett og legg til ny militaerresept.");
        System.out.println("6: Opprett og legg til ny hvit resept.");
        System.out.println("7: Opprett og legg til ny blaa resept.");
        System.out.println("8: Tilbake til hovedmeny.");
    }


}

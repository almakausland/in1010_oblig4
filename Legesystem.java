import java.io.FileNotFoundException;

public class Legesystem {

    Liste<Lege> legeListe;
    Liste<Pasient> pasientListe;
    Liste<Resept> reseptListe;
    Liste<Legemiddel> legemiddelListe;


    Legesystem (){

        legeListe = new SortertLenkeliste();
        pasientListe = new Lenkeliste();
        reseptListe = new Lenkeliste();
        legemiddelListe = new Lenkeliste();

    }

    public void print(){

        System.out.println("Leger i Legesystemet: ");

        for (Lege lege: legeListe){
            System.out.println(lege);
        }

        System.out.println("Pasienter i Legesystemet: ");

        for (Pasient pasient: pasientListe){
            System.out.println(pasient);
        }

        System.out.println("Resepter i Legesystemet: ");

        for (Resept resept: reseptListe){
            System.out.println(resept);
        }
    }

    //E6 deloppgave 1
    public void statVane(){
        int res = 0;

        System.out.print("Total utskrevne vanedannende resepter: ");

        for (Resept resept: reseptListe){
            if (resept.legemiddel instanceof Vanedannende){
                res ++;
            }
        }
        System.out.println(res);
    }

    //E6 deloppgave 2
    public void statNark(){
        int res = 0;

        System.out.print("Total utskrevne narkotiske resepter: ");

        for (Resept resept: reseptListe){
            if (resept.legemiddel instanceof Narkotisk){
                res ++;
            }
        }

        System.out.println(res);
    }

    //E6 deloppgave 3
    public void statMisbrukLege(){

        System.out.println("Leger som har skrevet ut minst en narkotisk resept: ");

        for (Lege lege: legeListe){
            int res = 0;
            for (Resept resept: lege.utskrevedeResepter) {
                if (resept.legemiddel instanceof Narkotisk) {
                    res++;
                }
            }
            if (res > 0) {
                System.out.println(" - Legenavn: " + lege.hentLegeNavn() + ", antall resepter: " + res);
            }
        }
    }

    //E6 deloppgave 3
    public void statMisbrukPasient(){
        System.out.println("Pasienter som har minst en narkotisk resept: ");

        for (Pasient pasient: pasientListe){
            int res = 0;
            for (Resept resept: pasient.hentResepter()) {
                if (resept.legemiddel instanceof Narkotisk) {
                    res++;
                }
            }
            if (res > 0) {
                System.out.println(" - Pasientnavn: " + pasient.hentNavn() + ", antall resepter: " + res);
            }
        }
    }
}

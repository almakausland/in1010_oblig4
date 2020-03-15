public class Legesystem {
    Lenkeliste<Lege> legeListe;
    Lenkeliste<Pasient> pasientListe;
    Lenkeliste<Resept> reseptListe;

    Legesystem (){

        legeListe = new SortertLenkeliste();
        pasientListe = new Lenkeliste();
        reseptListe = new Lenkeliste();

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

}
public class Legesystem {

    public static void main (String[] args){

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

    }

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
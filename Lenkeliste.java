import java.util.Iterator;

class Lenkeliste<T> implements Liste<T> {
    public Node start = null;

    class Node {
        Node neste = null;
        T data;
        Node (T x) {
            data = x;
        }
    }

    public int stoerrelse() {
        int antallNoder = 0;
        Node p = start;
        while (p != null) {
            antallNoder++;
            p = p.neste;
        }
        return antallNoder;
    }

    protected Node hentNode(int pos) {
        // Sjekk om angitt posisjon er gyldig
        sjekkIndeks(pos);

        Node p = start;
        for (int i = 0 ; i < pos ; i++) {
            p = p.neste;
        }
        return p;
    }

    public T hent(int pos) {
        return hentNode(pos).data;
    }


    public void leggTil(int pos , T x) {
        Node nyttElement = new Node(x);

        // Hvis angitt indeks er 0, endre start referanse
        if (pos == 0) {
            nyttElement.neste = start;
            start = nyttElement;
        } 
        
        // ellers sett inn element på angitt posisjon og knyt elementet sammen med forrige og neste element i listen
        else {
            Node forrigeElement = hentNode(pos - 1);
            nyttElement.neste = forrigeElement.neste;
            forrigeElement.neste = nyttElement;
        }

    }

    public void leggTil(T x) {
        leggTil(stoerrelse(), x);
    }

    public void sett(int pos, T x) {
        // Sjekk om angitt posisjon er gyldig
        sjekkIndeks(pos);
        
        Node angittNode = hentNode(pos);
        Node nyttElement = new Node(x);
        angittNode.data = nyttElement.data;
    }

    public T fjern(int pos) {
        // Sjekk om angitt posisjon er gyldig
        sjekkIndeks(pos);

        Node nodeAngitt = hentNode(pos);
        // om det ikke finnes en node før, sett start til neste node
        if (pos == 0 && (start == null)) {
            throw new UgyldigListeIndeks(pos);
        } else if (pos == 0 && (start != null)) {
            start = nodeAngitt.neste;
        } else {
            Node nodeEtter = nodeAngitt.neste;
            Node nodeFor = hentNode(pos - 1);
            nodeFor.neste = nodeEtter;
        }
        
        return nodeAngitt.data;
    }

    public T fjern() {
        return fjern(0);
    }

    // Sjekk om gitt indeks er inennfor range til lenkelisten
    private boolean iRekkevidde(int indeks) {
        boolean forStart = indeks < 0;
        boolean etterSlutt = indeks > stoerrelse() - 1;

        if (forStart || etterSlutt) {
            return false;
        } else {
            return true;
        }
    }

    // Kast unntak hvis angitt indeks ikke er i rekkevidde til lenkeliste
    private void sjekkIndeks(int indeks) {
        if (!iRekkevidde(indeks) || tomListe()) {
            throw new UgyldigListeIndeks(indeks);
        }
    }

    // Sjekk om listen er tom
    private boolean tomListe() {
        if (stoerrelse() == 0) {
            return true;
        } else {
            return false;
        }
    }
 
    // For implementering av Iterable
    public Iterator<T> iterator() {
        return new LenkelisteIterator<T>(this);
    }

}
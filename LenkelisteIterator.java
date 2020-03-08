import java.util.Iterator;

class LenkelisteIterator<T> implements Iterator<T> {
    private Liste<T> enListe;
    private int pos;

    public LenkelisteIterator(Liste<T> enListe) {
        this.enListe = enListe;
        pos = 0;
    }
    public T next() {
        return enListe.hent(pos++);
    }

    public boolean hasNext() {
        return (pos < enListe.stoerrelse());
    }
}
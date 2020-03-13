import java.lang.UnsupportedOperationException;

class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x) {
        leggTil(indeksForStoerre(x), x);
    }
    
    @Override
    public T fjern() {
        //T sisteElement = hentNode(stoerrelse() - 1).data;
        return fjern(stoerrelse() - 1);
        //return sisteElement;
    }


    @Override
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) {
        if (pos == indeksForStoerre(x)) {
            super.leggTil(pos, x);
        }
        else {
            throw new UnsupportedOperationException();
        }
        
    }

    private int indeksForStoerre(T x) {
        int antallNoder = 0;
        Node p = start;
        while ((p != null) && (x.compareTo(p.data) > 0)) {
            antallNoder++;
            p = p.neste;
        }
        return antallNoder;
    }
}
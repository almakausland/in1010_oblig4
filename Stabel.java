class Stabel<T> extends Lenkeliste<T> {
    public void leggPaa(T x) {
        leggTil(x);
    }

    public T taAv() {
        T sisteElement = hentNode(stoerrelse() - 1).data;
        fjern(stoerrelse() - 1);
        return sisteElement;
    }
}
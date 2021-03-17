public class Drużyna {

    private String nazwa;
    private int numer;
    private boolean czyAktywna;



    public Drużyna() {
        this.nazwa = "NajlepszaDruzynaSwiata";
        czyAktywna = true;
    }

    public Drużyna(String nazwa) {
        this.nazwa = nazwa;
        czyAktywna = true;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public boolean getCzyAktywna() {
        return czyAktywna;
    }

    public void setCzyAktywna(boolean czyAktywna) {
        this.czyAktywna = czyAktywna;
    }

    public String toString() {
        return  numer + " " + nazwa;
    }

}
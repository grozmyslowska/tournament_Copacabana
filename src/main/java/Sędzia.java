public class Sędzia {

    private String imienazwisko;
    private boolean czyAktywny;
    private int numer;

    public Sędzia(){
        imienazwisko="Gall Anonim";
        czyAktywny=true;
    }

    public Sędzia(String imienazwisko){
        this.imienazwisko=imienazwisko;
        czyAktywny=true;
    }

    public String getImieNazwisko() {
        return imienazwisko;
    }

    public void setImieNazwisko(String imienazwisko) {
        this.imienazwisko = imienazwisko;
    }

    public int getNumer(){
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public boolean getCzyAktywny() {
        return czyAktywny;
    }

    public void setCzyAktywny(boolean czyAktywny) {
        this.czyAktywny = czyAktywny;
    }

    public String toString() {
        return  numer + " " + imienazwisko;
    }




}

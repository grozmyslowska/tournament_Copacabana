import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TablicaDrużyn {

    public Drużyna[] tabD = new Drużyna[100];
    private int licznikDrużyn = 0;


    public void zgłoś(String nazwa) {
        tabD[++licznikDrużyn] = new Drużyna(nazwa);
        tabD[licznikDrużyn].setNumer(licznikDrużyn);
    }

    public void zgłoś() {
        tabD[++licznikDrużyn] = new Drużyna();
        tabD[licznikDrużyn].setNumer(licznikDrużyn);
    }

    public void wycofaj(int n) {
        if (n > licznikDrużyn) {
            System.out.println("Nie ma drużyny o zadanym numerze.");
        } else {
            tabD[n].setCzyAktywna(false);
        }
    }

    public void przeglądaj() {
        for (int i = 1; i <= licznikDrużyn; i++) {
            if (tabD[i].getCzyAktywna() == true) System.out.println(tabD[i].getNumer() + " " + tabD[i].getNazwa());
        }
    }

    public void zapisz() throws FileNotFoundException {
        PrintWriter zapisD = new PrintWriter("./src/main/resources/DaneDruzynyK.txt");
        for (int i = 1; i <= licznikDrużyn; i++) {
            if (tabD[i].getCzyAktywna() == true) zapisD.println(tabD[i].getNumer() + " " + tabD[i].getNazwa());
        }
        zapisD.close();
    }

    public int getLicznikAktywnychDrużyn() {
        int l = 0;
        for (int i = 1; i <= licznikDrużyn; i++)
            if (tabD[i].getCzyAktywna() == true) l++;
        return l;
    }

    public boolean getCzyAktywna(int n) {
        return tabD[n].getCzyAktywna();
    }

    public String getNazwa(int n) {
        return tabD[n].getNazwa();
    }

    public int getLicznikDrużyn() {
        return licznikDrużyn;
    }


}

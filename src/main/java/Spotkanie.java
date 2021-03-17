import java.util.Arrays;
import java.util.Scanner;

public class Spotkanie {

    TablicaDrużyn tablicaDrużyn;
    TablicaSędziów tablicaSędziów;
    int liczbaAktywnychDrużyn = 0, liczbaSpotkańKołowych = 0;

    protected int licznikSpotkania = 0;

    static int[] drużyna1 = new int[100];
    static int[] drużyna2 = new int[100];
    static int[] sędzia = new int[100];
    static int[] wynikDlaDrużyny1 = new int[100];
    static int[] wynikDlaDrużyny2 = new int[100];
    static int[] punktyDrużyny = new int[100];
    static boolean[][] czyByłMecz = new boolean[100][100];

    static int d1=0,d2=0,d3=0,d4=0; //zmienne pomocniczne dla generowania półfinału
    static int[] półfinałDrużyna1 = new int[2];
    static int[] półfinałDrużyna2 = new int[2];
    static int[] półfinałSędzia = new int[2];
    static int[] półfinałWynikDlaDrużyny1 = new int[2];
    static int[] półfinałWynikDlaDrużyny2 = new int[2];
    static int[] zwycięzcaPółfinału = new int[2];


    static int[] finałDrużyna1 = new int[1];
    static int[] finałDrużyna2 = new int[1];
    static int[] finałSędzia = new int[1];
    static int[] finałWynikDlaDrużyny1 = new int[1];
    static int[] finałWynikDlaDrużyny2 = new int[1];

    public Spotkanie(TablicaDrużyn tablicaDrużyn, TablicaSędziów tablicaSędziów) {
        this.tablicaDrużyn = tablicaDrużyn;
        this.tablicaSędziów = tablicaSędziów;
        liczbaAktywnychDrużyn = tablicaDrużyn.getLicznikAktywnychDrużyn();
        liczbaSpotkańKołowych = (liczbaAktywnychDrużyn * (liczbaAktywnychDrużyn - 1)) / 2;
    }

    public void dodajSpotkanie(int d1, int d2, int w1, int w2, int s) throws SpotkanieException {

        if(liczbaSpotkańKołowych+3==licznikSpotkania)throw new SpotkanieException("Zawody dobiegły końca.");
        if((d1>tablicaDrużyn.getLicznikDrużyn())||(d2>tablicaDrużyn.getLicznikDrużyn())) throw new SpotkanieException("Dane zostały niepoprawnie wprowadzone.");
        if(s>tablicaSędziów.getLicznik()) throw new SpotkanieException("Dane zostały niepoprawnie wprowadzone.");
        if ((d1 == 0) || (d2 == 0) || (s == 0)) throw new SpotkanieException("Dane zostały niepoprawnie wprowadzone.");
        if (!tablicaDrużyn.getCzyAktywna(d1)) throw new SpotkanieException(d1 + "Podana drużyna nie jest aktywna.");
        if (!tablicaDrużyn.getCzyAktywna(d2)) throw new SpotkanieException(d2 + "Podana drużyna nie jest aktywna.");
        if (!tablicaSędziów.getCzyAktywny(s)) throw new SpotkanieException(s + "Podany sędzia nie jest aktywny.");
        if (d1 == d2) throw new SpotkanieException("Podano te same drużyny.");
        if (w1 == w2) throw new SpotkanieException("Wynik musi być jednoznaczny.");

        if (liczbaSpotkańKołowych > licznikSpotkania) {
            systemKołowy(d1, d2, w1, w2, s);

        } else if (liczbaSpotkańKołowych + 2 > licznikSpotkania) {
            półfinał(d1, d2, w1, w2, s);

        } else {
            finał(d1, d2, w1, w2, s);
        }
    }

    public void systemKołowy(int d1, int d2, int w1, int w2, int s) throws SpotkanieException {

        if (czyByłMecz[d1][d2]) throw new SpotkanieException("Ten mecz już się odbył.");

        ++licznikSpotkania;
        this.drużyna1[licznikSpotkania] = d1;
        this.drużyna2[licznikSpotkania] = d2;
        this.sędzia[licznikSpotkania] = s;
        this.wynikDlaDrużyny1[licznikSpotkania] = w1;
        this.wynikDlaDrużyny2[licznikSpotkania] = w2;

        punktyDrużyny[d1] += w1;
        punktyDrużyny[d2] += w2;
        czyByłMecz[d1][d2] = true;
        czyByłMecz[d2][d1] = true;

        if (liczbaSpotkańKołowych == this.licznikSpotkania) {
            generujPółfinał();
        }
    }

    public void półfinał(int d1, int d2, int w1, int w2, int s) throws SpotkanieException {

        if(!((d1==this.d1)||(d1==this.d2)||(d1==this.d3)||(d1==this.d4)))throw new SpotkanieException("Drużyna: "+d1+" nie bierze udziału w półfinale.");
        if(!((d2==this.d1)||(d2==this.d2)||(d2==this.d3)||(d2==this.d4)))throw new SpotkanieException("Drużyna: "+d2+" nie bierze udziału w półfinale.");
        if(licznikSpotkania==liczbaSpotkańKołowych+1){
            if(d1==półfinałDrużyna1[0]||d1==półfinałDrużyna2[0])throw new SpotkanieException("Drużyna: "+d1+" już zagrała mecz w półfinale.");
            if(d2==półfinałDrużyna1[0]||d2==półfinałDrużyna2[0])throw new SpotkanieException("Drużyna: "+d2+" już zagrała mecz w półfinale.");

        }

        ++licznikSpotkania;
        this.półfinałDrużyna1[licznikSpotkania - liczbaSpotkańKołowych - 1] = d1;
        this.półfinałDrużyna2[licznikSpotkania - liczbaSpotkańKołowych - 1] = d2;
        this.półfinałSędzia[licznikSpotkania - liczbaSpotkańKołowych - 1] = s;
        this.półfinałWynikDlaDrużyny1[licznikSpotkania - liczbaSpotkańKołowych - 1] = w1;
        this.półfinałWynikDlaDrużyny2[licznikSpotkania - liczbaSpotkańKołowych - 1] = w2;
        if (półfinałWynikDlaDrużyny1[licznikSpotkania - liczbaSpotkańKołowych - 1] > półfinałWynikDlaDrużyny2[licznikSpotkania - liczbaSpotkańKołowych - 1]) {
            zwycięzcaPółfinału[licznikSpotkania - liczbaSpotkańKołowych - 1] = d1;
        } else {
            zwycięzcaPółfinału[licznikSpotkania - liczbaSpotkańKołowych - 1] = d2;
        }

        if (liczbaSpotkańKołowych + 2 == this.licznikSpotkania) {
            generujFinał();
        }
    }

    public void finał(int d1, int d2, int w1, int w2, int s) throws SpotkanieException {
        if(!((d1==zwycięzcaPółfinału[0])||(d1==zwycięzcaPółfinału[1])))throw new SpotkanieException("Drużyna: "+d1+" nie bierze udziału w finale.");
        if(!((d2==zwycięzcaPółfinału[0])||(d2==zwycięzcaPółfinału[1])))throw new SpotkanieException("Drużyna: "+d2+" nie bierze udziału w finale.");

        ++licznikSpotkania;
        this.finałDrużyna1[0] = d1;
        this.finałDrużyna2[0] = d2;
        this.finałSędzia[0] = s;
        this.finałWynikDlaDrużyny1[0] = w1;
        this.finałWynikDlaDrużyny2[0] = w2;
        if (finałWynikDlaDrużyny1[0] > finałWynikDlaDrużyny2[0]) {
            System.out.println("Wygranym jest: "+d1 + ". "+ tablicaDrużyn.getNazwa(d1));
        } else {
            System.out.println("Wygranym jest: "+d2 + ". "+ tablicaDrużyn.getNazwa(d2));}

    }


    public void generujPółfinał() {
        System.out.println("PÓŁFINAŁ CZAS ZACZĄĆ!");
        int[] kopia = (int[]) punktyDrużyny.clone();
        Arrays.sort(kopia);
        int d1 = 0, d2 = 0, d3 = 0, d4 = 0;
        for (int i = 0; i <= licznikSpotkania; i++) {
            if (punktyDrużyny[i] == kopia[kopia.length - 1]) {
                d1 = i;
                break;
            }
        }
        for (int i = 0; i <= licznikSpotkania; i++) {
            if ((punktyDrużyny[i] == kopia[kopia.length - 2]) && (i != d1)) {
                d2 = i;
                break;
            }
        }
        for (int i = 0; i <= licznikSpotkania; i++) {
            if ((punktyDrużyny[i] == kopia[kopia.length - 3]) && (i != d1) && (i != d2)) {
                d3 = i;
                break;
            }
        }
        for (int i = 0; i <= licznikSpotkania; i++) {
            if ((punktyDrużyny[i] == kopia[kopia.length - 4]) && (i != d1) && (i != d2) && (i != d3)) {
                d4 = i;
                break;
            }
        }
        System.out.println("Drużyny, które przeszły do półfinału: " + d1 + " " + d2 + " " + d3 + " " + d4);
        this.d1=d1;this.d2=d2;this.d3=d3;this.d4=d4;
    }

    public void generujFinał() {
        System.out.println("FINAŁ CZAS ZACZĄĆ!");

        System.out.println("Drużyny, które przeszły do finału: " + zwycięzcaPółfinału[0] + " " + zwycięzcaPółfinału[1]);
    }


    public void wyświetlWyniki() {
        for (int i = 1; i <= licznikSpotkania; i++) {
            System.out.println(" (" + drużyna1[i] + ") " + wynikDlaDrużyny1[i] + ":" + wynikDlaDrużyny2[i] + " (" + drużyna2[i] + ") ");
        }
    }

    public void wyświetlWynikiZNazwamiDrużyn() {
        for (int i = 1; i <= licznikSpotkania; i++) {
            System.out.print(tablicaDrużyn.getNazwa(drużyna1[i]));
            System.out.print(" (" + drużyna1[i] + ") " + wynikDlaDrużyny1[i] + ":" + wynikDlaDrużyny2[i] + " (" + drużyna2[i] + ") ");
            System.out.println(tablicaDrużyn.getNazwa(drużyna2[i]));
        }
    }

    public void wyświetlWynikSpotkania(int d1, int d2) {
        if (czyByłMecz[d1][d2]) {
            int l=0;
            if(licznikSpotkania>liczbaSpotkańKołowych)l=liczbaSpotkańKołowych;
            else l=licznikSpotkania;
            for (int i = 1; i <= l; i++) {
                if ((d1 == this.drużyna1[i]) && (d2 == this.drużyna2[i])) {
                    System.out.print(tablicaDrużyn.getNazwa(this.drużyna1[i]));
                    System.out.print(" (" + this.drużyna1[i] + ") " + wynikDlaDrużyny1[i] + ":" + wynikDlaDrużyny2[i] + " (" + this.drużyna2[i] + ") ");
                    System.out.println(tablicaDrużyn.getNazwa(this.drużyna2[i]));
                } else if ((d2 == this.drużyna1[i]) && (d1 == this.drużyna2[i])) {
                    System.out.print(tablicaDrużyn.getNazwa(this.drużyna1[i]));
                    System.out.print(" (" + this.drużyna1[i] + ") " + wynikDlaDrużyny1[i] + ":" + wynikDlaDrużyny2[i] + " (" + this.drużyna2[i] + ") ");
                    System.out.println(tablicaDrużyn.getNazwa(this.drużyna2[i]));
                }
            }
        } else System.out.println("Nie było takiego spotkania.");
    }

    public void wyświetlWynikSpotkania(int sp) {
        if (licznikSpotkania >= sp) {
            System.out.print(tablicaDrużyn.getNazwa(drużyna1[sp]));
            System.out.print(" (" + drużyna1[sp] + ") " + wynikDlaDrużyny1[sp] + ":" + wynikDlaDrużyny2[sp] + " (" + drużyna2[sp] + ") ");
            System.out.println(tablicaDrużyn.getNazwa(drużyna2[sp]));
        } else System.out.println("Nie było takiego spotkania.");
    }

    public void Generator() throws SpotkanieException {
        Scanner scan = new Scanner(System.in);
        int s=tablicaSędziów.getPierwszyAktywnySędzia();
        int w1=0,w2=0;
        for(int i=1;i<liczbaAktywnychDrużyn;i++){
            for(int j=i+1;j<=liczbaAktywnychDrużyn;j++) {
                do{
                System.out.print("Mecz gra ");
                System.out.print(tablicaDrużyn.getNazwa(i));
                System.out.print(" (" + i + ") z (" + j + ") ");
                System.out.println(tablicaDrużyn.getNazwa(j));
                System.out.print("Podaj wynik dla "+i+": ");
                w1 = scan.nextInt();
                System.out.print("Podaj wynik dla "+j+": ");
                w2 = scan.nextInt();
                if(w1==w2)System.out.println("Wyniki muszą być jednoznaczne. Proszę podać wyniki ponownie.");
                }while (w1==w2);

                dodajSpotkanie(i,j,w1,w2,s);
            }
        }
    }

}

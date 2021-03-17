import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Siatkówka extends Spotkanie {

    static int[] sędzia1 = new int[100];
    static int[] sędzia2 = new int[100];
    static int[] półfinałSędzia1 = new int[2];
    static int[] półfinałSędzia2 = new int[2];
    static int[] finałSędzia1 = new int[1];
    static int[] finałSędzia2 = new int[1];

    public Siatkówka(TablicaDrużyn tabD, TablicaSędziów tabS) {
        super(tabD, tabS);
    }

    public void dodajSpotkanie(int d1, int d2, int w1, int w2, int s, int s1, int s2) throws SpotkanieException {

        if(liczbaSpotkańKołowych+3==licznikSpotkania)throw new SpotkanieException("Zawody dobiegły końca.");
        if((d1>tablicaDrużyn.getLicznikDrużyn())||(d2>tablicaDrużyn.getLicznikDrużyn())) throw new SpotkanieException("Dane zostały niepoprawnie wprowadzone.");
        if((s>tablicaSędziów.getLicznik())||(s1>tablicaSędziów.getLicznik())||(s2>tablicaSędziów.getLicznik())) throw new SpotkanieException("Dane zostały niepoprawnie wprowadzone.");
        if ((d1 == 0) || (d2 == 0) || (s == 0)) throw new SpotkanieException("Dane zostały niepoprawnie wprowadzone.");
        if (!tablicaDrużyn.getCzyAktywna(d1)) throw new SpotkanieException(d1 + "Podana drużyna nie jest aktywna.");
        if (!tablicaDrużyn.getCzyAktywna(d2)) throw new SpotkanieException(d2 + "Podana drużyna nie jest aktywna.");
        if (!tablicaSędziów.getCzyAktywny(s)) throw new SpotkanieException(s + "Podany sędzia nie jest aktywny.");
        if (d1 == d2) throw new SpotkanieException("Podano te same drużyny.");
        if (w1 == w2) throw new SpotkanieException("Wynik musi być jednoznaczny.");
        if ((s==s1)||(s==s2)||(s1==s2))throw new SpotkanieException("Sędziowie muszą być innymi osobami.");

        if (liczbaSpotkańKołowych > licznikSpotkania) {
            systemKołowy(d1, d2, w1, w2, s, s1, s2);

        } else if (liczbaSpotkańKołowych + 2 > licznikSpotkania) {
            półfinał(d1, d2, w1, w2, s, s1, s2);

        } else {
            finał(d1, d2, w1, w2, s, s1, s2);
        }
    }

    public void systemKołowy(int d1, int d2, int w1, int w2, int s, int s1, int s2) throws SpotkanieException {

        if (czyByłMecz[d1][d2]) throw new SpotkanieException("Ten mecz już się odbył.");

        ++licznikSpotkania;
        this.drużyna1[licznikSpotkania] = d1;
        this.drużyna2[licznikSpotkania] = d2;
        this.sędzia[licznikSpotkania] = s;
        this.wynikDlaDrużyny1[licznikSpotkania] = w1;
        this.wynikDlaDrużyny2[licznikSpotkania] = w2;
        this.sędzia1[licznikSpotkania]=s1;
        this.sędzia2[licznikSpotkania]=s2;

        punktyDrużyny[d1] += w1;
        punktyDrużyny[d2] += w2;
        czyByłMecz[d1][d2] = true;
        czyByłMecz[d2][d1] = true;

        if (liczbaSpotkańKołowych == this.licznikSpotkania) {
            generujPółfinał();
        }
    }

    public void półfinał(int d1, int d2, int w1, int w2, int s, int s1, int s2) throws SpotkanieException {
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
        this.półfinałSędzia1[licznikSpotkania - liczbaSpotkańKołowych - 1] = s1;
        this.półfinałSędzia2[licznikSpotkania - liczbaSpotkańKołowych - 1] = s2;

        if (półfinałWynikDlaDrużyny1[licznikSpotkania - liczbaSpotkańKołowych - 1] > półfinałWynikDlaDrużyny2[licznikSpotkania - liczbaSpotkańKołowych - 1]) {
            zwycięzcaPółfinału[licznikSpotkania - liczbaSpotkańKołowych - 1] = d1;
        } else {
            zwycięzcaPółfinału[licznikSpotkania - liczbaSpotkańKołowych - 1] = d2;
        }

        if (liczbaSpotkańKołowych + 2 == this.licznikSpotkania) {
            generujFinał();
        }
    }

    public void finał(int d1, int d2, int w1, int w2, int s, int s1, int s2) throws SpotkanieException {
        if(!((d1==zwycięzcaPółfinału[0])||(d1==zwycięzcaPółfinału[1])))throw new SpotkanieException("Drużyna: "+d1+" nie bierze udziału w finale.");
        if(!((d2==zwycięzcaPółfinału[0])||(d2==zwycięzcaPółfinału[1])))throw new SpotkanieException("Drużyna: "+d2+" nie bierze udziału w finale.");

        ++licznikSpotkania;
        this.finałDrużyna1[0] = d1;
        this.finałDrużyna2[0] = d2;
        this.finałSędzia[0] = s;
        this.finałWynikDlaDrużyny1[0] = w1;
        this.finałWynikDlaDrużyny2[0] = w2;
        this.finałSędzia1[0] = s1;
        this.finałSędzia2[0] = s2;

        if (finałWynikDlaDrużyny1[0] > finałWynikDlaDrużyny2[0]) {
            System.out.println("Wygranym jest: " + d1 + ". " + tablicaDrużyn.getNazwa(d1));
        } else {
            System.out.println("Wygranym jest: " + d2 + ". " + tablicaDrużyn.getNazwa(d2));
        }

    }

    public void zapisz() throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("./src/main/resources/WynikiSiatkowka.txt");
        int l=0;
        if(liczbaSpotkańKołowych<=licznikSpotkania)l=liczbaSpotkańKołowych;
        else l=licznikSpotkania;

            zapis.println("Wyniki spotkań kołowych:");
            for (int i = 1; i <= l; i++) {
                zapis.print(tablicaDrużyn.getNazwa(drużyna1[i]));
                zapis.print(" (" + drużyna1[i] + ") " + wynikDlaDrużyny1[i] + ":" + wynikDlaDrużyny2[i] + " (" + drużyna2[i] + ") ");
                zapis.println(tablicaDrużyn.getNazwa(drużyna2[i]));
            }
            zapis.println();

        if (licznikSpotkania > liczbaSpotkańKołowych) {
            zapis.println("Wyniki półfinału:");
            zapis.print(tablicaDrużyn.getNazwa(półfinałDrużyna1[0]));
            zapis.print(" (" + półfinałDrużyna1[0] + ") " + półfinałWynikDlaDrużyny1[0] + ":" + półfinałWynikDlaDrużyny2[0] + " (" + półfinałDrużyna2[0] + ") ");
            zapis.println(tablicaDrużyn.getNazwa(półfinałDrużyna2[0]));

            if (licznikSpotkania > liczbaSpotkańKołowych + 1) {
                zapis.print(tablicaDrużyn.getNazwa(półfinałDrużyna1[1]));
                zapis.print(" (" + półfinałDrużyna1[1] + ") " + półfinałWynikDlaDrużyny1[1] + ":" + półfinałWynikDlaDrużyny2[1] + " (" + półfinałDrużyna2[1] + ") ");
                zapis.println(tablicaDrużyn.getNazwa(półfinałDrużyna2[1]));
                zapis.println();

                if (licznikSpotkania > liczbaSpotkańKołowych + 2) {
                    zapis.println("Wyniki finału:");
                    zapis.print(tablicaDrużyn.getNazwa(finałDrużyna1[0]));
                    zapis.print(" (" + finałDrużyna1[0] + ") " + finałWynikDlaDrużyny1[0] + ":" + finałWynikDlaDrużyny2[0] + " (" + finałDrużyna2[0] + ") ");
                    zapis.println(tablicaDrużyn.getNazwa(finałDrużyna2[0]));
                }
            }
        }
        zapis.close();
    }

    public void Generator() throws SpotkanieException {
        Scanner scan = new Scanner(System.in);
        int s=tablicaSędziów.getPierwszyAktywnySędzia();
        int s1=tablicaSędziów.getDrugiAktywnySędzia();
        int s2=tablicaSędziów.getTrzeciAktywnySędzia();
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
                dodajSpotkanie(i,j,w1,w2,s,s1,s2);
            }
        }
    }

}

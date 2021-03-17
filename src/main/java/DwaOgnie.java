import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DwaOgnie extends Spotkanie {

    public DwaOgnie(TablicaDrużyn tabD, TablicaSędziów tabS){super(tabD,tabS);}

    public void zapisz() throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("./src/main/resources/WynikiDwaOgnie.txt");
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
}

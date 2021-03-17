import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TablicaSędziów {

    Sędzia [] tabS;
    private static int licznik = 0;

    public TablicaSędziów(){
        tabS =new Sędzia[100];}



    public void dodaj(String imienazwisko){
        tabS[++licznik]=new Sędzia(imienazwisko);
        tabS[licznik].setNumer(licznik);
    }
    public void dodaj(){
        tabS[++licznik]=new Sędzia();
        tabS[licznik].setNumer(licznik);
    }

    public void usuń(int n){
        if(n>licznik){System.out.println("Nie ma sędziego o zadanym numerze.");}
        else {tabS[n].setCzyAktywny(false);}
    }

    public void przeglądaj(){
        for(int i=1;i<=licznik;i++){
            if(tabS[i].getCzyAktywny()==true)System.out.println(tabS[i].getNumer()+" "+ tabS[i].getImieNazwisko());
        }
    }

    public void zapisz() throws FileNotFoundException {
        PrintWriter zapisS = new PrintWriter("./src/main/resources/DaneSedziowieK.txt");
        for(int i=1;i<=licznik;i++) {
            if(tabS[i].getCzyAktywny()==true)zapisS.println(tabS[i].getNumer()+" "+ tabS[i].getImieNazwisko());
        }
        zapisS.close();
    }

    public boolean getCzyAktywny(int n){return tabS[n].getCzyAktywny();}

    public int getLicznikAktywnychSędziów(){
        int l = 0;
        for (int i = 1; i <= licznik; i++)
            if (tabS[i].getCzyAktywny() == true) l++;
        return l;
    }

    public int getLicznik(){
        return licznik;
    }

    public int getPierwszyAktywnySędzia(){
        int a=0;
        for (int i = 1; i <= licznik; i++){
            if (tabS[i].getCzyAktywny() == true){
                a= i;
                break;
            }

        }
        return a;
    }
    public int getDrugiAktywnySędzia(){
        int a=0,b=0;
        for (int i = 1; i <= licznik; i++){
            if (tabS[i].getCzyAktywny() == true){
                if(a!=0){
                    b=i;
                    break;
                }
                a= i;
            }

        }
        return b;
    }
    public int getTrzeciAktywnySędzia(){
        int a=0,b=0,c=0;
        for (int i = 1; i <= licznik; i++){
            if (tabS[i].getCzyAktywny() == true){
                if(b!=0){
                    c=i;
                    break;
                }
                if(a!=0){
                    b=i;
                }
                a= i;
            }
        }
        return c;
    }
}
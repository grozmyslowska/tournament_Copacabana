import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void Rejestracja(TablicaDrużyn D,TablicaSędziów S) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int wyborPolecenia;
        boolean obsługaPętli = true;

        while (obsługaPętli) {
            System.out.println("INSTRUKCJA:( 1 Dodaj drużynę. 2 Dodaj sędziego. 3 Usuń drużynę. 4 Usuń sędziego. 5 Przeglądaj drużyny. 6 Przeglądaj sędziów. 7 Zapisz i zakończ. )");

            wyborPolecenia = scan.nextInt();
            switch (wyborPolecenia) {

                case 1:

                    System.out.print("Podaj nazwę drużyny: ");
                    String nazwa;
                    Scanner odczyt1 = new Scanner(System.in);
                    nazwa = odczyt1.nextLine();
                    D.zgłoś(nazwa);
                    break;

                case 2:

                    System.out.print("Podaj imię i nazwisko sędziego: ");
                    String imienazwisko;
                    Scanner odczyt2 = new Scanner(System.in);
                    imienazwisko = odczyt2.nextLine();
                    S.dodaj(imienazwisko);
                    break;

                case 3:
                    System.out.print("Podaj numer drużyny do wycofania: ");
                    D.wycofaj(scan.nextInt());
                    break;

                case 4:
                    System.out.print("Podaj numer sędziego do usunięcia: ");
                    S.usuń(scan.nextInt());
                    break;

                case 5:
                    D.przeglądaj();
                    break;

                case 6:
                    S.przeglądaj();
                    break;

                case 7:
                    if(D.getLicznikAktywnychDrużyn()<5) System.out.println("Do przeprowadzenia zawodów drużyn musi być przynajmniej 5.");
                    else if(S.getLicznikAktywnychSędziów()<3)System.out.println("Do przeprowadzenia zawodów sędziów musi być przynajmniej 3.");
                    else{
                    System.out.println("Czy na pewno chcesz zamknąć rejestrację? Po zamknięciu zgłaszanie i wycofywanie nie będzie już możliwe. (1-tak, 0-nie)");
                    wyborPolecenia = scan.nextInt();
                    if(wyborPolecenia==1) {
                        obsługaPętli = false;
                        System.out.println("Zakończono zgłaszanie i wycofywanie.\n\n");
                        D.zapisz();
                        S.zapisz();
                    }}
                    break;

                default:
                    System.out.println("Nie ma takiego polecenia.");
                    break;
            }
        }

    }
    public static void RejestracjaZPliku(TablicaDrużyn D,TablicaSędziów S) throws FileNotFoundException {
        Scanner odczyt = new Scanner(new File("./src/main/resources/DaneDruzyny.txt"));
        while(odczyt.hasNext()){
            int nr = odczyt.nextInt();
            String tekst = odczyt.nextLine();
            D.zgłoś(tekst);
        }
        odczyt = new Scanner(new File("./src/main/resources/DaneSedziowie.txt"));
        while(odczyt.hasNext()){
            String tekst = odczyt.nextLine();
            S.dodaj(tekst);
        }
        System.out.println();
        System.out.println("Drużyny:");
        D.przeglądaj();
        System.out.println();
        System.out.println("Sędziowie:");
        S.przeglądaj();
        System.out.println();
    }
    public static void ZawodySiatkówki(TablicaDrużyn D,TablicaSędziów S) throws SpotkanieException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int wyborPolecenia;
        boolean obsługaPętli = true;
        Siatkówka siatkówka = new Siatkówka(D, S);

        if(D.getLicznikAktywnychDrużyn()>4) {
            System.out.println("Czy chcesz wygenerować zawody kołowe? (1-tak, 0-nie)");
            wyborPolecenia = scan.nextInt();
            if (wyborPolecenia == 1) siatkówka.Generator();
        }

        while (obsługaPętli) {
            System.out.println("INSTRUKCJA:( 1 Dodaj spotkanie. 2 Wyświetl wyniki systemu kołowego. 3 Wyświetl wyniki z nazwami drużyn." +
                    " 4 Wyświetl wyniki spotkania (po drużynach). 5 Wyświetl wyniki spotkania (po numerze spotkania). 6 Zapisz i zakończ. )");

            wyborPolecenia = scan.nextInt();
            switch (wyborPolecenia) {

                case 1:

                    int d1 = 0, d2 = 0, w1 = 0, w2 = 0, s = 0, s1 = 0, s2 = 0;
                    System.out.print("Podaj numer 1 drużyny: ");
                    d1 = scan.nextInt();
                    System.out.print("Podaj numer 2 drużyny: ");
                    d2 = scan.nextInt();
                    System.out.print("Podaj wynik dla 1 drużyny: ");
                    w1 = scan.nextInt();
                    System.out.print("Podaj wynik dla 2 drużyny: ");
                    w2 = scan.nextInt();
                    System.out.print("Podaj numer sędziego głównego: ");
                    s = scan.nextInt();
                    System.out.print("Podaj numer 1 sędziego: ");
                    s1 = scan.nextInt();
                    System.out.print("Podaj numer 2 sędziego: ");
                    s2 = scan.nextInt();

                    try {
                        siatkówka.dodajSpotkanie(d1, d2, w1, w2, s, s1, s2);
                    }
                    catch (SpotkanieException a) {
                        System.out.println(a.getMessage());
                    }
                    break;

                case 2:

                    siatkówka.wyświetlWyniki();
                    break;

                case 3:
                    siatkówka.wyświetlWynikiZNazwamiDrużyn();
                    break;

                case 4:
                    int d11 = 0, d22 = 0;
                    System.out.println("Podaj numer 1 drużyny:");
                    d11 = scan.nextInt();
                    System.out.println("Podaj numer 2 drużyny:");
                    d22 = scan.nextInt();
                    siatkówka.wyświetlWynikSpotkania(d11, d22);
                    break;

                case 5:
                    int nr = 0;
                    System.out.println("Podaj numer spotkania:");
                    nr = scan.nextInt();
                    siatkówka.wyświetlWynikSpotkania(nr);
                    break;

                case 6:
                    try {
                        siatkówka.zapisz();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    obsługaPętli = false;
                    break;

                default:
                    System.out.println("Nie ma takiego polecenia.");
                    break;
            }
        }

    }
    public static void ZawodyDwaOgnie(TablicaDrużyn D,TablicaSędziów S) throws SpotkanieException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int wyborPolecenia;
        boolean obsługaPętli = true;
        DwaOgnie dwaOgnie = new DwaOgnie(D, S);

        if(D.getLicznikAktywnychDrużyn()>4) {
            System.out.println("Czy chcesz wygenerować zawody kołowe? (1-tak, 0-nie)");
            wyborPolecenia = scan.nextInt();
            if (wyborPolecenia == 1) dwaOgnie.Generator();
        }

        while (obsługaPętli) {
            System.out.println("INSTRUKCJA:( 1 Dodaj spotkanie. 2 Wyświetl wyniki systemu kołowego. 3 Wyświetl wyniki z nazwami drużyn." +
                    " 4 Wyświetl wyniki spotkania (po drużynach). 5 Wyświetl wyniki spotkania (po numerze spotkania). 6 Zapisz i zakończ. )");

            wyborPolecenia = scan.nextInt();
            switch (wyborPolecenia) {

                case 1:

                    int d1 = 0, d2 = 0, w1 = 0, w2 = 0, s = 0;
                    System.out.print("Podaj numer 1 drużyny: ");
                    d1 = scan.nextInt();
                    System.out.print("Podaj numer 2 drużyny: ");
                    d2 = scan.nextInt();
                    System.out.print("Podaj wynik dla 1 drużyny: ");
                    w1 = scan.nextInt();
                    System.out.print("Podaj wynik dla 2 drużyny: ");
                    w2 = scan.nextInt();
                    System.out.print("Podaj numer sędziego: ");
                    s = scan.nextInt();

                    try {
                        dwaOgnie.dodajSpotkanie(d1, d2, w1, w2, s);
                    }
                    catch (SpotkanieException a) {
                        System.out.println(a.getMessage());
                    }
                    break;

                case 2:

                    dwaOgnie.wyświetlWyniki();
                    break;

                case 3:
                    dwaOgnie.wyświetlWynikiZNazwamiDrużyn();
                    break;

                case 4:
                    int d11 = 0, d22 = 0;
                    System.out.print("Podaj numer 1 drużyny: ");
                    d11 = scan.nextInt();
                    System.out.print("Podaj numer 2 drużyny: ");
                    d22 = scan.nextInt();
                    dwaOgnie.wyświetlWynikSpotkania(d11, d22);
                    break;

                case 5:
                    int nr = 0;
                    System.out.print("Podaj numer spotkania: ");
                    nr = scan.nextInt();
                    dwaOgnie.wyświetlWynikSpotkania(nr);
                    break;

                case 6:
                    try {
                        dwaOgnie.zapisz();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    obsługaPętli = false;
                    break;

                default:
                    System.out.println("Nie ma takiego polecenia.");
                    break;
            }
        }
    }
    public static void ZawodyPrzeciąganieLiny(TablicaDrużyn D,TablicaSędziów S) throws SpotkanieException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int wyborPolecenia;
        boolean obsługaPętli = true;
        PrzeciąganieLiny przeciąganieLiny = new PrzeciąganieLiny(D, S);

        if(D.getLicznikAktywnychDrużyn()>4) {
            System.out.println("Czy chcesz wygenerować zawody kołowe? (1-tak, 0-nie)");
            wyborPolecenia = scan.nextInt();
            if (wyborPolecenia == 1) przeciąganieLiny.Generator();
        }

        while (obsługaPętli) {
            System.out.println("INSTRUKCJA:( 1 Dodaj spotkanie. 2 Wyświetl wyniki systemu kołowego. 3 Wyświetl wyniki z nazwami drużyn." +
                    " 4 Wyświetl wyniki spotkania (po drużynach). 5 Wyświetl wyniki spotkania (po numerze spotkania). 6 Zapisz i zakończ. )");

            wyborPolecenia = scan.nextInt();
            switch (wyborPolecenia) {

                case 1:

                    int d1 = 0, d2 = 0, w1 = 0, w2 = 0, s = 0;
                    System.out.print("Podaj numer 1 drużyny: ");
                    d1 = scan.nextInt();
                    System.out.print("Podaj numer 2 drużyny: ");
                    d2 = scan.nextInt();
                    System.out.print("Podaj wynik dla 1 drużyny: ");
                    w1 = scan.nextInt();
                    System.out.print("Podaj wynik dla 2 drużyny: ");
                    w2 = scan.nextInt();
                    System.out.print("Podaj numer sędziego: ");
                    s = scan.nextInt();

                    try {
                        przeciąganieLiny.dodajSpotkanie(d1, d2, w1, w2, s);
                    }
                    catch (SpotkanieException a) {
                        System.out.println(a.getMessage());
                    }

                    break;

                case 2:

                    przeciąganieLiny.wyświetlWyniki();
                    break;

                case 3:
                    przeciąganieLiny.wyświetlWynikiZNazwamiDrużyn();
                    break;

                case 4:
                    int d11 = 0, d22 = 0;
                    System.out.print("Podaj numer 1 drużyny: ");
                    d11 = scan.nextInt();
                    System.out.print("Podaj numer 2 drużyny: ");
                    d22 = scan.nextInt();
                    przeciąganieLiny.wyświetlWynikSpotkania(d11, d22);
                    break;

                case 5:
                    int nr = 0;
                    System.out.print("Podaj numer spotkania: ");
                    nr = scan.nextInt();
                    przeciąganieLiny.wyświetlWynikSpotkania(nr);
                    break;

                case 6:
                    try {
                        przeciąganieLiny.zapisz();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    obsługaPętli = false;
                    break;

                default:
                    System.out.println("Nie ma takiego polecenia.");
                    break;
            }
        }
    }


    public static void main(String[] args) throws SpotkanieException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Witaj na plaży Kopakabana!\n" +
                "\nOprócz plażowania toczą się tu też rozgrywki w 3 grach zespołowych: siatkówka plażowa," +
                "\n 2 ognie i przeciąganie liny. Każda z drużyn ma swoją nazwę i numer. Toczą one " +
                "\nmecze/spotkania na zasadzie każdy z każdym. Pierwsze 4 drużyny z największą liczbą " +
                "\nzwycięstw przechodzą do półfinałów a ich zwycięzcy do finałów. Każde ze spotkań sędziuje " +
                "\nsędzia, a dodatkowo w siatkówce 2 sędziów pomocniczych. Kto będzie zwyciązcą?\n");

        System.out.println("REJESTRACJĘ CZAS ZACZĄĆ! \n\n");

        TablicaDrużyn D = new TablicaDrużyn();
        TablicaSędziów S = new TablicaSędziów();

        System.out.print("Czy chcesz wgrać dane z pliku, czy z klawiatury? (1-plik, 0-klawiatura) ");
        if (scan.nextInt() == 0) Rejestracja(D, S);
        else RejestracjaZPliku(D,S);


        boolean Ss = true, Dd = true, Pp = true;
        int wyborZawodow;

        while ((Ss) || (Dd) || (Pp)) {
            System.out.println("Jakie zawody rozpocząć?");
            if (Ss) System.out.println("1 Siatkówka.");
            if (Dd) System.out.println("2 Dwa ognie.");
            if (Pp) System.out.println("3 Przeciąganie liny.");

            wyborZawodow = scan.nextInt();
            switch (wyborZawodow) {
                case 1:
                    Ss = false;
                    ZawodySiatkówki(D,S);
                    break;
                case 2:
                    Dd = false;
                    ZawodyDwaOgnie(D,S);
                    break;

                case 3:
                    Pp = false;
                    ZawodyPrzeciąganieLiny(D,S);
                    break;

                default:
                    break;
            }
        }
    }

}
package pl.com.bottega.photostock.sales.presentation;

import java.util.Scanner;

public class MainScreen {

    private final Scanner scanner;
    private final SearchScreen searchScreen;
    private final ReservationScreen reservationScreen;
    private final OfferScreen offerScreen;
    private LightBoxScreen lightBoxScreen;

    public MainScreen(Scanner scanner,
                      SearchScreen searchScreen,
                      ReservationScreen reservationScreen,
                      OfferScreen offerScreen, LightBoxScreen lightBoxScreen) {
        this.scanner = scanner;
        this.searchScreen = searchScreen;
        this.reservationScreen = reservationScreen;
        this.offerScreen = offerScreen;
        this.lightBoxScreen = lightBoxScreen;
    }

    public void print() {
        while (true) {
            printMenu();
            String command = getCommand();
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        switch (command) {
            case "1":
                searchScreen.print();
                break;
            case "2":
                reservationScreen.print();
                break;
            case "3":
                offerScreen.print();
                break;
            case "4":
                lightBoxScreen.print();
                break;
            default:
                System.out.println("Sorry nie rozumiem ;(");
        }
    }

    private void printMenu() {
        System.out.println("1. Wyszukaj produkty");
        System.out.println("2. Zarezerwuj produkt");
        System.out.println("3. Wygeneruj ofertę");
        System.out.println("4. Light boxy");
    }

    private String getCommand() {
        System.out.print("Co chcesz zrobić: ");
        return scanner.nextLine();
    }
}

package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.model.client.CantAffordException;
import pl.com.bottega.photostock.sales.model.purchase.Offer;
import pl.com.bottega.photostock.sales.model.purchase.OfferMismatchException;
import pl.com.bottega.photostock.sales.model.product.Product;

import java.util.Scanner;

public class OfferScreen {
    private Scanner scanner;
    private final LoginScreen loginScreen;
    private final PurchaseProcess purchaseProcess;

    public OfferScreen(Scanner scanner, LoginScreen loginScreen, PurchaseProcess purchaseProcess) {
        this.scanner = scanner;
        this.purchaseProcess = purchaseProcess;
        this.loginScreen = loginScreen;
    }

    public void print() {
        String reservationNumber = purchaseProcess.getReservation(loginScreen.getAuthenticatedClientNumber());
        try {
            Offer offer = purchaseProcess.calculateOffer(reservationNumber);
            printOffer(offer);
            askForConfirmation(offer, reservationNumber);
        } catch (IllegalStateException ex) {
            System.out.println("Nie ma aktywnych produktów w rezerwacji. Dodaj produkty!!!");
        }
    }

    private void askForConfirmation(Offer offer, String reservationNumber) {
        System.out.println("Czy chcesz dokonać zakupu? (t/n)");
        String answer = scanner.nextLine();
        if (answer.equals("t")) {
            try {
                purchaseProcess.confirm(reservationNumber, offer);
                System.out.println("Brawo dziękujemy gratulujemy właściwej decyzji!!!!");
            } catch (CantAffordException ex) {
                System.out.println("Niestety nie masz wystarczających środków na koncie (HINT Weź kredyt, zmień pracę!!!).");
            } catch (OfferMismatchException ex) {
                System.out.println("Za późno oferta wygasła!!!!");
            }
        }
        else {
            System.out.println("Szkoda ;( Może następnym razem.");
        }
    }

    private void printOffer(Offer offer) {
        System.out.println("Oferta specialnie dla Ciebie:");
        int i = 1;
        for (Product product : offer.getItems()) {
            System.out.println(String.format("%d. %s", i++, product.getName()));
        }
        System.out.println(String.format("Zaledwie: %s", offer.getTotalCost()));
    }
}

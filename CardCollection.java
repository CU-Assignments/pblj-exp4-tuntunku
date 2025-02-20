import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" + "symbol='" + symbol + '\'' + ", value='" + value + '\'' + '}';
    }
}

public class CardCollection {
    private Collection<Card> cards = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addCard() {
        System.out.print("Enter card symbol (e.g., Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter card value (e.g., Ace, 2, King): ");
        String value = scanner.nextLine();
        cards.add(new Card(symbol, value));
        System.out.println("Card added successfully!");
    }

    public void searchBySymbol() {
        System.out.print("Enter the symbol to search: ");
        String symbol = scanner.nextLine();
        boolean found = false;
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for the given symbol.");
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public static void main(String[] args) {
        CardCollection cardCollection = new CardCollection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCard Collection Management");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cardCollection.addCard();
                    break;
                case 2:
                    cardCollection.searchBySymbol();
                    break;
                case 3:
                    cardCollection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

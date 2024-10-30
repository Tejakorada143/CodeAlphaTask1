import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

class Portfolio {
    private HashMap<String, Integer> holdings;

    public Portfolio() {
        holdings = new HashMap<>();
    }

    public void buyStock(String symbol, int quantity) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        System.out.printf("Bought %d shares of %s.%n", quantity, symbol);
    }

    public void sellStock(String symbol, int quantity) {
        if (holdings.containsKey(symbol) && holdings.get(symbol) >= quantity) {
            holdings.put(symbol, holdings.get(symbol) - quantity);
            if (holdings.get(symbol) == 0) {
                holdings.remove(symbol);
            }
            System.out.printf("Sold %d shares of %s.%n", quantity, symbol);
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio() {
        System.out.println("=== Portfolio Holdings ===");
        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (String symbol : holdings.keySet()) {
                System.out.printf("%s: %d shares%n", symbol, holdings.get(symbol));
            }
        }
    }
}

public class StockTradingPlatform {
    private static ArrayList<Stock> market = new ArrayList<>();
    private static Portfolio portfolio = new Portfolio();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMarket();

        while (true) {
            System.out.println("\n=== Stock Trading Platform ===");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewMarketData();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting platform. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeMarket() {
        market.add(new Stock("AAPL", 150.00));
        market.add(new Stock("GOOGL", 2800.00));
        market.add(new Stock("AMZN", 3400.00));
        market.add(new Stock("TSLA", 700.00));
    }

    private static void viewMarketData() {
        System.out.println("=== Current Market Data ===");
        for (Stock stock : market) {
            System.out.printf("%s: $%.2f%n", stock.getSymbol(), stock.getPrice());
        }
    }

    private static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        for (Stock stock : market) {
            if (stock.getSymbol().equals(symbol)) {
                portfolio.buyStock(symbol, quantity);
                return;
            }
        }
        System.out.println("Stock symbol not found.");
    }

    private static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        portfolio.sellStock(symbol, quantity);
    }
}

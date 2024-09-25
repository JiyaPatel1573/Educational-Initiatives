import java.util.ArrayList;
import java.util.List;

// Subject (Stock)
class Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public Stock(String name, double price) {
        this.stockName = name;
        this.price = price;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
}

// Observer Interface
interface Observer {
    void update(String stockName, double price);
}

// Concrete Observer 1 (StockTrader)
class StockTrader implements Observer {
    private String name;

    public StockTrader(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("Trader " + name + ": The price of " + stockName + " has changed to " + price + ".");
    }
}

// Test the Observer Pattern
public class ObserverPattern{
    public static void main(String[] args) {
        Stock appleStock = new Stock("Apple", 150.00);
        StockTrader trader1 = new StockTrader("Alice");
        StockTrader trader2 = new StockTrader("Bob");

        appleStock.subscribe(trader1);
        appleStock.subscribe(trader2);

        appleStock.setPrice(155.00);
        appleStock.setPrice(160.00);
    }
}


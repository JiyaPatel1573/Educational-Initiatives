// Component Interface (Coffee)
interface ICoffee {
    String getDescription();
    double getCost();
}

// Concrete Component (Simple Coffee)
class SimpleCoffee implements ICoffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 5.00; // Base cost of simple coffee
    }
}

// Decorator Class (CoffeeDecorator)
abstract class CoffeeDecorator implements ICoffee {
    protected ICoffee coffee;

    public CoffeeDecorator(ICoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// Concrete Decorator 1 (Milk)
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.50; // Milk cost is $1.50
    }
}

// Concrete Decorator 2 (Sugar)
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50; // Sugar cost is $0.50
    }
}

// Test the Decorator Pattern
public class DecoratorPattern{
    public static void main(String[] args) {
        // Order a simple coffee
        ICoffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " Cost: $" + coffee.getCost());

        // Add milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " Cost: $" + coffee.getCost());

        // Add sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " Cost: $" + coffee.getCost());
    }
}

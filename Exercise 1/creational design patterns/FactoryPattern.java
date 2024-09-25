// Abstract Product (Animal)
interface Animal {
    void speak();
}

// Concrete Product 1 (Dog)
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Dog says: Woof Woof!");
    }
}

// Concrete Product 2 (Cat)
class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Cat says: Meow!");
    }
}

// Concrete Product 3 (Duck)
class Duck implements Animal {
    @Override
    public void speak() {
        System.out.println("Duck says: Quack!");
    }
}

// Factory Class (Animal Factory)
class AnimalFactory {
    public Animal getAnimal(String animalType) {
        switch (animalType.toLowerCase()) {
            case "dog":
                return new Dog();
            case "cat":
                return new Cat();
            case "duck":
                return new Duck();
            default:
                throw new IllegalArgumentException("Unknown animal type.");
        }
    }
}

// Test the Factory Pattern
public class FactoryPattern{
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();

        Animal dog = factory.getAnimal("dog");
        dog.speak();

        Animal cat = factory.getAnimal("cat");
        cat.speak();

        Animal duck = factory.getAnimal("duck");
        duck.speak();
    }
}

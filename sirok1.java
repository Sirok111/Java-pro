public class sirok1 {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобік");
        Cat catMurka = new Cat("Мурка");

        dogBobik.run(150);
        dogBobik.swim(5);

        catMurka.run(100);
        catMurka.swim(5);

        System.out.println("Кількість тварин: " + Animal.getAnimalCount());
        System.out.println("Кількість собак: " + Dog.getDogCount());
        System.out.println("Кількість котів: " + Cat.getCatCount());
    }
}

class Animal {
    protected String name;
    private static int animalCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public void run(int distance) {
        System.out.println(name + " пробіг(ла) " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплив(ла) " + distance + " м.");
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}

class Dog extends Animal {
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробіг " + distance + " м.");
        } else {
            System.out.println(name + " не зміг пробігти " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= MAX_SWIM_DISTANCE) {
            System.out.println(name + " проплив " + distance + " м.");
        } else {
            System.out.println(name + " не зміг пропливти " + distance + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}

class Cat extends Animal {
    private static final int MAX_RUN_DISTANCE = 200;
    private static int catCount = 0;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробіг(ла) " + distance + " м.");
        } else {
            System.out.println(name + " не зміг(ла) пробігти " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не вміє плавати.");
    }

    public static int getCatCount() {
        return catCount;
    }
}



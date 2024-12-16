
interface Participant {
    boolean run(int distance);
    boolean jump(int height);
    String getName();
}

class Human implements Participant {
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;

    public Human(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println("Людина " + name + " пробігла " + distance + " м.");
            return true;
        } else {
            System.out.println("Людина " + name + " не змогла пробігти " + distance + " м.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println("Людина " + name + " перестрибнула " + height + " м.");
            return true;
        } else {
            System.out.println("Людина " + name + " не змогла перестрибнути " + height + " м.");
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
class Cat implements Participant {
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;

    public Cat(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println("Кіт " + name + " пробіг " + distance + " м.");
            return true;
        } else {
            System.out.println("Кіт " + name + " не зміг пробігти " + distance + " м.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println("Кіт " + name + " перестрибнув " + height + " м.");
            return true;
        } else {
            System.out.println("Кіт " + name + " не зміг перестрибнути " + height + " м.");
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

// Реалізація Робота
class Robot implements Participant {
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;

    public Robot(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println("Робот " + name + " пробіг " + distance + " м.");
            return true;
        } else {
            System.out.println("Робот " + name + " не зміг пробігти " + distance + " м.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println("Робот " + name + " перестрибнув " + height + " м.");
            return true;
        } else {
            System.out.println("Робот " + name + " не зміг перестрибнути " + height + " м.");
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

// Інтерфейс для перешкоди
interface Obstacle {
    boolean overcome(Participant participant);
}

// Реалізація Бігової доріжки
class Track implements Obstacle {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public boolean overcome(@org.jetbrains.annotations.NotNull Participant participant) {
        return participant.run(length);
    }
}

// Реалізація Стіни
class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.jump(height);
    }
}

// Головний клас
public class sir {
    public static void main(String[] args) {
        Participant[] participants = {
                new Human("Олексій", 100, 2),
                new Cat("Мурчик", 200, 3),
                new Robot("R2D2", 150, 1)
        };

        Obstacle[] obstacles = {
                new Track(100),
                new Wall(2),
                new Track(150),
                new Wall(3)
        };

        for (Participant participant : participants) {
            System.out.println("Учасник " + participant.getName() + " розпочинає проходження перешкод.");
            boolean completedAll = true;

            for (Obstacle obstacle : obstacles) {
                if (!obstacle.overcome(participant)) {
                    System.out.println("Учасник " + participant.getName() + " вибув із змагання.");
                    completedAll = false;
                    break;
                }
            }

            if (completedAll) {
                System.out.println("Учасник " + participant.getName() + " успішно пройшов усі перешкоди!");
            }
        }
    }
}

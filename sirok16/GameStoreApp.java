package sirok16.app;

import sirok16.dao.GameDao;
import sirok16.model.Game;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GameStoreApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GameDao dao = new GameDao();

    public static void main(String[] args) throws Exception {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addGame();
                case "2" -> deleteGame();
                case "3" -> searchByName();
                case "4" -> filterByPrice();
                case "5" -> filterByType();
                case "6" -> showSortedByDate();
                case "7" -> showAllGames();
                case "0" -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("Невірна команда.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                === Магазин Ігор ===
                1. Додати гру
                2. Видалити гру
                3. Пошук гри за назвою
                4. Фільтр за ціною
                5. Фільтр за типом
                6. Всі ігри (за датою додавання)
                7. Всі ігри
                0. Вихід
                Виберіть опцію:""");
    }

    private static void addGame() throws Exception {
        Game game = new Game();
        System.out.print("Назва: ");
        game.setName(scanner.nextLine());

        System.out.print("Дата випуску (yyyy-mm-dd): ");
        game.setReleaseDate(LocalDate.parse(scanner.nextLine()));

        System.out.print("Рейтинг: ");
        game.setRating(Double.parseDouble(scanner.nextLine()));

        System.out.print("Ціна: ");
        game.setCost(Double.parseDouble(scanner.nextLine()));

        System.out.print("Опис: ");
        game.setDescription(scanner.nextLine());

        System.out.print("Тип: ");
        game.setType(scanner.nextLine());

        game.setCreationDate(LocalDate.now());

        dao.addGame(game);
        System.out.println("Гру додано!");
    }

    private static void deleteGame() throws Exception {
        System.out.print("Введіть ID гри для видалення: ");
        int id = Integer.parseInt(scanner.nextLine());
        dao.deleteGame(id);
        System.out.println("Гру видалено.");
    }

    private static void searchByName() throws Exception {
        System.out.print("Введіть назву гри: ");
        List<Game> games = dao.findByName(scanner.nextLine());
        games.forEach(System.out::println);
    }

    private static void filterByPrice() throws Exception {
        System.out.print("Введіть максимальну ціну: ");
        double price = Double.parseDouble(scanner.nextLine());
        List<Game> games = dao.filterByPrice(price);
        games.forEach(System.out::println);
    }

    private static void filterByType() throws Exception {
        System.out.print("Введіть тип гри: ");
        List<Game> games = dao.filterByType(scanner.nextLine());
        games.forEach(System.out::println);
    }

    private static void showSortedByDate() throws Exception {
        List<Game> games = dao.findAllSortedByCreationDate();
        games.forEach(System.out::println);
    }

    private static void showAllGames() throws Exception {
        List<Game> games = dao.findAll();
        games.forEach(System.out::println);
    }
}

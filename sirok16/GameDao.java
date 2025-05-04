package sirok16.dao;

import sirok16.model.Game;
import sirok16.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    public void addGame(Game game) throws SQLException {
        String sql = "INSERT INTO Games (name, release_date, rating, cost, description, type, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getName());
            stmt.setDate(2, Date.valueOf(game.getReleaseDate()));
            stmt.setDouble(3, game.getRating());
            stmt.setDouble(4, game.getCost());
            stmt.setString(5, game.getDescription());
            stmt.setString(6, game.getType());
            stmt.setDate(7, Date.valueOf(game.getCreationDate()));
            stmt.executeUpdate();
        }
    }

    public void deleteGame(int id) throws SQLException {
        String sql = "DELETE FROM Games WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Game> findByName(String name) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games WHERE name LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    games.add(mapRow(rs));
                }
            }
        }
        return games;
    }

    public List<Game> filterByPrice(double maxPrice) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games WHERE cost <= ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, maxPrice);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    games.add(mapRow(rs));
                }
            }
        }
        return games;
    }

    public List<Game> filterByType(String type) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games WHERE type = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    games.add(mapRow(rs));
                }
            }
        }
        return games;
    }

    public List<Game> findAllSortedByCreationDate() throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games ORDER BY creation_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                games.add(mapRow(rs));
            }
        }
        return games;
    }

    public List<Game> findAll() throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                games.add(mapRow(rs));
            }
        }
        return games;
    }

    private Game mapRow(ResultSet rs) throws SQLException {
        Game game = new Game();
        game.setId(rs.getInt("id"));
        game.setName(rs.getString("name"));
        game.setReleaseDate(rs.getDate("release_date").toLocalDate());
        game.setRating(rs.getDouble("rating"));
        game.setCost(rs.getDouble("cost"));
        game.setDescription(rs.getString("description"));
        game.setType(rs.getString("type"));
        game.setCreationDate(rs.getDate("creation_date").toLocalDate());
        return game;
    }
}


package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CafeManha {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/cafedb";
		String dbusername = "root";
		String dbpassword = "JMh#241128";

		try {
			Connection connection = DriverManager.getConnection(jdbcURL, dbusername, dbpassword);

			if (connection != null) {
				System.out.println("Conectado ao banco de dados!");

				// CafeManha.addUser(connection, "", "", "");
				// CafeManha.addUser(connection, "", "", "");

				// CafeManha.listUsers(connection);

				// CafeManha.updateUsers(connection, "BRuno", "0000000000", "peixe");
				
				CafeManha.deleteUser( connection, "Guilherme" );

				connection.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public static void addUser(Connection connection, String name, String cpf, String prato) {

		try {
			String sql = "INSERT INTO usuarios (nome, cpf, prato) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, cpf);
			statement.setString(3, prato);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("Novo usuario inserido com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao adicionar usuario.");
		}
	}

	public static void listUsers(Connection connection) {
		try {
			String sql = "SELECT * FROM usuarios";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			int count = 0;

			while (result.next()) {
				String username = result.getString("nome");
				String cpf = result.getString("cpf");
				String prato = result.getString("prato");

				System.out.println(username + "," + cpf + "," + prato);
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar usuarios.");
		}
	}

	public static void updateUsers(Connection connection, String nome, String cpf, String prato) {
		try {
			String sql = "UPDATE usuarios SET nome=?, cpf=?, prato=? WHERE nome=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setString(2, cpf);
			statement.setString(3, prato);
			statement.setString(4, nome);

			int rowsUpdated = statement.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Atualizado com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao atualizar usuario.");
		}
	}

	public static void deleteUser(Connection connection, String nome) {
		try {
			String sql = "DELETE FROM usuarios WHERE nome=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);

			int rowsDeleted = statement.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Exclu?do com sucesso!");
			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar usu?rio.");
		}
	}
}

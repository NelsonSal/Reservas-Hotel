package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva nuevaReserva) {

		try (con) {
			
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (FechaEntrada,FechaSalida,valor,FormaPago)" + "VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(nuevaReserva, statement);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private void ejecutaRegistro(Reserva nuevaReserva, PreparedStatement statement) throws SQLException {

		statement.setDate(1, nuevaReserva.getFechaIn());
		statement.setDate(2, nuevaReserva.getFechaOut());
		statement.setString(3, nuevaReserva.getValorReserva());
		statement.setString(4, nuevaReserva.getFormaDePago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys(); // Try with resourses. Cierra el resulset solo.
																	// Autocloseable
		try (resultSet) {
			while (resultSet.next()) {
				nuevaReserva.setIdReserva(resultSet.getInt(1));
				//System.out.println(String.format("Fue insertado el producto %s", nuevaReserva.getIdReserva()));
			}
		}

	}

	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		// ConnectionFactory factory = new ConnectionFactory();
		// final Connection con =factory.recuperaConexion();
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT Id_Reserva,FechaEntrada,FechaSalida,Valor,FormaPago FROM reservas");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("Id_Reserva"), resultSet.getDate("FechaEntrada"),
								resultSet.getDate("FechaSalida"), resultSet.getString("Valor"),
								resultSet.getString("FormaPago"));

						resultado.add(fila);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

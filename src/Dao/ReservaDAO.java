package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.ConnectionFactory;
import modelo.Huesped;
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
		ConnectionFactory factory = new ConnectionFactory();
		 final Connection con =factory.recuperaConexion();
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
			con.close();
			return resultado;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarReserva(Integer Id_Reserva) {
		List<Reserva> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con =factory.recuperaConexion(); 
		
		try(con){
			var querySelector = "SELECT Id_Reserva,FechaEntrada,FechaSalida,Valor,FormaPago FROM reservas "
					+ "WHERE Id_Reserva=?";
			System.out.println(querySelector);
			final PreparedStatement statement = con.prepareStatement(querySelector);
			
			
			try(statement){
				statement.setInt(1, Id_Reserva);
				statement.execute();
			
				final ResultSet resultSet=statement.getResultSet();
				try(resultSet){
						while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("Id_Reserva"),
								resultSet.getDate("FechaEntrada"),
								resultSet.getDate("FechaSalida"),
								resultSet.getString("Valor"),
								resultSet.getString("FormaPago"));
						
						resultado.add(fila);
						
					}
				} 
			}
			con.close();
			return resultado;	
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificarReserva(Integer id_Reserva, Date fechaIn, Date fechaOut, String valor, String formaDePago) {
		final Connection con= new ConnectionFactory().recuperaConexion();
		try(con){
			final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET "
		            + " FechaEntrada = ?"
		            + ", FechaSalida = ?"
		            + ", valor = ?"
		            + ", formaPago = ?"
		            + " WHERE Id_Reserva = ?");
			try(statement){
				statement.setDate(1, fechaIn);
				statement.setDate(2, fechaOut);
				statement.setString(3, valor);
				statement.setString(4, formaDePago);
				statement.setInt(5, id_Reserva);
				statement.execute();
				int updateCount = statement.getUpdateCount();
				con.close();
				return updateCount;
			}
		
		}catch (SQLException e) {
		throw new RuntimeException(e);
	
		}
	}

	public int eliminarReserva(Integer id_Reserva) {
		final Connection con= new ConnectionFactory().recuperaConexion();
		try(con){
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas  WHERE Id_Reserva =?");
			try(statement){
				statement.setInt(1, id_Reserva);
				statement.execute();
				int updateCount = statement.getUpdateCount(); 
				System.out.println("Se elimino el producto con ID: "+ updateCount);
				con.close();
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
}

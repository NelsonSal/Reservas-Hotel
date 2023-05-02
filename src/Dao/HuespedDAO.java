package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;
import modelo.Reserva;
import conexion.*;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes (nombre,apellido,FechaNacimiento,nacionalidad,telefono,id_reserva)" + "VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(huesped, statement);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	private void ejecutaRegistro(Huesped huesped, PreparedStatement statement)
			throws SQLException {
		
		statement.setString(1, huesped.getNombre());  
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());
		
		statement.execute();
		
	}
	
	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		 final Connection con =factory.recuperaConexion();
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT Id_Huesped,Nombre,Apellido,FechaNacimiento,Nacionalidad,Telefono, Id_Reserva FROM huespedes");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				try (resultSet) {
					while (resultSet.next()) {
						resulsetGetData(resultado, resultSet);
					}
				}
			}
			con.close();
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void resulsetGetData(List<Huesped> resultado, final ResultSet resultSet) throws SQLException {
		Huesped fila = new Huesped(resultSet.getInt("Id_Huesped"),
				resultSet.getString("Nombre"),
				resultSet.getString("Apellido"),
				resultSet.getDate("FechaNacimiento"),
				resultSet.getString("Nacionalidad"),
				resultSet.getString("Telefono"),
				resultSet.getInt("Id_Reserva")
				);

		resultado.add(fila);
	}
	
	public List<Huesped> buscar(String apellido) {
		List<Huesped> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con =factory.recuperaConexion(); 
		
		try(con){
			var querySelector = "SELECT Id_Huesped,Nombre,Apellido,"
					+ " FechaNacimiento,Nacionalidad,Telefono,"
					+ " Id_Reserva FROM huespedes"
					+ " WHERE apellido=?";
			final PreparedStatement statement = con.prepareStatement(querySelector);
			
			
			try(statement){
				statement.setString(1, apellido);
				statement.execute();
			
				final ResultSet resultSet=statement.getResultSet();
				try(resultSet){
						while (resultSet.next()) {
						resulsetGetData(resultado, resultSet);
						
					}
				} 
			}
			con.close();
			return resultado;	
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> buscarPorIdReserva(Integer idReserva) {
		List<Huesped> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con =factory.recuperaConexion(); 
		
		try(con){
			var querySelector = "SELECT Id_Huesped,Nombre,Apellido,"
					+ " FechaNacimiento,Nacionalidad,Telefono,"
					+ " Id_Reserva FROM huespedes"
					+ " WHERE Id_Reserva=?";
			final PreparedStatement statement = con.prepareStatement(querySelector);
			
			
			try(statement){
				statement.setInt(1, idReserva);
				statement.execute();
			
				final ResultSet resultSet=statement.getResultSet();
				try(resultSet){
						while (resultSet.next()) {
						resulsetGetData(resultado, resultSet);
						
					}
				} 
			}
			con.close();
			return resultado;	
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono) {
		final Connection con= new ConnectionFactory().recuperaConexion();
		try(con){
			final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
		            + " Nombre = ?"
		            + ", Apellido = ?"
		            + ", FechaNacimiento = ?"
		            + ", Nacionalidad = ?"
		            + ", Telefono = ?"
		            + " WHERE Id_Huesped = ?");
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id);
				statement.execute();
				int updateCount = statement.getUpdateCount();
				con.close();
				return updateCount;
			}
		
		}catch (SQLException e) {
		throw new RuntimeException(e);
	
		}
	}

}

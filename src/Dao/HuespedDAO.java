package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Huesped;
import modelo.Reserva;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		try (con) {
			// con.setAutoCommit(false); //Tomamos el control manual del commit
			// (realización) d ela transacción, para que solo se haga
			// si no hay ningun error.

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes (nombre,apellido,FechaNacimiento,nacionalidad,telefono,id_reserva)" + "VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(huesped, statement);

				// con.commit(); // Se ejecuta la transacción si mo hay error
			}
		} catch (SQLException e) {
				//con.rollback();//Se reversa la transacción si error.
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
		
//		final ResultSet resultSet=statement.getGeneratedKeys(); //Try with resourses. Cierra el resulset solo. Autocloseable
//		try(resultSet){
//			while(resultSet.next()) {
//			//producto.setId(resultSet.getInt(1));
//				nuevaReserva.setIdReserva(resultSet.getInt(1));
//			System.out.println(String.format("Fue insertado el producto %s", nuevaReserva.getIdReserva()));
//		}
//		}
		
	}

}

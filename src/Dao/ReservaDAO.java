package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import modelo.Reserva;

public class ReservaDAO {
	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {

		try (con) {
			// con.setAutoCommit(false); //Tomamos el control manual del commit
			// (realización) d ela transacción, para que solo se haga
			// si no hay ningun error.

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (FechaEntrada,FechaSalida,valor,FormaPago)" + "VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(reserva, statement);

				// con.commit(); // Se ejecuta la transacción si mo hay error
			}
		} catch (SQLException e) {
//con.rollback();//Se reversa la transacción si error.
			throw new RuntimeException(e);
		}
	}
	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement)
			throws SQLException {
		
		statement.setDate(1, reserva.getFechaIn());  
		statement.setDate(2, reserva.getFechaOut());
		statement.setString(3, reserva.getValorReserva());
		statement.setString(4, reserva.getFormaDePago());
		
		statement.execute();
		
		final ResultSet resultSet=statement.getGeneratedKeys(); //Try with resourses. Cierra el resulset solo. Autocloseable
		try(resultSet){
			while(resultSet.next()) {
			//producto.setId(resultSet.getInt(1));
			reserva.setIdReserva(resultSet.getInt(1));
			System.out.println(String.format("Fue insertado el producto %s", reserva));
		}
		}
	}

}
//System.out.println("llego a guardar en DAO---> crear metodo enviar  a DB");
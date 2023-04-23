package pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import conexion.ConnectionFactory;
import modelo.FormaDePago;

public class PruebaLecturaFormPago {
	public static void main(String[] args) {
		
	
	
		//List<FormaDePago> resultado = new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con =factory.recuperaConexion(); 
		try(con){
			final PreparedStatement statement = con.prepareStatement("SELECT IDpago,NOMBRE FROM formasdepago");
			
			
			try(statement){
				statement.execute();
			
				final ResultSet resultSet=statement.getResultSet();
				try(resultSet){
						while (resultSet.next()) {
							
							
							FormaDePago fila = new FormaDePago(resultSet.getInt("IDpago"),
								resultSet.getString("NOMBRE"));
						System.out.println(fila);
						//resultado.add(fila);
					}
				} 
			}
				
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	


	}}

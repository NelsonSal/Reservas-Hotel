package Dao;

import java.sql.Connection;

import modelo.Reserva;

public class ReservaDAO {
	final private Connection con;
	public ReservaDAO(Connection con) {
		this.con=con;
	}
	public void guardar(Reserva reserva) {
		//TODO
		System.out.println("llego a guardar en DAO---> crear metodo enviar  a DB");
	}

}

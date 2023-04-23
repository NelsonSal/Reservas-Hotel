package modelo;

import java.sql.Date;

public class Reserva {
	private int idReserva;
	private Date fechaIn;
	private Date fechaOut;
	//private double valor;
	private String valorReserva;
	private String formaDePago;
	//private double dias;
	private double tarifa=500;
	
	
	
	
	public Reserva() {
	}

	public Reserva(Date fechaIn, Date fechaOut, String valorReserva, String formaDePago) {
		// TODO Auto-generated constructor stub
	}

//	public double cantDias (long fechaIn, long fechaOut) {
//		long diferencia = fechaOut - fechaIn;
//		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
//		return dias;
//	}
	
	public double valorReserva(long fechaIn, long fechaOut) {
		long diferencia = fechaOut - fechaIn;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		double valor=tarifa*dias;
		return valor;
	}

}
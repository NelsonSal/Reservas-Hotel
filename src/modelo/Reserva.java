package modelo;

import java.sql.Date;

public class Reserva {
	private int idReserva;
	private Date fechaIn;
	private Date fechaOut;
	private double valor;
	private String formaDePago;
	private double dias;
	private double tarifa=500;
	
	public double cantDias (long fechaIn, long fechaOut) {
		long diferencia = fechaOut - fechaIn;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return dias;
	}
	
	public double valorReserva(long fechaIn, long fechaOut) {
		long diferencia = fechaOut - fechaIn;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		valor=tarifa*dias;
		return valor;
	}

}
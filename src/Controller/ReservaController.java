package Controller;

import modelo.Reserva;

public class ReservaController {
	private Reserva reserva;
	
	//Reserva reserva = new Reserva();
	
	
	public double calcularValor(long fechaIn,long fechaOut) {
		this.reserva = new Reserva();
		return reserva.valorReserva(fechaIn, fechaOut);  
	}

}

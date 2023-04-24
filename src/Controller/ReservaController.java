package Controller;



import Dao.ReservaDAO;
import conexion.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	private Reserva reserva;
	private ReservaDAO reservaDao;
	
	//Reserva reserva = new Reserva();
	public ReservaController() {
		this.reservaDao = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public double calcularValor(long fechaIn,long fechaOut) {
		this.reserva = new Reserva();
		return reserva.valorReserva(fechaIn, fechaOut);  
	}

	public int guardarReserva(Reserva nuevaReserva) {
		// TODO Auto-generated method stub
		System.out.println("llego a guardarReserva en Controller---> crear DAO");
		return reservaDao.guardar(nuevaReserva);
		
		
	}

}

package Controller;



import java.sql.Date;
import java.util.List;



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

	public void guardarReserva(Reserva nuevaReserva) {
		reservaDao.guardar(nuevaReserva);
		
		
	}
	public List<Reserva> listar() {
		return reservaDao.listar();
		
	}
	public List<Reserva> buscarReserva(Integer Id_Reserva){
		return reservaDao.buscarReserva(Id_Reserva);
	}

	public int modificar(Integer Id_Reserva, Date fechaIn, Date fechaOut, String valor, String formaDePago) {
		// TODO Auto-generated method stub
		return reservaDao.modificarReserva(Id_Reserva,fechaIn,fechaOut,valor,formaDePago);
	}

}

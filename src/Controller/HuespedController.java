package Controller;



import java.sql.Date;
import java.util.List;

import Dao.HuespedDAO;
import conexion.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {
	//private Huesped huesped;
	private HuespedDAO huespedDao;
	
	//Huesped huesped = new Huesped();
	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	
	public void guardarHuesped(Huesped huesped) {
		huespedDao.guardar(huesped);
	}


	public List<Huesped> listar() {
		return huespedDao.listar();
	}
	
	public List<Huesped> buscarHuesped(String apellido){
		return huespedDao.buscar(apellido);
	}
	
	public List<Huesped> buscarHuespedPorReserva(Integer idReserva){
		return huespedDao.buscarPorIdReserva(idReserva);
	}


	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono) {
		return huespedDao.modificar(id,nombre,apellido,fechaNacimiento,nacionalidad,telefono);
	}
}

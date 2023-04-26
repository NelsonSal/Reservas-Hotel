package Controller;



import Dao.HuespedDAO;
import conexion.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {
	private Huesped huesped;
	private HuespedDAO huespedDao;
	
	//Huesped huesped = new Huesped();
	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	
	public void guardarHuesped(Huesped huesped) {
		System.out.println("name: "+huesped.getNombre());
		// TODO Auto-generated method stub
		System.out.println("llego a guardarHuesped en Controller---> crear DAO");
		huespedDao.guardar(huesped);
		
		
	}

}

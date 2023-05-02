package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.HuespedController;
import Controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservaController reservaController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		cargarTablaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtBuscar.getText().isBlank()) {
					limpiarTabla();
					tbReservas.clearSelection();
					tbHuespedes.clearSelection();
					cargarTablaHuespedes();
					cargarTablaReservas();

				} else {
					try {
						int id = Integer.parseInt(txtBuscar.getText());
						limpiarTabla();
						cargarTablaBuscaReserva(id);
						cargarHuespedPorReserva(id);

					} catch (NumberFormatException nfe) {
						limpiarTabla();
						cargarTablaBuscaHuespedes();

					}
				}

			}
		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtBuscar.getText().isBlank()) {
					modificar();
					limpiarTabla();
					cargarTablaReservas();
					cargarTablaHuespedes();
				} else {
					try {
						int id = Integer.parseInt(txtBuscar.getText());
						modificar();
						limpiarTabla();
						cargarTablaBuscaReserva(id);
						cargarHuespedPorReserva(id);

					} catch (NumberFormatException nfe) {
						modificar();

					}
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();

			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void cargarTablaReservas() {

		var reservas = this.reservaController.listar();
		llenarTablaReservas(reservas);

	}

	private void cargarTablaHuespedes() {

		var huespedes = this.huespedController.listar();
		llenarTablaHuespedes(huespedes);

	}

	private void limpiarTabla() {
		modelo.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
	}

	private void cargarTablaBuscaHuespedes() {

		var huespedes = this.huespedController.buscarHuesped(txtBuscar.getText());
		llenarTablaHuespedes(huespedes);

		huespedes.forEach(huesped -> cargarTablaBuscaReserva(huesped.getIdReserva()));

	}

	private void cargarHuespedPorReserva(Integer id) {

		var huespedes = this.huespedController.buscarHuespedPorReserva(id);
		llenarTablaHuespedes(huespedes);
	}

	private void cargarTablaBuscaReserva(Integer id) {
		var reservas = this.reservaController.buscarReserva(id);
		llenarTablaReservas(reservas);
	}

	private void llenarTablaReservas(List<Reserva> reservas) {
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getIdReserva(), reserva.getFechaIn(),
				reserva.getFechaOut(), reserva.getValorReserva(), reserva.getFormaDePago() }));
	}

	private void llenarTablaHuespedes(List<Huesped> huespedes) {
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getIdHuesped(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getIdReserva() }));
	}

	private boolean tieneFilaElegidaReservas() {

		boolean elijeReserva = tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;

		return elijeReserva;
	}

	private boolean tieneFilaElegidaHuespedes() {

		boolean elijeHuesped = tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;

		return elijeHuesped;
	}

	private void modificar() {
		if (tieneFilaElegidaReservas() && tieneFilaElegidaHuespedes()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item para Modificar");
			return;
		}
		if (tieneFilaElegidaReservas() == false) {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());// mia
						String fechaIn = String.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1));
						String fechaOut = String.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2));
						String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
						String formaDePago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
						int updateCount;

						updateCount = this.reservaController.modificar(id, java.sql.Date.valueOf(fechaIn),
								java.sql.Date.valueOf(fechaOut), valor, formaDePago);
						tbReservas.clearSelection();
						JOptionPane.showMessageDialog(this, updateCount + " Item modificado con éxito!");

					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item Reserva2"));

		} else if (tieneFilaElegidaHuespedes() == false) {
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());// mia
						String nombre = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1));
						String apellido = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2));
						String fechaNacimiento = String
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3));
						String nacionalidad = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4));
						String telefono = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5));
						int updateCount;

						updateCount = this.huespedController.modificar(id, nombre, apellido,
								java.sql.Date.valueOf(fechaNacimiento), nacionalidad, telefono);

						tbReservas.clearSelection();
						tbHuespedes.clearSelection();
						JOptionPane.showMessageDialog(this, updateCount + " Item modificado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item Huesped2"));
		}

	}

	private void eliminar() {
		if (tieneFilaElegidaReservas() && tieneFilaElegidaHuespedes()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item ");
			return;
		} else if (tieneFilaElegidaReservas() == false) {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						int cantidadEliminada;
						cantidadEliminada = this.reservaController.eliminar(id);
						tbReservas.clearSelection();
						tbHuespedes.clearSelection();
						limpiarTabla();
						cargarTablaHuespedes();
						cargarTablaReservas();

						JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		} else if (tieneFilaElegidaHuespedes() == false) {
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
						int cantidadEliminada;
						cantidadEliminada = this.reservaController.eliminar(id);
						tbReservas.clearSelection();
						tbHuespedes.clearSelection();
						limpiarTabla();

						cargarTablaReservas();
						cargarTablaHuespedes();

						JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}
}

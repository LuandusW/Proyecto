import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jxl.format.Colour;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Ranking extends JFrame{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	public Properties prop=null;
	public FileInputStream is = null;
	public Statement st=null;
	public PreparedStatement stmt=null;
	public ResultSet rs=null;
	public String sql=null;
	public Statement statement=null;
	public Connection conn=null;
	private JTextField txtNombre1;
	private JTextField txtPuntuacion1;
	private JTextField txtNombre2;
	private JTextField txtPuntuacion2;
	private JTextField txtPuntuacion3;
	private JTextField txtNombre3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking frame = new Ranking();
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
	public Ranking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 7, 9);
		contentPane.add(scrollPane);
		
		table = new JTable();
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		table.setBackground(null);
		table.setGridColor(Color.black);
		scrollPane.setVisible(false);
		conectar();
		cargarCabecera();
		cargarDatos();
		int puntuacion1=(int) modelo.getValueAt(0, 1);
		String p1=Integer.toString(puntuacion1);
		int puntuacion2=(int) modelo.getValueAt(1, 1);
		String p2=Integer.toString(puntuacion2);
		int puntuacion3=(int) modelo.getValueAt(2, 1);
		String p3=Integer.toString(puntuacion3);
		
		txtNombre1 = new JTextField();
		txtNombre1.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre1.setBounds(271, 315, 86, 20);
		contentPane.add(txtNombre1);
		txtNombre1.setColumns(10);
		txtNombre1.setBackground(null);
		txtNombre1.setBorder(null);
		txtNombre1.setText((String) modelo.getValueAt(0, 0));
		
		txtPuntuacion1 = new JTextField();
		txtPuntuacion1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntuacion1.setColumns(10);
		txtPuntuacion1.setBackground(null);
		txtPuntuacion1.setBorder(null);
		txtPuntuacion1.setBounds(271, 335, 86, 20);
		contentPane.add(txtPuntuacion1);
		txtPuntuacion1.setText(p1);
		
		txtNombre2 = new JTextField();
		txtNombre2.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre2.setColumns(10);
		txtNombre2.setBackground(null);
		txtNombre2.setBorder(null);
		txtNombre2.setBounds(186, 315, 86, 20);
		contentPane.add(txtNombre2);
		txtNombre2.setText((String) modelo.getValueAt(1, 0));
		
		txtPuntuacion2 = new JTextField();
		txtPuntuacion2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntuacion2.setColumns(10);
		txtPuntuacion2.setBackground(null);
		txtPuntuacion2.setBorder(null);
		txtPuntuacion2.setBounds(186, 335, 86, 20);
		contentPane.add(txtPuntuacion2);
		txtPuntuacion2.setText(p2);
		
		txtNombre3 = new JTextField();
		txtNombre3.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre3.setColumns(10);
		txtNombre3.setBackground(null);
		txtNombre3.setBorder(null);
		txtNombre3.setBounds(355, 315, 86, 20);
		contentPane.add(txtNombre3);
		txtNombre3.setText((String) modelo.getValueAt(2, 0));
		
		txtPuntuacion3 = new JTextField();
		txtPuntuacion3.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntuacion3.setColumns(10);
		txtPuntuacion3.setBackground(null);
		txtPuntuacion3.setBorder(null);
		txtPuntuacion3.setBounds(355, 335, 86, 20);
		contentPane.add(txtPuntuacion3);
		txtPuntuacion3.setText(p3);
		
	}
	public void conectar() {
		try {
			prop=new Properties();
			is = new FileInputStream("src/main/resources/bd.properties");
			prop.load(is);
			String user = prop.getProperty("user","");
			String password = prop.getProperty("password","");
			String url = prop.getProperty("url","");
			String driver = prop.getProperty("driver","");
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("== Conexion establecida ==");
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public void cargarCabecera() {
		try {
			Statement s = conn.createStatement();
			rs=s.executeQuery("SELECT nom_usuario, puntuacion FROM USUARIOS");
			ResultSetMetaData metaDatos = rs.getMetaData();
			int numeroColumnas = metaDatos.getColumnCount();
			Object[] etiquetas= new Object[numeroColumnas];
			for (int i = 0; i < numeroColumnas; i++) {
				etiquetas[i]=metaDatos.getColumnLabel(i+1);
				modelo.addColumn(metaDatos.getColumnLabel(i + 1));
			}
			System.out.println("Columnas cargadas");

		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public void cargarDatos() {
		try {
			Statement s = conn.createStatement();
			rs=s.executeQuery("SELECT nom_usuario, puntuacion FROM USUARIOS ORDER BY puntuacion DESC LIMIT 3");
			ResultSetMetaData metaDatos = rs.getMetaData();
			while (rs.next()) {
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] fila = new Object[numeroColumnas];
				for (int i = 0; i < numeroColumnas; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				modelo.addRow(fila);
			}
			System.out.println("Datos cargados");
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./contenido/trofeos.png"));
			g.drawImage(image, 10, 11, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

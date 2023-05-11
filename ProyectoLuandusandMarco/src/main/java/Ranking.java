import java.awt.EventQueue;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Ranking extends JFrame {

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
	private JTable table_1;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 250, 414, -240);
		contentPane.add(scrollPane);
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		conectar();
		
		cargarCabecera();
		
		cargarDatos();
		
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
			rs=s.executeQuery("SELECT * FROM PRO");
			ResultSetMetaData metaDatos = rs.getMetaData();
			// Se obtiene el numero de columnas.
			int numeroColumnas = metaDatos.getColumnCount();
			// Se obtienen las etiquetas para cada columna
			Object[] etiquetas= new Object[numeroColumnas];
			for (int i = 0; i < numeroColumnas; i++) {
				etiquetas[i]=metaDatos.getColumnLabel(i+1);
				modelo.addColumn(metaDatos.getColumnLabel(i + 1));
			}

		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public void cargarDatos() {
		try {
			Statement s = conn.createStatement();
			rs=s.executeQuery("SELECT * FROM PRO");
			ResultSetMetaData metaDatos = rs.getMetaData();
			while (rs.next()) {
				// Se obtiene el numero de columnas.
				int numeroColumnas = metaDatos.getColumnCount();
				Object[] fila = new Object[numeroColumnas];
				for (int i = 0; i < numeroColumnas; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				modelo.addRow(fila);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}

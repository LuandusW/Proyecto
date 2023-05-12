import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
import java.awt.event.ActionEvent;

public class Interface extends JFrame {

	private JPanel contentPane;
	public Properties prop = null;
	public FileInputStream is = null;
	public Statement st = null;
	public PreparedStatement stmt = null;
	public ResultSet rs = null;
	public String sql = null;
	public Statement statement = null;
	public Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom_usuario = JOptionPane.showInputDialog("Nombre Usuario:");
				insertarDatos(nom_usuario);
				Dinosaurio dino = new Dinosaurio();
				dino.setVisible(true);
			}
		});
		btnJugar.setBounds(168, 132, 125, 32);
		contentPane.add(btnJugar);

		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ranking r = new Ranking();
				r.setVisible(true);
			}
		});
		btnRanking.setBounds(168, 195, 125, 32);
		contentPane.add(btnRanking);
		conectar();
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

	public void insertarDatos(String nom_usuario) {
		try {
			PreparedStatement statement=conn.prepareStatement("INSERT INTO USUARIOS(nom_usuario) VALUES (?)");
			statement.setString(1,nom_usuario);
			int retorno=statement.executeUpdate();
			if (retorno>0) {
				System.out.println("Insertado correctamente");
			}
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}

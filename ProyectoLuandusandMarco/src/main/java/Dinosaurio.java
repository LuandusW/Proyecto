import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Dinosaurio extends JFrame {

	private GraficoDinosaurio graficoDinosaurio;
	public Properties prop=null;
	public FileInputStream is = null;
	public Statement st=null;
	public PreparedStatement stmt=null;
	public ResultSet rs=null;
	public String sql=null;
	public Statement statement=null;
	public Connection conn=null;

	public Dinosaurio() {
		super("Dinosaurio");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graficoDinosaurio = new GraficoDinosaurio();
		add(graficoDinosaurio);
		addKeyListener(graficoDinosaurio);
	}
	
	public void empiezaJuego() {
		graficoDinosaurio.empiezaJuego();
	}

		public static void main(String[] args) {
			Dinosaurio dn = new Dinosaurio();
			dn.setVisible(true);
			dn.empiezaJuego();
			}

	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./contenido/cactus1.png"));
			g.drawImage(image, 100, 100, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void insertarDatos(int puntuacion) {
		try {
			PreparedStatement statement=conn.prepareStatement("INSERT INTO USUARIOS(nom_usuario) VALUES (?)");
			statement.setInt(2,puntuacion);
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

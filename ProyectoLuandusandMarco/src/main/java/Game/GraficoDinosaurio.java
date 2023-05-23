package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JPanel;

import ObjetosDinosaurio.Cactus;
import ObjetosDinosaurio.DieDino;
import ObjetosDinosaurio.Dino;
import ObjetosDinosaurio.Isla;
import ObjetosDinosaurio.Nubes;
import Util.Resource;

public class GraficoDinosaurio extends JPanel implements Runnable, KeyListener {
	public static final int JUEGO_STATUS = 0;
	public static final int JUEGO_EMPIEZA = 1;
	public static final int JUEGO_ACABA = 2;
	private static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 110;

	private Thread thread;
	private Dino dino;
	private Isla isla;
	private Nubes nubes;
	private DieDino diedino;
	private int puntos;
	private Dinosaurio dn;
	private int statusGame = JUEGO_STATUS;
	private boolean saltar = true;

	private BufferedImage imgGameOver;
	private BufferedImage imgReplay;
	
	public Properties prop=null;
	public FileInputStream is = null;
	public Statement st=null;
	public PreparedStatement stmt=null;
	public ResultSet rs=null;
	public String sql=null;
	public Statement statement=null;
	public Connection conn=null;

	public GraficoDinosaurio() {
		thread = new Thread(this);
		dino = new Dino();
		// posicion que inicia dinosaurio
		dino.setX(50);
		dino.setY(60);
		isla = new Isla(this);
		nubes = new Nubes();
		diedino = new DieDino(dino, this);
		imgGameOver = Resource.getResourceImage("./contenido/gameover_text.png");
		imgReplay = Resource.getResourceImage("./contenido/replay_button.png");
		conectar();
	}

	public void empiezaJuego() {
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				actualizar();
				repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dn.log.debug(e);
			}
		}
	}

	public void actualizar() {
		switch (statusGame) {
		case JUEGO_EMPIEZA:
			dino.actualizar();
			isla.actualizar();
			nubes.actualizar();
			diedino.actualizar();
			if (!dino.getVida()) {
				statusGame = JUEGO_ACABA;
			}
			break;

		}

	}

	public void plusPuntos(int puntos) {
		this.puntos += puntos;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());

		switch (statusGame) {
		case JUEGO_STATUS:
			dino.draw(g);
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			g.drawString("Puntos: " + String.valueOf(puntos), 500, 20);
			break;
		case JUEGO_EMPIEZA:
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			g.drawString("Puntos: " + String.valueOf(puntos), 500, 20);
			insertarPuntuacion(puntos);
			break;
		case JUEGO_ACABA:
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			if (statusGame == JUEGO_ACABA) {
				g.drawImage(imgGameOver, 180, 40, null);
				g.drawImage(imgReplay, 260, 60, null);
			}
			break;

		}

	}

	private void restartGame() {
		dino.setVida(true);
		dino.setX(50);
		dino.setY(60);
		diedino.reset();
	}

	public void noPuedeSaltar() {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if (dino.getY() == 67) {
				saltar = false;
			}
			if (statusGame == JUEGO_STATUS) {
				statusGame = JUEGO_EMPIEZA;
			} else if (statusGame == JUEGO_EMPIEZA) {
				if (dino.getY() < 67) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						saltar = false;
					}
				}
				if (dino.getY() == 67 ) {
					saltar = true;
					dino.jump();
				}
			} else if (statusGame == JUEGO_ACABA) {
				restartGame();
				statusGame = JUEGO_EMPIEZA;
				puntos = 0;
			}
			break;
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
	public void insertarPuntuacion(int puntuacion) {
		try {
			Statement st = conn.createStatement();
			String sql="SELECT nom_usuario FROM USUARIOS ORDER BY id DESC LIMIT 1";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String nom_usuario=rs.getString(1);
				PreparedStatement statement=conn.prepareStatement("UPDATE USUARIOS SET nom_usuario=?, puntuacion=? WHERE nom_usuario='"+nom_usuario+"'");
				statement.setString(1,nom_usuario);
				statement.setInt(2,puntuacion);
				int retorno=statement.executeUpdate();
				if (retorno>0) {
				}
			}
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

}

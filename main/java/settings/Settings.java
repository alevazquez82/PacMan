
package settings;

public class Settings {
	
	public final int FPS = 60;
	public final int NUMERO_FANTASMAS = 4;

// iniSprites {x, y, id, direccion (0 dcha, 1 izda, 2 up 3 do)}
	private int[][] iniSprites = { { 9, 4, 0, 0 }, { 2, 8, 0, 0 }, { 5, 8, 1, 0 }, { 12, 8, 2, 1 }, { 15, 8, 3, 1 } };

	private Boolean reinstanciarPacmanFantasmas = true;
	
	private int[] duracionFantasmasAzulesNivel = {7500, 7500, 6500, 6000, 5000, 4500, 4000, 3500, 3000};
	
	private int duracionPacManDying = 3000;

	//private int[] colorFondos = { 143, 127, 9, 70, 62, 4 };
	private int[] colorFondos = { 143, 127, 9, 72, 72, 72 };

	private int puntos = 0;
	private int nivel = 1;
	private int hiScore = 2000;
	
	public final int SUMAR_PTOS_COME_PUNTITO = 10;
	
	// ------------------------------------------------
	// Funcion Constructora
	// ------------------------------------------------
	private Settings() {}
	// SINGLETON
	private static Settings INSTANCIA = new Settings();
	public static Settings getInstancia() {return INSTANCIA;}

	// --------------------------------------------------
	// Instancias Controles, Estado, UrlAudio, Laberinto
	// --------------------------------------------------
	public Controles controles = new Controles();
	public Estado estado = new Estado();
	public UrlAudio urlaudio = new UrlAudio();
	public Laberinto laberinto = new Laberinto();
	
	// ====================================================================
	public class Controles {

		private Boolean izquierda = false;
		private Boolean derecha = false;
		private Boolean arriba = false;
		private Boolean abajo = false;

		public Controles() {}

		// Getters & Setters
		public Boolean isIzquierda() {
			return izquierda;
		}

		public void setIzquierda(Boolean izquierda) {
			this.izquierda = izquierda;
		}

		public Boolean isDerecha() {
			return derecha;
		}

		public void setDerecha(Boolean derecha) {
			this.derecha = derecha;
		}

		public Boolean isArriba() {
			return arriba;
		}

		public void setArriba(Boolean arriba) {
			this.arriba = arriba;
		}

		public Boolean isAbajo() {
			return abajo;
		}

		public void setAbajo(Boolean abajo) {
			this.abajo = abajo;
		}
	}

	// ====================================================================
	public class Estado {

		private Boolean preJuego = true;
		private Boolean preparado = false;
		private Boolean enJuego = false;
		private Boolean pacmanDying = false;
		private Boolean nivelSuperado = false;
		private Boolean gameOver = false;

		public Estado() {}

		// Getters & Setters
	}
	
	// ====================================================================
	public class UrlAudio {
		
		private String ruta = "media/";
		
		private String intermision = "pacmanintermision.wav";
		private String preparado = "pacmaninicionivel.wav";
		private String wakawaka = "pacmanwakawaka.wav";
		private String gameover = "gameoveretro.wav";
		private String pitidokey = "key.wav";
		private String numkey = "numkey.wav";
		private String azules = "pacmanazules.wav";
		private String pacmandies = "pacmandies.wav";
		private String eatingGhost = "pacmaneatinghost.wav";
		private String eatingcherry = "pacmaneatingcherry.wav";
		
		public UrlAudio() {
		}
		
		public String getIntermision() {
			return ruta + intermision;
		}

		public String getPreparado() {
			return ruta + preparado;
		}
		
		public String getWakawaka() {
			return ruta + wakawaka;
		}

		public String getGameover() {
			return ruta + gameover;
		}
		
		public String getPitidokey() {
			return ruta + pitidokey;
		}
		
		public String getNumkey() {
			return ruta + numkey;
		}
		
		public String getAzules() {
			return ruta + azules;
		}
		
		public String getPacmandies() {
			return ruta + pacmandies;
		}
		
		public String getEatingGhost() {
			return ruta + eatingGhost;
		}
		
		public String getEatingcherry() {
			return ruta + eatingcherry;
		}
	}
	
	// ====================================================================
	public class Laberinto {
		
		public final int PARED = 9;
		public final int PUNTITO = 1;
		public final int PUNTITO_GORDO = 5;
		public final int VACIO = 0;
		
		public final int TILE_X = 50;// 50x50px
		public final int TILE_Y = 50;
		
		public int FILAS;
		public int COLUMNAS;
		
		public int RES_X;
		public int RES_Y;
		
		private int contadorPuntitos = 0;
		private int contadorPuntitosGordos = 0;

		// columnas 19 x 15 filas
		public int[][] matriz = {
				{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
				{ 9, 5, 1, 1, 1, 1, 1, 1, 1, 9, 1, 1, 1, 1, 1, 1, 1, 5, 9 },
				{ 9, 1, 9, 9, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 9, 9, 1, 9 },

				{ 9, 1, 9, 9, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 9, 9, 1, 9 },
				{ 9, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 9 },
				{ 9, 1, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 1, 9 },

				{ 9, 1, 1, 1, 1, 9, 1, 1, 1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 9 },
				{ 9, 9, 9, 9, 1, 9, 9, 9, 1, 9, 1, 9, 9, 9, 1, 9, 9, 9, 9 },
				{ 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9 },

				{ 9, 5, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 5, 9 },
				{ 9, 1, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 1, 9 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },

				{ 9, 1, 9, 9, 1, 9, 9, 9, 9, 1, 9, 9, 9, 9, 1, 9, 9, 1, 9 },
				{ 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9 },
				{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 } 
			};

		public Laberinto() {
			
			this.FILAS = matriz.length;
			this.COLUMNAS = matriz[0].length;
			
			System.out.println(this.FILAS);
			System.out.println(this.COLUMNAS);
			
			this.RES_X = (int) (this.COLUMNAS * TILE_X);
			this.RES_Y = (int) (this.FILAS * TILE_Y);
		}

		public int getContadorPuntitos() {
			return contadorPuntitos;
		}

		public void setContadorPuntitos(int contadorPuntitos) {
			this.contadorPuntitos = contadorPuntitos;
		}

		public int getContadorPuntitosGordos() {
			return contadorPuntitosGordos;
		}

		public void setContadorPuntitosGordos(int contadorPuntitosGordos) {
			this.contadorPuntitosGordos = contadorPuntitosGordos;
		}
	}

	// Getters & Setters ( *** settings *** )
	public int[][] getIniSprites() {
		return this.iniSprites;
	}

	public void setIniSprites(int[][] iniSprites) {
		this.iniSprites = iniSprites;
	}

	public Boolean getReinstanciar_pacmanFantasmas() {
		return this.reinstanciarPacmanFantasmas;
	}

	public void setReinstanciar_pacmanFantasmas(Boolean reinstanciar_pacmanFantasmas) {
		this.reinstanciarPacmanFantasmas = reinstanciar_pacmanFantasmas;
	}

	public int[] getColorFondos() {
		return this.colorFondos;
	}
	
	public int getPuntos() {
		return this.puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getHiScore() {
		return this.hiScore;
	}

	public void setHiScore(int hiScore) {
		this.hiScore = hiScore;
	}
	
	public int[] getDuracionFantasmasAzulesNivel() {
		return duracionFantasmasAzulesNivel;
	}

	public void setDuracionFantasmasAzulesNivel(int[] duracionFantasmasAzulesNivel) {
		this.duracionFantasmasAzulesNivel = duracionFantasmasAzulesNivel;
	}

	public int getDuracionPacManDying() {
		return duracionPacManDying;
	}

	public void setDuracionPacManDying(int duracionPacManDying) {
		this.duracionPacManDying = duracionPacManDying;
	}
}

package principal;

import java.util.ArrayList;
import settings.Settings;
import sprites.Fantasma;
import sprites.PacMan;
import sprites.Pared;
import sprites.Puntitos;

public class Instancias {
    
    private int filas;
    private int columnas;
    private int ancho;
    private int alto;
    
    private Settings settings;
    private ArrayList<Pared> pared = new ArrayList<>();
    private ArrayList<Puntitos> puntitos = new ArrayList<>();
    private PacMan pacman;
    private Fantasma[] fantasma;
    
    public Instancias(Settings settings){
        
        this.settings = settings;
        
        this.filas = settings.laberinto.FILAS;
        this.columnas = settings.laberinto.COLUMNAS;
        
        this.ancho = settings.laberinto.TILE_X;
        this.alto = settings.laberinto.TILE_Y;
        
    }
    
    public ArrayList<Pared> instanciarParedesLaberinto(){
        
        for(int i = 0; i < this.filas; i++){
            for(int ii = 0; ii < this.columnas; ii++){
                
                int tile = settings.laberinto.matriz[i][ii];
                
                //9 = pared, 1 = punto chico
                if (tile == 9){
                    pared.add(new Pared(ii, i, ancho, alto));
                }
                
            }
        }
        
        return pared;
    }
    
    public ArrayList<Puntitos> instanciarPuntitosLaberinto(){
        
        for(int i = 0; i < this.filas; i++){
            for(int ii = 0; ii < this.columnas; ii++){
                
                int tile = settings.laberinto.matriz[i][ii];
                
                //1 = puntito, 5 = punto gordo
                if (tile == 1){
                    puntitos.add(new Puntitos(ii, i, ancho, alto, false));
                    int acum = settings.laberinto.getContadorPuntitos();
                    settings.laberinto.setContadorPuntitos(acum + 1);
                
                }else if (tile == 5){
                    puntitos.add(new Puntitos(ii, i, ancho, alto, true));
                    int acum = settings.laberinto.getContadorPuntitosGordos();
                    settings.laberinto.setContadorPuntitosGordos(acum + 1);
                }
                
            }
        }
        
        return puntitos;
    }
    
    public PacMan instanciarPacMan(){
        
        int [][] args = settings.getIniSprites();
        
        pacman = new PacMan(args[0][0], args[0][1],
                 settings.laberinto.TILE_X, settings.laberinto.TILE_Y, args[0][3]);
        
        return pacman;
        
    }
    
    public Fantasma[] instanciarFantasmas(Ventana ventana) {
		
		fantasma = new Fantasma[settings.NUMERO_FANTASMAS];
		
		for (int i = 0; i < settings.NUMERO_FANTASMAS; i ++) {
			
			int x = settings.getIniSprites()[i + 1][0];
			int y = settings.getIniSprites()[i + 1][1];
			int id = settings.getIniSprites()[i + 1][2];
			int direcc = settings.getIniSprites()[i + 1][3];
			
			fantasma[i] = new Fantasma(x, y, settings.laberinto.TILE_X, settings.laberinto.TILE_Y,
					id, direcc, ventana);
		}
		
		return fantasma;
	}
}

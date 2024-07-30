package sprites;

import interfaces.IChooseRndDirection;
import interfaces.ISpritesMethods;
import java.awt.Color;
import java.awt.Graphics;
import principal.Ventana;
import settings.Settings;

public class Fantasma implements ISpritesMethods, IChooseRndDirection{

    private static Boolean estanAzules = false;
    private Boolean estaComido = false;
    private Boolean colorIntermitente = false;

    // {direccionX, direccionY, direccion}
    // ( 1,0 = ri | -1,0 = le | 0,-1 up | 0,1 = do )
    private int[][] direcciones = {{1, 0, 0}, {-1, 0, 1}, {0, -1, 2}, {0, 1, 3},};

    // Posibles random direcciones (excluimos la direccion actual)
    private int[][] otraDireccionRND = {{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2},};

    // Puntos donde los fantasmas pueden cambiar de direccion
    private static int[][] ptosClave = {{4, 1}, {14, 1}, {4, 4}, {6, 4}, {12, 4}, {14, 4}, {4, 8},
        {6, 8}, {12, 8}, {14, 8}, {1, 11}, {4, 11}, {6, 11}, {12, 11}, {14, 11}, {17, 11},
        {4, 13}, {9, 13}, {14, 13}};
    
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private int id;
    private int dirPorDefecto = 0;
    private int direccion;
    private int[] velXY = {0, 0};
    private int vel;
    private Boolean avanzar;
    
    private Ventana ventana;
    private int[] coorPacMan;

    public Fantasma(int x, int y, int tileX, int tileY, int id, int dirPorDefecto, Ventana ventana) {
        super();
        this.tileX = tileX;
        this.tileY = tileY;
        this.x = x * this.tileX;
        this.y = y * this.tileY;
        
        this.dirPorDefecto = dirPorDefecto;
        this.velXY[0] = direcciones[this.dirPorDefecto][0];
        this.velXY[1] = direcciones[this.dirPorDefecto][1];
        this.direccion = this.dirPorDefecto;
        this.id = id;
        
        this.vel = (int)(this.tileY / DIVIDIR_TILE_ENTRE);
        this.avanzar = true;
        this.ventana = ventana;    
    }
    

    @Override
    public void dibuja(Graphics g, int[][] matriz, Settings sett) {
        // Color RGB --> Fantasma0, Fantasma1...
        int[][]  rgb = {{9, 165, 205}, {225, 25, 9}, {245, 105, 135}, {55, 225, 9}};
    
        // Color 'estanAzules'
        int[] rgbAzules = {9, 75, 205};
        
       // coorPacMan = getPacManPosition();
       actualiza(matriz, sett);
       
       if (!estanAzules){
           g.setColor(new Color(rgb[this.id][0], rgb[this.id][1], rgb[this.id][2]));
       
       }else{
           g.setColor(new Color(rgbAzules[0], rgbAzules[1], rgbAzules[2]));
          // if(checkIntermitentes(sett))colorIntermitente(g);
        }
       
       if (!estaComido){
           
           g.fillArc(this.x + 1, this.y, this.tileX - 2, this.tileY, 0, 360);
           g.fillRect(this.x +1, this.y +(int)(this.tileY / 2), this.tileX - 2, 
                   (int) (this.tileY / 2) -1);
           drawSoloOjos(g);
       }else{
           drawSoloOjos(g);
       }
       
       //drawPtosClaves(g);
    }
    

private void drawSoloOjos(Graphics g) {

		int radioOjo = 17;
		int radioPupila = 8;

		int ojoX = (int) (this.x + 3);
		int ojoY = (int) (this.tileY / 4);
		int centroX = (int) (this.tileX / 2);

		// mover Pupilas para que miren hacia la direccion en la que van...
		int cuantoMover = 5;
		int vX = this.velXY[0] * cuantoMover;
		int vY = this.velXY[1] * cuantoMover;

		// Ojos (color blanco)
		g.setColor(new Color(241, 241, 241));
		g.fillArc(ojoX, this.y + ojoY, radioOjo, radioOjo, 0, 360);
		g.fillArc(ojoX + centroX, this.y + ojoY, radioOjo, radioOjo, 0, 360);

		// Pupilas (color negro)
		int pupilaX = ojoX + (int) (radioOjo / 2) - (int) (radioPupila / 2);
		int pupilaY = this.y + ojoY + (int) (radioOjo / 2) - (int) (radioPupila / 2);

		g.setColor(new Color(9, 9, 9));
		g.fillArc(pupilaX + vX, pupilaY + vY, radioPupila, radioPupila, 0, 360);
		g.fillArc(pupilaX + centroX + vX, pupilaY + vY, radioPupila, radioPupila, 0, 360);
	}

    @Override
    public void actualiza(int[][] matriz, Settings sett) {
        
        int perseguir = 999;
        
        if (this.x % this.tileX == 00 && this.y % this.tileY == 0 ){
            
            int x = (int) ((this.x / this.tileX));
            int y = (int) ((this.y / this.tileY));
        
            Boolean colisionVelXY = checkColisionLaberintoVelXY(x, y, velXY, matriz, sett);
        
            if (!colisionVelXY){
                
                this.avanzar =true;
                
            }else {
                
                this.direccion = elegirOtraDireccionRND(otraDireccionRND, this.direccion);
                cambiarVelXY();
            }
        }
        
        if (this.avanzar){
            
            this.x += this.velXY[0] * this.vel;
            this.y += this.velXY[1] * this.vel;
        }
    }
    
    private void cambiarVelXY(){
        
        this.velXY[0] = direcciones[this.direccion][0];
        this.velXY[1] = direcciones[this.direccion][1];
        this.avanzar = false;
    }

//Getters & Setters
    public Boolean getEstaComido() {
        return estaComido;
    }

    public void setEstaComido(Boolean estaComido) {
        this.estaComido = estaComido;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }
    
    

}

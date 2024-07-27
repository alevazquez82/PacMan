
package sprites;

import interfaces.ISpritesMethods;
import java.awt.Color;
import java.awt.Graphics;
import settings.Settings;


public class PacMan implements ISpritesMethods{

    public static final int NRO_ANIMAS_BOCA = 10;
    
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private int dirPorDefecto;
    private int pulsada;
    private int pulsadaConfirmada;
    
    // {direccionX, direccionY, max-Boca, maxBoca, minBoca, minBoca}
    //(direcciones 0=derecha, 1=izquierda, 2=arriba, 3=abajo)
    private int [][] direcciones = {
        {1, 0, 80, 190, 45, 270},
        {-1, 0, 260, 190, 225, 270},
        {0, -1, 170, 190, 135, 270},
        {0, 1, 350, 190, 315, 270}
    };
     
     private int contadorAnima;
     private int step;
     private int iniRad;
     
     private int [] velXY = {0, 0};
     private int vel;
     private Boolean avanzar;

    public PacMan(int x, int y, int tileX, int tileY, int dirPorDefecto) {
        super();
        this.tileX = tileX;
        this.tileY = tileY;        
        this.x = x * this.tileX;
        this.y = y * this.tileY;
        this.dirPorDefecto = dirPorDefecto;
        
        this.pulsada = dirPorDefecto;
        this.iniRad = this.direcciones[this.pulsada][2];
        
        this.pulsadaConfirmada = this.pulsada;
        
        this.contadorAnima = NRO_ANIMAS_BOCA;
        this.step = (int) ((85 + 5) / NRO_ANIMAS_BOCA);
        
        this.velXY[0] = this.direcciones[this.pulsada][0];
        this.velXY[1] = this.direcciones[this.pulsada][1];
    
        this.vel = (int) (this.tileY / DIVIDIR_TILE_ENTRE);
        this.avanzar = true;
    } 
    
        @Override
    public void dibuja(Graphics g, int[][] matriz, Settings sett) {
   
        //Color PacMan
        int [] rgb = {245, 215, 5};
        
        this.pulsada = actualizaTeclado(sett);
        actualiza(matriz, sett);
        
        this.contadorAnima --;
        if (this.contadorAnima <= 1){
            this.contadorAnima = NRO_ANIMAS_BOCA;
        }
        
        int iniR = this.iniRad - this.contadorAnima * this.step;
        int finR = this.direcciones[0][3] + (this.contadorAnima * this.step) * 2;
    
		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.fillArc(this.x, this.y, this.tileX, this.tileY, iniR, finR);       
    }
    
    @Override
    public void actualiza(int[][] matriz, Settings sett) {
        
        if (this.x % this.tileX == 0 && this.y % this.tileY == 0){
            
            int x = (int) (this.x  / this.tileX);
            int y = (int) (this.y  / this.tileY);
            
            Boolean colisionPulsada =  checkColisionLaberintoPulsada(x, y, matriz, sett);
            Boolean colisionVelXY = checkColisionLaberintoVelXY(x, y, velXY, matriz, sett);
            if(!colisionPulsada){
                
                this.avanzar = true;
                this.pulsadaConfirmada = this.pulsada;
                this.velXY[0] = this.direcciones[this.pulsada][0];
                this.velXY[1] = this.direcciones[this.pulsada][1];
                this.iniRad = this.direcciones[this.pulsada][2];
                
            }else if (!colisionVelXY){
                
                this.avanzar = true;
                
            }else{
                this.avanzar = false;
            }    
        }
        
        if (this.avanzar){
            
            this.x += this.velXY[0] * this.vel;
            this.y += this.velXY[1] * this.vel;
        }
    }
    
    private Boolean checkColisionLaberintoPulsada(int x, int y, int[][] matriz, Settings sett){
     
        int velX = this.direcciones[this.pulsada][0];
        int velY = this.direcciones[this.pulsada][1];
        
        if (x + velX < 0 || x + velX >= sett.laberinto.matriz[0].length) 
            return false;
        
        if (matriz[y + velY][x + velX] == sett.laberinto.PARED){
            return true;
        }
        
        return false;
    }
    
    private int actualizaTeclado(Settings sett){
        
        if (sett.controles.isIzquierda()){
            return 1;
        }else if (sett.controles.isDerecha()){
            return 0;
        }else if (sett.controles.isArriba()){
            return 2;
        }else if (sett.controles.isAbajo()){
            return 3;
        }
        return 0;
    }

    // Getters & Setters

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

    public int getTileY() {
        return tileY;
    }

    

}

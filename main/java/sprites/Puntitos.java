
package sprites;

import interfaces.ISpritesDraw;
import java.awt.Color;
import java.awt.Graphics;
import settings.Settings;

public class Puntitos implements ISpritesDraw{

    public static final int INI_SIZE_GORDOS = 35;
    public static final int SIZE_NORMALES = 10;
    
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private int matrizX;
    private int matrizY;
    private Boolean gordos;
    private int size;
    private int reDimension = -1;

    public Puntitos(int x, int y, int tileX, int tileY, Boolean gordos) {

        this.tileX = tileX;
        this.tileY = tileY;
        this.matrizX = x;
        this.matrizY = y;
        this.x = x * this.tileX ;
        this.y = y * this.tileY;
        
        this.gordos = gordos;
        
        this.size = this.gordos ? INI_SIZE_GORDOS : SIZE_NORMALES;
    
    }
    
    
    
    @Override
    public void dibuja(Graphics g, int[][] matriz, Settings sett) {
    
        int[] rgb = {220, 220, 220, 200, 255, 9};
        
        final int RADIO = (int) (this.size / 2);
        final int CENTRO_X = (int) (this.tileX / 2);
        final int CENTRO_Y = (int) (this.tileY / 2);
        
        if(this.gordos){
            
            g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
            this.size += cambiarSizeGordos();
                    
        }else{
            
            g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
        }
        
        if(matriz[matrizY][matrizX] == sett.laberinto.PUNTITO ||
                matriz[matrizY][matrizX] == sett.laberinto.PUNTITO_GORDO){
            g.fillOval(this.x + CENTRO_X - RADIO, this.y + CENTRO_Y - RADIO, RADIO * 2, RADIO * 2); 
        }
    }
    
    private int cambiarSizeGordos(){
        
        if(reDimension > 0 && size >= INI_SIZE_GORDOS){
            
            reDimension = -1;
            
        }else if(reDimension < 0 && size <=1){
            
            reDimension = 1;
        }
        return reDimension;
    }
}

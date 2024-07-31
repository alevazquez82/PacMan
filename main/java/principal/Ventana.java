package principal;

import interfaces.IResetControles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import settings.Settings;
import sprites.Fantasma;
import sprites.Pared;
import sprites.Puntitos;
import sprites.PacMan;

public class Ventana extends JPanel implements ActionListener, IResetControles{
    private static final long serialVersionUID = 5413669749544194676L;
    private Settings settings;
    private Instancias instancias;
    
    private ArrayList<Pared> pared;
    private ArrayList<Puntitos> puntitos;
    private PacMan pacman;
    private Fantasma[] fantasma;
    
    private Timer timer;
    private static long milisec;
    
    public Ventana(){
        
        inicializa();
    }
    private void inicializa(){
        
        settings = Settings.getInstancia();
        
        addKeyListener(new Controles());
        
        int[] rgb = settings.getColorFondos();
        setBackground(new Color(rgb[3], rgb[4], rgb[5]));
        setFocusable(true);
        setPreferredSize(new Dimension(settings.laberinto.RES_X, settings.laberinto.RES_Y));
       
        comenzar();
    }

    private void comenzar() {
       
        instancias = new Instancias(settings);
        
        pared = instancias.instanciarParedesLaberinto();
        puntitos = instancias.instanciarPuntitosLaberinto();
        pacman = instancias.instanciarPacMan();
        fantasma = instancias.instanciarFantasmas(this);
        
        timer = new Timer((int) (2000 / 60) ,this);
        timer.start();
        timer.setRepeats(true);
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        renderiza(g);
        
    }
    
    private void renderiza(Graphics g){
        
        int[][] matriz = settings.laberinto.matriz;
        
        for(Pared tile: pared) {
            tile.dibuja(g);
        }
        
        for(Puntitos tile: puntitos) {
            tile.dibuja(g, matriz, settings);
        }
        
        if (pacman !=null){
            pacman.dibuja(g, matriz, settings);
        }
        
        for (int i = 0; i < settings.NUMERO_FANTASMAS; i++){
            
            if (fantasma[i] != null){
                fantasma[i].dibuja(g, matriz, settings);
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Fantasma.checkFinFantasmasAzules(settings);
        
        repaint();
    }
    
    private class Controles extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            //super.keyPressed(e);
            
            int key = e.getKeyCode();
            
            if(key == KeyEvent.VK_LEFT){
                resetControles(false, settings);
                settings.controles.setIzquierda(true);
                
            }else if(key == KeyEvent.VK_RIGHT){
                resetControles(false, settings);
                settings.controles.setDerecha(true);
                
            }else if(key == KeyEvent.VK_UP){
                resetControles(false, settings);
                settings.controles.setArriba(true);
                
            }else if(key == KeyEvent.VK_DOWN){
                resetControles(false, settings);
                settings.controles.setAbajo(true);
            }
            if (key == KeyEvent.VK_ESCAPE){
                Toolkit.getDefaultToolkit().beep();
                System.exit(0);
            }
        }
        
    }
    
    //Getters & Setters

    public PacMan getPacman() {
        return pacman;
    }

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }

    public static long getMiliSec() {
        return milisec;
    }

    public static void setMiliSec(long milisec) {
        Ventana.milisec = milisec;
    }
    
    

}

package principal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame{
    public Main(){
        inicializa();
    }
    
    private void inicializa(){
        add(new Ventana());
        
        setResizable(false);
        pack();
        
        setTitle("Pacman Java");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                
                        try{
                            JFrame principal = new Main();
                            principal.setVisible(true);
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }    
            }
            
        });
        

    }    
}

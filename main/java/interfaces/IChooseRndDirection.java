
package interfaces;

public interface IChooseRndDirection {
    
    final int MIN_PERC_PERSEGUIR = 60;
    final int MAX_PERC_PERSEGUIR = 85;
    
    default int elegirOtraDireccionRND(int[][] otraDireccionRnd, int direccion){
        //Elige una nueva direccion entre 3 posibles distinta a la actual
        return otraDireccionRnd[direccion][(int)(Math.random() * 3)];
      
    }
    
    
}

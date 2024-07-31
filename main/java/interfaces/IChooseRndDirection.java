package interfaces;

public interface IChooseRndDirection {

    final int MIN_PERC_PERSEGUIR = 60;
    final int MAX_PERC_PERSEGUIR = 85;

    default int elegirOtraDireccionRND(int[][] otraDireccionRnd, int direccion) {
        //Elige una nueva direccion entre 3 posibles distinta a la actual
       		return otraDireccionRnd[direccion][(int) (Math.random() * 3)];
	}
	
	default int perseguirApacMan(int x, int y, int pacmanX, int pacmanY) {
		
		final int RANGO_RND = 10;
		int hor_ver = (int) (Math.random() * RANGO_RND);
		
		if (hor_ver < (int) (RANGO_RND / 2)) {
			
			if (y < pacmanY) {
				
				return 3;
				
			} else {
				
				return 2;
			}
			
		} else {
			
			if (x < pacmanX) {
				return 0;
				
			} else {
				
				return 1;
			}
		}
	}
}
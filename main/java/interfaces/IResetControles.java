
package interfaces;

import settings.Settings;

public interface IResetControles {

    default void resetControles(Boolean bool, Settings settings){
        
        settings.controles.setIzquierda(bool);
        settings.controles.setDerecha(bool);
        settings.controles.setArriba(bool);
        settings.controles.setAbajo(bool);
    }
}

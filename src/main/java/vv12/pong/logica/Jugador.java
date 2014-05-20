package vv12.pong.logica;

import vv12.pong.graficos.Pantalla;
import org.newdawn.slick.Input;

/**
 *
 */
public class Jugador {

    int puntuacion;
    Pala pala = new Pala();

    public Jugador(int posx, int posy){
        puntuacion = 0;
        pala.setPosX(posx);
        pala.setPosY(posy);
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void incrementarPuntuacion() {
        this.puntuacion = this.puntuacion + 1;
    }

    public Pala getPala() {
        return pala;
    }

    /**
     * Método que controla el movimiento de la pala
     * del jugador humano a partir de la tecla pulsada.
     *
     * @param key
     */
    public void jugar(int no_jug, Input input) {
        switch (no_jug){
		    case 1:
		        if (input.isKeyDown(Input.KEY_A)){
		            pala.subirPala();
		        } else if (input.isKeyDown(Input.KEY_Z)) {
		            pala.bajarPala();
		        }
		        break;
        	case 2:
		        if (input.isKeyDown(Input.KEY_UP)){
		            pala.subirPala();
		        } else if (input.isKeyDown(Input.KEY_DOWN)) {
		            pala.bajarPala();
		        }
		        break;
        }
    }
}
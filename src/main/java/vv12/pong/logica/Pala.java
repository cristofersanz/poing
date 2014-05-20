/**
 * Author: Cristofer Sanz Blasco (584191)
 * Project: pong_vv
 * File: .java
 * Modified: 8/04/14
 * Description: 
 */
package vv12.pong.logica;

import vv12.pong.graficos.Pantalla;
import static vv12.pong.utiles.Utiles.conf;
/**
 *
 */
public class Pala {

    private static final int WIDTH = Integer.valueOf(conf("pantalla.width"));
    private static final int HEIGHT = Integer.valueOf(conf("pantalla.height"));
	private static final int ANCHURA = Integer.valueOf(conf("pala.anchura"));
	private static final int ALTURA = Integer.valueOf(conf("pala.altura"));
	private static final int VEL = Integer.valueOf(conf("pala.velocidad"));

    private int posX;
    private int posYSup;
    private int posYInf;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
    	setPosYSup(posY);
    	setPosYInf(posY + ALTURA);
    }

    public int getPosYSup() {
        return posYSup;
    }

    public void setPosYSup(int posYSup) {
        this.posYSup = posYSup;
    }

    public int getPosYInf() {
        return posYInf;
    }

    public void setPosYInf(int posYInf) {
        this.posYInf = posYInf;
    }

    public int getPosMedia() {
        return (int) Math.floor((posYSup + posYInf) / 2);
    }

    public int getAnchura(){
    	return ANCHURA;
    }

    public int getAltura(){
    	return ALTURA;
    }

    void subirPala() {
        if (getPosYSup() >= 1){
            setPosYSup(getPosYSup() - VEL);
            setPosYInf(getPosYInf() - VEL);
        }
    }

    void bajarPala() {
        if (getPosYInf() <= HEIGHT - 1) {
            setPosYSup(getPosYSup() + VEL);
            setPosYInf(getPosYInf() + VEL);
        }
    }
}

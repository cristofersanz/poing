/**
 * Author: Cristofer Sanz Blasco (584191)
 * Project: pong_vv
 * File: .java
 * Modified: 8/04/14
 * Description: 
 */
package vv12.pong.logica;

import vv12.pong.graficos.Pantalla;
/**
 *
 */
public class Pala {

	private static final int ANCHURA = 10;
	private static final int ALTURA = 80;
	private static final int VEL = 10;

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
        if (getPosYInf() <= Pantalla.getFilas() - 1) {
            setPosYSup(getPosYSup() + VEL);
            setPosYInf(getPosYInf() + VEL);
        }
    }
}

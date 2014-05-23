/**
 * Author: Cristofer Sanz Blasco (584191)
 * Project: pong_vv
 * File: .java
 * Modified: 8/04/14
 * Description: 
 */
package vv12.pong.logica;

import static vv12.pong.utiles.Utiles.conf;

/**
 *
 */
public class Pala {

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
        setPosYInf(posY + Integer.valueOf(conf("pala.altura")));
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

    public int getAnchura() {
        return Integer.valueOf(conf("pala.anchura"));
    }

    public int getAltura() {
        return Integer.valueOf(conf("pala.altura"));
    }

    void subirPala() {
        if (getPosYSup() >= 1) {
            setPosYSup(getPosYSup() - Integer.valueOf(conf("pala.velocidad")));
            setPosYInf(getPosYInf() - Integer.valueOf(conf("pala.velocidad")));
        }
    }

    void bajarPala() {
        if (getPosYInf() <= Integer.valueOf(conf("pantalla.height")) - 1) {
            setPosYSup(getPosYSup() + Integer.valueOf(conf("pala.velocidad")));
            setPosYInf(getPosYInf() + Integer.valueOf(conf("pala.velocidad")));
        }
    }
}

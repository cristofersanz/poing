package vv12.pong.logica;

import java.util.Random;

import static vv12.pong.utiles.Utiles.conf;
import static vv12.pong.utiles.Utiles.enRango;


/**
 *
 */
public class Bola {
    public enum Direccion {ARDCHA, ABDCHA, ABIZDA, ARIZDA}

    private int posX;
    private int posY;
    private Direccion dir;
    private int ang;

    public Bola() {
        /* Nueva direccion al aleatoria */
        dir = Direccion.values()[new Random().nextInt(4)];
        posX = Integer.valueOf(conf("pantalla.width")) / 2;
        posY = Integer.valueOf(conf("pantalla.height")) / 2;
    }

    public int getRadio() {
        return Integer.valueOf(conf("bola.radio"));
    }

    public int getPosX() {
        return posX;
    }

    public int getPosXIzda() {
        return posX - Integer.valueOf(conf("bola.velocidad"));
    }

    public int getPosXDcha() {
        return posX + Integer.valueOf(conf("bola.radio")) + Integer.valueOf(conf("bola.velocidad"));
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosYSup() {
        return posY - Integer.valueOf(conf("bola.velocidad"));
    }

    public int getPosYInf() {
        /* Anticipa la posicion al proximo movimiento */
        return posY + Integer.valueOf(conf("bola.radio")) + Integer.valueOf(conf("bola.velocidad"));
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Direccion getDir() {
        return dir;
    }

    public void setDir(Direccion dir) {
        this.dir = dir;
    }

    private boolean chocaPala(Pala pala) {
        return enRango(pala.getPosYSup() - Integer.valueOf(conf("pala.margen_extra")), getPosY(),
                pala.getPosYInf() + Integer.valueOf(conf("pala.margen_extra")));
    }

    private boolean chocaEnPunta(Pala pala) {
        return enRango(pala.getPosYSup() - Integer.valueOf(conf("pala.margen_extra")), getPosY(), pala.getPosYSup() + 2) ||
                enRango(pala.getPosYInf() - 2, getPosY(), pala.getPosYInf() + Integer.valueOf(conf("pala.margen_extra")));
    }

    /**
     * Calcula los rebotes contra paredes y palas y,
     * en caso de rebotar contra una pared izda o dcha, señala
     * el tanto.
     *
     * @param palaJug1
     * @param palaJug2
     * @return
     */
    public int calcularRebotes(Pala palaJug1, Pala palaJug2) {
        /*
            0 - No victoria
            1 - Punto para Jug1 (gol en Jug2)
            2 - Punto para Jug2 (gol en Jug1)
        */

        /* Rebotes superior e inferior*/
        if (getPosYSup() <= 1) {
            rebotaArriba();
        } else if (getPosYInf() >= Integer.valueOf(conf("pantalla.height")) - 1) {
            rebotaAbajo();
        }

            /* Rebotes en palas o goles */
        if (getPosXIzda() <= (palaJug1.getPosX() + palaJug1.getAnchura())) {
            if (chocaPala(palaJug1)) {
                if (chocaEnPunta(palaJug1)) {    /* Pica la bola */
                    ang = 2;
                } else {
                    ang = 1;
                }
                rebotaPalaJug1();
            } else {    /* Marca gol Jug2 -> Jug1 */
                return 2;
            }
        } else if (getPosXDcha() >= palaJug2.getPosX() - 1) {
            if (chocaPala(palaJug2)) {
                if (chocaEnPunta(palaJug2)) {    /* Pica la bola */
                    ang = 2;
                } else {
                    ang = 1;
                }
                rebotaPalaJug2();
            } else {     /* Marca gol Jug1 -> Jug2 */
                return 1;
            }
        }

            /* Sólo avanza la bola */
        noRebota();
        return 0;
    }

    private void noRebota() {
        switch (getDir()) {
            case ABDCHA:
                moverABDCHA();
                break;
            case ABIZDA:
                moverABIZDA();
                break;
            case ARIZDA:
                moverARIZDA();
                break;
            case ARDCHA:
                moverARDCHA();
                break;
            default:
                break;
        }
    }

    private void rebotaPalaJug2() {
        switch (getDir()) {
            case ABDCHA:
                setDir(Direccion.ABIZDA);
                moverABIZDA();
                break;
            case ARDCHA:
                setDir(Direccion.ARIZDA);
                moverARIZDA();
                break;
        }
    }

    private void rebotaPalaJug1() {
        switch (getDir()) {
            case ABIZDA:
                setDir(Direccion.ABDCHA);
                moverABDCHA();
                break;
            case ARIZDA:
                setDir(Direccion.ARDCHA);
                moverARDCHA();
                break;
        }
    }

    private void rebotaAbajo() {
        switch (getDir()) {
            case ABDCHA:
                setDir(Direccion.ARDCHA);
                moverARDCHA();
                break;
            case ABIZDA:
                setDir(Direccion.ARIZDA);
                moverARIZDA();
                break;
        }
    }

    private void rebotaArriba() {
        switch (getDir()) {
            case ARDCHA:
                setDir(Direccion.ABDCHA);
                moverABDCHA();
                break;
            case ARIZDA:
                setDir(Direccion.ABIZDA);
                moverABIZDA();
                break;
        }
    }

    private void moverABDCHA() {
        setPosX(getPosX() + Integer.valueOf(conf("bola.velocidad")));
        setPosY(getPosY() + (Integer.valueOf(conf("bola.velocidad")) * ang) / 2);
    }

    private void moverABIZDA() {
        setPosX(getPosX() - Integer.valueOf(conf("bola.velocidad")));
        setPosY(getPosY() + (Integer.valueOf(conf("bola.velocidad")) * ang) / 2);
    }

    private void moverARDCHA() {
        setPosX(getPosX() + Integer.valueOf(conf("bola.velocidad")));
        setPosY(getPosY() - (Integer.valueOf(conf("bola.velocidad")) * ang) / 2);
    }

    private void moverARIZDA() {
        setPosX(getPosX() - Integer.valueOf(conf("bola.velocidad")));
        setPosY(getPosY() - (Integer.valueOf(conf("bola.velocidad")) * ang) / 2);
    }
}

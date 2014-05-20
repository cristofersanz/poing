package vv12.pong.logica;

import vv12.pong.graficos.Pantalla;
import vv12.pong.logica.Pala;

import java.util.Random;

import static vv12.pong.utiles.Utiles.enRango;

/**
 *
 */
public class Bola {
    public enum Direccion { ARDCHA, ABDCHA, ABIZDA, ARIZDA }

    private static final int RADIO = 12;
    private static final int VEL = 6;

    private int posX;
    private int posY;
    private Direccion dir;
    private int ang = 1;

    public Bola() {
        dir = Direccion.values()[new Random().nextInt(4)];
        posX = Pantalla.getColumnas() / 2;
        posY = Pantalla.getFilas() / 2;
    }

    public int getRadio() {
    	return RADIO;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosXIzda() {
        return posX - VEL;
    }

    public int getPosXDcha() {
        return posX + RADIO + VEL;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosYSup() {
        return posY - VEL;
    }

    public int getPosYInf() {
        return posY + RADIO + VEL;
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
        } else if (getPosYInf() >= Pantalla.getFilas() - 1) {
            rebotaAbajo();
        }

        /* Rebotes en palas o goles */
        if (getPosXIzda() <= (palaJug1.getPosX() + palaJug1.getAnchura())) {
            if (enRango(palaJug1.getPosYSup(), getPosY(), palaJug1.getPosYInf())) {
        		if (enRango(palaJug1.getPosYSup(), getPosY(), palaJug1.getPosYSup() + 2) ||
        			enRango(palaJug1.getPosYInf() - 2, getPosY(), palaJug1.getPosYInf())) {		// Fix: Evitar hardcodear
        			ang = 2;
        		} else {
        			ang = 1;
        		}
                rebotaPalaJug1();
            } else {    /* Marca gol Jug2 -> Jug1 */
                return 2;
            }
        } else if (getPosXDcha() >= palaJug2.getPosX() - 1) {
            if (enRango(palaJug2.getPosYSup(), getPosY(), palaJug2.getPosYInf())) {
        		if (enRango(palaJug2.getPosYSup(), getPosY(), palaJug1.getPosYSup() + 2) ||
        			enRango(palaJug2.getPosYInf() - 2, getPosY(), palaJug1.getPosYInf())) {		// Fix: Evitar hardcodear
        			ang = 2;
        		} else {
        			ang = 1;
        		}
                rebotaPalaJug2();
            } else {     /* Marca gol Jug1 -> Jug2 */
                return 1;
            }
        }

        /* No hay rebotes */
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
        setPosX(getPosX() + VEL);
        setPosY(getPosY() + (VEL * ang) / 2);
    }

    private void moverABIZDA() {
        setPosX(getPosX() - VEL);
        setPosY(getPosY() + (VEL * ang) / 2);
    }

    private void moverARDCHA() {
        setPosX(getPosX() + VEL);
        setPosY(getPosY() - (VEL * ang) / 2);
    }

    private void moverARIZDA() {
        setPosX(getPosX() - VEL);
        setPosY(getPosY() - (VEL * ang) / 2);
    }
}

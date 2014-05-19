package vv12.pong.logica;

import vv12.pong.graficos.Pantalla;
import vv12.pong.logica.Pala;

import static vv12.pong.utiles.Utiles.enRango;

/**
 *
 */
public class Bola {
    public enum Direccion { ARDCHA, ABDCHA, ABIZDA, ARIZDA }

    private int posX;
    private int posY;

    private Direccion dir;

    public Bola() {
        dir = Direccion.values()[(int) Math.floor(Math.random() * 4)];
        posX = Pantalla.getColumnas() / 2;
        posY = Pantalla.getFilas() / 2;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
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
     * @param palaHumano
     * @param palaMaquina
     * @return
     */
    public int calcularRebotes(Pala palaHumano, Pala palaMaquina) {
        /*
            0 - No victoria
            1 - Punto para humano (gol en maquina)
            2 - Punto para maquina (gol en humano)
        */

        /* Rebotes superior e inferior*/
        if (getPosY() == 1) {
            rebotaArriba();
        } else if (getPosY() == Pantalla.getFilas() - 1) {
            rebotaAbajo();
        }

        /* Rebotes en palas o goles */
        if (getPosX() == palaHumano.getPosX() + 1) {
            if (enRango(palaHumano.getPosYSup(), getPosY(), palaHumano.getPosYInf())) {
                rebotaPalaHumano();
            } else {    /* Marca gol Maquina -> Humano */
                return 2;
            }
        } else if (getPosX() == palaMaquina.getPosX() - 1) {
            if (enRango(palaMaquina.getPosYSup(), getPosY(), palaMaquina.getPosYInf())) {
                rebotaPalaMaquina();
            } else {     /* Marca gol Humano -> Maquina */
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

    private void rebotaPalaMaquina() {
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

    private void rebotaPalaHumano() {
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
        setPosX(getPosX() + 1);
        setPosY(getPosY() + 1);
    }

    private void moverABIZDA() {
        setPosX(getPosX() - 1);
        setPosY(getPosY() + 1);
    }

    private void moverARDCHA() {
        setPosX(getPosX() + 1);
        setPosY(getPosY() - 1);
    }

    private void moverARIZDA() {
        setPosX(getPosX() - 1);
        setPosY(getPosY() - 1);
    }
}

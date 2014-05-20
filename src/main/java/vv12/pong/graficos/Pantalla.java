package vv12.pong.graficos;

import org.newdawn.slick.Graphics;
import vv12.pong.logica.*;
/**
 *
 */
public class Pantalla {

    /** Screen width */
    private static final int WIDTH = 800;       // Fix: Evitar duplicar
    /** Screen height */
    private static final int HEIGHT = 600;      // Fix: Evitar duplicar

    public static void pintarJugador(Graphics g, Jugador jugador) {
        g.fillRect(jugador.getPala().getPosX(),jugador.getPala().getPosYSup(),jugador.getPala().getAnchura(),jugador.getPala().getAltura());
    }

    public static void pintarBola(Graphics g, Bola bola) {
        g.fillOval(bola.getPosX(), bola.getPosY(), bola.getRadio(), bola.getRadio());
    }

    public static void pintarRed(Graphics g) {
        g.fillRect(WIDTH/2,0,2,HEIGHT);
    }

    public static void pintarMarcadores(Graphics g, Jugador j1, Jugador j2) {
        g.drawString(String.valueOf(j1.getPuntuacion()),(float)WIDTH/2 - 50,(float)50);
        g.drawString(String.valueOf(j2.getPuntuacion()),(float)WIDTH/2 + 10,(float)50);
    }

    public static int getColumnas() {
        return WIDTH;
    }

    public static int getFilas() {
        return HEIGHT;
    }
}

package vv12.pong.graficos;

import org.newdawn.slick.Graphics;
import vv12.pong.logica.*;

import static vv12.pong.utiles.Utiles.conf;

/**
 *
 */
public class Pantalla {

    private static final int WIDTH = Integer.valueOf(conf("pantalla.width"));
    private static final int HEIGHT = Integer.valueOf(conf("pantalla.height"));
    private static final int ANCHURA_RED = Integer.valueOf(conf("red.anchura"));
    private static final float MARCADOR1_POS_X = (WIDTH * 2) / 5;
    private static final float MARCADOR2_POS_X = (WIDTH * 3) / 5;
    private static final float MARCADOR_POS_Y = (float) (0.05 * HEIGHT);

    public static void pintarJugador(Graphics g, Jugador jugador) {
        g.fillRect(jugador.getPala().getPosX(), jugador.getPala().getPosYSup(),
            jugador.getPala().getAnchura(), jugador.getPala().getAltura());
    }

    public static void pintarBola(Graphics g, Bola bola) {
        g.fillOval(bola.getPosX(), bola.getPosY(), bola.getRadio(), bola.getRadio());
    }

    public static void pintarRed(Graphics g) {
        g.fillRect(WIDTH / 2, 0, ANCHURA_RED, HEIGHT);
    }

    public static void pintarMarcadores(Graphics g, Jugador j1, Jugador j2) {
        g.drawString(String.valueOf(j1.getPuntuacion()), MARCADOR1_POS_X, MARCADOR_POS_Y);
        g.drawString(String.valueOf(j2.getPuntuacion()), MARCADOR2_POS_X, MARCADOR_POS_Y);
    }
}

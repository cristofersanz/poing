package vv12.pong.graficos;

// import logica.Bola;
// import logica.Jugador;
// import logica.JugadorHumano;

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
        g.drawRect(jugador.getPala().getPosX(),jugador.getPala().getPosYSup(),jugador.getPala().getAnchura(),jugador.getPala().getAltura());
    }

    public static void pintarBola(Graphics g, Bola bola) {
        g.drawOval(bola.getPosX(), bola.getPosY(), 10, 10);
    }

    public static int getColumnas() {
        return WIDTH;
    }

    public static int getFilas() {
        return HEIGHT;
    }

    // public static void pintarContador(Jugador jHumano, Jugador jMaquina) {
    //     pantalla.putString(15, 2, "Humano: " + jHumano.getPuntuacion(), Color.BLUE, Color.DEFAULT, ScreenCharacterStyle.valueOf("Bold"));
    //     pantalla.putString(45, 2, "Maquina: " + jMaquina.getPuntuacion(), Color.RED, Color.DEFAULT, ScreenCharacterStyle.valueOf("Bold"));
    // }
}

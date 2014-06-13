package vv12.pong.graficos;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import vv12.pong.logica.Bola;
import vv12.pong.logica.Jugador;

import static vv12.pong.utiles.Utiles.conf;

/**
 *
 */
public class Pantalla {

    public static void pintarJugador(Graphics g, Jugador jugador) {
        g.fillRect(jugador.getPala().getPosX(), jugador.getPala().getPosYSup(),
                jugador.getPala().getAnchura(), jugador.getPala().getAltura());
    }

    public static void pintarBola(Graphics g, Bola bola) {
        g.fillOval(bola.getPosX(), bola.getPosY(), bola.getRadio(), bola.getRadio());
    }

    public static void pintarRed(Graphics g) {
        g.fillRect(Integer.valueOf(conf("pantalla.width")) / 2, 0,
                Integer.valueOf(conf("red.anchura")), Integer.valueOf(conf("pantalla.height")));
    }

    public static void pintarMarcadores(Graphics g, Jugador j1, Jugador j2) {
        g.drawString(String.valueOf(j1.getPuntuacion()), (Integer.valueOf(conf("pantalla.width")) * 2) / 5,
                (float) (0.05 * Integer.valueOf(conf("pantalla.height"))));
        g.drawString(String.valueOf(j2.getPuntuacion()), (Integer.valueOf(conf("pantalla.width")) * 3) / 5,
                (float) (0.05 * Integer.valueOf(conf("pantalla.height"))));
    }

    public static void pintarPause(Graphics g) throws SlickException {
        g.drawImage(new Image("pause.png"), Integer.valueOf(conf("pantalla.width")) / 3,
                9 * Integer.valueOf(conf("pantalla.height")) / 20);
    }

    public static void pintarMenu(Graphics g, int opcionMenu) throws SlickException {
        switch (opcionMenu) {
            case 1:
                g.drawImage(new Image("nivel_facil.png"), Integer.valueOf(conf("pantalla.width")) / 3,
                        5 * Integer.valueOf(conf("pantalla.height")) / 12);
                break;

            case 2:
                g.drawImage(new Image("nivel_normal.png"), Integer.valueOf(conf("pantalla.width")) / 3,
                        5 * Integer.valueOf(conf("pantalla.height")) / 12);
                break;

            case 3:
                g.drawImage(new Image("nivel_dificil.png"), Integer.valueOf(conf("pantalla.width")) / 3,
                        5 * Integer.valueOf(conf("pantalla.height")) / 12);
                break;
            default:
                break;
        }
    }

    public static void pintarGameOver(Graphics g, int gameOver) throws SlickException {
        switch (gameOver) {
            case 1:
                g.drawImage(new Image("gameover_1.png"), Integer.valueOf(conf("pantalla.width")) / 4,
                        5 * Integer.valueOf(conf("pantalla.height")) / 12);
                break;

            case 2:
                g.drawImage(new Image("gameover_2.png"), Integer.valueOf(conf("pantalla.width")) / 4,
                        5 * Integer.valueOf(conf("pantalla.height")) / 12);
                break;
            default:
                break;
        }
    }
}

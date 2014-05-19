package vv12.pong;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import vv12.pong.graficos.*;
import vv12.pong.logica.*;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

    /** Screen width */
    private static final int WIDTH = 800;
    /** Screen height */
    private static final int HEIGHT = 600;
    
    private static final int J1_POS_X = 50;    // Fix: No hardcodear
    private static final int J2_POS_X = 700;    // Fix: No hardcodear
    private static final int POS_Y = 200;   // Fix: No hardcodear

    private static final boolean DEBUG = true;

    private Jugador jugador1;
    private Jugador jugador2;
    private Bola bola;
    private Input input;

    public Game() {
        super("Pong original!");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        Pantalla.pintarJugador(g,jugador1);
        Pantalla.pintarJugador(g,jugador2);
        Pantalla.pintarBola(g,bola);

        if (DEBUG){
            g.drawString("X: " + bola.getPosX() + " || Y: " + bola.getPosY() + " || DIR: " +
                bola.getDir(), 100,10);
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        jugador1 = new Jugador(J1_POS_X,POS_Y);
        jugador2 = new Jugador(J2_POS_X,POS_Y);
        bola = new Bola();
        input = new Input(HEIGHT);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        jugador1.jugar(1,input);
        jugador2.jugar(2,input);
        bola.calcularRebotes(jugador1.getPala(),jugador2.getPala());
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.start();
    }

}

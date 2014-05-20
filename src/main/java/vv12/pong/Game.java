package vv12.pong;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import vv12.pong.graficos.*;
import vv12.pong.logica.*;
import java.util.Properties;
import static vv12.pong.utiles.Utiles.conf;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

    private static final int WIDTH = Integer.valueOf(conf("pantalla.width"));
    private static final int HEIGHT = Integer.valueOf(conf("pantalla.height"));
    private static final int J1_POS_X = (int) (0.05 * WIDTH);    
    private static final int J2_POS_X = (int) (0.95 * WIDTH);   
    private static final int POS_Y = (HEIGHT - Integer.valueOf(conf("pala.altura"))) / 2;
    private static final int TIEMPO_RESET = Integer.valueOf(conf("tiempo_reset"));
    private static final boolean DEBUG = Boolean.valueOf(conf("debug"));

    private Jugador jugador1;
    private Jugador jugador2;
    private Bola bola;
    private Input input;
    private boolean reset;
    private int timer;

    public Game() {
        super("Pong original!");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        Pantalla.pintarJugador(g,jugador1);
        Pantalla.pintarJugador(g,jugador2);
        Pantalla.pintarBola(g,bola);
        Pantalla.pintarRed(g);
        Pantalla.pintarMarcadores(g,jugador1,jugador2);

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
        timerOn();
    }

    public void reinit(){
        bola = new Bola();
        timerOn();
    }

    public void timerOn() {
        timer = 0;
        reset = true;
    }

    public void timerOff() {
        reset = false;
    }

    public void timer(int delta) throws SlickException {
        if (reset) {
            timer += delta;
        }

        if (timer >= TIEMPO_RESET) {
            timerOff();
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        jugador1.jugar(1,input);
        jugador2.jugar(2,input);
        
        timer(delta);

        switch (bola.calcularRebotes(jugador1.getPala(),jugador2.getPala(),reset)){
            case 1:     /* Gana jugador 1 */
                jugador1.incrementarPuntuacion();
                reinit();
                break;
            case 2:     /* Gana jugador 2 */
                jugador2.incrementarPuntuacion();
                reinit();
                break;
            default:
                break;
        }
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.start();
    }
}
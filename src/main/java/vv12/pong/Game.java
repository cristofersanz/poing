package vv12.pong;

import org.newdawn.slick.*;
import vv12.pong.graficos.Pantalla;
import vv12.pong.logica.Bola;
import vv12.pong.logica.Jugador;

import static vv12.pong.utiles.Utiles.conf;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame implements KeyListener {

    /* COMPONENTES JUEGO */
    private Jugador jugador1;
    private Jugador jugador2;
    private Bola bola;
    private Input input;

    /* FLAGS */
    private boolean reset;
    private boolean pause = false;
    private boolean menu = true;
    private int opcionMenu = 1;
    private int gameOver = 0;
    private int timer;

    public Game() {
        super("Pong original!");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        jugador1 = new Jugador((int) (0.05 * Integer.valueOf(conf("pantalla.width"))),
                (Integer.valueOf(conf("pantalla.height")) - Integer.valueOf(conf("pala.altura"))) / 2);
        jugador2 = new Jugador((int) (0.95 * Integer.valueOf(conf("pantalla.width"))),
                (Integer.valueOf(conf("pantalla.height")) - Integer.valueOf(conf("pala.altura"))) / 2);
        bola = new Bola();
        input = new Input(Integer.valueOf(conf("pantalla.height")));

        /* Esperar tiempo hasta iniciar partida */
        timerOn();
    }

    @Override
    public void keyPressed(int key, char c) {
        if (menu) {
            switch (key) {
                case Input.KEY_UP:
                    if (opcionMenu > 1)         /* Dificultad fácil */
                        opcionMenu -= 1;
                    break;
                case Input.KEY_DOWN:
                    if (opcionMenu < 3)         /* Dificultad difícil */
                        opcionMenu += 1;
                    break;
                case Input.KEY_ENTER:
                    seleccionarDificultad();
                    break;
            }
        } else {
            if (key == Input.KEY_ENTER) {
                pause();
            }
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (!pause && !menu && gameOver == 0) {

            /* Escucha teclas de movimiento y mueve */
            jugador1.jugar(1, input);
            jugador2.jugar(2, input);

            /* Disminuye tiempo del timer si está en marcha */
            timer(delta);

            /* Mueve bola si no está iniciando partida o juego */
            if (!reset) {
                switch (bola.calcularRebotes(jugador1.getPala(), jugador2.getPala())) {
                    case 1:     /* Gana jugador 1 */
                        jugador1.incrementarPuntuacion();
                        if (jugador1.getPuntuacion() >= Integer.valueOf(conf("num_partidas")))
                            gameOver = 1;
                        else
                            reinit();
                        break;
                    case 2:     /* Gana jugador 2 */
                        jugador2.incrementarPuntuacion();
                        if (jugador2.getPuntuacion() >= Integer.valueOf(conf("num_partidas")))
                            gameOver = 2;
                        else
                            reinit();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        Pantalla.pintarJugador(g, jugador1);
        Pantalla.pintarJugador(g, jugador2);
        Pantalla.pintarBola(g, bola);
        Pantalla.pintarRed(g);
        Pantalla.pintarMarcadores(g, jugador1, jugador2);

        if (pause) {
            Pantalla.pintarPause(g);
        }

        if (menu) {
            Pantalla.pintarMenu(g, opcionMenu);
        }

        if (gameOver != 0) {
            Pantalla.pintarGameOver(g, gameOver);
        }

        if (Boolean.valueOf(conf("debug"))) {
            g.drawString("X: " + bola.getPosX() + " || Y: " + bola.getPosY() + " || DIR: " +
                    bola.getDir() + " || PAUSE: " + pause, 100, 10);
        }
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setVSync(true);
        app.setDisplayMode(Integer.valueOf(conf("pantalla.width")), Integer.valueOf(conf("pantalla.height")), false);
        app.setForceExit(false);
        app.start();
    }

    private void reinit() {
        /* Centra bola y espera para empezar partida */
        bola = new Bola();
        timerOn();
    }

    private void timerOn() {
        timer = 0;
        reset = true;
    }

    private void timerOff() {
        reset = false;
    }

    private void pause() {
        pause = !pause;
    }

    private void timer(int delta) {
        /* Añade tiempo si timer activo */
        if (reset) {
            timer += delta;
        }

        /* Desactiva timer si tiempo superado */
        if (timer >= Integer.valueOf(conf("tiempo_reset"))) {
            timerOff();
        }
    }

    private void seleccionarDificultad() {
        switch (opcionMenu) {
            case 1:     /* Facil */
                conf("pala.altura", conf("facil.pala.altura"));
                conf("bola.velocidad", conf("facil.bola.velocidad"));
                break;

            case 2:     /* Normal */
                conf("pala.altura", conf("normal.pala.altura"));
                conf("bola.velocidad", conf("normal.bola.velocidad"));
                break;

            case 3:     /* Dificil */
                conf("pala.altura", conf("dificil.pala.altura"));
                conf("bola.velocidad", conf("dificil.bola.velocidad"));
                break;
            default:
                break;
        }
        menu = false;
    }
}
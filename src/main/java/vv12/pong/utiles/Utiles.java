package vv12.pong.utiles;

/**
 *
 */
public class Utiles {

    /**
     * Calcula si un valor pertenece a un rango inclusive:
     *  min <= real <= max
     *
     * @param min
     * @param real
     * @param max
     * @return
     */
    public static boolean enRango(int min, int real, int max) {
        return (min <= real) && (real <= max);
    }
}

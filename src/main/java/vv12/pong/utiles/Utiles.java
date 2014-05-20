package vv12.pong.utiles;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
public class Utiles {

    private static Properties conf;

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


    public static String conf(String key) {
        try {
            if (conf == null){
                conf = new Properties();
                conf.load(Utiles.class.getClassLoader().getResourceAsStream("conf.properties"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return conf.getProperty(key);
    }
}

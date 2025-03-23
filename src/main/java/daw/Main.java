
package daw;

/**
 *
 * @author eloy
 */
public class Main {

public static void main(String[] args) {
        int tamaño = 10;
        double porcentajeVivas = 30.0;

        Tablero tablero = new Tablero(tamaño, porcentajeVivas);
        System.out.println("Generacion Inicial:");
        tablero.mostrarTablero();

        int generaciones = 5;
        for (int i = 1; i <= generaciones; i++) {
            tablero.generarSiguienteGeneracion();
            System.out.println("Generacion " + i + ":");
            tablero.mostrarTablero();
        }
    }
}

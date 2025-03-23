package daw;

import java.util.Scanner;

/**
 *
 * @author eloy
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar tamaño del tablero y porcentaje de células vivas iniciales
        System.out.print("Introduce el tamaño del tablero: ");
        int tamaño = scanner.nextInt();

        System.out.print("Introduce el porcentaje de celulas vivas (0-100): ");
        double porcentajeVivas = scanner.nextDouble();

        // Crear tablero e imprimir la primera generación
        Tablero tablero = new Tablero(tamaño, porcentajeVivas);
        System.out.println("Generacion inicial:");
        tablero.mostrarTablero();

        // Preguntar cuántas generaciones desea simular
        System.out.print("Introduce el número de generaciones a simular: ");
        int generaciones = scanner.nextInt();

        // Simular generaciones
        for (int i = 1; i <= generaciones; i++) {
            tablero.generarSiguienteGeneracion();
            System.out.println("Generacion " + i + ":");
            tablero.mostrarTablero();
        }

        // Mostrar los valores internos de las células vivas al final de la simulación
        System.out.println("\nDetalles de las celulas vivas despues de la simulacion:");
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (tablero.getMatrizActual()[i][j].isViva()) {
                    System.out.println("Celula en (" + i + "," + j + "): " + tablero.getMatrizActual()[i][j]);
                }
            }
        }

    }
}

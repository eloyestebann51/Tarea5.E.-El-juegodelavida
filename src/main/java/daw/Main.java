package daw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = null;
        boolean juegoActivo = true;
        int numeroGeneracion = 1;
        int generacionesIgualesConsecutivas = 0;
        List<Integer> listaCelulasVivasRondas = new ArrayList<>();

        System.out.println("Bienvenido al Juego de la Vida");
        System.out.println("1. Iniciar un nuevo juego");
        System.out.println("2. Cargar partida");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el tamano del tablero: ");
                int n = scanner.nextInt();
                System.out.print("Ingrese el porcentaje de celulas vivas: ");
                double porcentajeVivas = scanner.nextDouble();
                tablero = new Tablero(n, porcentajeVivas);
                break;
            case 2:
                System.out.print("Ingrese el nombre del archivo a cargar: ");
                String nombreArchivo = scanner.next();
                CargarJuego.JuegoCargado juego = CargarJuego.JuegoCargado.cargarPartida(nombreArchivo);
                if (juego == null) {
                    System.out.println("No se pudo cargar el juego.");
                    return;
                }
                tablero = juego.tablero;
                numeroGeneracion = juego.numeroGeneracion;
                listaCelulasVivasRondas = juego.celulasVivasRondas;
                break;

            default:
                System.out.println("Opcion no valida.");
                return;
        }

        Celula[][] matrizAnterior = copiarMatriz(tablero.getMatrizActual());

        System.out.println("Generacion " + numeroGeneracion + ":");
        tablero.mostrarTablero();
        System.out.println("Celulas vivas: " + contarCelulasVivas(tablero.getMatrizActual()));
        listaCelulasVivasRondas.add(contarCelulasVivas(tablero.getMatrizActual()));

        while (juegoActivo) {
            System.out.println("\nSeleccione una opcion:");
            System.out.println("1. Mostrar siguiente generacion");
            System.out.println("2. Terminar el juego");
            System.out.print("Opcion: ");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    tablero.generarSiguienteGeneracion();
                    numeroGeneracion++;

                    System.out.println("Generacion " + numeroGeneracion + ":");
                    tablero.mostrarTablero();
                    System.out.println("Celulas vivas: " + contarCelulasVivas(tablero.getMatrizActual()));
                    listaCelulasVivasRondas.add(contarCelulasVivas(tablero.getMatrizActual()));

                    // Comprobamos si hay 3 generaciones consecutivas iguales
                    if (matricesIguales(matrizAnterior, tablero.getMatrizActual())) {
                        generacionesIgualesConsecutivas++;
                    } else {
                        generacionesIgualesConsecutivas = 0;
                    }

                    // Si hay 3 generaciones consecutivas iguales, terminar el juego sin mostrar la siguiente
                    if (generacionesIgualesConsecutivas == 3) {
                        System.out.println("El juego ha terminado debido a la estabilizacion de celulas durante tres generaciones consecutivas.");
                        juegoActivo = false;
                    }

                    // Actualizar matriz anterior
                    matrizAnterior = copiarMatriz(tablero.getMatrizActual());

                    break;
                case 2:
                    System.out.println("Juego terminado por el usuario.");
                    juegoActivo = false;
                    GuardarJuego.guardarEstadoJuego(tablero, numeroGeneracion, listaCelulasVivasRondas);
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static Celula[][] copiarMatriz(Celula[][] matriz) {
        int n = matriz.length;
        Celula[][] copia = new Celula[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copia[i][j] = new Celula();
                copia[i][j].setViva(matriz[i][j].getViva());
            }
        }
        return copia;
    }

    private static boolean matricesIguales(Celula[][] matriz1, Celula[][] matriz2) {
        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz1[i].length; j++) {
                if (matriz1[i][j].getViva() != matriz2[i][j].getViva()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int contarCelulasVivas(Celula[][] matriz) {
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].getViva()) {
                    contador++;
                }
            }
        }
        return contador;
    }
}

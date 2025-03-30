package daw;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = null;
        boolean juegoActivo = true;
        int numeroGeneracion = 1;
        
        System.out.println("Bienvenido al Juego de la Vida");
        System.out.println("1. Iniciar un nuevo juego");
        System.out.println("2. Cargar partida (no implementado aun)");
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
                System.out.println("Funcionalidad de carga no implementada aun.");
                return;
            default:
                System.out.println("Opcion no valida.");
                return;
        }
        
        int generacionesSinCambio = 0;
        int celulasVivasPrevias = contarCelulasVivas(tablero);
        
        System.out.println("Generacion " + numeroGeneracion + ":");
        tablero.mostrarTablero();
        System.out.println("Celulas vivas: " + celulasVivasPrevias);
        
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
                    int celulasVivasActuales = contarCelulasVivas(tablero);
                    
                    System.out.println("Generacion " + numeroGeneracion + ":");
                    tablero.mostrarTablero();
                    System.out.println("Celulas vivas: " + celulasVivasActuales);
                    
                    if (celulasVivasActuales == celulasVivasPrevias) {
                        generacionesSinCambio++;
                    } else {
                        generacionesSinCambio = 0;
                    }
                    celulasVivasPrevias = celulasVivasActuales;
                    
                    if (generacionesSinCambio == 3) {
                        System.out.println("El juego ha terminado debido a la estabilizacion de celulas.");
                        juegoActivo = false;
                    }
                    break;
                case 2:
                    System.out.println("Juego terminado por el usuario.");
                    juegoActivo = false;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
    
    private static int contarCelulasVivas(Tablero tablero) {
        Celula[][] matriz = tablero.getMatrizActual();
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].isViva()) {
                    contador++;
                }
            }
        }
        return contador;
    }
}
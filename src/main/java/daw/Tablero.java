package daw;

import java.util.Random;

public class Tablero {

    private int n; // Tamaño del tablero
    private int[][] matrizActual;
    private int[][] matrizSiguiente;

    public Tablero() {
    }

    public Tablero(int n, double porcentajeVivas) {
        this.n = n;
        this.matrizActual = new int[n][n];
        this.matrizSiguiente = new int[n][n];
        iniciarTablero(porcentajeVivas);
    }

    public void iniciarTablero(double porcentajeCelulasVivas) {
        Random generarAleatorio = new Random();
        
        // Calculo de las celulas vivas
        int totalCasillas = n * n;
        int celulasVivas = (int) (porcentajeCelulasVivas * totalCasillas / 100);

        // Colocación de células aleatoriamente
        int fila, columna;
        for (int i = 0; i < celulasVivas; i++) {
            do {
                fila = generarAleatorio.nextInt(n);
                columna = generarAleatorio.nextInt(n);
                // Si la posición ya está ocupada, genera otra
            } while (matrizActual[fila][columna] == 1);
            // Guarda el valor habiendo comprobado previamente
            matrizActual[fila][columna] = 1;
        }
    }

    // Método para mostrar el tablero
    public void mostrarTablero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrizActual[i][j] == 1 ? "* " : "0 ");
            }
            System.out.println();
        }
    }

    // Método para contar células vecinas vivas
    public int contarVecina(int fila, int columna) {
        int cantidadVivasAlrededor = 0; // Contador de vivas
        // Matriz de las posiciones a comprobar
        int[][] direcciones = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        // Comprobación de las posiciones vecinas
        for (int[] direccion : direcciones) {
            int i = fila + direccion[0];
            int j = columna + direccion[1];
            if (i >= 0 && i < n && j >= 0 && j < n && matrizActual[i][j] == 1) {
                cantidadVivasAlrededor++;
            }
        }
        return cantidadVivasAlrededor;
    }

    // Método para copiar la matriz siguiente a la actual
    public void copiarMatriz() {
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrizSiguiente[i], 0, matrizActual[i], 0, n);
        }
    }

    public int[][] getMatriz() {
        return matrizActual;
    }

    public void generarSiguienteGeneracion() {
        // Se recorre toda la matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Se comprueba la cantidad de vecinas vivas
                int cantidadVivasAlrededor = contarVecina(i, j);
                if (matrizActual[i][j] == 1) { // Células vivas
                    if (cantidadVivasAlrededor == 2 || cantidadVivasAlrededor == 3) {
                        matrizSiguiente[i][j] = 1; // Sobrevive
                    } else {
                        matrizSiguiente[i][j] = 0; // Muere
                    }
                } else { // Células muertas
                    if (cantidadVivasAlrededor == 3) {
                        matrizSiguiente[i][j] = 1; // Revive
                    } else {
                        matrizSiguiente[i][j] = 0;
                    }
                }
            }
        }
        /*
            Como trabajamos con matrizSiguiente para no modificar matrizActual
            en todo el proceso, al final copiamos los valores actualizados de matrizSiguiente
            a matrizActual. De esta forma estudiamos la matriz actual sin modificarla en 
            ningún momento.
        */
        copiarMatriz();
    }
}

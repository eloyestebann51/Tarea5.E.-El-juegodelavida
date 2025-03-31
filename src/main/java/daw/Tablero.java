package daw;

import java.util.Random;

public class Tablero {

    private int n; // Tamaño del tablero
    private Celula[][] matrizActual;
    private Celula[][] matrizSiguiente;
    

    public Tablero() {
    }

    public Tablero(int n, double porcentajeVivas) {
        this.n = n;
        this.matrizActual = new Celula[n][n];
        this.matrizSiguiente = new Celula[n][n];
        inicializarTablero(porcentajeVivas);
    }

    public void inicializarTablero(double porcentajeCelulasVivas) {
        Random generarAleatorio = new Random();
        int totalCasillas = n * n;
        int celulasVivas = (int) (porcentajeCelulasVivas * totalCasillas / 100);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizActual[i][j] = new Celula();
                matrizSiguiente[i][j] = new Celula();
            }
        }

        int fila, columna;
        for (int i = 0; i < celulasVivas; i++) {
            do {
                fila = generarAleatorio.nextInt(n);
                columna = generarAleatorio.nextInt(n);
            } while (matrizActual[fila][columna].getViva());
            matrizActual[fila][columna].setViva(true);
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrizActual[i][j].getViva() ? "* " : "0 ");
            }
            System.out.println();
        }
    }

    public int contarVecinasVivas(int fila, int columna) {
        int cantidadVivasAlrededor = 0;
        int[][] direcciones = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        for (int[] direccion : direcciones) {
            int i = fila + direccion[0];
            int j = columna + direccion[1];
            if (i >= 0 && i < n && j >= 0 && j < n && matrizActual[i][j].getViva()) {
                cantidadVivasAlrededor++;
            }
        }
        return cantidadVivasAlrededor;
    }

    public void copiarMatriz() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizActual[i][j].setViva(matrizSiguiente[i][j].getViva());
            }
        }
    }

    public void generarSiguienteGeneracion() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cantidadVivasAlrededor = contarVecinasVivas(i, j);
                boolean estadoActual = matrizActual[i][j].getViva();
                boolean nuevoEstado = estadoActual;

                if (estadoActual) {
                    if (cantidadVivasAlrededor < 2 || cantidadVivasAlrededor > 3) {
                        nuevoEstado = false;
                    }
                } else {
                    if (cantidadVivasAlrededor == 3) {
                        nuevoEstado = true;
                    }
                }
                matrizSiguiente[i][j].setViva(nuevoEstado);
            }
        }
        copiarMatriz();
    }

    public Celula[][] getMatrizActual() {
        return matrizActual;
    }

    public Celula[][] getMatrizSiguiente() {
        return matrizSiguiente;
    }
    
    
}
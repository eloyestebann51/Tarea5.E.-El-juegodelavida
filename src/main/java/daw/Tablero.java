package daw;

import java.util.Random;

public class Tablero {

    private int n;//n = tamaño
    private int[][] matrizActual;
    private int[][] matrizSiguiente;

    public Tablero() {
    }

    public Tablero(int n, int[][] matrizActual, int[][] matrizSiguiente) {
        this.n = n;
        this.matrizActual = matrizActual;
        this.matrizSiguiente = matrizSiguiente;
    }

    public void IniciarTablero(double porcentajeCelulasVivas) {
        Random generarAleatorio = new Random();

        //Calculo de las celulas vivas
        int totalCasillas = n * n;
        int celulasVivas = (int) porcentajeCelulasVivas * (totalCasillas / 100);

        //Colocacion celulas aleatoriamente
        int fila, columna;
        for (int i = 0; i < celulasVivas; i++) {
            do {
                fila = generarAleatorio.nextInt(n);//(2)
                columna = generarAleatorio.nextInt(n);//(3)
                //si es la misma posicon que la anterior vez vuelve a generar
            } while (matrizActual[fila][columna] == 1);
            //guarda el valor habiendo comprobado previamente
            matrizActual[fila][columna] = 1;
        }
    }
}

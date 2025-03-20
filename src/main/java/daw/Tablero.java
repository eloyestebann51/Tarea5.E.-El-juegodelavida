package daw;

import java.util.Random;

public class Tablero {

    private int n;//n = tamaño
    private int[][] matrizActual;
    private int[][] matrizSiguiente;

    public Tablero() {
    }

    public Tablero(int n, double porcentajeVivas) {
        this.n = n;
        this.matrizActual = new int[n][n];
        this.matrizSiguiente = new int[n][n];
        IniciarTablero(porcentajeVivas);
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

    //metodo para mostrar tablero
    public void mostrarTablero() {
        //n = (matrizActual.length)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrizActual[i][j] + " ");
            }
            System.out.println("");
        }
    }

    //metodo para contar vecinas
    public int contarVecina(int fila, int columna) {
        int cantidadVivasAlrededor = 0;//contador de vivas
        //matriz de las posiciones a comprobar
        int[][] direcciones = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        //for para comprobar las posiciones vecinas
        for (int[] direccion : direcciones) {
            //a la posicionActual le suma la posicion a comprobar 
            int i = fila + direccion[0];
            int j = columna + direccion[1];
            if (matrizActual[i][j] == 1) {
                cantidadVivasAlrededor++;
            }
        }
        return cantidadVivasAlrededor;
    }

    //metodo copiar matriz 
    public void copiarMatriz() {
        //n = (matrizActual.length)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizActual[i][j] = matrizSiguiente[i][j];
            }
        }
    }

    public int[][] getMatriz() {
        return matrizActual;
    }

    public void generarSiguienteGeneracion() {
        //n = (matrizActual.length)
        //=1 para que no compruebe los bordes
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                //comprueba las posiciones y guarda la cantidad de vecinas
                int cantidadVivasAlrededor = contarVecina(i, j);
                if (matrizActual[i][j] == 1) { //celulas vivas
                    if (cantidadVivasAlrededor == 2 || cantidadVivasAlrededor == 3) {
                        matrizSiguiente[i][j] = 1;//vive
                    } else if (cantidadVivasAlrededor > 3) {
                        matrizSiguiente[i][j] = 0;//muere
                    } else if (cantidadVivasAlrededor == 0 || cantidadVivasAlrededor == 1) {
                        matrizSiguiente[i][j] = 0;//muere
                    }
                } else { //celulas muertas
                    if (cantidadVivasAlrededor == 3) {
                        matrizSiguiente[i][j] = 1; //revive
                    }
                }
            }
        }
        /*
            Como trabajamos con matrizSiguiente para no modificar la matrizActual
            en todo el proceso, al final copiamos los valores acrtualizados de matrizSiguiente
            a la matrizActual. De esta forma estudiamos la matriz actual sin modificarla en 
            ningun momento.
        */
        copiarMatriz();
    }
}

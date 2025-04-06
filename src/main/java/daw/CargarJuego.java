package daw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarJuego {

    // Clase JuegoCargado se convierte en estatica para poder agregar el metodo cargarPartida.
    public static class JuegoCargado {

        public Tablero tablero;
        public int numeroGeneracion;
        public List<Integer> celulasVivasRondas;

        // Constructor
        public JuegoCargado(Tablero tablero, int numeroGeneracion, List<Integer> celulasVivasRondas) {
            this.tablero = tablero;
            this.numeroGeneracion = numeroGeneracion;
            this.celulasVivasRondas = celulasVivasRondas;
        }

        // Metodo no estatico cargarPartida dentro de JuegoCargado
        public static JuegoCargado cargarPartida(String nombreArchivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo + ".txt"))) {
                // Leer dimensiones
                String[] dimensiones = reader.readLine().split(" ");
                int n = Integer.parseInt(dimensiones[0]);

                // Leer numero de generacion
                int numeroGeneracion = Integer.parseInt(reader.readLine());

                // Leer estado de la matriz
                Celula[][] matriz = new Celula[n][n];
                for (int i = 0; i < n; i++) {
                    String[] fila = reader.readLine().split(" ");
                    for (int j = 0; j < n; j++) {
                        matriz[i][j] = new Celula();
                        matriz[i][j].setViva(fila[j].equals("1"));
                    }
                }

                // Leer lista de celulas vivas por ronda
                List<Integer> celulasVivasRondas = new ArrayList<>();
                String[] celulasVivas = reader.readLine().split(" ");
                for (String celula : celulasVivas) {
                    celulasVivasRondas.add(Integer.parseInt(celula));
                }

                // Crear el tablero y copiar el estado de las celulas
                Tablero tablero = new Tablero(n, 0); // crea un tablero de tamano n con 0% de celulas vivas
                Celula[][] actual = tablero.getMatrizActual();

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        actual[i][j].setViva(matriz[i][j].getViva());
                    }
                }

                // Devolver el objeto JuegoCargado con todos los datos actualizados
                return new JuegoCargado(tablero, numeroGeneracion, celulasVivasRondas);

            } catch (IOException e) {
                System.out.println("Error al cargar la partida: " + e.getMessage());
                return null;
            }
        }
    }
}

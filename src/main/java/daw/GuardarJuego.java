package daw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GuardarJuego {
    public static void guardarEstadoJuego(Tablero tablero, int numeroGeneracion, List<Integer> celulasVivasRondas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo para guardar la partida: ");
        String nombreArchivo = scanner.next();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo + ".txt"))) {
            int n = tablero.getMatrizActual().length;
            
            // Guardar dimensiones del tablero
            writer.write(n + " " + n);
            writer.newLine();
            
            // Guardar numero de generacion
            writer.write(String.valueOf(numeroGeneracion));
            writer.newLine();
            
            // Guardar estado de la matriz
            Celula[][] matriz = tablero.getMatrizActual();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(matriz[i][j].getViva() ? "1 " : "0 ");
                }
                writer.newLine();
            }
            
            // Guardar celulas vivas por generacion
            for (int celulasVivas : celulasVivasRondas) {
                writer.write(celulasVivas + " ");
            }
            writer.newLine();
            
            writer.flush();
            System.out.println("Partida guardada exitosamente en " + nombreArchivo + ".txt");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }
}

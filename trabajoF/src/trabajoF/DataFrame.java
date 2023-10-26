package trabajoF;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFrame {

	   public static void main(String[] args) throws CSVInvalidException {
	        LectorCSV lector = new LectorCSV();
	        lector.leerCSV();

	        // Visualizar los datos del CSV
	        lector.visualizarDatos();
	    }


 protected Map<String, List<String>> columnas;
 protected List<String> etiquetasColumnas;

 public DataFrame() {
     columnas = new HashMap<>();
     etiquetasColumnas = new ArrayList<>();
 }

 public DataFrame(boolean header, List<String> etiquetasPersonalizadas) {
     columnas = new HashMap<>();
     if (header) {
         etiquetasColumnas = etiquetasPersonalizadas;
         for (String etiqueta : etiquetasColumnas) {
             columnas.put(etiqueta, new ArrayList<>());
         }
     }
 }

 public void ordenarPorColumna(String nombreColumna) {
     if (columnas.containsKey(nombreColumna)) {
         List<String> columna = columnas.get(nombreColumna);
         columna.sort(Comparator.naturalOrder()); // Ordenar en orden ascendente
     }
 }

 public void visualizarDatos() {
     if (!etiquetasColumnas.isEmpty()) {
         // Ordenar los datos por la primera columna en orden ascendente
         ordenarPorColumna(etiquetasColumnas.get(0));

         // Obtener el ancho máximo de cada columna
         Map<String, Integer> anchosColumnas = new HashMap<>();
         for (String etiqueta : etiquetasColumnas) {
             int anchoMaximo = etiqueta.length();
             for (String valor : columnas.get(etiqueta)) {
                 anchoMaximo = Math.max(anchoMaximo, valor.length());
             }
             anchosColumnas.put(etiqueta, anchoMaximo);
         }

         // Imprimir las etiquetas de columna con separadores y barras horizontales
         for (String etiquetaColumna : etiquetasColumnas) {
             System.out.printf("%-" + (anchosColumnas.get(etiquetaColumna) + 1) + "s|", etiquetaColumna);
         }
         System.out.println();

         for (String etiquetaColumna : etiquetasColumnas) {
             for (int i = 0; i < anchosColumnas.get(etiquetaColumna) + 1; i++) {
                 System.out.print("-");
             }
             System.out.print("+");
         }
         System.out.println();

         int numRows = etiquetasColumnas.isEmpty() ? 0 : columnas.get(etiquetasColumnas.get(0)).size();
         for (int i = 0; i < numRows; i++) {
             for (String etiquetaColumna : etiquetasColumnas) {
                 String valor = columnas.get(etiquetaColumna).get(i);
                 System.out.printf("%-" + (anchosColumnas.get(etiquetaColumna) + 1) + "s|", valor);
             }
             System.out.println();
         }
     }
 }

 public List<String> getColumna(String nombreColumna) {
     if (columnas.containsKey(nombreColumna)) {
         return columnas.get(nombreColumna);
     }
     return new ArrayList<>(); // Devolver una lista vacía si no se encuentra la columna
 }
}

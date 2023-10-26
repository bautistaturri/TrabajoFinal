package trabajoF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;

public class LectorCSV extends DataFrame {
    public LectorCSV() {
        super(); // Llama al constructor de la clase base (DataFrame)
    }

    
    
    
    //Se utiliza throws  CSVInvalidException para tener un mejor manejo de las
    // excepciones
    public void leerCSV() throws CSVInvalidException {
        Path filePath = Paths.get("C:\\Users\\M\\Desktop\\TrabajoFinal\\pruebax.csv");
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String linea;
            boolean primeraLinea = true;
            int cantidadColumnas = 0;

            while ((linea = br.readLine()) != null) {
                String[] datosDeLinea = linea.split(",");

                if (primeraLinea) {
                    etiquetasColumnas = new ArrayList<>(Arrays.asList(datosDeLinea));
                    cantidadColumnas = etiquetasColumnas.size();
                    for (String etiqueta : etiquetasColumnas) {
                        columnas.put(etiqueta, new ArrayList<>());
                    }
                    primeraLinea = false;
                } else {
                    if (datosDeLinea.length != cantidadColumnas) {
                        throw new CSVInvalidException("La línea '" + linea + "' contiene una cantidad incorrecta de columnas.");
                    }
                    for (int i = 0; i < datosDeLinea.length; i++) {
                        columnas.get(etiquetasColumnas.get(i)).add(datosDeLinea[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



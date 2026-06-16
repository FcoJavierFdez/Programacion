package Exàmens.COLAS;

import java.io.*;
import java.util.*;

public class ExamenColaDirectorios {
    public static void main(String[] args) {
        // Implementamos la cola con LinkedList tal como exige la UF05
        Queue<File> colaTareas = new LinkedList<>();

        // Ruta relativa por defecto si el examen pide automatizar
        String rutaCarpeta = "./tareas_pendientes";
        File carpeta = new File(rutaCarpeta);

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("[ERROR] Crea la carpeta './tareas_pendientes' para ejecutar el examen.");
            return;
        }

        // CARGAR FICHEROS DIRECTAMENTE EN LA COLA (Queue)
        File[] archivosEncontrados = carpeta.listFiles();
        if (archivosEncontrados != null) {
            for (File f : archivosEncontrados) {
                if (f.isFile() && f.getName().endsWith(".txt")) {
                    colaTareas.add(f); // Se encola el archivo entero al final (FIFO)
                }
            }
        }

        System.out.println("Tareas iniciales encoladas: " + colaTareas.size());

        // ARCHIVO DE SALIDA (Historial de procesamiento)
        File archivoHistorial = new File(carpeta, "historico_tareas.log");

        // PROCESAMIENTO DE LA COLA CON BUFFEREDWRITER (Modo Append activado con 'true')
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoHistorial, true))) {

            // CASUÍSTICA: Iterar la cola hasta que se vacíe por completo
            while (!colaTareas.isEmpty()) {
                // Sacamos el primer archivo de la cola (poll)
                File tareaActual = colaTareas.poll();
                System.out.println("\nProcesando el archivo de tarea: " + tareaActual.getName());

                bw.write("INICIO PROCESO: " + tareaActual.getName());
                bw.newLine();

                // LECTURA DEL CONTENIDO INTERNO DEL ARCHIVO EXTRAÍDO DE LA COLA
                try (BufferedReader br = new BufferedReader(new FileReader(tareaActual))) {
                    String lineaInterna;
                    while ((lineaInterna = br.readLine()) != null) {
                        // Procesamos la línea simulando una operación de examen
                        bw.write(" -> Contenido procesado: " + lineaInterna.toUpperCase());
                        bw.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("[ERROR] No se pudo leer el contenido de " + tareaActual.getName());
                }

                // Borramos el archivo procesado de la carpeta para simular que ha salido del
                // buzón
                if (tareaActual.delete()) {
                    System.out.println("Archivo eliminado de pendientes físicamente.");
                }

                bw.write("FIN PROCESO: " + tareaActual.getName());
                bw.newLine();
                bw.write("------------------------------------");
                bw.newLine();
            }

            bw.flush(); // Asegurar datos en disco
            System.out.println("\n[ÉXITO] Todas las tareas de la cola han sido procesadas.");
            System.out.println("Historial actualizado en: " + archivoHistorial.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("[ERROR CRÍTICO] No se pudo abrir el archivo de log histórico.");
        }
    }
}
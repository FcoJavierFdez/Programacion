package ARCHIVOS;

import java.io.File;

public class ModificadorEstructura {

    public static void main(String[] args) {
        
        System.out.println("=== Iniciant procés de modificació ===");

        // 1. CANVI DE NOMS DE LES CARPETES
        // Definim la ruta origen relativa a la carpeta de treball
        File docOriginal = new File("Documentos");
        File docNou = new File("DOCS");
        
        // Canviem el nom de la carpeta principal
        if (docOriginal.renameTo(docNou)) {
            System.out.println("S'ha renomenat 'Documentos' a 'DOCS'.");
        } else {
            System.out.println("Avís: No s'ha pogut renomenar 'Documentos' (potser ja es diu 'DOCS' o no existeix).");
        }
        
        // Ara les rutes d'origen de les subcarpetes han de començar per 'DOCS/'
        File fotosOriginal = new File("DOCS/Fotografias");
        File fotosNou = new File("DOCS/FOTOS");
        fotosOriginal.renameTo(fotosNou); //
        
        File llibresOriginal = new File("DOCS/Libros");
        File llibresNou = new File("DOCS/LECTURES");
        llibresOriginal.renameTo(llibresNou); //

        // 2. ELIMINAR LES EXTENSIONS DELS ARXIUS
        // Cridem a una funció auxiliar per fer neteja dels arxius que hi ha dins
        llevarExtensioArxius(fotosNou);
        llevarExtensioArxius(llibresNou);
        
        System.out.println("=== Procés finalitzat ===");
    }

    /**
     * Recorre un directori i lleva l'extensió de tots els arxius que conté.
     * @param directori Objecte File que representa la carpeta a processar.
     */
    public static void llevarExtensioArxius(File directori) {
        // Comprovem primer si la ruta indicada existeix i efectivament és una carpeta
        if (directori.exists() && directori.isDirectory()) {
            
            // listFiles() retorna un vector amb tots els elements de la carpeta
            File[] llista = directori.listFiles(); 
            
            if (llista != null) {
                for (int i = 0; i < llista.length; i++) {
                    File element = llista[i];
                    
                    // Ens assegurem que l'element és un fitxer i no una altra subcarpeta
                    if (element.isFile()) {
                        String nomSencer = element.getName(); // Obtenim el nom de l'arxiu
                        
                        // Busquem l'últim punt per separar el nom de l'extensió
                        int posPunt = nomSencer.lastIndexOf('.');
                        
                        // Si l'arxiu té una extensió (el punt no està a la posició 0 ni és inexistent)
                        if (posPunt > 0) {
                            // Extraiem només la part del nom
                            String nomSenseExtensio = nomSencer.substring(0, posPunt);
                            
                            // Construïm la nova ruta: la mateixa carpeta pare + el nou nom
                            File desti = new File(directori.getPath() + "/" + nomSenseExtensio);
                            
                            // Per moure l'element sense treure'l de la seua carpeta, li assignem el nou nom
                            if (element.renameTo(desti)) {
                                System.out.println("Renomenat: " + nomSencer + " -> " + nomSenseExtensio);
                            }
                        }
                    }
                }
            }
        }
    }
}
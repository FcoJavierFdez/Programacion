package TRANSIT;

import TRANSIT.Semafor.ColorSemafor;

public class ControlTransit {
    public static void main(String[] args) {
        // 1. Instanciar el semàfor
        Semafor miSemafor = new Semafor();

        // 2. Verificar estat inicial (hauria de ser ROIG -> "Parar")
        System.out.print("Estat inicial: ");
        miSemafor.instruccioConductor();

        // 3. Canviar a VERD i mostrar instrucció
        System.out.print("Canvi a VERD: ");
        miSemafor.canviarEstat(ColorSemafor.VERD);
        miSemafor.instruccioConductor();

        // 4. Canviar a GROC i mostrar instrucció
        System.out.print("Canvi a GROC: ");
        miSemafor.canviarEstat(ColorSemafor.GROC);
        miSemafor.instruccioConductor();
    }
}
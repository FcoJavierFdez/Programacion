package com.restaurant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import java.util.List;

class AppTest {

    // ==========================================
    // 1. HERÈNCIA I ABSTRACCIÓ: 2 punts
    // ==========================================
    @Test
    void test01_AbstraccioProducte() {
        assertTrue(Modifier.isAbstract(Producte.class.getModifiers()), 
            "La classe Producte ha de ser abstracta.");
    }

    @Test
    void test02_HerenciaClasses() {
        assertEquals(Producte.class, PlatPrincipal.class.getSuperclass(), 
            "PlatPrincipal ha d'heretar de Producte");
        assertEquals(Producte.class, Beguda.class.getSuperclass(), 
            "Beguda ha d'heretar de Producte");
        assertEquals(PlatPrincipal.class, PlatVegetaria.class.getSuperclass(), 
            "PlatVegetaria ha d'heretar de PlatPrincipal");
    }

    // ==========================================
    // 2. INTERFÍCIES I MARCADORS: 2 punts
    // ==========================================
    @Test
    void test03_DefinicioInterficies() {
        assertTrue(Facturable.class.isInterface(), "Facturable ha de ser una interfície.");
        assertTrue(Vegetaria.class.isInterface(), "Vegetaria ha de ser una interfície marcadora.");
        assertTrue(SenseGluten.class.isInterface(), "SenseGluten ha de ser una interfície marcadora.");
    }

    @Test
    void test04_ClassesAmbMarcadors() {
        PlatVegetaria pv = new PlatVegetaria("Amanida", 5.0);
        Beguda b = new Beguda("Aigua", 1.5);
        PlatPrincipal pp = new PlatPrincipal("Macarrons", 8.0);

        assertTrue(pv instanceof Vegetaria, "PlatVegetaria ha d'implementar Vegetaria.");
        assertTrue(b instanceof SenseGluten, "Beguda ha d'implementar SenseGluten.");
        assertTrue(pv instanceof Facturable, "PlatVegetaria ha de ser Facturable.");
        assertTrue(pp instanceof Facturable, "PlatPrincipal ha de ser Facturable.");
        assertTrue(b instanceof Facturable, "Beguda ha de ser Facturable.");
        assertFalse(pp instanceof Vegetaria, "PlatPrincipal estàndard NO ha de ser Vegetaria.");
    }

    // ==========================================
    // 3. CÀLCUL D'IVA: 2 punts
    // ==========================================
    @Test
    void test05_IvaPlatPrincipal_i_Vegetaria() {
        PlatPrincipal p = new PlatPrincipal("Test Plat", 10.0);
        PlatVegetaria pv = new PlatVegetaria("Test Veggie", 10.0);
        
        assertEquals(11.0, p.calcularPreuAmbIVA(), 0.001, 
            "L'IVA del PlatPrincipal ha de ser del 10% (preu * 1.10).");
        assertEquals(11.0, pv.calcularPreuAmbIVA(), 0.001, 
            "PlatVegetaria ha d'heretar el comportament correcte del càlcul d'IVA (10%).");
    }

    @Test
    void test06_IvaBeguda() {
        Beguda b = new Beguda("Test Beguda", 10.0);
        assertEquals(12.1, b.calcularPreuAmbIVA(), 0.001, 
            "L'IVA de la Beguda ha de ser del 21% (preu * 1.21).");
    }

    // ==========================================
    // 4. GESTIÓ D'EXCEPCIONS: 2 punts
    // ==========================================
    @Test
    void test07_EstructuraExcepcio() {
        assertTrue(RuntimeException.class.isAssignableFrom(SenseEstocException.class) || Exception.class.isAssignableFrom(SenseEstocException.class), 
            "SenseEstocException ha d'heretar de RuntimeException o al menys d'Exception.");
    }

    @Test
    void test08_LlançamentExcepcio() {
        Comanda c1 = new Comanda(1);
        c1.afegirProducte(new PlatPrincipal("Entrecot de vedella", 22.0));
        
        assertThrows(SenseEstocException.class, () -> c1.prepararComanda(), 
            "S'ha de llançar SenseEstocException si el nom del producte conté 'Entrecot'.");
            
        Comanda c2 = new Comanda(2);
        c2.afegirProducte(new PlatPrincipal("Llentilles", 12.0));
        
        assertDoesNotThrow(() -> c2.prepararComanda(), 
            "No s'ha de llançar cap excepció si hi ha estoc (no és 'entrecot').");
    }

    // ==========================================
    // 5. POLIMORFISME I LÒGICA: 2 punts
    // ==========================================
    @Test
    void test09_PolimorfismeLlistaComanda() {
        Comanda c = new Comanda(3);
        
        Producte p1 = new PlatPrincipal("Hamburguesa", 10.0); 
        Producte p2 = new Beguda("Cola", 2.0);                
        
        c.afegirProducte(p1);
        c.afegirProducte(p2);
        
        List<Producte> llista = c.getProductes();
        assertEquals(2, llista.size(), "La comanda ha de contenir correctament els productes afegits.");
        
        double total = 0;
        for (Producte prod : llista) {
            if (prod instanceof Facturable) {
                total += ((Facturable) prod).calcularPreuAmbIVA();
            }
        }
        
        assertEquals(13.42, total, 0.001, 
            "El càlcul del total iterant de forma polimòrfica ha de processar l'IVA de cada tipus correctament.");
    }
}

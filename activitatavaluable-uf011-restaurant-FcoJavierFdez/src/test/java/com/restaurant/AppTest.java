package com.restaurant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class AppTest {

    private Beguda[] arrayBegudes = {
        new Beguda("Aigua mineral", 1.20),
        new Beguda("Aigua amb gas", 1.50),
        new Beguda("Cervesa", 2.50),
        new Beguda("Cervesa sense alcohol", 2.30),
        new Beguda("Vi negre", 3.80),
        new Beguda("Vi blanc", 3.50),
        new Beguda("Vi rosat", 3.60),
        new Beguda("Orxata", 2.80),
        new Beguda("Granissat de llima", 2.50),
        new Beguda("Granissat de cafè", 2.70),
        new Beguda("Refresc de cola", 2.20),
        new Beguda("Refresc de taronja", 2.20),
        new Beguda("Refresc de llima", 2.20),
        new Beguda("Cafè amb llet", 1.80),
        new Beguda("Cafè tallat", 1.50)
    };

    private PlatPrincipal[] arrayPlats = {
        new PlatPrincipal("Paella valenciana", 12.50),
        new PlatPrincipal("Arròs al forn", 10.80),
        new PlatPrincipal("Fideuà", 11.90),
        new PlatPrincipal("All i pebre", 13.20),
        new PlatPrincipal("Pollastre al forn", 9.80),
        new PlatPrincipal("Mandonguilles amb tomaca", 9.50),
        new PlatVegetaria("Esgarraet", 8.00),
        new PlatVegetaria("Truita de creïlles", 7.50),
        new PlatVegetaria("Arròs amb bolets", 8.70),
        new PlatVegetaria("Lasanya vegetal", 7.80),
        new PlatVegetaria("Amanida valenciana", 6.90),
        new PlatPrincipal("Ternera a la brasa", 14.50),
        new PlatPrincipal("Sepia a la planxa", 12.00),
        new PlatPrincipal("Calamars a la romana", 10.50),
        new PlatPrincipal("Hamburguesa completa", 9.90)

    };
    private ArrayList<Beguda> begudes = new ArrayList<>(Arrays.asList(arrayBegudes));
    private ArrayList<PlatPrincipal> plats = new ArrayList<>(Arrays.asList(arrayPlats));

    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        App.sc = new Scanner(System.in);
    }

    // ---------- HELPERS ----------

    private void setInput(String data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        App.sc = new Scanner(System.in);
    }

    // ==================================================
    // 1. CONEXIÓ CORRECTA AMB LA BASE DE DADES: 1 punts
    // ==================================================
    
    @Test
    @DisplayName("test01_ConnexioCorrecta")
    void test01_ConnexioCorrecta() {
        Connection conn = assertDoesNotThrow(
            () -> App.connectarBBDD("jdbc:mysql://localhost:3306/hort_i_cuina", "root", ""),
            "La connexió ha llançat una excepció inesperada."
        );

        assertNotNull(conn, "L'objecte Connection no s'ha generat correctament.");     
    }

    // ==========================================
    // 2. CARREGA BEGUDES CORRECTAMENT: 1 punts
    // ==========================================

    @Test
    @DisplayName("test02_BegudesCarregades")
    void test02_BegudesCarregades() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hort_i_cuina", "root", "")){
            ArrayList<Beguda> begudesCarregades = App.getBegudes(con);
            
            for (Beguda b : begudes)
            assertTrue(isContained(b, begudesCarregades), 
                "Hi ha begudes no carregades.");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("El test ha donat uns excepcion no esperada i les begudes no s'han carregat correctament");
        }    
    }

    // ==========================================
    // 3. CARREGA PLATS CORRECTAMENT: 2 punts
    // ==========================================
    
    @Test 
    @DisplayName("test03_PlatsCarregats")
    void test03_PlatsCarregats() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hort_i_cuina", "root", "")){
            ArrayList<PlatPrincipal> platsCarregats = App.getPlats(con);
            
            for (PlatPrincipal p : plats)
            assertTrue(isContained(p, platsCarregats), 
                "Hi ha plats no carregats.");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("El test ha donat uns excepcion no esperada i els plats no s'han carregat correctament");
        }    
    }

    @Test
    @DisplayName("test04_PlatsVegetariansCreats")
    void test04_PlatsVegetariansCreats(){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hort_i_cuina", "root", "")){
            ArrayList<PlatPrincipal> platsCarregats = App.getPlats(con);
            assertTrue(contarVegetarians(platsCarregats),
                "Recorda que hi ha plats que son vegetarians i s'han d'instanciar com a tal");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("El test ha donat uns excepcion no esperada i els plats no s'han carregat correctament");
        }  
    }
    
    // ========================================
    // 4. CREA EL FITXER CORRECTAMENT: 1 punts
    // ========================================
    
    @Test
    @DisplayName("test05_FitxerCreat")
    void test05_FitxerCreat() {
        File f = new File("TicketCreat.txt");
        if (f.exists())
            f.delete();
        Comanda c = new Comanda(1);
        c.afegirProducte(new PlatPrincipal("Arròs al forn", 10.80));
        App.imprimirTicket(c,"TicketCreat.txt");
        
        assertTrue(new File("TicketCreat.txt").exists());
    }

    // ====================================================
    // 5. MOSTRA CORRECTAMENT LA DUPLA (NOM-PREU): 1.5 punts
    // ====================================================
    
    @Test
    @DisplayName("test06_DuplesNomPreu")
    void test06_DuplesNomPreu() {
        File f = new File("TicketNom-Preu.txt");
        if (f.exists())
            f.delete();

        Comanda c = new Comanda(2);
        PlatVegetaria pv = new PlatVegetaria("Lasanya vegetal", 7.80);
        Beguda b = new Beguda("Orxata", 2.80);
        c.afegirProducte(pv);
        c.afegirProducte(b);
        App.imprimirTicket(c,"TicketNom-Preu.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            br.readLine(); // FIRST LINE "TICKET DE COMPRA"
            
            // NOM DEL PLAT
            String platLine = br.readLine();
            assertTrue(platLine.contains("(V)"),"Els plats vegetarians han de ser indicats com a tal en el ticket");

            StringTokenizer st = new StringTokenizer(platLine,":");
            st.nextToken();
            String platName = st.nextToken().trim();
            assertTrue(existeixProducte(platName), "El producte amb nom "+platName+" no està en la carta");
            double preuTeoricPlat = pv.calcularPreuAmbIVA();

            // PREU DEL PLAT
            String preuPlat = br.readLine();
            st = new StringTokenizer(preuPlat,":");
            st.nextToken();
            String platPreu = st.nextToken().trim();

            assertTrue(String.format("%.2f",preuTeoricPlat).equals(platPreu),"El preu no es correson amb el de la carta");

            // NOM DE LA BEGUDA
            String begudaLine = br.readLine();
            st = new StringTokenizer(begudaLine,":");
            st.nextToken();
            String begudaName = st.nextToken().trim();
            assertTrue(existeixProducte(begudaName),"El producte amb nom "+begudaName+" no està en la carta");
            double preuTeoricBeguda = b.calcularPreuAmbIVA();

            // PREU DE LA BEGUDA
            String preuBeguda = br.readLine();
            st = new StringTokenizer(preuBeguda,":");
            st.nextToken();
            String begudaPreu = st.nextToken().trim();

            assertTrue(String.format("%.2f",preuTeoricBeguda).equals(begudaPreu),"El preu no es correson amb el de la carta");

        } catch (IOException e) {
            fail("El test ha donat uns excepcion no esperada: L'arxiu no s'ha creat correctament");
        }
    }

    // ==========================================
    // 6. CALCULA EL TOTAL CORRECTAMENT: 1.5 punts
    // ==========================================
    
    @Test
    @DisplayName("test07_TotalCorrecte")
    void test07_TotalCorrecte() {
        File f = new File("TicketTotal.txt");
        if (f.exists())
            f.delete();

        Comanda c = new Comanda(3);
        PlatPrincipal pp = new PlatPrincipal("Mandonguilles amb tomaca", 9.50);
        Beguda b = new Beguda("Aigua amb gas", 1.50);
        c.afegirProducte(pp);
        c.afegirProducte(b);
        App.imprimirTicket(c,"TicketTotal.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = "";
            while((linea = br.readLine()) != null){
                if (linea.startsWith("Total")){
                    StringTokenizer st = new StringTokenizer(linea, ":");
                    st.nextToken();
                    String totalPreu = st.nextToken().trim();
                    double totalTeoric = pp.calcularPreuAmbIVA() + b.calcularPreuAmbIVA();
                    assertTrue(String.format("%.2f",totalTeoric).equals(totalPreu),"El total no s'ha calculat correctament");
                }
            }
            
        } catch (IOException e) {
            fail("El test ha donat uns excepcion no esperada: L'arxiu no s'ha creat correctament");
        }
    }

    // =====================================================
    // 7. GESTIONA EL ERRORS SQL/INPUT/IOEXCEPTION: 2 punts
    // =====================================================
    
    @Test // SQLException
    @DisplayName("test08_ExcepcioNoExisteixDB")
    void test08_ExcepcioNoExisteixDB(){
        assertThrows(SQLException.class, () -> {
            App.connectarBBDD("jdbc:mysql://localhost:3306/fakeDB","root","");
            }, "S'ha de llançar l'excepció SQLException si la conexió no es valida");
    }

    @Test // InputMismatchException
    @DisplayName("test09_ExcepcioNumberFormat")
    void test09_ExcepcioNumberFormat(){
        setInput("FAIL\nFAIL\n1\n1\nn\n");
        System.out.println(App.sc.next());
        assertDoesNotThrow( () -> App.main(new String[0]), 
            "Has de controlar l'execució si l'usuari introdueix text en lloc d'un número");
    }

    private boolean isContained(Producte p, ArrayList<? extends Producte> productes) {
        for (Producte prod : productes){
            if (p.getNom().equals(prod.getNom()) && p.getPreuBase() == prod.getPreuBase())
                return true;
        }
        return false;
    }

    private boolean contarVegetarians(ArrayList<PlatPrincipal> plats){
        int veggie = 0;
        for (PlatPrincipal pp : plats)
            if (pp instanceof PlatVegetaria)
                veggie++;
        
        return veggie == 5;
    }

    private boolean existeixProducte(String nom) {
        ArrayList<Producte> productes = new ArrayList<>(begudes);
        productes.addAll(plats);

        for (Producte p : productes)
            if (p.getNom().equals(nom))
                return true;

        return false;

    }

}
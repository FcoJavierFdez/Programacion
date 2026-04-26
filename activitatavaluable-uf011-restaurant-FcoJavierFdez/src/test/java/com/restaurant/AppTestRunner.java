package com.restaurant;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class AppTestRunner {

    public static void main(String[] args) {
        HashMap<String,Double> puntuacions = new HashMap<>();
        puntuacions.put("test01_ConnexioCorrecta",1.0);
        puntuacions.put("test02_BegudesCarregades",1.0);
        puntuacions.put("test03_PlatsCarregats",1.0);
        puntuacions.put("test04_PlatsVegetariansCreats",1.0);
        puntuacions.put("test05_FitxerCreat",1.0);
        puntuacions.put("test06_DuplesNomPreu",1.5);
        puntuacions.put("test07_TotalCorrecte",1.5);
        puntuacions.put("test08_ExcepcioNoExisteixDB",1.0);
        puntuacions.put("test09_ExcepcioNumberFormat",1.0);
        
        DoubleAdder puntuacioTotal = new DoubleAdder();
        puntuacioTotal.add(10.0);
        AtomicInteger passed = new AtomicInteger(9);
        StringBuilder sb = new StringBuilder();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(AppTest.class))
                .build();

        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        // Resum
        TestExecutionSummary summary = listener.getSummary();
        System.out.println("\n\n********************************************************");
        System.out.println("***************** RESULTATS DELS TESTS *****************");
        System.out.println("********************************************************\n");
        
        summary.getFailures().forEach(f -> {
            String nom = f.getTestIdentifier().getDisplayName();
            sb.append("+ " + nom + ": " + f.getException().getMessage()+"\n");
            puntuacioTotal.add(-puntuacions.get(nom));
            passed.addAndGet(-1);
        });

        if (passed.get() != 9){
            System.out.println("***************************");
            System.out.println("**** TESTS NO SUPERATS ****");
            System.out.println("***************************");
            System.out.println(sb.toString());
        }

        // Mostrar puntuació
        System.out.println("\n*****************************");
        System.out.println("**** RESULTAT DELS TESTS ****");
        System.out.println("*****************************");
        System.out.println("Tests Totals: 9");
        System.out.println("Tests superats: " + passed.get());
        System.out.println("Tests no superats: " + (9-passed.get()));
        System.out.println("PUNTUACIÓ FINAL: " + puntuacioTotal.doubleValue() + "/10.0");

    }
}
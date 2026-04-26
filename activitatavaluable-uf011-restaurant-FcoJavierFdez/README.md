[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ImIiLMQV)
# Activitat avaluable UF11 — L’Hort i la Cuina

Continuació de l’activitat avaluable anterior del restaurant, ara ampliada amb **persistència de dades** i **accés a base de dades des de Java**.

## Projecte: L’Hort i la Cuina (sistema Gamma)
![ImgProjecte](./src/img/avaluable2.png)

# Activitat avaluable UF11 — L’Hort i la Cuina

Continuació de l’activitat avaluable anterior del restaurant, ara ampliada amb **persistència de dades** i **accés a base de dades des de Java**.

## Brief del client

**De:** Miquel Roca (Director d’Operacions – Grup de Restauració *L’Hort i la Cuina*)  
**Per a:** Equip de Desenvolupament  
**Assumpte:** Nova versió del sistema – càrrega del catàleg des de base de dades i emissió de ticket  

Hola equip,

La beta anterior del sistema va quedar operativa i ja podem gestionar productes, comandes i càlculs bàsics. Ara necessitem fer el següent pas: **deixar de crear els productes manualment dins del codi** i començar a llegir-los des d’una **base de dades ja preparada**.

En aquesta nova versió, el restaurant vos proporcionarà una base de dades ja creada i penjada a l’Aula Virtual. El vostre programa haurà de connectar-se a eixa base de dades, llegir el catàleg de plats i begudes, mostrar-lo per consola i permetre que l’usuari cree una comanda triant un plat i una beguda.

Quan la comanda estiga feta, el sistema haurà de generar un fitxer de text amb el ticket de compra.

---

## Context funcional i Objectius

Partiu del projecte de l’activitat anterior. Podeu reutilitzar les classes ja implementades (`Producte`, `Beguda`, `PlatPrincipal`, `Comanda`, etc.) i continuar fent servir la lògica de negoci que ja teniu creada. Els productes ja no s’han de definir a mà, sinó que s’han de carregar des de la base de dades.

L'objectiu és desenvolupar una aplicació Java que realitze els passos següents:
* Es connecte a una base de dades proporcionada.
* Recupere la informació de plats i begudes transformant cada registre en un objecte.
* Mostre les opcions per consola amb el seu identificador, nom, si és vegetarià (en cas dels plats) i preu base.
* Permeta a l’usuari triar un plat i una beguda introduint el número corresponent.
* Cree una comanda amb eixos productes utilitzant el model d'objectes previ.
* Calcule el total i mostre per consola un resum amb el preu amb IVA de cada producte.
* Genere un fitxer `ticket.txt` amb el resultat final.

---

## Requisits tècnics i d'avaluació automàtica

Per tal que el programa puga ser corregit automàticament, s'han de respectar **estrictament** les signatures següents a la classe `App.java`:

* **Connexió a la base de dades:** `public static Connection connectarBBDD(String URL, String user, String password) throws SQLException`
* **Recuperació de plats:** `public static ArrayList<PlatPrincipal> getPlats(Connection con)`
* **Recuperació de begudes:** `public static ArrayList<Beguda> getBegudes(Connection con)`
* **Generació del fitxer:** `public static void imprimirTicket(Comanda c, String ruta)`

A més, en la classe `Producte` necessitem que existisquen exactament estos mètodes:
* `getNom(...)`
* `getPreuBase()`

Cal controlar adequadament els errors d'SQL (mitjançant excepcions), errors d'entrada de consola i errors d'escriptura de fitxers. La base de dades no s'ha de crear des del programa; ja es dona preparada.

### Format del fitxer `ticket.txt`
El mètode `imprimirTicket` ha de generar el fitxer amb el format exacte següent:

> TICKET DE COMPRA
> 
> Plat: Calamars a la romana  
> Preu plat: 10.50  
> Beguda: Cervesa  
> Preu beguda: 2.50  
>
> Total: 13.00

---

## Importació de la Base de Dades

L'aplicació necessita la base de dades `hort_i_cuina` per a funcionar. Seguiu aquests passos per a preparar el vostre entorn local:

1. Obri XAMPP Control Panel i arranca Apache i MySQL.
2. Entra en phpMyAdmin i crea la base de dades anomenada `hort_i_cuina`.
3. Ves a la pestanya SQL dins de la nova base de dades.
4. Apega l'script SQL de creació de taules (`Beguda` i `Plat`) i inserció de dades que es proporciona al material de la pràctica (està en aules).
5. Polsa Executar.

---

## Guia: Com executar els tests d'avaluació en local

⚠️ **AVÍS IMPORTANT SOBRE LA CORRECCIÓ:** Per a aquesta activitat, **la correcció automàtica NO s'executarà a GitHub Classroom**. Heu de comprovar que el vostre codi funciona i supera els tests al vostre ordinador. Només quan tingueu la puntuació desitjada en local, haureu de fer el *commit* i *push* per a pujar el codi definitiu.

El projecte inclou un fitxer anomenat `AppTestRunner.java`. Aquest fitxer conté un programa que s'encarrega d'executar totes les proves de la rúbrica i calcular la vostra nota final sobre 10.

### Pas 1: Preparar la Base de Dades
1. Obriu XAMPP (o el vostre servidor local) i arranqueu els serveis d'**Apache** i **MySQL**.
2. Comproveu que la base de dades `hort_i_cuina` està creada i té les dades inserides. Els tests es connectaran a aquesta base de dades.

### Pas 2: Localitzar i executar l'avaluador
1. Obriu el vostre entorn de desenvolupament (VS Code, IntelliJ, Eclipse...).
2. Navegueu per la carpeta del projecte fins a obrir el fitxer `src/test/java/com/restaurant/AppTestRunner.java`.
3. Busqueu el mètode `public static void main(String[] args)`.
4. Polseu el botó de **"Run"** o **"Executar"** que apareix damunt del mètode `main` (igual que feu quan executeu el vostre programa principal `App.java`).

### Pas 3: Interpretar els resultats
* **Si algun test falla:** La consola vos mostrarà una secció anomenada `**** TESTS NO SUPERATS ****` amb una llista dels errors. Llig l'error, ves al teu codi en `App.java`, corregeix-ho i torna a executar el Pas 2.
* **Si tot és correcte:** Voreu que els 9 tests estan superats i la vostra puntuació final és de `10.0/10.0`.

### Pas 4: Pujar el codi a GitHub
Quan el test runner local vos done el vistiplau, guardeu i entregueu la faena:
1. `git add .`
2. `git commit -m "Solució completada i tests superats"`
3. `git push`

---

## Rúbrica d'Avaluació Automàtica (10 punts)

| Test | Criteri d'Avaluació | Descripció de la validació | Punts |
| :--- | :--- | :--- | :---: |
| **`test01`** | **Connexió a la Base de Dades** | El mètode `connectarBBDD` genera un objecte `Connection` vàlid cap a la base de dades `hort_i_cuina` sense llançar excepcions. | **1.0** |
| **`test02`** | **Càrrega de Begudes** | El mètode `getBegudes()` recupera correctament tots els registres de la taula `Beguda` i els instància amb el nom i preu base correctes. | **1.0** |
| **`test03`** | **Càrrega de Plats** | El mètode `getPlats()` recupera correctament tots els registres de la taula `Plat` i instància els objectes amb el nom i preu base correctes. | **1.0** |
| **`test04`** | **Herència i Plats Vegetarians** | Al carregar els plats, s'identifica el camp `vegetaria`. Si és cert, l'objecte instanciat és obligatòriament de la classe filla `PlatVegetaria`. | **1.0** |
| **`test05`** | **Creació del Fitxer** | El mètode `imprimirTicket()` genera físicament el fitxer de text en la ruta especificada amb la informació de la comanda. | **1.0** |
| **`test06`** | **Format del Ticket** | El fitxer conté les dades en el format exacte exigit: mostra el nom, els plats vegetarians inclouen `(V)` i el preu té aplicat l'IVA. | **1.5** |
| **`test07`** | **Càlcul del Total** | El fitxer mostra la suma correcta amb el preu final amb IVA de tots els productes sumats, formatat a 2 decimals. | **1.5** |
| **`test08`** | **Excepcions SQL** | Si s'intenta connectar a una base de dades inexistent, el mètode propaga o llança correctament la `SQLException`. | **1.0** |
| **`test09`** | **Gestió d'Errors per Teclat** | Si l'usuari introdueix text en lloc de números al menú, el programa no peta i gestiona l'excepció (tipus `InputMismatchException`). | **1.0** |
| | | **PUNTUACIÓ MÀXIMA TOTAL:** | **10.0** |

// Clase que representa la entidad Vehiculo (modelo de datos)
public class Vehiculo {

    // Atributos de la clase
    private int id; // Identificador del vehículo (clave primaria en la BD)
    private String matricula; // Matrícula del vehículo
    private String modelo; // Modelo y marca del vehículo
    private int idDept; // ID del departamento al que pertenece

    // Constructor sin ID (se usa para insertar nuevos vehículos)
    public Vehiculo(String matricula, String modelo, int idDept) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.idDept = idDept;
    }

    // Constructor completo (se usa para consultas o actualizaciones)
    public Vehiculo(int id, String matricula, String modelo, int idDept) {
        this.id = id;
        this.matricula = matricula;
        this.modelo = modelo;
        this.idDept = idDept;
    }

    // Getter del ID
    public int getId() {
        return id;
    }

    // Getter de la matrícula
    public String getMatricula() {
        return matricula;
    }

    // Getter del modelo
    public String getModelo() {
        return modelo;
    }

    // Getter del departamento
    public int getIdDept() {
        return idDept;
    }

    // Setter del ID (se usa al asignarlo manualmente)
    public void setId(int id) {
        this.id = id;
    }

    // Setter del modelo (permite modificar el modelo del vehículo)
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Método toString para mostrar el objeto de forma legible
    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", idDept=" + idDept +
                '}';
    }
}
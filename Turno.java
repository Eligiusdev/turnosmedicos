

public class Turno {
    private int idTurno;
    private String nombrePaciente;
    private String nombreMedico;
    private boolean atendido;

    public Turno(int idTurno, String nombrePaciente, String nombreMedico) {
        this.idTurno = idTurno;
        this.nombrePaciente = nombrePaciente;
        this.nombreMedico = nombreMedico;
        this.atendido = false;
    }

    // Getters y Setters
    public int getIdTurno() { return idTurno; }
    public String getNombrePaciente() { return nombrePaciente; }
    public String getNombreMedico() { return nombreMedico; }
    public boolean isAtendido() { return atendido; }
    public void setAtendido(boolean atendido) { this.atendido = atendido; }

    @Override
    public String toString() {
        return idTurno + ";" + nombrePaciente + ";" + nombreMedico + ";" + atendido;
    }

    public static Turno desdeString(String linea) {
        String[] partes = linea.split(";");
        Turno t = new Turno(Integer.parseInt(partes[0]), partes[1], partes[2]);
        t.setAtendido(Boolean.parseBoolean(partes[3]));
        return t;
    }
}

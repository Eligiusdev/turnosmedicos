

import java.io.*;
import java.util.*;

public class GestorTurnos {
    private Map<String, Queue<Turno>> colasPorMedico;
    private List<Turno> historico;
    private int contadorID;
    private final String ARCHIVO = "turnos.txt";

    public GestorTurnos() {
        colasPorMedico = new HashMap<>();
        historico = new ArrayList<>();
        contadorID = 1;
        cargarTurnos();
    }

    public void agendarTurno(String paciente, String medico) {
        Turno turno = new Turno(contadorID++, paciente, medico);
        colasPorMedico.putIfAbsent(medico, new LinkedList<>());
        colasPorMedico.get(medico).offer(turno);
        historico.add(turno);
        System.out.println("Turno agendado con ID: " + turno.getIdTurno() + " para el Dr. " + medico);
    }

    public void atenderTurno(String medico) {
        Queue<Turno> cola = colasPorMedico.get(medico);
        if (cola == null || cola.isEmpty()) {
            System.out.println("No hay turnos pendientes para el Dr. " + medico);
            return;
        }
        Turno turno = cola.poll();
        turno.setAtendido(true);
        System.out.println("Atendiendo turno ID: " + turno.getIdTurno() + ", paciente: " + turno.getNombrePaciente());
    }

    public void mostrarPendientes(String medico) {
        Queue<Turno> cola = colasPorMedico.get(medico);
        if (cola == null || cola.isEmpty()) {
            System.out.println("No hay turnos pendientes para el Dr. " + medico);
            return;
        }
        System.out.println("Turnos pendientes para el Dr. " + medico + ":");
        for (Turno t : cola) {
            System.out.println("ID: " + t.getIdTurno() + " - " + t.getNombrePaciente());
        }
    }

    public void mostrarAtendidos(String medico) {
        System.out.println("Turnos atendidos para el Dr. " + medico + ":");
        for (Turno t : historico) {
            if (t.getNombreMedico().equalsIgnoreCase(medico) && t.isAtendido()) {
                System.out.println("ID: " + t.getIdTurno() + " - " + t.getNombrePaciente());
            }
        }
    }

    public void cancelarTurno(int id) {
        Turno aCancelar = null;
        for (Turno t : historico) {
            if (t.getIdTurno() == id && !t.isAtendido()) {
                aCancelar = t;
                break;
            }
        }
        if (aCancelar != null) {
            Queue<Turno> cola = colasPorMedico.get(aCancelar.getNombreMedico());
            if (cola != null) {
                cola.remove(aCancelar);
                historico.remove(aCancelar);
                System.out.println("Turno ID " + id + " cancelado con éxito.");
            }
        } else {
            System.out.println("Turno no encontrado o ya fue atendido.");
        }
    }

    public void guardarTurnos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Turno t : historico) {
                bw.write(t.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar turnos: " + e.getMessage());
        }
    }

    private void cargarTurnos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Turno t = Turno.desdeString(linea);
                historico.add(t);
                if (!t.isAtendido()) {
                    colasPorMedico.putIfAbsent(t.getNombreMedico(), new LinkedList<>());
                    colasPorMedico.get(t.getNombreMedico()).offer(t);
                }
                contadorID = Math.max(contadorID, t.getIdTurno() + 1);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar turnos: " + e.getMessage());
        }
    }
}


import java.util.Scanner;

public class ControladorTurnos {
    private GestorTurnos gestor;
    private Scanner scanner;

    public ControladorTurnos() {
        gestor = new GestorTurnos();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE TURNOS MÉDICOS =====");
        System.out.println("1. Agendar turno");
        System.out.println("2. Atender turno");
        System.out.println("3. Ver turnos atendidos");
        System.out.println("4. Ver turnos pendientes");
        System.out.println("5. Cancelar turno");
        System.out.println("6. Salir");
        System.out.println("=====================================");
    }

    private int obtenerOpcion() {
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    private void procesarOpcion(int opcion) {
        scanner.nextLine(); // Limpiar buffer
        switch (opcion) {
            case 1:
                agendarTurno();
                break;
            case 2:
                atenderTurno();
                break;
            case 3:
                verTurnosAtendidos();
                break;
            case 4:
                verTurnosPendientes();
                break;
            case 5:
                cancelarTurno();
                break;
            case 6:
                gestor.guardarTurnos();
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void agendarTurno() {
        System.out.print("Ingrese el nombre del paciente: ");
        String paciente = scanner.nextLine();
        System.out.print("Ingrese el nombre del médico: ");
        String medico = scanner.nextLine();
        gestor.agendarTurno(paciente, medico);
    }

    private void atenderTurno() {
        System.out.print("Ingrese el nombre del médico: ");
        String medico = scanner.nextLine();
        gestor.atenderTurno(medico);
    }

    private void verTurnosAtendidos() {
        System.out.print("Ingrese el nombre del médico: ");
        String medico = scanner.nextLine();
        gestor.mostrarAtendidos(medico);
    }

    private void verTurnosPendientes() {
        System.out.print("Ingrese el nombre del médico: ");
        String medico = scanner.nextLine();
        gestor.mostrarPendientes(medico);
    }

    private void cancelarTurno() {
        System.out.print("Ingrese el ID del turno a cancelar: ");
        int idTurno = scanner.nextInt();
        gestor.cancelarTurno(idTurno);
    }
}


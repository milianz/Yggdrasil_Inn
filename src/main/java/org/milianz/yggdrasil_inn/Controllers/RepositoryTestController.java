package org.milianz.yggdrasil_inn.Controllers;

import org.milianz.yggdrasil_inn.Domain.Entities.*;
import org.milianz.yggdrasil_inn.Domain.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/test")
public class RepositoryTestController {

    @Autowired private iBillRepository billRepository;
    @Autowired private iBookRepository bookRepository;
    @Autowired private iColorRepository colorRepository;
    @Autowired private iEmployeeRepository employeeRepository;
    @Autowired private iEventRoomRepository eventRoomRepository;
    @Autowired private iFidelityRepository fidelityRepository;
    @Autowired private iFloorRepository floorRepository;
    @Autowired private iPosadaRepository posadaRepository;
    @Autowired private iRoomRepository roomRepository;
    @Autowired private iRotationRepository rotationRepository;
    @Autowired private iUserRepository userRepository;


    @GetMapping("/")
    public ResponseEntity<String> testConexion() {
        return ResponseEntity.ok("Conexión exitosa a Yggdrasil Inn");
    }

    //  Facturas (Bill)
    @GetMapping("/facturas/entre-fechas")
    public ResponseEntity<List<Bill>> facturasPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(billRepository.findByIssueDateBetween(inicio, fin));
    }

    @GetMapping("/facturas/monto-mayor")
    public ResponseEntity<List<Bill>> facturasPorMonto(@RequestParam double monto) {
        return ResponseEntity.ok(billRepository.findBillsWithTotalGreaterThan(monto));
    }

    @GetMapping("/facturas/por-estado-reserva")
    public ResponseEntity<List<Bill>> facturasPorEstadoReserva(@RequestParam String estado) {
        return ResponseEntity.ok(billRepository.findBillsByBookStatus(estado));
    }

    //  Reservas (Book)
    @GetMapping("/reservas/estado-despues")
    public ResponseEntity<List<Book>> reservasPorEstadoYFecha(
            @RequestParam String estado,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(bookRepository.findByStatusAndCheckInAfter(estado, fecha));
    }

    @GetMapping("/reservas/entre-fechas")
    public ResponseEntity<List<Book>> reservasEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(bookRepository.findBookingsBetweenDates(inicio, fin));
    }

    @GetMapping("/reservas/por-usuario")
    public ResponseEntity<List<Book>> reservasPorUsuario(@RequestParam String username) {
        return ResponseEntity.ok(bookRepository.findBookingsByUsername(username));
    }

    //  Colores
    @GetMapping("/colores/por-nombre")
    public ResponseEntity<Color> colorPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(colorRepository.findByColorNameIgnoreCase(nombre));
    }

    @GetMapping("/colores/contiene")
    public ResponseEntity<List<Color>> coloresQueContienen(@RequestParam String patron) {
        return ResponseEntity.ok(colorRepository.findColorsContaining(patron));
    }

    @GetMapping("/colores/por-frecuencia")
    public ResponseEntity<List<Color>> coloresPorFrecuencia() {
        return ResponseEntity.ok(colorRepository.findColorsOrderedByFrequencyInFloors());
    }

    //  Empleados
    @GetMapping("/empleados/por-nombre")
    public ResponseEntity<List<Employee>> empleadosPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(employeeRepository.findByFirstNameContainingOrLastNameContaining(nombre, nombre));
    }

    @GetMapping("/empleados/por-dominio")
    public ResponseEntity<List<Employee>> empleadosPorDominio(@RequestParam String dominio) {
        return ResponseEntity.ok(employeeRepository.findEmployeesByEmailDomain(dominio));
    }

    @GetMapping("/empleados/top-fidelidad")
    public ResponseEntity<List<Employee>> topEmpleadosFidelidad(@RequestParam int limite) {
        return ResponseEntity.ok(employeeRepository.findTopEmployeesByFidelityAssignments(limite));
    }

    // Salones de Eventos
    @GetMapping("/salones/por-capacidad")
    public ResponseEntity<List<EventRoom>> salonesPorCapacidad(@RequestParam int capacidad) {
        return ResponseEntity.ok(eventRoomRepository.findByCapacityGreaterThanEqual(capacidad));
    }

    @GetMapping("/salones/por-ornamento")
    public ResponseEntity<List<EventRoom>> salonesPorOrnamento(@RequestParam String estilo) {
        return ResponseEntity.ok(eventRoomRepository.findEventRoomsByOrnamentStyle(estilo));
    }

    @GetMapping("/salones/mas-reservados")
    public ResponseEntity<List<EventRoom>> salonesMasReservados(@RequestParam int limite) {
        return ResponseEntity.ok(eventRoomRepository.findMostBookedEventRooms(limite));
    }

    // Fidelidad
    @GetMapping("/fidelidad/despues-fecha")
    public ResponseEntity<List<Fidelity>> fidelidadDespuesDeFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(fidelityRepository.findByFechaUltimaAsignacionAfter(fecha));
    }

    @GetMapping("/fidelidad/por-puntos")
    public ResponseEntity<List<Fidelity>> fidelidadPorPuntos(@RequestParam int puntos) {
        return ResponseEntity.ok(fidelityRepository.findByPointsGreaterThan(puntos));
    }

    @GetMapping("/fidelidad/por-email")
    public ResponseEntity<List<Fidelity>> fidelidadPorEmail(@RequestParam String patron) {
        return ResponseEntity.ok(fidelityRepository.findFidelityByUserEmailPattern(patron));
    }

    // Pisos
    @GetMapping("/pisos/por-posada")
    public ResponseEntity<List<Floor>> pisosPorPosada(@RequestParam UUID posadaId) {
        return ResponseEntity.ok(floorRepository.findByPosadaId(posadaId));
    }

    @GetMapping("/pisos/por-color")
    public ResponseEntity<List<Floor>> pisosPorColor(@RequestParam String colorNombre) {
        return ResponseEntity.ok(floorRepository.findFloorsByColorName(colorNombre));
    }

    @GetMapping("/pisos/por-habitaciones")
    public ResponseEntity<List<Floor>> pisosPorCantidadHabitaciones() {
        return ResponseEntity.ok(floorRepository.findFloorsOrderedByRoomCount());
    }

    // Posadas
    @GetMapping("/posadas/por-nombre")
    public ResponseEntity<Posada> posadaPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(posadaRepository.findByNameContainingIgnoreCase(nombre));
    }

    @GetMapping("/posadas/por-direccion")
    public ResponseEntity<List<Posada>> posadasPorDireccion(@RequestParam String patron) {
        return ResponseEntity.ok(posadaRepository.findPosadasByAddressPattern(patron));
    }

    @GetMapping("/posadas/con-pisos")
    public ResponseEntity<List<Posada>> posadasConPisos(@RequestParam int minPisos) {
        return ResponseEntity.ok(posadaRepository.findPosadasWithMoreThanXFloors(minPisos));
    }

    // Habitaciones
    @GetMapping("/habitaciones/por-piso")
    public ResponseEntity<List<Room>> habitacionesPorPiso(@RequestParam Integer pisoId) {
        return ResponseEntity.ok(roomRepository.findByFloorId(pisoId));
    }

    @GetMapping("/habitaciones/por-palabra")
    public ResponseEntity<List<Room>> habitacionesPorPalabra(@RequestParam String palabra) {
        return ResponseEntity.ok(roomRepository.searchRoomsByKeyword(palabra));
    }

    @GetMapping("/habitaciones/por-color-piso")
    public ResponseEntity<List<Room>> habitacionesPorColorPiso(@RequestParam Integer colorId) {
        return ResponseEntity.ok(roomRepository.findRoomsByFloorColor(colorId));
    }

    // Rotaciones
    @GetMapping("/rotaciones/entre-fechas")
    public ResponseEntity<List<Rotation>> rotacionesEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(rotationRepository.findByRotationDateBetween(inicio, fin));
    }

    @GetMapping("/rotaciones/por-empleado")
    public ResponseEntity<List<Rotation>> rotacionesPorEmpleado(@RequestParam UUID empleadoId) {
        return ResponseEntity.ok(rotationRepository.findRotationsForEmployee(empleadoId));
    }

    @GetMapping("/rotaciones/por-username")
    public ResponseEntity<List<Rotation>> rotacionesPorUsername(@RequestParam String username) {
        return ResponseEntity.ok(rotationRepository.findRotationsByUsername(username));
    }

    // Usuarios
    @GetMapping("/usuarios/por-email")
    public ResponseEntity<User> usuarioPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }

    @GetMapping("/usuarios/mayores-que")
    public ResponseEntity<List<User>> usuariosMayoresQue(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return ResponseEntity.ok(userRepository.findUsersOlderThan(fecha));
    }

    @GetMapping("/usuarios/top-reservas")
    public ResponseEntity<List<User>> topUsuariosPorReservas(@RequestParam int limite) {
        return ResponseEntity.ok(userRepository.findTopUsersByBookings(limite));
    }

    // IDs para referencias
    @GetMapping("/referencias/posadas")
    public ResponseEntity<Map<String, UUID>> obtenerIdsPosadas() {
        List<Posada> posadas = posadaRepository.findAll();
        Map<String, UUID> idsMap = new HashMap<>();
        for (Posada posada : posadas) {
            idsMap.put(posada.getName(), posada.getId());
        }
        return ResponseEntity.ok(idsMap);
    }

    @GetMapping("/referencias/colores")
    public ResponseEntity<Map<String, Integer>> obtenerIdsColores() {
        List<Color> colores = colorRepository.findAll();
        Map<String, Integer> idsMap = new HashMap<>();
        for (Color color : colores) {
            idsMap.put(color.getColorName(), color.getId());
        }
        return ResponseEntity.ok(idsMap);
    }

    @GetMapping("/referencias/empleados")
    public ResponseEntity<Map<String, UUID>> obtenerIdsEmpleados() {
        List<Employee> empleados = employeeRepository.findAll();
        Map<String, UUID> idsMap = new HashMap<>();
        for (Employee emp : empleados) {
            idsMap.put(emp.getUsername(), emp.getId());
        }
        return ResponseEntity.ok(idsMap);
    }
}
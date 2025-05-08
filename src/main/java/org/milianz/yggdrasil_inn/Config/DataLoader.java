package org.milianz.yggdrasil_inn.Config;

import org.milianz.yggdrasil_inn.Domain.Entities.*;
import org.milianz.yggdrasil_inn.Domain.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        // Cargar colores
        Color colorEsmeralda = new Color();
        colorEsmeralda.setColorName("Esmeralda");
        colorRepository.save(colorEsmeralda);

        Color colorRuby = new Color();
        colorRuby.setColorName("Rubí");
        colorRepository.save(colorRuby);

        Color colorZafiro = new Color();
        colorZafiro.setColorName("Zafiro");
        colorRepository.save(colorZafiro);

        Color colorCarmin = new Color();
        colorCarmin.setColorName("Carmín");
        colorRepository.save(colorCarmin);

        Color colorCrepusculo = new Color();
        colorCrepusculo.setColorName("Crepúsculo");
        colorRepository.save(colorCrepusculo);

        // Posada
        Posada posadaAinz = new Posada();
        posadaAinz.setName("Yggdrasil Inn - Ainz Ooal Gown");
        posadaAinz.setAddress("Calle Nazarick 1, Ciudad Oscura");
        posadaRepository.save(posadaAinz);

        // Pisos
        Floor pisoEsmeralda = new Floor();
        pisoEsmeralda.setNombre("Piso Esmeralda");
        pisoEsmeralda.setColor(colorEsmeralda);
        pisoEsmeralda.setPosada(posadaAinz);
        floorRepository.save(pisoEsmeralda);

        Floor pisoRuby = new Floor();
        pisoRuby.setNombre("Piso Rubí");
        pisoRuby.setColor(colorRuby);
        pisoRuby.setPosada(posadaAinz);
        floorRepository.save(pisoRuby);

        Floor pisoZafiro = new Floor();
        pisoZafiro.setNombre("Piso Zafiro");
        pisoZafiro.setColor(colorZafiro);
        pisoZafiro.setPosada(posadaAinz);
        floorRepository.save(pisoZafiro);

        Floor pisoCarmin = new Floor();
        pisoCarmin.setNombre("Piso Carmín");
        pisoCarmin.setColor(colorCarmin);
        pisoCarmin.setPosada(posadaAinz);
        floorRepository.save(pisoCarmin);

        Floor pisoCrepusculo = new Floor();
        pisoCrepusculo.setNombre("Piso Crepúsculo");
        pisoCrepusculo.setColor(colorCrepusculo);
        pisoCrepusculo.setPosada(posadaAinz);
        floorRepository.save(pisoCrepusculo);

        // Habitaciones
        Room habitacionTrono = new Room();
        habitacionTrono.setName("Suite del Trono");
        habitacionTrono.setDescription("Lujosa suite con temática real, con cama king size y jacuzzi privado");
        habitacionTrono.setFloor(pisoEsmeralda);
        roomRepository.save(habitacionTrono);

        Room habitacionHechicero = new Room();
        habitacionHechicero.setName("Habitación del Hechicero");
        habitacionHechicero.setDescription("Habitación temática con elementos mágicos y misteriosos");
        habitacionHechicero.setFloor(pisoRuby);
        roomRepository.save(habitacionHechicero);

        Room habitacionSombras = new Room();
        habitacionSombras.setName("Cuarto de las Sombras");
        habitacionSombras.setDescription("Habitación elegante con decoración oscura y sofisticada");
        habitacionSombras.setFloor(pisoZafiro);
        roomRepository.save(habitacionSombras);

        Room habitacionCelestial = new Room();
        habitacionCelestial.setName("Suite Celestial");
        habitacionCelestial.setDescription("Habitación amplia con techo estrellado y terraza privada");
        habitacionCelestial.setFloor(pisoCarmin);
        roomRepository.save(habitacionCelestial);

        Room habitacionGuerrero = new Room();
        habitacionGuerrero.setName("Habitación del Guerrero");
        habitacionGuerrero.setDescription("Habitación con decoración de armaduras y elementos militares");
        habitacionGuerrero.setFloor(pisoCrepusculo);
        roomRepository.save(habitacionGuerrero);

        // Salones de eventos
        EventRoom salonDorado = new EventRoom();
        salonDorado.setName("Salón Dorado");
        salonDorado.setCapacity(200);
        salonDorado.setOrnament("Decoración dorada con detalles de realeza");
        salonDorado.setFloor(pisoEsmeralda);
        eventRoomRepository.save(salonDorado);

        EventRoom salonPlateado = new EventRoom();
        salonPlateado.setName("Salón Plateado");
        salonPlateado.setCapacity(150);
        salonPlateado.setOrnament("Decoración plateada con elementos modernos");
        salonPlateado.setFloor(pisoRuby);
        eventRoomRepository.save(salonPlateado);

        // Usuarios
        User usuario1 = new User();
        usuario1.setFirstName("Albedo");
        usuario1.setLastName("Demoness");
        usuario1.setUsername("albedo");
        usuario1.setPassword("nazarick1");
        usuario1.setEmail("albedo@gmail.com");
        usuario1.setBirthDate(new Date(82, 4, 15)); // 15/May/1982
        userRepository.save(usuario1);

        User usuario2 = new User();
        usuario2.setFirstName("Demiurge");
        usuario2.setLastName("Infernal");
        usuario2.setUsername("demiurge");
        usuario2.setPassword("nazarick2");
        usuario2.setEmail("demiurge@outlook.com");
        usuario2.setBirthDate(new Date(83, 7, 20)); // 20/Ago/1983
        userRepository.save(usuario2);

        User usuario3 = new User();
        usuario3.setFirstName("Cocytus");
        usuario3.setLastName("Warrior");
        usuario3.setUsername("cocytus");
        usuario3.setPassword("nazarick3");
        usuario3.setEmail("cocytus@hotmail.com");
        usuario3.setBirthDate(new Date(80, 1, 10)); // 10/Feb/1980
        userRepository.save(usuario3);

        // Empleados
        Employee empleado1 = new Employee();
        empleado1.setFirstName("Sebas");
        empleado1.setLastName("Tian");
        empleado1.setUsername("sebas");
        empleado1.setPassword("mayordomo1");
        empleado1.setEmail("sebas@yggdrasil.inn");
        empleado1.setBirthDate(new Date(70, 3, 10)); // 10/Abr/1970
        empleado1.setBranch("Central");
        empleado1.setDepartment("Administración");
        empleado1.setIsCoordinator(true); // Usar el método setter en lugar de acceso directo
        empleado1.setIsSupervisor(false); // Usar el método setter en lugar de acceso directo
        employeeRepository.save(empleado1);

        Employee empleado2 = new Employee();
        empleado2.setFirstName("Mare");
        empleado2.setLastName("Elfin");
        empleado2.setUsername("mare");
        empleado2.setPassword("gemelo2");
        empleado2.setEmail("mare@yggdrasil.inn");
        empleado2.setBirthDate(new Date(90, 7, 5)); // 5/Ago/1990
        empleado2.setBranch("Norte");
        empleado2.setDepartment("Mantenimiento");
        empleado2.setIsCoordinator(false); // Usar el método setter en lugar de acceso directo
        empleado2.setIsSupervisor(false); // Usar el método setter en lugar de acceso directo
        employeeRepository.save(empleado2);

        Employee empleado3 = new Employee();
        empleado3.setFirstName("Shalltear");
        empleado3.setLastName("Bloodfallen");
        empleado3.setUsername("shalltear");
        empleado3.setPassword("vampire1");
        empleado3.setEmail("shalltear@yggdrasil.inn");
        empleado3.setBirthDate(new Date(88, 10, 25)); // 25/Nov/1988
        empleado3.setBranch("Sur");
        empleado3.setDepartment("Recepción");
        empleado3.setIsCoordinator(false); // Usar el método setter en lugar de acceso directo
        empleado3.setIsSupervisor(false); // Usar el método setter en lugar de acceso directo
        employeeRepository.save(empleado3);

        // Reservas
        // 1. Reserva activa para habitación
        Book reserva1 = new Book();
        reserva1.setCheckIn(LocalDate.now().plusDays(5));
        reserva1.setCheckOut(LocalDate.now().plusDays(10));
        reserva1.setStatus("ACTIVA");
        reserva1.setUser(usuario1);
        reserva1.setRoom(habitacionTrono);
        bookRepository.save(reserva1);

        // 2. Reserva completada para habitación
        Book reserva2 = new Book();
        reserva2.setCheckIn(LocalDate.now().minusDays(15));
        reserva2.setCheckOut(LocalDate.now().minusDays(10));
        reserva2.setStatus("COMPLETADA");
        reserva2.setUser(usuario2);
        reserva2.setRoom(habitacionHechicero);
        bookRepository.save(reserva2);

        // 3. Reserva cancelada para habitación
        Book reserva3 = new Book();
        reserva3.setCheckIn(LocalDate.now().plusDays(20));
        reserva3.setCheckOut(LocalDate.now().plusDays(25));
        reserva3.setStatus("CANCELADA");
        reserva3.setUser(usuario3);
        reserva3.setRoom(habitacionSombras);
        bookRepository.save(reserva3);

        // 4. Reserva activa para salón de eventos
        Book reserva4 = new Book();
        reserva4.setCheckIn(LocalDate.now().plusDays(30));
        reserva4.setCheckOut(LocalDate.now().plusDays(31)); // Un día para evento
        reserva4.setStatus("ACTIVA");
        reserva4.setUser(usuario1);
        reserva4.setEventRoom(salonDorado);
        bookRepository.save(reserva4);

        // 5. Reserva futura para habitación premium
        Book reserva5 = new Book();
        reserva5.setCheckIn(LocalDate.now().plusDays(45));
        reserva5.setCheckOut(LocalDate.now().plusDays(55));
        reserva5.setStatus("ACTIVA");
        reserva5.setUser(usuario2);
        reserva5.setRoom(habitacionCelestial);
        bookRepository.save(reserva5);

        // Facturas
        // 1. Factura para reserva completada
        Bill factura1 = new Bill();
        factura1.setIssueDate(LocalDate.now().minusDays(10));
        factura1.setTotal(5000.0);
        factura1.setBook(reserva2);
        billRepository.save(factura1);

        // 2. Factura por adelantado para reserva activa
        Bill factura2 = new Bill();
        factura2.setIssueDate(LocalDate.now());
        factura2.setTotal(8500.0);
        factura2.setBook(reserva1);
        billRepository.save(factura2);

        // 3. Factura para evento
        Bill factura3 = new Bill();
        factura3.setIssueDate(LocalDate.now());
        factura3.setTotal(15000.0);
        factura3.setBook(reserva4);
        billRepository.save(factura3);

        // Programa de fidelidad
        // 1. Cliente frecuente con muchos puntos
        Fidelity fidelidad1 = new Fidelity();
        fidelidad1.setPuntosAcumulados(5000);
        fidelidad1.setFechaUltimaAsignacion(LocalDate.now().minusDays(5));
        fidelidad1.setUser(usuario1);
        fidelidad1.setEmployee(empleado1);
        fidelityRepository.save(fidelidad1);

        // 2. Cliente con puntos moderados
        Fidelity fidelidad2 = new Fidelity();
        fidelidad2.setPuntosAcumulados(2500);
        fidelidad2.setFechaUltimaAsignacion(LocalDate.now().minusDays(20));
        fidelidad2.setUser(usuario2);
        fidelidad2.setEmployee(empleado3);
        fidelityRepository.save(fidelidad2);

        // 3. Cliente nuevo con pocos puntos
        Fidelity fidelidad3 = new Fidelity();
        fidelidad3.setPuntosAcumulados(500);
        fidelidad3.setFechaUltimaAsignacion(LocalDate.now().minusDays(30));
        fidelidad3.setUser(usuario3);
        fidelidad3.setEmployee(empleado2);
        fidelityRepository.save(fidelidad3);

        // Rotaciones de personal
        // 1. Rotación actual
        Rotation rotacion1 = new Rotation();
        rotacion1.setRotationDate(LocalDate.now().withDayOfMonth(1)); // Primer día del mes actual
        rotacion1.setShift("MAÑANA");
        rotacion1.setActive(true);
        rotacion1.setEmployee(empleado1);
        rotacion1.setFloor(pisoEsmeralda);
        rotationRepository.save(rotacion1);

        // 2. Rotación actual segundo piso
        Rotation rotacion2 = new Rotation();
        rotacion2.setRotationDate(LocalDate.now().withDayOfMonth(1)); // Primer día del mes actual
        rotacion2.setShift("TARDE");
        rotacion2.setActive(true);
        rotacion2.setEmployee(empleado2);
        rotacion2.setFloor(pisoCrepusculo);
        rotationRepository.save(rotacion2);

        // 3. Rotación del mes pasado (histórica)
        Rotation rotacion3 = new Rotation();
        rotacion3.setRotationDate(LocalDate.now().minusMonths(1).withDayOfMonth(1)); // Primer día del mes anterior
        rotacion3.setShift("NOCHE");
        rotacion3.setActive(false);
        rotacion3.setEmployee(empleado3);
        rotacion3.setFloor(pisoCarmin);
        rotationRepository.save(rotacion3);

        System.out.println("Datos de prueba cargados correctamente en la base de datos.");
    }
}
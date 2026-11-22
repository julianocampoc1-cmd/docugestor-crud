package com.docugestor.app;

import com.docugestor.model.Rol;
import com.docugestor.model.Usuario;
import com.docugestor.service.RolService;
import com.docugestor.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final RolService rolService = new RolService();
    private static final UsuarioService usuarioService = new UsuarioService();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n==========================================");
            System.out.println("   DOCUGESTOR - GESTION DOCUMENTAL (CRUD) ");
            System.out.println("==========================================");
            System.out.println("1. Listar roles");
            System.out.println("2. Registrar rol");
            System.out.println("3. Actualizar rol");
            System.out.println("4. Cambiar estado de rol");
            System.out.println("5. Listar usuarios (con rol)");
            System.out.println("6. Registrar usuario");
            System.out.println("7. Actualizar usuario");
            System.out.println("8. Cambiar estado de usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> listarRoles();
                case 2 -> registrarRol();
                case 3 -> actualizarRol();
                case 4 -> cambiarEstadoRol();
                case 5 -> listarUsuarios();
                case 6 -> registrarUsuario();
                case 7 -> actualizarUsuario();
                case 8 -> cambiarEstadoUsuario();
                case 0 -> System.out.println("Saliendo del sistema DocuGestor...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private static void listarRoles() {
        List<Rol> roles = rolService.listarRoles();
        System.out.println("\n--- ROLES REGISTRADOS ---");
        roles.forEach(System.out::println);
    }

    private static void registrarRol() {
        System.out.print("Nombre del rol: ");
        String nombre = sc.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();
        System.out.print("Activo? (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());
        System.out.println(rolService.registrarRol(nombre, descripcion, estado)
                ? "Rol registrado correctamente." : "No se pudo registrar el rol.");
    }

    private static void actualizarRol() {
        listarRoles();
        System.out.print("ID del rol a actualizar: ");
        int id = leerEntero();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nueva descripcion: ");
        String descripcion = sc.nextLine();
        System.out.print("Activo? (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());
        System.out.println(rolService.actualizarRol(id, nombre, descripcion, estado)
                ? "Rol actualizado correctamente." : "No se pudo actualizar el rol.");
    }

    private static void cambiarEstadoRol() {
        listarRoles();
        System.out.print("ID del rol: ");
        int id = leerEntero();
        System.out.print("Nuevo estado (true=Activo / false=Inactivo): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());
        System.out.println(rolService.cambiarEstadoRol(id, estado)
                ? "Estado del rol actualizado." : "No se pudo actualizar el estado.");
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        System.out.println("\n--- USUARIOS REGISTRADOS ---");
        usuarios.forEach(System.out::println);
    }

    private static void registrarUsuario() {
        listarRoles();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Correo electronico: ");
        String correo = sc.nextLine();
        System.out.print("Clave: ");
        String clave = sc.nextLine();
        System.out.print("ID del rol a asignar: ");
        int idRol = leerEntero();
        System.out.print("Activo? (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());
        System.out.println(usuarioService.registrarUsuario(documento, nombre, correo, clave, idRol, estado)
                ? "Usuario registrado correctamente." : "No se pudo registrar el usuario.");
    }

    private static void actualizarUsuario() {
        listarUsuarios();
        System.out.print("ID del usuario a actualizar: ");
        int id = leerEntero();
        Usuario actual = usuarioService.buscarUsuarioPorId(id);
        if (actual == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        listarRoles();
        System.out.print("Nuevo documento [" + actual.getDocumento() + "]: ");
        String documento = sc.nextLine();
        System.out.print("Nuevo nombre completo [" + actual.getNombreCompleto() + "]: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo correo [" + actual.getCorreo() + "]: ");
        String correo = sc.nextLine();
        System.out.print("Nueva clave: ");
        String clave = sc.nextLine();
        System.out.print("Nuevo ID de rol [" + actual.getIdRol() + "]: ");
        int idRol = leerEntero();
        System.out.print("Activo? (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());
        System.out.println(usuarioService.actualizarUsuario(id, documento, nombre, correo, clave, idRol, estado)
                ? "Usuario actualizado correctamente." : "No se pudo actualizar el usuario.");
    }

    private static void cambiarEstadoUsuario() {
        listarUsuarios();
        System.out.print("ID del usuario: ");
        int id = leerEntero();
        System.out.print("Nuevo estado (true=Activo / false=Inactivo): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());
        System.out.println(usuarioService.cambiarEstadoUsuario(id, estado)
                ? "Estado del usuario actualizado." : "No se pudo actualizar el estado.");
    }

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}
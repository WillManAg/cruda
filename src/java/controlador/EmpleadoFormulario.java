/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author william.marin
 */


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.servlet.http.HttpServletRequest;
import modelo.Empleado;

public class EmpleadoFormulario {

    public static Empleado leer(HttpServletRequest request) {

        Integer empno = enteroOpcional(
                request.getParameter("empno"), "EMPNO");

        String ename = textoOpcional(
                request.getParameter("ename"));

        String job = textoOpcional(
                request.getParameter("job"));

        Integer mgr = enteroOpcional(
                request.getParameter("mgr"), "MGR");

        Date hiredate = fechaOpcional(
                request.getParameter("hiredate"));

        Integer sal = enteroOpcional(
                request.getParameter("sal"), "SAL");

        Integer comm = enteroOpcional(
                request.getParameter("comm"), "COMM");

        Integer deptno = enteroOpcional(
                request.getParameter("deptno"), "DEPTNO");

        if (empno == null) {
            throw new IllegalArgumentException(
                    "El numero de empleado es obligatorio.");
        }

        if (ename != null && ename.length() > 10) {
            throw new IllegalArgumentException(
                    "El nombre no puede superar 10 char.");
        }

        if (job != null && job.length() > 9) {
            throw new IllegalArgumentException(
                    "El puesto no puede superar 9 char.");
        }

        return new Empleado(
                empno, ename, job, mgr,
                hiredate, sal, comm, deptno
        );
    }

    private static String textoOpcional(String texto) {

        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }

        return texto.trim();
    }

    private static Integer enteroOpcional(
            String texto, String campo) {

        String valor = textoOpcional(texto);

        if (valor == null) {
            return null;
        }

        try {
            return Integer.valueOf(valor);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    campo + " debe ser un numero entero");
        }
    }

    private static Date fechaOpcional(String texto) {

        String valor = textoOpcional(texto);

        if (valor == null) {
            return null;
        }

        try {
            LocalDate fecha = LocalDate.parse(valor);

            if (fecha.getYear() < 1000
                    || fecha.getYear() > 9999) {

                throw new IllegalArgumentException(
                        "La fecha debe estar entre 1000 y 9999.");
            }

            return Date.valueOf(fecha);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "La fecha no vale.");
        }
    }
}


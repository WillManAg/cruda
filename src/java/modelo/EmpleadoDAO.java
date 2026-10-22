/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author william.marin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadoDAO {

    public ArrayList<Empleado> listarEmpleados()
            throws SQLException {

        ArrayList<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT * FROM emp ORDER BY EMPNO";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Empleado empleado = new Empleado(
                        rs.getInt("EMPNO"),
                        rs.getString("ENAME"),
                        rs.getString("JOB"),
                        rs.getObject("MGR", Integer.class),
                        rs.getDate("HIREDATE"),
                        rs.getObject("SAL", Integer.class),
                        rs.getObject("COMM", Integer.class),
                        rs.getObject("DEPTNO", Integer.class)
                );

                empleados.add(empleado);
            }
        }

        return empleados;
    }
}

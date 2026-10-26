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
import java.sql.Types;

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

    public int insertarEmpleado(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO emp "
                + "(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empleado.getEmpno());
            stmt.setString(2, empleado.getEname());
            stmt.setString(3, empleado.getJob());
            stmt.setObject(4, empleado.getMgr(), Types.INTEGER);
            stmt.setObject(5, empleado.getHiredate(), Types.DATE);
            stmt.setObject(6, empleado.getSal(), Types.INTEGER);
            stmt.setObject(7, empleado.getComm(), Types.INTEGER);
            stmt.setObject(8, empleado.getDeptno(), Types.INTEGER);

            return stmt.executeUpdate();
        }

    }
}

<%-- 
    Document   : listado
    Created on : 22 oct 2026, 10:54:06
    Author     : william.marin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Empleado"%>

<%
    ArrayList<Empleado> empleados
            = (ArrayList<Empleado>) request.getAttribute("empleados");
%>

<%!
    private String mostrar(Object valor) {
        if (valor == null) {
            return "—";
        }

        return valor.toString()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Listado de empleados</title>

        <link rel="stylesheet"
              href="<%= request.getContextPath()%>/css/estilos.css">
    </head>

    <body>

        <main class="contenedor">

            <header class="cabecera">

                <span class="etiqueta">
                    GESTIÓN DE EMPLEADOS
                </span>

                <h1>Empleados<span class="punto">.</span></h1>

                <p>Consulta los registros de la base de datos.</p>

            </header>

            <section class="panel">

                <div class="panel-titulo">

                    <h2>Listado de empleados</h2>

                    <span>
                        <%= empleados != null ? empleados.size() : 0%>
                        registros
                    </span>

                </div>

                <%
                    if (empleados != null && !empleados.isEmpty()) {
                %>

                <div class="tabla-contenedor">

                    <table class="tabla-empleados">

                        <thead>
                            <tr>
                                <th>EMPNO</th>
                                <th>ENAME</th>
                                <th>JOB</th>
                                <th>MGR</th>
                                <th>HIREDATE</th>
                                <th>SAL</th>
                                <th>COMM</th>
                                <th>DEPTNO</th>
                            </tr>
                        </thead>

                        <tbody>

                            <%
                                for (Empleado empleado : empleados) {
                            %>

                            <tr>
                                <td><%= mostrar(empleado.getEmpno())%></td>
                                <td><%= mostrar(empleado.getEname())%></td>
                                <td><%= mostrar(empleado.getJob())%></td>
                                <td><%= mostrar(empleado.getMgr())%></td>
                                <td><%= mostrar(empleado.getHiredate())%></td>
                                <td><%= mostrar(empleado.getSal())%></td>
                                <td><%= mostrar(empleado.getComm())%></td>
                                <td><%= mostrar(empleado.getDeptno())%></td>
                            </tr>

                            <%
                                }
                            %>

                        </tbody>

                    </table>

                </div>

                <%
                } else {
                %>

                <p>No se han encontrado empleados.</p>

                <%
                    }
                %>

                <div class="acciones-listado">

                    <a class="boton-volver"
                       href="<%= request.getContextPath()%>/index.jsp">
                        ← Volver al menú
                    </a>

                </div>

            </section>

            <footer class="pie">
                <span>Gestión de Empleados · Java + JDBC + MariaDB · BY: William Marin</span>
            </footer>

        </main>

    </body>
</html>


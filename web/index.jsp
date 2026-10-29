<%-- 
    Document   : index
    Created on : 22 oct 2026, 9:28:50
    Author     : william.marin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.CookieOperaciones"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.DateTimeException"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Gestión de Empleados</title>

        <link rel="stylesheet"
              href="<%= request.getContextPath()%>/css/estilos.css">
    </head>

    <body>

        <main class="contenedor">

            <header class="cabecera">
                <span class="etiqueta">PANEL DE GESTIÓN DE LA BBDD</span>

                <h1>Gestión de empleados</h1>

                <p>
                    CRUD sobre la tabla de emp
                </p>
            </header>

            <section class="panel">

                <div class="panel-titulo">
                    <h2>¿Qué quieres hacer?</h2>
                    <span>Selecciona una operación</span>
                </div>



                <%
                    String error = (String) request.getAttribute("error");

                    Integer empleadoEliminado
                            = (Integer) session.getAttribute("empleadoEliminado");

                    if (empleadoEliminado != null) {
                        session.removeAttribute("empleadoEliminado");
                    }

                    int totalOperaciones = CookieOperaciones.obtener(request);

                    String ultimaOperacion = "Todavía no hay operaciones";

                    Cookie[] cookies = request.getCookies();

                    if (cookies != null) {

                        for (Cookie cookie : cookies) {

                            if ("ultimaOperacion".equals(cookie.getName())) {

                                try {

                                    long timestamp = Long.parseLong(
                                            cookie.getValue()
                                    );

                                    DateTimeFormatter formato
                                            = DateTimeFormatter
                                                    .ofPattern("dd/MM/yyyy HH:mm:ss")
                                                    .withZone(ZoneId.of("Europe/Madrid"));

                                    ultimaOperacion = formato.format(
                                            Instant.ofEpochMilli(timestamp)
                                    );

                                } catch (NumberFormatException | DateTimeException e) {
                                    ultimaOperacion = "Fecha no válida";
                                }

                                break;
                            }
                        }
                    }

                %>


                <% if (error != null) {%>

                <div class="alerta-error" role="alert">
                    <strong>No se ha podido localizar el empleado</strong>
                    <p><%= error%></p>
                </div>

                <% }%>

                <div class="acciones">

                    <a href="formulario.jsp" class="tarjeta">
                        <div class="icono icono-azul">
                            <svg viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="1.8">
                            <path d="M12 5v14M5 12h14"
                                  stroke-linecap="round"/>
                            </svg>
                        </div>

                        <h3>Añadir empleado</h3>
                        <p>Registra un nuevo empleado en la tabla.</p>
                        <span class="flecha">↗</span>
                    </a>

                    <div class="tarjeta">

                        <div class="icono icono-violeta">
                            <svg viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="1.8">
                            <path d="M12 20h9" stroke-linecap="round"/>
                            <path d="M16.5 3.5a2.12 2.12 0 0 1 3 3L9 17l-4 1 1-4z"
                                  stroke-linejoin="round"/>
                            </svg>
                        </div>

                        <h3>Modificar empleado</h3>
                        <p>Busca un empleado para actualizar sus datos.</p>

                        <form class="busqueda-rapida"
                              action="<%= request.getContextPath()%>/buscarEmpleado"
                              method="get">

                            <input type="hidden"
                                   name="accion"
                                   value="modificar">

                            <div class="campo">

                                <label for="empno-modificar">
                                    Número de empleado
                                </label>

                                <input type="number"
                                       id="empno-modificar"
                                       name="empno"
                                       placeholder="Ej. 7369"
                                       min="-2147483648"
                                       max="2147483647"
                                       step="1"
                                       required>

                            </div>

                            <button type="submit" class="boton-primario">
                                Buscar
                            </button>

                        </form>

                    </div>

                    <div class="tarjeta">

                        <div class="icono icono-rojo">
                            <svg viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="1.8">
                            <path d="M3 6h18M8 6V4h8v2M6 6l1 14h10l1-14"
                                  stroke-linecap="round"
                                  stroke-linejoin="round"/>
                            <path d="M10 10v6M14 10v6"
                                  stroke-linecap="round"/>
                            </svg>
                        </div>

                        <h3>Despedir empleado</h3>
                        <p>Busca un empleado para eliminar su registro.</p>

                        <form class="busqueda-rapida"
                              action="<%= request.getContextPath()%>/buscarEmpleado"
                              method="get">

                            <input type="hidden"
                                   name="accion"
                                   value="eliminar">

                            <div class="campo">

                                <label for="empno-eliminar">
                                    Número de empleado
                                </label>

                                <input type="number"
                                       id="empno-eliminar"
                                       name="empno"
                                       placeholder="Ej. 7499"
                                       min="-2147483648"
                                       max="2147483647"
                                       step="1"
                                       required>

                            </div>

                            <button type="submit" class="boton-primario">
                                Buscar
                            </button>

                        </form>

                    </div>


                    <a href="<%= request.getContextPath()%>/listarEmpleados"
                       class="tarjeta">
                        <div class="icono icono-verde">
                            <svg viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="1.8">
                            <rect x="3" y="4" width="18" height="16" rx="2"/>
                            <path d="M3 10h18M9 10v10"/>
                            </svg>
                        </div>

                        <h3>Ver empleados</h3>
                        <p>Consulta todos los registros de la tabla.</p>
                        <span class="flecha">↗</span>
                    </a>

                </div>


                <div class="resumen-actividad">

                    <h3>Actividad de este navegador</h3>

                    <p>
                        Operaciones realizadas:
                        <strong><%= totalOperaciones%></strong>
                    </p>

                    <p>
                        Última operación:
                        <strong><%= ultimaOperacion%></strong>
                    </p>

                </div>

            </section>

            <footer class="pie">
                <span>Gestión de Empleados · Java + JDBC + MariaDB BY: William Marin</span>

                <a href="<%= request.getContextPath()%>/probarConexion">
                    Comprobar conexión ↗
                </a>
            </footer>

        </main>


        <% if (empleadoEliminado != null) {%>

        <dialog id="popup-eliminado"
                class="dialogo-exito"
                aria-labelledby="titulo-eliminado">

            <div class="confirmacion-icono">✓</div>

            <h2 id="titulo-eliminado">
                ¡Empleado eliminado!
            </h2>

            <p>
                El empleado 
                <strong><%= empleadoEliminado%></strong>
                se ha eliminado correctamente de la BBDD.
            </p>

            <form method="dialog">

                <button type="submit" class="boton-primario">
                    Aceptar
                </button>

            </form>

        </dialog>

        <script>
            document.getElementById("popup-eliminado").showModal();
        </script>

        <% }%>

    </body>
</html>

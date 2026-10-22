<%-- 
    Document   : index
    Created on : 22 oct 2026, 9:28:50
    Author     : william.marin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                    <a href="buscar.jsp?accion=modificar" class="tarjeta">
                        <div class="icono icono-violeta">
                            <svg viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="1.8">
                            <path d="M12 20h9" stroke-linecap="round"/>
                            <path d="M16.5 3.5a2.12 2.12 0 0 1 3 3L9 17l-4 1 1-4z"
                                  stroke-linejoin="round"/>
                            </svg>
                        </div>

                        <h3>Modificar empleado</h3>
                        <p>Actualiza la información de un emp en la tabla.</p>
                        <span class="flecha">↗</span>
                    </a>

                    <a href="buscar.jsp?accion=eliminar" class="tarjeta">
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
                        <p>Elimina un registro de forma PERMANENTEEEE.</p>
                        <span class="flecha">↗</span>
                    </a>

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

            </section>

            <footer class="pie">
                <span>Gestión de Empleados · Java + JDBC + MariaDB</span>

                <a href="<%= request.getContextPath()%>/probarConexion">
                    Comprobar conexión ↗
                </a>
            </footer>

        </main>

    </body>
</html>

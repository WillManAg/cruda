<%-- 
    Document   : formulario.jsp
    Created on : 26 oct 2026, 9:33:36
    Author     : william.marin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    private String escapar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>

<%

    String error = (String) request.getAttribute("error");

    Integer empleadoCreado
            = (Integer) session.getAttribute("empleadoCreado");

    if (empleadoCreado != null) {
        session.removeAttribute("empleadoCreado");
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Añadir empleado</title>

        <link rel="stylesheet"
              href="<%= request.getContextPath()%>/css/estilos.css">
    </head>

    <body>

        <main class="contenedor">

            <header class="cabecera">

                <span class="etiqueta">
                    GESTIÓN DE EMPLEADOS
                </span>

                <h1>Añadir empleado<span class="punto">.</span></h1>

                <p>Introduce los datos del nuevo empleado.</p>

            </header>

            <div class="formulario-layout">

                <section class="panel formulario-panel">

                    <div class="panel-titulo">
                        <h2>Datos del empleado</h2>
                        <span>* Obligatorio</span>
                    </div>

                    <% if (error != null) {%>

                    <div class="alerta-error" role="alert">
                        <strong>No se ha podido guardar</strong>
                        <p><%= escapar(error)%></p>
                    </div>

                    <% }%>

                    <form action="<%= request.getContextPath()%>/crearEmpleado"
                          method="post">

                        <div class="formulario-grid">

                            <div class="campo campo-ancho">

                                <label for="empno">
                                    Número de empleado
                                    <span class="requerido">*</span>
                                </label>

                                <input type="number"
                                       id="empno"
                                       name="empno"
                                       placeholder="Ej. 8000"
                                       min="-2147483648"
                                       max="2147483647"
                                       step="1"
                                       value="<%= escapar(request.getParameter("empno"))%>"
                                       required>

                                <small>Identificador único del empleado.</small>

                            </div>

                            <div class="campo">

                                <label for="ename">Nombre</label>

                                <input type="text"
                                       id="ename"
                                       name="ename"
                                       maxlength="10"
                                       placeholder="Ej. SMITH"
                                       value="<%= escapar(request.getParameter("ename"))%>">

                                <small>Máximo 10 caracteres.</small>

                            </div>

                            <div class="campo">

                                <label for="job">Puesto de trabajo</label>

                                <input type="text"
                                       id="job"
                                       name="job"
                                       maxlength="9"
                                       placeholder="Ej. ANALYST"
                                       value="<%= escapar(request.getParameter("job"))%>">

                                <small>Máximo 9 caracteres.</small>

                            </div>

                            <div class="campo">

                                <label for="mgr">Número del jefe</label>

                                <input type="number"
                                       id="mgr"
                                       name="mgr"
                                       step="1"
                                       min="-2147483648"
                                       max="2147483647"
                                       placeholder="Ej. 7839"
                                       value="<%= escapar(request.getParameter("mgr"))%>">

                            </div>

                            <div class="campo">

                                <label for="hiredate">Fecha de contratación</label>

                                <input type="date"
                                       id="hiredate"
                                       name="hiredate"
                                       min="1000-01-01"
                                       max="9999-12-31"
                                       value="<%= escapar(request.getParameter("hiredate"))%>">

                            </div>

                            <div class="campo">

                                <label for="sal">Salario</label>

                                <input type="number"
                                       id="sal"
                                       name="sal"
                                       step="1"
                                       min="-2147483648"
                                       max="2147483647"
                                       placeholder="Ej. 1800"
                                       value="<%= escapar(request.getParameter("sal"))%>">

                            </div>

                            <div class="campo">

                                <label for="comm">Comisión</label>

                                <input type="number"
                                       id="comm"
                                       name="comm"
                                       step="1"
                                       min="-2147483648"
                                       max="2147483647"
                                       placeholder="Ej. 300"
                                       value="<%= escapar(request.getParameter("comm"))%>">

                            </div>

                            <div class="campo campo-ancho">

                                <label for="deptno">Departamento</label>

                                <input type="number"
                                       id="deptno"
                                       name="deptno"
                                       step="1"
                                       min="-2147483648"
                                       max="2147483647"
                                       placeholder="Ej. 20"
                                       value="<%= escapar(request.getParameter("deptno"))%>">

                            </div>

                        </div>

                        <div class="formulario-acciones">

                            <button type="submit" class="boton-primario">
                                Guardar empleado
                            </button>

                            <a href="<%= request.getContextPath()%>/index.jsp"
                               class="boton-secundario">
                                Cancelar
                            </a>

                        </div>

                    </form>

                </section>

                <aside class="panel panel-requisitos">

                    <div class="requisitos-cabecera">

                        <span class="icono icono-azul">i</span>

                        <div>
                            <h2>Antes de guardar</h2>
                            <p>Requisitos de la base de datos</p>
                        </div>

                    </div>

                    <div class="requisito">

                        <strong>Número de empleado</strong>
                        <p>
                            Es el único campo obligatorio.
                            No puede repetirse si EMPNO es clave primaria.
                        </p>

                    </div>

                    <div class="requisito">

                        <strong>Nombre y puesto</strong>
                        <p>
                            El nombre admite 10 caracteres como máximo
                            y el puesto admite 9.
                        </p>

                    </div>

                    <div class="requisito">

                        <strong>Campos numéricos</strong>
                        <p>
                            Solo admiten números enteros,
                            sin decimales.
                        </p>

                    </div>

                    <div class="requisito">

                        <strong>Fecha de contratación</strong>
                        <p>
                            Debe ser una fecha válida.
                            Puedes dejarla vacía.
                        </p>

                    </div>

                    <div class="requisito">

                        <strong>Campos opcionales</strong>
                        <p>
                            Los campos sin rellenar se guardarán
                            como NULL en MariaDB.
                        </p>

                    </div>

                    <div class="requisitos-nota">
                        Los datos se validan antes de guardarse.
                        Si ocurre un error, podrás corregirlos.
                    </div>

                </aside>

            </div>

            <footer class="pie">
                <span>Gestión de Empleados · Java + JDBC + MariaDB</span>
            </footer>

        </main>


        <% if (empleadoCreado != null) {%>

        <dialog id="popup-exito"
                class="dialogo-exito"
                aria-labelledby="titulo-exito">

            <div class="confirmacion-icono">✓</div>

            <h2 id="titulo-exito">¡Empleado creado!</h2>

            <p>
                El empleado con número
                <strong><%= empleadoCreado%></strong>
                se ha guardado correctamente en MariaDB.
            </p>

            <a href="<%= request.getContextPath()%>/index.jsp"
               class="boton-primario">
                Aceptar y volver al inicio
            </a>

        </dialog>

        <script>
            document.getElementById("popup-exito").showModal();
        </script>

        <% }%>

    </body>
</html>

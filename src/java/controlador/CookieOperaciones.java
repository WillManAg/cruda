/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author william.marin
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieOperaciones {
    
    private static final String NOMBRE = "contadorOperaciones";
    
    public static int obtener(HttpServletRequest request) {
        
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            
            for (Cookie cookie : cookies) {
                
                if (NOMBRE.equals(cookie.getName())) {
                    
                    try {
                        int contador = Integer.parseInt(
                                cookie.getValue()
                        );
                        
                        return Math.max(0, contador);
                        
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                    
                }
                
            }
            
        }
        
        return 0;
    }
    
    public static void incrementar(
            HttpServletRequest request,
            HttpServletResponse response) {
        
        int contador = obtener(request);
        
        if (contador < Integer.MAX_VALUE) {
            contador++;
        }
        
        Cookie cookie = new Cookie(
            NOMBRE, String.valueOf(contador)
        );
        
        cookie.setMaxAge(60 * 60 * 24 * 30);
        
        String ruta = request.getContextPath();
        
        cookie.setPath(ruta.isEmpty() ? "/" : ruta);
        
        cookie.setHttpOnly(true);
        
        response.addCookie(cookie);
        
        Cookie ultimaOperacion = new Cookie(
                "ultimaOperacion",
                String.valueOf(System.currentTimeMillis())
        );
        
        ultimaOperacion.setMaxAge(60 * 60 * 24 * 30);
        
        ultimaOperacion.setPath(ruta.isEmpty() ? "/" : ruta);
        
        ultimaOperacion.setHttpOnly(true);
        
        response.addCookie(ultimaOperacion);
        
    }
    
}

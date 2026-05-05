package edu.ucam.bd;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConexionBD {

    public static Connection getConexion() throws Exception {

        // Obtener el contexto inicial de Tomcat
        Context initContext = new InitialContext();

        // Buscar el recurso definido en context.xml
        Context envContext = (Context) initContext.lookup("java:/comp/env");

        DataSource ds = (DataSource) envContext.lookup("jdbc/dad2");

        // Devolver conexión
        return ds.getConnection();
    }
}
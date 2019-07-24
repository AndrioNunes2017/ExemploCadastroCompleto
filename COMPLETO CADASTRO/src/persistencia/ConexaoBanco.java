/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBanco {

    //Atributos estáticos com os dados do Banco de Dados
    //private static String URL = "jdbc:mysql://host:porta/db1";
    //private static String USUARIO = "qidados";
    //private static String SENHA = "qidados";
    
    private static String URL="jdbc:mysql://host:porta/db2";
    private static String USUARIO="root";
    private static String SENHA="root";

    //Método que efetua a conexão com o MySQL
    public static Connection getConexao() throws SQLException {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException se) {
            throw new SQLException("Erro ao conectar! " + se.getMessage());
        }//fecha catch
        return c;
    }//fecha metodo
    
}//fecha classe


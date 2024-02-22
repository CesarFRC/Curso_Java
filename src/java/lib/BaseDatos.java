
package lib;

import java.sql.Connection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDatos {
    
    
     static public Connection conectar() {
        Connection conexion = null; //Aqui lo que hacemos es conectarnos a la base de datos pero por medio de pull que es con varias conexiones
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/curso");
            conexion = ds.getConnection();
        }catch(Throwable e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null,e.getMessage()));
        }
        
        return conexion;
    }
     /*Este metodo nos ayuda a desconectarnos de la base de datos */
     static public void desconectar(Connection conexion) {
        try{
            if (conexion != null)
                conexion.close();
        }catch(Throwable e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null,e.getMessage()));
        }
        /*El pul de conexio nos ayuda a manejar las conexiones mas estables que de manera tradicional y esta se va mas ordenada */
        /*Cuando no es pul de conexiones solamente se entra y siguie creciendo*/
    }
}

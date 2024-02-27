package ini;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lib.BaseDatos;
//Estos dos son importantes para que funcione con java server face
@Named
@ViewScoped
public class ini00 implements Serializable {
    String usuario;
    String password;

    //Para que tenga las propiedades en el front get esel valor que le damos al momento
    //Mientras que con el set podemos enviar y modificar esa informacion del set

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        //Regresamos el metodo accesar a el front
        public String accesar(){
            //Una pequeña consulta a la base de datos 
         Connection conexion = BaseDatos.conectar();
          try{
              Statement st = conexion.createStatement();
              String sql = "select usu_clave, usu_password from usuario";
              ResultSet rs = st.executeQuery(sql);
              if(rs.next()){
                  String x = rs.getString("usu_password");
   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,x));

              }
          }catch(Throwable e) /* este cacha cualquier tipo de error */{
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null,e.getMessage()));
          }finally{
              BaseDatos.desconectar(conexion);
          }
        return "";            
        }    
}

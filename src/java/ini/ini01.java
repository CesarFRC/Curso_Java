package ini;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import java.sql.Connection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lib.BaseDatos;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

@Named
@ViewScoped

public class ini01 implements Serializable {
    List<Map<String,?>>registros;
    //podemos poner una llave y un valor Un mapa es una lista dentro de otra lista

    public List<Map<String, ?>> getRegistros() {
        return registros;
    }
    
    //Nos conectamos a la base de datos para hacer un select
  @PostConstruct
  public void init(){
      Connection conexion = BaseDatos.conectar();
      try{
          //aqui realizamos una consulta a la base de datos
       Statement st = conexion.createStatement();
       String sql = "select usu_clave, usu_password" +
                    "from usuario" +
                    "order by usu_clave";
          ResultSet rs = st.executeQuery(sql);
          
          //Con esta manera podemos accede la informacion de como se llaman los campos para que haga en automatico
          ResultSetMetaData md =  rs.getMetaData();
          
          //Aqui estamos inicializando la arrayList Lista completa
          registros = new ArrayList<>();
          //Es un bucle que nos va filtrando y ordenando la informacion para verla  
          while(rs.next()){
              Map<String,?> registro = new HashMap<>();
              //Tenemos fila ahora solo falta los campos para recorrer de la columna
              
              for(int i =1; i<=md.getColumnCount(); i++){
              
              }
              
                registros.add(registro);
          }
      }catch(Throwable e){
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null,e.getMessage()));
      }finally{
          BaseDatos.desconectar(conexion);
      }
  }
}

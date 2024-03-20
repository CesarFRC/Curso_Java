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
    
    //Ingresamos un get para obtener ese valor
    String titulo;

    public String getTitulo() {
        return titulo;
    }
    
    //Le ponemos a los dos su get y su set por que vamos a escribir y leer
    String usu_clave;
    String usu_password;

    public String getUsu_clave() {
        return usu_clave;
    }

    public void setUsu_clave(String usu_clave) {
        this.usu_clave = usu_clave;
    }

    public String getUsu_password() {
        return usu_password;
    }

    public void setUsu_password(String usu_password) {
        this.usu_password = usu_password;
    }
    
    //Nos conectamos a la base de datos para hacer un select
  @PostConstruct
  public void init(){
      Connection conexion = BaseDatos.conectar();
      try{
          //aqui realizamos una consulta a la base de datos
       Statement st = conexion.createStatement();
       String sql = "select usu_id ,usu_clave, usu_password " +
                    "from usuario " +
                    "order by usu_clave ";
          ResultSet rs = st.executeQuery(sql);
          
          //Con esta manera podemos accede la informacion de como se llaman los campos para que haga en automatico
          ResultSetMetaData md =  rs.getMetaData();
          
          //Aqui estamos inicializando la arrayList Lista completa
          registros = new ArrayList<>();
          //Es un bucle que nos va filtrando y ordenando la informacion para verla  
          while(rs.next()){
              Map<String,Object> registro = new HashMap<>();
              //Tenemos fila ahora solo falta los campos para recorrer de la columna
              
              for(int i =1; i<=md.getColumnCount(); i++){
                       //Como se llama la cosa de tu mapa y su valor
                       //Con esto ya estamos llenado el registro de nuestros mapas 
              registro.put(md.getColumnLabel(i).toLowerCase(), rs.getObject(i));
              }
              
                registros.add(registro);
          }
      }catch(Throwable e){
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null,e.getMessage()));
      }finally{
          BaseDatos.desconectar(conexion);
      }
  }
  //Un nuevo metodo
  public void agregar(){
     titulo="Agregar Usuario";
  }
  
  public void guardar(){
  
  }
}

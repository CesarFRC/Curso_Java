package ini;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
        return "ini01";            
        }    
}

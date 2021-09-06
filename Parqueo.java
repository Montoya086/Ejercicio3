/*

 Autor: Andrés Estuardo Montoya Wilhelm
 Programa: Parqueo.java
 Creación: 30/8/2021   Modificación: 05/9/2021
 Lenguaje: Java
    
*/
public class Parqueo {
    String tipo;
    boolean techado;
    boolean aereo;
    int usos;
    Vehiculo carro;
    public Parqueo(){//parqueo por defecto

    }
    public Parqueo(String tipo, boolean techado, boolean aereo){//parqueo con caracteristicas
        this.tipo=tipo;
        this.techado=techado;
        this.aereo=aereo;
        usos=0;
        carro=null;
    }
    public Vehiculo getCarro(){//mostrar el carro
        return carro;
    }
    public void setCarro(Vehiculo carro){//ingresar el carro
        this.carro=carro;
    }
    public void addUsos(){//añadir usos
        usos++;
    }
    public String getTipo(){// obtener el tipo
        return tipo;
    }
    public boolean getTechado(){//obetner si es techado o no
        return techado;
    }
    public boolean getAereo(){//obtener si es aereo o no
        return aereo;
    }
    public int getUsos(){// obtener los usos del parqueo
        return usos;
    }
    public void setTipo(String tipo){// colocar el tipo del parqueo
        this.tipo = tipo;
    }
    public void setTechado(boolean techado){//colocar si es techado o no
        this.techado = techado;
    }
    public void setAereo(boolean aereo){// colocar si es aereo o no
        this.aereo = aereo;
    }
    public void setUsos(int usos){// colocar las veces que se ha usado el parqueo 
        this.usos = usos;
    }

}


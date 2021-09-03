public class Parqueo {
    String tipo;
    boolean techado;
    boolean aereo;
    int usos;
    Vehiculo carro;
    public Parqueo(String tipo, boolean techado, boolean aereo){
        this.tipo=tipo;
        this.techado=techado;
        this.aereo=aereo;
        usos=0;
        carro=null;
    }
    public Vehiculo getCarro(){
        return carro;
    }
    public void setCarro(Vehiculo carro){
        this.carro=carro;
    }
    public void addUsos(){
        usos++;
    }
    public String getTipo(){
        return tipo;
    }
    public boolean getTechado(){
        return techado;
    }
    public boolean getAereo(){
        return aereo;
    }
    public int getUsos(){
        return usos;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setTechado(boolean techado){
        this.techado = techado;
    }
    public void setAereo(boolean aereo){
        this.aereo = aereo;
    }

}


import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.security.Principal;
public class Estacionamiento {
    ArrayList<Parqueo>parqueos;
    File Datos_carros,Datos;
    int parqueoLleno=0;
    public Estacionamiento(){
        parqueos=new ArrayList<Parqueo>();
        Datos_carros=new File("Datos_Carros.txt");
        Datos=new File("Datos.txt");
    }
    public void agregar_Espacios(String tipo, boolean techado, boolean aereo){
        parqueos.add(new Parqueo(tipo, techado, aereo));
    }
    public void agregar_espacios_vacios(){
        parqueos.add(new Parqueo());
    }
    public boolean verificacion_parqueo_lleno(){
        boolean bandera1=true;
        for (int j=0;j<parqueos.size();j++) {
            if(parqueos.get(j).getCarro()==null){
                bandera1=false;
            }
        }
        if(bandera1==true){
            return true;
        }else{
            return false;
        }
    }
    public boolean verificacion_parqueo_vacio(){
        boolean bandera1=true;
        for (int j=0;j<parqueos.size();j++) {
            if(parqueos.get(j).getCarro()!=null){
                bandera1=false;
            }
        }
        if(bandera1==true){
            return true;
        }else{
            return false;
        }
    }
    public boolean verificacion_de_placa_existe(String placa){
        boolean bandera1=false;
        for (int j=0;j<parqueos.size();j++) {
            if(parqueos.get(j).getCarro()!=null){
                if(parqueos.get(j).getCarro().get_placa().equals(placa)){
                    bandera1=true;
                }
            }
        }
        if(bandera1==true){
            return true;
        }else{
            return false;
        }
    }
    public void ingreso(int hora_entrada, String placa, String marca, String modelo){
        boolean bandera=true;
        for (int i=0;i<parqueos.size()&&bandera;i++) {
            if(parqueos.get(i).getCarro()==null){
                parqueos.get(i).setCarro(new Vehiculo(hora_entrada, placa, marca, modelo));
                parqueos.get(i).addUsos();
                bandera=false;
            }
        }
    }
    public void salida(String placa, int hora_salida){
        
    }
    public String crear_archivo_Datos_carros(){// crear archivo de datos de carros y parqueos
        try{
            if(!Datos_carros.exists()){//comprobar si existe
                if(Datos_carros.createNewFile()){// si se creó
                    return "Se creo el archivo "+Datos_carros.getName(); 
                }else{
                    return"No se ha podido crear el archivo";
                }
            }else{
                return "El archivo "+Datos_carros.getName()+" ya existe"; 
            }
        }catch(IOException e){
            return "No se ha podido crear el archivo"; 
        }
    }
    public String crear_archivo_Datos(){// crear archivo de datos
        try{
            if(!Datos.exists()){// comprobar si existe
                if(Datos.createNewFile()){// si se creó
                    return "Se creo el archivo "+Datos.getName(); 
                }
                else{
                    return"No se ha podido crear el archivo";
                }
            }else{
                return "El archivo "+Datos.getName()+" ya existe"; 
            }
        }catch(IOException e){
            return "No se ha podido crear el archivo"; 
        }
    }
    public void guardar_datos_carros(){ // guardar datos de los carros y parqueos
        try{
            FileWriter w = new FileWriter("Datos_Carros.txt",true);
            for(int i=0;i<parqueos.size();i++){
                if(i==parqueos.size()-1){
                    if(parqueos.get(i).getCarro()==null){                                                                                              //↓separador de estacionamiento/carro
                        w.write(parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+","+"null");
                    }else{                                                                                                                                //↓separador de estacionamiento/carro
                        String s = parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+","+parqueos.get(i).getCarro().get_hora_entrada()+";"+parqueos.get(i).getCarro().get_placa()+";"+parqueos.get(i).getCarro().get_marca()+";"+parqueos.get(i).getCarro().get_modelo();
                        w.write(s);
                    }
                }else{
                    if(parqueos.get(i).getCarro()==null){                                                                                              //↓separador de estacionamiento/carro
                        w.write(parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+","+"null");
                        w.write("\r\n");
                    }else{                                                                                                                                //↓separador de estacionamiento/carro
                        String s = parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+","+parqueos.get(i).getCarro().get_hora_entrada()+";"+parqueos.get(i).getCarro().get_placa()+";"+parqueos.get(i).getCarro().get_marca()+";"+parqueos.get(i).getCarro().get_modelo();
                        w.write(s);
                        w.write("\r\n");
                    }
                }
            }
            w.close();
        }catch (IOException e){
            System.out.println(e);
        }
        
    }
    public int contar_datos_carros(){//contar los espacios de estacionamiento
        int cont=0;
        try{
            FileReader r = new FileReader("Datos_Carros.txt");
            BufferedReader br = new BufferedReader(r);
            String linea;
            while((linea=br.readLine())!=null){
                cont++;
            }
            r.close();
        }catch(IOException e){

        }
        return cont;
    }
    public void leer_datos_carros(){//leer y guardar los datos del parqueo
        try{
            FileReader r = new FileReader("Datos_Carros.txt");
            BufferedReader br = new BufferedReader(r);
            String linea;
            int cont=0;
            while((linea=br.readLine())!=null){
                String[] parqueo_carro = linea.split(",");
                String[] datos_parqueo = parqueo_carro[0].split(";");
                String[] datos_carro = parqueo_carro[1].split(";");
                //poner los datos del parqueo
                parqueos.get(cont).setTipo(datos_parqueo[0]);
                if(datos_parqueo[1].equals("false")){
                    parqueos.get(cont).setTechado(false);
                }else{
                    parqueos.get(cont).setTechado(true);
                }
                if(datos_parqueo[2].equals("false")){
                    parqueos.get(cont).setAereo(false);
                }else{
                    parqueos.get(cont).setAereo(true);
                }
                parqueos.get(cont).setUsos(Integer.parseInt(datos_parqueo[3]));
                //poner los datos del carro
                if(datos_carro[0].equals("null")){//espacio vacio
                    parqueos.get(cont).setCarro(null);
                }else{
                    parqueos.get(cont).setCarro(new Vehiculo(Integer.parseInt(datos_carro[0]), datos_carro[1], datos_carro[2], datos_carro[3]));
                }
                cont++;
            }
            r.close();
        }catch(IOException e){

        }
    }
    public boolean archivo1_existe(){
        return Datos_carros.exists();
    }
    public boolean archivo2_existe(){
        return Datos.exists();
    }
    public void borrar_archivos(){
        Datos_carros.delete();
        Datos.delete();
    }
    public void setParqueolleno(){
        parqueoLleno++;
    }
}

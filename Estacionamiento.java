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
    public Estacionamiento(){
        parqueos=new ArrayList<Parqueo>();
        Datos_carros=new File("Datos_Carros.txt");
        Datos=new File("Datos.txt");
    }
    public void agragar_Espacios(String tipo, boolean techado, boolean aereo){
        parqueos.add(new Parqueo(tipo, techado, aereo));
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
                        w.write(parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+"|"+"null");
                    }else{                                                                                                                                //↓separador de estacionamiento/carro
                        String s = parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+"|"+parqueos.get(i).getCarro().get_hora_entrada()+";"+parqueos.get(i).getCarro().get_placa()+";"+parqueos.get(i).getCarro().get_marca()+";"+parqueos.get(i).getCarro().get_modelo();
                        w.write(s);
                    }
                }else{
                    if(parqueos.get(i).getCarro()==null){                                                                                              //↓separador de estacionamiento/carro
                        w.write(parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+"|"+"null");
                        w.write("\r\n");
                    }else{                                                                                                                                //↓separador de estacionamiento/carro
                        String s = parqueos.get(i).getTipo()+";"+parqueos.get(i).getTechado()+";"+parqueos.get(i).getAereo()+";"+parqueos.get(i).getUsos()+"|"+parqueos.get(i).getCarro().get_hora_entrada()+";"+parqueos.get(i).getCarro().get_placa()+";"+parqueos.get(i).getCarro().get_marca()+";"+parqueos.get(i).getCarro().get_modelo();
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
        }catch(IOException e){

        }
        return cont;
    }
    public void leer_datos_carros(){
        try{
            FileReader r = new FileReader("Datos_Carros.txt");
            BufferedReader br = new BufferedReader(r);
            String linea;
            int cont=0;
            while((linea=br.readLine())!=null){
                String[] parqueo_carro = linea.split("|");
                String[] datos_parqueo = parqueo_carro[0].split(";");
                String[] datos_carro = parqueo_carro[1].split(";");
                parqueos.get(cont).setTipo(datos_parqueo[0]);
                if(datos_parqueo[0].equals("false")){
                    parqueos.get(cont).setTechado(false);
                }else{
                    parqueos.get(cont).setTechado(true);
                }
            }
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
}

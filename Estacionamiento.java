/*

 Autor: Andrés Estuardo Montoya Wilhelm
 Programa: Estacionamiento.java
 Creación: 30/8/2021   Modificación: 05/9/2021
 Lenguaje: Java
    
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Estacionamiento {
    ArrayList<Parqueo>parqueos;
    ArrayList<String[]>horarios;
    ArrayList<String[]>marcas;
    File Datos_carros,Datos;
    int parqueoLleno=0;
    int contador_horas=0;
    int contador_salidas=0;
    public Estacionamiento(){//constructor
        parqueos=new ArrayList<Parqueo>();
        horarios= new ArrayList<String[]>();
        marcas = new ArrayList<String[]>();
        Datos_carros=new File("Datos_Carros.txt");
        Datos=new File("Datos.txt");
    }
    
    /** 
     * @param tipo
     * @param techado
     * @param Parqueo(tipo
     * @param techado
     * @param j=0;j<parqueos.size();j++)if(parqueos.get(j).getCarro()==null
     */
    public void agregar_Espacios(String tipo, boolean techado, boolean aereo){//agregar espacios
        parqueos.add(new Parqueo(tipo, techado, aereo));
    }
    
    /** 
     * @param j=0;j<parqueos.size();j++)if(parqueos.get(j).getCarro()==null
     */
    public void agregar_espacios_vacios(){//agregar espacios por defecto
        parqueos.add(new Parqueo());
    }
    
    /** 
     * @param j=0;j<parqueos.size();j++)if(parqueos.get(j).getCarro()==null
     * @return boolean
     */
    public boolean verificacion_parqueo_lleno(){//verificar si el parqueo está lleno, devuelve true si esta lleno
        boolean bandera1=true;
        for (int j=0;j<parqueos.size();j++) {//busca espacios vacios
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
    
    /** 
     * @param j=0;j<parqueos.size();j++)if(parqueos.get(j).getCarro()!=null
     * @return boolean
     */
    public boolean verificacion_parqueo_vacio(){//verificar si el parqueo esta vacio
        boolean bandera1=true;
        for (int j=0;j<parqueos.size();j++) { // busca espacios llenos
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
    
    /** 
     * @param j=0;j<parqueos.size();j++
     * @return boolean
     */
    public boolean verificacion_de_placa_existe(String placa){//verificar si la placa existe
        boolean bandera1=false;
        for (int j=0;j<parqueos.size();j++) {
            if(parqueos.get(j).getCarro()!=null){//busca las placas entre los carros que existen
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
    
    /** 
     * @param hora_entrada
     * @param placa
     * @param marca
     * @param i=0;i<parqueos.size()&&bandera;i++
     */
    public void ingreso(int hora_entrada, String placa, String marca, String modelo){//ingresar un nuevo carro
        boolean bandera=true;
        for (int i=0;i<parqueos.size()&&bandera;i++) {
            if(parqueos.get(i).getCarro()==null){//ingresa en el primer espacio vacio
                parqueos.get(i).setCarro(new Vehiculo(hora_entrada, placa, marca, modelo));
                parqueos.get(i).addUsos();
                bandera=false;
            }
        }
        boolean bandera2=false;
        if(marcas.size()>0){//agregar al contador de marcas
            for(int i=0;i<marcas.size();i++){ // buscar si la marca ya existe
                if(marcas.get(i)!=null){
                    if(marcas.get(i)[0].toLowerCase().equals(marca.toLowerCase())){
                        bandera2=true;
                        int cont = Integer.parseInt(marcas.get(i)[1]);
                        cont++;
                        marcas.get(i)[1]=cont+""; 
                    }
                }
            }
        }
        if(!bandera2){//agregar un nuevo contador
            marcas.add(new String[]{marca,"1"});
        }
    }
    
    /** 
     * @param placa
     * @param hora_salida
     */
    public void salida(String placa, int hora_salida){//salida del carro
        try{
            int ncarro=-1;
            boolean bandera=false;
            for(int j=0;j<parqueos.size();j++){ // busqueda del carro con la placa
                if(parqueos.get(j).getCarro()!=null){
                    if(parqueos.get(j).getCarro().get_placa().equals(placa)){
                        ncarro=j;
                        int hora_entrada = parqueos.get(ncarro).getCarro().get_hora_entrada();
                        String horario = hora_entrada+"-"+hora_salida;
                        for(int i=0;i<horarios.size();i++){ // buscar si el horario ya existe
                            if(horarios.get(i)!=null){
                                if(horarios.get(i)[0].equals(horario)){
                                    bandera=true;
                                    int cont = Integer.parseInt(horarios.get(i)[1]);// sacar el contador a int
                                    cont++;
                                    horarios.get(i)[1]=cont+""; //meter el nuevo contador a string
                                }
                            }
                        }
                    }
                }
            }
            if(ncarro>-1){
                int hora_entrada = parqueos.get(ncarro).getCarro().get_hora_entrada();
                String horario = hora_entrada+"-"+hora_salida;
                if(!bandera){//agregar un contador de horarios
                    horarios.add(new String[]{horario,"1"});
                }
                int tiempo = Math.abs(hora_salida-hora_entrada);//sumatoria de tiempos
                contador_horas = contador_horas + tiempo;
                contador_salidas++;
                parqueos.get(ncarro).setCarro(null);
            }
        }catch(Exception e){

        }
    }
    
    /** 
     * @return String
     */
    public String mayor_horario(){//horarios mayormente usados
        try{
            String horario_mayor="No hay horarios actualmente";
            Integer[] conts = new Integer[horarios.size()];
            if(horarios.size()>0){//si existen horarios en los contadores
                for(int i=0;i<horarios.size();i++){//crea un array con los contadores
                    conts[i]=Integer.parseInt(horarios.get(i)[1]); 
                }
                Arrays.sort(conts, Collections.reverseOrder());//ordena de mayor a menor
                int mayor = conts[0];// agarra el mayor
                for(int i=0;i<horarios.size();i++){
                    if(horarios.get(i)[1].equals(Integer.toString(mayor))){
                        horario_mayor = horarios.get(i)[0];//busca el intervalo que tiene dicho contador
                    }
                }
            }
            return horario_mayor;
        }catch(Exception e){
            return "No hay horarios actualmente";
        }
    }
    
    /** 
     * @return String
     */
    public String mayor_marca(){ // buscar las marcas más utilizadas
        try{
            String marca_mayor="No se han registrado carros";
            Integer[] conts = new Integer[marcas.size()];
            if(marcas.size()>0){//si existen marcas en los contadores
                for(int i=0;i<marcas.size();i++){//crea un array con los contadores
                    conts[i]=Integer.parseInt(marcas.get(i)[1]); 
                }
                Arrays.sort(conts, Collections.reverseOrder());//ordena de mayor a menor
                int mayor = conts[0];//agarra el mayor
                for(int i=0;i<marcas.size();i++){
                    if(marcas.get(i)[1].equals(Integer.toString(mayor))){
                        marca_mayor = marcas.get(i)[0];//busca la marca que tiene dicho contador
                    }
                }
            }
            return marca_mayor;
        }catch(Exception e){
            return "No se han registrado carros";
        }
    }
    
    /** 
     * @return String
     */
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
    
    /** 
     * @return String
     */
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
    public void guardar_datos(){//guardar datos de la empresa
        try{
            FileWriter w = new FileWriter("Datos.txt",true);
            if(horarios.size()>0){//guarda el listado de horarios con sus usos
                String s="";
                for(int i=0;i<horarios.size()-1;i++){
                    s=s+horarios.get(i)[0]+";"+horarios.get(i)[1]+",";
                }
                s=s+horarios.get(horarios.size()-1)[0]+";"+horarios.get(horarios.size()-1)[1];
                w.write(s);
                w.write("\r\n");
            }else{
                w.write("null");
                w.write("\r\n");
            }
            if(marcas.size()>0){ // guarda el listado de las marcas con las veces registradas
                String s="";
                for(int i=0;i<marcas.size()-1;i++){
                    s=s+marcas.get(i)[0]+";"+marcas.get(i)[1]+",";
                }
                s=s+marcas.get(marcas.size()-1)[0]+";"+marcas.get(marcas.size()-1)[1];
                w.write(s);
                w.write("\r\n");
            }else{
                w.write("null");
                w.write("\r\n");
            }
            w.write(Integer.toString(contador_horas));
            w.write("\r\n");
            w.write(Integer.toString(contador_salidas));
            w.write("\r\n");
            w.write(Integer.toString(parqueoLleno));
            w.write("\r\n");
            w.close();
        }catch(IOException e){

        }
    }
    public void leer_datos(){// leer datos de la empresa
        try{
            FileReader r = new FileReader("Datos.txt");
            BufferedReader br = new BufferedReader(r);
            String linea;
            if((linea=br.readLine())!=null){//lee el listado de horarios con sus usos
                if(!linea.equals("null")){
                    String[] horarioslist = linea.split(",");
                    for(int i=0;i<horarioslist.length;i++){
                        String[] horarios_cont = horarioslist[i].split(";");
                        horarios.add(new String[]{horarios_cont[0],horarios_cont[1]});
                    }
                }
            }
            if((linea=br.readLine())!=null){//lee el listado de las marcas con las veces registradas
                if(!linea.equals("null")){
                    String[] marcaslist = linea.split(",");
                    for(int i=0;i<marcaslist.length;i++){
                        String[] marcas_cont = marcaslist[i].split(";");
                        marcas.add(new String[]{marcas_cont[0],marcas_cont[1]});
                    }
                }
            }
            if((linea=br.readLine())!=null){//lee las horas totales de uso
                contador_horas=Integer.parseInt(linea);
            }
            if((linea=br.readLine())!=null){//lee el contador de carros que salen
                contador_salidas=Integer.parseInt(linea);
            }
            if((linea=br.readLine())!=null){// lee cuantos carros no pudieron entrar por parqueo lleno
                parqueoLleno=Integer.parseInt(linea);
            }
            br.close();
            r.close();
        }catch(IOException e){

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

        }
        
    }
    
    /** 
     * @return int
     */
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
            br.close();
            r.close();
        }catch(IOException e){

        }
    }
    
    /** 
     * @param promedio_horas()if(contador_salidas!=0
     * @return boolean
     */
    public boolean archivo1_existe(){//ver si el archivo eciste
        return Datos_carros.exists();
    }
    
    /** 
     * @param promedio_horas()if(contador_salidas!=0
     * @return boolean
     */
    public boolean archivo2_existe(){//ver si el archivo eciste
        return Datos.exists();
    }
    
    /** 
     * @param promedio_horas()if(contador_salidas!=0
     */
    public void borrar_archivos(){//borrar archivos
        Datos_carros.delete();
        Datos.delete();
    }
    
    /** 
     * @param promedio_horas()if(contador_salidas!=0
     */
    public void setParqueolleno(){//agregar a un carro que no entro porque el parqueo estaba lleno
        parqueoLleno++;
    }
    
    /** 
     * @param )if(contador_salidas!=0
     * @return int
     */
    public int promedio_horas(){//calcular el promedio de horas
        if(contador_salidas!=0){
        return contador_horas/contador_salidas;
        }
        else{
            return 0;
        }
    }
    
    /** 
     * @return String
     */
    public String parqueo_mas_usado(){//mostrar el numero de parqueo mas usado
        try{
            String parqueo_mayor="No se ha usado ningun parqueo";
            Integer[] conts = new Integer[parqueos.size()];
            if(parqueos.size()>0){//si hay parqueos recorre el array
                for(int i=0;i<parqueos.size();i++){
                    conts[i]=parqueos.get(i).getUsos(); //hace un array de los usos de los parqueos
                }
                Arrays.sort(conts, Collections.reverseOrder());//ordena de mayor a menor
                int mayor = conts[0];//agarra el mayor
                for(int i=0;i<parqueos.size();i++){
                    if(parqueos.get(i).usos==mayor){
                        parqueo_mayor = Integer.toString(i+1);
                    }
                }
            }
            return parqueo_mayor;
        }catch(Exception e){
            return "No se ha usado ningun parqueo";
        }
    }
    
    /** 
     * @param aereo;if(parqueos.get(nparqueo).getTechado()
     * @return String
     */
    public String vehiculos_rechazados(){//mostrar los vehiculos que fueron rechazados porque el parqueo estaba lleno
        return Integer.toString(parqueoLleno);
    }
    
    /** 
     * @param aereo;if(parqueos.get(nparqueo).getTechado()
     * @return String
     */
    public String caracteristicas_parqueo(String parqueo){//mostrar las caracteristicas de el parqueo ingresado
        int nparqueo = Integer.parseInt(parqueo);
        String techado;
        String aereo;
        if(parqueos.get(nparqueo).getTechado()){
            techado="si";
        }else{
            techado="no";
        }
        if(parqueos.get(nparqueo).getAereo()){
            aereo="si";
        }else{
            aereo="no";
        }
        String s="Tipo: "+parqueos.get(nparqueo).getTipo()+"\nTechado: "+techado+"\nAereo: "+aereo;
        return s;
    }
}

/*

 Autor: Andrés Estuardo Montoya Wilhelm
 Programa: Vista.java
 Creación: 30/8/2021   Modificación: 05/9/2021
 Lenguaje: Java
    
*/
import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;
public class Vista {
    Scanner sc;
    public Vista(){// constructor
        sc=new Scanner(System.in);
    }
    
    /** 
     * @param options(
     */
    public void creacion_archivos(String a){// todos los mensajes de archivos y su creacion
        System.out.println(a);
    }
    
    /** 
     * @return int
     */
    public int options(){// opciones principales
        try{
            System.out.println("Elija una de las siguientes opciones:");
            System.out.println("1. Ingreso");
            System.out.println("2. Salida");
            System.out.println("3. Ampliar estacionamiento");
            System.out.println("4. Mostrar estadisticas");
            System.out.println("5. Guardar y Salir");
            int r=Integer.parseInt(sc.nextLine());
            return r;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return 0;
        }
    }
    
    /** 
     * @return int
     */
    public int pedir_hora_entrada(){//pedir la hora de entrada
        try{
            System.out.println("Ingrese la hora de entrada (0-24)");
            int h=Integer.parseInt(sc.nextLine());
            return h;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return -999;
        }
    }
    
    /** 
     * @return int
     */
    public int pedir_hora_salida(){//pedir la hora de salida
        try{
            System.out.println("Ingrese la hora de salida (0-24)");
            int h=Integer.parseInt(sc.nextLine());
            return h;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return -999;
        }
    }
    
    /** 
     * @return String
     */
    public String pedir_marca(){//pedir la marca del carro
        try{
            System.out.println("Ingrese la marca del carro");
            String m=sc.nextLine();
            return m;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    
    /** 
     * @return String
     */
    public String pedir_placa(){//pedir la placa del carro
        try{
            System.out.println("Ingrese la placa");
            String p=sc.nextLine();
            return p;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    
    /** 
     * @return String
     */
    public String pedir_modelo(){//pedir el modelo del carro
        try{
            System.out.println("Ingrese el modelo");
            String md=sc.nextLine();
            return md;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    
    /** 
     * @return int
     */
    public int ampliar_estacionamiento(){// mensaje de ampliar el estacionamiento
        try{
            System.out.println("Ingrese cuantos parqueos desea agregar (>1): ");
            int r=Integer.parseInt(sc.nextLine());
            return r;
        }catch(Exception e){
            System.out.println("Ingrese un valor valido");
            return -1;
        }
    }
    
    /** 
     * @param pedir_techado(
     */
    public void ingrese_num_mayor(){//mensaje de advertencia
        System.out.println("Ingrese un numero mayor a 1");
    }
    
    /** 
     * @return String
     */
    public String pedir_techado(){//pedir si el parqueo es techado o no
        try{
            System.out.println("El parqueo sera techado? (si/no): ");
            String r = sc.nextLine();
            return r;
        }catch(Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    
    /** 
     * @return String
     */
    public String pedir_aereo(){// pedir si es aereo o no
        try{
            System.out.println("El parqueo sera aereo? (si/no): ");
            String r = sc.nextLine();
            return r;
        }catch(Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    
    /** 
     * @return int
     */
    public int pedir_tipo(){// pedir el tipo de estacionamiento
        try{
            System.out.println("Elija una de las siguientes opciones:");
            System.out.println("1. Estandar");
            System.out.println("2. Europeo");
            System.out.println("3. Largo");
            int r=Integer.parseInt(sc.nextLine());
            return r;
        }catch(Exception e){
            return 0;
        }
    }
    public void parqueo_lleno(){// decir que el parqueo esta lleno
        System.out.println("\nEl parqueo esta lleno\n");
    }
    public void parqueo_vacio(){// decir que el parqueo esta vacio
        System.out.println("\nEl parqueo esta vacio\n");
    }
    public void placa_no_existe(){// decir que la placa no existe
        System.out.println("\nLa placa no existe\n");
    }
    public void mayor_horario(String horario){// mostrar el horario de mayor actividad en el parqueo
        System.out.println("\nHorario mas utilizado: "+ horario);
    }
    public void promedio_horas(int promedio){//mostrar el promedio de horas
        System.out.println("\nPromedio de horas de uso: "+ promedio);
    }
    public void parqueo_mas_usado(String parqueo){// mostrar el parqueo más usado
        System.out.println("\nNumero de parqueo mas usado: "+ parqueo);
    }
    public void caracteristicas_parqueo(String caracteristicas){// mostrar las caracteristicas del parqueo ingresado
        System.out.println("\nCon caractaristicas: \n"+ caracteristicas);
    }
    public void mayor_marca(String marca){// mostrar la marca más usada
        System.out.println("\nLa marca mas usada es: "+ marca);
    }
    public void vehiculos_rechazados(String vehiculos){// mostrar la cantidad de vehiculos rechazados
        System.out.println("\nVehiculos rechazados por falta de espacio: "+ vehiculos);
    }
}

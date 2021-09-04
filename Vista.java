import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;
public class Vista {
    Scanner sc;
    public Vista(){
        sc=new Scanner(System.in);
    }
    public void creacion_archivos(String a){
        System.out.println(a);
    }
    public int options(){
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
    public int pedir_hora_entrada(){
        try{
            System.out.println("Ingrese la hora de entrada (0-24)");
            int h=Integer.parseInt(sc.nextLine());
            return h;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return -999;
        }
    }
    public int pedir_hora_salida(){
        try{
            System.out.println("Ingrese la hora de salida (0-24)");
            int h=Integer.parseInt(sc.nextLine());
            return h;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return -999;
        }
    }
    public String pedir_marca(){
        try{
            System.out.println("Ingrese la marca del carro");
            String m=sc.nextLine();
            return m;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    public String pedir_placa(){
        try{
            System.out.println("Ingrese la placa");
            String p=sc.nextLine();
            return p;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    public String pedir_modelo(){
        try{
            System.out.println("Ingrese el modelo");
            String md=sc.nextLine();
            return md;
        }catch (Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    public int ampliar_estacionamiento(){
        try{
            System.out.println("Ingrese cuantos parqueos desea agregar (>1): ");
            int r=Integer.parseInt(sc.nextLine());
            return r;
        }catch(Exception e){
            System.out.println("Ingrese un valor valido");
            return -1;
        }
    }
    public void ingrese_num_mayor(){
        System.out.println("Ingrese un numero mayor a 1");
    }
    public String pedir_techado(){
        try{
            System.out.println("El parqueo sera techado? (si/no): ");
            String r = sc.nextLine();
            return r;
        }catch(Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    public String pedir_aereo(){
        try{
            System.out.println("El parqueo sera aereo? (si/no): ");
            String r = sc.nextLine();
            return r;
        }catch(Exception e){
            System.out.println("Ingrese un valor valido");
            return "-error-";
        }
    }
    public int pedir_tipo(){
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
    public void parqueo_lleno(){
        System.out.println("\nEl parqueo esta lleno\n");
    }
    public void parqueo_vacio(){
        System.out.println("\nEl parqueo esta vacio\n");
    }
    public void placa_no_existe(){
        System.out.println("\nLa placa no existe\n");
    }
}

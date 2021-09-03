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
}

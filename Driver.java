public class Driver{
    public static void main(String[] args){
        Vista v= new Vista();
        Estacionamiento estacionamiento=new Estacionamiento();
        if(!estacionamiento.archivo1_existe()&&!estacionamiento.archivo2_existe()){//crear nuevo archivo
           v.creacion_archivos(estacionamiento.crear_archivo_Datos()); 
           v.creacion_archivos(estacionamiento.crear_archivo_Datos_carros()); 
           for(int i=0;i<5;i++){
               estacionamiento.agragar_Espacios("estandar", false, false);
           }
        }else{//abrir archivo existente
            /*for(int i=0;i<5;i++){//BORRAR
                estacionamiento.agragar_Espacios("estandar", false, false);
            }
            estacionamiento.borrar_archivos();
            v.creacion_archivos(estacionamiento.crear_archivo_Datos()); 
            v.creacion_archivos(estacionamiento.crear_archivo_Datos_carros());*/ 
            
        }
        int r=0;
        while(r!=5){
            r=v.options();
            switch (r) {
                case 1://ingreso
                    int hora_entrada=-999;
                    while(hora_entrada==-999||!(hora_entrada>=0&&hora_entrada<=24)){
                        hora_entrada = v.pedir_hora_entrada();
                    }
                    String placa="-error-";
                    while(placa.equals("-error-")){
                        placa=v.pedir_placa();
                    }
                    String marca="-error-";
                    while(marca.equals("-error-")){
                        marca=v.pedir_marca();
                    }
                    String modelo="-error-";
                    while(modelo.equals("-error-")){
                        modelo=v.pedir_modelo();
                    }
                    estacionamiento.ingreso(hora_entrada, placa, marca, modelo);
                    break;
                case 2:// Salida
                    
                    break;
                case 3:// Ampliar estacionamiento
                    
                    break;
                case 4:// Mostrar estadisticas
                    
                    break;
                case 5:// Guardar y salir
                    estacionamiento.guardar_datos_carros();
                    
                    break;
                default:
                    break;
            }
        }
    }
}
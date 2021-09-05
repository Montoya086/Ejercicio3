public class Driver{
    public static void main(String[] args){
        Vista v= new Vista();
        Estacionamiento estacionamiento=new Estacionamiento();
        if(!estacionamiento.archivo1_existe()&&!estacionamiento.archivo2_existe()){//crear nuevo archivo
           v.creacion_archivos(estacionamiento.crear_archivo_Datos()); 
           v.creacion_archivos(estacionamiento.crear_archivo_Datos_carros()); 
           for(int i=0;i<5;i++){
               estacionamiento.agregar_Espacios("estandar", false, false);
           }
        }else{//abrir archivo existente
            for(int i=0;i<estacionamiento.contar_datos_carros();i++){
                estacionamiento.agregar_espacios_vacios();
            }
            estacionamiento.leer_datos_carros();
            estacionamiento.leer_datos();          
        }
        int r=0;
        while(r!=5){
            r=v.options();
            switch (r) {
                case 1://ingreso
                    if(!estacionamiento.verificacion_parqueo_lleno()){
                        int hora_entrada=-999;
                        while(hora_entrada==-999||!(hora_entrada>=0&&hora_entrada<=24)){
                            hora_entrada = v.pedir_hora_entrada();
                        }
                        String placa="-error-";
                        while(placa.equals("-error-") || placa.contains(",")||placa.contains(";")){
                            placa=v.pedir_placa();
                        }
                        String marca="-error-";
                        while(marca.equals("-error-")|| marca.contains(",")||marca.contains(";")){
                            marca=v.pedir_marca();
                        }
                        String modelo="-error-";
                        while(modelo.equals("-error-")|| modelo.contains(",")||modelo.contains(";")){
                            modelo=v.pedir_modelo();
                        }
                        estacionamiento.ingreso(hora_entrada, placa, marca, modelo);
                    }else{
                        v.parqueo_lleno();
                        estacionamiento.setParqueolleno();
                    }
                    break;
                case 2:// Salida
                    if(!estacionamiento.verificacion_parqueo_vacio())
                    {
                        String placa="-error-";
                        while(placa.equals("-error-") || placa.contains(",")||placa.contains(";")||!estacionamiento.verificacion_de_placa_existe(placa)){
                            placa=v.pedir_placa();
                            if(!estacionamiento.verificacion_de_placa_existe(placa)){
                                v.placa_no_existe();
                            }
                        }
                        int hora_salida=-999;
                        while(hora_salida==-999||!(hora_salida>=0&&hora_salida<=24)){
                            hora_salida = v.pedir_hora_salida();
                        }
                        estacionamiento.salida(placa, hora_salida);
                    }else{
                        v.parqueo_vacio();
                    }
                    break;
                case 3:// Ampliar estacionamiento
                    int numero=0;
                    while(numero<1){
                        numero=v.ampliar_estacionamiento();
                        if(numero<1){
                            v.ingrese_num_mayor();
                        }
                    }
                    int tipo1=0;
                    String tipo2;
                    while((tipo1<1||tipo1>3)){
                        tipo1=v.pedir_tipo();
                    }
                    if(tipo1==1){
                        tipo2="estandar";
                    }else if(tipo1==2){
                        tipo2="europeo";
                    }else if(tipo1==3){
                        tipo2="largo";
                    }else{
                        tipo2="estandar";
                    }
                    String techado1="-error-";
                    boolean techado2;
                    while(techado1.equals("-error-")||!(techado1.equals("no")||techado1.equals("si"))|| techado1.contains(",")||techado1.contains(";")){
                        techado1=v.pedir_techado().toLowerCase();
                    }
                    if(techado1.equals("si")){
                        techado2=true;
                    }else{
                        techado2=false;
                    }
                    String aereo1="-error-";
                    boolean aereo2;
                    while(aereo1.equals("-error-")||!(aereo1.equals("no")||aereo1.equals("si"))|| aereo1.contains(",")||aereo1.contains(";")){
                        aereo1=v.pedir_aereo().toLowerCase();
                    }
                    if(aereo1.equals("si")){
                        aereo2=true;
                    }else{
                        aereo2=false;
                    }
                    for(int i=0; i<numero;i++){
                        estacionamiento.agregar_Espacios(tipo2, techado2, aereo2);
                    }
                    break;
                case 4:// Mostrar estadisticas
                    System.out.println(estacionamiento.mayor_horario());
                    System.out.println(estacionamiento.promedio_horas());
                    System.out.println("El parqueo mas usado es el No."+estacionamiento.parqueo_mas_usado());
                    System.out.println("La marca mas utilizada es "+estacionamiento.mayor_marca());
                    System.out.println("Se han rechazado un total de "+estacionamiento.vehiculos_rechazados()+" vehiculos debido a que el estacionamiento estaba lleno");
                    break;
                case 5:// Guardar y salir
                    estacionamiento.borrar_archivos();
                    v.creacion_archivos(estacionamiento.crear_archivo_Datos()); 
                    v.creacion_archivos(estacionamiento.crear_archivo_Datos_carros());
                    estacionamiento.guardar_datos_carros();   
                    estacionamiento.guardar_datos();               
                    break;
                default:
                    break;
            }
        }
    }
}
/*

 Autor: Andrés Estuardo Montoya Wilhelm
 Programa: Driver.java
 Creación: 30/8/2021   Modificación: 05/9/2021
 Lenguaje: Java
    
*/
public class Driver{
    
    /** 
     * @param args
     */
    public static void main(String[] args){
        Vista v= new Vista();
        Estacionamiento estacionamiento=new Estacionamiento(); // inicializa el estacionamiento
        if(!estacionamiento.archivo1_existe()&&!estacionamiento.archivo2_existe()){//crear nuevo archivo
           v.creacion_archivos(estacionamiento.crear_archivo_Datos()); // creacion de los datos de la empresa
           v.creacion_archivos(estacionamiento.crear_archivo_Datos_carros()); 
           for(int i=0;i<5;i++){// crear los espacios por defecto
               estacionamiento.agregar_Espacios("estandar", false, false);
           }
        }else{//abrir archivo existente
            for(int i=0;i<estacionamiento.contar_datos_carros();i++){
                estacionamiento.agregar_espacios_vacios();
            }
            //leer datos
            estacionamiento.leer_datos_carros();
            estacionamiento.leer_datos();          
        }
        int r=0;
        while(r!=5){//mientras la opcion no sea salir
            r=v.options();
            switch (r) {
                case 1://ingreso
                    if(!estacionamiento.verificacion_parqueo_lleno()){// verificar si el parqueo está lleno
                        int hora_entrada=-999;
                        while(hora_entrada==-999||!(hora_entrada>=0&&hora_entrada<=24)){// ingreso hora de entrada
                            hora_entrada = v.pedir_hora_entrada();
                        }
                        String placa="-error-";
                        while(placa.equals("-error-") || placa.contains(",")||placa.contains(";") || estacionamiento.verificacion_de_placa_existe(placa)){// ingresio placa
                            placa=v.pedir_placa();
                        }
                        String marca="-error-";
                        while(marca.equals("-error-")|| marca.contains(",")||marca.contains(";")){// ingreso marca
                            marca=v.pedir_marca();
                        }
                        String modelo="-error-";
                        while(modelo.equals("-error-")|| modelo.contains(",")||modelo.contains(";")){// ingreso modelo
                            modelo=v.pedir_modelo();
                        }
                        estacionamiento.ingreso(hora_entrada, placa, marca, modelo);// ingreso de datos
                    }else{
                        v.parqueo_lleno();
                        estacionamiento.setParqueolleno();
                    }
                    break;
                case 2:// Salida
                    if(!estacionamiento.verificacion_parqueo_vacio())//verificar si el parqueo está vacio
                    {
                        String placa="-error-";
                        while(placa.equals("-error-") || placa.contains(",")||placa.contains(";")||!estacionamiento.verificacion_de_placa_existe(placa)){//ingreso de la placa
                            placa=v.pedir_placa();
                            if(!estacionamiento.verificacion_de_placa_existe(placa)){// placa no existe
                                v.placa_no_existe();
                            }
                        }
                        int hora_salida=-999;
                        while(hora_salida==-999||!(hora_salida>=0&&hora_salida<=24)){// ingreso hora salida
                            hora_salida = v.pedir_hora_salida();
                        }
                        estacionamiento.salida(placa, hora_salida);
                    }else{
                        v.parqueo_vacio();//parqueo vacio
                    }
                    break;
                case 3:// Ampliar estacionamiento
                    int numero=0;
                    while(numero<1){//ingreso del numero de parqueos
                        numero=v.ampliar_estacionamiento();
                        if(numero<1){
                            v.ingrese_num_mayor();
                        }
                    }
                    int tipo1=0;
                    String tipo2;
                    while((tipo1<1||tipo1>3)){//pedir tipo de parqueo
                        tipo1=v.pedir_tipo();
                    }
                    if(tipo1==1){//tipos de parqueo
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
                    while(techado1.equals("-error-")||!(techado1.equals("no")||techado1.equals("si"))|| techado1.contains(",")||techado1.contains(";")){//pedir techado o no
                        techado1=v.pedir_techado().toLowerCase();
                    }
                    if(techado1.equals("si")){// techado
                        techado2=true;
                    }else{//no techado
                        techado2=false;
                    }
                    String aereo1="-error-";
                    boolean aereo2;
                    while(aereo1.equals("-error-")||!(aereo1.equals("no")||aereo1.equals("si"))|| aereo1.contains(",")||aereo1.contains(";")){//pedir aereo
                        aereo1=v.pedir_aereo().toLowerCase();
                    }
                    if(aereo1.equals("si")){//aereo
                        aereo2=true;
                    }else{//no aereo
                        aereo2=false;
                    }
                    for(int i=0; i<numero;i++){//agregar los espacios
                        estacionamiento.agregar_Espacios(tipo2, techado2, aereo2);
                    }
                    break;
                case 4:// Mostrar estadisticas
                    v.mayor_horario(estacionamiento.mayor_horario());//mayor horario
                    v.promedio_horas(estacionamiento.promedio_horas());//promedio de horas
                    v.parqueo_mas_usado(estacionamiento.parqueo_mas_usado());// parqueo mas usado
                    v.caracteristicas_parqueo(estacionamiento.caracteristicas_parqueo(estacionamiento.parqueo_mas_usado()));// caracteristicas del parqueo más usado
                    v.mayor_marca(estacionamiento.mayor_marca());//mayor marca
                    v.vehiculos_rechazados(estacionamiento.vehiculos_rechazados());//vehiculos rechazados
                    break;
                case 5:// Guardar y salir
                    estacionamiento.borrar_archivos();//borrar archivos anteriores
                    v.creacion_archivos(estacionamiento.crear_archivo_Datos()); //crear archivos vacios
                    v.creacion_archivos(estacionamiento.crear_archivo_Datos_carros());
                    estacionamiento.guardar_datos_carros();   //guardar datos
                    estacionamiento.guardar_datos();               
                    break;
                default:
                    break;
            }
        }
    }
}
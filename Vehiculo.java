public class Vehiculo {
    int hora_entrada;
    String placa;
    String marca;
    String modelo;
    public Vehiculo(int hora_entrada,String placa, String marca, String modelo){
        this.hora_entrada=hora_entrada;
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
    }
    public int get_hora_entrada(){//obtener la hora de entrada
        return hora_entrada;
    }
    public String get_placa(){// obtener la placa
        return placa;
    }
    public String get_marca(){// obtener la marca
        return marca;
    }
    public String get_modelo(){// obtener el modelo
        return modelo;
    }
}

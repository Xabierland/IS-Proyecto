package is.Modelo;

public class Generador {
    public Generador(){}
    public Barco hacerBarco(int pTipo){
        return BarcosFactory.getBarcosFactory().hacerBarco(pTipo);

    }

}

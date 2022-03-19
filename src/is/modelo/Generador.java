package is.modelo;

public class Generador {
    public Generador(){}
    public Barco hacerBarco(int dir,int type, int x, int y){
        return BarcosFactory.getBarcosFactory().hacerBarco(dir, type, x, y);
    }
}

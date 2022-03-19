package is.modelo;

public class Submarino extends Barco{

    public Submarino(int dir,int x, int y) {
        super(dir,x,y);
        super.longitud=3;
    }
    protected void getnombre() {
        System.out.println("Submarino");
        
    }
}

package is.modelo;

public class Fragata extends Barco{

    public Fragata(int dir,int x, int y) {
        super(dir,x,y);
        super.longitud=1;
    }

    @Override
    protected void getnombre() {
        System.out.println("Fragata");
        
    }
    
}

package is.modelo;

public class Portavion extends Barco {

    public Portavion(int dir,int x, int y) {
        super(dir,x,y);
        super.longitud=4;
    }
    protected void getnombre() {
        System.out.println("Portaavion");
        
    }
}

package is.modelo;

public class Destructor extends Barco{
    public Destructor(int dir,int x, int y) {
        super(dir,x,y);
        super.longitud=2;
    }
    protected void getnombre() {
        System.out.println("Destructor");
        
    }
    
}

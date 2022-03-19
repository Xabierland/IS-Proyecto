package is.modelo;

public class Coordenadas {
    private int x;
    private int y;
    public Coordenadas(int pX,int pY){
        x=pX;
        y=pY;

    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean mismaCoordenada(Coordenadas pC){
        return (pC.getX()==this.x&&pC.getY()==this.y);

    }

}

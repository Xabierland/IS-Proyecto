package is.modelo;

public class Coordenada {
    private int x;
    private int y;
    public Coordenada(int pX, int pY){
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
    public boolean mismaCoordenada(Coordenada pC){
        return (pC.getX()==this.x&&pC.getY()==this.y);

    }

}

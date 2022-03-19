package is.modelo;

public class Coordena {
    private int x;
    private int y;
    public Coordena(int pX, int pY){
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
    public boolean mismaCoordenada(Coordena pC){
        return (pC.getX()==this.x&&pC.getY()==this.y);

    }

}

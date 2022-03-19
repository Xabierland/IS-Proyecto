package is.modelo;

public abstract class Barco {
    protected int dir;
    protected int longitud;
    protected boolean hundido;
    protected Coordena coordena;
    public Barco(int pdir,int px, int py){
        this.dir=pdir;
        this.hundido=false;
        this.coordena =new Coordena(px, py);

    }
    protected abstract void getnombre();
    protected Coordena getCoordenadas(){
        return this.coordena;
    }
    
    protected Coordena[] calcularCoordenadas(){
        Coordena[] coor=new Coordena[longitud];
        for (int i=0; i<longitud;i++){
            switch(dir){
                case 0:
                    coor[i]=new Coordena(coordena.getX(), coordena.getY()-i);
                    break;
                case 1:
                    coor[i]=new Coordena(coordena.getX()+i, coordena.getY());
                    break;
                case 2:
                    coor[i]=new Coordena(coordena.getX(), coordena.getY()+i);
                    break;    
                case 3:
                    coor[i]=new Coordena(coordena.getX()-i, coordena.getY());
                    break;
                        
            }
        }
        return coor;
    }
}

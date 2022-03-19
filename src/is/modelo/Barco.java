package is.modelo;

public abstract class Barco {
    protected int dir;
    protected int longitud;
    protected boolean hundido;
    protected Coordenadas coordenadas;
    public Barco(int pdir,int px, int py){
        this.dir=pdir;
        this.hundido=false;
        this.coordenadas=new Coordenadas(px, py);

    }
    protected abstract void getnombre();
    protected Coordenadas getCoordenadas(){
        return this.coordenadas;
    }
    
    protected Coordenadas[] calcularCoordenadas(){
        Coordenadas[] coor=new Coordenadas[longitud];
        for (int i=0; i<longitud;i++){
            switch(dir){
                case 0:
                    coor[i]=new Coordenadas(coordenadas.getX(),coordenadas.getY()-i);
                    break;
                case 1:
                    coor[i]=new Coordenadas(coordenadas.getX()+i,coordenadas.getY());    
                    break;
                case 2:
                    coor[i]=new Coordenadas(coordenadas.getX(),coordenadas.getY()+i);
                    break;    
                case 3:
                    coor[i]=new Coordenadas(coordenadas.getX()-i,coordenadas.getY());
                    break;
                        
            }
        }
        return coor;
    }
}

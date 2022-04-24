package is.modelo;

public abstract class Barco {
    protected int dir;
    protected int longitud;
    protected boolean hundido;
    protected Coordenada coordena;
    protected String name;
    public Barco(int pdir,int px, int py){
        this.dir=pdir;
        this.hundido=false;
        this.coordena =new Coordenada(px, py);
    }
    
    protected Coordenada[] calcularCoordenadas(){
        Coordenada[] coor=new Coordenada[longitud];
        for (int i=0; i<longitud;i++){
            switch(dir){
                case 0:
                    coor[i]=new Coordenada(coordena.getX(), coordena.getY()-i);
                    break;
                case 1:
                    coor[i]=new Coordenada(coordena.getX()+i, coordena.getY());
                    break;
                case 2:
                    coor[i]=new Coordenada(coordena.getX(), coordena.getY()+i);
                    break;    
                case 3:
                    coor[i]=new Coordenada(coordena.getX()-i, coordena.getY());
                    break;
                        
            }
        }
        return coor;
    }
}

package is.modelo;

import java.util.ArrayList;

public class Flota {
    private ArrayList<Barco> flota;
    public Flota(){
        flota= new ArrayList<>();
        
    }
    public void annadirBarcos(int dir,int type, int x, int y){
        Barco nBarco=BarcosFactory.getBarcosFactory().hacerBarco(dir, type, x, y);
        this.flota.add(nBarco);
        System.out.printf("Se ha añadido a la flota un %s\n",nBarco.name);
        }
    public Barco getBarcoporPos(int x, int y){
        Coordenada c= new Coordenada(x, y);
        Barco nBarco=null;
        for (Barco barco : flota) {
            for (Coordenada i: barco.calcularCoordenadas()) {
                if(i.mismaCoordenada(c)){
                    nBarco=barco;
                }
            }
        }
        return nBarco;
    }

}

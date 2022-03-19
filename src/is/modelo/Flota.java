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
        System.out.println("se ha añadido a la flota un");
        nBarco.getnombre();


        }

    //todo optimizar
    public Barco getBarcoporPos(int x, int y){
        Coordena c= new Coordena(x, y);
      
        
        Barco nBarco=null;
        for (Barco barco : flota) {
            for (Coordena i: barco.calcularCoordenadas()) {
                if(i.mismaCoordenada(c)){
                    nBarco=barco;
                }
            }
        }
        return nBarco;
    }

}

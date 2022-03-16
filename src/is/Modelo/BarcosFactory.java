package is.Modelo;

public class BarcosFactory {
    private static BarcosFactory miBarcosFactory;
    private BarcosFactory(){}
    public static BarcosFactory getBarcosFactory(){
        if(miBarcosFactory==null){
            miBarcosFactory=new BarcosFactory();
        }
        return miBarcosFactory;
    }
    public Barco hacerBarco(int pTipo){
        Barco nBarco=null;
        if(pTipo==1){
            nBarco=new Fragata();
        }
        else{
            if(pTipo==2){
                nBarco=new Destructor();
            }
            else{
                if(pTipo==3){
                    nBarco=new Submarino();
                }
                else{
                    if(pTipo==4){
                        nBarco=new Portavion();
                    }
                }
            }
        }
        return nBarco;
    }
    
}

package is.modelo;

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
        switch (pTipo)
        {
            case 0: nBarco=new Fragata();break;
            case 1: nBarco=new Destructor();break;
            case 2: nBarco=new Submarino();break;
            case 3: nBarco=new Portavion();break;
        }
        return nBarco;
    }
    
}

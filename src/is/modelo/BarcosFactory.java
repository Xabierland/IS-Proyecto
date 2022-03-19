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
    public Barco hacerBarco(int dir,int type, int x, int y){
        Barco nBarco=null;
        switch (type)
        {
            case 1: nBarco=new Fragata(dir,x,y);break;
            case 2: nBarco=new Destructor(dir,x,y);break;
            case 3: nBarco=new Submarino(dir,x,y);break;
            case 4: nBarco=new Portavion(dir,x,y);break;
        }
        return nBarco;
    }
    
}

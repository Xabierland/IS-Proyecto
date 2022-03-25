package is.modelo;

public class ArmaFactory {
    private static ArmaFactory miArmaFactory;
    private ArmaFactory(){}
    public static ArmaFactory getBarcosFactory(){
        if(miArmaFactory==null){
            miArmaFactory=new ArmaFactory();
        }
        return miArmaFactory;
    }
    public Arma hacerArma(int type, boolean finita){
        Arma nArma=null;
        switch (type)
        {
            case 0: nArma=new Bomba(type,finita);break;
            case 1: nArma=new Misil(type, finita);break;
            case 2: nArma=new Radar(type, finita);break;
            case 3: nArma=new Escudo(type, finita);break;
            //case 4: nArma=new Reparar(type);break;
        }
        return nArma;
    }
}

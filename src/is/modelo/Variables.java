package is.modelo;

public class Variables
{
    private static Variables misVariables=null;
    // TamanoTablero x TamanoTablero
    private final int TamanoTablero = 10;
    //IA - Cuanto mas grande el numero mas facil - Simpre mayor que 0
    private final int dificultadIA=3;
    // 0 - Bomba | 1 - Misil | 2 - Radar | 3 - Escudo | 4 - Reparar
    private final int NumArmas = 5;
    //Barcos
    private final int NumBarcos = 10;
    private final int NumFragata = 4;
    private final int NumDestructor = 3;
    private final int NumSubmarino = 2;
    private final int NumPortavion = 1;
    //Tienda
    private final int dineroInicial = 3000;
    private final int precioMisil=500;
    private final int precioRadar=1000;
    private final int precioEscudo =1000;
    private final int precioReparacion=1000;

    private Variables(){}

    public static Variables getMisVariables()
    {
        if(misVariables==null)
        {
            misVariables=new Variables();
        }
        return misVariables;
    }

    //Getters
    //TABLERO
    public int getTamanoTablero() {return TamanoTablero;}
    //IA
    public int getDificultadIA() {return dificultadIA;}
    //ARMAS
    public int getNumArmas() {return NumArmas;}
    //BARCOS
    public int getNumBarcos() {return NumBarcos;}
    public int getNumFragata() {return NumFragata;}
    public int getNumSubmarino(){return NumSubmarino;}
    public int getNumDestructor(){return NumDestructor;}
    public int getNumPortavion(){return NumPortavion;}
    //TIENDA
    public int getDineroInicial() {return dineroInicial;}
    public int getPrecioMisil(){return precioMisil;}
    public int getPrecioRadar(){return precioRadar;}
    public int getPrecioEscudo(){return precioEscudo;}
    public int getPrecioReparacion(){return precioReparacion;}
}

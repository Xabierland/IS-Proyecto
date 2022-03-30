package is.modelo;

public class Variables
{
    private static Variables misVariables=null;
    //ESCALA
    private int escala=1;
    // TamanoTablero x TamanoTablero
    private int TamanoTablero = 10;
    //IA - Cuanto mas grande el numero mas facil - Simpre mayor que 0
    private int dificultadIA=3;
    // 0 - Bomba | 1 - Misil | 2 - Radar | 3 - Escudo | 4 - Reparar
    private final int NumArmas = 5;
    //Barcos
    private final int NumBarcos = 10;
    private final int NumFragata = 4;
    private final int NumDestructor = 3;
    private final int NumSubmarino = 2;
    private final int NumPortavion = 1;
    //Tienda
    private int dineroInicial = 3000;
    private int dineroPorHundir = 500;
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
    //ESCALA
    public void setEscala(int i) {escala=i;}
    //TABLERO
    public int getTamanoTablero() {return TamanoTablero*escala;}
    //IA
    public int getDificultadIA() {return dificultadIA;}
    public void setDificultadIA(int i) {dificultadIA=i;}
    //ARMAS
    public int getNumArmas() {return NumArmas;}
    //BARCOS
    public int getNumBarcos() {return NumBarcos*escala;}
    public int getNumFragata() {return NumFragata*escala;}
    public int getNumSubmarino(){return NumSubmarino*escala;}
    public int getNumDestructor(){return NumDestructor*escala;}
    public int getNumPortavion(){return NumPortavion*escala;}
    //TIENDA
    public int getDineroInicial() {return dineroInicial;}
    public void setDineroInicial(int i) {dineroInicial=i;}
    public int getDineroPorHundir() {return dineroPorHundir;}
    public void setDineroPorHundir(int i) {dineroPorHundir=i;}
    public int getPrecioMisil(){return precioMisil;}
    public int getPrecioRadar(){return precioRadar;}
    public int getPrecioEscudo(){return precioEscudo;}
    public int getPrecioReparacion(){return precioReparacion;}
}

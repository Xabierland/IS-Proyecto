package is.modelo;

public class NPC extends Jugador{
    public NPC(int pIndex) {
        super(pIndex);
        armamento.addArma(0,false,false);
    }



    //Getters & Setters
    public void setDinero(int pDinero)
    {
        dinero=pDinero;
    }



    //BARCOS
    public void anadirBarco(int pX, int pY)
    {
        tablero.addBarco(Partida.getMiPartida().getDireccion(), Partida.getMiPartida().getTipoBarco(), pX, pY, true, flota);
    }

    public void anadirBarcoAuto()
    {
        tablero.addBarcoAutomatico(true, flota);
    }



    //Ataque
    public boolean ataque()
    {
        ataqueIA();
        return true;
    }

    @Override
    public boolean ataque(int pX, int pY, int tipoArma) {
        return false;
    }

    public boolean defensa() {
        ataqueIA();
        return true;
    }

    @Override
    public boolean defensa(int pX, int pY, int tipoArma) {
        return false;
    }

    public void ataqueIA() {
        int pArma, tipoAtaque, cualCoor;
        Coordenada c;
        boolean atacado = false;
        tipoAtaque = getRandomInteger(10, 0);
        cualCoor = getRandomInteger(Variables.getMisVariables().getDificultadIA(), 0);
        while (!atacado)
        {
            switch (tipoAtaque)
            {
                default: //BOMBA 60%
                {
                    pArma = 0;
                    c=IAgetCoordenadas(cualCoor);
                    if(armamento.usarArma(pArma,0,c.getX(),c.getY(),true))
                    {
                        atacado=true;
                    }
                    break;
                }
                case 6: //MISIL 10%
                {
                    pArma = 1;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(cualCoor);
                        if(armamento.usarArma(pArma,0,c.getX(),c.getY(),true))
                        {
                            atacado=true;
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,1))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 7: //RADAR 10%
                {
                    pArma = 2;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(armamento.usarArma(pArma,0,c.getX(),c.getY(),true))
                        {
                            atacado=true;
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,2))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 8: //ESCUDO 10%
                {
                    pArma = 3;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(tablero.getIfBarcoByPos(c.getX(),c.getY())) {
                            if (armamento.usarArma(pArma, 1, c.getX(), c.getY(),true)) {
                                atacado = true;
                            }
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,3))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
                case 9: //REPARACION 10%
                {
                    pArma = 4;
                    if (armamento.existeMunicion(pArma))
                    {
                        c=IAgetCoordenadas(1);
                        if(tablero.getIfBarcoByPos(c.getX(),c.getY())) {
                            if (armamento.usarArma(pArma, 1, c.getX(), c.getY(),true)) {
                                atacado = true;
                            }
                            else
                            {
                                tipoAtaque = getRandomInteger(10, 0);
                            }
                        }
                    }
                    else
                    {
                        if (!Shop.getTienda().comprarArma(1,4))
                        {
                            tipoAtaque = getRandomInteger(10, 0);
                        }
                    }
                    break;
                }
            }
        }
    }

    private Coordenada IAgetCoordenadas(int factor)
    {
        Coordenada c;
        if (factor == 0) {
            c = ListaJugadores.getMiListaJugadores().getJugador(0).getTablero().getCoordenadasDeUnBarco();
        } else {
            c = new Coordenada(getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0), getRandomInteger(Variables.getMisVariables().getTamanoTablero(), 0));
        }
        return c;
    }
}

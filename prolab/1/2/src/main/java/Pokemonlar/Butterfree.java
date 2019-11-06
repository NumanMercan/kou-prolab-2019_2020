package Pokemonlar;

public class Butterfree extends Pokemon {
    private int hasarPuani;
    boolean kartKullanildiMi = false;

    public Butterfree()
    {
        super("Butterfree","Hava");
        this.setHasarPuani(10);
    }

    public int hasarPuaniGoster()
    {
        return this.hasarPuani;
    }

    public int getHasarPuani() {
        return hasarPuani;
    }

    public void setHasarPuani(int hasarPuani) {
        this.hasarPuani = hasarPuani;
    }
}
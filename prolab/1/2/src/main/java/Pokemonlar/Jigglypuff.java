package Pokemonlar;

public class Jigglypuff extends Pokemon {
    private int hasarPuani;
    boolean kartKullanildiMi = false;

    public Jigglypuff()
    {
        super("Jigglypuff","Ses");
        this.setHasarPuani(70);
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
package daw;

/**
 *
 * @author Eloym
 */
class Celula {

    private boolean viva;
    private boolean estadoAnterior;
    private int edad;

    public Celula() {
        this.viva = false;
        this.estadoAnterior = false;
        this.edad = 0;
    }

    public boolean isViva() {
        return viva;
    }

    public boolean getEstadoAnterior() {
        return estadoAnterior;
    }

    public int getEdad() {
        return edad;
    }

    public void setViva(boolean viva) {
        this.estadoAnterior = this.viva;
        this.viva = viva;
        if (viva) {
            this.edad++;
        } else {
            this.edad = 0;
        }
    }

    @Override
    public String toString() {
        return "Celula{"
                + "viva=" + viva
                + ", estadoAnterior=" + estadoAnterior
                + ", edad=" + edad
                + '}';
    }

}

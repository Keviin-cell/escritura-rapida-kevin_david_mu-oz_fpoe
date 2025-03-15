package com.example.escriturarapidaproject.model;

/**
 * Clase que representa la lógica principal del juego "Escritura Rápida".
 * Gestiona los niveles, puntos, oportunidades y el tiempo de juego.
 * @author Kevin Muñoz
 */
public class Game {
    private int nivel;
    private int puntos;
    private int oportunidades;
    private int tiempoRestante;
    private String palabraOFraseActual;
    private boolean modoFrases;

    /**
     * Constructor de la clase Game.
     * Inicializa el juego con el nivel 1, 4 oportunidades y un tiempo base de 20 segundos.
     *
     * @param modoFrases Indica si el juego está en modo frases (true) o palabras (false).
     */
    public Game(boolean modoFrases) {
        this.nivel = 1;
        this.puntos = 0;
        this.oportunidades = 4;
        this.modoFrases = modoFrases;
        this.tiempoRestante = 20;
        this.palabraOFraseActual = modoFrases ? Word.obtenerFrasePorNivel(nivel) : Word.obtenerPalabraPorNivel(nivel);
    }

    /**
     * Avanza al siguiente nivel, aumenta la puntuación y actualiza la palabra/frase.
     * Además, ajusta el tiempo restante según el nivel alcanzado.
     */
    public void avanzarNivel() {
        puntos += 10;
        nivel++;

        // Reiniciar el tiempo base a 20 segundos
        int nuevoTiempo = 20;

        // Reducir el tiempo en niveles específicos
        if (nivel >= 6 && nivel < 11) {
            nuevoTiempo = 18;
        } else if (nivel >= 11 && nivel < 16) {
            nuevoTiempo = 16;
        } else if (nivel >= 16 && nivel < 21){
            nuevoTiempo = 14;
        } else if (nivel >= 21){
            nuevoTiempo = 12;
        }

        tiempoRestante = nuevoTiempo;

        // Asignar nueva palabra o frase
        palabraOFraseActual = modoFrases ? Word.obtenerFrasePorNivel(nivel) : Word.obtenerPalabraPorNivel(nivel);
    }


    /**
     * Reduce el número de oportunidades cuando el jugador comete un error.
     */
    public void perderOportunidad() {
        oportunidades--;
    }

    /**
     * Reduce en 1 segundo el tiempo restante del nivel actual.
     * No permite que el tiempo se reduzca por debajo de 0.
     */
    public void reducirTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    /**
     * Verifica si el juego ha terminado.
     *
     * @return true si el jugador se quedó sin oportunidades, false en caso contrario.
     */
    public boolean juegoTerminado() {
        return oportunidades <= 0;
    }

    /**
     * Obtiene la puntuación actual del jugador.
     *
     * @return El puntaje acumulado.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Obtiene la cantidad de oportunidades restantes.
     *
     * @return Número de oportunidades disponibles.
     */
    public int getOportunidades() {
        return oportunidades;
    }

    /**
     * Obtiene la palabra o frase actual que el jugador debe escribir.
     *
     * @return La palabra o frase del nivel actual.
     */
    public String getPalabraOFraseActual() {
        return palabraOFraseActual;
    }

    /**
     * Obtiene el nivel actual del jugador.
     *
     * @return Nivel en el que se encuentra el jugador.
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene el tiempo restante en el nivel actual.
     *
     * @return Segundos restantes antes de que el tiempo se acabe.
     */
    public int getTiempoRestante() {
        return tiempoRestante;
    }
}

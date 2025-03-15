package com.example.escriturarapidaproject.model;

import java.util.Random;

/**
 * Clase Word que gestiona la selección de palabras y frases según el nivel del jugador.
 * Proporciona palabras y frases de diferentes dificultades (fácil, media, difícil).
 * @author Kevin Muñoz
 */
public class Word {

    // Listas de palabras y frases clasificadas por dificultad
    private static final String[] palabrasFaciles = {
            "Casa", "Sol", "Luz", "Perro", "Gato", "Pan", "Río", "Té", "Mar", "Flor"
    };

    private static final String[] palabrasMedias = {
            "Computadora", "Biblioteca", "Helicóptero", "Estudiante", "Programación",
            "Automóvil", "Espejismo", "Montaña", "Jardinería", "Televisión"
    };

    private static final String[] palabrasDificiles = {
            "Electrodoméstico", "Neurociencia", "Paralelepípedo", "Otorrinolaringólogo",
            "Desoxirribonucleico", "Cinematografía", "Inconstitucionalidad",
            "Esternocleidomastoideo", "Anticonstitucionalmente", "Criptografía"
    };

    private static final String[] frasesFaciles = {
            "El sol brilla", "Hoy es un buen día", "Amo la programación", "Java es genial",
            "La luna es hermosa", "Voy a la escuela", "Me gusta leer", "El cielo es azul",
            "Las flores son bellas", "El perro ladra", "Tengo hambre"
    };

    private static final String[] frasesMedias = {
            "Hoy aprendí algo nuevo en clase", "El agua hierve a cien grados Celsius",
            "Programar es divertido y desafiante", "La naturaleza es increíblemente hermosa",
            "Un astronauta viaja al espacio exterior", "El viento sopla con fuerza en otoño",
            "La tecnología avanza a pasos agigantados", "El océano es profundo y misterioso",
            "Estudiar es la clave del éxito en la vida"
    };

    private static final String[] frasesDificiles = {
            "La perseverancia y la disciplina son esenciales para alcanzar el éxito",
            "El aprendizaje continuo es fundamental en el mundo de la programación",
            "Las ecuaciones matemáticas son fundamentales para la ingeniería",
            "El desarrollo de software requiere lógica, paciencia y creatividad",
            "Los avances en inteligencia artificial están revolucionando el mundo",
            "El cambio climático es un desafío que debemos enfrentar urgentemente",
            "Las estrellas en el cielo nocturno nos recuerdan lo pequeño que somos"
    };

    /**
     * Generador de números aleatorios para seleccionar palabras o frases aleatoriamente.
     */
    private static final Random rand = new Random();

    /**
     * Obtiene una frase aleatoria según el nivel del jugador.
     * Se selecciona una frase de una dificultad específica dependiendo del nivel actual.
     *
     * @param nivel Nivel actual del jugador.
     * @return Una frase aleatoria correspondiente a la dificultad del nivel.
     */
    public static String obtenerFrasePorNivel(int nivel){
        if (nivel <= 5) {
            return frasesFaciles[rand.nextInt(frasesFaciles.length)];
        } else if (nivel <= 10) {
            return frasesMedias[rand.nextInt(frasesMedias.length)];
        } else {
            return frasesDificiles[rand.nextInt(frasesDificiles.length)];
        }
    }

    /**
     * Obtiene una palabra aleatoria según el nivel del jugador.
     * Se selecciona una palabra de una dificultad específica dependiendo del nivel actual.
     *
     * @param nivel Nivel actual del jugador.
     * @return Una palabra aleatoria correspondiente a la dificultad del nivel.
     */
    public static String obtenerPalabraPorNivel(int nivel) {
        if (nivel <= 5) {
            return palabrasFaciles[rand.nextInt(palabrasFaciles.length)];
        } else if (nivel <= 10) {
            return palabrasMedias[rand.nextInt(palabrasMedias.length)];
        } else {
            return palabrasDificiles[rand.nextInt(palabrasDificiles.length)];
        }
    }

}

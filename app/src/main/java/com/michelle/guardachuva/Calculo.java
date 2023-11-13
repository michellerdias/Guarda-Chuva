package com.michelle.guardachuva;

public class Calculo {
    private double anguloValue;
    private double alturaValue;
    private double larguraValue;
    private double volume;

    public Calculo(double angulo, double altura, double largura) {
        this.anguloValue = angulo;
        this.alturaValue = altura;
        this.larguraValue = largura;

        calcularVolume();
    }

    private void calcularVolume() {
        double anguloRadianos = Math.toRadians(anguloValue);
        double alturaEfetiva = alturaValue * Math.cos(anguloRadianos);
        volume = larguraValue * alturaEfetiva * 0.1718;
    }

    public double getVolume() {
        return volume;
    }
}
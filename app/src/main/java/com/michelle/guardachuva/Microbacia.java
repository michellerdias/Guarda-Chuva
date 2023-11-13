package com.michelle.guardachuva;

public class Microbacia {
    String nomeProprietario, cpf, rua, volume, altura, angulo, cep, largura, numero;

    public Microbacia(String nomeProprietario, String cpf, String rua, String volume, String altura, String angulo, String cep, String largura, String numero) {
        this.nomeProprietario = nomeProprietario;
        this.cpf = cpf;
        this.rua = rua;
        this.volume = volume;
        this.altura = altura;
        this.angulo = angulo;
        this.cep = cep;
        this.largura = largura;
        this.numero = numero;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getAngulo() {
        return angulo;
    }

    public void setAngulo(String angulo) {
        this.angulo = angulo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLargura() {
        return largura;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}

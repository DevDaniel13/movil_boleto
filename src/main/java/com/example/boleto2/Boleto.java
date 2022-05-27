package com.example.boleto2;

import java.util.Scanner;

public class Boleto {
    
    private int id;
    private String nombre;
    private int edad;
    private String destino;
    private int viaje;
    private double precio;
    private String fecha;
    
    public Boleto(int id, String nombre, int edad, String destino, int viaje, double precio, String fecha){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.destino = destino;
        this.viaje = viaje;
        this.precio = precio;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public int getViaje() {
        return viaje;
    }
    public void setViaje(int viaje) {
        this.viaje = viaje;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double calcularSubtotal(){
        if(this.getViaje() == 2){
            double subtotal = this.getPrecio()+((this.getPrecio()*0.8));
            return subtotal;
        }else{
            double subtotal = this.getPrecio();
            return subtotal;
        }
    }
    public double calcularImpuesto(){
        double impuesto = this.getPrecio()*0.16;
        return impuesto;
    }
    public double calcularDescuento(){
        if(this.getEdad() >= 60){
            double descuento = this.getPrecio()*0.5;
            return descuento;
        }else{
            double descuento = 0;
            return descuento;
        }
    }
    public double calcularTotal(double subtotal, double impuesto, double descuento){
        double total = (subtotal + impuesto) - descuento;
        return total;
    }

    public String Imprimir(double subtotal, double impuesto, double descuento, double total){
        if(viaje == 1){
            return "DATOS DEL BOLETO\nNo. Boleto: " + id +
                    "\nNombre Cliente: " + nombre +
                    "\nDestino: " + destino +
                    "\nTipo Viaje: " + viaje + " : Sencillo" +
                    "\nPrecio: $" + precio +
                    "\nFecha: " + fecha +
                    "\nIMPORTE\nSubtotal: $" + subtotal +
                    "\nImpuesto 16% (+): $" + impuesto +
                    "\nDescuento (-): $" + descuento +
                    "\nTotal a pagar: $" + total;
        }else if(viaje == 2){
            return "DATOS DEL BOLETO\nNo. Boleto: " + id +
                    "\nNombre Cliente: " + nombre +
                    "\nDestino: " + destino +
                    "\nTipo Viaje: " + viaje + " : Doble" +
                    "\nPrecio: $" + precio +
                    "\nFecha: " + fecha +
                    "\nIMPORTE\nSubtotal: $" + subtotal +
                    "\nImpuesto 16% (+): $" + impuesto +
                    "\nDescuento (-): $" + descuento +
                    "\nTotal a pagar: $" + total;
        }
        return "Captura de datos erronea";
    }
}

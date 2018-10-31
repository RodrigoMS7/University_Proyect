package proyecto2.logic;
// Generated 30/10/2018 06:44:16 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Bien generated by hbm2java
 */
public class Bien  implements java.io.Serializable {


     private Integer codigo;
     private Categoria categoria;
     private Solicitud solicitud;
     private Integer cantidad;
     private String modelo;
     private String descripcion;
     private String marca;
     private Double precio;
     private Set activos = new HashSet(0);

    public Bien() {
    }

    public Bien(String descripcion, String marca, String modelo, Integer cantidad, Double precio) {
//       this.categoria = categoria;
//       this.solicitud = solicitud;
        this.cantidad = cantidad;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precio = precio;
        // this.activos = activos;
    }
    
    public Bien(Categoria categoria, Solicitud solicitud, Integer cantidad, String modelo, String descripcion, String marca, Double precio, Set activos) {
       this.categoria = categoria;
       this.solicitud = solicitud;
       this.cantidad = cantidad;
       this.modelo = modelo;
       this.descripcion = descripcion;
       this.marca = marca;
       this.precio = precio;
       this.activos = activos;
    }
   
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Solicitud getSolicitud() {
        return this.solicitud;
    }
    
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Set getActivos() {
        return this.activos;
    }
    
    public void setActivos(Set activos) {
        this.activos = activos;
    }




}



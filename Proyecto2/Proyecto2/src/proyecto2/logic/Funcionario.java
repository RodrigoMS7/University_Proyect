package proyecto2.logic;
// Generated 24/10/2018 06:40:55 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Funcionario generated by hbm2java
 */
public class Funcionario  implements java.io.Serializable {


     private String id;
     private String nombre;
     private Set labors = new HashSet(0);
     private Set usuarios = new HashSet(0);
     private Set solicituds = new HashSet(0);

    public Funcionario() {
    }

	
    public Funcionario(String id) {
        this.id = id;
    }
    
     public Funcionario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Funcionario(String id, String nombre, Set labors, Set usuarios, Set solicituds) {
       this.id = id;
       this.nombre = nombre;
       this.labors = labors;
       this.usuarios = usuarios;
       this.solicituds = solicituds;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getLabors() {
        return this.labors;
    }
    
    public void setLabors(Set labors) {
        this.labors = labors;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }
    public Set getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set solicituds) {
        this.solicituds = solicituds;
    }




}



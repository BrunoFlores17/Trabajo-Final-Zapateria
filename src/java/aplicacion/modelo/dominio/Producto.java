package aplicacion.modelo.dominio;
// Generated 11/07/2019 12:45:52 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
 public class Producto  implements java.io.Serializable {


     private int codProducto;
     private Categoria categorias;
     private String descripcion;
     private Double precio;
     private Integer stock;
     private byte [] foto;
     private boolean estado;
     private Set detalles = new HashSet(0);
     

    public Producto() {
        
    }

	
    public Producto(int codProducto, Categoria categorias) {
        this.codProducto = codProducto;
        this.categorias = categorias;
    }
    public Producto(int codProducto, Categoria categorias, String descripcion, Double precio, Integer stock, Set detalles) {
       this.codProducto = codProducto;
       this.categorias = categorias;
       this.descripcion = descripcion;
       this.precio = precio;
       this.stock = stock;
       this.detalles = detalles;
    }
   
    public int getCodProducto() {
        return this.codProducto;
    }
    
    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }
    public Categoria getCategorias() {
        return this.categorias;
    }
    
    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Integer getStock() {
        return this.stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Set getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(Set detalles) {
        this.detalles = detalles;
    }

    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }




}



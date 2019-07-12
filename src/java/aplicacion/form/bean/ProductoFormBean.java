/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.form.bean;

import aplicacion.bean.CategoriaBean;
import aplicacion.bean.ProductoBean;
import aplicacion.modelo.dominio.Categoria;
import aplicacion.modelo.dominio.Producto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ProductoFormBean implements Serializable{
    @ManagedProperty(value =  "#{productoBean}")
    private ProductoBean productoBean;
    @ManagedProperty(value =  "#{categoriaBean}")
    private CategoriaBean categoriaBean;
    private Producto prod;
    private Integer cod;
    private Random numero;
    private Categoria cat;
    private List<Categoria> categorias;
    private List<Producto>listadoProductos;
    private Producto unProducto;
    private transient UploadedFile archivo = null;
    private int num=10;

    /**
     * Creates a new instance of ProductoFormBean
     */
    public ProductoFormBean() {
        prod = new Producto();
        categorias = new ArrayList();
    }
    
    public StreamedContent getFotoProducto() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if(context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE){
            return new DefaultStreamedContent();
        }else {
            String codigo = context.getExternalContext().getRequestParameterMap().get("codProd");
            Producto producto = getProductoBean().consultar(Integer.parseInt(codigo));  
            if(producto.getFoto() == null){
                return null;
            }else{
                return new DefaultStreamedContent(new ByteArrayInputStream(producto.getFoto()));
            }
        }
    }
    public void agregarProducto(){
        getProd().setEstado(true);
        if(getArchivo() != null){
            byte[] contents = getArchivo().getContents();
            getProd().setFoto(contents);
        }else{
            getProd().setFoto(null);
        }
        try{
            getProductoBean().agregarProd(getProd());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Creado"));    
        }
        catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo al Crear Producto"));    
        }
        setProd(new Producto());
    }
    public List<Producto> obtenerListaProductos(){
        return listadoProductos = getProductoBean().obtenerListado();
    }
    public List<Categoria> obtenerCategorias(){
        return getCategoriaBean().obtenerListado();
    }
    
    public void eliminarProd(){
        productoBean.elminarProd(unProducto);
        FacesMessage msg = new FacesMessage("Producto Eliminado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        unProducto = new Producto();
        
    }
    
    public void modificarProd(){
        getProductoBean().modificarProd(getUnProducto());
        FacesMessage msg = new FacesMessage("Producto Modificado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setUnProducto(new Producto());
    }
    
    public void establecerProducto(Producto otroProducto){
        setUnProducto(otroProducto);
    }
    
    public ProductoBean getProductoBean() {
        return productoBean;
    }

    public void setProductoBean(ProductoBean productoBean) {
        this.productoBean = productoBean;
    }

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Producto> getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(List<Producto> listadoProductos) {
        this.listadoProductos = listadoProductos;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Random getNumero() {
        return numero;
    }

    public void setNumero(Random numero) {
        this.numero = numero;
    }

    /**
     * @return the categoriaBean
     */
    public CategoriaBean getCategoriaBean() {
        return categoriaBean;
    }

    /**
     * @param categoriaBean the categoriaBean to set
     */
    public void setCategoriaBean(CategoriaBean categoriaBean) {
        this.categoriaBean = categoriaBean;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    /**
     * @return the unProducto
     */
    public Producto getUnProducto() {
        return unProducto;
    }

    /**
     * @param unProducto the unProducto to set
     */
    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the archivo
     */
    public UploadedFile getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
}

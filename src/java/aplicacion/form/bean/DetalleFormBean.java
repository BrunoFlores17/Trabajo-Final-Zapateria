/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.form.bean;

import aplicacion.bean.DetalleBean;
import aplicacion.bean.ModoPagoBean;
import aplicacion.modelo.dominio.Detalle;
import aplicacion.modelo.dominio.Factura;
import aplicacion.modelo.dominio.ModoPago;
import aplicacion.modelo.dominio.Producto;
import aplicacion.modelo.dominio.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santi
 */
@ManagedBean
@SessionScoped
public class DetalleFormBean  implements Serializable{
    @ManagedProperty(value = "#{detalleBean}")
    private DetalleBean detalleBean;
    @ManagedProperty(value = "#{modoPagoBean}")
    private ModoPagoBean modoPagoBean;
    private Factura unaFactura;
    private Producto unProducto;
    private Detalle unDetalle;
    private ModoPago modoPago;
    private List<Detalle> listadoDetalleCompra;
    private int cantidadProd;
    private List tiposModoPago;

    /**
     * Creates a new instance of DetalleFormBean
     */
    public DetalleFormBean(){
        detalleBean =new DetalleBean();
        unaFactura=new Factura();
        unProducto=new Producto();
        unDetalle=new Detalle();
        modoPago=new ModoPago();
        listadoDetalleCompra=new ArrayList();
        tiposModoPago=new ArrayList();
        tiposModoPago.add("Efectivo");
        tiposModoPago.add("Tarjeta de Credito");
        tiposModoPago.add("Tarjeta Debito");
    }
    
    public void agregarDetalle(){
        getUnDetalle().setFactura(getUnaFactura());
        getUnDetalle().setProductos(getUnProducto());
        getUnDetalle().setIddetalle((int) (Math.random()*1000000));
         try {
             getDetalleBean().agregarDetalle(getUnDetalle());
             FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle agregado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo agregar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void eliminarDetalle(){
         try {
             getDetalleBean().eliminarDetalle(getUnDetalle());
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle eliminado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void modificarDetalle(){
         try {
             getDetalleBean().modificarDetalle(getUnDetalle());
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle modificado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    
    public String confirmarPago(){
        String res=null;
        getModoPago().setIdmodoPago((int) (Math.random()*1000000));
        getModoPago().setInteres(5.0);
        getUnaFactura().setNumFactura((int) (Math.random()*1000000));
        getUnaFactura().setModopago(getModoPago());
        getUnaFactura().setFecha(new Date());
        
        getUnaFactura().setUsuarios((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado"));
        return res="pCo?faces-redirect=true";
    }
    public void confirmarCompra(Producto unProducto){
        setUnDetalle(new Detalle());
        getUnDetalle().setIddetalle((int) (Math.random()*1000000));
        getUnDetalle().setProductos(unProducto);
        getUnDetalle().setCantidad(getCantidadProd());
        String total=String.valueOf(getCantidadProd() * unProducto.getPrecio());
        getUnDetalle().setPrecioProdCant(total);
        getListadoDetalleCompra().add(getUnDetalle());
        
    }
    public double calculoTotalProducto(Producto unProducto){
     return(getCantidadProd() * unProducto.getPrecio());
    }
    public void cargarFactura()/*throws JRException, IOException*/{
        for(Detalle det : getListadoDetalleCompra()){
            det.setFactura(unaFactura);
            getDetalleBean().agregarDetalle(det);
      
        }  
        /*listarArrayUsuarioPdf()*/;
    }
   
    /*public void listarArrayUsuarioPdf() throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        List<Detalle> listadoDetalles = new ArrayList();
        listadoDetalles = getDetalleBean().obtenerDetalles();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/facturaReport.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(listadoDetalles))
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=factura-report.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }*/
    
    /**
     * @return the detalleBean
     */
    public DetalleBean getDetalleBean() {
        return detalleBean;
    }

    /**
     * @param detalleBean the detalleBean to set
     */
    public void setDetalleBean(DetalleBean detalleBean) {
        this.detalleBean = detalleBean;
    }

    /**
     * @return the unaFactura
     */
    public Factura getUnaFactura() {
        return unaFactura;
    }

    /**
     * @param unaFactura the unaFactura to set
     */
    public void setUnaFactura(Factura unaFactura) {
        this.unaFactura = unaFactura;
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
     * @return the unDetalle
     */
    public Detalle getUnDetalle() {
        return unDetalle;
    }

    /**
     * @param unDetalle the unDetalle to set
     */
    public void setUnDetalle(Detalle unDetalle) {
        this.unDetalle = unDetalle;
    }

    /**
     * @return the modoPago
     */
    public ModoPago getModoPago() {
        return modoPago;
    }

    /**
     * @param modoPago the modoPago to set
     */
    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }

    /**
     * @return the listadoDetalleCompra
     */
    public List<Detalle> getListadoDetalleCompra() {
        return listadoDetalleCompra;
    }

    /**
     * @param listadoDetalleCompra the listadoDetalleCompra to set
     */
    public void setListadoDetalleCompra(List<Detalle> listadoDetalleCompra) {
        this.listadoDetalleCompra = listadoDetalleCompra;
    }

    /**
     * @return the cantidadProd
     */
    public int getCantidadProd() {
        return cantidadProd;
    }

    /**
     * @param cantidadProd the cantidadProd to set
     */
    public void setCantidadProd(int cantidadProd) {
        this.cantidadProd = cantidadProd;
    }

    /**
     * @return the tiposModoPago
     */
    public List getTiposModoPago() {
        return tiposModoPago;
    }

    /**
     * @param tiposModoPago the tiposModoPago to set
     */
    public void setTiposModoPago(List tiposModoPago) {
        this.tiposModoPago = tiposModoPago;
    }

    /**
     * @return the modoPagoBean
     */
    public ModoPagoBean getModoPagoBean() {
        return modoPagoBean;
    }

    /**
     * @param modoPagoBean the modoPagoBean to set
     */
    public void setModoPagoBean(ModoPagoBean modoPagoBean) {
        this.modoPagoBean = modoPagoBean;
    }
    
}

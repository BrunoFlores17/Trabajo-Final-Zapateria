/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean;

import aplicacion.dao.IFacturaDAO;
import aplicacion.dao.mysql.FacturaDaoImp;
import aplicacion.modelo.dominio.Factura;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author santi
 */
@ManagedBean
@SessionScoped
public class FacturaBean {
    private IFacturaDAO facturaDAO;
    private Factura unaFactura;

    /**
     * Creates a new instance of FacturaBean
     */
    public FacturaBean() {
        facturaDAO=new FacturaDaoImp();
        unaFactura=new Factura();
    }
    public void crearFactura(Factura unaFactura){
        getFacturaDAO().agregar(unaFactura);
    }
    public void eliminarFactura(Factura unaFactura){
        getFacturaDAO().eliminar(unaFactura);
    }
    public void modificarFactura(Factura unaFactura){
        getFacturaDAO().modificar(unaFactura);
    }
    public Factura obtenerFactura(int numFactura){
        return getFacturaDAO().obtenerFactura(numFactura);
    }

    /**
     * @return the facturaDAO
     */
    public IFacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    /**
     * @param facturaDAO the facturaDAO to set
     */
    public void setFacturaDAO(IFacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
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
}
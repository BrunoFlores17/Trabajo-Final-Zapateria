/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean;

import aplicacion.dao.IClienteDAO;
import aplicacion.dao.mysql.ClienteDaoImp;
import aplicacion.modelo.dominio.Cliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Santi
 */
@ManagedBean
@SessionScoped
public class ClienteBean {
    private IClienteDAO clienteDAO;
    /**
     * Creates a new instance of ClienteBean
     */
    public ClienteBean() {
        clienteDAO=new ClienteDaoImp();
    }
    
    public void agregarCliente(Cliente unCliente){
    clienteDAO.agregar(unCliente);
    }
    public void modificarCliente(Cliente unCliente){
        clienteDAO.modificar(unCliente);
    }
    public void eliminarCliente(Cliente unCliente){
        clienteDAO.eliminar(unCliente);
    }

    /**
     * @return the clienteDAO
     */
    public IClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    /**
     * @param clienteDAO the clienteDAO to set
     */
    public void setClienteDAO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
}

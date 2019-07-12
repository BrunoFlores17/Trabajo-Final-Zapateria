/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.Cliente;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IClienteDAO {
    void agregar(Cliente unUsuario);
    void modificar(Cliente unUsuario);
    void eliminar(Cliente unUsuario);
    Cliente consultar(String nombreUsuario);
    List<Cliente> obtenerListaClientesActivos();
}

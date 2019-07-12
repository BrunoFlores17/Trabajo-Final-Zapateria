/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.Factura;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IFacturaDAO {
    Factura obtenerFactura(int numFactura);
    void agregar(Factura unaFactura);
    void eliminar(Factura unaFactura);
    void modificar(Factura unaFactura);
}

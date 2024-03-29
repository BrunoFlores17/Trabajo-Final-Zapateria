/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.mysql;

import aplicacion.dao.IProductoDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Producto;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Bruno
 */
public class ProductoDaoImp implements IProductoDAO{

    @Override
    public void agregar(Producto unProducto) {  
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();        
        
        session.beginTransaction(); 
        System.out.println("99999999999999999999999999999999999999999999999999"+ unProducto);
        session.save(unProducto);
        
        session.getTransaction().commit();
        session.close();
        
    }

    @Override
    public void eliminar(Producto unProducto) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(unProducto);
        System.out.println("Eliminado jajaja");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(Producto unProducto) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(unProducto);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Producto consultar(Integer codProducto) {
        List<Producto> listado = new ArrayList();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.like("codProducto", codProducto));
        listado = criteria.list();
        session.getTransaction().commit();
        session.close();        
        return listado.get(0);
    }

    @Override
    public List<Producto> obtenerListadoProductos() {
        List<Producto> listado = new ArrayList();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("estado", true));
        listado = criteria.list();
        session.getTransaction().commit();
        session.close();
        return listado;
    }
    
}

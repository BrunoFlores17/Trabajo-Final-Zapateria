/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.mysql;

import aplicacion.dao.IClienteDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Bruno
 */
public class ClienteDaoImp implements IClienteDAO{

    @Override
    public void agregar(Cliente unUsuario) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(unUsuario);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(Cliente unUsuario) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(unUsuario);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void eliminar(Cliente unUsuario) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(unUsuario);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Cliente consultar(String nombreUsuario) {
         List<Cliente> listado ;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.like("nombreUsuario", nombreUsuario));
        listado = criteria.list();
        session.getTransaction().commit();
        session.close();        
        return listado.get(0);
    }

    @Override
    public List<Cliente> obtenerListaClientesActivos() {
        List<Cliente> listado ;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Cliente.class);
        listado = criteria.list();
        session.getTransaction().commit();
        session.close();
        return listado;
    }    
}

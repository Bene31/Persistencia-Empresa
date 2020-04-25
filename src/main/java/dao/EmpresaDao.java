/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Empresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Igor
 */
public class EmpresaDao {

    public void salvarAtualizar(Empresa empresa) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if (empresa.getCnpj() != null) {
            empresa = em.merge(empresa);
        }
        em.persist(empresa);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(Empresa empresa) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        empresa = em.merge(empresa);
        em.remove(empresa);
        em.getTransaction().commit();
        em.close();
    }

    public List<Empresa> pesquisar(Empresa empresa) {
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Empresa e "
                + "where 1 = 1 ");

        if (empresa.getCnpj() != null
                && !empresa.getCnpj().equals("")) {
            sql.append("and e.cnpj like :cnpj ");
        }
        
        if (empresa.getNomeFantasia() != null
                && !empresa.getNomeFantasia().equals("")) {
            sql.append("and e.nomeFantasia like :nomeFantasia ");
        }

        Query query = em.createQuery(sql.toString());

        if (empresa.getCnpj() != null && !empresa.getCnpj().equals("")) {
            query.setParameter("cnpj", "%" + empresa.getCnpj() + "%");
        }
        
        if (empresa.getNomeFantasia() != null && !empresa.getNomeFantasia().equals("")) {
            query.setParameter("nomeFantasia", "%" + empresa.getNomeFantasia() + "%");
        }
        return query.getResultList();
    }

}

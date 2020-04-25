/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Servico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Igor
 */
public class ServicoDao {

    public void salvarAtualizar(Servico servico) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if (servico.getCodigo() != 0) {
            servico = em.merge(servico);
        }
        em.persist(servico);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(Servico servico) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        servico = em.merge(servico);
        em.remove(servico);
        em.getTransaction().commit();
        em.close();
    }

    public List<Servico> pesquisar(Servico servico) {
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Servico s "
                + "where 1 = 1 ");
        if (servico.getCodigo() != 0) {
            sql.append("and s.codigo = :codigo ");
        }

        if (servico.getTitulo() != null
                && !servico.getTitulo().equals("")) {
            sql.append("and s.titulo like :titulo ");
        }

        if (servico.getEmpresa() != null && servico.getEmpresa().getCnpj() != null) {
            sql.append("and s.empresa.cnpj = :cnpjEmpresa");
        }

        Query query = em.createQuery(sql.toString());

        if (servico.getCodigo() != 0) {
            query.setParameter("codigo", servico.getCodigo());
        }

        if (servico.getTitulo() != null && !servico.getTitulo().equals("")) {
            query.setParameter("titulo", "%" + servico.getTitulo() + "%");
        }

        if (servico.getEmpresa() != null && servico.getEmpresa().getCnpj() != null) {
            query.setParameter("cnpjEmpresa", servico.getEmpresa().getCnpj());
        }

        return query.getResultList();
    }

}

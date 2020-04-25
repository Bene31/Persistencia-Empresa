/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chris
 */
public class Conexao {
    
    public static EntityManagerFactory emf;
    private static Conexao conexao;

    public Conexao() {
        
        emf = Persistence.createEntityManagerFactory("br.com.hibernate_TrabalhoFornecedor_jar_1.0-SNAPSHOTPU");
    }

    public synchronized static EntityManager getEntityManager() {
        if (conexao == null){
            conexao = new Conexao();
        }
        
        return emf.createEntityManager();
    }

   
    
}

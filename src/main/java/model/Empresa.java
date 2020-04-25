/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Conexao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Igor
 */
@Entity
@Table(name = "EMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByCnpj", query = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Empresa.findByNomeFantasia", query = "SELECT e FROM Empresa e WHERE e.nomeFantasia = :nomeFantasia")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CNPJ", length = 32)
    private String cnpj;

    @Basic(optional = false)
    @Column(name = "NOME_FANTASIA", length = 255)
    private String nomeFantasia;

    @Formula("(SELECT COALESCE(COUNT(*), 0) FROM SERVICO S WHERE S.CNPJ_EMPRESA = CNPJ)")
    private int quantidadeServicos;

    @Formula("(SELECT COALESCE(SUM(S.VALOR), 0) FROM SERVICO S WHERE S.CNPJ_EMPRESA = CNPJ)")
    private float valorTotalServicos;
    
    @Formula("(SELECT COALESCE(S.TITULO, '') FROM SERVICO S WHERE S.VALOR IN (SELECT MAX(SS.VALOR) FROM SERVICO SS WHERE SS.CNPJ_EMPRESA = CNPJ ))")
    private String servicoMaisCaro;

    @OneToMany(orphanRemoval = true, mappedBy = "empresa")
    private List<Servico> servicos;

    public Empresa() {
    }

    public Empresa(String cnpj) {
        this.cnpj = cnpj;
    }

    public Empresa(String cnpj, String nomeFantasia) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @XmlTransient
    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
    
    public String getServicoMaisCaro() {
        return servicoMaisCaro;
    }

    public int getQuantidadeServicos() {
        return quantidadeServicos;
    }

    public float getValorTotalServicos() {
        return valorTotalServicos;
    }

    public void validar() throws Exception {
        if (this.cnpj == null || this.cnpj.replace(".", "").replace("/", "").replace("-", "").trim().equals("")) {
            throw new Exception("Campo CNPJ precisa ser preenchido!");
        }
        
        if (this.nomeFantasia == null || this.nomeFantasia.equals("")) {
            throw new Exception("Campo Nome Fantasia precisa ser preenchido!");
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnpj != null ? cnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.cnpj == null && other.cnpj != null) || (this.cnpj != null && !this.cnpj.equals(other.cnpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Empresa[ cnpj=" + cnpj + " ]";
    }

}

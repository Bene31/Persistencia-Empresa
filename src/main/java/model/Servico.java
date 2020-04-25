/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Igor
 */
@Entity
@Table(name = "SERVICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s"),
    @NamedQuery(name = "Servico.findByCodigo", query = "SELECT s FROM Servico s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Servico.findByTitulo", query = "SELECT s FROM Servico s WHERE s.titulo = :titulo"),
    @NamedQuery(name = "Servico.findByDataRealizacao", query = "SELECT s FROM Servico s WHERE s.dataRealizacao = :dataRealizacao"),
    @NamedQuery(name = "Servico.findByValor", query = "SELECT s FROM Servico s WHERE s.valor = :valor")})
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ServicoSeq")
    @SequenceGenerator(name="ServicoSeq", sequenceName="SERVICO_SEQ", allocationSize = 1)
    private int codigo;
    
    @Basic(optional = false)
    @Column(name = "TITULO", length = 255)
    private String titulo;
    
    @Basic(optional = false)
    @Column(name = "DATA_REALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRealizacao;
    
    @Basic(optional = false)
    @Column(name = "VALOR")
    private float valor;
    
    @JoinColumn(name = "CNPJ_EMPRESA", referencedColumnName = "CNPJ")
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Servico() {
    }

    public Servico(int codigo) {
        this.codigo = codigo;
    }

    public Servico(int codigo, String titulo, Date dataRealizacao, float valor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.dataRealizacao = dataRealizacao;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public void validar() throws Exception {
        if (this.empresa == null || this.empresa.getCnpj() == null || this.empresa.getCnpj().replace(".", "").replace("/", "").replace("-", "").trim().equals("")) {
            throw new Exception("Campo Empresa precisa ser preenchido!");
        }
        
        if (this.titulo == null || this.titulo.equals("")) {
            throw new Exception("Campo Título precisa ser preenchido!");
        }
        
        if (this.dataRealizacao == null || this.dataRealizacao.equals("")) {
            throw new Exception("Campo Data Realização precisa ser preenchido!");
        }
        
        if (this.valor == 0) {
            throw new Exception("Campo Valor precisa ser preenchido!");
        }
    }

    @Override
    public String toString() {
        return "model.Servico[ codigo=" + codigo + " ]";
    }
    
}

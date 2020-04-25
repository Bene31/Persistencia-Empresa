/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpresaDao;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javassist.tools.rmi.RemoteException;
import model.Empresa;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author terra
 */
public class EmpresaController {

    private final PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);
    private Empresa empresaDigitado;
    private Empresa empresaSelecionado;
    private List<Empresa> empresasTabelas;
    private EmpresaDao empresadao;

    public Empresa getEmpresaDigitado() {
        return empresaDigitado;
    }

    public void setEmpresaDigitado(Empresa empresaDigitado) {
        Empresa oldEmpresaDigitado = this.empresaDigitado;
        this.empresaDigitado = empresaDigitado;
        propertyChangeSupport.firePropertyChange("empresaDigitado", oldEmpresaDigitado, empresaDigitado);
    }

    public Empresa getEmpresaSelecionado() {
        return empresaSelecionado;
    }

    public void setEmpresaSelecionado(Empresa empresaSelecionado) {
        setEmpresaDigitado(empresaSelecionado);
        this.empresaSelecionado = empresaSelecionado;
    }

    public List<Empresa> getEmpresasTabelas() {
        return empresasTabelas;
    }

    public void setEmpresasTabelas(List<Empresa> empresasTabelas) {
        this.empresasTabelas = empresasTabelas;
    }

    public EmpresaController() {
        empresadao = new EmpresaDao();
        empresasTabelas = ObservableCollections.observableList(new ArrayList<Empresa>());
        novo();
        pesquisar();
    }
    
    public void salvar(Empresa empresa) throws Exception, RemoteException {
        empresa.validar();
        empresadao.salvarAtualizar(empresa);
    }

    public void salvar() throws Exception, RemoteException {
        empresaDigitado.validar();
        empresadao.salvarAtualizar(empresaDigitado);
        novo();
        pesquisar();
    }
    
    public void excluir(Empresa empresa) {
        empresadao.excluir(empresa);
    }

    public void excluir() {
        empresadao.excluir(empresaSelecionado);
        novo();
        pesquisar();
    }

    public final void novo() {
        setEmpresaDigitado(new Empresa());
    }
    
    public Empresa pesquisar(String cnpj) {
        Empresa e = new Empresa();
        e.setCnpj(cnpj);
        
        List<Empresa> resultadoPesquisa = empresadao.pesquisar(e);
        
        if (resultadoPesquisa != null && resultadoPesquisa.size() > 0)
            return resultadoPesquisa.get(0);
        
        return null;
    }

    public final void pesquisar() {
        List<Empresa> resultadoPesquisa = empresadao.pesquisar(empresaDigitado); 
        
        empresasTabelas.clear();
        empresasTabelas.addAll(resultadoPesquisa);
    }

    public void addPropertyChangeListener(PropertyChangeListener e) {
        propertyChangeSupport.addPropertyChangeListener(e);
    }

    public void removePropertyChangeListener(PropertyChangeListener e) {
        propertyChangeSupport.removePropertyChangeListener(e);
    }
}

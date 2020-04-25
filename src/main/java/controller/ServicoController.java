/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServicoDao;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.Servico;
import javassist.tools.rmi.RemoteException;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author terra
 */
public class ServicoController {

    private final PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);
    private Servico servicoDigitado;
    private Servico servicoSelecionado;
    private List<Servico> servicosTabelas;
    private ServicoDao servicodao;

    public Servico getServicoDigitado() {
        return servicoDigitado;
    }

    public void setServicoDigitado(Servico servicoDigitado) {
        Servico oldServicoDigitado = this.servicoDigitado;
        this.servicoDigitado = servicoDigitado;
        propertyChangeSupport.firePropertyChange("servicoDigitado", oldServicoDigitado, servicoDigitado);
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        setServicoDigitado(servicoSelecionado);
        this.servicoSelecionado = servicoSelecionado;
    }

    public List<Servico> getServicosTabelas() {
        return servicosTabelas;
    }

    public void setServicosTabelas(List<Servico> servicosTabelas) {
        this.servicosTabelas = servicosTabelas;
    }

    public ServicoController() {
        servicodao = new ServicoDao();
        servicosTabelas = ObservableCollections.observableList(new ArrayList<Servico>());
        novo();
        pesquisar();
    }
    
    public void salvar(Servico servico) throws Exception, RemoteException {
        servico.validar();
        servicodao.salvarAtualizar(servico);
    }

    public void salvar() throws Exception, RemoteException {
        servicoDigitado.validar();
        servicodao.salvarAtualizar(servicoDigitado);
        novo();
        pesquisar();
    }
    
    public void excluir(Servico servico) {
        servicodao.excluir(servico);
    }

    public void excluir() {
        servicodao.excluir(servicoSelecionado);
        novo();
        pesquisar();
    }

    public final void novo() {
        setServicoDigitado(new Servico());
    }

    public final void pesquisar() {
        List<Servico> resultadoPesquisa = servicodao.pesquisar(servicoDigitado);
        
        servicosTabelas.clear();
        servicosTabelas.addAll(resultadoPesquisa);
    }

    public void addPropertyChangeListener(PropertyChangeListener e) {
        propertyChangeSupport.addPropertyChangeListener(e);
    }

    public void removePropertyChangeListener(PropertyChangeListener e) {
        propertyChangeSupport.removePropertyChangeListener(e);
    }

}

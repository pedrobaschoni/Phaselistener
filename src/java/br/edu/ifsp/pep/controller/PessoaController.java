package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.modelo.Pessoa;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class PessoaController implements Serializable{
    private Pessoa pessoa;
    private Pessoa pessoaAutenticada;
    
    @EJB
    private PessoaDAO pessoaDAO;

    public PessoaController() {
        this.pessoa = new Pessoa();
    }
    
    public void autenticar() {
        this.pessoaAutenticada = this.pessoaDAO.buscarPorLoginSenha(pessoa.getLogin(), pessoa.getSenha());
        
        if(pessoaAutenticada != null) {
            Util.info("Login realizado com sucesso");
        } else {
            Util.error("Login ou senha inválidos");
        }
        this.pessoa = new Pessoa();
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaAutenticada() {
        return pessoaAutenticada;
    }

    public void setPessoaAutenticada(Pessoa pessoaAutenticada) {
        this.pessoaAutenticada = pessoaAutenticada;
    }

    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }
    
    
}

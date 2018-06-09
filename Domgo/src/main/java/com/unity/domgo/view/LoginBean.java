/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.view;

import com.unity.domgo.model.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UsuarioBean usuarioBean;
    private String login;
    private String senha;
    private Usuario usuario;
    private Boolean logado = false;

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String logar() {

        for (Usuario user : usuarioBean.getAll()) {
            if (login.equals(user.getLogin()) && senha.equals(user.getSenha())) {
                this.logado = true;
                this.usuario = user;
                return "/restrito/sistema/search?faces-redirect=true";

            }

        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario e/ou senha invalidas", ""));
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        logado = false;
        return "/faces/index.xhtml?faces-redirect=true";
    }

}

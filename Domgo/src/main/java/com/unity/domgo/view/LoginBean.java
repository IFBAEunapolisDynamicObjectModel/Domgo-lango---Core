/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.view;

import com.unity.domgo.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

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
        if (login.equals("w") && senha.equals("1")) {
            usuario = new Usuario();
            logado = true;
            return "/redirect/index.xhtml?faces-redirect=true";

        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario e/ou senha invalidas", ""));
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        logado = false;
        return "/login.xhtml?faces-redirect=true";
    }

}

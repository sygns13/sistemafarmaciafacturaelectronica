/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.clases;

/**
 *
 * @author Omr
 */
public class Accesos {

    private String usuario = "";
    private String login = "";
    private String idUsuario = "";
    private String tipoUsuario = "";

    private Accesos() {
    }

    public static Accesos getInstance() {
        return AccesosHolder.INSTANCE;
    }

    private static class AccesosHolder {

        private static final Accesos INSTANCE = new Accesos();
    }

    public String getIdUsuario() {
        //System.out.println("3"+this.idUsuario);
        return idUsuario;
    }

    public void setIdUsuario(String idUsuar) {
        this.idUsuario = idUsuar;
        //System.out.println("1"+idUsuar);
        //System.out.println("2"+this.idUsuario);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}

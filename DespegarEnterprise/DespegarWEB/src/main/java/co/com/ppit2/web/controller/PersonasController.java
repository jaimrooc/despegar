package co.com.ppit2.web.controller;

import co.com.ppit2.controller.PersonasJpaController;
import co.com.ppit2.despegarejb.sessionsBeans.PersonasSessionBean;
import co.com.ppit2.despegarejbimplementation.PersonasSessionBeanRemote;
import co.com.ppit2.model.Personas;
import co.com.ppit2.model.dto.DtoAccionesGUI;
import co.com.ppit2.web.controller.utils.Constants;
import co.com.ppit2.web.controller.utils.FunnelFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.ppit2.web.controller.utils.WrapUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Sessions;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Controlador para la gestion el login
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
@Controller
public class PersonasController {

    // Objeto que manipula las interacciones de la GUI
    private DtoAccionesGUI dtoAccionesGUI;

    // Objeto con atributos convenios, y su backup
    private Personas personas;

    private List<Personas> personasLista;
    private List<Personas> personasListaBackup;
    private Personas personasListaFiltro;
    private boolean esUsuarioAdmin;

    private boolean nuevoRegistro;

    // Mesaje general
    private WrapUtil<String> msg;

    @RequestMapping(value = "/persona.do", method = RequestMethod.GET)
    public String ingresoPersonas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            return "personas/personas";
        }
        return "personas/personas";
    }

    @Init
    public void metodoPrincipal() {
        defaultValues();
    }

    /**
     * Metodo encargado de resetear los valores actuales a valores por defecto
     */
    private void defaultValues() {
        msg = new WrapUtil();
        dtoAccionesGUI = new DtoAccionesGUI();
        cambiarInterfaz("lista");
        personasListaFiltro = new Personas();
        nuevoRegistro = false;
        PersonasSessionBeanRemote n = new PersonasSessionBean();
        try {
            personasListaBackup = n.getTodasLasPersonas();
        } catch (Exception ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        personasLista = personasListaBackup;
    }

    @Command()
    @NotifyChange("*")
    public void filtrarLista() {
        personasLista = FunnelFilter.personas(personasListaBackup, personasListaFiltro);
    }

    public void cambiarInterfaz(String accion) {
        if (accion.equals("lista")) {
            dtoAccionesGUI.accionListarFormulario();
        } else if (accion.equals("nuevo")) {
            dtoAccionesGUI.accionIngresarRegistro();
        }
    }

    @Command()
    @NotifyChange("*")
    public void nuevo() {
        personas = new Personas();
        nuevoRegistro = true;
        cambiarInterfaz("nuevo");
        try {
            esUsuarioAdmin = ((Personas) Sessions.getCurrent().getAttribute(Constants.USER_SESSION)).isAdmin();
        } catch (Exception e) {
        }
    }

    @Command
    @NotifyChange("*")
    public void editar(@BindingParam("nodo") Personas persona) {
        try {
            personas = persona;
            try {
                esUsuarioAdmin = personas.isAdmin();
            } catch (Exception e) {
                esUsuarioAdmin = false;
            }
            nuevoRegistro = false;
            cambiarInterfaz("nuevo");
        } catch (Exception ex) {
            Logger.getLogger(ConveniosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Command()
    @NotifyChange("*")
    public void atras(@BindingParam("estado") String accion) {
        defaultValues();
    }

    @Command
    @NotifyChange("*")
    public void guardar() {

        if (camposDePersonasValidos()) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
            PersonasJpaController controller = new PersonasJpaController(emf);
            try {
                if (personas.getSegundoApellido() == null || personas.getSegundoApellido().isEmpty()) {
                    personas.setSegundoApellido(" ");
                }
                if (nuevoRegistro) {
                    controller.create(personas);
                    personasLista.add(personas);
                } else {
                    controller.edit(personas);
                }
            } catch (Exception ex) {
                msg.verMensaje(Labels.getLabel("proceso.no.exitoso"), WrapUtil.ERROR);
            }
            msg.verMensaje(Labels.getLabel("proceso.exitos"), WrapUtil.EXITO);
            cambiarInterfaz("lista");
        } else {
            cambiarInterfaz("nuevo");
        }
    }

    public boolean camposDePersonasValidos() {
        if ((personas == null)
                || (personas.getIdentificacion() == null || personas.getIdentificacion().isEmpty())
                || (personas.getNombre() == null || personas.getNombre().isEmpty())
                || (personas.getPrimerApellido() == null || personas.getPrimerApellido().isEmpty())
                || (personas.getPassword() == null || personas.getPassword().isEmpty())
                || (personas.getRepassword() == null || personas.getRepassword().isEmpty())) {
            msg.verMensaje(Labels.getLabel("validaciones.campos.minimos"), WrapUtil.INFORMACION);
            return false;
        }
        if (!personas.getPassword().equals(personas.getRepassword())) {
            msg.verMensaje(Labels.getLabel("password.not.equals"), WrapUtil.INFORMACION);
            return false;
        }
        return true;
    }

    public DtoAccionesGUI getDtoAccionesGUI() {
        return dtoAccionesGUI;
    }

    public void setDtoAccionesGUI(DtoAccionesGUI dtoAccionesGUI) {
        this.dtoAccionesGUI = dtoAccionesGUI;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public List<Personas> getPersonasLista() {
        return personasLista;
    }

    public void setPersonasLista(List<Personas> personasLista) {
        this.personasLista = personasLista;
    }

    public List<Personas> getPersonasListaBackup() {
        return personasListaBackup;
    }

    public void setPersonasListaBackup(List<Personas> personasListaBackup) {
        this.personasListaBackup = personasListaBackup;
    }

    public Personas getPersonasListaFiltro() {
        return personasListaFiltro;
    }

    public void setPersonasListaFiltro(Personas personasListaFiltro) {
        this.personasListaFiltro = personasListaFiltro;
    }

    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }

    public WrapUtil<String> getMsg() {
        return msg;
    }

    public void setMsg(WrapUtil<String> msg) {
        this.msg = msg;
    }

    public boolean isEsUsuarioAdmin() {
        return esUsuarioAdmin;
    }

    public void setEsUsuarioAdmin(boolean esUsuarioAdmin) {
        this.esUsuarioAdmin = esUsuarioAdmin;
    }

}

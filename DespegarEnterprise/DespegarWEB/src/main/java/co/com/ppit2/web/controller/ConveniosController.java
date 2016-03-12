package co.com.ppit2.web.controller;

import co.com.ppit2.controller.ConveniosJpaController;
import co.com.ppit2.model.Convenios;
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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;

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
public class ConveniosController {

    // Objeto que manipula las interacciones de la GUI
    private DtoAccionesGUI dtoAccionesGUI;

    // Objeto con atributos convenios, y su backup
    private Convenios convenios;

    private List<Convenios> conveniosLista;
    private List<Convenios> conveniosListaBackup;
    private Convenios conveniosListaFiltro;

    private boolean nuevoRegistro;

    // Mensaje footer
    private static final String footerMessage = Labels.getLabel("footer.list");

    // Mesaje general
    private WrapUtil<String> msg;

    @RequestMapping(value = "/convenios.do", method = RequestMethod.GET)
    public String ingresoPersonas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "convenios/convenio";
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
        conveniosListaFiltro = new Convenios();
        nuevoRegistro = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        ConveniosJpaController controller = new ConveniosJpaController(emf);
        try {
            conveniosListaBackup = controller.getTodosLosConvenios();
            conveniosLista = conveniosListaBackup;
        } catch (Exception ex) {
            Logger.getLogger(ConveniosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Command()
    @NotifyChange("*")
    public void filtrarLista() {
        conveniosLista = FunnelFilter.convenios(conveniosListaBackup, conveniosListaFiltro);
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
        convenios = new Convenios();
        convenios.setId(null);
        convenios.setPorcentaje(0.0);
        convenios.setTipoEntidad("Ingresar entidad");
        nuevoRegistro = true;
        cambiarInterfaz("nuevo");
    }

    @Command
    @NotifyChange("*")
    public void editar(@BindingParam("nodo") Convenios convenio) {
        try {
            convenios = convenio;
            nuevoRegistro = true;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        ConveniosJpaController controller = new ConveniosJpaController(emf);
        try {
            if (convenios.getId() == null) {
                controller.create(convenios);
                conveniosLista.add(convenios);
            } else {
                controller.edit(convenios);
            }
        } catch (Exception ex) {
            Logger.getLogger(ConveniosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cambiarInterfaz("lista");
    }

    public WrapUtil<String> getMsg() {
        return msg;
    }

    public void setMsg(WrapUtil<String> msg) {
        this.msg = msg;
    }

    public DtoAccionesGUI getDtoAccionesGUI() {
        return dtoAccionesGUI;
    }

    public void setDtoAccionesGUI(DtoAccionesGUI dtoAccionesGUI) {
        this.dtoAccionesGUI = dtoAccionesGUI;
    }

    public Convenios getConvenios() {
        return convenios;
    }

    public void setConvenios(Convenios convenios) {
        this.convenios = convenios;
    }

    public List<Convenios> getConveniosLista() {
        return conveniosLista;
    }

    public void setConveniosLista(List<Convenios> conveniosLista) {
        this.conveniosLista = conveniosLista;
    }

    public Convenios getConveniosListaFiltro() {
        return conveniosListaFiltro;
    }

    public void setConveniosListaFiltro(Convenios conveniosListaFiltro) {
        this.conveniosListaFiltro = conveniosListaFiltro;
    }

    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }

    public String getFooter() {
        return String.format(footerMessage, conveniosLista.size());
    }

}

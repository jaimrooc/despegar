package co.com.ppit2.web.controller;

import co.com.ppit2.web.controller.utils.WrapUtil;
import co.edu.polijic.ejb.interfaces.TransaccionesSessionBeanRemote;
import co.edu.polijic.pagos.modelos.TipoPago;
import co.edu.polijic.pagos.modelos.Transaccion;
import java.util.Hashtable;
import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

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
public class HomeController {

    private WrapUtil<String> msgInfo = new WrapUtil<String>();
    private WrapUtil<String> cambiarPassword = new WrapUtil<String>();
    private TestPagos testPagos;

    @Resource(mappedName = "jms/TestQueue")
    private Queue testQueue;

    @Resource(mappedName = "jms/TestConnectionFactory")
    private QueueConnectionFactory test;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String inicio_default() {
        return "contenido/home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String inicio() {
        return "contenido/home";
    }

    @RequestMapping(value = "/home.do", method = RequestMethod.GET)
    public String printWelcome() {
        return "contenido/home";
    }

    @Init
    public void init() {
        testPagos = new TestPagos();
    }

    @Command
    @NotifyChange("*")
    public void imprimir() {
        try {

            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            properties.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            properties.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            properties.put("org.omg.CORBA.ORBInitialHost", "localhost");
            properties.put("org.omg.CORBA.ORBInitialPort", "3700");
            Context context = new InitialContext(properties);

            TransaccionesSessionBeanRemote bean = (TransaccionesSessionBeanRemote) context.lookup("java:global/CES4-ear-1.0-SNAPSHOT/CES4-ejb-1.0-SNAPSHOT/jdbc/despegarPagosDS!co.edu.polijic.ejb.interfaces.TransaccionesSessionBeanRemote");
            
            Transaccion transaccion = new Transaccion();
            TipoPago tp = new TipoPago();
            tp.setCdtipopago(testPagos.getTipoPago().getCdtipopago());
            tp.setDsdescripcion(testPagos.getTipoPago().getDsdescripcion());
            transaccion.setCdtipopago(tp);
            transaccion.setCdtarjetaorigen(testPagos.getTarjetaOrigen());
            transaccion.setCdtarjetadestino(testPagos.getTarjetaDestino());
            transaccion.setVltransaccion(testPagos.getValor());
            transaccion.setNmcuotaspago(testPagos.getNmcuotaspago());
            bean.createTransaccion(transaccion);
        } catch (Exception e) {
        }

    }

    @Command
    @NotifyChange("*")
    public void imprimir2() {
//        System.out.println("Imprimir");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
//        PersonasJpaController persona = new PersonasJpaController(emf);
//        
//        try {
//            for (Personas nodo : persona.getPrimerasCincoPersonas()) {
//                System.out.println(nodo.getNombre());
//            }
//        } catch (Exception ex) {
//            System.out.println("Error fue: " + ex);
//        }
//
//        try {
//            Hashtable properties = new Hashtable();
//            properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
//            properties.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
//            properties.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
//            properties.put("org.omg.CORBA.ORBInitialHost", "localhost");
//            properties.put("org.omg.CORBA.ORBInitialPort", "3700");
//            NewServiceLocator nsl = new NewServiceLocator(properties);
//            QueueConnectionFactory qcf = (QueueConnectionFactory) nsl.getConnectionFactory("jms/TestConnectionFactory");
//            Queue q = (Queue) nsl.getDestination("jms/TestQueue");
//
//            QueueConnection connection = qcf.createQueueConnection();
//            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//            QueueSender sender = session.createSender(q);
//            sender.send(session.createTextMessage("Envio mensaje prueba"));
//            System.out.println("Enviado exitosamente");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("Fin envio mensaje");
    }

    public WrapUtil<String> getCambiarPassword() {
        return cambiarPassword;
    }

    public void setCambiarPassword(WrapUtil<String> cambiarPassword) {
        this.cambiarPassword = cambiarPassword;
    }

    public WrapUtil<String> getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(WrapUtil<String> msgInfo) {
        this.msgInfo = msgInfo;
    }

    public TestPagos getTestPagos() {
        return testPagos;
    }

    public void setTestPagos(TestPagos testPagos) {
        this.testPagos = testPagos;
    }

}

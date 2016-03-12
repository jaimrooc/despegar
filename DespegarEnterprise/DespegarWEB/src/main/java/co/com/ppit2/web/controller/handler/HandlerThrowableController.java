package co.com.ppit2.web.controller.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Init;

/**
 * @author	Jaime Andres Rojas Ocampo
 * @version 1.0 Manejador de excepciones
 *
 * Historial:
 *
 * Fecha	Inic	Descripcion ------------	----
 * ----------------------------------------------- 2015-3-12	JARO	Codigo Inicial
 *
 */
@Controller
public class HandlerThrowableController {

    private final Logger logger = Logger.getLogger(getClass());

    /**
     * Guarda el continido del log
     *
     * @param exc
     */
    @Init
    public void init(@BindingParam("exception") Exception exc) {
        logger.error(exc.getMessage(), exc);
    }
}

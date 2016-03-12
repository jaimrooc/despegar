package co.com.ppit2.web.controller.utils;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 * 
 * Envoltorio de datos de tipo, mensajes y css
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
public class WrapUtil<T> {

    public static String MSG_INFO = "info";
    public static String MSG_ALERTA = "alerta";
    public static String MSG_ERROR = "error";
    public static String MSG_EXITO = "exito";

    public static String ALERTA = "alerta";
    public static String INFORMACION = "info";
    public static String EXITO = "exito";
    public static String ERROR = "error";

    public static String SCLASS_NEEDED = " needed-field";
    public static String SCLASS_SUCCESS = " success-field";

    public static String TXT_INFO = " labeltexInfo";
    public static String TXT_ERROR = " labeltexError";

    private String mensaje = "";
    private boolean visible = false;
    private String sclass = "";

    private T type;

    public WrapUtil() {
        this(null, null, null, false);
    }

    public WrapUtil(T type, String msg) {
        this(type, msg, null, true);
    }

    public WrapUtil(T type, String msg, String sclass, boolean visible) {
        this.type = type;
        this.mensaje = msg;
        this.sclass = sclass;
        this.visible = visible;
    }

    public void clear() {
        this.clear(null);
    }

    public void clear(T type) {
        this.type = type;
        this.mensaje = "";
        this.sclass = "";
        this.visible = false;
    }

    public void verMensaje(String mensaje, String sclass) {
        this.mensaje = mensaje;
        this.visible = true;
        this.sclass = sclass;
    }

    public boolean isEmpty() {
        boolean esVacio = (type == null || ((type instanceof String) && (type
                .toString() == null || type.toString().isEmpty())));
        return esVacio;
    }

    public boolean isEquals(T typeCompare) {
        if (type == null || typeCompare == null) {
            return false;
        }
        boolean isEqualse = type.equals(typeCompare);
        return isEqualse;
    }

    public void verObject(T type, String sclass) {
        this.verObject(type, "", sclass);
    }

    public void verObject(T type, String mensaje, String sclass) {
        this.type = type;
        verMensaje(mensaje, sclass);
    }

    /**
     * *
     * Append sclass to box
     *
     * @param sclass class to append
     */
    public void appendSclass(String sclass) {
        if (this.sclass == null) {
            this.sclass = "";
        }
        this.sclass += sclass;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public static String getSclassNeeded() {
        return SCLASS_NEEDED;
    }

    public static String getSclassSuccess() {
        return SCLASS_SUCCESS;
    }

    public static String getAlerta() {
        return ALERTA;
    }

    public static String getInformacion() {
        return INFORMACION;
    }

    public static String getExito() {
        return EXITO;
    }

    public static String getError() {
        return ERROR;
    }

    public static String getTxtInfo() {
        return TXT_INFO;
    }

    public static String getTxtAlert() {
        return TXT_ERROR;
    }

    public static String getMsgInfo() {
        return MSG_INFO;
    }

    public static void setMsgInfo(String msgInfo) {
        MSG_INFO = msgInfo;
    }

    public static String getMsgAlerta() {
        return MSG_ALERTA;
    }

    public static void setMsgAlerta(String msgAlerta) {
        MSG_ALERTA = msgAlerta;
    }

    public static String getMsgError() {
        return MSG_ERROR;
    }

    public static void setMsgError(String msgError) {
        MSG_ERROR = msgError;
    }

    public static String getMsgExito() {
        return MSG_EXITO;
    }

    public static void setMsgExito(String msgExito) {
        MSG_EXITO = msgExito;
    }

    public static String getTxtError() {
        return TXT_ERROR;
    }

    public static void setTxtError(String txtError) {
        TXT_ERROR = txtError;
    }

    public static void setAlerta(String alerta) {
        ALERTA = alerta;
    }

    public static void setInformacion(String informacion) {
        INFORMACION = informacion;
    }

    public static void setExito(String exito) {
        EXITO = exito;
    }

    public static void setError(String error) {
        ERROR = error;
    }

    public static void setSclassNeeded(String sclassNeeded) {
        SCLASS_NEEDED = sclassNeeded;
    }

    public static void setSclassSuccess(String sclassSuccess) {
        SCLASS_SUCCESS = sclassSuccess;
    }

    public static void setTxtInfo(String txtInfo) {
        TXT_INFO = txtInfo;
    }
}

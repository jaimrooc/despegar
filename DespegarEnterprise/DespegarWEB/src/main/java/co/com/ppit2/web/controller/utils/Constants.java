package co.com.ppit2.web.controller.utils;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 *
 * Clase que contiene los atributos constantes para cargar los estilos y datos
 * estaticos
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
public class Constants {
    
    // Constante con valor de 'Persistence Unit Name'
    public static final String PERSISTENCE_UNIT_NAME = "despegarEjemplo";

    // Constante con valor en session
    public static final String USER_SESSION = "personaSession";
    
    // Msg Genericos
    public static final String MSG_INFO = "INFO";
    public static final String MSG_ERROR = "ERROR";
    public static final String MSG_EXITO = "EXITO";
    public static final String MSG_ALERTA = "ALERTA";

    // Estados de las vistas
    public static final String VIEW_LISTAR = "LISTAR";
    public static final String VIEW_VER = "VER";
    public static final String VIEW_EDITAR = "EDITAR";
    public static final String VIEW_NUEVO = "NUEVO";
    public static final String VIEW_INIT = "INIT";
    public static final String VIEW_GUARDAR = "SAVE";

    // Constantes para solicitudes, Asignaciones
    public static final String VIEW_HISTORIAL = "HISTORIAL";
    public static final String VIEW_LISTAR_OUTPUT = "SALIDA";
    public static final String VIEW_LISTAR_INPUT = "ENTRADA";
    public static final String VIEW_LISTAR_RESPOSE = "RESPUESTA";
    public static final String VIEW_LISTAR_ALOCATION = "ASIGNACION";
    public static final String VIEW_PRINCIPAL = "PRINCIPAL";
    public static final String VIEW_DESASIGNAR = "DESASIGNAR";
    public static final String VIEW_LIST_DETALLE = "DETALLE";

    // Estados Profile Security
    public static final String MENU = "MENU";
    public static final String SUBMENU = "SUBMENU";
    public static final String ACCION = "ACCION";
    public static final String VINCULO = "VINCULO";
    public static final String VIEW_VER_SOLICITUD = "VER_SOLICITUD";
    public static final String FALSE = "false";

    // Permisos en el menu
    public static final String MODULO_CLASES = "Clases";
    public static final String MODULO_CONTENIDO_DATOS = "Contenido de datos";
    public static final String MODULO_DISCIPLINAS = "Disciplinas";

	// Permisos en el submenu
//	public static final String MODULO_TIPO_REQUERIMIENTOS = "TIPO_REQUERIMIENTOS";
//	public static final String MODULO_PLANTILLAS 	= "PLANTILLAS";
//	public static final String MODULO_ROLES         = "ROL";
//	public static final String MODULO_CARGO         = "CARGO";
//	public static final String MODULO_DESIGNACION   = "DESIGNACION";
//	public static final String MODULO_TORRE         = "LINEA_SERVICIOS";
//	public static final String MODULO_SECTORES      = "SECTORES";
//	public static final String MODULO_AMBIENTE_ASIGNACION = "AMBIENTE_ASIGNACION";
//	public static final String MODULO_LOCALIZACION_GEOGRAFICA = "LOCALIZACION_GEOGRAFICA";
//	public static final String MODULO_REGISTRO_DE_AUDITORIA = "REGISTRO_DE_AUDITORIA";
//	public static final String MODULO_REINICIO_PWD = "REINICIO_PWD";
//	public static final String MODULO_AYUDA = "AYUDA";
//	public static final String MODULO_TIPO_TAREA_INTERNA   = "TIPO_TAREA_INTERNA";
    /**
     * ACCIONES Administracion-ROL
     *
     */
    public static final String ADMIN_ROL_NUEVO = "ROL_NUEVO";
    public static final String ADMIN_ROL_MODIFICAR = "ROL_MODIFICAR";
    public static final String ADMIN_ROL_GENERAR_PERFIL = "ROL_GENERAR_PERFIL";

    public static final String SESSION_MENU_PERMISSION = "PERMISSION_MENU";
    public static final String SESSION_SUBMENU_PERMISSION = "PERMISSION_SUBMENU";

    /**
     * estados de autenticacion
     */
    public static final String AUTHENTICATION_P = "P";
    /**
     * Constantes para el manejo de session
     */
    public static final String SESSION_USER = "USER";
    public static final String SESSION_USERNAME = "USERNAME";
    public static final String SESSION_FULL_NAME = "FULL_NAME";
    public static final String SESSION_USER_LANG = "USER_LANG";

    public static final String SESSION_TIPO_REQ = "TIPO_REQ";
    public static final String SESSION_TIPO_FASE = "TIPO_FASE";
    public static final String SESSION_PLANTILLA = "PLANTILLA";
    public static final String SESSION_PLANTILLA_FASE = "PLANTILLA_FASE";
    public static final String SESSION_PROYECTO = "PROYECTO";
    public static final String SESSION_REQUERIMIENTO = "REQUERIMIENTO";
    public static final String SESSION_ASOCIADO = "ASOCIADO";
    public static final String SESSION_ALUMNO = "ALUMNO";
    public static final String SESSION_CLIENTE_ID = "CLIENTE_ID";
    public static final String SESSION_ACCOUNT = "ACCOUNT";
    public static final String SESSION_TORRE = "TORRE";

    public static final String SESSION_REPORTES = "REPORTES";

    /**
     * Constantes para el manejo del tree del profileSecurity de roles.
     */
    public static final String SESSION_PROFILE_PERMISSION = "PERMISSION_PROFILE";
    public static final String SESSION_PROFILE_SECURITY_ROL = "PROFILE_SECURITY_ROL";
    public static final String SESSION_LIST_PROFILE_SECURITY_ROL = "LIST_PROFILE_SECURITY_ROL";
    public static final String SESSION_INSERT_PROFILE_SECURITY_ROL = "INSERT_PROFILE_SECURITY_ROL";
    public static final String SESSION_VIEW_EDITAR_ROL = "VIEW_EDITAR_ROL";
    public static final String SESSION_PROFILE_SELECT = "PROFILE_SELECT";
    public static final String SESSION_UPDATE_PROFILE_SECURITY_ROL = "UPDATE_PROFILE_SECURITY_ROL";

    public static final String SESSION_CUSTOMER_SECURITY = "PERMISSION_CUSTOMER";
    public static final String SESSION_PROFILE_SELECT_CUSOMER = "PROFILE_SELECT_CUSOMER";
    public static final String SESSION_CUSTOMER_CHECK_ALL = "CUSTOMER_CHECK_ALL";
    public static final String SESSION_PROFIL_CHECK_ALL = "PROFIL_CHECK_ALL";

    /**
     * Constantes para el manejo del tree del profileSecurity de osociados.
     *
     */
    public static final String SESSION_VIEW_EDITAR_ASOCIADO = "VIEW_EDITAR_ASOCIADO";
    public static final String SESSION_PROFILE_SELECT_ASOCIADO = "PROFILE_SELECT_ASOCIADO";
    public static final String SESSION_PROFILE_PERMISSION_ASOCIADO = "PROFILE_PERMISSIONT_ASOCIADO";

    /**
     * Constantes para los render de los arboles.
     *
     */
    public static final String RENDER_REQUERIMIENTO = "REQUERIMIENTO";
    public static final String RENDER_ASIGNACION_TAREA = "ASIGNACION_TAREA";
    public static final String RENDER_HOME_PENDIENTE_PROYECTO = "HOME_PENDIENTE_PROYECTO";
    public static final String RENDER_AYUDAS_LINEA = "AYUDAS_LINEA";

    /**
     * Constantes para manejo de archivos y plantillas
     */
    public static final String SESSION_FILE_ROLE_MASSIVE = "ROLE_MASSIVE";
    public static final String SESSION_FILE_ROLE_MASSIVE_NAME = "NAME_FILE";

    public static final String HELP_FILE_IMG = "imagenesAyuda";
    public static final String TEMPLATE_FILE_ROLE_MASSIVE = "templateFileRoleMassive";
    public static final String TEMPORARY_FILES = "rolesMasivos";
    public static final String TEMPLATE_FILE_ROLE_MASSIVE_NAME = "Plantilla_Asignacion_Roles_Masivo.xls";

    /**
     * Constantes Para el manejo de recoradatorios
     */
    public static final String RECORDATORIO_PROYECTOS = "PROYECTOS";

}

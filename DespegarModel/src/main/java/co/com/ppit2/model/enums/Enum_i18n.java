package co.com.ppit2.model.enums;

import java.io.Serializable;

/**
 * @author Jaime Andres Rojas Ocampo
 * @version 1.0
 * 
 * Lista de idiomas
 *
 * Historial:
 *
 * Fecha: 2015-05-01
 *
 * Inic: JARO
 *
 * Descripcion: Codigo Inicial
 */
public enum Enum_i18n implements Serializable {

	EN ("en", "language.english"),
	ES ("es", "language.spanish");
	
	
	Enum_i18n(String codigo, String i18n) {
		this.codigo = codigo;
		this.i18n = i18n;
	}
	
	private String i18n;
	private String codigo;
	
	public String getI18n() {
		return i18n;
	}
	public void setI18n(String i18n) {
		this.i18n = i18n;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
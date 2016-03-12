package co.com.ppit2.model.enums;

import java.io.Serializable;

/**
 *	@author	Jaime Andres Rojas Ocampo
 *	@version 1.0
 *	Modelo para la gestionar el contenidos de (Estado de usuario)
 *
 *	Historial:
 *
 *	Fecha		Inic	Descripcion
 *	------------	----	-----------------------------------------------
 *	2014-1-9	JARO	Codigo Inicial
 *
 */
public enum EnumUserState implements Serializable {
	/** Show the user state pending for active*/
	P ("Pending"),
	/** Show the user state active. These user can log on the application. */
	A ("Active"),
	/** Show the user state locked for log on the application, These user should reset password or recovery password for the platform.*/
	L ("Locked"); 
	
	EnumUserState(String descripcion) {
		this.description = descripcion;
	}
	
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

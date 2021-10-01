package com.clip.springboot.clip.api.enums;

public enum TipoCocinaEnum {
	MEXICANA(1L, "MEXICANA"), ITALIANA(2L, "ITALIANA"), JAPONESA(3L, "JAPONESA");
	
	private Long id;
	private String descripcion;
	
	private TipoCocinaEnum(Long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static String descripcionId(Long id) {
		for (TipoCocinaEnum item : TipoCocinaEnum.values()) {
			if (item.id.equals(id)) {
				return item.descripcion;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public static TipoCocinaEnum findById(Long id) {
		for (TipoCocinaEnum obj : TipoCocinaEnum.values()) {
			if (obj.id.equals(id)) {
				return obj;
			}
		}
		throw new IllegalArgumentException();
	}
}

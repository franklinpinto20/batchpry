package com.terminal.api.batch;

import java.math.BigInteger;

public class EntryDispatchEntity {
	

	private  String usuario;
	private  String clave;
	private  String numeroDespacho;
	private  BigInteger terId;
	private  String nitEmpresaTppc1;
	private  BigInteger claseVehiculo;
	private  BigInteger nivelServicio;
	private  String placa;
	private   String dptoOrigenRuta;
	private  String municipioOrigenRuta;
	private  String dptoDestinoRuta;
	private  String municipioDestinoRuta;
	private  String viaRuta;
	private  String fechaDespacho;
	private  String horaDespacho;
	private   String minutoDespacho;
	private  BigInteger pasajerosDespacho;
	private  BigInteger tipoDespacho;
	private  BigInteger tipoDespachoAutorizado;
	private  BigInteger tipoConvenio;
	private  BigInteger nitEmpresaTppc2;
	private  BigInteger nitEmpresaTe;
	private  BigInteger terminalesRecorridoRuta;
	private  BigInteger valorTasaUso;
	private  BigInteger origenPlaca;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNumeroDespacho() {
		return numeroDespacho;
	}
	public void setNumeroDespacho(String numeroDespacho) {
		this.numeroDespacho = numeroDespacho;
	}
	public BigInteger getTerId() {
		return terId;
	}
	public void setTerId(BigInteger terId) {
		this.terId = terId;
	}
	public String getNitEmpresaTppc1() {
		return nitEmpresaTppc1;
	}
	public void setNitEmpresaTppc1(String nitEmpresaTppc1) {
		this.nitEmpresaTppc1 = nitEmpresaTppc1;
	}
	public BigInteger getClaseVehiculo() {
		return claseVehiculo;
	}
	public void setClaseVehiculo(BigInteger claseVehiculo) {
		this.claseVehiculo = claseVehiculo;
	}
	public BigInteger getNivelServicio() {
		return nivelServicio;
	}
	public void setNivelServicio(BigInteger nivelServicio) {
		this.nivelServicio = nivelServicio;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getDptoOrigenRuta() {
		return dptoOrigenRuta;
	}
	public void setDptoOrigenRuta(String dptoOrigenRuta) {
		this.dptoOrigenRuta = dptoOrigenRuta;
	}
	public String getMunicipioOrigenRuta() {
		return municipioOrigenRuta;
	}
	public void setMunicipioOrigenRuta(String municipioOrigenRuta) {
		this.municipioOrigenRuta = municipioOrigenRuta;
	}
	public String getDptoDestinoRuta() {
		return dptoDestinoRuta;
	}
	public void setDptoDestinoRuta(String dptoDestinoRuta) {
		this.dptoDestinoRuta = dptoDestinoRuta;
	}
	public String getMunicipioDestinoRuta() {
		return municipioDestinoRuta;
	}
	public void setMunicipioDestinoRuta(String municipioDestinoRuta) {
		this.municipioDestinoRuta = municipioDestinoRuta;
	}
	public String getViaRuta() {
		return viaRuta;
	}
	public void setViaRuta(String viaRuta) {
		this.viaRuta = viaRuta;
	}
	public String getFechaDespacho() {
		return fechaDespacho;
	}
	public void setFechaDespacho(String fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}
	public String getHoraDespacho() {
		return horaDespacho;
	}
	public void setHoraDespacho(String horaDespacho) {
		this.horaDespacho = horaDespacho;
	}
	public String getMinutoDespacho() {
		return minutoDespacho;
	}
	public void setMinutoDespacho(String minutoDespacho) {
		this.minutoDespacho = minutoDespacho;
	}
	public BigInteger getPasajerosDespacho() {
		return pasajerosDespacho;
	}
	public void setPasajerosDespacho(BigInteger pasajerosDespacho) {
		this.pasajerosDespacho = pasajerosDespacho;
	}
	public BigInteger getTipoDespacho() {
		return tipoDespacho;
	}
	public void setTipoDespacho(BigInteger tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}
	public BigInteger getTipoDespachoAutorizado() {
		return tipoDespachoAutorizado;
	}
	public void setTipoDespachoAutorizado(BigInteger tipoDespachoAutorizado) {
		this.tipoDespachoAutorizado = tipoDespachoAutorizado;
	}
	public BigInteger getTipoConvenio() {
		return tipoConvenio;
	}
	public void setTipoConvenio(BigInteger tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}
	public BigInteger getNitEmpresaTppc2() {
		return nitEmpresaTppc2;
	}
	public void setNitEmpresaTppc2(BigInteger nitEmpresaTppc2) {
		this.nitEmpresaTppc2 = nitEmpresaTppc2;
	}
	public BigInteger getNitEmpresaTe() {
		return nitEmpresaTe;
	}
	public void setNitEmpresaTe(BigInteger nitEmpresaTe) {
		this.nitEmpresaTe = nitEmpresaTe;
	}
	public BigInteger getTerminalesRecorridoRuta() {
		return terminalesRecorridoRuta;
	}
	public void setTerminalesRecorridoRuta(BigInteger terminalesRecorridoRuta) {
		this.terminalesRecorridoRuta = terminalesRecorridoRuta;
	}
	public BigInteger getValorTasaUso() {
		return valorTasaUso;
	}
	public void setValorTasaUso(BigInteger valorTasaUso) {
		this.valorTasaUso = valorTasaUso;
	}
	public BigInteger getOrigenPlaca() {
		return origenPlaca;
	}
	public void setOrigenPlaca(BigInteger origenPlaca) {
		this.origenPlaca = origenPlaca;
	}
     //Borrar de aqui hacia abajo

    private Long id;
    private String name;
    private String description;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


public class Clientes {
	
	public enum Status{
		ATIVO,CANCELADO;
	}
	
	
	
	private Long id;
	private String name;
	private Status status;
	private Departamento departamento;
	
	
	
	public Clientes(Long id, String name, Status status, Departamento departamento) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.departamento = departamento;
	}
	

	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


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
	
	
	
	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


	public void Inativar() {
		setStatus(status.CANCELADO);
		System.out.printf("ID: %d Nome: %s -- Status: %s%n",getId() ,getName(), getStatus());
		
	}
	
	

}

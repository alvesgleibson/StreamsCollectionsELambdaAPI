import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class TestandoInterface {

	public static void main(String[] args) {
		
		
		
		List<String>nomes = new ArrayList<>();
		
		nomes.add("Ana");
		nomes.add("Paulo");
		nomes.add("Elias");
		nomes.add("João");
		
		System.out.println(nomes);
		
		System.out.println("--------------------------------");
		nomes.remove("João");
		for(int i =0; i < nomes.size(); i++) {
			System.out.printf("Id: %d nome: %s%n",i, nomes.get(i));
		}
		
		
		System.out.println("--------------------------------");
		
		
		for (String string : nomes) {
			System.out.println(string);
		}
		
		
		System.out.println("--------------------------------");
		nomes.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
				
			}
		});
		
		
		System.out.println("--------------------------------");
		nomes.forEach(nome -> {
			System.out.println(nome);
		});
		
		
		System.out.println("--------------------------------");
		
		nomes.forEach(System.out::println);
		
		System.out.println("--------------------------------");
		
		List<Clientes> clientes = new ArrayList<>();
		
		
		clientes.add(new Clientes(1L, "Celso", Clientes.Status.ATIVO, new Departamento("TI", "Portugual")));
		clientes.add(new Clientes(2L, "Selma", Clientes.Status.ATIVO, new Departamento("Design", "EUA")));
		clientes.add(new Clientes(3L, "Saulo", Clientes.Status.ATIVO, new Departamento("Telecom", "França")));
		clientes.add(new Clientes(4L, "Miguel", Clientes.Status.ATIVO, new Departamento("Vendas", "Polonia")));
		clientes.add(new Clientes(5L, "Rosebrigue", Clientes.Status.ATIVO, new Departamento("Marketing", "Peru")));
		clientes.add(new Clientes(6L, "Rosalba", Clientes.Status.ATIVO, new Departamento("Futebol", "Chile")));
		clientes.add(new Clientes(7L, "Henrique", Clientes.Status.ATIVO, new Departamento("GP", "Alemanha")));
		clientes.add(new Clientes(8L, "Vitor", Clientes.Status.ATIVO, new Departamento("GP", "Alemanha")));
		clientes.add(new Clientes(9L, "Brow", Clientes.Status.ATIVO, new Departamento("Design", "EUA")));
		
		for (Clientes clientes2 : clientes) {
			System.out.printf("ID: %d Nome: %s%n",clientes2.getId(), clientes2.getName());
		}
		
		
		System.out.println("--------------------------------");
		clientes.forEach(clientesForEach -> {
			System.out.println(clientesForEach.getName());
		});
		
		
		System.out.println("--------------------------------");
		clientes.forEach(listaClientes ->{
			System.out.printf("ID: %d Nome: %s -- Status: %s%n",listaClientes.getId() ,listaClientes.getName(), listaClientes.getStatus());
		});
		
		System.out.println("--------------------------------");
		
		for (Clientes clientes2 : clientes) {
			if (clientes2.getName().startsWith("C")) {
				clientes2.setStatus(Clientes.Status.CANCELADO);
			}
		}
		clientes.forEach(clientesInfo -> {
			System.out.printf("ID: %d Nome: %s -- Status: %s%n",clientesInfo.getId() ,clientesInfo.getName(), clientesInfo.getStatus());
		});
		
		System.out.println("--------------------------------");
		
		
		Predicate<Clientes> predicate = p -> p.getName().startsWith("R");
		Consumer<Clientes> consumerClientes = p ->{
			p.setStatus(Clientes.Status.CANCELADO);
		};
		
		Stream<Clientes> streamClientes = clientes.stream().filter(predicate);
		streamClientes.forEach(consumerClientes);
		
		clientes.forEach(clientesInfo -> {
			System.out.printf("ID: %d Nome: %s -- Status: %s%n",clientesInfo.getId() ,clientesInfo.getName(), clientesInfo.getStatus());
		});
		
		System.out.println("--------------------------------");
		
		clientes.stream()
		.filter(ps -> ps.getName().startsWith("R"))
		.forEach(Clientes::Inativar);
		
		System.out.println("---------------Mostrar todos Clientes Ativos-----------------");
		
		clientes.stream()
		.filter(pre -> pre.getStatus().equals(pre.getStatus().ATIVO))
		.forEach(ps -> {
			
			System.out.printf("ID: %d Nome: %s -- Status: %s%n",ps.getId() ,ps.getName(), ps.getStatus());
		});
		
		System.out.println("---------------Mostrar todos Clientes Cancelados-----------------");
		
		clientes.stream()
		.filter(pre -> pre.getStatus().equals(pre.getStatus().CANCELADO))
		.forEach(c -> {
			System.out.printf("ID: %d Nome: %s -- Status: %s%n",c.getId() ,c.getName(), c.getStatus());
		});
		
		System.out.println("---------------Retorna uma nova Lista com os clientes filtrados-----------------");
		List<Clientes>listAtivo = clientes.stream()
				.filter(pre -> pre.getStatus().equals(Clientes.Status.ATIVO))//filtrar clientes com o status ativo
				.collect(Collectors.toList());//Add uma nova lista de clientes já filtrado para a listAtivo
		
		
		for(Clientes cli : listAtivo) {
			System.out.printf("ID: %d Nome: %s -- Depar: %s -- Pais: %s -- Status: %s%n",cli.getId() ,cli.getName(), cli.getDepartamento().getNomeDepartamento(), cli.getDepartamento().getLocal(), cli.getStatus());
		}
		
		System.out.println("---------------Retorna uma nova Lista de departamento filtrados via status ativo e usando map-----------------");
		List<Departamento>listaDepartamentosAtivos = clientes.stream()
				.filter(pre -> pre.getStatus().equals((Clientes.Status.ATIVO)))
				.map(ma -> ma.getDepartamento())//ou posso usar o .map(Clientes :: getDepartamento) para filtrar o departamento
				.distinct()
				.collect(Collectors.toList());
		
		for(Departamento dep : listaDepartamentosAtivos) {
			System.out.printf("Departamento: %s -- País: %s%n", dep.getNomeDepartamento(), dep.getLocal());
		}
		
		System.out.println("-----------------Procurar se existe o nome Rosebrigue---------------");
		
		clientes.stream().filter(pre -> pre.getName().equals("Rosebrigue")).forEach(p -> {
			if(p != null) {
				System.out.println("Tem o nome Rosebrigue no ID: "+p.getId());
			}
		});

		System.out.println("-----------------Procurar se existe o nome Henrique---------------");
		
		boolean temHenrique = clientes.stream().anyMatch(pre -> pre.getName().equals("Henriques"));
		System.out.println(temHenrique);
		
		}}
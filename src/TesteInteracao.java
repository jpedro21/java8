import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TesteInteracao {
	
	public static void main(String[] args) {
		
		List<String> palavras = new ArrayList<>();
		palavras.add("Sorvete");
		palavras.add("Pizza");
		palavras.add("Churrasco");
		
		//Exemplo com classe normal
		Comparator<String> ordenador = new OrdenadorDeString();
		palavras.sort(ordenador);
		
		//Exemplo com classe anônima
		palavras.sort(new Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2) {
		        if(s1.length() < s2.length()) 
		            return -1;
		        if(s1.length() > s2.length()) 
		            return 1;
		        return 0;
		    }
		});
		
		//Exemplo lambda com comandos de mais de uma linha
		palavras.sort((s1, s2) -> {
			 if(s1.length() < s2.length()) 
		            return -1;
		        if(s1.length() > s2.length()) 
		            return 1;
		        return 0;
		});
		
		//Exemplo lambda com apenas uma linha
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		
		//Podemos fugir da chamada do método compare, apenas subtraindo o tanho do s1 e s2
		palavras.sort((s1, s2) -> s1.length() - s2.length());
		
		/*
		 * Ordenando string utilizando função estática da INTERFACE Comparator e utilizando lambda
		 * Apenas um método default em uma interface não é static pois se eu quiser invoca-la
		 * preciso criar uma classe anônima
		 */
		palavras.sort(Comparator.comparing(s -> s.length()));
		
		/*
		 * Ordenando utilizando Comparator mas agora com reference
		 */
		palavras.sort(Comparator.comparing(String::length));
		
		palavras.sort(String.CASE_INSENSITIVE_ORDER);
		
		//Exemplo utiizado classe 
		Consumer<String> consumer = new ImprimirListStrings();
		palavras.forEach(consumer);
		
		//Exemplo utilizando classe anônima
		palavras.forEach(new Consumer<String>(){
		    public void accept(String palavra) {
		        System.out.println(palavra);
		    }
		});
		
		//Exemplo utilizando lambda
		palavras.forEach((String s) -> System.out.println(s));
		
		/*
		 * Exemplo utilizando lambda. Para utilizar lambda a interface de funcional
		 * ou seja, obrigatóriamente ela deve ter apenas um método abstrato e opcionalmente 
		 * métodos default
		 */
		palavras.forEach(s -> System.out.println(s));
		
		//Utilizando Reference
		palavras.forEach(System.out::println);
		
		/*O comando abaixo não funciona pois o target que no caso é o "Object o" deveria 
		 * ser uma interface funcional como por exemplo um Consumer
		 */
		//Object o = s -> System.out.println(s);
		
		//Exemplo criação Runnable com classe anônima
		new Thread(new Runnable() {

		    @Override
		    public void run() {
		        System.out.println("Executando um Runnable");
		    }

		}).start();
		
		//Exemplo com lambda
		new Thread(() -> System.out.println("Executando um Runnable")).start();
	}
	
}

class OrdenadorDeString implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		
		if(s1.length() > s2.length())
			return 1;
		if(s1.length() < s2.length())
			return -1;
		return 0;
	}
	
}

class ImprimirListStrings implements Consumer<String> {
	
	@Override
	public void accept(String s) {
		System.out.println(s);		
	}
	
}

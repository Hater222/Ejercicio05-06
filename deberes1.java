

	package web;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;

	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;

	public class deberes1 {
	    public static void main(String[] args) {
	        // Juego específico a buscar en Amazon
	        String juego = "The Legend of Zelda: Breath of the Wild";

	        try {
	            // URL de búsqueda en Amazon para el juego específico
	            String url = "https://www.amazon.com/s?k=" + juego.replace(" ", "+");

	            // Realizar la solicitud HTTP y obtener el documento HTML
	            Document document = Jsoup.connect(url).get();

	            // Seleccionar todos los elementos que contienen los productos
	            Elements productElements = document.select("div[data-component-type='s-search-result']");

	            // Crear un archivo CSV para escribir los resultados
	            BufferedWriter writer = new BufferedWriter(new FileWriter("productos_amazon.csv"));

	            // Escribir los encabezados en el archivo CSV
	            writer.write("Título, Precio (EUR)\n");

	            // Iterar sobre los elementos de los productos y extraer los datos
	            for (Element productElement : productElements) {
	                // Extraer el título del producto
	                String title = productElement.select("span[class='a-size-medium a-color-base a-text-normal']")
	                        .text()
	                        .replace(",", ""); // Eliminar comas para evitar conflictos en el CSV

	                // Extraer el precio del producto
	                String price = productElement.select("span[class='a-offscreen']")
	                        .text()
	                        .replace("€", "") // Eliminar el símbolo del euro
	                        .replace(",", ".");

	                // Escribir los datos en una línea del archivo CSV
	                writer.write(title + ", " + price + "\n");
	            }

	            // Cerrar el escritor y finalizar
	            writer.close();
	            System.out.println("El archivo CSV se ha creado exitosamente.");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


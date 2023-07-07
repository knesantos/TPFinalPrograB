package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.Entities.Pais;

public class PaisRepository {
    private List<Pais> paises;

    public PaisRepository() {
        this.paises = new ArrayList<>();
    }

    public void loadPaisesFromXML() {
    	 String paisesXMLPath = "C:\\Users\\santi\\OneDrive\\Documentos\\Santi\\Progra B\\TrabajoPrograB\\TPFinalPrograB\\src\\Resources\\XML\\paises.xml.txt";
         
        try {
            File file = new File(paisesXMLPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pais");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element element = (Element) nodeList.item(temp);

                String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                String nombreAbreviado = element.getElementsByTagName("nombreAbreviado").item(0).getTextContent();

                Pais pais = new Pais(nombre, nombreAbreviado);
                paises.add(pais);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public List<Pais> getPaises() {
        return paises;
    }

    public Pais findByNombre(String nombre) {
        for (Pais pais : paises) {
            if (pais.getnombre().equalsIgnoreCase(nombre)) {
                return pais;
            }
        }
        return null; // Si no se encuentra el país con el nombre dado
    }
}
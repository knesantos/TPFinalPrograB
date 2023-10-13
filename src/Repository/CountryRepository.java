package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.Entities.Country;

public class CountryRepository {
    private List<Country> Contries;

    public CountryRepository() {
        this.Contries = new ArrayList<>();
    }

    public void loadContriesFromXML() {
    	 String ContriesXMLPath = "src\\Resources\\XML\\paises.xml";
        try {
            File file = new File(ContriesXMLPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pais");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element element = (Element) nodeList.item(temp);

                String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                String nombreAbreviado = element.getElementsByTagName("nombreAbreviado").item(0).getTextContent();

                Country Country = new Country(nombre, nombreAbreviado);
                Contries.add(Country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public List<Country> getContries() {
        return Contries;
    }

    public Country findByNombre(String nombre) {
        for (Country Country : Contries) {
            if (Country.getName().equalsIgnoreCase(nombre)) {
                return Country;
            }
        }
        return null; // Si no se encuentra el país con el nombre dado
    }
}
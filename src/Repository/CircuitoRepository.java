package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.Entities.Circuito;

public class CircuitoRepository {
    private List<Circuito> circuitos;

    public CircuitoRepository() {
        this.circuitos = new ArrayList<>();
    }

    public void loadCircuitosFromXML() {
    	String circuitosXMLPath = "C:\\Users\\santi\\OneDrive\\Documentos\\Santi\\Progra B\\TrabajoPrograB\\TPFinalPrograB\\src\\Resources\\XML\\circuitos.xml";
        
        try {
            File file = new File(circuitosXMLPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("circuito");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element element = (Element) nodeList.item(temp);

                int cantVueltas = Integer.parseInt(element.getElementsByTagName("cantVueltas").item(0).getTextContent());
                int cantCurvas = Integer.parseInt(element.getElementsByTagName("cantCurvas").item(0).getTextContent());
                int cantZonasSobrepaso = Integer.parseInt(element.getElementsByTagName("cantZonasSobrepaso").item(0).getTextContent());
                long longitud = Long.parseLong(element.getElementsByTagName("longitud").item(0).getTextContent());
                long recordVueltaRapida = Long.parseLong(element.getElementsByTagName("recordVueltaRapida").item(0).getTextContent());
                String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();

                Circuito circuito = new Circuito(cantVueltas, cantCurvas, cantZonasSobrepaso, longitud, recordVueltaRapida, nombre);
                circuitos.add(circuito);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public List<Circuito> getCircuitos() {
        return circuitos;
    }

    public Circuito findByNombre(String nombre) {
        for (Circuito circuito : circuitos) {
            if (circuito.getNombre().equalsIgnoreCase(nombre)) {
                return circuito;
            }
        }
        return null; // Si no se encuentra el circuito con el nombre dado
    }
}
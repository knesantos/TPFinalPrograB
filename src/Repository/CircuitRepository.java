package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.Entities.Circuit;

public class CircuitRepository {
    private List<Circuit> Circuits;

    public CircuitRepository() {
        this.Circuits = new ArrayList<>();
    }

    public void loadCircuitsFromXML() {
    	String CircuitsXMLPath = "src\\Resources\\XML\\circuitos.xml";
        
        try {
            File file = new File(CircuitsXMLPath);
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
                String Name = element.getElementsByTagName("nombre").item(0).getTextContent();

                Circuit Circuit = new Circuit(cantVueltas, cantCurvas, cantZonasSobrepaso, longitud, recordVueltaRapida, Name);
                Circuits.add(Circuit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public List<Circuit> getCircuits() {
        return Circuits;
    }

    public Circuit findByName(String Name) {
        for (Circuit Circuit : Circuits) {
            if (Circuit.getName().equalsIgnoreCase(Name)) {
                return Circuit;
            }
        }
        return null; // Si no se encuentra el Circuit con el Name dado
    }
}
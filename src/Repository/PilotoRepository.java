package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.Entities.Piloto;

public class PilotoRepository {
    private List<Piloto> pilotos;

    public PilotoRepository() {
        this.pilotos = new ArrayList<>();
    }

    public void loadPilotosFromXML() {
    	 String pilotosXMLPath = "src\\Resources\\XML\\pilotos.xml";
        try {
            File file = new File(pilotosXMLPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("piloto");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element element = (Element) nodeList.item(temp);

                long presupuesto = Long.parseLong(element.getElementsByTagName("presupuesto").item(0).getTextContent());
                int defensa = Integer.parseInt(element.getElementsByTagName("defensa").item(0).getTextContent());
                int sobrepaso = Integer.parseInt(element.getElementsByTagName("sobrepaso").item(0).getTextContent());
                int clasificacion = Integer.parseInt(element.getElementsByTagName("clasificacion").item(0).getTextContent());
                int cuidadoNeumaticos = Integer.parseInt(element.getElementsByTagName("cuidadoNeumaticos").item(0).getTextContent());
                int largada = Integer.parseInt(element.getElementsByTagName("largada").item(0).getTextContent());
                String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                String nombreAbrev = element.getElementsByTagName("nombreAbreviado").item(0).getTextContent();
                int cantCarrerasGanadas = Integer.parseInt(element.getElementsByTagName("cantCarrerasGanadas").item(0).getTextContent());
                int cantPolePositions = Integer.parseInt(element.getElementsByTagName("cantPolePositions").item(0).getTextContent());
                int cantCampeonatos = Integer.parseInt(element.getElementsByTagName("cantCampeonatos").item(0).getTextContent());
                int cantParticipaciones = Integer.parseInt(element.getElementsByTagName("cantParticipaciones").item(0).getTextContent());

                Piloto piloto = new Piloto(presupuesto, defensa, sobrepaso, clasificacion, cuidadoNeumaticos, largada,
                        nombre, nombreAbrev, cantCarrerasGanadas, cantPolePositions, cantCampeonatos, cantParticipaciones);
                pilotos.add(piloto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 

    public List<Piloto> getPilotos() {
        return pilotos;
    }

    public Piloto findByNombre(String nombre) {
       
        for (Piloto piloto : pilotos) {
            if (piloto.getnombre().equalsIgnoreCase(nombre)) {
                return piloto;
            }
        }
        return null; // Si no se encuentra el piloto con el nombre dado
    }
}
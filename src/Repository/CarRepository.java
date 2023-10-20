package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.Entities.Car;
import Core.Entities.Medium;
import Core.Entities.Tire;

public class CarRepository {
    private List<Car> Cars;

    public CarRepository() {
        this.Cars = new ArrayList<>();
        loadCarsFromXML();
    }

    public void loadCarsFromXML() {
        String filePath = "src\\Resources\\XML\\autos.xml";

        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("auto");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element element = (Element) nodeList.item(temp);

                int performanceSobrepaso = Integer.parseInt(element.getElementsByTagName("performanceSobrepaso").item(0).getTextContent());
                int performanceCurvas = Integer.parseInt(element.getElementsByTagName("performanceCurvas").item(0).getTextContent());
                double peso = Double.parseDouble(element.getElementsByTagName("peso").item(0).getTextContent());
                int fiabilidad = Integer.parseInt(element.getElementsByTagName("fiabilidad").item(0).getTextContent());
                int velocidadMax = Integer.parseInt(element.getElementsByTagName("velocidadMax").item(0).getTextContent());
                double aceleracion = Double.parseDouble(element.getElementsByTagName("aceleracion").item(0).getTextContent());
                int potencia = Integer.parseInt(element.getElementsByTagName("potencia").item(0).getTextContent());
                int consumo = Integer.parseInt(element.getElementsByTagName("consumo").item(0).getTextContent());
                String marca = element.getElementsByTagName("marca").item(0).getTextContent();
                String modelo = element.getElementsByTagName("modelo").item(0).getTextContent();

                Tire Tire = new Medium(0, 0, 0, 0, 0);

                Car Car = new Car(performanceSobrepaso, performanceCurvas, peso, fiabilidad, velocidadMax, aceleracion, potencia, consumo, Tire, marca, modelo);

                // Establecer la ruta de la imagen del auto
                String relativePath = "src/Resources/Images/" + modelo + ".png";
                File f = new File(relativePath);

                if(f.exists() && !f.isDirectory()) { 
                    System.out.println("Imagen existe: " + relativePath);
                } else {
                    System.out.println("Imagen no existe: " + relativePath);
                }

                Car.setAvatar(relativePath);
                Cars.add(Car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Car> getCars() {
        return Cars;
    }
}
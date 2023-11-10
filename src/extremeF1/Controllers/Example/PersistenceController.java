package extremeF1.Controllers.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Core.Entities.Championship;

public class PersistenceController implements Serializable {

    private static final String FILENAME = "championship.file";

    public static void persistChampionship(Championship championship) {
        File file = new File(FILENAME);
        System.out.println("Attempting to write to file: " + file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(championship);
        } catch (IOException e) {
            System.out.println("IOException during serialization: " + e.getMessage());
        }
    }

    public static Championship championshipInstance() {
        File file = new File(FILENAME);
        Championship championship = null;
        if (file.exists()) {
            System.out.println("File found at: " + file.getAbsolutePath());
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                championship = (Championship) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception during deserialization: " + e);
                System.exit(0);
            }
        } else {
            System.out.println("File does not exist at: " + file.getAbsolutePath());
        }
        return championship;
    }
}
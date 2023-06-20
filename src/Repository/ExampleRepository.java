package Repository;

//Interfaz
import Core.Interfaces.Repository.IExampleRepository;

//Implementacion
public class ExampleRepository implements IExampleRepository {
    @Override
    public void repositoryMethod1() {
        // Implementación del método repositoryMethod1
    }

    @Override
    public int repositoryMethod2(String param) {
        // Implementación del método repositoryMethod2
        return 0;
    }
}
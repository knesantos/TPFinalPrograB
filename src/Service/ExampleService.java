package Service;
//Repositorios que utiliza el service
import Core.Interfaces.Repository.IExampleRepository;
//Interfaz
import Core.Interfaces.Service.IExampleService;

//Implementacion
public class ExampleService implements IExampleService {
    private IExampleRepository exampleRepository;

    public ExampleService(IExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Override
    public void method1() {
        // Lógica del método method1 del servicio
        exampleRepository.repositoryMethod1();
    }

    @Override
    public int method2(String param) {
        // Lógica del método method2 del servicio
        return exampleRepository.repositoryMethod2(param);
    }
}
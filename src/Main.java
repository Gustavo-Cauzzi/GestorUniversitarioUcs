import dtos.UniversidadeDTO;
import repository.AppRepository;
import utils.DataGenerator;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        UniversidadeDTO universidade = DataGenerator.generateUniversidade();
        AppRepository.getInstance().readFromUniversity(universidade);
        MainView.getInstance().invoke();
    }
}
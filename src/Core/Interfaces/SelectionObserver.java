package Core.Interfaces;

import Core.Entities.Auto;
import Core.Entities.Piloto;

public interface SelectionObserver {
    void onSelectionComplete(Piloto selectedPilot, Auto selectedCar);
    void onButtonPress(boolean isPressed);
}
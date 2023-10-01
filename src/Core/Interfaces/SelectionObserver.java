package Core.Interfaces;

import Core.Entities.Car;
import Core.Entities.Driver;

public interface SelectionObserver {
    void onSelectionComplete(Driver selectedPilot, Car selectedCar);
    void onButtonPress(boolean isPressed);
}
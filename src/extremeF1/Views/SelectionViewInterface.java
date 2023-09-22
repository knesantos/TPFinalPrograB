package extremeF1.Views;

import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Events.AceptSelectionListener;

public interface SelectionViewInterface {

 public void setAceptPilotListener(AceptPilotListener listener);
	
 public void setAceptCarListener(AceptCarListener listener);
 
void setButtonAcceptSelectionListener(AceptSelectionListener listener);



	
}

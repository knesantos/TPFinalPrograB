package extremeF1.Views;

import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Events.CloseGameListener;
import Core.Entities.Events.LoadGameListener;
import Core.Entities.Events.StartGameListener;

public interface StartViewInterface {

	public void setCloseGameListener(CloseGameListener listener);
	public void setStartGameListener(StartGameListener listener);
	public void setLoadListener(LoadGameListener listener);
}

package extremeF1.Views;

import Core.Entities.Events.ChangeTireListener;
import Core.Entities.Events.LoadFuelListener;

public interface PitsViewInterface {
	
	public void setLoadFuelListener(LoadFuelListener listener);
	
	public void setChangeTireListener(ChangeTireListener listener);
	

}

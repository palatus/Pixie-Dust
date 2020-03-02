package towerz;

import engine.Backpack;
import engine.Item;
import engine.Window;
import engine.WorldObject;

public class SortStart {

	public static Backpack worldSpace = new Backpack(2048);
	public static Window window = new Window("Tower Defense 2");
	
	public static void main(String[] args) {
		
		loadReservedIDList();
		generateItems(52);
		loadSceneItems();
		window.grabFocus();
		window.revalidate();
		
		//Light l = new Light();
		
		//window.getLightmap().pushSunlight();
		
	}
	
	public static void loadSceneItems() {
		
		for(Item i: worldSpace.getContents()) {
			
			window.getScene().getSceneObjects().add(i);
			
		}
		
	}
	
	public static void loadReservedIDList() {
		
		// TODO read from file stuff
		
		Item i = new Item("Reserved ID");
		i.setOwnerGroup(worldSpace);
		i.setId(23);
		WorldObject.ReservedID.add(i);
		
	}
	
	public static void generateItems(int n) { // populate sample backpack with n items

		for (int j = 0; j < n; j++) {
				
			Item i = new Item("gen");
			boolean unique = false;

			do {
				
				unique = i.setId((int) (Math.random() * (worldSpace.getMaxSlots()*10+1)));
				if(!unique) {
					//System.err.println("Used ID - Generating another");
				}
				
				i.setX((int) (Math.random() * 600));
				i.setY((int) (Math.random() * 600));
				i.setHeight(32);
				i.setWidth(32);
				
			} while (!unique);

			if(worldSpace.full())
				break;
			
			worldSpace.addItem(i);
		}

		WorldObject.ListedID.clean();
		WorldObject.ListedID.sort();
		
	}

}

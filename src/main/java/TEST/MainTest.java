package TEST;

import java.lang.reflect.Field;

import com.laptrinhjavaweb.entity.BuildingEntity;

public class MainTest {

	public static void main(String[] args) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName("abcd");
		buildingEntity.setStructure("haha");
		
		try {
			Field[] fields = buildingEntity.getClass().getDeclaredFields();
			Object[] parameters = new Object[fields.length];

			for(int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Object value = fields[i].get(buildingEntity);
				if(value!=null) {
					parameters[i] = value;
				}
			}
			System.out.println();
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}

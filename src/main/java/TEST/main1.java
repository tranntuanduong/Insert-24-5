package TEST;

import java.sql.Timestamp;
import java.util.Date;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repositoty.impl.BuildingRepository;
import com.laptrinhjavaweb.repositoty.impl.UserRepository;

public class main1 {
	public static void main(String[] args) {
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setCreateddate(ts);
		buildingEntity.setCreatedby("kakaka");
		buildingEntity.setName("duong dep trai");
		buildingEntity.setStructure("ahihihi");
		buildingEntity.setNumberOfBasement(1950);
		buildingEntity.setBuildingArea(22);
		
		BuildingRepository buildingRepository = new BuildingRepository();
		buildingRepository.insert(buildingEntity);
		System.out.println();
//		UserEntity userEntity = new UserEntity();
//		UserRepository userRepository = new UserRepository();
//		userEntity.setUsername("kakakakak");
//		userEntity.setPassword("ahihihihi");
//		userRepository.insert(userEntity);
	}
}

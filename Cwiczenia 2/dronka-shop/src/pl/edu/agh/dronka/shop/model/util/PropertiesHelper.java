package pl.edu.agh.dronka.shop.model.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.*;

public class PropertiesHelper {
	public static Map<String, Object> getPropertiesMap(Item item) {
		Map<String, Object> propertiesMap = new LinkedHashMap<>();
		
		propertiesMap.put("Nazwa", item.getName());
		propertiesMap.put("Cena", item.getPrice());
		propertiesMap.put("Kategoria", item.getCategory().getDisplayName()); 
		propertiesMap.put("Ilość", Integer.toString(item.getQuantity()));
		propertiesMap.put("Tanie bo polskie", item.isPolish());
		propertiesMap.put("Używany", item.isSecondhand());
		Map<String, Object> specialPropertiesMap = item.getSpecialPropertiesMap();
		if (specialPropertiesMap != null) {
			propertiesMap.putAll(specialPropertiesMap);
		}

		return propertiesMap;
	}

	public static List<String> getBooleanPropertiesList(Category category) {
		ArrayList<String> booleanPropertiesList = new ArrayList<>();
		booleanPropertiesList.add("Tanie bo polskie");
		booleanPropertiesList.add("Używany");

		switch (category) {
			case BOOKS -> {
				booleanPropertiesList.add("Twarda oprawa");
			}
			case ELECTRONICS -> {
				booleanPropertiesList.add("Mobilny");
				booleanPropertiesList.add("Gwarancja");
			}
			case FOOD -> {
			}
			case MUSIC -> {
				booleanPropertiesList.add("Posiada teledysk");
			}
			case SPORT -> {
			}
		}
		return booleanPropertiesList;
	}
}

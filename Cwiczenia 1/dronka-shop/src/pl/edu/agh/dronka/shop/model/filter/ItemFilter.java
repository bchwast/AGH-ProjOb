package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.Item;
import pl.edu.agh.dronka.shop.model.util.PropertiesHelper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemFilter {
	private Category category;
	private Set<String> filters;

	public void setCategory(Category category) {
		this.category = category;
		this.filters = new HashSet<>();
	}

	public void setFilter(String propertyName, boolean value) {
		if (value) {
			filters.add(propertyName);
		}
		else {
			filters.remove(propertyName);
		}
	}

	public boolean appliesTo(Item item) {

		if (item.getCategory() != category) {
			return false;
		}

		Map<String, Object> propertiesMap = PropertiesHelper.getPropertiesMap(item);

		for (String propertyName : filters) {
			if (!(boolean) propertiesMap.get(propertyName)) {
				return false;
			}
		}

		return true;
	}
}
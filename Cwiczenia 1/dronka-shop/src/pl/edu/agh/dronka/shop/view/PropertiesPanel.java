package pl.edu.agh.dronka.shop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;
import pl.edu.agh.dronka.shop.model.util.PropertiesHelper;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -2804446079853846996L;
	private ShopController shopController;
	private final ItemFilter filter = new ItemFilter();

	public PropertiesPanel(ShopController shopController) {
		this.shopController = shopController;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void fillProperties() {
		removeAll();
		Category category = shopController.getCurrentCategory();
		filter.setCategory(category);

		for (String propertyCategory : PropertiesHelper.getBooleanPropertiesList(category)) {
			add(createPropertyCheckbox(propertyCategory, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					filter.setFilter(propertyCategory,
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}
	}

	private JCheckBox createPropertyCheckbox(String propertyName,
			ActionListener actionListener) {

		JCheckBox checkBox = new JCheckBox(propertyName);
		checkBox.setSelected(false);
		checkBox.addActionListener(actionListener);

		return checkBox;
	}

}

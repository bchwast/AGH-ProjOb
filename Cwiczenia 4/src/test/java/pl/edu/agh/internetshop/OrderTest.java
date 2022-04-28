package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {

	private Order getOrderWithOneMockedProduct() {
		Product product = mock(Product.class);
		return new Order(Collections.singletonList(product));
	}

	private Order getOrderWithMultipleMockedProducts() {
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);
		Product product3 = mock(Product.class);
		return new Order(Arrays.asList(product1, product2, product3));
	}

	@Test
	public void testGetOneProductThroughOrder() {
		// given
		Product expectedProduct = mock(Product.class);
		Order order = new Order(Collections.singletonList(expectedProduct));

		// when
		List<Product> actualProducts = order.getProducts();

		// then
		assertSame(expectedProduct, actualProducts.get(0));
	}

	@Test
	public void testGetMultipleProductsThroughOrder() {
		// given
		Product expectedProduct1 = mock(Product.class);
		Product expectedProduct2 = mock(Product.class);
		Product expectedProduct3 = mock(Product.class);
		Order order = new Order(Arrays.asList(expectedProduct1, expectedProduct2, expectedProduct3));

		// when
		List<Product> actualProducts = order.getProducts();

		// then
		assertEquals(3, actualProducts.size());
		assertSame(expectedProduct1, actualProducts.get(0));
		assertSame(expectedProduct2, actualProducts.get(1));
		assertSame(expectedProduct3, actualProducts.get(2));
	}

	@Test
	public void testSetProductsAsNullInOrder() {
		// given, when, then
		assertThrows(NullPointerException.class, () -> new Order(null));
	}

	@Test
	public void testSetShipment() throws Exception {
		// given
		Order order = getOrderWithMultipleMockedProducts();
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void testShipmentWithoutSetting() throws Exception {
		// given
		Order order = getOrderWithMultipleMockedProducts();

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void testGetPrice() throws Exception {
		// given
		BigDecimal expectedProductPrice1 = BigDecimal.valueOf(300);
		BigDecimal expectedProductPrice2 = BigDecimal.valueOf(700);
		BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);
		given(product1.getPrice()).willReturn(expectedProductPrice1);
		given(product2.getPrice()).willReturn(expectedProductPrice2);
		Order order = new Order(Arrays.asList(product1, product2));

		// when
		BigDecimal actualProductPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(expectedProductPrice, actualProductPrice);
	}

	private Order getOrderWithCertainNumberOfProductsAndPrice(int numberOfProducts, double productPriceValue) {
		BigDecimal productPrice = BigDecimal.valueOf(productPriceValue);
		List<Product> products = new ArrayList<>();
		IntStream.range(0, numberOfProducts).mapToObj(i -> mock(Product.class)).forEach(product -> {
			given(product.getPrice()).willReturn(productPrice);
			products.add(product);
		});
		return new Order(products);
	}

	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainNumberOfProductsAndPrice(1, 2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.46 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundDown() {
		// given

		// when
		Order order = getOrderWithCertainNumberOfProductsAndPrice(2, 0.01); // 0.02 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.02)); // 0.02 PLN
																							
	}

	@Test
	public void testPriceWithTaxesWithRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainNumberOfProductsAndPrice(1, 0.03); // 0.03 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
																							
	}

	@Test
	public void testPriceWithNoOrders() {
		// given

		// when
		Order order = new Order(Collections.emptyList());

		// then
		assertBigDecimalCompareValue(order.getPrice(), BigDecimal.valueOf(0));
	}

	@Test
	public void testSetShipmentMethod() {
		// given
		Order order = getOrderWithMultipleMockedProducts();
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void testSending() {
		// given
		Order order = getOrderWithOneMockedProduct();
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void testIsSentWithoutSending() {
		// given
		Order order = getOrderWithMultipleMockedProducts();
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void testWhetherIdExists() throws Exception {
		// given
		Order order = getOrderWithOneMockedProduct();

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void testSetPaymentMethod() throws Exception {
		// given
		Order order = getOrderWithMultipleMockedProducts();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void testPaying() throws Exception {
		// given
		Order order = getOrderWithOneMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void testIsPaidWithoutPaying() throws Exception {
		// given
		Order order = getOrderWithMultipleMockedProducts();

		// when

		// then
		assertFalse(order.isPaid());
	}
}

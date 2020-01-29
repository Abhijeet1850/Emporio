package com.test.emporio.features_ui;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import com.test.emporio.PageActions.CartPage;
import com.test.emporio.PageActions.CheckOutPage;
import com.test.emporio.PageActions.Homepage;
import com.test.emporio.PageActions.ProductDescriptionPage;
import com.test.emporio.PageActions.ProductListPage;
import com.test.emporio.TestBase.TestBase;
import com.test.emporio.helper.configReader.PropertyReader;
import com.test.emporio.helper.logger.LoggerHelper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

public class ManageOrderCreateFlow extends TestBase {

	private Logger log = LoggerHelper.getLogger(ManageOrderCreateFlow.class);

	Homepage homepage = new Homepage(driver);
	ProductDescriptionPage prodDesc = new ProductDescriptionPage(driver);
	ProductListPage prodList = new ProductListPage(driver);
	CartPage cart = new CartPage(driver);
	CheckOutPage checkout = new CheckOutPage(driver);

	PropertyReader reader = new PropertyReader();

	@Given("I visit the bestbuy url {string}")
	public void i_visit_the_bestbuy_url(String url) {
		homepage.init();

	}

	@Given("I Search for the Product {string} on HomePage")
	public void i_Search_for_the_Product_on_HomePage(String searchText) {

		if (homepage.isChooseCountryPopupPresent()) {
			homepage.chooseCountry();
			if (homepage.isSignUpPopVisible())
				homepage.closeSignUpPopup();
		}
		homepage.enterProductSearch(searchText);
	}

	@Given("I filter out the Products from ProductList page using Price Filter")
	public void i_filter_out_the_Products_from_ProductList_page_using_Price_Filter() {
		prodList.selectPriceFilter();
	}

	@Given("I select the first available product from List")
	public void i_select_the_first_available_product_from_List() {
		prodList.selectFirstAvailableProduct();
	}

	@Given("I select AddToCart option for the product on Product Description Page")
	public void i_select_AddToCart_option_for_the_product_on_Product_Description_Page() {
		prodDesc.addItemToCart();
		Assertions.assertThat(prodDesc.verifyIfItemAddedToCart()).isTrue();

	}

	@Given("I move to View Cart Page")
	public void i_move_to_View_Cart_Page() {
		prodDesc.goToCart();
	}

	@Then("I update Shipping Location to {string}")
	public void i_update_Shipping_Location_to(String zip) {

		cart.selectShipToAddress(zip);

	}

	@Then("I click on checkout Option")
	public void i_click_on_checkout_Option() {

		cart.clickCheckout();
	}

	@Then("I select {string} option")
	public void i_select_option(String string) {
		checkout.selectContinueAsGuest();
	}

	@Then("I Enter Following Details")
	public void i_Enter_Following_Details(DataTable data) {
		List<Map<String, String>> shippingData = data.asMaps();
		checkout.fillShippingInfo(shippingData.get(0));

	}

	@Then("I Continue to payment Information")
	public void i_Continue_to_payment_Information() {

	}

	@Then("I Enter basic Payment Details")
	public void i_Enter_basic_Payment_Details(DataTable data) {
		List<Map<String, String>> paymentData = data.asMaps();
		checkout.enterPaymentDetails(paymentData.get(0));
		checkout.placeOrder();
	}

}

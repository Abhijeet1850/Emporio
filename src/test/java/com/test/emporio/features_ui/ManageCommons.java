package com.test.emporio.features_ui;

import com.test.emporio.TestBase.TestBase;
import com.test.emporio.helper.configReader.PropertyReader;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ManageCommons {

	TestBase testbase;
	PropertyReader reader = new PropertyReader();
	public final String driverPath = "\\src\\main\\java\\com\\test\\emporio\\Drivers";

	@Before
	public void setUp() {
		testbase = new TestBase();
		testbase.setupDriver(reader.getBroswerType(), driverPath);
	}

	@After
	public void tearDown() {
		testbase.shutDownDriver();
	}

}

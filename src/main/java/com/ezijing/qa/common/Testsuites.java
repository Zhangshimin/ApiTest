package com.ezijing.qa.common;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;
import junit.framework.Test;

public class Testsuites {
	public static Test suite() {
		   TestSuite suite = new TestSuite(Testsuites.class.getName());


		   suite.addTest(new JUnit4TestAdapter(TestLogin.class));

		   return suite;
		 }

}

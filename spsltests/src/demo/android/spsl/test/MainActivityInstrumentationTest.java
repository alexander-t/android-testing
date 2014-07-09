package demo.android.spsl.test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import demo.android.spsl.MainActivity;
import demo.android.spsl.R;

/**
 * Mostly tests that shared preferences are synchronised with the item list 
 */
public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Activity testedActivity;
	private EditText textNewItem;
	private ImageButton buttonAdd;
	private ListView listViewItems;

	public MainActivityInstrumentationTest() {
		super(MainActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		testedActivity = getActivity();
		testedActivity.runOnUiThread(new Runnable() {
			public void run() {
				clearSharedPreferences();
			}
		});
		textNewItem = (EditText) testedActivity.findViewById(R.id.textNewItem);
		buttonAdd = (ImageButton) testedActivity
				.findViewById(R.id.buttonAddItem);
		listViewItems = (ListView) testedActivity
				.findViewById(R.id.listViewItems);

		getInstrumentation().waitForIdleSync();
	}

	@SmallTest
	public void testPreconditions() {
		assertNotNull(testedActivity);
	}

	@MediumTest
	public void testItemListIsEmptyOnStartup() {
		assertNotNull(listViewItems);
		assertEquals(0, listViewItems.getCount());
	}

	@MediumTest
	@UiThreadTest
	public void testThreeUnsortedItemsAdded_areSortedAlphabetically() {
		addItemToList("Cecil");
		addItemToList("Abel");
		addItemToList("Babel");

		assertEquals(3, listViewItems.getCount());
		assertEquals("Abel", listViewItems.getItemAtPosition(0));
		assertEquals("Babel", listViewItems.getItemAtPosition(1));
		assertEquals("Cecil", listViewItems.getItemAtPosition(2));
	}

	@MediumTest
	@UiThreadTest
	public void testAddingTheSameElementSeveralTimes_onlyOneElementIsPresentInTheList() {
		addItemToList("Abel");
		addItemToList("Abel");
		addItemToList("Abel");

		assertEquals(1, listViewItems.getCount());
		assertEquals("Abel", listViewItems.getItemAtPosition(0));
	}

	private void addItemToList(String item) {
		textNewItem.setText(item);
		buttonAdd.performClick();
	}

	// This cumbersome way of clearing shared preferences ensures that the
	// listener is triggered, as opposed to when using clear().
	private void clearSharedPreferences() {
		SharedPreferences sp = testedActivity.getSharedPreferences(
				"android.demo.sharedpreferences", Context.MODE_PRIVATE);
		for (String key : sp.getAll().keySet()) {
			sp.edit().remove(key).commit();
		}
	}
}

package demo.android.spsl.test;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;
import android.widget.ImageButton;
import demo.android.spsl.MainActivity;
import demo.android.spsl.R;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	private Activity testedActivity;
	private EditText textNewItem;
	private ImageButton buttonAdd;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		// Note that we start the activity "manually" here
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(intent, null, null);
		testedActivity = getActivity();

		textNewItem = (EditText) testedActivity.findViewById(R.id.textNewItem);
		buttonAdd = (ImageButton) testedActivity
				.findViewById(R.id.buttonAddItem);
	}

	@SmallTest
	public void testPreconditions() {
		assertNotNull(testedActivity);
	}

	@SmallTest
	public void testNewItemTextInputIsVisible() {
		assertNotNull(textNewItem);
		ViewAsserts.assertOnScreen(testedActivity.getWindow().getDecorView(),
				textNewItem);
	}

	@SmallTest
	public void testNewItemTextInputIsBlank() {
		assertEquals(0, textNewItem.getText().length());
	}

	@SmallTest
	public void testButtonAddIsVisible() {
		assertNotNull(buttonAdd);
		ViewAsserts.assertOnScreen(testedActivity.getWindow().getDecorView(),
				buttonAdd);
	}

	@SmallTest
	public void testNewItemTextAndAddButtomAreTopAligned() {
		ViewAsserts.assertTopAligned(textNewItem, buttonAdd);
	}
}

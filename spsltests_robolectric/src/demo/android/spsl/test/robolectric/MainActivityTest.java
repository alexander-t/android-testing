package demo.android.spsl.test.robolectric;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.widget.EditText;
import demo.android.spsl.MainActivity;
import demo.android.spsl.R;

@Config(emulateSdk=18)
@RunWith(RobolectricTestRunner.class)  
public class MainActivityTest {

	private MainActivity testedActivity;
	private EditText textNewItem;
	
	@Before
	public void setUp() {
		testedActivity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
		textNewItem = (EditText) testedActivity.findViewById(R.id.textNewItem);
	}
	
	@Test
	public void correctApplicationName() {
		
		// getResources() doesn't require visibility
        String appName = new MainActivity().getResources().getString(R.string.app_name);
        assertEquals("Shared Preferences Shopping List", appName);
    }
	
	@Test
	public void newItemTextIsBlankUponStartup() {
		assertEquals("", textNewItem.getText().toString());
	}
	
}

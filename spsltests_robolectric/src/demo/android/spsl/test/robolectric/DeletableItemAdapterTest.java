package demo.android.spsl.test.robolectric;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import demo.android.spsl.DeletableItemAdapter;
import demo.android.spsl.R;

@Config(emulateSdk=18)
@RunWith(RobolectricTestRunner.class)
public class DeletableItemAdapterTest {
	
	private DeletableItemAdapter testedAdapter;
	
	@Before
	public void setUp() {
		testedAdapter = new DeletableItemAdapter(Robolectric.application,  
				R.layout.listlayout, Arrays.asList("One", "Two", "Three"));
	}
		
	@Test
	public void deleteButtonsTagsCorrespondToTheModelValues() {
		assertEquals("One", ((ImageButton) getRow(0).findViewById(R.id.buttonDelete)).getTag());
		assertEquals("Two", ((ImageButton) getRow(1).findViewById(R.id.buttonDelete)).getTag());
		assertEquals("Three", ((ImageButton) getRow(2).findViewById(R.id.buttonDelete)).getTag());
	}
	
	private View getRow(int row) {
		return testedAdapter.getView(row, null, null);
	}
	
	@Test
	public void itemLabelValuesCorrespondToTheModelValues() {
		assertEquals("One", ((TextView) getRow(0).findViewById(R.id.labelItemName)).getText());
		assertEquals("Two", ((TextView) getRow(1).findViewById(R.id.labelItemName)).getText());
		assertEquals("Three", ((TextView) getRow(2).findViewById(R.id.labelItemName)).getText());
	}
}

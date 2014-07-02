package demo.android.spsl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {

	private EditText textNewItem;
	private ListView listViewItems;
	private SharedPreferences preferences;
	private ArrayAdapter<String> adapter; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textNewItem = (EditText) findViewById(R.id.textNewItem);
        listViewItems = (ListView) findViewById(R.id.listViewItems);
        
        preferences = getSharedPreferences("android.demo.sharedpreferences", Context.MODE_PRIVATE);
        preferences.registerOnSharedPreferenceChangeListener(this);
                
        final List<String> listModel = new ArrayList(preferences.getAll().values());
		adapter = new DeletableItemAdapter(this, R.layout.listlayout, listModel);
        adapter.sort(new NormalStringComparator());
        listViewItems.setAdapter(adapter);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		// Remember that key = value in this implementation
		if (sharedPreferences.contains(key)) {
			adapter.add(key);
			adapter.sort(new NormalStringComparator());
		} else {
			adapter.remove(key);
		}
		adapter.notifyDataSetChanged();
	}

    public void buttonAddClicked(View view) {
    	String newItem = textNewItem.getText().toString(); 
    	if (!newItem.isEmpty()) {
    		preferences.edit().putString(newItem, newItem).commit();
    		textNewItem.setText("");
    	}
    }
    
    public void buttonDeleteClicked(View view) {
    	preferences.edit().remove((String) view.getTag()).commit();
    	adapter.notifyDataSetChanged();
    }

	private static class NormalStringComparator implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}
} 
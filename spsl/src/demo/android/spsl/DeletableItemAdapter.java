package demo.android.spsl;

import java.util.List;

import demo.android.spsl.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class DeletableItemAdapter extends ArrayAdapter<String> {

	private int layoutResourceId;
	
	private List<String> items;
	
	public DeletableItemAdapter(Context context, int layoutResourceId, List<String> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
		View row = inflater.inflate(layoutResourceId, parent, false);
		TextView label = (TextView) row.findViewById(R.id.labelItemName);
		ImageButton button = (ImageButton) row.findViewById(R.id.buttonDelete);
		button.setTag(items.get(position));
		label.setText(items.get(position));
		return row;
	}
}

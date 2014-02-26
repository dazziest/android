package com.nu.njajalmedia.views.adapters;

import java.util.List;

import com.nu.njajalmedia.R;
import com.nu.njajalmedia.models.Article;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Article> {

	private List<Article> listMenuItems;
	private Context context;
	private int rowResourceId;
//	private ImageLoader imageLoader;

	public CustomArrayAdapter(Context context, int textViewResourceId, List<Article> menuItemList) {
		super(context, textViewResourceId, menuItemList);
		this.context = context;
        this.listMenuItems = menuItemList;
        this.rowResourceId = textViewResourceId;
//        imageLoader=new ImageLoader(context.getApplicationContext());
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(rowResourceId, parent, false);
		Article article = listMenuItems.get(position);
		TextView text=(TextView)convertView.findViewById(R.id.textTitle);
	    TextView textDesc=(TextView)convertView.findViewById(R.id.textDesc);
	    ImageView image=(ImageView)convertView.findViewById(R.id.image);
	    text.setText(article.getTitle());
	    textDesc.setText(Html.fromHtml(article.getDescription()).toString());
//	    imageLoader.DisplayImage(article.getImgUrl(), image);
		return convertView;
	}
}

package ale.movcalendar.Class;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import ale.movcalendar.R;

public class RecommendedAdapter extends ArrayAdapter<RecommendedItem>{

    Context context;

    public RecommendedAdapter(Context context, int id, List<RecommendedItem> items) {
        super(context, id, items);
        this.context = context;
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView genero;
        TextView año;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RecommendedItem recommendedItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_recommended_item, null);
            holder = new ViewHolder();

            holder.nombre = (TextView) convertView.findViewById(R.id.textview_nombre);
            holder.genero = (TextView) convertView.findViewById(R.id.textview_genero);
            holder.año = (TextView) convertView.findViewById(R.id.textview_año);
            holder.imagen = (ImageView) convertView.findViewById(R.id.imageview_imgagen);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.nombre.setText(recommendedItem.getNombre());
        holder.genero.setText(recommendedItem.getGenero());
        holder.año.setText(recommendedItem.getAño());
        holder.imagen.setImageResource(recommendedItem.getImagen());

        return convertView;
    }

}


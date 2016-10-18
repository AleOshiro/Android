package ale.movcalendar.Class;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ale.movcalendar.R;

public class ListViewAdapter extends ArrayAdapter<SeriesClass>{

    private SeriesClass[] lista;

    public ListViewAdapter(Context context, SeriesClass[] datos) {
        super(context, R.layout.activity_item, datos);
        this.lista = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.activity_item, null);

        TextView nombre = (TextView)item.findViewById(R.id.item);
        TextView temporada = (TextView)item.findViewById(R.id.temp);

        nombre.setText(lista[position].getNombre());
        temporada.setText(lista[position].getTemporada());

        return(item);
    }

}

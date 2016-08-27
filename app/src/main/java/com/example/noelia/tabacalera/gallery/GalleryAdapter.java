package com.example.noelia.tabacalera.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.noelia.tabacalera.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Noelia on 13/08/2016.
 */

public class GalleryAdapter extends BaseAdapter {
/*    private Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return GalleryDataProvider.ITEMS.length;
    }

    @Override
    public GalleryDataProvider getItem(int position) {
        return GalleryDataProvider.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getIdDrawable();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_gallery, viewGroup, false);
        }
       ImageView imagenGaleria = (ImageView) view.findViewById(R.id.imageGallery);


        final GalleryDataProvider item = getItem(position);
        imagenGaleria.setImageResource(item.getIdDrawable());

        return view;
    }*/

/*    private static final String url = "http://tabacalera.net23.net/gallery/";*/
    private Context context;
    private String[] items;

    public GalleryAdapter(Context context, String[] items){
        super();
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() { return items.length; }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Declaramos el ImageView
        ImageView img = null;
        if (convertView == null) {
            //Referenciamos el ImageView
            img = new ImageView(context);
            //Referenciamos el ImageView al convertView
            convertView = img;
            img.setPadding(5, 5, 5, 5);
        } else {
            img = (ImageView) convertView;
        }
        //Uso de la librería Picasso
        Picasso.with(context)
                //Cargamos la imagen sobre la que se esté iterando
                .load(items[position])
                //Imagen por defecto usada mientras se cargan las imágenes
                .placeholder(R.drawable.rounded_edit_text)
                .resize(200, 300)
                .centerCrop()
                //Se aplica sobre la imagen (ImageView - se hizo referencia a "convertView")
                .into(img);

        return convertView;

/*        ImageView img = null;
        String item = new GalleryDataProvider(items[position]).getImage();
        Picasso.with(context).load(url+item).resize(100,100).into(img);
        return img;*/
    }

    static class ViewHolder {
        ImageView imageView;
    }

}

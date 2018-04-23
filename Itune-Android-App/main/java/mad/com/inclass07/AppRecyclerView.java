package mad.com.inclass07;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AppRecyclerView extends RecyclerView.Adapter<AppRecyclerView.ViewHolder> {
    ArrayList<App> mData;
    Context context;
    public AppRecyclerView(ArrayList<App> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filteredapp, parent, false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        App app = mData.get(position);
        holder.appName.setText(app.getName());
        holder.priceValue.setText(app.getPrice()+"");
        holder.delete.setImageResource(R.drawable.delete_icon);
        Picasso.with(context).load(app.getLarge_thumb_url()).into(holder.imageView);
        Double value = Double.parseDouble(app.getPrice());
        if(value < 2 && value >= 0 )
            holder.priceSymbol.setImageResource(R.drawable.price_low);
        else if(value >= 2 && value < 6)
            holder.priceSymbol.setImageResource(R.drawable.price_medium);
        else
            holder.priceSymbol.setImageResource(R.drawable.price_high);
        holder.app = app;
       holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.addApp(mData.get(position),context);
                mData.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView appName, priceValue;
        ImageView imageView, priceSymbol, delete;
        App app;

        public ViewHolder(View itemView) {
            super(itemView);
            this.app = app;
            appName = (TextView) itemView.findViewById(R.id.appName);
            priceValue = (TextView) itemView.findViewById(R.id.priceValue);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            priceSymbol = (ImageView) itemView.findViewById(R.id.priceSymbol);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }


}

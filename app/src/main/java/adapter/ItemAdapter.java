package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymart.ItemaddtocartActivity;
import com.example.mymart.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.ItemModel;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    Context context;
    List<ItemModel> itemModels;

    public ItemAdapter(Context context, List<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_description,viewGroup,false);
        return new ItemViewHolder(view);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }



    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

        final ItemModel model=itemModels.get(i);
        final String imgPath = "http://10.0.2.2:3001/uploads/"+ model.getImage();
        StrictMode();


        try {
            URL url=new URL(imgPath);
            itemViewHolder.imgview.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (Exception e){
            e.printStackTrace();
        }

        itemViewHolder.itemid.setText(model.getId());
        itemViewHolder.itemname.setText(model.getItemname());
        itemViewHolder.itemprice.setText(model.getItemprice());
        itemViewHolder.itemdescription.setText(model.getItemdescription());
        itemViewHolder.category.setText(model.getCategory());
        itemViewHolder.image.setText(model.getImage());


        itemViewHolder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ItemaddtocartActivity.class);
                intent.putExtra("name",model.getItemname());
                intent.putExtra("price",model.getItemprice());
                intent.putExtra("description",model.getItemdescription());
                intent.putExtra("category",model.getCategory());
                intent.putExtra("image",model.getImage());
                intent.putExtra("id",model.getId());
                intent.putExtra("image",model.getImage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgview;
        TextView itemname,itemprice,itemdescription,category,itemid,image;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgview=itemView.findViewById(R.id.imgview);
            itemname=itemView.findViewById(R.id.name);
            itemprice=itemView.findViewById(R.id.price);
            itemdescription=itemView.findViewById(R.id.description);
            category=itemView.findViewById(R.id.category);
            itemid=itemView.findViewById(R.id.itemid);
            image=itemView.findViewById(R.id.img);
        }
    }
}

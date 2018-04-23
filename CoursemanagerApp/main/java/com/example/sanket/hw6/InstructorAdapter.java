package com.example.sanket.hw6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hrishikesh Deshpande on 08-Nov-17.
 */

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder> {
    public Context context ;
    public List<InstructorInfo> instructorList ;
    public int checkedId ;
    public InstructorAdapter(Context context,List<InstructorInfo> instructorList ){
        this.context = context ;
        this.instructorList = instructorList ;
    }

    public int getCheckedId() {
        return checkedId;
    }

    public void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
    }
    int lastCheckedPosition = -1 ;
    boolean inBind = false ;

    @Override
    public InstructorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.instructor, parent, false);

        return new InstructorViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(InstructorViewHolder holder, final int position) {
        InstructorInfo instructorInfo = instructorList.get(position) ;
        holder.instructorName.setText(instructorInfo.getInstructor_fname()+" "+instructorInfo.getInstructor_lname()) ;
        holder.instructorImage.setImageBitmap(instructorInfo.getImage());
        holder.isSelected.setChecked(lastCheckedPosition == position);
        instructorInfo.setChecked(lastCheckedPosition==position);

    }

    @Override
    public int getItemCount() {
        return instructorList.size();
    }


    public class InstructorViewHolder extends RecyclerView.ViewHolder {
        ImageView instructorImage ;
        TextView instructorName ;
        RadioButton isSelected ;
        InstructorViewHolder(View view){
            super(view) ;
            instructorImage = (ImageView)view.findViewById(R.id.instructorImgRv) ;
            instructorName=(TextView)view.findViewById(R.id.instructorNameRv) ;
            isSelected=(RadioButton)view.findViewById(R.id.instructorSelectedRv) ;
            isSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastCheckedPosition= getAdapterPosition() ;
                    notifyDataSetChanged();
                }
            });
        }

    }

}

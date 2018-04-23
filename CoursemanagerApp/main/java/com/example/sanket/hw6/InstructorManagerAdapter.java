package com.example.sanket.hw6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by sanket on 11/8/2017.
 */

public class InstructorManagerAdapter  extends RecyclerView.Adapter<InstructorManagerAdapter.InstructorManagerViewHolder> {
    public Context context ;
    public ChangeInstructorFragment cf ;
    public List<InstructorInfo> instructorList ;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<InstructorInfo> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<InstructorInfo> instructorList) {
        this.instructorList = instructorList;
    }

    @Override
    public InstructorManagerAdapter.InstructorManagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.instructoritem, parent, false);

        return new InstructorManagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstructorManagerAdapter.InstructorManagerViewHolder holder, final int position) {
        final InstructorInfo instructorInfo = instructorList.get(position) ;
        holder.fullName.setText(instructorInfo.getInstructor_fname()+ " "+ instructorInfo.getInstructor_lname()) ;
        holder.EmailofInstructor.setText(instructorInfo.getInstructor_email());
        holder.PersonalWebOfInstr.setText(instructorInfo.getInstructor_website());
        holder.instructorImage1.setImageBitmap(instructorInfo.getImage());
        holder.ownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf.changeFragmentToInstructorDetail(instructorInfo);
            }
        });
        holder.ownView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete course?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.databaseDataManager.deleteInstructor(instructorInfo.instr_ID) ;
                                instructorList.remove(position) ;
                                notifyDataSetChanged();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.instructorList.size();
    }

    public class InstructorManagerViewHolder extends RecyclerView.ViewHolder {
        TextView fullName,EmailofInstructor,PersonalWebOfInstr ;
        ImageView instructorImage1 ;
        View ownView ;

        public InstructorManagerViewHolder(View view) {
            super(view) ;
            fullName = (TextView) view.findViewById(R.id.fullName) ;
            EmailofInstructor = (TextView) view.findViewById(R.id.EmailofInstructor) ;
            PersonalWebOfInstr = (TextView) view.findViewById(R.id.PersonalWebOfInstr) ;
            instructorImage1 = (ImageView)view.findViewById(R.id.instructorImage1) ;
            ownView = view ;
        }
    }

    public InstructorManagerAdapter(Context context, List<InstructorInfo> instructorList) {
        this.context = context ;
        this.instructorList = instructorList ;
        cf = (ChangeInstructorFragment)context ;
    }

    public interface ChangeInstructorFragment {
        void changeFragmentToInstructorDetail(InstructorInfo info) ;
    }
}

package com.example.sanket.hw6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hrishikesh Deshpande on 07-Nov-17.
 */

public class CourseManagerAdapter extends RecyclerView.Adapter<CourseManagerAdapter.CourseManagerViewHolder> {
    public Context context ;
    public List<CourseInfo> courseList ;
    public List<InstructorInfo> instructorList ;
    changeFragment cf ;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<CourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseInfo> courseList) {
        this.courseList = courseList;
    }

    public List<InstructorInfo> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<InstructorInfo> instructorList) {
        this.instructorList = instructorList;
    }

    @Override
    public CourseManagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.courseitem, parent, false);

        return new CourseManagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseManagerViewHolder holder, final int position) {
    final CourseInfo course = courseList.get(position) ;
        holder.courseTitle.setText(course.getTitle()) ;
        holder.courseInstructor.setText(course.getInstructorName());
        holder.courseTime.setText(course.getDay() + " " + course.getTime());
        holder.courseImage.setImageBitmap(getInstructorImageFromId(course.getInstructor_id()));
        course.setInstructorImage(getInstructorImageFromId(course.getInstructor_id()));
        holder.ownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf.goToCourseDetailFragment(course);
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
                            MainActivity.databaseDataManager.deleteCourse(course.getCourse_Id()) ;
                            courseList.remove(position) ;
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

    private Bitmap getInstructorImageFromId(int id) {
        if(instructorList == null) {
            return null ;
        }
        for(int i=0 ; i<instructorList.size() ; i++) {
            if(instructorList.get(i).getInstr_ID() == id) {
                return instructorList.get(i).getImage() ;
            }
        }
        return null ;
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseManagerViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle,courseInstructor,courseTime ;
        ImageView courseImage ;
        View ownView ;

        public CourseManagerViewHolder(View view) {
            super(view) ;
            courseTitle = (TextView) view.findViewById(R.id.courseTitle) ;
            courseInstructor = (TextView) view.findViewById(R.id.courseInstructor) ;
            courseTime = (TextView) view.findViewById(R.id.courseTime) ;
            courseImage = (ImageView)view.findViewById(R.id.courseImage) ;
            ownView = view ;
        }
    }

    public CourseManagerAdapter(Context context, List<CourseInfo> courseList) {
        this.context = context ;
        this.courseList = courseList ;
        cf = (changeFragment)context;
    }

    public interface changeFragment {
        void goToCourseDetailFragment(CourseInfo courseInfo) ;
    }
}

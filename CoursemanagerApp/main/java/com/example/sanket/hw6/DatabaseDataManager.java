package com.example.sanket.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by sanket on 10/23/2017.
 */

public class DatabaseDataManager {

   private Context context;
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase db;
    private RegisterinfoDAO registerinfoDAO;
    private InstructorInfoDAO instructorInfoDAO;
    private CourseInfoDAO courseInfoDAO;
    private UserCourseInfoDAO userCourseInfoDAO;

    public DatabaseDataManager(Context context){
        this.context=context;
        databaseOpenHelper = new DatabaseOpenHelper(this.context);
        db= databaseOpenHelper.getWritableDatabase();
        registerinfoDAO=new RegisterinfoDAO(db);
        instructorInfoDAO = new InstructorInfoDAO(db);
        courseInfoDAO = new CourseInfoDAO(db);
        userCourseInfoDAO = new UserCourseInfoDAO(db);
    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }

    public long saveUser(Registerinfo registerinfo){
        return this.registerinfoDAO.save(registerinfo);
    }

    public boolean checkRegisterDAO(String uname,String password){
        return this.registerinfoDAO.get(uname,password);
    }

    public long saveInstructor(InstructorInfo instructorInfo){
        return this.instructorInfoDAO.saveinstructor(instructorInfo);
    }

    public InstructorInfo getinstructor(int id){
        return this.instructorInfoDAO.getInstructor(id);
    }
    public List<InstructorInfo> getAll(){
        return this.instructorInfoDAO.getAll();
    }
    public long saveCourse(CourseInfo courseInfo){
        return this.courseInfoDAO.saveCourse(courseInfo);
    }

    public List<CourseInfo> getAllCourses(){
        return this.courseInfoDAO.getAllCourse();
    }
    public CourseInfo getCourse(int id){
        return this.courseInfoDAO.getCourse(id);
    }
    public long saveuserCourseInfo(UserCourseInfo userCourseInfo){
        return this.userCourseInfoDAO.saveuserCourseInfo(userCourseInfo);
    }
    public int getcourseId(String username){
        return this.userCourseInfoDAO.getcourseId(username);
    }
    public boolean deleteCourse(int id){
       return this.courseInfoDAO.deleteCourse(id);
    }

    public boolean deleteInstructor(int id){
        return this.instructorInfoDAO.deleteInstructor(id);
    }

}


package com.example.sanket.hw6;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentInteractionListener,
        CreateCourse.createCourseFragmentInteraction,
courseManager.CourseManagerFragmentInteraction,
        AddInstructure.AddInstructureFragmentInteraction,
CourseManagerAdapter.changeFragment,
CourseDetail.CourseDetailFragmentInteraction,
instructorDetail.OnInstructorDetailInteraction,
InstructorManagerAdapter.ChangeInstructorFragment,
instructorManager.onInstructorManagerFragmentInteraction,
Register.onInteractionWithRegister{

    public  static DatabaseDataManager databaseDataManager;
    public static CourseInfo courseDetailObj ;
    public static InstructorInfo infoObj ;
    String userName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseDataManager = new DatabaseDataManager(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new Login(),"first")
                .commit();
    }

    @Override
    public void gotoCourseManager(String Username) {
        userName = Username ;
        courseManager courseManager1 = new courseManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,courseManager1,"first")
                .addToBackStack(null)
                .commit();
    }

    public void goToAddInstructor() {
        AddInstructure courseManager1 = new AddInstructure();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,courseManager1,"addinstructor")
                .addToBackStack(null)
                .commit();
    }

    public void goToInstructorManager() {
        instructorManager ccFragment = new instructorManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,ccFragment,"instructorManager")
                .addToBackStack(null)
                .commit();
    }

    public void logOut() {
        Login courseManager1 = new Login();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,courseManager1,"login")
                .addToBackStack(null)
                .commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuHome) {
            gotoCourseManager(null);
        }else if(item.getItemId() == R.id.menuAddInstructor) {
                goToAddInstructor();
        }else if(item.getItemId() == R.id.menuExit) {
                finishAffinity();
        }else if(item.getItemId() == R.id.menuInstructor) {
            goToInstructorManager();
        }else if(item.getItemId() == R.id.menuLogout) {
            logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    public void RegisterFunction(View view) {
        Register f = new Register();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,f,"second")
                .addToBackStack(null)
                .commit();
    }
    @Override
    protected void onDestroy() {
        databaseDataManager.close();
        super.onDestroy();

    }

    @Override
    public void interactWithCreateCourse(String username) {

    }

    @Override
    public String onFragmentInteractionWithCourseManager() {
        return userName ;

    }

    @Override
    public void addCourseOnClick() {
        CreateCourse ccFragment = new CreateCourse();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,ccFragment,"createCourse")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void goToCourseDetailFragment(CourseInfo courseInfo) {
        CourseDetail ccFragment = new CourseDetail();
        courseDetailObj = courseInfo ;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,ccFragment,"Coursedetail")
                .addToBackStack(null)
                .commit();
    }



    @Override
    public CourseInfo onInteractionWithCourseDetail() {
        return courseDetailObj;
    }

    @Override
    public InstructorInfo onInteraction() {
        return infoObj;
    }

    @Override
    public void changeFragmentToInstructorDetail(InstructorInfo info) {
        instructorDetail ccFragment = new instructorDetail();
        infoObj = info ;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,ccFragment,"instructordetail")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onInstructorManagerInteraction() {

    }

    @Override
    public void afterRegister() {
        gotoCourseManager(null) ;
    }
}

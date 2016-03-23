package com.sevencstudio.harish.networksyncvolley;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by harish on 16/03/16.
 */
public class ModelViewBinding {


    public static void Bind(Activity activity, int id, Object object) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(id, null, false);


        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                String name = activity.getResources().getResourceEntryName(child.getId());
                String fielddata ="";
                VolleyWrapper.DEBUG(name+" #"+child.getClass().getSimpleName(), "views");
                Field field;
                try {
                    field= object.getClass().getDeclaredField(name);

                    field.setAccessible(true);
                    fielddata="" + field.get(object);
                    field.setAccessible(false);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


                if (child.getClass().getSimpleName().contains("TextView")) {



                        TextView textView = (TextView) activity.findViewById(child.getId());
                        textView.setText(fielddata);




                }
                else if (child.getClass().getSimpleName().contains("ImageView")) {
        
                        ImageView imageView = (ImageView) activity.findViewById(child.getId());
                        VolleyWrapper.load(fielddata).into(imageView);


                }
            }
        }
    }
  public static View Bind(Context context, int id, Object object) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(id, null, false);


        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                String name = context.getResources().getResourceEntryName(child.getId());
                String fielddata ="";
                VolleyWrapper.DEBUG(name+" #"+child.getClass().getSimpleName(), "views");
                Field field;
                try {
                    field= object.getClass().getDeclaredField(name);

                    field.setAccessible(true);
                    fielddata="" + field.get(object);
                    field.setAccessible(false);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


                if (child.getClass().getSimpleName().contains("TextView")) {



                        TextView textView = (TextView) view.findViewById(child.getId());
                        textView.setText(fielddata);




                }
                else if (child.getClass().getSimpleName().contains("ImageView")) {

                        ImageView imageView = (ImageView) view.findViewById(child.getId());
                        VolleyWrapper.load(fielddata).into(imageView);


                }
            }
        }
      return view;
    }

}

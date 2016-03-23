package com.sevencstudio.harish.networksyncvolley;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Created by harish on 16/03/16.
 */
public class ViewModelBinding {

    public static void Bind(Activity activity, int id, Object object) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(id, null, false);


        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                if (child.getClass().getSimpleName().contains("EditText")) {
                    String name = activity.getResources().getResourceEntryName(child.getId());


                    Field field;
                    try {
                        field= object.getClass().getDeclaredField(name);

                        EditText editText = (EditText) activity.findViewById(child.getId());
                        VolleyWrapper.DEBUG( field.getType().getSimpleName()  + "##" +editText.getText().toString(), "views");
                        SetFieldValue(object, editText.getText().toString(), field);

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }


                }
            }
        }

    }

    public static void SetFieldValue(Object object,String value,Field field)
    {
        field.setAccessible(true);
        String type = field.getType().getSimpleName();
        VolleyWrapper.DEBUG(type, "variable types");

        try {
        switch (type) {
            case "String":


                    field.set(object, ""+value);

                break;
            case "int":
                if(value.matches(""))
                    value ="0";
                field.set(object,Integer.parseInt(""+value));

                break;
            case "float":

                if(value.matches(""))
                    value ="0";
                field.set(object,Float.parseFloat("" + value));

                break;
            case "double":

                if(value.matches(""))
                    value ="0";

                VolleyWrapper.DEBUG(""+value, "vcheckvb value");
                field.set(object,Double.parseDouble("" + value));

                break;
            case "long":

                if(value.matches(""))
                    value ="0";
                field.set(object,Long.parseLong("" + value));

                break;

        }
        } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
    }
}

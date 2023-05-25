package com.recipeFinder.shared.utils;

import com.recipeFinder.features.GroceryList.models.GroceryListModel;
import com.recipeFinder.features.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Helpers {
    public static class Objects {
        public static Object[][] getTableData(ArrayList<Object> objects) {
            Object[][] data = new Object[objects.size()][];
            for (int i = 0; i < objects.size(); i++) {
                Object obj = objects.get(i);
                Class<?> objClass = obj.getClass();
                Field[] fields = objClass.getDeclaredFields();
                Object[] fieldValues = new Object[fields.length];

                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    field.setAccessible(true);
                    try {
                        fieldValues[j] = field.get(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                data[i] = fieldValues;
            }

            return data;
        }
    }
}

package com.flavorfinder.features;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    public String name;
    public String date;

    public Model(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public static String[] getTableHeaders() {
        Class<?> cls = Model.class;
        List<String> fieldNames = new ArrayList<>();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames.toArray(new String[0]);
    }


    public Object[] getClassFieldValues() {
        Class<?> cls = this.getClass();
        List<Object> fieldValues = new ArrayList<>();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                fieldValues.add(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return fieldValues.toArray();
    }

    public static void main(String[] args) {

        class GroceryListModel extends Model {
            public GroceryListModel(String name, String date) {
                super(name, date);
            }
        }

        ArrayList<GroceryListModel> models = new ArrayList<>();
        models.add(new GroceryListModel("name1", "date1"));
        models.add(new GroceryListModel("name2", "date2"));

        System.out.println(Arrays.toString(GroceryListModel.getTableHeaders()));
//        System.out.println(Arrays.deepToString(GroceryListModel.getTableData(models)));
    }
}

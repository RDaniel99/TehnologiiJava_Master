package database;

import models.Category;
import models.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class Database {
    private ArrayList<Record> records;
    private HashSet<String> categories;
    private static Database INSTANCE;

    private Database() {
        records = new ArrayList<>();
        categories = new HashSet<>();
    }

    public static synchronized Database getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    public void addCategory(Category category) {
        categories.add(category.getName());
    }

    public Boolean doesCategoryExist(String name) {
        return categories.contains(name);
    }

    public void addRecord(Record record) {

        records.add(record);
    }

    public void deleteRecord(Record record) {

        records.remove(record);
    }

    public ArrayList<Record> getRecords() {

        return records;
    }

    public void clear() {

        records.clear();
    }
}

package database;

import models.Record;

import java.util.ArrayList;

public class Database {
    private ArrayList<Record> records;
    private static Database INSTANCE;

    private Database() {
        records = new ArrayList<>();
    }

    public static synchronized Database getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
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

//package com.pirateking.canstar.helpers;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by ronald on 12/7/2017.
// */
//
//public class DBHelper extends SQLiteOpenHelper {
//    // database name
//    private static final String DB_NAME = "newtrends";
//
//    //name of tables
//    public static final String DB_TABLE_PRODUCTS = "tbl_products";
//    public static final String DB_TABLE_PRODUCTS1 = "tbl_product_details";
//    public static final String DB_TABLE_USERS = "tbl_users";
//    public static final String DB_TABLE_LOCATIONS = "tbl_locations";
//    public static final String DB_TABLE_INTRANSITS = "tbl_intransits";
//    public static final String DB_TABLE_ITEMS = "tbl_items";
//    public static final String DB_TABLE_PRICES = "tbl_prices";
//    public static final String DB_TABLE_PULLOUTS = "tbl_pullouts";
//    public static final String DB_TABLE_REASONS = "tbl_reasons";
//    public static final String DB_TABLE_TRANSACTIONS = "tbl_transactions";
//
//
////    public static final String DB_TABLE_SAMPLE = "tbl_sample";
////    public static final String DB_TABLE_Hospital = "tbl_hospital";
//    // Columns for tbl_header;
//
//
//    // columns for tbl_users
//    private static final String DB_COLUMN_NAME = "name";
//    private static final String DB_COLUMN_TYPE = "type";
//    private static final String DB_COLUMN_USERID = "user_id";
//
//
//    // references
//    // users, locations, intransits, items, prices, pullouts, reasons tables
//    private static final String DB_COLUMN_SERIAL_NO = "serial_no";
//
//    // columns locations
//    private static final String DB_COLUMN_LOCATION_CODE = "location_code";
//    private static final String DB_COLUMN_LOCATION_NAME = "location_name";
//
//    //columns for tbl_intransits
//    private static final String DB_COLUMN_DOCUMENT_NO = "document_no";
//    private static final String DB_COLUMN_ITEMCODE = "item_code";
//    private static final String DB_COLUMN_QUANTITY = "quantity";
//    private static final String DB_COLUMN_DATE_CREATED = "date_created";
//    private static final String DB_COLUMN_DATE_UPDATED = "date_updated";
//
//    //columns for tbl_items
//    private static final String DB_COLUMN_DESCRIPTION = "brand_desc";
//    private static final String DB_COLUMN_BRAND = "brand";
//    private static final String DB_COLUMN_BARCODE = "barcode";
//
//    //  columns for tbl_prices
//    private static final String DB_COLUMN_PRICE_ID = "price_id";
//    private static final String DB_COLUMN_PRICE = "price";
//
//    // colums for tbl_pullouts existed
//
//    //columns for tbl_reason
//    private static final String DB_COLUMN_REASON_TYPE = "reason_type";
//    private static final String DB_COLUMN_REASON = "reason";
//
//
//    // columns for tbl_transactions
//    private static final String DB_COLUMN_TRANS_ID = "trans_id";
//    private static final String DB_COLUMN_USER_ID = "user_id";
//    private static final String DB_COLUMN_PROMOCODE = "promo_code";
//
//
//
//    // test table
//    private static final String DB_KEY_ID = "id";
//    public static final String DB_KEY_PRODUCT_NAME = "product_name";
//    public static final String DB_KEY_CATEGORY = "category";
//
//    private DecimalFormat df2 = new DecimalFormat(".##");
//
//
//    private Context mContext;
//    public DBHelper(Context context) {
//        super(context, DB_NAME, null, 1);
//        this.mContext = context;
//        Log.d("Database Operations","Database Created");
//    }
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTableProducts = "CREATE TABLE " + DB_TABLE_PRODUCTS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_NAME + " TEXT, "
//                + DB_COLUMN_TYPE + " TEXT, "
//                + DB_COLUMN_USERID + " TEXT, "
//                + DB_COLUMN_SERIAL_NO + " TEXT);";
//
//        String createTableLocations = "CREATE TABLE " + DB_TABLE_LOCATIONS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_LOCATION_CODE + " TEXT, "
//                + DB_COLUMN_LOCATION_NAME + " TEXT, "
//                + DB_COLUMN_SERIAL_NO + " TEXT);";
//
//        String createTableIntransit = "CREATE TABLE " + DB_TABLE_INTRANSITS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_SERIAL_NO + " TEXT, "
//                + DB_COLUMN_LOCATION_CODE + " TEXT, "
//                + DB_COLUMN_DOCUMENT_NO + " TEXT, "
//                + DB_COLUMN_ITEMCODE + " TEXT, "
//                + DB_COLUMN_QUANTITY + " TEXT, "
//                + DB_COLUMN_DATE_CREATED + " TEXT, "
//                + DB_COLUMN_DATE_UPDATED + " TEXT);";
//
//        String createTableItems = "CREATE TABLE " + DB_TABLE_ITEMS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_SERIAL_NO + " TEXT, "
//                + DB_COLUMN_ITEMCODE + " TEXT, "
//                + DB_COLUMN_DESCRIPTION + " TEXT, "
//                + DB_COLUMN_BARCODE + " TEXT, "
//                + DB_COLUMN_BRAND + " TEXT);";
//
//        String createTablePrices = "CREATE TABLE " + DB_TABLE_PRICES
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_SERIAL_NO + " TEXT, "
//                + DB_COLUMN_ITEMCODE + " TEXT, "
//                + DB_COLUMN_PRICE + " TEXT, "
//                + DB_COLUMN_DATE_UPDATED + " TEXT);";
//
//        String createTablePullOuts = "CREATE TABLE " + DB_TABLE_PULLOUTS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_SERIAL_NO + "   TEXT, "
//                + DB_COLUMN_LOCATION_CODE + " TEXT, "
//                + DB_COLUMN_DOCUMENT_NO + " TEXT, "
//                + DB_COLUMN_ITEMCODE + " TEXT, "
//                + DB_COLUMN_QUANTITY + " TEXT, "
//                + DB_COLUMN_DATE_CREATED + " TEXT, "
//                + DB_COLUMN_DATE_UPDATED + " TEXT);";
//
//        String createTableReasons = "CREATE TABLE " + DB_TABLE_REASONS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_REASON_TYPE + " TEXT, "
//                + DB_COLUMN_REASON + " TEXT);";
//
//
////        String createTableSample = "CREATE TABLE " + DB_TABLE_USERS
////                + " (id INTEGER PRIMARY KEY, "
////                + DB_KEY_PRODUCT_NAME + " TEXT, "
////                + DB_KEY_CATEGORY + " TEXT);";
//
//        String createTableUsers = "CREATE TABLE " + DB_TABLE_USERS
//                + " (id INTEGER PRIMARY KEY, "
//                + DB_COLUMN_NAME + " TEXT, "
//                + DB_COLUMN_TYPE + " TEXT, "
//                + DB_COLUMN_USERID + " TEXT, "
//                + DB_COLUMN_SERIAL_NO + " TEXT);";
//
//
//
//        db.execSQL(createTableProducts);
//        db.execSQL(createTableUsers);
//        db.execSQL(createTableLocations);
//        db.execSQL(createTableIntransit);
//        db.execSQL(createTableItems);
//        db.execSQL(createTablePrices);
//        db.execSQL(createTablePullOuts);
//        db.execSQL(createTableReasons);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PRODUCTS);
//        onCreate(db);
//    }
//
//    public void InsertData(String productname, String category){
//        ContentValues cv = new ContentValues();
//        cv.put(DB_COLUMN_NAME, productname);
//        cv.put(DB_COLUMN_TYPE, category);
//        long k = this.getWritableDatabase().insert(DB_TABLE_PRODUCTS, null, cv);
//        this.close();
//        Log.d("Suck","On row Inserted");
//    }
//
//    public List<ProductObjects> getProductDetails(String searchInput){
//        List<ProductObjects> mSearchList = new ArrayList<>();
//        String sql;
//        Log.d("ratbu ","sample");
//        if(searchInput.isEmpty()) sql = "SELECT * FROM tbl_products"  ;
//        else sql = "SELECT * FROM " + DB_TABLE_PRODUCTS + " WHERE " + DB_COLUMN_NAME + " LIKE '%"+searchInput+"%' " +
//                "or "+DB_COLUMN_SERIAL_NO+" LIKE '%"+searchInput+"%'  LIMIT 10";
//
//        Cursor c = this.getReadableDatabase().rawQuery(sql, null);
//
//        if(c.getCount() > 0){
//            c.moveToFirst();
//            while (!c.isAfterLast()){
//                ProductObjects productObjects = new ProductObjects();
//                productObjects.setId(c.getString(c.getColumnIndexOrThrow(DB_KEY_ID)));
//                productObjects.setProduct_name(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_NAME)));
//                productObjects.setProduct_category(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_TYPE)));
//                mSearchList.add(productObjects);
//                c.moveToNext();
//                Log.d("testing sample","" + productObjects.getId().toString()  + " " + productObjects.getProduct_name().toString() + " " + productObjects.getProduct_category().toString() );
//            }
//        }
//
//        c.close();
//        this.close();
//        return mSearchList;
//    }
//
//
//
//
//    public Boolean checkUsersDetails(){
//        Boolean test = null;
//        Cursor c = this.getReadableDatabase().rawQuery("SELECT count(*) FROM " + DB_TABLE_USERS, null);
//        c.moveToFirst();
//        int count  = c.getInt(0);
//        if(count > 0){
//            Log.d("example", "" + count);
//            test = true;
//            return test;
//        }else{
//            test = false;
//        }
//        return test;
//    }
//
////    public void saveUserDetails(List<UserObjects> mlistUserObjects){
////        List<UserObjects> mlistuserdetails = new ArrayList<>();
////        mlistuserdetails = mlistUserObjects;
////        SQLiteDatabase db = this.getWritableDatabase();
////        if(mlistuserdetails.size() > 0) {
////
////            Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_USERS , null);
//////            if (c.getColumnCount() > 0) {
////
//////                db.execSQL("DELETE FROM " + DB_TABLE_USERS);
//////                db.close();
////
////
////                for (int x = 0;  mlistuserdetails.size() > x; x++) {
////                    Log.d("fucksize123", "" + mlistuserdetails.size() + " " +mlistuserdetails.get(x).getName());
////                    ContentValues cv = new ContentValues();
////                    cv.put(DB_COLUMN_NAME, mlistuserdetails.get(0).getName());
////                    cv.put(DB_COLUMN_TYPE, mlistuserdetails.get(0).getType());
////                    cv.put(DB_COLUMN_USER_ID, mlistuserdetails.get(0).getUser_id());
////                    cv.put(DB_COLUMN_SERIAL_NO, mlistuserdetails.get(0).getSerial_no());
////
////                    db.insert(DB_TABLE_USERS, null, cv);
////                    db.close();
////                    Log.d("tito pemsa", "On row Inserted");
////                }
//////            }
////
////        }
////
////    }
//
//
//    public void insertUsers(List<UserObjects> mlistuserobjects){
//        List<UserObjects> mlistUsers = new ArrayList<>();
//        mlistUsers = mlistuserobjects;
//        try{
//            Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_USERS, null);
//            if (c.getColumnCount() > 0) {
//                SQLiteDatabase db = this.getWritableDatabase();
//                db.execSQL("DELETE FROM " + DB_TABLE_USERS);
//                db.close();
//
//                for(int x=0; x<=mlistUsers.size(); x++){
//                    ContentValues values = new ContentValues();
//                    values.put(DB_COLUMN_NAME, mlistUsers.get(x).getName());
//                    values.put(DB_COLUMN_TYPE, mlistUsers.get(x).getType());
//                    values.put(DB_COLUMN_USER_ID,mlistUsers.get(x).getUser_id());
//                    values.put(DB_COLUMN_SERIAL_NO, mlistuserobjects.get(x).getSerial_no());
//                    this.getWritableDatabase().insert(DB_TABLE_USERS, null, values);
//                    this.close();
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void insertItems(List<ItemsObject> mlistitemObjects){
//        List<ItemsObject> mlistItems = new ArrayList<>();
//        mlistItems = mlistitemObjects;
//
//        try{
//            Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_ITEMS, null);
////            c.moveToFirst();
//            Log.d("sizes1 ", " " + c.getCount() +  " " + mlistItems.size());
//            if (mlistItems.size() > c.getCount()) {
//                Log.d("sizes2 ", " " + c.getCount() +  " " + mlistItems.size());
//                SQLiteDatabase db = this.getWritableDatabase();
//                db.execSQL("DELETE FROM " + DB_TABLE_ITEMS);
//                db.close();
//
//                for(int x=0; x<=mlistItems.size(); x++){
//                    ContentValues values = new ContentValues();
//                    Log.d("bulaga", "" + mlistItems.get(x).getSerial_No() + " " + mlistItems.get(x).getId());
//                    values.put(DB_COLUMN_SERIAL_NO, mlistItems.get(x).getSerial_No());
//                    values.put(DB_COLUMN_ITEMCODE, mlistItems.get(x).getItemcode());
//                    values.put(DB_COLUMN_DESCRIPTION,mlistItems.get(x).getDescription());
//                    values.put(DB_COLUMN_BRAND, mlistItems.get(x).getBrand());
//                    values.put(DB_COLUMN_BARCODE, mlistItems.get(x).getBarcode());
//                    this.getWritableDatabase().insert(DB_TABLE_ITEMS, null, values);
//                    this.close();
//                }
//            }
//
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public void insertPrices(List<PriceObjects> mlistprices){
//        List<PriceObjects> mlistItems = new ArrayList<>();
//        mlistItems = mlistprices;
//        Log.d("fuck123","123123" + mlistItems.size());
//        try{
//            Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_PRICES, null);
//            if (mlistItems.size() > c.getCount()) {
//                Log.d("fuck123","123123");
//                SQLiteDatabase db = this.getWritableDatabase();
//                db.execSQL("DELETE FROM " + DB_TABLE_ITEMS);
//                db.close();
//
//                for(int x=0; x<=mlistItems.size(); x++){
//                    ContentValues values = new ContentValues();
//                    values.put(DB_COLUMN_SERIAL_NO, mlistItems.get(x).getSerial_no());
//                    values.put(DB_COLUMN_ITEMCODE, mlistItems.get(x).getItemcode());
//                    values.put(DB_COLUMN_PRICE,mlistItems.get(x).getPrice());
//                    values.put(DB_COLUMN_DATE_UPDATED, mlistItems.get(x).getDate());
//                    this.getWritableDatabase().insert(DB_TABLE_PRICES, null, values);
//                    this.close();
//                }
//            }
//
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    // for sales
//    public List<ItemsObject> getBarcode(String barcode){
//        List<ItemsObject> mlistbarcode = new ArrayList<>();
//        Cursor c = this.getReadableDatabase().rawQuery("select a.id ,a."+DB_COLUMN_SERIAL_NO+",a."+DB_COLUMN_ITEMCODE+",a."+DB_COLUMN_DESCRIPTION+"," +
//                "a."+DB_COLUMN_BRAND+",a."+DB_COLUMN_BARCODE+",b."+DB_COLUMN_PRICE+" from "+DB_TABLE_ITEMS+" a, " +
//            ""+DB_TABLE_PRICES+" b where a." + DB_COLUMN_ITEMCODE + " = b."+DB_COLUMN_ITEMCODE+" and a."+DB_COLUMN_BARCODE+" = '"+barcode+"';", null);
////        int x = 0;
//        if(c.getCount() > 0){
//            c.moveToFirst();
//                ItemsObject itemsObject = new ItemsObject();
//                itemsObject.setId(c.getInt(c.getColumnIndexOrThrow(DB_KEY_ID)));
//                itemsObject.setItemcode(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_ITEMCODE)));
//                itemsObject.setBrand(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_BRAND)));
//                itemsObject.setDescription(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_DESCRIPTION)));
//                itemsObject.setPrice(c.getDouble(c.getColumnIndexOrThrow(DB_COLUMN_PRICE)));
//                mlistbarcode.add(itemsObject);
////                Log.d("fuckshit", " " + mlistbarcode.get(0).getItemcode());
//            }
//        c.close();
//        this.close();
//        return  mlistbarcode;
//    }
//
////    public List<ItemsObject> getBarcodeResult (String barcode){
////        List<ItemsObject> mlistItemSearch = new ArrayList<>();
////        Log.d("pawalk1","test");
////        int x = 0;
////        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_ITEMS, null);
////        if(c.getCount() > 0){
////            Log.d("pawalk2","test");
////            c.moveToFirst();
////            while (!c.isAfterLast()){
////                ItemsObject itemsObject = new ItemsObject();
////                itemsObject.setId(c.getInt(c.getColumnIndexOrThrow(DB_KEY_ID)));
////                itemsObject.setItemcode(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_ITEMCODE)));
////                itemsObject.setBrand(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_BRAND)));
////                itemsObject.setDescription(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_DESCRIPTION)));
////                mlistItemSearch.add(itemsObject);
////                x++;
////                Log.d("pawalk", " " + mlistItemSearch.get(x).getItemcode());
////            }
////        }
////        c.close();
////        this.close();
////        return mlistItemSearch;
////    }
//
////    public List<PlantsObject> getSearchPlants(String searchInput){
////        List<PlantsObject> mSearchList = new ArrayList<>();
////        Log.d("test", ""+searchInput);
////        Cursor c = this.getReadableDatabase().rawQuery("select * from tbl_plants_details where plant_name LIKE '%"+searchInput+"%'  or name_tag LIKE '%"+searchInput+"%' or diseases LIKE '%"+searchInput+"%' LIMIT 1", null);
////        if (c.getCount() > 0) {
////            c.moveToFirst();
////            while(!c.isAfterLast()) {
////                PlantsObject plantsObject = new PlantsObject();
////                plantsObject.setId(c.getString(c.getColumnIndexOrThrow(DB_KEY_ID)));
////                plantsObject.setPlant_name(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_PLANT_NAME)));
////                plantsObject.setSci_name(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_SCI_NAME)));
////                plantsObject.setName_tag(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_NAME_TAG)));
////
////                Log.d("dbhelper", plantsObject.getName_tag() + " On row Inserted " + c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_SCI_NAME)));
////
////                mSearchList.add(plantsObject);
////                c.moveToNext();
////            }
////        }
////
////        c.close();
////        this.close();
////        return mSearchList;
////    }
//
//    public void saveTransaction(List<UserObjects> mlisttransctions){
//        List<UserObjects> mlistgetTransactions = new ArrayList<>();
//        mlistgetTransactions = mlisttransctions;
//        Log.d("dbhelper size", "" + mlistgetTransactions.size());
//
//
////        if(mlistgetPlant.size() > 0) {
////
////            Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_WEB_PLANTS + " order by name_tag", null);
////            if (c.getColumnCount() > 0) {
////                SQLiteDatabase db = this.getWritableDatabase();
////                db.execSQL("DELETE FROM " + DB_TABLE_WEB_PLANTS);
////                Log.d("Database Operations", "On TABLE Hospital deleted");
////                Log.d("fuck", "" + mlistgetPlant.size());
////                db.close();
////
////
////                for (int x = 0; x < mlistgetPlant.size(); x++) {
////                    ContentValues cv = new ContentValues();
////                    cv.put(DB_KEY_WEB_PLANT_NAME, mlistgetPlant.get(x).getPlant_name());
////                    cv.put(DB_KEY_WEB_SCI_NAME, mlistgetPlant.get(x).getSci_name());
////                    cv.put(DB_KEY_WEB_NAME_TAG, mlistgetPlant.get(x).getName_tag());
////                    cv.put(DB_KEY_WEB_TRIVIA, mlistgetPlant.get(x).getTrivia());
////                    cv.put(DB_KEY_WEB_DISEASES, mlistgetPlant.get(x).getDisease());
////                    cv.put(DB_KEY_WEB_HISTORY, mlistgetPlant.get(x).getHistory());
////                    cv.put(DB_KEY_WEB_BENEFITS, mlistgetPlant.get(x).getBenefits());
////                    cv.put(DB_KEY_WEB_DOSAGE, mlistgetPlant.get(x).getDosage());
////                    cv.put(DB_KEY_WEB_PREPARATION, mlistgetPlant.get(x).getPreparation());
////                    cv.put(DB_KEY_WEB_PLANT_DESCRIPTION, mlistgetPlant.get(x).getDescription());
////                    long k = this.getWritableDatabase().insert(DB_TABLE_WEB_PLANTS, null, cv);
////                    this.close();
////                    Log.d("from web inserted", "On row Inserted");
////                }
////            }
////
////        }
//
//    }
//
////    public void saveSample(){
////
////        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_CONTENTS, null);
////        if(c.getColumnCount() > 0){
////            SQLiteDatabase db = this.getWritableDatabase();
////            db.execSQL("DELETE FROM "+ DB_TABLE_CONTENTS );
////            Log.d("Database Operations","On TABLE deleted");
////            db.close();
////
////            String[] names = {
////                    "Akapulko",
////                    "Bawang",
////                    "Ampalaya",
////                    "Guava",
////                    "Lagundi",
//////                    "Niyog-niyogan",
////                    "Sambong",
////                    "Tsaang gubat",
////                    "Herba Buena",
////                    "Pansit-Pansitan",
////                    "Sabila",
////                    "Tanglad",
////                    "Chinese Oregano",
////                    "Papaya",
//////                    "Balanoy/Solasi",
////                    "Luyang Dilaw"
////            };
////            String[] disease = {
////                    "Ringworm Human Worm Infistation",
////                    "Diarrhea Stomatitis SoreGums",
////                    "Diabetes Hemorrohoids Cough Ulcer Fever",
////                    "Diarrhea Stomatitis SoreGums SunBurn PricklyHeat Fainting",
////                    "Cough Chickenfox SkinRushes Measles Colds Pain Asthma",
////                    "Human Worm Infistation",
////                    "Fever Kidney stones headaches wounds gas distention upset stomach asthma",
////                    "Diarrhea Stomachache",
////                    "Dysmenorrhea",
////                    "Abscess/ Boils Headache",
////                    "Sore Throat Dandruff Ankle Sprain / Wrist Sprain Burns Acne",
////                    "Diarrhea",
////                    "Colds and Paint Cough",
////                    "Indigestion Constipation Dengue",
////                    "Athlete’s Foot",
////                    "Rhizome Leaves",
////            };
////            for (int x = 0; x < names.length; x++){
////                ContentValues cv = new ContentValues();
////                cv.put(DB_KEY_PLANT_NAME, names[x]);
////                cv.put(DB_KEY_DISEASE, disease[x]);
////                long k = this.getWritableDatabase().insert(DB_TABLE_CONTENTS, null, cv);
////                this.close();
////                Log.d("fucksuck","On row Inserted");
////            }
////        }
////    }
////
////    public void saveHospital(){
////        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_Hospital, null);
////        if(c.getColumnCount() > 0){
////            SQLiteDatabase db = this.getWritableDatabase();
////            db.execSQL("DELETE FROM "+ DB_TABLE_Hospital );
////            Log.d("Database Operations","On TABLE Hospital deleted");
////            db.close();
////
////            String[] hospital = {
////                    "Clinica Antipolo Hospital and Wellness Center"
////                    ,"Metro Antipolo Hospital and Medical Center Inc"
////                    ,"Antipolo Doctors Hospital"
////                    ,"Soriano-Leyble Medical and Maternity Hospital"
////                    ,"Cornel Medical Center"
////                    ,"Rizal Provincial Hospital System Antipolo Annex"
////                    ,"Unciano Medical Center"
////                    ,"Blessed Trinity Hospital"
////                    ,"Rizal Provincial Hospital System"
////            };
////            for (int x = 0; x < hospital.length; x++){
////                ContentValues cv = new ContentValues();
////                cv.put(DB_KEY_HOSPITALNAME, hospital[x]);
////                long k = this.getWritableDatabase().insert(DB_TABLE_Hospital, null, cv);
////                this.close();
////                Log.d("Database Operations","On row Inserted");
////            }
////        }
////    }
//
////    public void savePlantDetails(List<PlantsObject> mlistPlantDetails){
////        List<PlantsObject> mlistgetPlant = new ArrayList<>();
////        mlistgetPlant = mlistPlantDetails;
////            Log.d("dbhelper size", "" + mlistgetPlant.size());
////            if(mlistgetPlant.size() > 0) {
////
////                Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_WEB_PLANTS + " order by name_tag", null);
////                if (c.getColumnCount() > 0) {
////                    SQLiteDatabase db = this.getWritableDatabase();
////                    db.execSQL("DELETE FROM " + DB_TABLE_WEB_PLANTS);
////                    Log.d("Database Operations", "On TABLE Hospital deleted");
////                    Log.d("fuck", "" + mlistgetPlant.size());
////                    db.close();
////
////
////                    for (int x = 0; x < mlistgetPlant.size(); x++) {
////                        ContentValues cv = new ContentValues();
////                        cv.put(DB_KEY_WEB_PLANT_NAME, mlistgetPlant.get(x).getPlant_name());
////                        cv.put(DB_KEY_WEB_SCI_NAME, mlistgetPlant.get(x).getSci_name());
////                        cv.put(DB_KEY_WEB_NAME_TAG, mlistgetPlant.get(x).getName_tag());
////                        cv.put(DB_KEY_WEB_TRIVIA, mlistgetPlant.get(x).getTrivia());
////                        cv.put(DB_KEY_WEB_DISEASES, mlistgetPlant.get(x).getDisease());
////                        cv.put(DB_KEY_WEB_HISTORY, mlistgetPlant.get(x).getHistory());
////                        cv.put(DB_KEY_WEB_BENEFITS, mlistgetPlant.get(x).getBenefits());
////                        cv.put(DB_KEY_WEB_DOSAGE, mlistgetPlant.get(x).getDosage());
////                        cv.put(DB_KEY_WEB_PREPARATION, mlistgetPlant.get(x).getPreparation());
////                        cv.put(DB_KEY_WEB_PLANT_DESCRIPTION, mlistgetPlant.get(x).getDescription());
////                        long k = this.getWritableDatabase().insert(DB_TABLE_WEB_PLANTS, null, cv);
////                        this.close();
////                        Log.d("from web inserted", "On row Inserted");
////                    }
////                }
////
////            }
////
////    }
////
////
////    public void saveDiseaseDetails(List<DiseaseObject> mlistDisease){
////        List<DiseaseObject> mlistgetDisease = new ArrayList<>();
////        mlistgetDisease = mlistDisease;
////        Log.d("dbhelper size", "" + mlistgetDisease.size());
////
////        if(mlistgetDisease.size() > 0) {
////
////                Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_WEB_DISEASE, null);
////                if (c.getColumnCount() > 0) {
////                    SQLiteDatabase db = this.getWritableDatabase();
////                    db.execSQL("DELETE FROM " + DB_TABLE_WEB_DISEASE);
////                    Log.d("Database Operations", "On TABLE Hospital deleted");
////                    db.close();
////
////
////                    for (int x = 0; x < mlistgetDisease.size(); x++) {
////                        ContentValues cv = new ContentValues();
////                        cv.put(DB_KEY_WEB_DISEASE, mlistgetDisease.get(x).getDiseasename());
////                        cv.put(DB_KEY_WEB_DISEASE_DESC, mlistgetDisease.get(x).getDisease_description());
////                        long k = this.getWritableDatabase().insert(DB_TABLE_WEB_DISEASE, null, cv);
////                        this.close();
////                        Log.d("from web inserted", "On row Inserted");
////                    }
////                }
////
////        }
////
////    }
////
//
////
////
////    public List<PlantsObject> getPlantDetails() {
////        List<PlantsObject> mListPlantsDetails = new ArrayList<>();
////
////        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_WEB_PLANTS, null);
////
////        if (c.getCount() > 0) {
////            c.moveToFirst();
////            while(!c.isAfterLast()) {
////                PlantsObject plantsObject = new PlantsObject();
////
////
////                plantsObject.setId(String.valueOf(c.getInt(c.getColumnIndexOrThrow(DB_KEY_ID))));
////                plantsObject.setPlant_name(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_PLANT_NAME)));
////                plantsObject.setSci_name(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_SCI_NAME)));
////                plantsObject.setDescription(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_PLANT_DESCRIPTION)));
////                plantsObject.setName_tag(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_NAME_TAG)));
////                plantsObject.setDisease(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_DISEASES)));
////                plantsObject.setHistory(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_HISTORY)));
////                plantsObject.setPreparation(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_PREPARATION)));
////                plantsObject.setTrivia(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_TRIVIA)));
////                plantsObject.setBenefits(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_BENEFITS)));
////                plantsObject.setPreparation(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_PREPARATION)));
////                plantsObject.setDosage(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_DOSAGE)));
////                Log.d("plant_details","" + plantsObject.getId().toString());
////
////                mListPlantsDetails.add(plantsObject);
////                c.moveToNext();
////            }
////        }
////        c.close();
////        this.close();
////        return mListPlantsDetails;
////    }
////
////
////    public List<DiseaseObject> getDieseaseDetails() {
////        List<DiseaseObject> mListDiseaseDetails = new ArrayList<>();
////
////        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_WEB_DISEASE, null);
////
////        if (c.getCount() > 0) {
////            c.moveToFirst();
////            while(!c.isAfterLast()) {
////                DiseaseObject diseaseObject = new DiseaseObject();
////                diseaseObject.setDiseaseid(String.valueOf(c.getInt(c.getColumnIndexOrThrow(DB_KEY_ID))));
////                diseaseObject.setDiseasename(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_DISEASE)));
////                diseaseObject.setDisease_description(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_DISEASE_DESC)));
////
////                Log.d("disease_details","" + diseaseObject.getDiseaseid().toString());
////
////                mListDiseaseDetails.add(diseaseObject);
////                c.moveToNext();
////            }
////        }
////        c.close();
////        this.close();
////        return mListDiseaseDetails;
////    }
////
////    public List<DiseaseObject> getSearchDisease(String searchInput){
////        List<DiseaseObject> mSearchList = new ArrayList<>();
////        Log.d("test", ""+searchInput);
////        Cursor c = this.getReadableDatabase().rawQuery("select * from tbl_disease_details where disease_name LIKE '%"+searchInput+"%' LIMIT 1", null);
////        if (c.getCount() > 0) {
////            c.moveToFirst();
////            while(!c.isAfterLast()) {
////                  DiseaseObject diseaseObject = new DiseaseObject();
////                  diseaseObject.setDiseaseid(c.getString(c.getColumnIndexOrThrow(DB_KEY_ID)));
////                  diseaseObject.setDiseasename(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_DISEASE)));
////                  diseaseObject.setDisease_description(c.getString(c.getColumnIndexOrThrow(DB_KEY_WEB_DISEASE_DESC)));
////
////                Log.d("puke","" + diseaseObject.getDiseaseid().toString());
////                mSearchList.add(diseaseObject);
////                c.moveToNext();
////            }
////        }
////
////        c.close();
////        this.close();
////        return mSearchList;
////    }
//
//
//
//}

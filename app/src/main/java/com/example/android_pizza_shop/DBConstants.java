package com.example.android_pizza_shop;

public class DBConstants {
    public static final String DATABASE_NAME = "LEPizza.db";
    public static final int DATABASE_VERSION = 1;
    // Hashing algorithm for passwords
    public static final String HASH_ALGORITHM = "SHA-256";
    public static final String USERS_TABLE = "Users";

    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String createTableUsers = "CREATE TABLE " + USERS_TABLE +" (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_PASSWORD + " TEXT)";

    public static final String insertInitialUsers = "INSERT INTO Users (email, password) VALUES " +
            "('user1@example.com', '" + PasswordHasher.hashPassword("password1") + "')," +
            "('user2@example.com', '" + PasswordHasher.hashPassword("password2") + "')," +
            "('user3@example.com', '" + PasswordHasher.hashPassword("password3") + "')," +
            "('user4@example.com', '" + PasswordHasher.hashPassword("password4") + "')," +
            "('user5@example.com', '" + PasswordHasher.hashPassword("password5") + "')," +
            "('user6@example.com', '" + PasswordHasher.hashPassword("password6") + "')," +
            "('user7@example.com', '" + PasswordHasher.hashPassword("password7") + "')," +
            "('user8@example.com', '" + PasswordHasher.hashPassword("password8") + "')," +
            "('user9@example.com', '" + PasswordHasher.hashPassword("password9") + "')," +
            "('user10@example.com', '" + PasswordHasher.hashPassword("password10") + "')";

    // Table for Pizzas
    public static final String PIZZAS_TABLE = "Pizzas";
    public static final String COLUMN_PIZZA_NAME = "name";
    public static final String COLUMN_PIZZA_KILOJOULES = "kilojoules";
    public static final String COLUMN_PIZZA_DESCRIPTION = "description";
    public static final String COLUMN_PIZZA_IMAGE = "image";
    public static final String COLUMN_PIZZA_PRICE = "price";
    public static final String createTablePizzas = "CREATE TABLE " + PIZZAS_TABLE + " (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_PIZZA_NAME + " TEXT," +
            COLUMN_PIZZA_KILOJOULES + " REAL," +
            COLUMN_PIZZA_DESCRIPTION + " TEXT," +
            COLUMN_PIZZA_IMAGE + " TEXT," +
            COLUMN_PIZZA_PRICE + " REAL)";

    // Table for Sides
    public static final String SIDES_TABLE = "Sides";
    public static final String COLUMN_SIDES_NAME = "name";
    public static final String COLUMN_SIDES_KILOJOULES = "kilojoules";
    public static final String COLUMN_SIDES_DESCRIPTION = "description";
    public static final String COLUMN_SIDES_IMAGE = "image";
    public static final String COLUMN_SIDES_PRICE = "price";
    public static final String createTableSides = "CREATE TABLE " + SIDES_TABLE + " (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_SIDES_NAME + " TEXT," +
            COLUMN_SIDES_KILOJOULES + " REAL," +
            COLUMN_SIDES_DESCRIPTION + " TEXT," +
            COLUMN_SIDES_IMAGE + " TEXT," +
            COLUMN_SIDES_PRICE + " REAL)";

    // Table for Drinks
    public static final String DRINKS_TABLE = "Drinks";
    public static final String COLUMN_DRINKS_NAME = "name";
    public static final String COLUMN_DRINKS_KILOJOULES = "kilojoules";
    public static final String COLUMN_DRINKS_DESCRIPTION = "description";
    public static final String COLUMN_DRINKS_IMAGE = "image";
    public static final String COLUMN_DRINKS_PRICE = "price";
    public static final String createTableDrinks = "CREATE TABLE " + DRINKS_TABLE + " (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DRINKS_NAME + " TEXT," +
            COLUMN_DRINKS_KILOJOULES + " REAL," +
            COLUMN_DRINKS_DESCRIPTION + " TEXT," +
            COLUMN_DRINKS_IMAGE + " TEXT," +
            COLUMN_DRINKS_PRICE + " REAL)";

    // Table for Desserts
    public static final String DESSERTS_TABLE = "Desserts";
    public static final String COLUMN_DESSERTS_NAME = "name";
    public static final String COLUMN_DESSERTS_KILOJOULES = "kilojoules";
    public static final String COLUMN_DESSERTS_DESCRIPTION = "description";
    public static final String COLUMN_DESSERTS_IMAGE = "image";
    public static final String COLUMN_DESSERTS_PRICE = "price";
    public static final String createTableDesserts = "CREATE TABLE " + DESSERTS_TABLE + " (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DESSERTS_NAME + " TEXT," +
            COLUMN_DESSERTS_KILOJOULES + " REAL," +
            COLUMN_DESSERTS_DESCRIPTION + " TEXT," +
            COLUMN_DESSERTS_IMAGE + " TEXT," +
            COLUMN_DESSERTS_PRICE + " REAL)";

    // Insert statements for Pizzas
    public static final String insertInitialPizzas = "INSERT INTO " + PIZZAS_TABLE +
            " (" + COLUMN_PIZZA_NAME + ", " +
            COLUMN_PIZZA_KILOJOULES + ", " +
            COLUMN_PIZZA_DESCRIPTION + ", " +
            COLUMN_PIZZA_IMAGE + ", " +
            COLUMN_PIZZA_PRICE + ") VALUES " +
            "('Pepperoni ', 1000, 'A great crust, gooey cheese, and tons of pepperoni.', 'pizza1_image', 31)," +
            "('Fire Breather', 1100, 'Spicy pizza sauce, Topped with mozzarella, chicken, bacon, spinach, cherry tomatoes, and red onion on top.', 'pizza2_image', 32)," +
            "('Tomato', 1200, 'Fresh tomato sauce, sundried tomato and basil, with mozzarella cheese top a crisp, chewy crust.', 'pizza3_image', 33)," +
            "('Supreme', 1300, 'Pepperoni, mild sausage, beef topping, green pepper, sliced mushrooms, red onion and pizza mozzarella.', 'pizza4_image', 34)," +
            "('Fig', 1400, 'Fig and prosciutto pizza with balsamic glaze Topped with fresh mozzarella, arugula, and walnuts.', 'pizza5_image', 35)," +
            "('Minimalist', 1500, 'Mini pizza spread with pizza sauce and topped with basil', 'pizza6_image', 36)," +
            "('Mozzarella Spinach', 1600, 'Mozzarella pizza topped with spinach', 'pizza7_image', 37)," +
            "('Red Onion', 1700, 'Red onions, spinach and olives on a crispy crust', 'pizza8_image', 38)," +
            "('4 cheese', 1800, '4 cheeses loaded onto the one pizza', 'pizza9_image', 39)," +
            "('Mozzarella pizza', 1900, 'Fresh tomato sauce, basil, and mozzarella cheese top a crisp, chewy crust.', 'pizza10_image', 40)";

    // Insert statements for Sides
    public static final String insertInitialSides = "INSERT INTO " + SIDES_TABLE +
            " (" + COLUMN_SIDES_NAME + ", " +
            COLUMN_SIDES_KILOJOULES + ", " +
            COLUMN_SIDES_DESCRIPTION + ", " +
            COLUMN_SIDES_IMAGE + ", " +
            COLUMN_SIDES_PRICE + ") VALUES " +
            "('Chicken Wings', 500, 'Deep fried chicken wings seasoned with a delicious blend of herbs & spices', 'side1_image', 11)," +
            "('Hot Chips', 600, 'Deep fried chips', 'side2_image', 12)," +
            "('Roasted Vegetable Salad', 700, 'Chunky sweet potato roasted with a vibrant mix of vegetables', 'side3_image', 13)," +
            "('Chicken Ranch Salad', 800, 'Chicken slices laid over a mix of lettuce, cucumber, tomato, avocado and onion', 'side4_image', 14)," +
            "('Cheesy Vegetable Pasta', 900, 'Spiral pasta mixed in with 3 types of cheese', 'side5_image', 15)," +
            "('Spicy Vege Wrap', 1000, 'Spicy vegetables in a warm toasted tortilla wrap', 'side6_image', 16)," +
            "('Paneer Pocket', 1100, 'Curry mixed vegetables in a pocket of puff pastry', 'side7_image', 17)," +
            "('Cheesy Garlic Bread', 1200, 'French bread toasted with a garlic butter mixture, topped with mozzarella and Parmesan cheese', 'side8_image', 18)," +
            "('Garlic Bread', 1300, 'French bread toasted with a garlic butter mixture', 'side9_image', 19)," +
            "('Buttered Potato Slots', 1400, 'Oven roasted whole potatoes, sliced with butter squares in each slot', 'side10_image', 20)";

    // Insert statements for Drinks
    public static final String insertInitialDrinks = "INSERT INTO " + DRINKS_TABLE +
            " (" + COLUMN_DRINKS_NAME + ", " +
            COLUMN_DRINKS_KILOJOULES + ", " +
            COLUMN_DRINKS_DESCRIPTION + ", " +
            COLUMN_DRINKS_IMAGE + ", " +
            COLUMN_DRINKS_PRICE + ") VALUES " +
            "('Coke', 200, '375ml can', 'drink1_image', 1)," +
            "('Coke Zero', 300, '375ml can', 'drink2_image', 2)," +
            "('Diet Coke', 400, '375ml can', 'drink3_image', 3)," +
            "('7up', 500, '375ml can', 'drink4_image', 4)," +
            "('Orange Fanta', 600, '600ml bottle', 'drink5_image', 5)," +
            "('Mountain Dew', 700, '375ml can', 'drink6_image', 6)," +
            "('Pepsi', 800, '1.25L bottle', 'drink7_image', 7)," +
            "('Pepsi Max', 900, '375ml can', 'drink8_image', 8)," +
            "('Mount Franklin Water', 1000, '600ml bottle', 'drink9_image', 9)," +
            "('Mountain Dew Voltage', 1100, '375ml can', 'drink10_image', 10)";

    // Insert statements for Desserts
    public static final String insertInitialDesserts = "INSERT INTO " + DESSERTS_TABLE +
            " (" + COLUMN_DESSERTS_NAME + ", " +
            COLUMN_DESSERTS_KILOJOULES + ", " +
            COLUMN_DESSERTS_DESCRIPTION + ", " +
            COLUMN_DESSERTS_IMAGE + ", " +
            COLUMN_DESSERTS_PRICE + ") VALUES " +
            "('Fruit Pizza', 300, 'Seasonal fruits spread over a crispy biscuit base', 'dessert1_image', 21)," +
            "('S''mores Pizza', 400, 'Pizza base topped with smooth chocolate and roasted marshmallows ', 'dessert2_image', 22)," +
            "('Churros', 500, 'Churros dusted with a mix of cinnamon sugar and icing sugar', 'dessert3_image', 23)," +
            "('Ice Cream Sundae', 600, '3 scoops of icecream, topped with sprinkles ', 'dessert4_image', 24)," +
            "('Fruit Salad', 700, 'Seasonal fruit mixed in a bowl', 'dessert5_image', 25)," +
            "('Macaroons', 800, 'Choice of 4 flavours of lovely chewy macaroons', 'dessert6_image', 26)," +
            "('Dutch Pancakes', 900, '12 golden Mini pancakes, sprinkled with icing sugar and served with whipped cream ', 'dessert7_image', 27)," +
            "('Strawberry Fruit Cup', 1000, 'Layers of strawberry separated by freshly whipped cream ', 'dessert8_image', 28)," +
            "('Blueberry Pie', 1100, 'Blueberry pie topped with extra Blueberries and sliced almonds', 'dessert9_image', 29)," +
            "('Lemon Meringue Pie', 1200, 'A crisp base filled with a tangy citrus centre and topped with a fluffy meringue top', 'dessert10_image', 30)";

    public static final String PRODUCTS_TABLE = "Products";
    public static final String COLUMN_PRODUCT_ID = "ID";
    public static final String COLUMN_PRODUCT_TYPE = "productType";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_KILOJOULES = "kilojoules";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    public static final String COLUMN_PRODUCT_IMAGE = "image";
    public static final String COLUMN_PRODUCT_PRICE = "price";
    public static final String createTableProducts = "CREATE TABLE " + PRODUCTS_TABLE + " (" +
            COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_PRODUCT_TYPE + " TEXT," +
            COLUMN_PRODUCT_NAME + " TEXT," +
            COLUMN_PRODUCT_KILOJOULES + " REAL," +
            COLUMN_PRODUCT_DESCRIPTION + " TEXT," +
            COLUMN_PRODUCT_IMAGE + " TEXT," +
            COLUMN_PRODUCT_PRICE + " REAL)";


    public static final String insertInitialProducts = "INSERT INTO " + PRODUCTS_TABLE +
            " (" + COLUMN_PRODUCT_TYPE + ", " +
            COLUMN_PRODUCT_NAME + ", " +
            COLUMN_PRODUCT_KILOJOULES + ", " +
            COLUMN_PRODUCT_DESCRIPTION + ", " +
            COLUMN_PRODUCT_IMAGE + ", " +
            COLUMN_PRODUCT_PRICE + ") VALUES " +

            // Insert statements for Pizzas
            "('Pizza', 'Pizza1', 1000, 'Description for Pizza1', 'pizza1_image', 31)," +
            "('Pizza', 'Pizza2', 1100, 'Description for Pizza2', 'pizza2_image', 32)," +
            "('Pizza', 'Pizza3', 1200, 'Description for Pizza3', 'pizza3_image', 33)," +
            "('Pizza', 'Pizza4', 1300, 'Description for Pizza4', 'pizza4_image', 34)," +
            "('Pizza', 'Pizza5', 1400, 'Description for Pizza5', 'pizza5_image', 35)," +
            "('Pizza', 'Pizza6', 1500, 'Description for Pizza6', 'pizza6_image', 36)," +
            "('Pizza', 'Pizza7', 1600, 'Description for Pizza7', 'pizza7_image', 37)," +
            "('Pizza', 'Pizza8', 1700, 'Description for Pizza8', 'pizza8_image', 38)," +
            "('Pizza', 'Pizza9', 1800, 'Description for Pizza9', 'pizza9_image', 39)," +
            "('Pizza', 'Pizza10', 1900, 'Description for Pizza10', 'pizza10_image', 40)," +

            // Insert statements for Sides
            "('Side', 'Side1', 500, 'Description for Side1', 'side1_image', 11)," +
            "('Side', 'Side2', 600, 'Description for Side2', 'side2_image', 12)," +
            "('Side', 'Side3', 700, 'Description for Side3', 'side3_image', 13)," +
            "('Side', 'Side4', 800, 'Description for Side4', 'side4_image', 14)," +
            "('Side', 'Side5', 900, 'Description for Side5', 'side5_image', 15)," +
            "('Side', 'Side6', 1000, 'Description for Side6', 'side6_image', 16)," +
            "('Side', 'Side7', 1100, 'Description for Side7', 'side7_image', 17)," +
            "('Side', 'Side8', 1200, 'Description for Side8', 'side8_image', 18)," +
            "('Side', 'Side9', 1300, 'Description for Side9', 'side9_image', 19)," +
            "('Side', 'Side10', 1400, 'Description for Side10', 'side10_image', 20)," +

            // Insert statements for Drinks
            "('Drink', 'Drink1', 200, 'Description for Drink1', 'drink1_image', 1)," +
            "('Drink', 'Drink2', 300, 'Description for Drink2', 'drink2_image', 2)," +
            "('Drink', 'Drink3', 400, 'Description for Drink3', 'drink3_image', 3)," +
            "('Drink', 'Drink4', 500, 'Description for Drink4', 'drink4_image', 4)," +
            "('Drink', 'Drink5', 600, 'Description for Drink5', 'drink5_image', 5)," +
            "('Drink', 'Drink6', 700, 'Description for Drink6', 'drink6_image', 6)," +
            "('Drink', 'Drink7', 800, 'Description for Drink7', 'drink7_image', 7)," +
            "('Drink', 'Drink8', 900, 'Description for Drink8', 'drink8_image', 8)," +
            "('Drink', 'Drink9', 1000, 'Description for Drink9', 'drink9_image', 9)," +
            "('Drink', 'Drink10', 1100, 'Description for Drink10', 'drink10_image', 10)," +

            // Insert statements for Desserts
            "('Dessert', 'Dessert1', 300, 'Description for Dessert1', 'dessert1_image', 21)," +
            "('Dessert', 'Dessert2', 400, 'Description for Dessert2', 'dessert2_image', 22)," +
            "('Dessert', 'Dessert3', 500, 'Description for Dessert3', 'dessert3_image', 23)," +
            "('Dessert', 'Dessert4', 600, 'Description for Dessert4', 'dessert4_image', 24)," +
            "('Dessert', 'Dessert5', 700, 'Description for Dessert5', 'dessert5_image', 25)," +
            "('Dessert', 'Dessert6', 800, 'Description for Dessert6', 'dessert6_image', 26)," +
            "('Dessert', 'Dessert7', 900, 'Description for Dessert7', 'dessert7_image', 27)," +
            "('Dessert', 'Dessert8', 1000, 'Description for Dessert8', 'dessert8_image', 28)," +
            "('Dessert', 'Dessert9', 1100, 'Description for Dessert9', 'dessert9_image', 29)," +
            "('Dessert', 'Dessert10', 1200, 'Description for Dessert10', 'dessert10_image', 30)";

    // Table for Pickup Stores
    public static final String PICKUP_STORES_TABLE = "PickupStores";
    public static final String COLUMN_STORE_ID = "store_id";
    public static final String COLUMN_STORE_NAME = "store_name";
    public static final String COLUMN_STORE_ADDRESS = "store_address";
    public static final String COLUMN_STORE_PHONE = "store_phone";
    public static final String createTablePickupStores = "CREATE TABLE " + PICKUP_STORES_TABLE + " (" +
            COLUMN_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_STORE_NAME + " TEXT," +
            COLUMN_STORE_ADDRESS + " TEXT," +
            COLUMN_STORE_PHONE + " TEXT)";

    // Insert statements for Pickup Stores
    // Insert statements for Pickup Stores
    public static final String insertInitialPickupStores = "INSERT INTO " + PICKUP_STORES_TABLE + " (" +
            COLUMN_STORE_NAME + ", " +
            COLUMN_STORE_ADDRESS + ", " +
            COLUMN_STORE_PHONE + ") VALUES " +
            "('Store1', '123 Main St', '123-456-7890')," +
            "('Store2', '456 Elm St', '234-567-8901')," +
            "('Store3', '789 Oak St', '345-678-9012')," +
            "('Store4', '321 Pine St', '456-789-0123')," +
            "('Store5', '654 Maple St', '567-890-1234')," +
            "('Store6', '987 Cedar St', '678-901-2345')," +
            "('Store7', '210 Birch St', '789-012-3456')," +
            "('Store8', '543 Walnut St', '890-123-4567')," +
            "('Store9', '876 Spruce St', '901-234-5678')," +
            "('Store10', '109 Fir St', '012-345-6789')";

    // Table for Orders
    public static final String ORDERS_TABLE = "Orders";
    public static final String ORDERS_ORDER_ID = "OrderID";
    public static final String ORDERS_USER_NAME = "Username";
    public static final String ORDERS_ORDER_DATE = "OrderDate";
    public static final String ORDERS_TOTAL_AMOUNT = "TotalAmount";
    public static final String createTableOrders = "CREATE TABLE " + ORDERS_TABLE + " (" +
            ORDERS_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ORDERS_USER_NAME + " TEXT," +
            ORDERS_ORDER_DATE + " TEXT," +
            ORDERS_TOTAL_AMOUNT + " REAL)";

    // Table for OrderItems
    public static final String ORDER_ITEMS_TABLE = "OrderItems";
    public static final String ORDERITEMS_ITEM_ID = "OrderItemID";
    public static final String ORDERITEMS_PRODUCT_TYPE = "ProductType";
    public static final String ORDERITEMS_PRODUCT_NAME = "ProductName";
    public static final String ORDERITEMS_KILOJOULES = "Kilojoules";
    public static final String ORDERITEMS_ITEM_DESCRIPTION = "Description";
    public static final String ORDERITEMS_ITEM_IMAGE = "Image";
    public static final String ORDERITEMS_ITEM_PRICE = "Price";
    public static final String ORDERITEMS_ITEM_QUANTITY = "Quantity";
    public static final String createTableOrderItems = "CREATE TABLE " + ORDER_ITEMS_TABLE + " (" +
            ORDERITEMS_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ORDERS_ORDER_ID + " INTEGER," +
            ORDERITEMS_PRODUCT_TYPE + " TEXT," +
            ORDERITEMS_PRODUCT_NAME + " TEXT," +
            ORDERITEMS_KILOJOULES + " REAL," +
            ORDERITEMS_ITEM_DESCRIPTION + " TEXT," +
            ORDERITEMS_ITEM_IMAGE + " TEXT," +
            ORDERITEMS_ITEM_PRICE + " REAL," +
            ORDERITEMS_ITEM_QUANTITY + " INTEGER)";

    /*
    public static final String insertInitialUsers = "INSERT INTO Users (email, password) VALUES " +
            "('user1@example.com', 'password1')," +
            "('user2@example.com', 'password2')," +
            "('user3@example.com', 'password3')," +
            "('user4@example.com', 'password4')," +
            "('user5@example.com', 'password5')," +
            "('user6@example.com', 'password6')," +
            "('user7@example.com', 'password7')," +
            "('user8@example.com', 'password8')," +
            "('user9@example.com', 'password9')," +
            "('user10@example.com', 'password10')";
     */
}

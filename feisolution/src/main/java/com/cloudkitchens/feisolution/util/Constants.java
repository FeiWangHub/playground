package com.cloudkitchens.feisolution.util;

public class Constants {

    public static final int ORDERS_PER_RECEIVE = 2;
    public static final int ORDER_RECEIVE_FREQUENCY_SEC = 1;
    public static final int COURIER_TRIP_TIME_MIN_SEC = 3;//inclusive
    public static final int COURIER_TRIP_TIME_MAX_SEC = 15;//inclusive

    public static final String ORDERS_JSON = "[\n" +
            "    {\n" +
            "        \"id\": \"a8cfcb76-7f24-4420-a5ba-d46dd77bdffd\", \n" +
            "        \"name\": \"Banana Split\", \n" +
            "        \"prepTime\": 4\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"58e9b5fe-3fde-4a27-8e98-682e58a4a65d\", \n" +
            "        \"name\": \"McFlury\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"2ec069e3-576f-48eb-869f-74a540ef840c\", \n" +
            "        \"name\": \"Acai Bowl\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"690b85f7-8c7d-4337-bd02-04e04454c826\", \n" +
            "        \"name\": \"Yogurt\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"972aa5b8-5d83-4d5e-8cf3-8a1a1437b18a\", \n" +
            "        \"name\": \"Chocolate Gelato\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"c18e1242-0856-4203-a98c-7066ead3bd6b\", \n" +
            "        \"name\": \"Cobb Salad\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"66a2611c-9a93-4ccd-bb85-98f423247bf9\", \n" +
            "        \"name\": \"Cottage Cheese\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4cc9d503-4e0e-42a3-b200-87c785468df9\", \n" +
            "        \"name\": \"Coke\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"0a6537d7-9568-400e-9857-7aceb4e7347c\", \n" +
            "        \"name\": \"Snow Cone\", \n" +
            "        \"prepTime\": 4\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"74e0893f-8544-4298-b507-9cf5ab847d83\", \n" +
            "        \"name\": \"Pad See Ew\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"7a5ea4ed-e378-4354-8ab3-a09cf563f621\", \n" +
            "        \"name\": \"Chunky Monkey\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"60fbe431-7f7f-46c5-8ecf-6a26ae898685\", \n" +
            "        \"name\": \"Beef Stew\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"2e7f4153-92cb-43e2-96f4-d77e2f25422b\", \n" +
            "        \"name\": \"Cheese\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"d4ead98f-a158-429c-9c0d-28ab0fa830a6\", \n" +
            "        \"name\": \"Spinach Omelet\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"e868e485-c759-411b-b439-ca7086326bf6\", \n" +
            "        \"name\": \"Beef Hash\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"2bfc7ca9-d8ea-4625-9a03-6aa4b5fb635e\", \n" +
            "        \"name\": \"Pork Chop\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"1a4d0e6d-e50e-4ec0-ad5d-0f33e6a724e6\", \n" +
            "        \"name\": \"Kale Salad\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"a9f8f42f-7a58-4629-8b66-1acbf362f3b8\", \n" +
            "        \"name\": \"Fresh Fruit\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"6d2c9eb3-6ff8-408a-a60d-e60eaa467dae\", \n" +
            "        \"name\": \"Cranberry Salad\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"916ddba3-9c25-4065-8a90-ecf5c40c8394\", \n" +
            "        \"name\": \"Fudge Ice Cream Cake\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"23f5cb3a-fa51-49ad-9e29-83e98d6e7d6f\", \n" +
            "        \"name\": \"Mint Chocolate Ice Cream\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"c700ab30-d7bd-4e45-aafb-4329876c716c\", \n" +
            "        \"name\": \"Vegan Pizza\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"cf0932a9-533c-4603-bb1e-512c6e697b92\", \n" +
            "        \"name\": \"Orange Chicken\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"ed05dafe-d928-4f1a-856d-1dab3868acbd\", \n" +
            "        \"name\": \"MeatLoaf\", \n" +
            "        \"prepTime\": 7\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"53f012e3-da55-4314-b2e6-28e63f0418ad\", \n" +
            "        \"name\": \"Milk\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"5dd79771-4536-4eaa-b64c-63ac606f1357\", \n" +
            "        \"name\": \"Pastrami Sandwich\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"f84d8263-7aae-4254-85b7-27865ffd76b7\", \n" +
            "        \"name\": \"Arugula\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"f7074980-3424-43d4-9166-f495077a273e\", \n" +
            "        \"name\": \"Pickles\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"dcafe7ca-f7a7-4262-9b14-ab19729b2055\", \n" +
            "        \"name\": \"Chicken\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"614e4f4d-b2f4-4b60-9421-238f61affe20\", \n" +
            "        \"name\": \"Cookie Dough\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"d2d2d948-f019-4806-bb7e-bb2172bd75d9\", \n" +
            "        \"name\": \"Hamburger\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"8275f014-ed83-4554-b0ff-0dbdc24e57ad\", \n" +
            "        \"name\": \"French Fries\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"9687809b-8f4b-4a6f-be3f-6e41e4ced3ed\", \n" +
            "        \"name\": \"Ice\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"e537a1b8-1505-49ae-a71e-a3ab80e8b606\", \n" +
            "        \"name\": \"Carne Asada\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3aaccfce-3dbe-4967-a2fb-b6c06b4de61d\", \n" +
            "        \"name\": \"Sherbet\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"1b56ee15-70e7-443e-96cb-0479e9d46381\", \n" +
            "        \"name\": \"Orange Sorbet\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"51c5017b-f812-40ac-8d68-87aab3fbab6a\", \n" +
            "        \"name\": \"Frosty\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"9748651e-c3f8-4c4a-9de9-5360467c86a2\", \n" +
            "        \"name\": \"Fresh Bread\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"345cfefe-692e-4866-986e-99a18bedbf24\", \n" +
            "        \"name\": \"Burrito\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"87441694-ff97-4d1b-89e3-c4b0b1fd041e\", \n" +
            "        \"name\": \"Icy\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3713d8af-e6a0-4c11-966c-f26786dc602c\", \n" +
            "        \"name\": \"Push Pop\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"20343dea-8856-4717-9be9-be1b37d4791f\", \n" +
            "        \"name\": \"Pasta\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"098604b5-a141-4a5f-a2e3-0b653515cc42\", \n" +
            "        \"name\": \"Chicken Nuggets\", \n" +
            "        \"prepTime\": 4\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"a5697bad-48d4-46fd-b3bc-64ef2485907a\", \n" +
            "        \"name\": \"Ice Cream Sandwich\", \n" +
            "        \"prepTime\": 7\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"e09f9282-8e05-48a7-8e6e-e23937383735\", \n" +
            "        \"name\": \"Taco\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"9df6bbc7-55ee-4f67-8dd1-a9f5ccc56355\", \n" +
            "        \"name\": \"Tomato Soup\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"a2b13869-6b7e-4e35-8e11-fa9889d24073\", \n" +
            "        \"name\": \"Vanilla Ice Cream\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"0522c2ec-0242-45e7-8542-f3e98c1384b9\", \n" +
            "        \"name\": \"Poppers\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"aba0fc48-ec05-422f-aad4-8b8ec4de636d\", \n" +
            "        \"name\": \"Popsicle\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"68515b89-96bf-48b6-a07c-68cf17ca3c25\", \n" +
            "        \"name\": \"Strawberries\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"caf107cc-e6d6-421c-9991-8bcfcc15d5c5\", \n" +
            "        \"name\": \"Brown Rice\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4d308771-a0d6-46e7-8ff1-679ddc1d1aa4\", \n" +
            "        \"name\": \"Cheese Pizza\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"78b92574-49cd-4995-b5d0-635b3ced5a18\", \n" +
            "        \"name\": \"Pressed Juice\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"1fab9088-d99d-47ee-acf5-d0f0851738e4\", \n" +
            "        \"name\": \"Coconut\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"d7864cd5-f557-4c3e-93d9-b3fb6bb4a37b\", \n" +
            "        \"name\": \"Onion Rings\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"5f06be90-beac-4a51-a871-f8e324d38c3c\", \n" +
            "        \"name\": \"Fish Tacos\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"90367105-ec26-41d8-a4a2-6c474d3f703b\", \n" +
            "        \"name\": \"Pot Stickers\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"05ef2197-5e9a-446b-825e-4d169bdf5670\", \n" +
            "        \"name\": \"Kombucha\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"41330835-9221-483a-924f-08c7f664d656\", \n" +
            "        \"name\": \"Mixed Greens\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"51754609-3f89-487b-9888-bac0a65f86e2\", \n" +
            "        \"name\": \"Sushi\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3f0008f5-9429-4a16-ae44-f8f4256797ca\", \n" +
            "        \"name\": \"Apples\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"c052077c-687d-4f24-b2e1-75edeee1186a\", \n" +
            "        \"name\": \"Kebab\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"1b481a3e-84eb-4532-bd29-47f04e3e7adc\", \n" +
            "        \"name\": \"Mac & Cheese\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"b5d9491a-54b7-4916-a379-fed69d5d180d\", \n" +
            "        \"name\": \"Corn Dog\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"7bdf9af2-d90e-44e2-9c6c-1ff7b5121133\", \n" +
            "        \"name\": \"Grilled Corn Salad\", \n" +
            "        \"prepTime\": 7\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4b5793f3-2af5-4ac3-816a-7f9c6f91483c\", \n" +
            "        \"name\": \"Pistachio Ice Cream\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"160ccc3a-af83-43e0-af0b-6a889aea7339\", \n" +
            "        \"name\": \"Strawberyy Banana Split\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3b9da9f3-2465-4fbc-84c5-5ecb9bd132b1\", \n" +
            "        \"name\": \"McFlury\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"e6b72c73-fa53-4f2c-a004-0b60ba7b3be9\", \n" +
            "        \"name\": \"Acai Bowl\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4546646c-3ca5-4da6-9c60-312a897a6907\", \n" +
            "        \"name\": \"Yogurt\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"8ea031b3-6545-4140-9dee-745268d7b877\", \n" +
            "        \"name\": \"Chocolate Gelato\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"24f3e17b-eba0-409a-8630-463cb05d89c3\", \n" +
            "        \"name\": \"Cobb Salad\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"def3f65e-6ffb-4715-8561-ace9d312ddc5\", \n" +
            "        \"name\": \"Cottage Cheese\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"2c772706-189c-4ec4-80ad-06ea17f21f11\", \n" +
            "        \"name\": \"Coke\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"a0023411-c19d-4723-b6d1-6b8c8ff1b417\", \n" +
            "        \"name\": \"Snow Cone\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"46a4e47d-ccc1-4c88-a006-83930521899c\", \n" +
            "        \"name\": \"Pad See Ew\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"e65ef8c8-d9e2-47cf-a6e2-ac976bcfdb9d\", \n" +
            "        \"name\": \"Chunky Monkey\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"848dfc63-eab6-48b7-a2db-04d13190ce8c\", \n" +
            "        \"name\": \"Beef Stew\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"42951124-c574-45a0-bddf-25e702c65914\", \n" +
            "        \"name\": \"Cheese\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"df726ab4-a999-46b5-8e31-4a1bd523fcb9\", \n" +
            "        \"name\": \"Spinach Omelet\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"5bb755b9-e6a8-4932-9139-bc04fafbc240\", \n" +
            "        \"name\": \"Beef Hash\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"58dbdb26-670c-4dac-b72c-21152f5d62e7\", \n" +
            "        \"name\": \"Pork Chop\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4ed428d6-f247-451a-aebf-c5157b2eb502\", \n" +
            "        \"name\": \"Kale Salad\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"62e667ee-a86d-4be2-97f6-a6af60183e88\", \n" +
            "        \"name\": \"Fresh Fruit\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"df4e3e19-55d2-4131-8ab0-3563b44f8291\", \n" +
            "        \"name\": \"Cranberry Salad\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"44e6ea89-f11c-414d-aa72-7c10641eff8d\", \n" +
            "        \"name\": \"Fudge Ice Cream Cake\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"48465fc7-d534-4b9b-b919-63e5beebd301\", \n" +
            "        \"name\": \"Mint Chocolate Ice Cream\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"fb850bd3-728f-4118-a097-2bcf3a12c239\", \n" +
            "        \"name\": \"Vegan Pizza\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"8d6a4d1a-ca56-4567-b3aa-a1e3d20f8017\", \n" +
            "        \"name\": \"Orange Chicken\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"68e83bf6-bdee-4192-8905-40140b421bc6\", \n" +
            "        \"name\": \"MeatLoaf\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"0d74c9f0-4fd5-4606-a1dc-019d9ab52af6\", \n" +
            "        \"name\": \"Milk\", \n" +
            "        \"prepTime\": 15\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"1b9a920d-2880-44a3-abba-20903f2690d6\", \n" +
            "        \"name\": \"Pastrami Sandwich\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"c5ec5a4b-818e-4478-8956-019259be4168\", \n" +
            "        \"name\": \"Arugula\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4c25f887-03cc-4dcd-9945-6a8102c5ef22\", \n" +
            "        \"name\": \"Pickles\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"7f2381be-5ab9-4385-a7a2-4a940a22e134\", \n" +
            "        \"name\": \"Chicken\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"75bef98f-89ba-4359-b738-b3184688ca7a\", \n" +
            "        \"name\": \"Cookie Dough\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"60db864b-8270-43ef-9d91-985707390765\", \n" +
            "        \"name\": \"Hamburger\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"22f532ca-dfc8-448a-9e04-7dcfa01a175e\", \n" +
            "        \"name\": \"French Fries\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"a0d76390-3e71-440e-a742-20c36a67fa9a\", \n" +
            "        \"name\": \"Ice\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"020ad68f-da34-4eca-ae7e-3b67e80a299c\", \n" +
            "        \"name\": \"Carne Asada\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"2aab593e-1d1d-4a65-a582-2999f80c3dee\", \n" +
            "        \"name\": \"Sherbet\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3447acdb-475a-400c-91e0-9724d2043e27\", \n" +
            "        \"name\": \"Orange Sorbet\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"f6e68da8-9909-431e-bf1b-88367677a03e\", \n" +
            "        \"name\": \"Frosty\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"bcd22756-b3ac-473b-93fa-a19cf941bc40\", \n" +
            "        \"name\": \"Fresh Bread\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"506a5c01-b06b-4af8-a1f2-913f9ae01078\", \n" +
            "        \"name\": \"Burrito\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"10ea72c1-b538-4eb8-b260-f58775481a0b\", \n" +
            "        \"name\": \"Icy\", \n" +
            "        \"prepTime\": 9\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"5e9cb832-6a0a-49fa-8799-8d744b499fce\", \n" +
            "        \"name\": \"Push Pop\", \n" +
            "        \"prepTime\": 7\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"a7d778ff-9711-4d3c-b370-6052965f64c0\", \n" +
            "        \"name\": \"Pasta\", \n" +
            "        \"prepTime\": 7\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"40990373-5eac-4e15-9634-450886fe3374\", \n" +
            "        \"name\": \"Chicken Nuggets\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3ea0971e-2478-4545-b814-a857fb294a9d\", \n" +
            "        \"name\": \"Ice Cream Sandwich\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"33bcf189-ba71-48ab-a2d5-75c18d180269\", \n" +
            "        \"name\": \"Taco\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"22fc8ae6-74f7-467f-8735-774d579a2ae0\", \n" +
            "        \"name\": \"Tomato Soup\", \n" +
            "        \"prepTime\": 8\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"19de820c-4691-420c-ad67-95ec7f5abc2d\", \n" +
            "        \"name\": \"Vanilla Ice Cream\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"3b62609c-38f2-499e-a9b1-c3604dad425d\", \n" +
            "        \"name\": \"Poppers\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"df79a7e4-d3f9-450c-b2e3-316fb462db29\", \n" +
            "        \"name\": \"Popsicle\", \n" +
            "        \"prepTime\": 5\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"d5fbbee2-09a7-459e-9621-33a66cdbf3ab\", \n" +
            "        \"name\": \"Strawberries\", \n" +
            "        \"prepTime\": 10\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"69ad8b1c-3218-4c41-aa42-5aca43ca4173\", \n" +
            "        \"name\": \"Brown Rice\", \n" +
            "        \"prepTime\": 14\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"68037649-56cd-4f0f-90fd-6e6ccbf61b37\", \n" +
            "        \"name\": \"Cheese Pizza\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"5bd1e697-10b2-48cf-83c4-033eea97bfb2\", \n" +
            "        \"name\": \"Pressed Juice\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"228ec414-19ca-41e4-8d6c-bd2b0662bfa3\", \n" +
            "        \"name\": \"Coconut\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"b1315bfb-4528-4ae2-b6ed-adb03a25b94a\", \n" +
            "        \"name\": \"Onion Rings\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"eafcb22b-0f68-4773-80ba-f163b191578f\", \n" +
            "        \"name\": \"Fish Tacos\", \n" +
            "        \"prepTime\": 13\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"b25914fa-c5b0-4aa6-a0f5-fcc76203bba5\", \n" +
            "        \"name\": \"Pot Stickers\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"03267ca9-47fd-4d16-bba7-a650cc7660b6\", \n" +
            "        \"name\": \"Kombucha\", \n" +
            "        \"prepTime\": 4\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"9012736d-777b-4f5b-a12d-982e302fefa1\", \n" +
            "        \"name\": \"Mixed Greens\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"f21bf58b-e23b-491f-a285-d70cb21cc36f\", \n" +
            "        \"name\": \"Sushi\", \n" +
            "        \"prepTime\": 3\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"057bc043-5f22-4169-9711-c6e2488b35f7\", \n" +
            "        \"name\": \"Apples\", \n" +
            "        \"prepTime\": 12\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"7f682ade-8375-4ef4-aa9b-45440f361268\", \n" +
            "        \"name\": \"Kebab\", \n" +
            "        \"prepTime\": 6\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"0d841704-92a0-44e1-aaf8-02a8ee920775\", \n" +
            "        \"name\": \"Mac & Cheese\", \n" +
            "        \"prepTime\": 4\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"7f89b973-d4fd-4d6e-a279-afc2db6b3304\", \n" +
            "        \"name\": \"Corn Dog\", \n" +
            "        \"prepTime\": 11\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"2a23c77f-3ee3-4a52-8810-93e73df4e887\", \n" +
            "        \"name\": \"Grilled Corn Salad\", \n" +
            "        \"prepTime\": 7\n" +
            "    }, \n" +
            "    {\n" +
            "        \"id\": \"4f304b59-6634-4558-a128-a8ce12b1f818\", \n" +
            "        \"name\": \"Pistachio Ice Cream\", \n" +
            "        \"prepTime\": 3\n" +
            "    }\n" +
            "]";

}

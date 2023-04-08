public class Main {

    public static void main(String[] args) {
        SmallDatabase database = new SmallDatabase();
        Record record1 = new Record(234678L, "Иванов Иван Иванович", 2035.34);
        Record record2 = new Record(1L, "Иванов Иван Иванович", 2035.34);
        Record record3 = new Record(2L, "Яков Яков Якович", 25.34);
        Record recordClone = new Record(2L, "Клон Яков Якович", 25.34);

        database.setRecord(record1);
        database.setRecord(record2);
        database.setRecord(record3);
        database.printAll();
        System.out.println("-------------------------------");

        database.setRecord(recordClone);// не должно добавиться
        database.printAll();

        System.out.println("-------------------------------");

        System.out.println("Получить значение по номеру аккаунта");
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("Получить записи по имени");
        System.out.println(database.getRecordByName("Иванов Иван Иванович"));

        System.out.println("-------------------------------");

        System.out.println("Получить записи по значению");
        System.out.println(database.getRecordByValue(2035.34));

        System.out.println("-------------------------------");

        System.out.println("Изменение имени записи");
        database.editNameByAccount(234678L, "Андреев Андрей Андреевич");
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("Изменение значения записи");
        database.editValueByAccount(234678L, 10000.00);
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("Изменение записи");
        Record record4 = new Record(234678L, "Измененный Иван Иванович", 2035.34);
        database.editRecordByAccount(record4);
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("Удаление по аккаунту");
        database.deleteByAccount(234678L);
        database.printAll();

        System.out.println("-------------------------------");

        System.out.println("Удаление не существующего");

        database.deleteByAccount(234678L);
        database.printAll();

        System.out.println("-------------------------------");

        System.out.println("Изменение не существующего");

        database.editValueByAccount(234678L, 22.0);
        database.printAll();

    }
}

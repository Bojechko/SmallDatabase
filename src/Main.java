public class Main {

    public static void main(String[] args) {
        SmallDatabase database = new SmallDatabase();
        Record record1 = new Record(234678L, "������ ���� ��������", 2035.34);
        Record record2 = new Record(1L, "������ ���� ��������", 2035.34);
        Record record3 = new Record(2L, "���� ���� ������", 25.34);
        Record recordClone = new Record(2L, "���� ���� ������", 25.34);

        database.setRecord(record1);
        database.setRecord(record2);
        database.setRecord(record3);
        database.printAll();
        System.out.println("-------------------------------");

        database.setRecord(recordClone);// �� ������ ����������
        database.printAll();

        System.out.println("-------------------------------");

        System.out.println("�������� �������� �� ������ ��������");
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("�������� ������ �� �����");
        System.out.println(database.getRecordByName("������ ���� ��������"));

        System.out.println("-------------------------------");

        System.out.println("�������� ������ �� ��������");
        System.out.println(database.getRecordByValue(2035.34));

        System.out.println("-------------------------------");

        System.out.println("��������� ����� ������");
        database.editNameByAccount(234678L, "������� ������ ���������");
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("��������� �������� ������");
        database.editValueByAccount(234678L, 10000.00);
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("��������� ������");
        Record record4 = new Record(234678L, "���������� ���� ��������", 2035.34);
        database.editRecordByAccount(record4);
        System.out.println(database.getRecordByAccount(234678L));

        System.out.println("-------------------------------");

        System.out.println("�������� �� ��������");
        database.deleteByAccount(234678L);
        database.printAll();

        System.out.println("-------------------------------");

        System.out.println("�������� �� �������������");

        database.deleteByAccount(234678L);
        database.printAll();

        System.out.println("-------------------------------");

        System.out.println("��������� �� �������������");

        database.editValueByAccount(234678L, 22.0);
        database.printAll();

    }
}


import java.util.*;

public class SmallDatabase {
    private Map<Long, Record> database;         // � ������ ������ account �������� pk, �.�. �� �������� ���������� ���
                                                // ������� ������������
    private Map<Double, ArrayList<Record>> valueMap;   // � ������ ���� ����� ������������ ��� ������ �� ��������
                                                       // � ������ ���� � ���������������� ����� ����� ��������� ������
    private Map<String, ArrayList<Record>> nameMap;    // ���� �������� record � ������������ ����������
                                                       // ����� get ArrayList ������ O(1)

    public SmallDatabase() {
        database = new LinkedHashMap<>();       // ��� ������ LinkedHashMap, �.�. ����� ������ ��������
        valueMap = new LinkedHashMap<>();       // � ������ ������ ����� O(log(n))
        nameMap = new LinkedHashMap<>();        // � ������� O(1)
    }

    public void setRecord(Record record){
        if (!database.containsKey(record.getAccount())){//���� ����, �� �� ���������
            database.put(record.getAccount(),record);

            if (!nameMap.containsKey(record.getName())) {
                nameMap.put(record.getName(), new ArrayList<>());
            }
            nameMap.get(record.getName()).add(record);

            if (!valueMap.containsKey(record.getValue())) {
                valueMap.put(record.getValue(), new ArrayList<>());
            }
            valueMap.get(record.getValue()).add(record);
        } else {
            System.out.println(record.getAccount() + " ��� ���� !");
        }

    }


    public Record getRecordByAccount(Long account) {
        try {
            return database.get(account);
        }
        catch (NullPointerException e){
            System.out.println("��� ������ ��������");
        }
        return null;
    }


    public List<Record> getRecordByName(String name){
        try {
            return nameMap.get(name);
        }
        catch (NullPointerException e){
            System.out.println("��� ������ ������");
            return null;
        }
    }


    public List<Record> getRecordByValue(Double value){
        try {
            return valueMap.get(value);
        }
        catch (NullPointerException e){
            System.out.println("��� ������ ��������");
        }
        return null;
    }


    public void deleteByAccount(Long account){
        if (database.containsKey(account)) {
            String name = getRecordByAccount(account).getName();
            Double value = getRecordByAccount(account).getValue();
            Record recordToRemove = new Record(account, name, value);

            getRecordByName(name).remove(recordToRemove); //������� ������ �� ��������������� ������
            getRecordByValue(value).remove(recordToRemove);
            database.remove(account);//������� ������ �� ��

        } else {
            System.out.println("��� ������ �����!");
        }
    }


    public void editNameByAccount(Long account, String newName){
        if (database.containsKey(account)){
            Record recordForEdit = getRecordByAccount(account);

            deleteByAccount(account); // ������� ������ ��������

            recordForEdit.setName(newName);
            setRecord(recordForEdit); // ��������� ����������
        } else {
            System.out.println("��� ������ �����!");
        }

    }


    public void editValueByAccount(Long account, Double newValue){
        if (database.containsKey(account)) {
            Record recordForEdit = getRecordByAccount(account);

            deleteByAccount(account); // ������� ������ ��������

            recordForEdit.setValue(newValue);
            setRecord(recordForEdit); // ��������� ����������
        } else {
            System.out.println("��� ������ �����!");
        }
    }


    public void editNameAndValueByAccount(Long account, String newName, Double newValue){
        editNameByAccount(account,newName);
        editValueByAccount(account, newValue);
    }


    public void editRecordByAccount(Record record){              // �� ����, ��� ����� �������, �������� ������
        editNameByAccount(record.getAccount(),record.getName()); // �������� record ��� �����������. ������ ��� ��������
        editValueByAccount(record.getAccount(), record.getValue());
    }


    public void printAll(){
        for (Map.Entry<Long, Record> record : database.entrySet()) {
            System.out.println(record);
        }
    }
}

import java.util.*;

public class SmallDatabase {
    private Map<Long, Record> database;         // в данном случае account является pk, т.к. он является уникальным для
                                                // каждого пользователя
    private Map<Double, ArrayList<Record>> valueMap;   // в данные мапы будут использованы для поиска по значению
                                                       // в каждой мапе у соответствующего ключа будет храниться список
    private Map<String, ArrayList<Record>> nameMap;    // всех значений record с совпадающими элементами
                                                       // время get ArrayList всегда O(1)

    public SmallDatabase() {
        database = new LinkedHashMap<>();       // был выбран LinkedHashMap, т.к. время поиска элемента
        valueMap = new LinkedHashMap<>();       // в худшем случае будет O(log(n))
        nameMap = new LinkedHashMap<>();        // в среднем O(1)
    }

    public void setRecord(Record record){
        if (!database.containsKey(record.getAccount())){//если есть, то не добавляем
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
            System.out.println(record.getAccount() + " уже есть !");
        }

    }


    public Record getRecordByAccount(Long account) {
        try {
            return database.get(account);
        }
        catch (NullPointerException e){
            System.out.println("Нет такого аккаунта");
        }
        return null;
    }


    public List<Record> getRecordByName(String name){
        try {
            return nameMap.get(name);
        }
        catch (NullPointerException e){
            System.out.println("Нет такого именти");
            return null;
        }
    }


    public List<Record> getRecordByValue(Double value){
        try {
            return valueMap.get(value);
        }
        catch (NullPointerException e){
            System.out.println("Нет такого значения");
        }
        return null;
    }


    public void deleteByAccount(Long account){
        if (database.containsKey(account)) {
            String name = getRecordByAccount(account).getName();
            Double value = getRecordByAccount(account).getValue();
            Record recordToRemove = new Record(account, name, value);

            getRecordByName(name).remove(recordToRemove); //удаляем запись из вспомогательных таблиц
            getRecordByValue(value).remove(recordToRemove);
            database.remove(account);//удаляем запись из бд

        } else {
            System.out.println("Нет такого ключа!");
        }
    }


    public void editNameByAccount(Long account, String newName){
        if (database.containsKey(account)){
            Record recordForEdit = getRecordByAccount(account);

            deleteByAccount(account); // удаляем старое значение

            recordForEdit.setName(newName);
            setRecord(recordForEdit); // вставляем измененное
        } else {
            System.out.println("Нет такого ключа!");
        }

    }


    public void editValueByAccount(Long account, Double newValue){
        if (database.containsKey(account)) {
            Record recordForEdit = getRecordByAccount(account);

            deleteByAccount(account); // удаляем старое значение

            recordForEdit.setValue(newValue);
            setRecord(recordForEdit); // вставляем измененное
        } else {
            System.out.println("Нет такого ключа!");
        }
    }


    public void editNameAndValueByAccount(Long account, String newName, Double newValue){
        editNameByAccount(account,newName);
        editValueByAccount(account, newValue);
    }


    public void editRecordByAccount(Record record){              // не знал, как лучше сделать, изменять данные
        editNameByAccount(record.getAccount(),record.getName()); // объектом record или параметрами. Сделал оба варианта
        editValueByAccount(record.getAccount(), record.getValue());
    }


    public void printAll(){
        for (Map.Entry<Long, Record> record : database.entrySet()) {
            System.out.println(record);
        }
    }
}
public class Record {
    private Long account;
    private String name;
    private double value;

    public Record(Long account, String name, double value) {
        this.account = account;
        this.name = name;
        this.value = value;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Аккаунт = " + account + " имя = " + name + " value = " + value;
    }
}

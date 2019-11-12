import java.util.List;

public class Command {

    private String command;
    private List<Double> list;

    public Command(String command, List<Double> list){
        this.command = command;
        this.list = list;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<Double> getList() {
        return list;
    }

    public void setList(List<Double> list) {
        this.list = list;
    }
}

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LogSimples implements Iterable<String>{
    private List<String> mensagens;

    static LogSimples instance;

    private LogSimples(){
        mensagens = new LinkedList<>();
    }

    static LogSimples getInstance(){
        if (instance == null)
            instance = new LogSimples();
        return instance;
    }

    public void log(String m){
        String logM = LocalDate.now().toString() + " : " + m;
        mensagens.add(logM);
    }

    @Override
    public Iterator<String> iterator() {
        return mensagens.iterator();
    }
}

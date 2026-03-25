public class LogColchetes implements Iterable<String>, Ilog{
    private Ilog LogInterno;

    public LogColchetes(Ilog log){
        LogInterno = log;
    }

    @Override
    public void log(String m) {
        LogInterno.log("[" + m + "]");
    }
    
}

Public class LogColchetes implements Ilog{

    private ILog logInterno;

    public LogColchetes(ILog log){
        this.logInterno = log;
    }

    @Override
    public LogSimples getLog(){
       foreach(String m: logInterno.getLog()){
           logInterno.log("["+m+"]");
       }
       return logInterno.getLog();
    }
}
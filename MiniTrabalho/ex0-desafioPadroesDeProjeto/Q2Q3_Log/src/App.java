public class App {
    public static void main(String[] args) throws Exception {
        //LogSimples log = new LogSimples();
        LogSimples.getInstance().log("Mensagem de alerta 1");
        LogSimples.getInstance().log("Mensagem de alarme 23");
        LogSimples.getInstance().log("Mensagem de PANICO!!");

        for(String m:LogSimples.getInstance()){
            System.out.println(m);
        }
    }
}

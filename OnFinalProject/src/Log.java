
public class Log { //singleton pattern
   private static Log instance;
   
   static String id;
   static String password;
   
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   private Log() {}
   
   public static synchronized Log getInstance() {
      if(instance == null){
         instance = new Log();
      }
      return instance;
   }   
}
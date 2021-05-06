# Event-Management-System
## follow the steps below to run the application
* Download JavaFX 
  * https://gluonhq.com/products/javafx/
  * In IntelliJ IDEA click on File => Project Structure => click on add => choose your javaFX lib directory
* Add VM arguments
  * In IntelliJ IDEA click on Run => Edit Configurations => paste the line below into VM options
   * ``` --module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml  ```
   *  \path\to\javafx-sdk-15.0.1\lib should replaced to the path of JavaFX in your local machine 
* Install dependencies
  * add the .jar files to classpath

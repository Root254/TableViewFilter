package populatingdatafromdatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author user
 */
public class customer_fxml_layoutController implements Initializable {
    
    @FXML
    private TableColumn<Customer, Number> customerIDCol;
    @FXML
    private TableColumn<Customer, String> discountCodeCol;
    @FXML
    private TableColumn<Customer, Number> zipCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> emailCol;
    @FXML
    private TableColumn<Customer, Number> creditLimitCol;
    @FXML
    private TableView<Customer> customerDetailsTable;
     @FXML
    private TextField filterTextField;
    
    ObservableList<Customer> customerData = FXCollections.observableArrayList();
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /**
         * 
         * Fetch customer data from the database 
         * 
         **/
        try {
            Connection con = DBConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select CUSTOMER_ID, DISCOUNT_CODE, ZIP, NAME, EMAIL, CREDIT_LIMIT from CUSTOMER");
            
            while(rs.next()) {
                customerData.add(new Customer(rs.getInt("CUSTOMER_ID"), rs.getString("DISCOUNT_CODE"), rs.getInt("ZIP"),
                        rs.getString("NAME"), rs.getString("EMAIL"), rs.getInt("CREDIT_LIMIT")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(customer_fxml_layoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * 
         * set the columns by telling them what data they are to display
         * 
         **/
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        discountCodeCol.setCellValueFactory(new PropertyValueFactory<>("discountCode"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        creditLimitCol.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
        
        /**
         * 
         * populate the table with data
         * 
         **/
        customerDetailsTable.setItems(customerData);
        
        /**
         * 
         * creating option for search
         * 
         **/
        //1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Customer> filteredData = new FilteredList<>(customerData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                
                // If filter text is empty, display all persons.
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first customer_id, credit_limit, dicount_code and zip of every customer with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(customer.getDiscountCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true;        //Filter matches customer_id
                }
                else if(customer.getCreditLimit().toString().contains(newValue)){
                    return true;        //Filter matches credit_limit
            }
                else if(customer.getCustomerID().toString().contains(newValue)){
                    return true;        //Filter matches dicount_code
            }
                return false; // Does not match.              
            });           
        });
        
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(customerDetailsTable.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        customerDetailsTable.setItems(sortedData);
    }       
}

package FastFootShoesHistoricDataLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.model.MarkUp;
import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.apache.geode.demo.fastfootshoes.repositories.AlertRepository;
import org.apache.geode.demo.fastfootshoes.repositories.CustomerRepository;
import org.apache.geode.demo.fastfootshoes.repositories.MarkUpRepository;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.apache.geode.demo.fastfootshoes.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

/**
 * This class reads CSV files from the class path and loads them into the cluster
 * as Key/Value pairs
 * @author lshannon
 *
 */
@Component
public class CacheLoader {
	
	private static final String RECORDS_ADDED_TO_GEM_FIRE = " Records Added To GemFire: ";
	private ICsvBeanReader beanReader;
	private List<String> errorLog = new ArrayList<String>();
	private List<String> activityLog = new ArrayList<String>();

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	MarkUpRepository markupRepository;
	
	@Autowired
	AlertRepository alertRepository;
	
	private long startTime;
	
	public boolean checkIfHistoricIsThere() {
		return false;
	}
	
	/**
	 * Call this to load the Data, it uses the CSV files in its classpath
	 */
	public void loadData() {
		
		startTime = new Date().getTime();
		
		//load the customers
		String[] nameMapping = new String[]{"city","birthday","id","name", "emailAddress"};
		CellProcessor[] processors = new CellProcessor[] { 
        		new NotNull(), //city
        		new ParseDate("dd-MM-yyyy"), //birthday
                new NotNull(), //id
                new NotNull(), //name
                new NotNull() //email
        };
        loadCustomers("customer.csv",nameMapping,processors);
        
        //load the products
        nameMapping = new String[]{"stockOnHand","wholeSalePrice","brand","type", "color", "size", "gender", "id"};
        processors = new CellProcessor[] { 
        		new ParseInt(),//stockOnHand
        		new ParseDouble(),//wholeSalePrice
        		new NotNull(),//brand
        		new NotNull(),//type
        		new NotNull(),//color
        		new ParseDouble(),//size
        		new NotNull(),//gender
                new NotNull()//productId
        };
        loadProducts("products.csv",nameMapping,processors);
        
        //load the historic transactions - these are just randomly generated and do not respect stock quantity
        nameMapping = new String[]{"customerId","transactionDate","productId","quantity", "retailPrice", "id", "markUp", "orderStatus"};
        processors = new CellProcessor[] { 
        		new NotNull(),//customerId
        		new ParseDate("dd-MM-yyyy"),//transactionDate
        		new NotNull(),//productId
        		new ParseInt(),//quantity
        		new ParseDouble(),//retailsPrice
        		new NotNull(),//transactionId
        		new ParseDouble(),//markUp
        		new NotNull()//order status
        };
        loadTransactions("transactions.csv",nameMapping,processors);
        
        //load the mark ups
        nameMapping = new String[]{"id", "rate","levelName","qualifyingTransactionCountFloor","qualifyingTransactionCountCeiling"};
        processors = new CellProcessor[] {
        		new NotNull(),//id
        		new ParseDouble(),//rate
        		new NotNull(),//levelName
        		new ParseInt(),//qualifyingTransactionCountFloor
        		new ParseInt()//qualifyingTransactionCountCeiling
        };
        loadMarkUps("markUps.csv",nameMapping,processors);
        
        //clean out the alerts
        for (Alert alert : alertRepository.findAll()) {
        	alertRepository.delete(alert);
        }
        long endTime = new Date().getTime();
		long timeToLoad = endTime - startTime;
		activityLog.add("Total Loading Time: " + timeToLoad/1000 + " seconds");
        closeBeanReader();
		writeOutLogs();
	}
	
	private void writeOutLogs() {
		//Data Loading
		System.out.println("*************************************************************");
		System.out.println("********************DATA LOADING SUMMARY*********************");
		System.out.println("*************************************************************");
		for (String message : activityLog) {
			System.out.println(message);
		}
		System.out.println("*************************************************************");
		//Error during loading
		System.out.println("********************ERROR LOGS*******************************");
		System.out.println("*************************************************************");
		if (errorLog.size() <= 0) {
			System.out.println("No errors were recorded");
		}
		else {
			for (String error : errorLog) {
				System.out.println(error);
			}
		}
		System.out.println("*************************************************************");
	}


	private void loadCustomers(String file, String[] nameMapping, CellProcessor[] processors) {
			System.out.println("Started loading the customers");
        	initalizeBeanReader(file); 
            Customer cust;
            int custCount = 0;
            List<Customer> customerPuts = new ArrayList<Customer>();
            try {
				while ((cust = beanReader.read(Customer.class, nameMapping,processors)) != null) {
					customerPuts.add(cust);
					custCount++;
					if (customerPuts.size() % 10 == 0) {
						customerRepository.save(customerPuts);
						customerPuts.clear();
					}
				}
			} catch (IOException e) {
				errorLog.add(e.toString());
			}
            activityLog.add("Customers: Records Read: " + custCount + RECORDS_ADDED_TO_GEM_FIRE + customerRepository.count() );
	}

	
	
	private void loadProducts(String file, String[] nameMapping, CellProcessor[] processors) {
			System.out.println("Started loading the products");
        	initalizeBeanReader(file);
            Product prod;
            List<Product> productPuts = new ArrayList<Product>();
            int prodCount = 0;
            try {
				while ((prod = beanReader.read(Product.class, nameMapping,processors)) != null) {
					productPuts.add(prod);
					prodCount++;
					if (productPuts.size() % 10 == 0) {
						productRepository.save(productPuts);
						productPuts.clear();
					}
				}
			} catch (IOException e) {
				errorLog.add(e.toString());
			}
            activityLog.add("Products: Records Read: " + prodCount + RECORDS_ADDED_TO_GEM_FIRE + productRepository.count() );
	}
	
	private void loadTransactions(String file, String[] nameMapping, CellProcessor[] processors) {
				System.out.println("Started loading the transactions");
	            initalizeBeanReader(file);
	            List<Transaction> transactionPuts = new ArrayList<Transaction>();
	            Transaction txn;
	            int txnCount = 0;
	            try {
					while ((txn = beanReader.read(Transaction.class, nameMapping,processors)) != null) {
						transactionPuts.add(txn);
						txnCount++;
						if (transactionPuts.size() % 10 == 0) {
							transactionRepository.save(transactionPuts);
							transactionPuts.clear();
						}
					}
				} catch (IOException e) {
					errorLog.add(e.toString());
				}
	            activityLog.add("Transactions: Records Read: " + txnCount + RECORDS_ADDED_TO_GEM_FIRE + transactionRepository.count());
	}
	
	private void loadMarkUps(String file,String[] nameMapping, CellProcessor[] processors) {
			System.out.println("Started loading the mark ups");
            initalizeBeanReader(file);
            MarkUp markUp;
            List<MarkUp> markUpPuts = new ArrayList<MarkUp>();
            int markUpCount = 0;
            try {
				while ((markUp = beanReader.read(MarkUp.class, nameMapping,processors)) != null) {
					markUpPuts.add(markUp);
					markUpCount++;
					markupRepository.save(markUpPuts);
				}
			} catch (IOException e) {
				errorLog.add(e.toString());
			}
            activityLog.add("Mark Ups: Records Read: " + markUpCount + RECORDS_ADDED_TO_GEM_FIRE + markupRepository.count());
	}

	private void initalizeBeanReader(String file) {
		beanReader = new CsvBeanReader(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(file))),CsvPreference.STANDARD_PREFERENCE);
		try {
			@SuppressWarnings("unused")
			final String[] header = beanReader.getHeader(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void closeBeanReader() {
		if (beanReader != null) {
		    try {
				beanReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}

package org.apache.geode.demo.fastfootshoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.apache.geode.demo.fastfootshoes.repositories.AlertRepository;
import org.apache.geode.demo.fastfootshoes.repositories.CustomerRepository;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.apache.geode.demo.fastfootshoes.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

@Component
@EnableScheduling
public class CacheLoader {

	private ICsvBeanReader beanReader;
	private List<String> errorLog = new ArrayList<String>();
	private List<String> activityLog = new ArrayList<String>();
	private Map<String, Transaction> current_transactions = new HashMap<String, Transaction>();
	private int currentTransactionCount = 0;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AlertRepository alertRespository;
	
	private List<Transaction> transactionPuts = new ArrayList<Transaction>();

	public void loadData() {
		System.out.println("Loading the Data");
		// load the current transactions
		String[] nameMapping = new String[] { "customerId", "productId",
				"quantity", "retailPrice", "id", "markUp", "orderStatus" };
		CellProcessor[] processors = new CellProcessor[] { new NotNull(),// customerId
				new NotNull(),// productId
				new ParseInt(),// quantity
				new ParseDouble(),// retailsPrice
				new NotNull(),// transactionId
				new ParseDouble(),// markUp
				new NotNull() // order status
		};
		loadCurrentTransactions("current_transactions.csv", nameMapping,
				processors);
		System.out
				.println("*************************************************************");
		System.out
				.println("********************PLAYING TRANSACTIONS*********************");
		System.out
				.println("*************************************************************");
		start();
	}

	private void writeOutLogs() {
		// Data Loading
		System.out
				.println("*************************************************************");
		System.out
				.println("********************TRANSACTION LOADING SUMMARY**************");
		System.out
				.println("*************************************************************");
		for (String message : activityLog) {
			System.out.println(message);
		}
		System.out
				.println("*************************************************************");
		// Error during loading
		System.out
				.println("********************ERROR LOGS*******************************");
		System.out
				.println("*************************************************************");
		if (errorLog.size() <= 0) {
			System.out.println("No errors were recorded");
		} else {
			for (String error : errorLog) {
				System.out.println(error);
			}
		}
		System.out
				.println("*************************************************************");
		closeBeanReader();
	}

	private void loadCurrentTransactions(String file, String[] nameMapping,
			CellProcessor[] processors) {
		System.out.println("Started loading the transactions");
		initalizeBeanReader(file);
		Transaction txn;
		int txnCount = 0;
		try {
			while ((txn = beanReader.read(Transaction.class, nameMapping,
					processors)) != null) {
				current_transactions.put(txn.getId(), txn);
				txnCount++;
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		System.out
				.println("Current Transactions: Records Queued to be played later: "
						+ txnCount);
	}

	private void initalizeBeanReader(String file) {
		beanReader = new CsvBeanReader(new BufferedReader(
				new InputStreamReader(getClass().getClassLoader()
						.getResourceAsStream(file))),
				CsvPreference.STANDARD_PREFERENCE);
		try {
			@SuppressWarnings("unused")
			final String[] header = beanReader.getHeader(true);
		} catch (IOException e1) {
			errorLog.add(e1.toString());
		}
	}

	private void closeBeanReader() {
		if (beanReader != null) {
			try {
				beanReader.close();
			} catch (IOException e) {
				errorLog.add(e.toString());
			}
		}
	}

	@Scheduled(fixedRate = 5000)
	private void start() {
		if (currentTransactionCount < current_transactions.size()) {
			Transaction txn;
			for (int i = 0; i < 25; i++) {
				String key = (String) current_transactions.keySet().toArray()[currentTransactionCount];
				txn = current_transactions.get(key);
				txn.setTransactionDate(new Date());
				// check if there is enough quantity
				Product product = productRepository
						.findById(txn.getProductId());
				Customer customer = customerRepository.findById(txn
						.getCustomerId());
				// some of the ID for customers are not assigned to a customer,
				// need to fix the talend job
				if (customer != null && product != null
						&& product.getStockOnHand() - txn.getQuantity() > 0) {
					writeTransaction(txn, product, new Double(10.0));
				}
				// was not enough stock - log it in an alert
				else {
					if (customer != null) {
						Alert alert = new Alert();
						alert.setId("Lack of stock: " + new Date().getTime());
						alert.setMessage("Missed an order due to lack of stock! Product Id: "
								+ txn.getProductId()
								+ " Quantity: "
								+ txn.getQuantity()
								+ " Customer Id: "
								+ txn.getCustomerId());
						alert.setMessageDate(new Date());
						alertRespository.save(alert);
					}
					else {
						System.out.println("Order for customer that does not exists");
					}
				}
				currentTransactionCount++;
			}
			System.out.println("Added 25 transactions: "
					+ currentTransactionCount + " out of "
					+ current_transactions.size());
		} else {
			System.out.println("No transactions left to load");
			System.out
					.println("*************************************************************");
			System.out
					.println("********************COMPLETED TRANSACTIONS*******************");
			System.out
					.println("*************************************************************");
			writeOutLogs();
		}
	}

	private void writeTransaction(Transaction txn, Product product,
			double markupValue) {
		// create the transaction
		txn.setMarkUp(markupValue);
		txn.setRetailPrice(product.getWholeSalePrice() * markupValue);
		txn.setTransactionDate(new Date());
		// commit the transaction
		transactionPuts.add(txn);
		if (transactionPuts.size() % 10 == 0) {
			transactionRepository.save(transactionPuts);
			transactionPuts.clear();
		}
	}

}

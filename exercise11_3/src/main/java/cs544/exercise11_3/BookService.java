package cs544.exercise11_3;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class BookService implements IBookService {
	public List<IBookSupplier> suppliers ;

	public BookService(List<IBookSupplier> suppliers) {
		this.suppliers = suppliers;
	}

	public BookService() {
//		@Value("amazon")
//		IBookSupplier amazon ;
//		IBookSupplier barnesandnoble = new BarnesAndNoble();
//		IBookSupplier ebooks = new EBooks();
//
//		suppliers.add(amazon);
//		suppliers.add(barnesandnoble);
//		suppliers.add(ebooks);
	}

	public void buy(Book book) {
		double lowestPrice = 0;
		IBookSupplier cheapestSupplier = null;
		// find the cheapest supplier
		for (IBookSupplier supplier : suppliers) {
			double price = supplier.computePrice(book.getIsbn());
			if (cheapestSupplier == null) {
				cheapestSupplier = supplier;
				lowestPrice = price;
			} else {
				if (price < lowestPrice) {
					cheapestSupplier = supplier;
					lowestPrice = price;
				}
			}
		}
		// buy with the cheapest supplier
		if (cheapestSupplier != null) {
			cheapestSupplier.order(book);
		}

	}

	public void setSuppliers(List<IBookSupplier> suppliers) {
		this.suppliers=suppliers;
	}
}
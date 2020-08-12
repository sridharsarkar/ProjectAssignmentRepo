package page;

public interface homePage {
	
	
	public void launchURL(String URL) throws Exception;

	public void closethePopup();

	public void goToSection(String section);

	public void shopByScreenSize(String size);

	public void priceRangeSelect(String min, String max) throws InterruptedException;

	public void getTotalNumberOfItemsShowing() throws InterruptedException;

	public void addThreeTVtoCompare();

	public void compareItems() throws Exception;

	void closethePopup1();

	void selectMinimumPriceFromCompareList() throws Exception;

	void clickOnHelpCenter() throws Exception;

	void clickButtonPlaceOrder() throws Exception;

	void resolutionSelect() throws InterruptedException;




}

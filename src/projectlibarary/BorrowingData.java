package projectlibarary;

public class BorrowingData {
    private String borrowingID;
    private String userID;
    private String bookID;
    private String title;
    private String borrowDate;
    private String returnDate;
    private String returnStatus;
    private String allowedBorrowRange;
    private String calculatedReturnDate;

    public BorrowingData(String borrowingID, String userID, String bookID, String title, String borrowDate, String returnDate, String returnStatus, 
                         String allowedBorrowRange, String calculatedReturnDate  ) {
        this.borrowingID = borrowingID;
        this.userID = userID;
        this.bookID = bookID;
        this.title = title;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returnStatus = returnStatus;
        this.allowedBorrowRange = allowedBorrowRange;
        this.calculatedReturnDate = calculatedReturnDate;
    }

    public String getBorrowingID() {
        return borrowingID;
    }

    public void setBorrowingID(String borrowingID) {
        this.borrowingID = borrowingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String status) {
        this.returnStatus = returnStatus;
    }

    public String getAllowedBorrowRange() {
        return allowedBorrowRange;
    }

    public void setAllowedBorrowRange(String allowedBorrowRange) {
        this.allowedBorrowRange = allowedBorrowRange;
    }
    
    public String getCalculatedReturnDate() {
        return calculatedReturnDate;
    }

    public void setCalculatedReturnDate(String calculatedReturnDate) {
        this.calculatedReturnDate = calculatedReturnDate;
    }

}

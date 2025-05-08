
public class Book {
        private String id;
        private String title;
        private String author;
        private boolean isAvailable;
    
        public Book(String id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isAvailable = true; // By default, the book is available
        }
    
        // Getters and Setters
        public String getId() {
            return id;
        }
    
        public String getTitle() {
            return title;
        }
    
        public String getAuthor() {
            return author;
        }
    
        public boolean isAvailable() {
            return isAvailable;
        }
    
        public void setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }
    
        @Override
        public String toString() {
            return "Book id=" + id + "title=" + title + " author=" + author + " isAvailable=" + isAvailable ;
        }
    }

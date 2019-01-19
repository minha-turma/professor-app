package minhaturma.ufrpe.br.minhaturma.news;

public class News {

    private String title;
    private String integralText;
    private String author;
    private String resume;

    public News(String title){
        this.title = title;
    }

    public News(String title, String integralText, String author, String resume) {
        this.title = title;
        this.integralText = integralText;
        this.author = author;
        this.resume = resume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getintegralText() {
        return integralText;
    }

    public void setintegralText(String integralText) {
        this.integralText = integralText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}

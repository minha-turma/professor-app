package minhaturma.ufrpe.br.minhaturma;

public class NewsModel {

    private String title;
    private String integralText;
    private String author;
    private String resume;

    public NewsModel(String title){
        this.title = title;
    }

    public NewsModel(String title, String integralText, String author, String resume) {
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
